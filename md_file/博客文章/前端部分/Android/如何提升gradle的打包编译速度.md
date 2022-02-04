## 如何提升gradle的打包编译速度
```
个人实践成果：
1.修改代码后打包编译，由原本的2分半～五分钟左右的时间，变为30S
2.若没有修改代码，直接打包编译，时间大约是2~5s
```

### 做了哪些改进措施？
* 一：在gradle编译环境里设置了全局变量，(目录为：C:\Users\andrew\.gradle,增加gradle.properties文件，内容如下：)

```

org.gradle.daemon=true  // 开启线程守护，第一次编译时开线程，之后就不会再开了

org.gradle.parallel=true  // 开启并行编译，相当于多条线程再走

org.gradle.configureondemand=true   启用新的孵化模式

```

* 二：设置离线编译

路径：file->setting->build,Execution,Deployment->gradle

1.设置Use local gradle distribution,

Gradle Home:C:/Users/andrew/.gradle/wrapper/dists/gradle-3.3-all

* 三：改变编译方式

1.打开 Compile independent modules in parallel(may require larger heap size)选项

2.在Command-line Options里，增加：--offline
