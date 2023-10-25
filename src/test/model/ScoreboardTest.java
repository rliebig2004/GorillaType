package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests the Scoreboard class
public class ScoreboardTest {
    private Scoreboard sc;
    private Entry e1;
    private Entry e2;
    private Entry e3;

    // EFFECTS: initializes a new scoreboard and 3 new entries
    @BeforeEach
    void runBefore() {
        this.sc = new Scoreboard();
        this.e1 = new Entry(0,0,0);
        this.e2 = new Entry(20,30,40);
        this.e3 = new Entry(100.5f,120.5,200.3);
    }

    @Test
    void ConstructorTest() {
        assertEquals(0, this.sc.getListOfEntries().size());
    }

    @Test
    void addEntriesTestOneEntries() {
        // adds 1 entry
        assertEquals(e1, sc.addEntries(e1).get(0));
    }

    @Test
    void addEntriesTestTwoEntries() {
        // adds 2+ entries
        sc.addEntries(e2);
        sc.addEntries(e3);
        assertEquals(e2, sc.getListOfEntries().get(0));
        assertEquals(e3, sc.getListOfEntries().get(1));
    }

}
