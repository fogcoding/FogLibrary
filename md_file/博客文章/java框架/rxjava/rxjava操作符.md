### 目录

##### 一.编程方式介绍

1.函数式编程
2.响应式编程
3.函数响应式编程

##### 二、操作符介绍

1.什么是操作符？
2.RxJava中的操作符简介
3.RxJava中的操作符分类

##### 三、操作符在Android中的应用实践

1.线程切换注意事项
2.线程的生命周期
3.线程池的使用

##### 一、编程方式介绍

> 常见的编程方式有函数式编程、响应式编程、命令式编程、面向对象编程、面向接口编程、面向过程编程。RxJava函数响应式编程是将函数式编程与响应式编程的结合。

- **1.函数式编程**
  函数是什么？根据入参得到返回值。java中的方法、C中的函数、JavaScript中的function。在编程语言中方法的参数可以是方法（函数），返回值也可以是方法（函数），适用于计算处理的场景。函数式编程是编程中是一种手段或者方法。被重新提出和重视。
  **函数式编程有一些特点：**
  **纯函数**的定义是，对于相同的输入，永远会得到相同的输出，而且没有任何可观察的副作用，也不依赖外部环境的状态。int method1(int a, int b);
  **函数柯里化（curry）**的定义很简单：传递给函数一部分参数来调用它，让它返回一个函数去处理剩下的参数。

```jsx
    var add = function(x){
        return function(y){
        return x + y
        }
    }
    var add = x => (y => x + y);  // js ES6写法，也是比较正统的函数式写法 
```

**函数组**:学会了使用纯函数以及如何把它柯里化之后，我们会很容易写出这样的“包菜式”代码：h(g(f(x)));
**高阶函数**：就是参数为函数或者返回值为函数的函数，有了高阶函数，就可以将复用力度降低到函数级别，相对于面向对象语言，复用的力度更低
**闭包**：函数编程支持函数作为第一类对象，有时称为闭包或者仿函数（functor）对象。实质上，闭包是起函数的作用并可以像对象一样操作的对象。
**惰性求值**：函数式编程语言还提供惰性求值（Lazy evaluation，也称作call-by-need），是在将表达式赋值给变量（或称作绑定）时并不计算表达式的值，而在变量第一次被使用时才进行计算。这样就可以通过避免不必要的求值提升性能。
**无状态** 变量的不变性，入参是无变化的。

> 简单说，"函数式编程"是一种"编程范式"（programming paradigm），也就是如何编写程序的方法论。它属于"结构化编程"的一种，主要思想是把运算过程尽量写成一系列嵌套的函数调用。函数式编程是给软件开发者提供的另一套工具箱，为我们提供了另外一种抽象和思考的方式。Lambda演算在最初设计的时候就是为了研究计算相关的问题。所以函数式编程主要解决的也是计算问题，函数式编程是一种编程模型，他将计算机运算看做是数学中函数的计算，并且避免了状态以及变量的概念。函数式编程的抽象本质则是将函数也作为一个抽象单位，而反映成代码形式，则是高阶函数。

- **2.响应式编程**
  响应式编程就是与异步数据流交互的编程范式。在某种程度上，这并不是什么新东西。事件总线(Event buses)或咱们常见的单击事件就是一个异步事件流，你可以观察这个流，也可以基于这个流做一些自定义操作（原文：side effects，副作用，本文皆翻译为自定义操作）。响应式就是基于这种想法。你能够创建所有事物的数据流，而不仅仅只是单击和悬停事件数据流。 流廉价且无处不在，任何事物都可以当作一个流：变量、用户输入、属性、缓存、数据结构等等。比如，假设你的微博评论就是一个跟单击事件一样的数据流，你能够监听这个流，并做出响应。响应式编程(Reactive Programming 或称反应式编程)是一种流行的编程方法，编写代码是基于对变化的反应。它的灵感来自于我们的日常生活，也即我们如何采取行动以及与他人沟通。和平常经常听说的面向对象编程和函数式编程一样，响应式编程(Reactive Programming)就是一个编程范式，但是与其他编程范式不同的是它是基于数据流和变化传播的。
  我们经常在程序中这样写 A = B + C
  A被赋值为B和C的值。这时，如果我们改变B的值，A的值并不会随之改变。而如果我们运用一种机制，当B或者C的值发现变化的时候，A的值也随之改变，这样就实现了”响应式“。而响应式编程的提出，其目的就是简化类似的操作，因此它在用户界面编程领域以及基于实时系统的动画方面都有广泛的应用。另一方面，在处理嵌套回调的异步事件，复杂的列表过滤和变换的时候也都有良好的表现。Reactive响应式(反应式)编程 是一种新的编程风格，其特点是异步或并发、事件驱动、推送PUSH机制以及观察者模式的衍生。reactive应用(响应式应用)允许开发人员构建事件驱动（event-driven），可扩展性，弹性的反应系统：提供高度敏感的实时的用户体验感觉，可伸缩性和弹性的应用程序栈的支持，随时可以部署在多核和云计算架构。

> 响应式编程特点
>
> - 响应性是指一个系统应该总是能够及时响应用户请求，并且保持很低的延迟。
> - 弹性是指一个系统即使在部分组件开始出现故障的情况下也应该能够作出响应，将停机时间将至最低。
> - 可伸缩性是指一个系统在负载增加时应该能够根据需求增加资源以确保响应性，但同时也应该能在负载降低时减少资源，保持高效的资源利用率。
> - 消息驱动是指在一个系统的不同部分之间传递消息

> 响应式流是一种规范，下面三个重要的概念是响应式流API的构建基础：
>
> - 发布者是事件的发送方，可以向它订阅。
> - 订阅者是事件订阅方。
> - 订阅将发布者和订阅者联系起来，使订阅者可以向发布者发送信号。

> 响应式编程是一种基于异步数据流概念的编程模式。数据流就像一条河：它可以被观测，被过滤，被操作，或者为新的消费者与另外一条流合并为一条新的流。响应式编程的一个关键概念是事件。事件可以被等待，可以触发过程，也可以触发其它事件。事件是唯一的以合适的方式将我们的现实世界映射到我们的软件中：如果屋里太热了我们就打开一扇窗户。同样的，当我们更改电子表（变化的传播）中的一些数值时，我们需要更新整个表格或者我们的机器人碰到墙时会转弯（响应事件）。

- **3.函数响应式编程**
  FRP与普通的函数式编程相似，但是每个函数可以接收一个输入值的流，如果其中，一个新的输入值到达的话，这个函数将根据最新的输入值重新计算，并且产生一个新的输出。这是一种”数据流"编程模式。
  而主要利用函数式编程(Functional Programming)的思想和方法(函数、高阶函数)来支持Reactive Programming就是所谓的Functional Reactive Programming，简称FRP。FPR 将输入分为两个基础的部分：行为(behavior)和事件(events) 。这两个基本元素在函数响应式编程中都是第一类（first-class）值。 其中行为是随时间连续变化的数据，而事件则是基于离散的时间序列 。例如：在我们操作网页的时候，会触发很多的事件，包括点击，拖动，按键事件等。这些事件都是不连续的。对事件求值是没有意义的，所有我们一般要通过fromEvent，buffer等将其变成连续的行为来做进一步处理。与RP相比，FRP更偏重于底层。由于采用了函数式编程范式，FRP也自然而然带有其特点。这其中包括了不可变性，没有副作用以及通过组合函数来构建程序等特点。

二、操作符介绍

- **1.什么是操作符？**

> 指令系统的每一条指令都有一个操作符，它表示该指令应进行什么性质的操作。
> 操作符，常见于计算机语言之中，不同的指令用操作符这个字段的不同编码来表示，每一种编码代表一种指令。
> 指令系统的每一条指令都有一个操作符，它表示该指令应进行什么性质的操作。不同的指令用操作符这个字段的不同编码来表示，每一种编码代表一种指令。组成操作符字段的位数一般取决于计算机指令系统的规模。
> 操作符用于操作数据并生成一个新值。

先看看大家熟悉的java操作符的相关的就比较好理解了。
Java的算数操作符与其它大多数程序设计语言都是相同的，其中包括加号(+)、减号(-)、乘号(×)、除号(÷)以及取模(%)。
也就是说 int i = j+k; 这个语句中的 “+” 就是一个操作符。“=”也是一个操作符，“+”为算数操作符、“=”为赋值操作符。
布尔操作符 ！、 &&、 ||
关系比较操作符 < 、 <= 、 > 、>=、 != 、 == 、 === 、 !==

> 还有些人会发现这些都是编程语言级别的东西，怎么RxJava一个框架要定义这些东西？RxJava是 函数响应式编程，是以函数作为基本元素，相较于原来的编程都是变量、常量、对象这些作为基本元素。
> 响应式编程是一种面向数据流和变化传播的编程范式。这意味着可以在编程语言中很方便地表达静态或动态的数据流，而相关的计算模型会自动将变化的值通过数据流进行传播。

- **2.RxJava中的操作符简介**

> - 就是建立在变量、常量、对象这些编程方法的基础上，搭建的另一种编程方式或者是编程语言，只是这个语言的基础是原来的编程的方式。
>   通过RxJava中的规则完成编程的工作，这些规则的实现就是RxJava操作符。
> - RxJava将函数创建成RxJava中的元素Observable，然后进行变换、条件筛选、过滤、组合等操作然后将处理的结果发送给接收者Observer。
> - 这里边的两个英文单词要区分清楚Observable 和Observer，一个是可被观察者、一个是观察者。可被观察者Observable完成一系列操作后将数据发送给观察者Observer，一个是发射者，一个是接收者。弄清楚了，下面的操作符就理解主体结构了。

- **3.RxJava中的操作符分类**

> 像Java中有算数操作符加减乘除、逻辑操作符与或非、条件操作符大于小于等于这些。数据库sql中过滤、筛选、count这样的操作符。

> ###### RxJava也有它自己需要的操作符，分为了下面几类：
>
> - 1.直接创建一个Observable（创建操作）
> - 2.组合多个Observable（组合操作）
> - 3.对Observable发射的数据执行变换操作（变换操作）
> - 4.从Observable发射的数据中取特定的值（过滤操作）
> - 5.这些操作符用于从错误通知中恢复(错误处理)
> - 6.用于处理Observable的操作符（辅助操作）
> - 7.转发Observable的部分值（条件/布尔/过滤操作）
> - 8.对Observable发射的数据序列求值（算术/聚合操作）

学习这些操作符按照编程语言的思想去学习，这些操作符就比较好理解了。再看上几个实例，这些操作符基本使用就会了，其他的知道有这些操作符，具体使用的时候再研究就可以搞定了。学习几个实例就会明白这些操作符都是干什么的了。下面会主要以操作符的实现思想和实例的形式来介绍RxJava中的操作符。

##### （1）RxJava入门用法：

> 基础知识： RxJava最核心的两个东西是Observables（被观察者，事件源）和 Subscribers（观察者）。 Observables发出一系列事件，Subscribers处理这些事件。 这里的事件可以是任何你感兴趣的东西（触摸事件，web接口调用返回的数据。。。）

```tsx
// 观察者， 创建事件
        Observable<String> myObservable = Observable.create(new     Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> sub) {
                sub.onNext("Hello, world!");
                sub.onNext("Hello, world!");
                sub.onNext("Hello, world!");
                sub.onCompleted();
                 // sub.onError(new Exception("Observable throw onError exception!")); //onCompleted()和onError()不能同时使用
            }
        });
        // 订阅者 接收处理事件
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());
            }
        };
        myObservable.subscribe(mySubscriber);
```

##### （2）RxJava入门用法的简化用法：

```tsx
        // 首先来看看如何简化Observable对象的创建过程。
        // RxJava内置了很多简化创建Observable对象的函数，比如Observable.just就是用来创建只发出一个事件就结束的Observable对象，
        // 上面创建Observable对象的代码可以简化为一行
        Observable<String> myObservable = Observable.just("Hello, world!");

        // 接下来看看如何简化Subscriber，
        // 上面的例子中，我们其实并不关心OnComplete和OnError，
        // 我们只需要在onNext的时候做一些处理，这时候就可以使用Action1类。
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("call:" + s);
            }
        };
        myObservable.subscribe(onNextAction);
        // subscribe方法有一个重载版本，接受三个Action1类型的参数，分别对应OnNext，OnComplete， OnError函数。
        // myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
```

##### （3）RxJava入门用法使用java8的lambda表达式

```csharp
 Observable.just("Hello, world!").subscribe(s -> System.out.println(s));//一行就搞定了
```

###### 创建操作符 — 用于创建Observable的操作符，共有下面几种，其中create、from、just是比较常用的操作符，在这里介绍下这三个操作符。

> Create — 通过调用观察者的方法从头创建一个Observable
> From — 将其它的对象或数据结构转换为Observable
> Just — 将对象或者对象集合转换为一个会发射这些对象的Observable
> Defer — 在观察者订阅之前不创建这个Observable，为每一个观察者创建一个新的Observable
> Interval — 创建一个定时发射整数序列的Observable
> Range — 创建发射指定范围的整数序列的Observable
> Repeat — 创建重复发射特定的数据或数据序列的Observable
> Empty/Never/Throw — 创建行为受限的特殊Observable
> Start — 创建发射一个函数的返回值的Observable
> Timer — 创建在一个指定的延迟之后发射单个数据的Observable

##### （4）Create 操作符

> 通过调用观察者的方法从头创建一个Observable，是最基础的创建操作符.

```tsx
        // 观察者， 创建事件
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> sub) {
                //Edit data
                sub.onNext("Hello, world!");
                sub.onCompleted();
            }
        });
        // 订阅者 接收处理事件
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
                onError(new Throwable("1"));
                onError(new Throwable("2"));
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted!");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());
            }
        };
        myObservable.subscribe(mySubscriber);
```

##### （5）from操作符

> 它接收一个集合作为输入，然后每次输出一个元素给subscriber。

```csharp
String[] urls = { "url1", "url2", "url3" };
Observable.from(urls).subscribe(url -> System.out.println(url));
```

##### （6）Just 操作符

> 将对象或者对象集合转换为一个会发射这些对象的Observable，与from的区别：支持多个类型作为入参，集合类型不是每次输出一个元素，而是整个集合输出。

```csharp
String[] urls = { "url1", "url2", "url3" };
Observable.just("字符串", 123, true, urls).subscribe(item -> System.out.println(item));//这里的urls是一次发射出去的，不是拆分成数组后发射出去。
Observable.just("url1", "url2", "url3").subscribe(item -> System.out.println(item));
```

###### 变换操作符 - 这些操作符可用于对Observable发射的数据进行变换。map、Buffer、FlatMap是比较典型的变换操作符，在这里介绍下这三个操作符。

> **Map — 映射**，通过对序列的每一项都应用一个函数变换Observable发射的数据，实质是对序列中的每一项执行一个函数，函数的参数就是这个数据项
> **Buffer — 缓存**，可以简单的理解为缓存，它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个。
> **FlatMap — 扁平映射**，将Observable发射的数据变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable，可以认为是一个将嵌套的数据结构展开的过程。
> **ConcatMap** — cancatMap操作符与flatMap操作符类似，都是把Observable产生的结果转换成多个Observable，然后把这多个。Observable“扁平化”成一个Observable，并依次提交产生的结果给订阅者。与flatMap操作符不同的是，concatMap操作符在处理产生的。Observable时，采用的是“连接(concat)”的方式，而不是“合并(merge)”的方式，这就能保证产生结果的顺序性，也就是说提交给订阅者的结果是按照顺序提交的，不会存在交叉的情况。
> **GroupBy — 分组**，将原来的Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据。
> **Scan — 扫描**，对Observable发射的每一项数据应用一个函数，然后按顺序依次发射这些值。
> **Window — 窗口**，定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。类似于Buffer，但Buffer发射的是数据，Window发射的是Observable，每一个Observable发射原始Observable的数据的一个子集。

##### （7）Map — 映射

> 通过对序列的每一项都应用一个函数变换Observable发射的数据，实质是对序列中的每一项执行一个函数，函数的参数就是这个数据项。

> 关键词：将Observable发射出的数据转换成另外一个Observable。

```tsx
Observable.just("http://www.baidu.com/", "http://www.google.com/", "https://www.bing.com/")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        try {
                            URL url = new URL(s);
                            String host = url.getHost();
                            return InetAddress.getByName(host).toString();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("subscribe : " + s);
                    }
                });
```

##### （8）Buffer — 缓存

> 可以简单的理解为缓存，它定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个。

> 关键词：将Observable攒着一块发出去。

```tsx
final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!"}; //定义邮件内容
        //每隔0.8秒就随机发布一封邮件
        Observable<String> endlessMail = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
            try {
                if (subscriber.isUnsubscribed()) return;
                Random random = new Random();
                while (true) {
                String mail = mails[random.nextInt(mails.length)];
                subscriber.onNext(mail);
                Thread.sleep(800);
                }
            } catch (Exception ex) {
                subscriber.onError(ex);
            }
            }
        });
        //把上面产生的邮件内容缓存到列表中，并每隔2秒通知订阅者
        endlessMail.buffer(2, TimeUnit.SECONDS)
        .subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {
            System.out.println(String.format("You've got %d new messages!  Here they are!", list.size()));
            for (int i = 0; i < list.size(); i++)
                System.out.println("**" + list.get(i).toString());
            }
        }); 
```

##### （9）FlatMap — 扁平映射

> 将Observable发射的数据变换为Observables集合，然后将这些Observable发射的数据的放进一个单独的Observable，可以认为是一个将嵌套的数据结构展开的过程。

> 关键词：将Observable发射出的数据转换成多个Observable。提供了一个Observable转换成多个Observable的能力。

> - 在学习RxJava操作符的时候，Map和FlatMap比较难以理解。这里对FlatMap和Map的比较并做了针对性的介绍。
> - 这里有两个基础点可以仔细琢磨一下：1.Observable对象既可以是一个元素也可以是一个集合。2.Map操作符是提供一对一的转换、FlatMap是提供一对多的转换。
> - 下面是总结了Map和FlatMap的比较，可以参照分析出Map和FlatMap的操作符区别和作用。攻克了这两个操作符也就可以说基本明白RxJava操作符是怎么回事了。
> - 【基础知识】：Observable 对象 本身可以是一个对象也可以是一组对象
>   一个对象如：Observable.just(1) 结果是经过发射器之后只发射一条数据
>   一组对象如：Observable.just(1,2,3) 或者 Observable.from({"222", "sss", "eee"})。结果是经过发射器之后可以发射多条数据。
> - 【map和FlatMap的区别】：
> - map 和FlatMap都能把参数转化为另一个对象
>   map 是一对一的转化 返回值是普通类型 ， 如String、File 返回值是单个转换后的事件
> - FlatMap 一对多的转化 返回值是Observable，如Observable<File>、Observable<String> 返回值是Observable(转换后的Observable集合)
>   flatMap的返回类型是Observable，说明是可以返回多个内容，而不是事件类型String、List、或者数组(这些最终都是一个事件)。

> FlatMap() 的原理是这样的
>
> 1. 使用传入的事件对象创建一个 Observable 对象；
> 2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件； 【备注：这里的激活概念可以理解为这个操作Observable obs = Observable.from(file.listFiles());】
> 3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。
>    这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
>
> - 使用场景： FlatMap() 解决嵌套的问题 一个入参，返回多个事件，再根据多个事件，返回多个事件，这种嵌套问题。 （一个返回多个的使用from就可以搞定）

```csharp
    Observable.just(new File("E:\\aa\\"))
        .flatMap(new Func1<File, Observable<File>>() { 
            @Override
            public Observable<File> call(File file) {//1. 使用传入的事件对象创建一个 Observable 对象； --------> file就是传入的事件对象
                return Observable.from(file.listFiles()); //2.中的并不发送这个 Observable是个集合 ---> 就是这个  Observable.from(file.listFiles())
            }//3.每一个创建出来的 Observable --->这个Observable就是  就是(2)中激活出来的file.listFiles()中的每一个File的Observable对象
             //3.都被汇入同一Observable  这个Observable 是 这个call方法的返回值 Observable<File>,这个Observable是个集合，也是返回的集合(也就是扁平化的为一个集合)
        })
        .flatMap(new Func1<File, Observable<File>>() {
            @Override
            public Observable<File> call(File file) {
                return Observable.from(file.listFiles());
            }
        })
        .subscribe(new Action1<File>() {
            @Override
            public void call(File file) {
                System.out.println(file.getName());
            }
        });
```

FlatMap再来一个例子有助于理解

```tsx
    /**
     * 【flatMap案例】
     * 首先假设这么一种需求：假设有一个数据结构『学生』，现在需要打印出一组学生的名字。实现方式很简单：用map转换一下就可以了，此实现忽略
     * 那么再假设：如果要打印出每个学生所需要修的所有课程的名称呢？（需求的区别在于，每个学生只有一个名字，但却有多个课程。）首先可以这样实现：将学生作为事件传入for循环打印课程名称
     * 如果不想使用for循环该怎么办？   -------------------flatMap 代码见下方
     * 用rx的方式再使用for循环，就会觉得这个flatMap这个没有什么意义了。就变的难理解，用以前能实现的东西，再学这个flatMap的抵触。变成思维的转变。。。
     */
    public static void flatMap2(){
        String[] courses1 = {"语文", "数学" , "英语", "数学1" , "英语1", "数学2" , "英语3", "数学5" , "英语6"};
        String[] courses2 = {"语文111", "数学111" , "英语111", "数学1131" , "英语1141", "数学1511" , "英语1161"};
        Student[] students = {new Student("xiaoli", courses1), new Student("zhangsan", courses2)};

        Observable.from(students)
        .flatMap(new Func1<Student, Observable<String>>() { 
            @Override
            public Observable<String> call(Student student) {
                return Observable.from(student.courses);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                System.out.println(str);
            }
        });
    }
    
    static class Student{
        public Student(String name, String[] courses){
            this.name = name;
            this.courses = courses;
        }
        public String name;
        public String[] courses;
    }
```

> 过滤操作符 - 这些操作符用于从Observable发射的数据中进行选择。这里介绍比较典型的filter、distinct、elementAt三个操作符。
>
> - Debounce — 只有在空闲了一段时间后才发射数据，通俗的说，就是如果一段时间没有操作，就执行一次操作
> - Distinct — 去重，过滤掉重复数据项
> - ElementAt — 取值，取特定位置的数据项
> - Filter — 过滤，过滤掉没有通过谓词测试的数据项，只发射通过测试的
> - First — 首项，只发射满足条件的第一条数据
> - IgnoreElements — 忽略所有的数据，只保留终止通知(onError或onCompleted)
> - Last — 末项，只发射最后一条数据
> - Sample — 取样，定期发射最新的数据，等于是数据抽样，有的实现里叫ThrottleFirst
> - Skip — 跳过前面的若干项数据
> - SkipLast — 跳过后面的若干项数据
> - Take — 只保留前面的若干项数据
> - TakeLast — 只保留后面的若干项数据

##### （10）filter操作符

> 是对源Observable产生的结果按照指定条件进行过滤，只有满足条件的结果才会提交给订阅者。

```java
Observable.just(1, 2, 3, 4, 5)
        .filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer item) {
                return (item < 4);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
```

##### （11）distinct操作符

> 对源Observable产生的结果进行过滤，把重复的结果过滤掉，只输出不重复的结果给订阅者，非常类似于SQL里的distinct关键字。

```java
        Observable.just(1, 2, 1, 1, 2, 3)
        .distinct().subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Next: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("Error: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }
        });
```

##### （12）elementAt操作符

> 在源Observable产生的结果中，仅仅把指定索引的结果提交给订阅者，索引是从0开始的。

```csharp
        Observable.just(1, 2, 3, 4, 5, 6)
        .elementAt(5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Next:" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("Error:" + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println("completed!");
            }
        });
```

> 组合操作符 -- 用于将多个Observable组合成一个单一的Observable，这里只是介绍个CombineLatest操作符，以对组合操作符了解即可。 
>
> - And/Then/When — 通过模式(And条件)和计划(Then次序)组合两个或多个Observable发射的数据集
> - CombineLatest — 当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果
> - Join — 无论何时，如果一个Observable发射了一个数据项，只要在另一个Observable发射的数据项定义的时间窗口内，就将两个Observable发射的数据合并发射
> - Merge — 将两个Observable发射的数据组合并成一个
> - StartWith — 在发射原来的Observable的数据序列之前，先发射一个指定的数据序列或数据项
> - Switch — 将一个发射Observable序列的Observable转换为这样一个Observable：它逐个发射那些Observable最近发射的数据
> - Zip — 打包，使用一个指定的函数将多个Observable发射的数据组合在一起，然后将这个函数的结果作为单项数据发射

##### （13）combineLatest操作符

> 把两个Observable产生的结果进行合并，合并的结果组成一个新的Observable。这两个Observable中任意一个Observable产生的结果，都和另一个Observable最后产生的结果，按照一定的规则进行合并。

> 关键词：发射两个Observable的合并结果

```java
        Integer[] array2 = { 10, 20, 30, 40, 50 };
        Observable.combineLatest(Observable.just(4, 2,5), Observable.from(array2),
                new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer a, Integer b) {
                        //System.out.println("a:" + a + ",b:");
                        return a + b;
                    }
                }).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer a) {
                        System.out.println("Next: " + a);
                    }
                });
```

###### 异常处理操作符 -- 这些操作符用于从错误通知中恢复。这里主要介绍下retry、onErrorReturn、onErrorResumeNext三个操作符。

> - Catch — 捕获，继续序列操作，将错误替换为正常的数据，从onError通知中恢复
> - Retry — 重试，如果Observable发射了一个错误通知，重新订阅它，期待它正常终止
> - onErrorReturn - 方法 返回一个镜像原有Observable行为的新Observable。会忽略前者的onError调用，不会将错误传递给观察者，而是发射一个特殊的项并调用观察者的onCompleted方法。
> - onErrorResumeNext - onErrorResumeNext方法与onErrorReturn()方法类似，都是拦截原Observable的onError通知，不同的是拦截后的处理方式，onErrorReturn创建并返回一个特殊项，而onErrorResumeNext创建并返回一个新的Observabl，观察者会订阅它，并接收其发射的数据。
> - onExceptionResumeNext - onExceptionResumeNext方法与onErrorResumeNext方法类似创建并返回一个拥有类似原Observable的新Observable，，也使用这个备用的Observable。不同的是，如果onError收到的Throwable不是一个Exception，它会将错误传递给观察者的onError方法，不会使用备用的Observable。
> - retryWhen - retryWhen和retry类似，区别是，retryWhen将onError中的Throwable传递给一个函数，这个函数产生另一个Observable，retryWhen观察它的结果再决定是不是要重新订阅原始的Observable。如果这个Observable发射了一项数据，它就重新订阅，如果这个Observable发射的是onError通知，它就将这个通知传递给观察者然后终止。

##### （14）retry操作符

> 是当Observable发生错误或者异常时，重新尝试执行Observable的逻辑，如果经过n次重新尝试执行后仍然出现错误或者异常，则最后回调执行onError方法；当然如果源Observable没有错误或者异常出现，则按照正常流程执行。

> 关键词：observable.retry(1)

```java
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                    if (i == 4) {
                        throw new Exception("this is number 4 error！");
                    }
                    subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Throwable e) {
                    subscriber.onError(e);
                }
                }
            });
            observable.retry(1).subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                System.out.println("Sequence complete.");
                }

                @Override
                public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
                }

                @Override
                public void onNext(Integer value) {
                System.out.println("Next:" + value);
                }
            });
```

##### （15）onErrorReturn操作符

> 是在Observable发生错误或异常的时候（即将回调oError方法时），拦截错误并执行指定的逻辑，返回一个跟源Observable相同类型的结果，最后回调订阅者的onComplete方法

> 关键词：拦截error，并转换处理

```java
            Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
                        @Override
                        public void call(Subscriber<? super Integer> subscriber) {
                            if (subscriber.isUnsubscribed())
                                return;
                            // 循环输出数字
                            try {
                                for (int i = 0; i < 10; i++) {
                                    if (i == 4) {
                                        throw new Exception("this is number 4 error！");
                                    }
                                    subscriber.onNext(i);
                                }
                                subscriber.onCompleted();
                            } catch (Exception e) {
                                subscriber.onError(e);
                            }
                        }
                    });

                    observable.onErrorReturn(new Func1<Throwable, Integer>() {
                        @Override
                        public Integer call(Throwable throwable) {
                            return 1004;
                        }
                    }).subscribe(new Subscriber<Integer>() {
                        @Override
                        public void onCompleted() {
                            System.out.println("Sequence complete.");
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.err.println("Error: " + e.getMessage());
                        }

                        @Override
                        public void onNext(Integer value) {
                            System.out.println("Next:" + value);
                        }
                    });     
```

##### （16）onErrorResumeNext操作符

> 跟onErrorReturn类似，只不过onErrorReturn只能在错误或异常发生时只返回一个和源Observable相同类型的结果，而onErrorResumeNext操作符是在错误或异常发生时返回一个Observable，也就是说可以返回多个和源Observable相同类型的结果。

> 关键词：与onErrorReturn相比可以返回多个结果。

```java
Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed())
                    return;
                // 循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

        observable.onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                return Observable.just(100, 101, 102, 103);
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });
```

###### 算术、聚合、连接操作符 -- 这里主要介绍count、reduce、public三个操作符。

> 算术聚合操作符
>
> - Average — 计算Observable发射的数据序列的平均值，然后发射这个结果
> - Concat — 不交错的连接多个Observable的数据
> - Count — 计算Observable发射的数据个数，然后发射这个结果
> - Max — 计算并发射数据序列的最大值
> - Min — 计算并发射数据序列的最小值
> - Reduce — 按顺序对数据序列的每一个应用某个函数，然后返回这个值
> - Sum — 计算并发射数据序列的和

> - 连接操作符
> - Connect — 指示一个可连接的Observable开始发射数据给订阅者
> - Publish — 将一个普通的Observable转换为可连接的
> - RefCount — 使一个可连接的Observable表现得像一个普通的Observable
> - Replay — 确保所有的观察者收到同样的数据序列，即使他们在Observable开始发射数据之后才订阅

##### （17）Count操作符

> 将一个Observable转换成一个发射单个值的Observable，这个值表示原始Observable发射的数据的数量。如果原始Observable发生错误终止，Count不发射数据而是直接传递错误通知。如果原始Observable永远不终止，Count既不会发射数据也不会终止。

> 关键词：只发射数量，不关心内容。

```csharp
            Subscriber<Integer> mySubscriber = new Subscriber<Integer>() {
                @Override
                public void onNext(Integer s) {
                    System.out.println("onNext:" + s);
                }

                @Override
                public void onCompleted() {
                    System.out.println("onCompleted!");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("onError:" + e.getMessage());
                }
            };
            Observable.from(new String[] { "one", "two", "three" })
            .count()
            .subscribe(mySubscriber);
```

##### （18）Reduce操作符

> 对原始Observable发射数据的第一项应用一个函数，然后再将这个函数的返回值与第二项数据一起传递给函数，以此类推，持续这个过程直到原始Observable发射它的最后一项数据并终止，此时Reduce返回的Observable发射这个函数返回的最终值。 注意如果原始Observable没有发射任何数据，reduce抛出异常IllegalArgumentException。 在其它场景中，这种操作有时被称为累积，聚集，压缩，折叠，注射等。

> 关键字：叠加，返回叠加结果

```bash
            Observable.just(1,2,3,4)
            .reduce(new Func2<Integer, Integer, Integer>() {
                //integer为前面几项只和，integer2为当前发射的数据
                @Override
                public Integer call(Integer integer, Integer integer2) {
                //System.out.println("integer:"+integer+"  integer2:"+integer2);
                return integer+integer2;
                }
            }).subscribe(integer -> System.out.println("reduce:"+integer));
```

##### （19）Publish 操作符

> 将普通的Observable转换为可连接的Observable（ConnectableObservable），ConnectableObservable是Observable的子类。 可连接的Observable (connectable Observable)与普通的Observable差不多，不过它并不会在被订阅时开始发射数据，而是直到使用了Connect操作符时才会开始，这样可以更灵活的控制发射数据的时机。 注意：如果一个ConnectableObservable已经开始发射数据，再对其进行订阅只能接受之后发射的数据，订阅之前已经发射过的数据就丢失了。

> 关键词：发射可以控制，订阅完不会立即发射。

```csharp
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(5);
        //使用publish操作符将普通Observable转换为可连接的Observable
        ConnectableObservable<Long> connectableObservable = obs.publish();
        //第一个订阅者订阅，不会开始发射数据
        connectableObservable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("1.onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                System.out.println("1.onError");
            }
            @Override
            public void onNext(Long along) {
                System.out.println("1.onNext:"+along+"->time:"+ sdf.format(new Date()));
            }
        });
        //开始发射数据
        System.out.println("start time:" + sdf.format(new Date()));
        connectableObservable.connect();
        //第二个订阅者延迟2s订阅，这将导致丢失前面2s内发射的数据
        connectableObservable.delaySubscription(2, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("2.onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                System.out.println("2.onError");
            }
            @Override
            public void onNext(Long along) {
                System.out.println("2.onNext:"+along+"->time:"+ sdf.format(new Date()));
            }
        });
```

###### 条件和布尔操作 -- 这些操作符可用于单个或多个数据项，也可用于Observable。这里主要介绍all、amb、skipUtil这三个操作符。

> - All — 判断Observable发射的所有的数据项是否都满足某个条件
> - Amb — 给定多个Observable，只让第一个发射数据的Observable发射全部数据
> - Contains — 判断Observable是否会发射一个指定的数据项
> - DefaultIfEmpty — 发射来自原始Observable的数据，如果原始Observable没有发射数据，就发射一个默认数据
> - SequenceEqual — 判断两个Observable是否按相同的数据序列
> - SkipUntil — 丢弃原始Observable发射的数据，直到第二个Observable发射了一个数据，然后发射原始Observable的剩余数据
> - SkipWhile — 丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发射原始Observable剩余的数据
> - TakeUntil — 发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知
> - TakeWhile — 发射原始Observable的数据，直到一个特定的条件为真，然后跳过剩余的数据

##### （20）All操作符

> 对Observable发送的所有数据根据某个条件进行判断，当其发射出去的数据都满足该条件时，则返回true，否则返回false。

> 关键点：都满足该条件,才发送一个true,否则返回false。

```tsx
            Observable.just(2, 2, 1, 4).all(new Func1<Integer, Boolean>() {
                @Override
                public Boolean call(Integer integer) {
                    //System.out.println("integer:" + integer);
                    if (integer % 2 == 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }).subscribe(flag -> System.out.println(flag));
```

##### （21）Amb操作符

> 是对2到9个Observable进行处理，这些Observable会形成一种竞争关系，当哪个Observable最先发射出数据，则amb进行发射这个Observable里的数据，而其它的Observable将被丢弃。
> 关键字：只发送其中一组，其他组被抛弃

```csharp
            Subscriber<Integer> mySubscriber = new Subscriber<Integer>() {
                @Override
                public void onNext(Integer s) {
                    System.out.println("onNext:" + s);
                }

                @Override
                public void onCompleted() {
                    System.out.println("onCompleted!");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("onError:" + e.getMessage());
                }
            };
            Observable.amb(
                    createDelayObservable(4), 
                    createDelayObservable(3),
                    createDelayObservable(2), 
                    createDelayObservable(1))//发射出去的是这个，内容是1,2,3
            .subscribe(mySubscriber);
```

##### （22）SkipUtil操作符

> 是根据一个目标Observable为基准，当目标Observable没发射出去数据的时，原Observable发射出去的数据将会被忽略，当目标Observable发射数据时，则原Observable才开始发射数据。

> 关键词:skipUntil(Observable.just(100))这个是目标，他发射数据之前，原来的Observable发射出去的数据将会被忽略

```csharp
                Subscriber<Long> mySubscriber = new Subscriber<Long>() {
                        @Override
                        public void onNext(Long s) {
                            System.out.println("onNext:" + s);
                        }

                        @Override
                        public void onCompleted() {
                            System.out.println("onCompleted!");
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("onError:" + e.getMessage());
                        }
                    };
Observable.interval(1, TimeUnit.SECONDS)  .skipUntil(Observable.just(100).delay(5, TimeUnit.SECONDS))//延迟5s发送，目标Observable
                    //.skipUntil(Observable.timer(5, TimeUnit.SECONDS))
                    .subscribe(mySubscriber);
```

###### 辅助操作符 -- 辅助操作一组用于处理Observable的操作符.这里介绍下delay、do、SubscribeOn三个操作符。

> - Delay — 延迟一段时间发射结果数据
> - Do — 注册一个动作占用一些Observable的生命周期事件，相当于Mock某个操作
> - Materialize/Dematerialize — 将发射的数据和通知都当做数据发射，或者反过来
> - ObserveOn — 指定观察者观察Observable的调度程序（工作线程）
> - Serialize — 强制Observable按次序发射数据并且功能是有效的
> - Subscribe — 收到Observable发射的数据和通知后执行的操作
> - SubscribeOn — 指定Observable应该在哪个调度程序上执行
> - TimeInterval — 将一个Observable转换为发射两个数据之间所耗费时间的Observable
> - Timeout — 添加超时机制，如果过了指定的一段时间没有发射数据，就发射一个错误通知
> - Timestamp — 给Observable发射的每个数据项添加一个时间戳
> - Using — 创建一个只在Observable的生命周期内存在的一次性资源

##### （23）delay

> 让原始Observable在发射每项数据之前都暂停一段指定的时间段，结果是Observable发射的数据项在时间上整体延后一段时间.注意：delay不会平移onError通知，它会立即将这个通知传递给订阅者，同时丢弃任何待发射的onNext通知。但是它会平移一个onCompleted通知。

```java
Observable<Integer> obs = Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                    for (int i = 0; i < 5; i++) {
                        if (i > 2) {
                            // subscriber.onError(new Throwable("VALUE TO MAX"));
                            // //delay不会平移onError通知
                        }
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.computation());

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            /*
             * Delay操作符让原始Observable在发射每项数据之前都暂停一段指定的时间段。
             * 效果是Observable发射的数据项在时间上向前整体平移了一个增量
             *
             * 注意：delay不会平移onError通知，它会立即将这个通知传递给订阅者，同时丢弃任何待发射的onNext通知。
             * 然而它会平移一个onCompleted通知。
             */
            System.out.println("delay start:" + sdf.format(new Date()));
            obs.delay(2, TimeUnit.SECONDS).subscribe(new Subscriber<Integer>() {
                @Override
                public void onCompleted() {
                    System.out.println("delay onCompleted" + sdf.format(new Date()));
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("delay onError" + e.getMessage());
                }

                @Override
                public void onNext(Integer integer) {
                    System.out.println("delay onNext:" + sdf.format(new Date()) + "->" + integer);
                }
            });
```

##### （24）Do系列操作符就是为原始Observable的生命周期事件注册一个回调，当Observable的某个事件发生时就会调用这些回调。

> - RxJava实现了很多doxxx操作符
> - doOnEach：为 Observable注册这样一个回调，当Observable没发射一项数据就会调用它一次，包括onNext、onError和 onCompleted
> - doOnNext：只有执行onNext的时候会被调用
> - doOnSubscribe： 当观察者（Sunscriber）订阅Observable时就会被调用
> - doOnUnsubscribe： 当观察者取消订阅Observable时就会被调用；Observable通过onError或者onCompleted结束时，会反订阅所有的Subscriber
> - doOnCompleted：当Observable 正常终止调用onCompleted时会被调用。
> - doOnError： 当Observable 异常终止调用onError时会被调用。
> - doOnTerminate： 当Observable 终止之前会被调用，无论是正常还是异常终止
> - finallyDo： 当Observable 终止之后会被调用，无论是正常还是异常终止。

```csharp
        Observable.just(1, 2, 3)
                // 只有onNext的时候才会被触发
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer item) {
                        System.out.println("-->doOneNext: " + item);
                    }
                }).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.out.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });

        System.out.println("doOnEach,doOnError------------------------");
        Observable.just(1, 2, 3)
                // Observable每发射一个数据的时候就会触发这个回调，不仅包括onNext还包括onError和onCompleted
                .doOnEach(new Action1<Notification<? super Integer>>() {
                    @Override
                    public void call(Notification<? super Integer> notification) {
                        System.out.println("-->doOnEach: " + notification.getKind() + ":" + notification.getValue());
                        if ((int) notification.getValue() > 1) {
                            throw new RuntimeException("Item exceeds maximum value");
                        }
                    }
                })
                // Observable异常终止调用onError时会被调用
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("-->doOnError: " + throwable.getMessage());
                    }
                }).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.out.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });

        System.out.println("doxxx------------------------");
        Observable.just(1, 2, 3).doOnCompleted(new Action0() {
            @Override
            public void call() {
                System.out.println("-->doOnCompleted:正常完成onCompleted"); // 数据序列发送完毕回调
            }
        }).doOnSubscribe(() -> System.out.println("-->doOnSubscribe:被订阅")) // 被订阅时回调
                // 反订阅（取消订阅）时回调。当一个Observable通过OnError或者OnCompleted结束的时候，会反订阅所有的Subscriber
                .doOnUnsubscribe(() -> System.out.println("-->doOnUnsubscribe:反订阅"))
                // Observable终止之前会被调用，无论是正常还是异常终止
                .doOnTerminate(() -> System.out.println("-->doOnTerminate:终止之前"))
                // Observable终止之后会被调用，无论是正常还是异常终止
                .finallyDo(() -> System.out.println("-->finallyDo:终止之后")).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.out.println("Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("Sequence complete.");
                    }
                });
```

##### （25）线程切换SubscribeOn、observeOn：

> - 这两个操作符对于Android开发来说非常适用，因为Android中只能在主线程中修改UI，耗时操作不能在主线程中执行，
> - 所以我们经常会创建新的Thread去执行耗时操作，然后配合Handler修改UI，或者使用AsyncTask。
> - RxJava中使用这两个操作符能够让我们非常方便的处理各种线程问题。
> - SubscribeOn：指定Observable自身在哪个调度器上执行（即在那个线程上运行），如果Observable需要执行耗时操作，
> - 一般我们可以让其在新开的一个子线程上运行，好比AsyncTask的doInBackground方法。
> - observeOn:可以使用observeOn操作符指定Observable在哪个调度器上发送通知给观察者（调用观察者的onNext,onCompleted,onError方法）。
> - 一般我们可以指定在主线程中观察，这样就可以修改UI，相当于AsyncTask的onPreExecute 、onPrograssUpdate和onPostExecute 方法中执行
> - Schedulers.computation( ) 用于计算任务，如事件循环或和回调处理，不要用于IO操作(IO操作请使用Schedulers.io())；默认线程数等于处理器的数量
> - Schedulers.from(executor) 使用指定的Executor作为调度器
> - Schedulers.immediate( ) 在当前线程立即开始执行任务
> - Schedulers.io( ) 用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长；对于普通的计算任务，请使用Schedulers.computation()；Schedulers.io( )默认是一个CachedThreadScheduler，很像一个有线程缓存的新线程调度器
> - Schedulers.newThread( ) 为每个任务创建一个新线程
> - Schedulers.trampoline( ) 当其它排队的任务完成后，在当前线程排队开始执行

```csharp
            System.out.println("currentThread:" + Thread.currentThread().getName());
            Observable<Integer> obs = Observable.create(new Observable.OnSubscribe<Integer>() {
                @Override
                public void call(Subscriber<? super Integer> subscriber) {
                    System.out.println("on subscrib:" + Thread.currentThread().getName());
                    subscriber.onNext(1);
                    subscriber.onCompleted();
                }
            });

            // 在新建子线程中执行，在主线程中观察    AndroidSchedulers.mainThread()
            obs.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread())
                    .subscribe(i -> System.out.println("mainThread-onNext:" + Thread.currentThread().getName()));
            System.out.println("-------------------");
            obs.delaySubscription(2, TimeUnit.SECONDS).subscribeOn(Schedulers.trampoline()) // 用于计算任务，如事件循环或和回调处理
                    .observeOn(Schedulers.immediate()) // 在当前线程立即开始执行任务
                    .subscribe(i -> System.out.println("immediate-onNext:" + Thread.currentThread().getName()));
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
```

### 三、操作符在Android中的应用实践

#### 1.线程切换注意事项

subscribeOn()和observeOn()这两个操作符在使用的时候可能弄不清楚，下面操作符使用分类图可以参考一下。
create() , just() , from() 等 -- 事件产生
map() , flapMap() , scan() , filter() 等 -- 事件加工
subscribe() -- 事件消费
事件产生：默认运行在当前线程，可以由 subscribeOn() 自定义线程
事件加工：默认跟事件产生的线程保持一致, 可以由 observeOn() 自定义线程
事件消费：默认运行在当前线程，可以有observeOn() 自定义

#### 2.线程的生命周期

在Android开发中生命周期可以使用rxlifecycle作为自动生命周期管理。
在app开发中异步线程会产生内存泄漏的问题，是所有的异步线程都会产生，不只是RxJava才有。并且RxJava中
RxJava除了这种自动回收以外。还可以使用手动取消线程。RxJava有取消订阅的方法可以使用。
注意一点在已经进入非主线程的代码执行的时候，是取消不了发射的，但是可以取消订阅就可以解决内存泄漏的问题了。

#### 3.线程池的使用

RxJava的线程处理是可以配置线程池的
ThreadPoolExecutor executor；
.subscribeOn(Schedulers.from(executor)) //发射端使用线程池
.observeOn(AndroidSchedulers.mainThread()) //接收端使用主线程
这组线程池的搭配可以替代AsyncTask使用了，也可以与AsyncTask共用一个线程池来使用。



#### 来源：https://www.jianshu.com/p/c4d927e04658

