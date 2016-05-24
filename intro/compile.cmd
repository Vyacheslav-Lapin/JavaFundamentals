@echo off

set COMPILE_TARGET=out
set SRC_ROOT=src\main\java
set MAIN_CLASS_PATH=%SRC_ROOT%\com\epam\courses\jf\intro\homework\t01\main\Main.java

javac -d %COMPILE_TARGET% -cp %SRC_ROOT% %MAIN_CLASS_PATH%
