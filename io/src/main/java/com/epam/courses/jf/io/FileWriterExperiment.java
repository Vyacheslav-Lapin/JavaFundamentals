package com.epam.courses.jf.io;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileWriterExperiment {

    @SneakyThrows
    public static void main(String[] args) {
        // JavaSE7 style
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(
                        new FileWriter("text.txt")))) {
            // работа с потоком через потоковый объект
            pw.println("I'm a sentence in a text-file.");
        }
    }
}
