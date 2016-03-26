package com.epam.courses.jf.intro.homework.t06;

import java.util.Arrays;

public class NotePad {

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private Note[] notes;
    private int index;

    public NotePad(int initialCapacity) {
        assert initialCapacity > 0;
        this.notes = new Note[initialCapacity];
    }

    public NotePad() {
        this(16);
    }

    public Note addNote(String title, String body) {
        Note note = new Note(index++).setTitle(title).setBody(body);
        return isPossibleToAdd() ? notes[note.getId()] = note :
                null; // throw new RuntimeException("It`s impossible to add the Note - array already have a maximum size!");
    }

    private boolean isPossibleToAdd() {
        return index < notes.length || hasGrew();
    }

    private boolean hasGrew() {

        int capacity = notes.length << 1; // * 2 with overflow insurance
        
        if (capacity - index < 0) // is previous operation has overflow as a result?
            if (index - MAX_ARRAY_SIZE <= 0)
                capacity = MAX_ARRAY_SIZE;
            else
                return false;
        
        notes = Arrays.copyOf(notes, capacity);

        return true;
    }

    public Note getNote(int id) {
        assert id >= 0 && id < index;
        return notes[id];
    }

    public Note[] getNotes() {
        return Arrays.copyOf(notes, index); // better then clone for that case: result haven`t null`s at his tail
    }
}
