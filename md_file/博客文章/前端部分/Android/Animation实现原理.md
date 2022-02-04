### [郭霖-属性动画详解 上-中-下](http://blog.csdn.net/guolin_blog/article/details/43536355#comments)

### 个人阅读笔记：（相对于自己知识的补充）

#### 动画的种类：
> * 帧动画：将许多图片设置连续播放，利用视觉残留现象产生动画的方法
> * 补间动画: 缩放scale ， 旋转rotate ， 位移Translate ， 透明 alpha
> * [补间动画的入门知识](http://www.cnblogs.com/thinfog/p/5711609.html)
> * 属性动画： 为了一些补间动画说不能实现的效果而设计的动画设计方法

#### 一些备忘：
> * 通过设置动画监听需要实现所有的动画监听方法，假如使用AnimatorListenerAdapter则可以仅仅重写需要监听的时刻的方法，避免实现无用的方法
> * 在颜色渐变的过程中，有一段代码：

`String startColor = (String) startValue;`    
`String endColor = (String) endValue;`  
`int startRed = Integer.parseInt(startColor.substring(1, 3), 16);`    
`int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);`    
`int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);`    
`int endRed = Integer.parseInt(endColor.substring(1, 3), 16);`    
`int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);`  
`int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);`    

##### 原本对于这段代码完全不理解意思，经过研究源码和测试有以下结论：
> * *通过实现TypeEvaluator接口，可以实现对特定对象添加动画，十分的灵活*
> * String.substring()是一个截取字符串的方法，而Android中颜色以"#0000FF"这样的字符形式表示
> * 而颜色都是有红绿蓝三原色搭配组成，颜色字符串的数字共6位，1，2位代表红，3，4位代表绿，5，6位代表蓝，
> * 这段代码就是通过截取相应的字符串数据来获取三原色的数据值，然后改变数据的值使得颜色改变，而红绿蓝三种颜色的数据值都在0～255之间（FF为255）
> * *在强制转换成数字的时候，颜色的数据值是16进制的(截取字符串数据的时候，后面的16就是代表里面的数据值是16进制)，若要实现10进制操作要进行转换*

#### 不均匀速率的动画实现方法：Interpolator
> Interpolator的种类：
> * AccelerateInterpolator 加速运动
> * DecelerateInterpolator 减速运动
> *为了实现对于速度变化的模拟，需要相应的数学功底，多半是利用各种函数来实现各种速度的模拟*
> * [具体的实现方法]（http://blog.csdn.net/guolin_blog/article/details/44171115）
