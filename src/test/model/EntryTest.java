package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    private Entry entry1;
    private Entry entry2;
    private Entry entry3;

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
        assertEquals(2, entry.getWPS());
        assertEquals(3, entry.getAccuracy());
    }

    @Test
    void getTimeTest() {
        assertEquals(0, entry1.getTime());
        assertEquals(20, entry2.getTime());
        assertEquals(100.5f, entry3.getTime());
    }

    @Test
    void getWPSTest() {
        assertEquals(0, entry1.getWPS());
        assertEquals(30, entry2.getWPS());
        assertEquals(120.5, entry3.getWPS());
    }

    @Test
    void getAccuracyTest() {
        assertEquals(0, entry1.getAccuracy());
        assertEquals(40, entry2.getAccuracy());
        assertEquals(200.3, entry3.getAccuracy());
    }
}
