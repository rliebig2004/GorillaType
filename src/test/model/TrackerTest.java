package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

// tests the Tracker class
public class TrackerTest {
    private Tracker tracker;
    private float start;
    private float end;
    private float timer;
    private int numWords1;
    private int numWords2;

    // EFFECTS: initializes a new tracker, timer, and 2 example inputs
    @BeforeEach
    void runBefore() {
        this.tracker = new Tracker();
        this.start = 0;
        this.end = 0;
        this.timer = 0;
        this.numWords1 = 0;
        this.numWords2 = 2;
    }

    // EFFECTS: Tests the getBestTime method.
    @Test
    void getBestTimeTest() {
        this.start = tracker.startTimer();
        this.end = tracker.endTimer();
        assertEquals(((this.end - this.start) / 1000), this.tracker.getBestTime());
    }


    // EFFECTS: Tests the getWordsPerSecond method.
    @Test
    void getWordsPerMinuteTestNoWords() {
        this.start = tracker.startTimer();
        this.end = tracker.endTimer();
        this.timer = this.tracker.getBestTime();
        assertEquals((this.numWords1 / ((this.end - this.start) / 1000)),
                this.tracker.getWordsPerMinute(this.numWords1));
    }

    @Test
    void getWordsPerMinuteTest() {
        this.start = tracker.startTimer();
        this.end = tracker.endTimer();
        this.timer = this.tracker.getBestTime();
        assertEquals((this.numWords2 / ((this.end - this.start) / 1000)),
                this.tracker.getWordsPerMinute(this.numWords2));
    }


    @Test
    void getAccuracyPercentageTestGeneratedListLarger() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("a");
        generatedList.add("b");
        generatedList.add("c");
        inputList.add("a");
        assertEquals((1d / 3d)*100d, tracker.getAccuracyPercentage(generatedList, inputList));
    }

    @Test
    void getAccuracyPercentageTestGeneratedListLargerInaccurate() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("a");
        generatedList.add("b");
        generatedList.add("c");
        inputList.add("b");
        assertEquals((0d / 3d)*100, tracker.getAccuracyPercentage(generatedList, inputList));
    }

    @Test
    void getAccuracyPercentageTestInputListLarger() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("a");
        inputList.add("a");
        inputList.add("b");
        inputList.add("c");
        assertEquals((1d / 1d)*100, tracker.getAccuracyPercentage(generatedList, inputList));
    }

    @Test
    void getAccuracyPercentageTestInputListLargerInaccurate() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("d");
        inputList.add("a");
        inputList.add("b");
        inputList.add("c");
        assertEquals((0d / 3d)*100, tracker.getAccuracyPercentage(generatedList, inputList));
    }

    @Test
    void getAccuracyPercentageBothListsEqualSize() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("a");
        generatedList.add("b");
        generatedList.add("c");
        inputList.add("a");
        inputList.add("b");
        inputList.add("c");
        assertEquals((3d / 3d)*100, tracker.getAccuracyPercentage(generatedList, inputList));
    }

    @Test
    void getAccuracyPercentageBothListsEqualSizeInaccurateDifferentLetters() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("a");
        generatedList.add("b");
        generatedList.add("c");
        inputList.add("d");
        inputList.add("e");
        inputList.add("f");
        assertEquals((0d / 3d)*100, tracker.getAccuracyPercentage(generatedList, inputList));
    }

    @Test
    void getAccuracyPercentageBothListsEqualSizeInaccurateDifferentOrder() {
        List<String> generatedList = new ArrayList<String>();
        List<String> inputList = new ArrayList<String>();
        generatedList.add("a");
        generatedList.add("b");
        generatedList.add("c");
        inputList.add("c");
        inputList.add("b");
        inputList.add("a");
        assertEquals((1d / 3d)*100, tracker.getAccuracyPercentage(generatedList, inputList));
    }

}

