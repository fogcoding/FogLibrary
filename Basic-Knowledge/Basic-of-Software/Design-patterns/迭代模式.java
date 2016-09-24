// 迭代模式主要解决的问题是提供一种方法顺序访问一个聚合对象中的各种元素，而又不暴露该对象的内部表示
// 该模式已经在java中通过iterator接口对一些数据集合实现了迭代器功能

// 而如果我们自己来实现的话，步骤为：
// step 1.抽象一个iterator的接口
public interface iterator{
  T everyone();
}
// step 2.需要实现迭代对象通过实现该接口来给出访问每个元素
class data implments interator{
  private ing[] datacore = new int[]();
  private int index = 0;
  
  @overwrite
  public Object everyone(){
    id(index < datacore.length()){
      return datacore[index++];
    }else{
      return null;
    }
  }
}
// step 3. 通过接口里的方法获取数据信息
class operation{
  public static void main(String arg){
    data data = new data();
    while(data.everyone() != null){
      System.out.println(data.everyone());
    }
  }
}
// 迭代器不能在获取数据信息后，更改数据的内容
// 由于封装的特性，一个对象的内部数据，不能通过别的对象来修改
// 只能由对象向外提供修改自己数据的方法，然后别的对象通过调用该方法来修改数据内容
