// 观察者模式就是设置一个通知所有被观察者的对象，对所有需要知情的对象集中管理
// 这种通知的方式，可以实现对某个特定情况的集中管理
// 个人理解这种模式就是在整个程序中实现条件判断语句

// 传统观察者模式
class Observer{
  private Arraylist users = new ArrayList();
  
  public static void main(String arg){
    Observer observer = new Observer();
    for(int i=0;i<10;i++){
      observer.add(new Observered());
    }
    
    if("条件满足" != null){
      observer.notifyAll();
    }
    
  }
  
  public boolean addUser(Observered user){
    return users.add(user);
  }
  
  public void notifyAll(){
    for(Observered o:users){
      o.getInformation();
    }
  }

}

class Observered{
  public void getInformation(){
    System.out.println("我知道了！");
  }
}

// 抽象观察者模式
// 对比传统观察者模式而言，就是被通知的对象不再是做同样的事情了，而是有着各自不同的反应

public interface Observered{
  void getInformation();
}

class husky implments Observered{
  //if（带出去遛弯，不管了）
  @overwrite
  public void getInformation(){
    Syytem.out.println("soon later,欸，卧槽，我在哪里？");
  }
  
}

class cat implments Observered{
  //if（带出去遛弯，不管了）
  @overwrite
  public void getInformation(){
    Syytem.out.println("Zzzzzzzzzzzzzzzzzz");
  }
}






