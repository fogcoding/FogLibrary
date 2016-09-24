// 代理模式就是某个对象不能直接做某些事情或者不方便做某些事情的时候，通过一个代理对象来实现
// 例如下面这种情况来说，只有Agency能够获取到数据内容，那么其他对象想要访问就必须通过Agency对象来实现
// 该模式可以实现权限管理或功能解耦，功能结构就是一个用来执行的对象不会做任何事，但可以通过别的对象来完成
// 这样拓展和修改代码就只需要修改代理的相关内容，与其他的模块不会产生相互影响



class Visitor {
  public staic void main(String arg){
    Visitor visitor = new Visitor();
    System.out.println(visitor.get());
    
  }
  
  Visitor(){
  }
  
  public String get(){
    return new Agency().get();
  }

}

class Agency{
  agency(){
  }
  
  public String get(){
    return new data.get();
  }
  
  class data{
    private String Database = "严格保密的数据"；
    
    public String get(){
      return this.Database;
    }
  }

}
