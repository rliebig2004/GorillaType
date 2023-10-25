package ui;

import model.Scoreboard;
import model.WordGenerator;
import model.Tracker;
import java.text.DecimalFormat;
import model.Entry;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;

// Runs the typing application
public class GorillaTypeApplication {
    private Scanner inputCommand;
    private Scanner inputNumber;
    private Tracker result;
    private WordGenerator listOfWords;

    private Entry entry;
    private Scoreboard scoreboard;
    private List<Entry> entryList;
    private static DecimalFormat decfor;

    private static final String JSON_STORE = "./data/scoreboard.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: Constructs a typing game application
    public GorillaTypeApplication() {
        this.scoreboard = new Scoreboard();
        this.entryList = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }


    // EFFECTS: Runs the GorillaTypeApp
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = inputCommand.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye! See you soon!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a new round
    private void init() {
        this.listOfWords = new WordGenerator();
        this.scoreboard = new Scoreboard();
        inputCommand = new Scanner(System.in);
        inputCommand.useDelimiter("\n");
        inputNumber = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            processGenerateCommand();

        } else if (command.equals("s")) {
            scoreboardResult(false);

        } else if (command.equals("l")) {
            loadsScoreboard();

        } else {
            System.out.println("Selection not valid");
        }
    }


    // MODIFIES: this
    // EFFECTS: Produces most recent entry after each round, produces all entries when viewing the scoreboard
    public void scoreboardResult(boolean printOnce) {
        this.decfor = new DecimalFormat("0");
        this.entryList = this.scoreboard.getListOfEntries();
        if (printOnce) {
            Entry rslt = this.entryList.get(this.entryList.size() - 1);
            System.out.println("Best time: " + rslt.getTime() + "s");
            System.out.println("Accuracy Percentage: " + rslt.getWPM() + "%");
            System.out.println("Word Per Minute (WPM): " + decfor.format(rslt.getAccuracy()) + " words /minute");
            System.out.println();
        } else {
            for (int i = 0; i < this.entryList.size(); i++) {
                System.out.println("Attempt " + (i + 1));
                Entry rslt = this.entryList.get(i);
                System.out.println("Best time: " + rslt.getTime() + "s");
                System.out.println("Accuracy Percentage: " + rslt.getWPM() + "%");
                System.out.println("Word Per Minute (WPM): " + decfor.format(rslt.getAccuracy()) + " words /minute");
                System.out.println();
            }
        }
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nWelcome to GorillaType!");
        System.out.println("\tq -> exit the game");
        System.out.println("\tc -> continue");
        System.out.println("\ts -> see your Scoreboard!");
        System.out.println("\tl -> load Scoreboard from file");
    }

    // EFFECTS: calls the word generator class and produces a randomized phrase with total words of n
    public List<String> generateWords(int command) {
        return this.listOfWords.getRandomWordList(command);
    }


    // EFFECTS: produces the sentence provided by the user's input
    public List<String> generateAnswers() {
        String input = inputCommand.next();
        String[] splited = input.split("\\s+");
        List<String> answerList = Arrays.asList(splited);
        return answerList;
    }

    // EFFECTS: a command that runs the integer request
    public void processGenerateCommand() {
        List<String> returnList = wordsToTestCommand();
        System.out.print("Start Typing!");
        System.out.println();

        this.result = new Tracker();

        for (String str : returnList) {
            System.out.print(str + " ");
        }

        System.out.println();

        this.result.startTimer();
        List<String> returnInput = generateAnswers();
        this.result.endTimer();

        System.out.println();

        newEntry(returnList, returnInput);
        scoreboardResult(true);
        

        askToSaveFile();
    }

    // EFFECTS: prints out a command for the user to input the number of words they want to test themselves
    public List<String> wordsToTestCommand() {
        System.out.println("How many words would you like to test yourself today?");
        System.out.println("Enter: ");
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            i = inputNumber.nextInt();
            list = generateWords(i);
        } catch (InputMismatchException exception) {
            System.out.println("Selection not valid");
        }
        return list;
    }

    // REQUIRES: returnList not empty
    // MODIFIES: this
    // EFFECTS: creates a new entry and adds it to the scoreboard
    public void newEntry(List<String> returnList, List<String> returnInput) {
        float time = 0;
        double accuracy = 0;
        double wpm = 0;

        time = this.result.getBestTime();
        accuracy = this.result.getAccuracyPercentage(returnList, returnInput);
        wpm = this.result.getWordsPerMinute(returnInput.size());

        this.entry = new Entry(time, accuracy, wpm);
        this.scoreboard.addEntries(this.entry);
    }


    // EFFECTS: saves the scoreboard to file
    private void savesScoreboard() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreboard);
            jsonWriter.close();
            System.out.println("Saved Scoreboard to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads scoreboard from file
    private void loadsScoreboard() {
        try {
            this.scoreboard = jsonReader.read();
            this.entryList = this.scoreboard.getListOfEntries();
            System.out.println("Loaded Scoreboard from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: gives the user a choice to save their current round's progress
    public void askToSaveFile() {
        System.out.println("\nWould you like to save your progress?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");
        String input = inputCommand.next();
        if (input.equals("y")) {
            savesScoreboard();
        } else if (input.equals("n")) {
            System.out.println("See you next time!");;
        } else {
            System.out.println("Selection not valid");
        }
    }

}
