package model;


// Produces the results of the round as entries on the scoreboard.
public class Entry {
    private float bestTime;
    private double wps;
    private double accuracy;

    public Entry(float time, double wps, double accpers) {
        this.bestTime = time;
        this.wps = wps;
        this.accuracy = accpers;
    }

    // EFFECTS: Returns the value of the stopwatch.
    public float getTime() {
        return this.bestTime;
    }

    // EFFECTS: Returns the value of the WPS.
    public double getWPS() {
        return this.wps;
    }

    // EFFECTS: Returns the value of the accuracy percentage.
    public double getAccuracy() {
        return this.accuracy;
    }
}