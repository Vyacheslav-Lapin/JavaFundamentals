@echo off

rem clear
del out\*

rem compile
cd src\main\java
javac -d ..\..\..\out com\epam\courses\jf\javase01\homework\t01\main\Main.java
cd ..\..\..
