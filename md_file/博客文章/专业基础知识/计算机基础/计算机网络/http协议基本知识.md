

#### REF:[Android网络编程（一，二，三）HTTP协议原理 - 刘望舒](http://blog.csdn.net/itachi85/article/details/50982995)

#### REF:[http/https的区别](http://www.cnblogs.com/wxlzhizu/archive/2009/12/09/1620005.html)
#### REF:[okhttp访问https站点的安全验证详解 - hongyang](http://blog.csdn.net/lmj623565791/article/details/48129405)

### 个人笔记：
-------------------------------------
#### http的特点：
* C/S模式
* 通过GET,HEAD,POST等方法向服务器发送请求，这些方法各自拥有不同的特点和用途
* 可以传输任何类型的对象，通过Content-Type标记
* http响应是限制每次连接只处理一个请求，完成交互即自动断开连接，这样的方式非常节省服务器的效率和传输时间
* http协议对于事物处理没有记忆能力，缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快

#### http URL格式及其代表的含义
`http://host[":"port][abs_path]`  
* http 表示使用http协议来处理网络资源
* host 表示Internet主机域名或者IP地址
* post 表示一个端口号，为空则使用默认端口80
* abs_path 表示请求资源的URI（Web上任意的可用资源）。 

#### 在http协议中有两种报文，请求报文和响应报文，用途非常明显
* 请求报文
* 
![请求报文的一般格式](http://img.blog.csdn.net/20160326141150626)

> 如图所示，请求报文包括 请求行，请求报头，空行，请求数据 四个部分


##### 请求行
> 请求行的格式： Method Request-URI HTTP-Version CRLF

> Method表示请求方法；Request-URI是资源标识符；HTTP-Version表示请求的HTTP协议版本；CRLF表示回车和换行
> > HTTP请求方法有8种,分别是GET、POST、DELETE、PUT、HEAD、TRACE、CONNECT 、OPTIONS。其中PUT、DELETE、POST、GET分别对应着增删改查，对于移动开发最常用的就是POST和GET
> > * 1.GET：请求获取Request-URI所标识的资源
> > * 2.POST：在Request-URI所标识的资源后附加新的数据
> > * 3.HEAD 请求获取由Request-URI所标识的资源的响应消息报头
> > * 4.PUT 请求服务器存储一个资源，并用Request-URI作为其标识
> > * 5.DELETE 请求服务器删除Request-URI所标识的资源
> > * 6.TRACE 请求服务器回送收到的请求信息，主要用于测试或诊断
> > * 7.CONNECT 保留将来使用
> > * 8.OPTIONS 请求查询服务器的性能，或者查询与资源相关的选项和需求
> > * eg :  `GET http://github.com HTTP/1.1`  

##### 请求报头
> * 在请求行之后会有0个或者多个请求报头，每个请求报头都包含一个名字和一个值，它们之间用“：”分割。请求头部会以一个空行，发送回车符和换行符，通知服务器以下不会有请求头

##### 请求数据
> * 请求数据不在GET方法中使用，而是在POST方法中使用。POST方法适用于需要客户填写表单的场合，与请求数据相关的最常用的请求头是Content-Type和Content-Length

#### 响应报文:服务器对特定请求返回的数据内容
![http响应报文](http://img.blog.csdn.net/20160327135121539)

> 类似请求报文，HTTP的响应报文由状态行、消息报头、空行、响应正文组成

> 状态行格式：`HTTP-Version Status-Code Reason-Phrase CRLF`  
> > * HTTP-Version表示服务器HTTP协议的版本
> > * Status-Code表示服务器发回的响应状态代码
> > * Reason-Phrase表示状态代码的文本描述
> > 
> > * 状态码的可能取值及代表含义：
> > * •100~199：指示信息，表示请求已接收，继续处理
> > * •200~299：请求成功，表示请求已被成功接收、理解、接受  （常用200）
> > * •300~399：重定向，要完成请求必须进行更进一步的操作   
> > * •400~499：客户端错误，请求有语法错误或请求无法实现    （常用400）
> > * •500~599：服务器端错误，服务器未能实现合法的请求       
> > * 成功的请求行, eg:`HTTP/1.1 200 OK`  

> 具体的网络数据响应内容可在REF中的博客具体查看
> 自行操作则参考：[trinea-Android利用Fiddler对网络数据进行抓包](http://www.trinea.cn/android/android-network-sniffer/)

---------------------------------
#### Android中的网络请求交互实现方法：
* httpClient   (在6.0中被删除，要继续使用可以采用库文件依赖的方法进行)
* HttpUrlConnection    
* Volley  适用于高频率，短时间，小流量的网络访问，不能用来进行下载文件这种长时间，大数据量的网络交互  
* okhttp/retrofit  

> * Android中多半是用的网络请求方法，只有GET，POST

> * Volley 是内部封装了一个网络访问请求队列，我们只要将访问操作添加进请求队列就能自动访问，网络访问的种类包括String,Json,Image等

> * [Volley 全解析](http://blog.csdn.net/itachi85/article/details/51043704)

> * ImageLoader是另一个封装好的的图片请求工具

> * [OKHTTP 使用教学](http://blog.csdn.net/lmj623565791/article/details/47911083)               
> * [RETROFIT 使用教学](http://www.jianshu.com/p/eb5d03085926)

> * [各种网络框架的对比]()





---

| 状态码 | 状态码英文名称                  | 中文描述                                                     |
| :----- | :------------------------------ | :----------------------------------------------------------- |
| 100    | Continue                        | 继续。[客户端](http://www.dreamdu.com/webbuild/client_vs_server/)应继续其请求 |
| 101    | Switching Protocols             | 切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议 |
|        |                                 |                                                              |
| 200    | OK                              | 请求成功。一般用于GET与POST请求                              |
| 201    | Created                         | 已创建。成功请求并创建了新的资源                             |
| 202    | Accepted                        | 已接受。已经接受请求，但未处理完成                           |
| 203    | Non-Authoritative Information   | 非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本 |
| 204    | No Content                      | 无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档 |
| 205    | Reset Content                   | 重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域 |
| 206    | Partial Content                 | 部分内容。服务器成功处理了部分GET请求                        |
|        |                                 |                                                              |
| 300    | Multiple Choices                | 多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择 |
| 301    | Moved Permanently               | 永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替 |
| 302    | Found                           | 临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI |
| 303    | See Other                       | 查看其它地址。与301类似。使用GET和POST请求查看               |
| 304    | Not Modified                    | 未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源 |
| 305    | Use Proxy                       | 使用代理。所请求的资源必须通过代理访问                       |
| 306    | Unused                          | 已经被废弃的HTTP状态码                                       |
| 307    | Temporary Redirect              | 临时重定向。与302类似。使用GET请求重定向                     |
|        |                                 |                                                              |
| 400    | Bad Request                     | 客户端请求的语法错误，服务器无法理解                         |
| 401    | Unauthorized                    | 请求要求用户的身份认证                                       |
| 402    | Payment Required                | 保留，将来使用                                               |
| 403    | Forbidden                       | 服务器理解请求客户端的请求，但是拒绝执行此请求               |
| 404    | Not Found                       | 服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面 |
| 405    | Method Not Allowed              | 客户端请求中的方法被禁止                                     |
| 406    | Not Acceptable                  | 服务器无法根据客户端请求的内容特性完成请求                   |
| 407    | Proxy Authentication Required   | 请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权 |
| 408    | Request Time-out                | 服务器等待客户端发送的请求时间过长，超时                     |
| 409    | Conflict                        | 服务器完成客户端的 PUT 请求时可能返回此代码，服务器处理请求时发生了冲突 |
| 410    | Gone                            | 客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置 |
| 411    | Length Required                 | 服务器无法处理客户端发送的不带Content-Length的请求信息       |
| 412    | Precondition Failed             | 客户端请求信息的先决条件错误                                 |
| 413    | Request Entity Too Large        | 由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息 |
| 414    | Request-URI Too Large           | 请求的URI过长（URI通常为网址），服务器无法处理               |
| 415    | Unsupported Media Type          | 服务器无法处理请求附带的媒体格式                             |
| 416    | Requested range not satisfiable | 客户端请求的范围无效                                         |
| 417    | Expectation Failed              | 服务器无法满足Expect的请求头信息                             |
|        |                                 |                                                              |
| 500    | Internal Server Error           | 服务器内部错误，无法完成请求                                 |
| 501    | Not Implemented                 | 服务器不支持请求的功能，无法完成请求                         |
| 502    | Bad Gateway                     | 作为网关或者代理工作的服务器尝试执行请求时，从远程服务器接收到了一个无效的响应 |
| 503    | Service Unavailable             | 由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中 |
| 504    | Gateway Time-out                | 充当网关或代理的服务器，未及时从远端服务器获取请求           |
| 505    | HTTP Version not supported      | 服务器不支持请求的HTTP协议的版本，无法完成处理               |