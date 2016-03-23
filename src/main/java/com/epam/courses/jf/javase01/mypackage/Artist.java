package com.epam.courses.jf.javase01.mypackage;

public class Artist {
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Artist{name='" + name + "\'}";
    }

    public Artist duet(Artist artist) {
        name = name + " + " + artist.name;
        return this;
    }
}
