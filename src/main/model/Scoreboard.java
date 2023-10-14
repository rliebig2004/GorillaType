package model;

import java.util.*;

// Takes in the entries from the Entry class and puts it on the scoreboard of the game.
public class Scoreboard {
    private List<Entry> listOfEntries;

    public Scoreboard() {
        this.listOfEntries = new ArrayList<Entry>();
    }

    // EFFECTS: Adds new entries.
    public List<Entry> addEntries(Entry newEntry) {
        this.listOfEntries.add(newEntry);
        return this.listOfEntries;
    }

    // EFFECTS: Gets list of entries.
    public List<Entry> getListOfEntries() {
        return this.listOfEntries;
    }
}
