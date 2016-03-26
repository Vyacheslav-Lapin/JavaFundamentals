package com.epam.courses.jf.intro.homework.t06;

public class Note {
    private String title;
    private String body;
    private int id;

    public Note(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Note setBody(String body) {
        this.body = body;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && id == ((Note) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Note{" + "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", id=" + id +
                '}';
    }
}
