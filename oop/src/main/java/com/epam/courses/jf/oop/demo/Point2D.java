package com.epam.courses.jf.oop.demo;

public class Point2D {

    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point2D(int size) {
        this(size, size);
    }

    public void setX(int _x) {
        x = _x;
    }

    public int getX() {
        return x;
    }


    // ...
}

