
// 工厂模式就是一个操作可以有不同的对象来完成，我们只需要传入完成对象的参数，就能获得该对象产生的结果
// 工厂模式有着相当多的子类工厂模式


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






