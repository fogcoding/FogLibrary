两种方法删除BAT自身并且删除BAT所在目录。
*******************
dir |find "的目录" >1.txt
rem for /f "tokens=1 delims=的目录" %a in (1.txt) do rd %a /q /s
for /f "tokens=1 delims=的目录" %%a in (1.txt) do set ve=%%a
cd/
rd %ve% /q/s

************************
set var=%cd%
cd/
rd /q/s %var%
pause

*************



## 批处理运行遇到“另一个程序正在使用此文件,进程无法访问”

 原因是该批处理文件的文件名和系统命令重名，bat内部有同名的命令，它就会不断地调用自己（类似于call自己）。。。