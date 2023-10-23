package ui;

import model.Scoreboard;
import model.WordGenerator;
import model.Tracker;

import model.Entry;

import java.util.*;

// Runs the typing application
public class GorillaTypeApplication {
    private Scanner inputCommand;
    private Scanner inputNumber;
    private Tracker result;
    private WordGenerator listOfWords;
    private Entry entries;
    private Scoreboard scoreboard;

    public GorillaTypeApplication() {
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

            System.out.println("How many words would you like to test yourself today?");
            System.out.println("Enter: ");
            List<String> list = new ArrayList<>();
            int i = 0;
            try {
                i = inputNumber.nextInt();
                generateWords(i);
            } catch (InputMismatchException exception) {
                System.out.println("Selection not valid");
                return;
            }

            List<String> returnList = generateWords(i);
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
            float time = this.result.getBestTime();
            double accuracy = this.result.getAccuracyPercentage(returnList, returnInput);
            double wps = this.result.getWordsPerSecond(returnInput.size());

            this.entries = new Entry(time, accuracy, wps);
            this.scoreboard.addEntries(this.entries);

        } else if (command.equals("s")) {
            scoreboardResult();

        } else {
            System.out.println("Selection not valid");
        }
    }


    // EFFECTS: Produces the entries on the scoreboard
    public void scoreboardResult() {
        List<Entry> entryList = this.scoreboard.getListOfEntries();
        for (Entry rslt: entryList) {
            System.out.println("Best time: " + rslt.getTime() + "s");
            System.out.println("Accuracy Percentage: " + rslt.getWPS() + "%");
            System.out.println("Word Per Second (WPS): " + rslt.getAccuracy() + "/s");
        }
    }

    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nWelcome to GorillaType!");
        System.out.println("\tPress q to exit the game :(");
        System.out.println("\tPress c to continue :)");
        System.out.println("\tPress s to see the scoreboard!");
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

}
