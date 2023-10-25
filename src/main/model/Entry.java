package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Produces the results of the round as entries on the scoreboard.
public class Entry implements Writable {
    private float bestTime;
    private double wpm;
    private double accuracy;

    // EFFECTS: Constructs a new entry with time, wpm, and accuracy percentages of the user's attempt
    public Entry(float time, double wpm, double accpers) {
        this.bestTime = time;
        this.wpm = wpm;
        this.accuracy = accpers;
    }

    // EFFECTS: Returns the value of the stopwatch.
    public float getTime() {
        return this.bestTime;
    }

    // EFFECTS: Returns the value of the WPS.
    public double getWPM() {
        return this.wpm;
    }

    // EFFECTS: Returns the value of the accuracy percentage.
    public double getAccuracy() {
        return this.accuracy;
    }

    // EFFECTS: Creates an Entry as a Json Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("time", this.bestTime);
        json.put("wpm", this.wpm);
        json.put("accuracy", this.accuracy);
        return json;
    }
}