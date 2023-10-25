package model;

import java.util.*;


// Calculate the words-per-second value (WPS), accuracy percentage, and best time of the user.
public class Tracker {
    private float timeElapsed;
    private long start;
    private long end;
    private double wordsPerMinute;
    private double accuracyPercentage;

    // EFFECTS: initializes the time, WPM, and accuracy percentage
    public Tracker() {
        this.timeElapsed = 0;
        this.wordsPerMinute = 0;
        this.accuracyPercentage = 0;
    }

    // REQUIRES: seconds > 0
    // MODIFIES: this
    // EFFECTS: returns the best time of the user.
    public float getBestTime() {
        this.timeElapsed = (this.end - this.start) / 1000f;
        return this.timeElapsed;
    }

    // REQUIRES: number of seconds > 0
    // MODIFIES: this
    // EFFECTS: returns the typing speed of the user.
    public double getWordsPerMinute(int words) {
        this.timeElapsed = this.timeElapsed / 60;
        this.wordsPerMinute = words / this.timeElapsed;
        return this.wordsPerMinute;
    }

    // REQUIRES: number of generated words > 0
    // MODIFIES: this
    // EFFECTS: returns the typing accuracy of the user.
    public double getAccuracyPercentage(List<String> generatedList, List<String> inputList) {

        int result = 0;

        for (int i = 0; i < generatedList.size() && i < inputList.size(); i++) {
            String first = generatedList.get(i);
            String second = inputList.get(i);
            if (compare(first, second)) {
                result++;
            }
        }

        this.accuracyPercentage = ((double) result / generatedList.size()) * 100;
        return this.accuracyPercentage;
    }


    // EFFECTS: compare the first word with the second word
    public boolean compare(String word1, String word2) {
        return word1.equals(word2);
    }

    // EFFECTS: starts the timer
    public float startTimer() {
        this.start = System.currentTimeMillis();
        return this.start;
    }

    // EFFECTS: ends the timer
    public float endTimer() {
        this.end = System.currentTimeMillis();
        return this.end;
    }
}
