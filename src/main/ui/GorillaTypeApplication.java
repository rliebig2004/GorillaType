package ui;

import model.WordGenerator;
import model.Tracker;
import java.util.List;
import java.util.Scanner;

// runs the typing application
public class GorillaTypeApplication {
    private Scanner inputCommand;
    private Scanner inputNumber;
    private Tracker result;
    private int totalWords;
    private WordGenerator listOfWords;

    public GorillaTypeApplication() {
        runApp();
    }

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
        inputCommand = new Scanner(System.in);
        inputCommand.useDelimiter("\n");
        inputNumber = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            generateWords();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to GorillaType!");
        System.out.println("\tPress q to exit the game :(");
        System.out.println("\tPress c to continue :)");
    }

    // REQUIRES:
    // EFFECTS: calls the word generator class and produces a randomized phrase with total words of n
    private List<String> generateWords() {
        System.out.println("How many words would you like to test yourself today?");
        System.out.println("Enter: ");
        int i = inputNumber.nextInt();
        return this.listOfWords.getRandomWordList(i);
    }
}

// make a method that takes in an integer to produce n number of randomized phrases to type.
// make a method that takes in a command (enter) / (escape) for starting/ending the program
// make a method that takes

// 1. "Welcome to GorillaType!"
//    "Press escape to exit the game :("
//    "Press return to continue :)"
// 2. If esc = "Goodbye! See you soon!"
//    If return = "How many words would you like to test yourself today?"
// 3. "Happy Typing!"
//     (random words ...)
//
//     (user input ...)
// 4. Write a comparator method that compares the user input and the words generated (list with list)
// 5. When return = "You have typed -/n words! Here are your details!"
//    Best Time: - s            WPS: - words/s           Accuracy: - %
