package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests the Entry class
public class EntryTest {
    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

    // EFFECTS: initializes 3 new entries
    @BeforeEach
    void runBefore() {
        this.entry1 = new Entry(0,0,0);
        this.entry2 = new Entry(20,30,40);
        this.entry3 = new Entry(100.5f,120.5,200.3);
    }

    @Test
    void ConstructorTest() {
        Entry entry = new Entry(1f,2,3);
        assertEquals(1, entry.getTime());
        assertEquals(2, entry.getWPM());
        assertEquals(3, entry.getAccuracy());
    }

    @Test
    void getTimeTest() {
        assertEquals(0, entry1.getTime());
        assertEquals(20, entry2.getTime());
        assertEquals(100.5f, entry3.getTime());
    }

    @Test
    void getWPMTest() {
        assertEquals(0, entry1.getWPM());
        assertEquals(30, entry2.getWPM());
        assertEquals(120.5, entry3.getWPM());
    }

    @Test
    void getAccuracyTest() {
        assertEquals(0, entry1.getAccuracy());
        assertEquals(40, entry2.getAccuracy());
        assertEquals(200.3, entry3.getAccuracy());
    }
}
