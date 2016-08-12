
// REF： 八种单例模式的写法对比 http://tianweili.github.io/blog/2015/03/02/singleton-pattern/

public class SingleTon(){

  private static SignleTon Instance = null;
  
  public SingleTon(){}
  
  public staic SingleTon getInstance(){
    if(Instance == null){
      Instance = new SingleTon();
    }
    return Instance;
  
  // 注：这样的写法并不能完全保证单例或者全局变量的数据同步
  // 不能保证的原因：
  // 在多线程情况下，多个线程同时访问该静态方法获取实例对象
  // 在多线程情况下，多个线程同时对该实例对象进行数据操作

  }



}
