### [Android data binding 一 - 详细介绍和使用](http://blog.csdn.net/zhixuan322145/article/details/51817207)
### [Android data binding - Binding与Observer实现原理](http://blog.csdn.net/zhixuan322145/article/details/51897272)

#### 个人笔记：
> data binding 是希望简化数据与View绑定这一繁琐重复的工作而诞生的，在设计视图的同时就将各种数据绑定了View
> 初步看来，data binding一是简化了繁琐重复的步骤，大大加快了程序开发效率

> 另外，还有一款叫butterknife的开源软件，他也是为了解决无数次重复的findViewById(),setListener()操作而开发的编译软件，
相比而言，这款工具也简化了寻找控件和设置监听事件的代码量，但是他并没有使得数据与视图直接动态绑定，算是data binding的弱化版工具，
但是他也有他自己的好处：保留了原生地开发方式，一切的控件和数据的行为都是代码化可视化的，而data binding是直接逻辑化了，改变了编程
逻辑和编程习惯

> 但这两个辅助开发的编译工具，带来了新的bug可能和错误类型，不如原生的开发方式稳定可靠
