// 生产者和消费者模式主要解决的问题是类似库存量的变化的功能实现
// 该模式特别需要注意线程同步问题

class store{
  private store instance;
  private int food;
  
  public static void main(String arg){
    sotre = getinstance();
    for(int i=0;;i++){
      // 每次生产者生产的数量大于等于消费者的消费量，则可以一直支持下去
      store.add(new producer().produce());
      store.get(new consumer().get())
      new Thread().sleep(2000);
    }
    
    
  }
  
  public static int getIntance(){
    if(instance == null){
      food  = 0;
      instance = new store();
      return instance;
    }else{
      return instance;
    }
  }
  
  public void add(int food){
    this.food= this.food + food;
  }
  
  //为了库存的线程安全，需要同步锁
  public synchronized int get(int food){
    if((this.food - food) > 0){
      this.food = this.food - food;
      return food;
    }else{
      return 0;
    }
  }
  
}

class producer{
  public int produce(){
    return 5;
  }
}

class consumer{
  public void get(){
    return 3;
  }
}
