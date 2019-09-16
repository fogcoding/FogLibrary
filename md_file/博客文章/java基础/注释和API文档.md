## 注释和API文档

### 注释简述

​	很多时候，API文档是很重要的第一手资料，它可以快速地帮我们理解和学习一个成熟的框架或者代码系统。

​	编写文档是一个很繁琐又没有太多人关注的事情。在繁重的任务下，很少人会去注意编写清晰合理的文档来保证代码不会在中途偏离设计的本意。

​	即使如此，有一点是显而易见的：能够编写一份清晰合理，易于阅读的代码是很重要的能力，也可以说是走向高级工程师乃至于架构师的必备条件之一。



### 注释元素

---

javadoc 工具软件识别以下标签：

| **标签**      |                        **描述**                        |                           **示例**                           |
| :------------ | :----------------------------------------------------: | :----------------------------------------------------------: |
| @author       |                    标识一个类的作者                    |                     @author description                      |
| @deprecated   |                 指名一个过期的类或成员                 |                   @deprecated description                    |
| {@docRoot}    |                指明当前文档根目录的路径                |                        Directory Path                        |
| @exception    |                  标志一个类抛出的异常                  |            @exception exception-name explanation             |
| {@inheritDoc} |                  从直接父类继承的注释                  |      Inherits a comment from the immediate surperclass.      |
| {@link}       |               插入一个到另一个主题的链接               |                      {@link name text}                       |
| {@linkplain}  |  插入一个到另一个主题的链接，但是该链接显示纯文本字体  |          Inserts an in-line link to another topic.           |
| @param        |                   说明一个方法的参数                   |              @param parameter-name explanation               |
| @return       |                     说明返回值类型                     |                     @return explanation                      |
| @see          |               指定一个到另一个主题的链接               |                         @see anchor                          |
| @serial       |                   说明一个序列化属性                   |                     @serial description                      |
| @serialData   | 说明通过writeObject( ) 和 writeExternal( )方法写的数据 |                   @serialData description                    |
| @serialField  |             说明一个ObjectStreamField组件              |              @serialField name type description              |
| @since        |               标记当引入一个特定的变化时               |                        @since release                        |
| @throws       |                 和 @exception标签一样.                 | The @throws tag has the same meaning as the @exception tag.  |
| {@value}      |         显示常量的值，该常量必须是static属性。         | Displays the value of a constant, which must be a static field. |
| @version      |                      指定类的版本                      |                        @version info                         |

​	

### 注释范例

---

```java
/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package model;


import java.io.IOException;

/**
 * 类/接口注释
 * 类，接口描述，一般作详细描述,按照常用的说明顺序呢排列，主要包括:
 * 1.类的描述，以句号或句点结束,
 * 2.类设计的目标，完成什么样的功能一般和类的描述合并在一起
 * 3.<Strong>主要的类使用</Strong>如何使用该类，包括环境要求，比如线程是否安全，并发性要求以及使用约束
 * 4.<Strong>已知的BUG</Strong>
 * 5.描述类的修改历史：<Strong>修改人+日期+简单说明</Strong>
 * 6.@author作者、@version版本，@see参照，@since开始版本信息
 * <p>
 *
 * Created By Andrew Duan
 * Date:2019/8/24
 * e-mail:thinfog@126.com
 */
public class CommentExamples {

    private int age;
    private String name;


    /**
     * {@link java.lang.Object} class
     */
    public CommentExamples() {
    }

    /**
     * 构造方法
     *
     * @param age  年龄
     * @param name 姓名
     */
    public CommentExamples(int age, String name) {
        this.age = age;
        this.name = name;
    }


    /**
     * 获取人的年龄值
     *
     * @return 年龄
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置人的年龄，注意不能超过150岁
     *
     * @param age 年龄
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取人的姓名
     *
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置人的姓名，注意不能是非汉字
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * <h1>title 1</h1>
     * <p>
     * you can <em> even </em> insert a list:
     * <ol>
     * <li> item 1
     * <li> item 2
     * <li> item 3
     * </ol>
     *
     * @param a xxxxx
     * @return int zzzzzz
     * @throws IOException yyyyy
     * @version 1.0
     * @author fog
     * @see CommentExamples
     * @since 1.0
     * @deprecated
     */
    public int a(int a) throws IOException {

        return 0;
    }

}

```

