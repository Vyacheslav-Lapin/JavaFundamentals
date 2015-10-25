@echo off
cd src\main\java
javac javase01\t01\main\Main.java -d ..\..\..\out
cd ..\..\..\out
java javase01.t01.main.Main
cd ..
