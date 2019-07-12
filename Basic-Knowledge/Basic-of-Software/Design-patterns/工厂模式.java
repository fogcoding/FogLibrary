
// 工厂模式就是一个操作可以有不同的对象来完成，我们只需要传入完成对象的参数，就能获得该对象产生的结果
// 工厂模式有着相当多的子类工厂模式
/*
* 设计模式分为创建型，结构型，行为型三大类。
* 其中创建型的设计模式，目的是为了将对象的创建和使用分割，避免一个类既要负担创建的逻辑，还要负责使用时的逻辑，达到松耦合的目的。
*工厂模式就是其中典型的负责对象创建业务的模式
*具体类型包括：简单工厂模式，抽象方法工厂模式，抽象类工厂模式
*简单工厂的特点：传入参数即可得到对应的对象实例，而所获得的对象实例都具有共同的父类。
*抽象方法工厂模式：
*抽象类工厂模式：
*
*/


// 下面的代码并不是纯正的工厂模式，而是抽象方法模式和简单工厂的组合
// 简单工厂模式
class Product{
  public static void main(String arg){
    Product human = new Product();
    //获得第一个工厂的产品
    J j = human.get(new Factory1);
    //获得第二个工厂的产品
    K k = human.get(new Factory2);
  }

  public D get(T factory){
    return factory.provide();
  }

}

class Factory1{
  private String product = "工厂一的产品";

  public String provide(){
    return product;
  }

}

class Factory2{
  //工厂二的产品
  private int product = 20;

  public int provide(){
    return product;
  }

}


// 抽象工厂模式
//做法：抽象出一个工厂的接口，大家都可以去实现它，并且给出自己不同的产品
public interface Factory{
  T Product();
}

class elephant implments Facotry{
  @overwrite
  public T Product(){
    return new "犀牛角"
  }
}

class tiger implments Factory{
  @overwrite
  public T Product(){
    return new "虎骨酒";
  }
}
