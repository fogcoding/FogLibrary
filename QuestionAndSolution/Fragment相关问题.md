### 使用Fragment时遇到的问题收集和解决办法
* 问题1：设置了match_parent，却没有实现满屏效果
* 问题2：在Activity中使用Fragment做场景切换，会使得Activity丢失一部分数据，只要取消Fragment的效果，一切正常
##### 问题1.2的解决办法： 将Fragment的父容器设置为FragmentLayout，然后所有的问题都消失了
##### 没有理解的问题：为什么FrameLayout就不会出现这种问题，而其他的布局都会产生各种各样的问题？这些布局的什么属性不同而产生了这种结果？
