



#### if

```shell
#!/bin/bash
# 这里的变量声明不能有多余的空格还是其余的无效内容，格式要求特别严谨
a=1;

if((i>0));then
  echo 'a >0'
elif((i>1));then
  echo 'i>1'
else
  echo 'a>2'
fi

b=2;

if[["b!=0" && "b==2"]];then
  echo 'true'
else
  echo 'false'

```



#### while

```shell
#!/bin/bash

declare -i i=1

while((i<100))
do
  echo "i="$i
  let i++
done
# echo 'shell done!'

```



#### for

```shell
#!/bin/bash

# 一个实例
for a in {1..10}
do
  echo 'now a = '$a;
done

# 更贴近java语言的写法
for((i=1;i<=5;i++));do
    echo "这是第 $i 次调用";
done;



```



#### do-while













