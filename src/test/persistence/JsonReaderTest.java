package persistence;

import model.Entry;
import model.Scoreboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// Tests the JsonReader Class
class JsonReaderTest extends JsonTest {
    private Scoreboard sr;
    private List<Entry> entries;

    // EFFECTS: Initializes a new scoreboard and a list of entries
    @BeforeEach
    void runBefore() {
        this.sr = new Scoreboard();
        this.entries = new ArrayList<>();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            this.sr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyScoreboard() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyScoreboard.json");
        try {
            this.sr = reader.read();
            this.entries = sr.getListOfEntries();
            assertEquals(0, this.entries.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralScoreboard() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralScoreboard.json");
        try {
            this.sr = reader.read();
            this.entries = sr.getListOfEntries();
            assertEquals(2, this.entries.size());
            checkEntry(2.34F, 3.24D, 4.23D, this.entries.get(0));
            checkEntry(1F, 2D, 3D, this.entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}