# [Windows系统下的TCP参数优化](https://www.cnblogs.com/olartan/p/4268269.html)

**1****.** **TCP****连接的状态**

　　首先介绍一下TCP连接建立与关闭过程中的状态。TCP连接过程是状态的转换，促使状态发生转换的因素包括用户调用、特定数据包以及超时等，具体状态如下所示：

- **CLOSED****：**初始状态，表示没有任何连接。
- **LISTEN****：**Server端的某个Socket正在监听来自远方的TCP端口的连接请求。
- **SYN_SENT****：**发送连接请求后等待确认信息。当客户端Socket进行Connect连接时，会首先发送SYN包，随即进入SYN_SENT状态，然后等待Server端发送三次握手中的第2个包。
- **SYN_RECEIVED****：**收到一个连接请求后回送确认信息和对等的连接请求，然后等待确认信息。通常是建立TCP连接的三次握手过程中的一个中间状态，表示Server端的Socket接收到来自Client的SYN包，并作出回应。
- **ESTABLISHED****：**表示连接已经建立，可以进行数据传输。
- **FIN_WAIT_1****：**主动关闭连接的一方等待对方返回ACK包。若Socket在ESTABLISHED状态下主动关闭连接并向对方发送FIN包（表示己方不再有数据需要发送），则进入FIN_WAIT_1状态，等待对方返回ACK包，此后还能读取数据，但不能发送数据。在正常情况下，无论对方处于何种状态，都应该马上返回ACK包，所以FIN_WAIT_1状态一般很难见到。
- **FIN_WAIT_2****：**主动关闭连接的一方收到对方返回的ACK包后，等待对方发送FIN包。处于FIN_WAIT_1状态下的Socket收到了对方返回的ACK包后，便进入FIN_WAIT_2状态。由于FIN_WAIT_2状态下的Socket需要等待对方发送的FIN包，所有常常可以看到。若在FIN_WAIT_1状态下收到对方发送的同时带有FIN和ACK的包时，则直接进入TIME_WAIT状态，无须经过FIN_WAIT_2状态。
- **TIME_WAIT****：**主动关闭连接的一方收到对方发送的FIN包后返回ACK包（表示对方也不再有数据需要发送，此后不能再读取或发送数据），然后等待足够长的时间（2MSL）以确保对方接收到ACK包（考虑到丢失ACK包的可能和迷路重复数据包的影响），最后回到CLOSED状态，释放网络资源。
- **CLOSE_WAIT****：**表示被动关闭连接的一方在等待关闭连接。当收到对方发送的FIN包后（表示对方不再有数据需要发送），相应的返回ACK包，然后进入CLOSE_WAIT状态。在该状态下，若己方还有数据未发送，则可以继续向对方进行发送，但不能再读取数据，直到数据发送完毕。
- **LAST_ACK****：**被动关闭连接的一方在CLOSE_WAIT状态下完成数据的发送后便可向对方发送FIN包（表示己方不再有数据需要发送），然后等待对方返回ACK包。收到ACK包后便回到CLOSED状态，释放网络资源。
- **CLOSING****：**比较罕见的例外状态。正常情况下，发送FIN包后应该先收到（或同时收到）对方的ACK包，再收到对方的FIN包，而CLOSING状态表示发送FIN包后并没有收到对方的ACK包，却已收到了对方的FIN包。有两种情况可能导致这种状态：其一，如果双方几乎在同时关闭连接，那么就可能出现双方同时发送FIN包的情况；其二，如果ACK包丢失而对方的FIN包很快发出，也会出现FIN先于ACK到达。

　　**TCP****连接的状态转换如下图所示**

![img](https://images0.cnblogs.com/blog/43891/201502/021742387189895.png)

![img](https://images0.cnblogs.com/blog/43891/201502/021742518285634.png)

 

**2****.** **TCP****连接的关闭方式**

　　建立TCP连接需要三次握手，而关闭连接则需要四次握手，并且分为主动关闭和被动关闭。这是由于TCP连接是全双工的，我关了你的连接，并不等于你关了我的连接，因此双方都必须单独进行关闭。当一方完成它的数据发送任务后可以发送FIN包来终止这个方向的连接，表明自己不再有数据需要发送；收到FIN包的那一方虽然不能再读取数据，但仍能发送数据。以Client主动关闭连接为例：

1. Client向Server发送FIN包，表示Client主动关闭连接，然后进入FIN_WAIT_1状态，等待Server返回ACK包。此后Client不能再向Server发送数据，但能读取数据。
2. Server收到FIN包后向Client发送ACK包，然后进入CLOSE_WAIT状态，此后Server不能再读取数据，但可以继续向Client发送数据。Client收到Server返回的ACK包后进入FIN_WAIT_2状态，等待Server发送FIN包。
3. Server完成数据的发送后，将FIN包发送给Client，然后进入LAST_ACK状态，等待Client返回ACK包，此后Server既不能读取数据，也不能发送数据。
4. Client收到FIN包后向Server发送ACK包，然后进入TIME_WAIT状态，接着等待足够长的时间（2MSL）以确保Server接收到ACK包，最后回到CLOSED状态，释放网络资源。Server收到Client返回的ACK包后便回到CLOSED状态，释放网络资源。

　　TCP连接的建立到关闭，需要经历以下状态迁移（假定Client发起连接，并主动关闭连接）：

- **Client**

　　CLOSED -> SYN_SENT -> ESTABLISHED -> FIN_WAIT_1 -> FIN_WAIT_2 -> TIME_WAIT -> CLOSED

- **Server**

　　CLODES -> LISTEN -> SYN_RECEIVED -> ESTABLISHED -> CLOSE_WAIT -> LAST_ACK -> CLOSED

**3****. 对****Server****与****Client****的影响**

　　在详细了解TCP连接的状态和关闭方式后，我们会发现TIME_WAIT状态是一个坑爹的存在！主动关闭连接的一方在发送最后一个ACK包后，无论对方是否收到都会进入TIME_WAIT状态，等待2MSL的时间，然后才能释放网络资源。MSL就是Maximum Segment Lifetime（数据包的最大生命周期），是一个数据包能在互联网上生存的最长时间，若超过这个时间则该数据包将会消失在网络中。操作系统通常会将2MSL设为4分钟，最低不少于30秒，因而TIME_WAIT状态一般维持在30秒至4分钟。这个是TCP/IP协议必不可少的，是TCP/IP设计者设计的，也就是无法解决的。TIME_WAIT状态的存在主要有两个原因：

1. **可靠地实现****TCP****全双工连接的终止。**在关TCP闭连接时，最后的ACK包是由主动关闭方发出的，如果这个ACK包丢失，则被动关闭方将重发FIN包，因此主动方必须维护状态信息，以允许它重发这个ACK包。如果不维持这个状态信息，那么主动方将回到CLOSED状态，并对被动方重发的FIN包响应RST包，而被动关闭方将此包解释成一个错误（在Java中会抛出connection reset的SocketException)。因而，要实现TCP全双工连接的正常终止，必须能够处理四次握手协议中任意一个包丢失的情况，主动关闭方必须维持状态信息进入TIME_WAIT状态。
2. **确保迷路重复数据包在网络中消失，防止上一次连接中的包迷路后重新出现，影响新连接。**TCP数据包可能由于路由器异常而迷路，在迷路期间，数据包发送方可能因超时而重发这个包，迷路的数据包在路由器恢复后也会被送到目的地，这个迷路的数据包就称为Lost Duplicate。在关闭一个TCP连接后，如果马上使用相同的IP地址和端口建立新的TCP连接，那么有可能出现前一个连接的迷路重复数据包在前一个连接关闭后再次出现，影响新建立的连接。为了避免这一情况，TCP协议不允许使用处于TIME_WAIT状态的连接的IP和端口启动一个新连接，只有经过2MSL的时间，确保上一次连接中所有的迷路重复数据包都已消失在网络中，才能安全地建立新连接。

　　对于Client而言，每个连接都需要占用一个端口，而系统允许的可用端口数不足65000个（这也是在TCP参数优化后才能达到）。因此，如果Client发起过多的连接并主动关闭（假设没有重用端口或者连接多个Server），就会有大量的连接在关闭后处于TIME_WAIT状态，等待2MSL的时间后才能释放网络资源（包括端口），于是Client会由于缺少可用端口而无法新建连接。

　　对Server而言（特别是处理高并发短连接的Server），Server端与Client建立的连接是使用同一个端口的，即监听的端口，每个连接通过一个五元组区分，包括源IP地址、源端口、传输层协议号（协议类型）、目的IP地址、目的端口，因而在理论上，Server不受系统端口数的限制。但是，Server对每个端口上的连接数是有限制的，它要使用哈希表记录端口上的每个连接，并受到文件描述符的最大打开数的限制。所以，如果Server主动关闭连接，同样会有大量的连接在关闭后处于TIME_WAIT状态，等待2MSL的时间后才能释放网络资源（包括哈希表上的连接记录和文件描述符），于是Server会由于达到哈希表和文件描述符的限制而无法接受新连接，造成性能的急剧下滑，性能曲线会持续产生严重的波动。对于这种情况，有三种应对方式：

1. 试图让Client主动关闭连接，由于每个Client的并发量都比较低，因而不会产生性能瓶颈。
2. 优化Server的系统TCP参数，使其网络资源的最大值、消耗速度和恢复速度达到平衡。
3. 改写TCP协议，重新实现底层代码，不过该方式难度很大，而且系统的稳定性和安全性可能受到影响。

**4.** **TCPWindowSize**

　　TCPWindowSize的值表示TCP的窗口大小。TCP Receive Window（TCP数据接收缓冲）定义了发送端在没有获得接收端的确认信息的状态下可以发送的最大字节数。此数值越大，返回的确认信息就越少，相应的在发送端和接收端之间的通信就越好。此数值较小时可以降低发送端在等待接收端返回确认信息时发生超时的可能性，但这将增加网络流量，降低有效吞吐率。TCP在发送端和接收端之间动态调整一个最大段长度MSS（Maximum Segment Size）的整数倍。MSS在连接开始建立时确定，由于TCP Receive Window被调整为MSS的整数倍，在数据传输中完全长度的TCP数据段的比例增加，故而提高了网络吞吐率。

　　缺省情况下，TCP将试图根据MSS来优化窗口大小，起始值为16KB，最大值为64KB。TCPWindowSize的最大值通常为65535字节（64KB），以太网最大段长度为1460字节，低于64KB的1460的最大整数倍为62420字节，因而可以在注册表中将TCPWindowSize设置为62420，作为高带宽网络中适用的性能优化值。具体操作如下：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为TCPWindowSize的REG_DWORD值，该值的范围是从0到65535，将该值设置为62420。

**5****.** **TCP1323Opts**　

　　为了更高效地利用高带宽网络，可以使用比上述TCP窗口大得多的TCP窗口大小，此特性是Windows 2000和Windows Server 2003中的新特性，称为TCP Window Scaling，它将以前的65535字节（64KB）的限制提高到了1073741824字节（1GB）。在带宽与延迟的乘积值很高的连接上（例如卫星连接），可能需要将窗口的大小增加到64KB以上。使用TCP Window Scaling，系统可以允许确认信息间更大数据量的传输，增加了网络吞吐量及性能。发送端和接收端往返通信所需的时间被称为回环时间（RTT）。TCP Window Scaling仅在TCP连接的双方都开启时才真正有效。TCP有一个时间戳选项，通过更加频繁地计算来提高RTT值的估测值，此选项特别有助于估测更长距离的广域网上连接的RTT值，并更加精确地调整TCP重发超时时间。时间戳在TCP报头提供了两个区域，一个记录开始重发的时间，另一个记录接收到的时间。时间戳对于TCP Window Scaling，即确认信息收到前的大数据包传送特别有用，激活时间戳仅仅在每个数据包的头部增加12字节，对网络流量的影响微乎其微。数据完整性与数据吞吐率最大化哪个更为重要是个需要评估的问题。在某些环境中，例如视频流传输，需要更大的TCP窗口，这是最重要的，而数据完整性排在第二位。在这种环境中，TCP Window Scaling可以不打开时间戳。当发送端和接收端均激活TCP Window Scaling和时间戳时，此特性才有效。不过，若在发包时加入了时间戳，经过NAT之后，如果前面相同的端口被使用过，且时间戳大于这个连接发出的SYN中的时间戳，就会导致服务器忽略该SYN，表现为用户无法正常完成TCP的3次握手。初始时生成小的TCP窗口，之后窗口大小将按照内部算法增大。具体操作如下：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为TCP1323Opts的REG_DWORD值，该值的具体含义为：0（缺省值）表示禁用TCP Window Scaling和时间戳；1表示只启用TCP Window Scaling；2表示只启用时间戳；3表示同时启用TCP Window Scaling和时间戳。TCP1323Opts设置为激活TCP Window Scaling后，可以将上文中的注册表项TCPWindowSize的值增大，最大能达到1GB，为了达到最佳性能，这里的值最好设置成MSS的倍数，推荐值为256960字节。

**6****.** **TCP** **控制块表**

　　对于每个TCP连接，控制变量保存在一个称为TCP控制块（TCB）的内存块中。TCB表的大小由注册表项MaxHashTableSize控制。在活动连接很多的系统中，设定一个较大的表可以降低系统定位TCB表的时间。在TCB表上分区可以降低对表的访问的争夺。增加分区的数量，TCP的性能会得到优化，特别是在多处理器的系统上。注册表项NumTcbTablePartitions控制分区的数量，默认是处理器个数的平方。TCB通常预置在内存中，以防止TCP反复连接和断开时，TCB反复重新定位浪费时间，这种缓冲的方式促进了内存管理，但同时也限制了同一时刻允许的TCP连接数量。注册表项MaxFreeTcbs决定了处于空闲等待状态的TCB重新可用之前的连接数量，在NT架构中常设置成高于默认值，以确保有足够的预置的TCB。从Windows 2000开始添加了一个新特性，降低超出预置TCB运行的可能性。如果处于等待状态的连接多于MaxFreeTWTcbs中的设置，所有等待时间超过60秒的连接将被强制关闭，以后再次启用。此特性合并到Windows 2000 Server和Windows Server 2003后，MaxFreeTcbs将不再用于优化性能。具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为MaxHashTableSize的REG_DWORD值，该值的范围是从1到65536，并且必须为2的N次方，缺省值为512，建议设为8192。然后在Parameters子键下创建或修改名为NumTcbTablePartitions的REG_DWORD值，该值的范围是从1到65536，并且必须为2的N次方，缺省值为处理器个数的平方，建议设为处理器核心数的4倍。

**7****.** **TcpTimedWaitDelay**

　　TcpTimedWaitDelay的值表示系统释放已关闭的TCP连接并复用其资源之前，必须等待的时间。这段时间间隔就是以前的Blog中提到的TIME_WAIT状态（2MSL，数据包最长生命周期的两倍状态）。如果系统显示大量连接处于TIME_WAIT状态，则会导致并发量与吞吐量的严重下降，通过减小该项的值，系统可以更快地释放已关闭的连接，从而为新连接提供更多的资源，特别是对于高并发短连接的Server具有积极的意义。

　　该项的缺省值是240，即等待4分钟后释放资源；系统支持的最小值为30，即等待时间为30秒。具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为TcpTimedWaitDelay的REG_DWORD值，该值的范围是从0到300，建议将该值设置为30。

**8****.** **MaxUserPort**

　　MaxUserPort的值表示当应用程序向系统请求可用的端口时，TCP/IP可分配的最大端口号。如果系统显示建立连接时出现异常，那么有可能是由于匿名（临时）端口数不够导致的，特别是当系统打开大量端口来与Web service、数据库或其他远程资源建立连接时。

　　该项的缺省值是十进制的5000，这也是系统允许的最小值。Windows默认为匿名（临时）端口保留的端口号范围是从1024到5000。为了获得更高的并发量，建议将该值至少设为32768以上，甚至设为理论最大值65534，特别是对于模拟高并发测试环境的Client具有积极的意义。具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为MaxUserPort的REG_DWORD值，该值的范围是从5000到65534，缺省值为5000，建议将该值设置为65534。

**9****. 动态储备**

　　动态储备的值使系统能自动调整其配置，以接受大量突发的连接请求。如果同时接收到大量连接请求，超出了系统的处理能力，那么动态储备就会自动增大系统支持的暂挂连接的数量（即Client已请求而Server尚未处理的等待连接数，TCP连接的总数包括已连接数与等待连接数），从而可减少连接失败的数量。系统的处理能力和支持的暂挂连接的数量不足时，Client的连接请求将直接被拒绝。

　　缺省情况下，Windows 不启用动态储备，可以通过以下操作进行开启和设置：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\AFD\Parameters注册表子键，在Parameters子键下创建或修改下列名称的REG_DWORD值。

- EnableDynamicBacklog，值为1，表示开启动态储备。
- MinimumDynamicBacklog，值为128，表示支持的最小暂挂连接的数量为128。
- MaximumDynamicBacklog，值为2048，表示支持的最大暂挂连接的数量为2048。对于高并发短连接的Server，建议最大值设为1024及以上。
- DynamicBacklogGrowthDelta，值为128，表示支持的暂挂连接的数量的增量为128，即数量不足时自增长128，直到达到设定的最大值，如2048。

**10****.** **KeepAliveTime**

　　KeepAliveTime的值控制系统尝试验证空闲连接是否仍然完好的频率。如果该连接在一段时间内没有活动，那么系统会发送保持连接的信号，如果网络正常并且接收方是活动的，它就会响应。如果需要对丢失接收方的情况敏感，也就是说需要更快地发现是否丢失了接收方，请考虑减小该值。而如果长期不活动的空闲连接的出现次数较多，但丢失接收方的情况出现较少，那么可能需要增大该值以减少开销。

　　缺省情况下，如果空闲连接在7200000毫秒（2小时）内没有活动，系统就会发送保持连接的消息。 通常建议把该值设为1800000毫秒，从而丢失的连接会在30分钟内被检测到。具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为KeepAliveTime的REG_DWORD值，为该值设置适当的毫秒数。

**11****.** **KeepAliveInterval**

　　KeepAliveInterval的值表示未收到另一方对“保持连接”信号的响应时，系统重复发送“保持连接”信号的频率。在无任何响应的情况下，连续发送“保持连接”信号的次数超过TcpMaxDataRetransmissions（下文将介绍）的值时，将放弃该连接。如果网络环境较差，允许较长的响应时间，则考虑增大该值以减少开销；如果需要尽快验证是否已丢失接收方，则考虑减小该值或TcpMaxDataRetransmissions值。

　　缺省情况下，在未收到响应而重新发送“保持连接”的信号之前，系统会等待1000毫秒（1秒），可以根据具体需求修改，具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为KeepAliveInterval的REG_DWORD值，为该值设置适当的毫秒数。

**12****.** **TcpMaxDataRetransmissions**

　　TcpMaxDataRetransmissions的值表示TCP数据重发，系统在现有连接上对无应答的数据段进行重发的次数。如果网络环境很差，可能需要提高该值以保持有效的通信，确保接收方收到数据；如果网络环境很好，或者通常是由于丢失接收方而导致数据的丢失，那么可以减小该值以减少验证接收方是否丢失所花费的时间和开销。

　　缺省情况下，系统会重新发送未返回应答的数据段5次，可以根据具体需求修改，具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为TcpMaxDataRetransmissions的REG_DWORD值，该值的范围是从0到4294967295，缺省值为5，根据实际情况进行设置。

**13****.** **TcpMaxConnectRetransmisstions**

　　TcpMaxConnectRetransmisstions的值表示TCP连接重发，TCP退出前重发非确认连接请求（SYN）的次数。对于每次尝试，重发超时是成功重发的两倍。在Windows Server 2003中默认超时次数是2，默认超时时间为3秒（在注册表项TCPInitialRTT中）。速度较慢的WAN连接中超时时间可相应增加，不同环境中可能会有不同的最优化设置，需要在实际环境中测试确定。超时时间不要设置太大否则将不会发生网络连接超时时间。具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters注册表子键，在Parameters子键下创建或修改名为TcpMaxConnectRetransmisstions的REG_DWORD值，该值的范围是从0到255，缺省值为2，根据实际情况进行设置。然后在Parameters子键下创建或修改名为TCPInitialRTT的REG_DWORD值，同样根据实际情况进行设置。

**14****.** **TcpAckFrequency**

　　TcpAckFrequency的值表示系统发送应答消息的频率。如果值为2，那么系统将在接收到2个分段之后发送应答，或是在接收到1个分段但在200毫秒内没有接收到任何其他分段的情况下发送应答；如果值为3，那么系统将在接收到3个分段之后发送应答，或是在接收到1个或2个分段但在200毫秒内没有接收到任何其他分段的情况下发送应答，以此类推。如果要通过消除应答延迟来缩短响应时间，那么建议将该值设为1。在此情况下，系统会立即发送对每个分段的应答；如果连接主要用于传输大量数据，而200毫秒的延迟并不重要，那么可以减小该值以降低应答的开销。

　　缺省情况下，系统将该值设为2，即每隔一个分段应答一次。该值的有效范围是0到255，其中0表示使用缺省值2，可以根据具体需求修改，具体操作：

　　浏览至HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\TCPIP\Parameters\Interfaces\xx（xx由网络适配器决定）注册表子键，在xx子键下创建或修改名为TcpAckFrequency的REG_DWORD值，该值的范围是从1到13，缺省值为2，根据希望每发送几个分段返回一个应答而设置该值，建议百兆网络设为5，千兆网络设为13。