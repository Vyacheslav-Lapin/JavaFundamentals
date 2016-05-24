@echo off

if not "%COMPILE_TARGET%" == "" goto gotCompileTarget
set COMPILE_TARGET=out

:gotCompileTarget
if not "%SRC_ROOT%" == "" goto gotSrcRoot
set SRC_ROOT=src\main\java

:gotSrcRoot
if not "%MAIN_CLASS_PATH%" == "" goto gotMainClass
set MAIN_CLASS_PATH=%SRC_ROOT%\com\epam\courses\jf\intro\homework\t01\main\Main.java

:gotMainClass
javac -d %COMPILE_TARGET% -cp %SRC_ROOT% %MAIN_CLASS_PATH%
