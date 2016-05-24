@echo off

if not "%COMPILE_TARGET%" == "" goto gotCompile
set COMPILE_TARGET=out

:gotCompile
del /s /f /q %COMPILE_TARGET%\*.*
rem mkdir %COMPILE_TARGET%
