package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Takes in the entries from the Entry class and puts it on the scoreboard of the game.
public class Scoreboard implements Writable {
    private List<Entry> listOfEntries;

    // Constructs a scoreboard with a list of entries of the user's attempts
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entries", entriesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : this.listOfEntries) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
