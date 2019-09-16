## 有关Serviced相关错误的收录：

## 1.同一套代码已经在一个Proect能跑，把代码复制到另一个Proect中，无法运行
* 解决办法：在manifests中注册对应的Service即可
* 错误原因：Servive同Acitivity一样是需要注册的
* 备注：如果出现此种情况，或有log显示出：Broken pip,用以表示读写通道关闭或异常（此种错误信息多半是需要在manifests中注册，而咩有注册出现的提示）
