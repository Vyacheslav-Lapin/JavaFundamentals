package com.epam.courses.jf.intro;

public class WithAnotherClass {

    public static void main(String[] args) {
        AboutJava object = new AboutJava();
        object.printReleaseData();
    }
}

class AboutJava {
    void printReleaseData(){
        System.out.println("Java уже здесь!!!");
    }
}