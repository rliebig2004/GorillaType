package persistence;

import model.Entry;
import model.Scoreboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// Test the JsonWriter Class
class JsonWriterTest extends JsonTest {
    private Scoreboard scr;
    private List<Entry> entries;
    private Entry entry1;
    private Entry entry2;

    // EFFECTS: initializes a new scoreboard, a list of entries, and two new entries
    @BeforeEach
    void runBefore() {
        this.scr = new Scoreboard();
        this.entries = new ArrayList<>();
        this.entry1 = new Entry(2.34F, 3.24D, 4.23D);
        this.entry2 = new Entry(1F,2D,3D);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyScoreboard() {
        try {
            this.scr  = new Scoreboard();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyScoreboard.json");
            writer.open();
            writer.write(this.scr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyScoreboard.json");
            this.scr = reader.read();
            this.entries = this.scr.getListOfEntries();
            assertEquals(0, this.entries.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralScoreboard() {
        try {
            this.scr.addEntries(this.entry1);
            this.scr.addEntries(this.entry2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralScoreboard.json");
            writer.open();
            writer.write(this.scr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralScoreboard.json");
            this.scr = reader.read();
            this.entries = this.scr.getListOfEntries();
            assertEquals(2, this.entries.size());
            checkEntry(2.34F, 3.24D, 4.23D, this.entries.get(0));
            checkEntry(1F, 2D, 3D, this.entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
