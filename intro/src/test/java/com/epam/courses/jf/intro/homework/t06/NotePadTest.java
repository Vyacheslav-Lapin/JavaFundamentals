package com.epam.courses.jf.intro.homework.t06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotePadTest {

    NotePad notePad = new NotePad();

    @Test
    void writeNote() {
        assertNotNull(notePad.addNote("Title", "Body"));
    }

    @Test
    void createNotes() {
        fill(100_000);
    }

    private void fill(int capacity) {
        for (int i = 0; i < capacity; i++)
            assertEquals(
                    notePad.addNote("Title " + i, "Body" + i)
                            .getId(),
                    i);
    }

    @Test
    void printNotes() {

        fill(50);

        for (Note note: notePad.getNotes())
            System.out.println(note);
    }

    @Test
    void editNote() {
        Note note = notePad.addNote("Title", "Body");

        assertEquals(note, notePad.getNote(note.getId()));

        assertEquals(
                note.setBody("Another body")
                        .setTitle("Another title"),
                notePad.getNote(note.getId()));
    }
}
