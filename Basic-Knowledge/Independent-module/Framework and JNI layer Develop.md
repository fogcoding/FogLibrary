
## JNI (JAVA NATIVE INTERFACE)
#### 用途或功能:
* 实现JAVA和C/C++代码的交互，使得在某些追求效率的代码模块使用C/C++完成，充分利用程序各自的性能特点
* 有许多原本已经用C/C++实现的功能逻辑，可以通过JNI直接调用，避免重写
* APK文件可以进行反编译的缺陷，不利于程序的安全性，但通过JNI调用的方法，使得代码分为几个模块，不同语言，大大提升了程序的安全性

#### 实现方法：
![](http://a.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=f652ae8d7cd98d1062d904634056d36b/34fae6cd7b899e51d73c026542a7d933c8950d14.jpg)

[REF：JNI示例](http://www.cnblogs.com/wzben/p/5733571.html)

错误警示或备忘：
* NDK的下载可以直接在Android studio里面的project structure里面操作 （解压操作会在一个叫wanrouter.h的文件中卡住相当长的时间，不要取消）
* 自己创建的文件夹一定要是对应的类型和文件名（JNI文件一定要是JNI Folder,jniLIB 也不能叫jniLIBS ,否则IDE无法识别！也不报错！）
> ps:这里是浪费时间最多的地方
* JNI的SO文件是可以自动生成的，需要将SO文件复制到jniLIB才能顺利执行
> 但C文件较少自动是方便和优秀的，但是但C语言需要实现的模块和功能很多，自动生成的文件就会难以操作和管理，此时需要使用gradle配置来管理
代码：
`android {`  
`       sourceSets {`  
`           main {`  
`             jni.srcDirs = []`  
`               }`  
`       }`  
`}`  
