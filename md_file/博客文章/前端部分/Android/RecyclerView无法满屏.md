
###RecyclerView无法满屏的原因：
####RecyclerView在适配的时候，会在RecyclerView控件存在的区域适配，
####我们由XML设计的Layout文件存在根布局，所有在Layout中显示出来的效果的基础是根布局的存在，
####如果我们在创建RecyclerView的适配器的时候，仅仅传入承载数据内容的控件，该控件就会失去在根布局的约束下呈现出来的效果，所以原本设计好的满屏效果无法在适配时实现

###解决方法：
####在创建适配器的数据容器的时候，将根布局也传进去：inflate(R.layout.item_view,parent,false);
####而不是仅仅传递数据的容器控件：inflate(R.layout.item_view,null);

