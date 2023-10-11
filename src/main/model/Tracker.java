package model;

import ui.GorillaTypeApplication;
import java.util.Scanner;

// Calculate the words-per-second value (WPS), accuracy percentage, and best time of the user.
public class Tracker {
    private float timeElapsed;
    private double wordsPerSecond;
    private double accuracyPercentage;

    public Tracker() {
        this.timeElapsed = 0;
        this.wordsPerSecond = 0;
        this.accuracyPercentage = 0;
    }

    // REQUIRES: seconds > 0
    // MODIFIES: this
    // EFFECTS: returns the best time of the user.
    public float getBestTime(Scanner input) { // takes in a user input
        long start = System.currentTimeMillis();
        // user input (enter) or (escape) to stop timer. - taken from class GorillaTypeApp
        long end = System.currentTimeMillis();
        this.timeElapsed = (end - start) / 1000;
        return this.timeElapsed;
    }

    // REQUIRES: number of words > 0
    // MODIFIES: this
    // EFFECTS: returns the typing speed of the user.
    public double getWordsPerSecond(int words) {
        this.wordsPerSecond = words / this.timeElapsed;
        return this.wordsPerSecond; // don't forget to print "/s" in ui package.
    }

    // REQUIRES: number of words > 0
    // MODIFIES: this
    // EFFECTS: returns the typing accuracy of the user.
    public double getAccuracyPercentage(int words) {
//        this.accuracyPercentage = (words / GorillaTypeApplication.getTotalWords()) * 100;
        return this.accuracyPercentage; // don't forget to print "%" in ui package.
    }
}
