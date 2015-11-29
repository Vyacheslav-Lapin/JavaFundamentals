package javase01.t06;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NotePadTest {

    NotePad notePad = new NotePad();

    @Test
    public void writeNote() {
        assertNotNull(notePad.addNote("Title", "Body"));
    }

    @Test
    public void createNotes() {
        fill(100_000);
    }

    private void fill(int capacity) {
        for (int i = 0; i < capacity; i++)
            assertEquals(notePad.addNote("Title " + i, "Body" + i).getId(), i);
    }

    @Test
    public void printNotes() {

        fill(50);

        for (Note note: notePad.getNotes())
            System.out.println(note);
    }

    @Test
    public void editNote() {
        Note note = notePad.addNote("Title", "Body");

        assertEquals(note, notePad.getNote(note.getId()));

        assertEquals(
                note.setBody("Another body").setTitle("Another title"),
                notePad.getNote(note.getId()));
    }
}
