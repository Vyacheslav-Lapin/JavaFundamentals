@echo off
cd src\main\java
javac -d out com\epam\courses\jf\javase01\t01\main\Main.java
cd ..\..\..\out
java javase01.t01.main.Main
cd ..
