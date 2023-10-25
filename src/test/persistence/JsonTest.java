package persistence;

import model.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(float time, double wpm, double accuracy, Entry entry) {
        assertEquals(time, entry.getTime());
        assertEquals(wpm, entry.getWPM());
        assertEquals(accuracy, entry.getAccuracy());
    }
}
