package com.epam.courses.jf.intro;

import lombok.val;

public class CommandArgUsage {
    public static void main(String[] args) {

        for (val s : args)
            System.out.println("Следующий аргумент = " + s);
    }
}
