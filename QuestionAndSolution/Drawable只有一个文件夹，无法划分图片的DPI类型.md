### 是不是只有一个？

#####   如果原本项目里面有drawable-mhdpi等文件夹，可切换到project目录查看

### 确实只有一个文件夹，如何创建文件夹对图片正确分类？

#####   自己新建！
##### 新建步骤：res(右键)-> new -> android source dirctory -> recource type 选择为drawable -> Aiailable qualifiers 选择density >>新建 ->
选择目标DPI的种类，完成创建。
##### 注：不能在drawable文件下直接创建，否则无法通过R.drawable.x 引用

REF： [Stackoverflow-Drable Folder :How to put image for multiple DPI?](http://stackoverflow.com/questions/29294287/android-studio-drawable-folder-how-to-put-images-for-multiple-dpi?noredirect=1)
