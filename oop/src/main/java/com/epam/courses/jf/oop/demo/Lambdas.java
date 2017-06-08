package com.epam.courses.jf.oop.demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lambdas {

    public static void main(String[] args) {
        JButton button = new JButton();

        button.addActionListener(event -> System.out.println("button clicked"));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.out.println("button clicked");
            }
        });
    }
}
