// 生产者和消费者模式主要解决的问题是类似库存量的变化的功能实现
// 该模式特别需要注意线程同步问题

class store{
  private int food;
  public static void main(String arg){
    producer producer = new producer();
    store.get();
    
    
  }
  
  public static int getFood(){
    if(food == null){
      food  = 0;
      return food;
    }else{
      return food;
    }
  }
  
  public void add(int food){
    this.food= this.food + food;
  }
  
  public int get(int food){
    
    
  }
  
}

class producer{
  public int produce(){
    
    
  }
}

class consumer{
  public void get(){
      
  }
  
}
