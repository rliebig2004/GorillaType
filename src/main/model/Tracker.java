package model;

import java.util.*;

// Calculate the words-per-second value (WPS), accuracy percentage, and best time of the user.
public class Tracker {
    private float timeElapsed;
    private long start;
    private long end;
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
    public float getBestTime() {
        this.timeElapsed = (this.end - this.start) / 1000f;
        return this.timeElapsed;
    }

    // REQUIRES: number of seconds > 0
    // MODIFIES: this
    // EFFECTS: returns the typing speed of the user.
    public double getWordsPerSecond(int words) {
        this.wordsPerSecond = words / this.timeElapsed;
        return this.wordsPerSecond;
    }

    // REQUIRES: number of words > 0
    // MODIFIES: this
    // EFFECTS: returns the typing accuracy of the user.
    public double getAccuracyPercentage(List<String> generatedList, List<String> inputList) {

        int result = 0;

        if (generatedList.size() > 0 || inputList.size() > 0) {
            for (int i = 0; i < generatedList.size() && i < inputList.size(); i++) {
                String first = generatedList.get(i);
                String second = inputList.get(i);
                if (compare(first, second)) {
                    result++;
                }
            }
        }

        this.accuracyPercentage = ((double) result / generatedList.size()) * 100;
        return this.accuracyPercentage;
    }

    // EFFECTS: compare the first word with the second word
    public boolean compare(String word1, String word2) {
        boolean result = false;
        if (word1.equals(word2)) {
            result = true;
        }
        return result;
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
