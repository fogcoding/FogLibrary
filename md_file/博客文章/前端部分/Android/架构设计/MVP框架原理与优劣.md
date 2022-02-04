# MVP框架原理使用与优劣

知识点简介：

> 任何一个项目，其实都包含了数据库，后端，前端等部分，而前端部分，为了更好的组织代码，通常会选择一些组织形式，便于理解项目和功能迭代。
>
> 因为科学的组织结构，能够让项目条理清晰，逻辑通顺，减轻维护的压力和难度。
>
> 还有一个很重要的好处就是各业务层解耦，使得单元测试十分方便，极大地减少了开发时间,原因如下。
>
> * 单元测试可以大大地减少程序运行时才能发现的问题，这通常可以节省「用户反馈」->「Bug修复」->「新版本发布」->「用户安装新版本」这个耗时长达一周以上的过程。所以，程序的可测试性对于程序的稳定性是异常重要的。
>
> 
>
> 常见的组织结构有：MVC,MVP,MVVM这三种。
>
> 
>
> ### MVP架构模式
>
> ![MVC架构模式](https://user-images.githubusercontent.com/16816311/64138310-1e617900-ce2f-11e9-93ae-ecc3cb162f09.png)
>
> 



####  MVP模式的基本使用分析

##### model层

```java
public interface MVPIModel<T> {

    void getData(MVPModel.MVPCallBack<T> callBack);

}
```



```java
public class MVPModel implements MVPIModel<String>{

    @Override
    public void getData(MVPCallBack<String> callBack) {
        callBack.onSuccess("get some info from server!");
    }

    public interface MVPCallBack<T>{

        void onSuccess(T t);

        void onFailure(Exception e);

    }

}
```

​	显而易见的是，model层分为接口的抽象和接口的实现。

​	接口的抽象就是用于与view交互的数据是如何获取和处理的，一个view需要展示的数据类型远远不止一个，所以这里的model抽象也需要关联很多的数据的获取，修改等curd，乃至于更复杂的操作。

​	接口的实现是对于数据操作的详细处理过程，多半是操作数据库或者发起http访问。

​	由于model始终都是要提供给presenter交互，所以presenter里面一定会有model的调用过程。

​	为了避免代码耦合过于严重，使得代码修改变得十分复杂，多半在presenter里面是处理的接口对象，只不过会给该接口指定某种实现。这样一来，如果具体的业务逻辑发生变动或更改只需要修改接口的实现一行代码，就能实现整体的功能替换，十分灵活。

​	十分值得注意的是，model里面自带了一个内部回调接口。

​	因为model多半要与数据库和网络沟通，有太多的不确定性，无法给出稳定和即时的相应，所以需要使用回调的方式，在某个时间获取到数据后，调用接口通知后续的处理操作。还有另一个好处就是可以在presenter里面编写回调代码，使得获取数据和处理数据又一次分离，代码更加解耦。

​	这个回调接口的设计就是面向接口变成的精髓，直接解决了数据获取时间的不确定问题和数据结果的处理显示需要跨越对象的问题，通过一个接口的设计让一串可执行代码顺利地实现了连续跳跃传递，直至最终显示。

##### View层

```java
public interface MVPView {

    void showInfo();

    void showError();

    void showResult();

}
```



```
package com.fogstudy.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fogstudy.R;
import com.fogstudy.mvp.presenter.MVPInteractor;
import com.fogstudy.mvp.presenter.MVPPresenter;

public class MVPActivity extends AppCompatActivity implements MVPView {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private TextView text;

    private MVPPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        presenter = new MVPPresenter(this, new MVPInteractor());

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        text = findViewById(R.id.text);

        btn1.setOnClickListener(v -> presenter.pre("显示正常信息"));

        btn2.setOnClickListener(v -> presenter.pre(""));

        btn3.setOnClickListener(v -> presenter.pre("显示处理结果"));

    }

    @Override
    public void showInfo() {
        text.setText("显示正常信息");
    }

    @Override
    public void showError() {
        text.setText("显示错误信息");
    }

    @Override
    public void showResult() {
        text.setText("显示处理结果");
    }


}

```



​	View接口抽象的是view具体需要处理的交互逻辑操作，而Activity实现了这个抽象接口，从而使得具体的activity可以抽象为一个view接口，传递给presenter即可通过接口方法直接操作view的交互逻辑。



##### Presenter层

```java

public class MVPInteractor {

    public interface operation{
        void onError();

        void onInfo();

        void onResult();
    }

    public void handling(String pre, operation operation) {
        if (TextUtils.isEmpty(pre)){
            operation.onError();
        }else if (pre.contains("结果")){
            operation.onError();
        }else{
            operation.onInfo();
        }
    }

}
```



```java

public class MVPPresenter implements MVPInteractor.operation {

    private MVPView view;
    private MVPInteractor interactor;
    private MVPIModel<String> mvpiModel;

    public MVPPresenter(MVPView view, MVPInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        this.mvpiModel = new MVPModel();
    }

    @Override
    public void onError() {
        view.showError();
    }

    @Override
    public void onInfo() {
        mvpiModel.getData(new MVPModel.MVPCallBack<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        view.showInfo();
    }

    @Override
    public void onResult() {
        view.showResult();
    }

    public void pre(String args) {
        interactor.handling(args,this);
    }

}
```



​	从上面的代码来看呢，presenter是一个控制view层的操作的媒介，它是为了避免view和data直接耦合的中间抽象层。

​	view层本身只需要持有一个presenter对象，然后自己实现对应的view接口，传递给该presenter，最后直接通过presenter直接操控view的逻辑交互。这样虽然看起来复杂了很多，但是view自己对于交互操作的设计是传递给presenter的，修改了view接口的实现，对presenter的运行毫无影响，另一方面，presenter控制view进行业务逻辑切换的修改，对view本身对于某些业务的实现没有任何影响。实现了将业务逻辑的具体实现和业务逻辑的控制实现解耦。

---



#### MVP的优劣分析

##### 优势

* 其实这样的分离是无形中多增加了许多代码，贸然一看是把原本并不复杂的代码搞得更复杂了。 

* 其实不然，在业务逻辑很少，代码量很少的时候，为了维持一个框架的基本组成而编写的代码自然会显得十分多余。可当实际的业务处理逻辑增加到足够多的时候，没有清晰的框架梳理，代码会难以阅读和理解，并且修改会产生极大的变动，造成一处修改，导致多处修改，乃至于整个处理逻辑完全失效的极端情况发生。

* 这个框架是给不断膨胀的业务逻辑划分了清晰的结构和路线，使得即使代码不断膨胀也能够被拆分为无数个小小的模块，造成的再次修改和维护代价会维持在一个较低的水平。而且，当业务代码膨胀以后，为了维持组织架构的代码占用的比例会极端减少，乃至于可有可无，却发挥着巨大的作用，这无疑是工程学实践的宝贵经验。
* 由于各种抽象和解耦，导致了每个单一模块的问题被逐步细分，所以非常方便单元测试，可以提早发现各种可能出现的bug



##### 劣势	

* 对代码结构的精确化细分，会造成巨量的框架代码。当一个项目在业务逻辑并不十分庞大的时候，这样做是没什么好处，反而会造成大量没有什么意义的框架代码。

* MVP是个足够科学并有着一定理论基础的代码结构组织形式，所以他需要一定的学习成本，如果并不理解MVP框架的理论和基础知识，无疑大量的接口和中间层，抽象层会产生巨大的阅读压力和分析成本。

* 前人以MVP为架构形式组织了代码，那么后来接手的和同团队进行维护和修改这个项目的人，也需要有意识地去按照MVP架构的形式去编写代码，保持代码在增加过程中依然保持MVP的风格。否则就会出现混搭的情况，一旦混搭过多，代码会显得极其混乱，形成传说中的“山”。这种标准的推行是一个很考验团队合作和员工素质的事情。



#### 可能出现的问题

##### 内存泄漏

​	因为presenter持有Activity的引用，如果处理不当，会导致无法回收，所以在Activity执行onDestory方法时，应当调用presenter的自定义清空方法，将持有的View引用置null。另一方面，在presenter执行网络请求或者其他耗时操作的时候，结果返回都必须执行if(view != null)的判断操作，避免出现问题。

##### 接口类数量暴涨

​	

##### 生命周期难以控制





------

#### 总结

​	万事有利有弊，不可能有一个十全十美的解决方案，一切都在不断的变化之中。

​	要想清楚到底需要什么，足够清醒地去分析一个方案的推行会有什么后果和代价，不能人云亦云，要当高级工程师，进行技术决策和方案选择，架构设计，这些都是基本功底。

​	我要努力研究，研究再研究，争取早日达到这个水平。



