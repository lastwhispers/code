@echo off & setlocal EnableDelayedExpansion

title 杀死端口

set /p port=请输入端口号（0~65535）：

set pid=0
for /f "tokens=2,5" %%b in ('netstat -ano ^| findstr ":%port%"') do (
    set temp=%%b
    for /f "usebackq delims=: tokens=1,2" %%i in (`set temp`) do (
        if %%j==%port% (
            taskkill /f /pid %%c
            set pid=%%c
            echo 端口号【%port%】相关进程以杀死
        ) else (
            echo 不是本机占用端口【%port%】
        )
    )
)
if !pid!==0 (
   echo 端口号【%port%】没有占用
)

echo 操作完成

pause