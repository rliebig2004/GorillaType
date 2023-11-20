package ui.gui;

import model.Entry;
import model.Scoreboard;
import model.Tracker;
import model.WordGenerator;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JFrame implements ActionListener {

    private int inputNumber;
    private String user;
    private WordGenerator listOfWords;
    private Tracker result;
    private Entry entry;
    private Scoreboard scoreboard;
    private Scoreboard updateScoreboard;
    private List<Entry> entryList;
    private static DecimalFormat decfor;
    private List<String> wordsGenerated;
    private List<String> answerList;

    private static final String JSON_STORE = "./data/scoreboard.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel panel;
    private JTextArea wordsInputted;
    private JTextField userText;
    private JButton nextButton;
    private JButton backToMenuButton;
    private JButton finishButton;
    private JButton saveButton;
    private JLabel invalid;
    private JLabel results;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel() {
        super("GorillaTypeApplication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        this.panel = new JPanel();
        panel.setLayout(null);
        this.listOfWords = new WordGenerator();
        panel.setPreferredSize(new Dimension(WIDTH * 2, HEIGHT * 2));
        panel.setBackground(Color.decode("#313437"));

        this.inputNumber = 0;

        makeButtons();

        this.invalid = new JLabel("Woops! Please Enter A Number!");
        invalid.setFont(new Font("Roboto Mono", Font.BOLD, 20));
        invalid.setForeground(Color.decode("#E2B712"));
        invalid.setBounds(200, 50, 500, 200);

        visibleOn();
        panel.repaint();
    }

    private void makeButtons() {
        this.nextButton = new JButton("PLAY");
        nextButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        nextButton.setForeground(Color.GRAY);

        this.backToMenuButton = new JButton("MAIN MENU");
        backToMenuButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        backToMenuButton.setForeground(Color.GRAY);

        this.finishButton = new JButton("FINISH");
        finishButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        finishButton.setForeground(Color.GRAY);

        this.saveButton = new JButton("SAVE");
        saveButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        saveButton.setForeground(Color.GRAY);
    }

    public void visibleOn() {
        add(panel);
        setVisible(true);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);

        this.scoreboard = new Scoreboard();
        this.updateScoreboard = new Scoreboard();
        this.entryList = new ArrayList<>();
        this.entryList = this.scoreboard.getListOfEntries();
        this.result = new Tracker();
        userIntegerInput();
    }

    // Draws the game
    // modifies: g
    // effects:  takes user input and runs game
    private void runGame() {
        drawOutput(this.inputNumber);
        drawUserInput();
    }

    // MODIFIES:
    // EFFECTS: takes in the user's input of random words generated
    private void userIntegerInput() {
        JLabel label = new JLabel("Enter number of words you want to type: ");
        label.setFont(new Font("Monospaced", Font.PLAIN, 20));
        label.setForeground(Color.decode("#E2B712"));
        label.setBounds(230, 25, 500, 40);

        panel.add(label);

        userText = new JTextField();
        userText.setBounds(710, 30, 50, 30);
        userText.setFont(new Font("Times New Roman", Font.BOLD, 25));
        userText.setForeground(Color.decode("#E2B712"));
        userText.setBackground(Color.decode("#313437"));
        userText.setBorder(null);
        panel.add(userText);
        userText.requestFocus(true);


        nextButton.addActionListener(this);
        nextButton.setBounds(200, 80, 600, 50);
        panel.add(nextButton);
    }

    // modifies: g
    // effects:  Draws the randomized words output onto the panel
    private void drawOutput(int n) {
        // panel.add(new JLabel("Start Typing!"));
        this.wordsGenerated = this.listOfWords.getRandomWordList(n);
        String output = "";


        for (String str : this.wordsGenerated) {
            output += str + " ";
        }

        JLabel outputList = new JLabel("<html>" + output + "</html>");
        outputList.setFont(new Font("Roboto Mono", Font.BOLD, 20));
        outputList.setForeground(Color.decode("#646669"));
        outputList.setBounds(200, 60, 600, 300);

        panel.add(outputList);
        panel.repaint();
    }

    // Draw the invaders
    // modifies: g
    // effects:  draws the invaders onto g
    private void drawUserInput() {
        this.result.startTimer();
        this.wordsInputted = new JTextArea();
        wordsInputted.setLineWrap(true);


        wordsInputted.setBounds(200, 270, 600, 100);
        wordsInputted.setFont(new Font("Roboto Mono", Font.BOLD, 20));
        wordsInputted.setForeground(Color.decode("#E2B712"));
        wordsInputted.setBackground(Color.decode("#313437"));
        panel.add(wordsInputted);
        wordsInputted.requestFocus(true);


        finishButton.addActionListener(this);
        finishButton.setBounds(200, 400, 600, 50);
        panel.add(finishButton);

        panel.repaint();
    }

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

    public void scoreboardResult() {
        results = new JLabel("Breakdown!");
        results.setFont(new Font("Monospaced", Font.BOLD, 20));
        results.setForeground(Color.decode("#E2B712"));
        results.setBounds(450, 470, 500, 80);
        panel.add(results);

        printOnce();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            try {
                runNextButton();

            } catch (NumberFormatException ex) {
                panel.add(invalid);
                panel.repaint();
            }

        } else if (e.getSource() == backToMenuButton) {
            dispose();
            new MenuPanel();

        } else if (e.getSource() == finishButton) {
            this.result.endTimer();
            finishButton.setEnabled(false);
            split();
            gameOver();

        } else if (e.getSource() == saveButton) {
            saveButton.setEnabled(false);
            saveToFile();

        }
    }

    private void runNextButton() {
        this.user = userText.getText();
        this.inputNumber = Integer.parseInt(user);
        panel.remove(invalid);
        userText.setEditable(false);
        nextButton.setEnabled(false);
        runGame();
    }

    private void split() {
        String output = this.wordsInputted.getText();
        String[] split = output.split("\\s+");
        this.answerList = Arrays.asList(split);
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver() {
        backToMenuButton.addActionListener(this);
        backToMenuButton.setBounds(200, 700, 600, 50);
        panel.add(backToMenuButton);

        newEntry(this.wordsGenerated, this.answerList);
        scoreboardResult();

        saveButton.addActionListener(this);
        saveButton.setBounds(200, 750, 600, 50);
        panel.add(saveButton);

        panel.repaint();
    }

    private void saveToFile() {
        updateScoreboard();
        this.updateScoreboard.addEntries(this.scoreboard.getListOfEntries().get(0));

        try {
            jsonWriter.open();
            jsonWriter.write(this.updateScoreboard);
            jsonWriter.close();

            JLabel loaded = new JLabel("Attempt Saved!");
            loaded.setFont(new Font("Monospaced", Font.BOLD, 18));
            loaded.setForeground(Color.decode("#E2B712"));
            loaded.setBounds(430, 470, 500, 80);

            panel.remove(results);
            panel.add(loaded);

        } catch (FileNotFoundException e) {
            JLabel notLoaded = new JLabel("Attempt Not Saved!");
            notLoaded.setFont(new Font("Monospaced", Font.PLAIN, 20));
            notLoaded.setForeground(Color.decode("#E2B712"));
            notLoaded.setBounds(400, 550, 500, 80);

            panel.add(notLoaded);
        }
        panel.repaint();
    }

    private void updateScoreboard() {
        try {
            this.updateScoreboard = jsonReader.read();
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    private void printOnce() {
        this.decfor = new DecimalFormat("0");
        Entry rslt = this.entryList.get(this.entryList.size() - 1);

        JLabel time = new JLabel("Best time: " + rslt.getTime() + "s");
        time.setFont(new Font("Monospaced", Font.PLAIN, 20));
        time.setForeground(Color.decode("#E2B712"));
        time.setBounds(250, 530, 500, 80);
        panel.add(time);

        JLabel accuracy = new JLabel("Accuracy Percentage: " + rslt.getWPM() + "%");
        accuracy.setFont(new Font("Monospaced", Font.PLAIN, 20));
        accuracy.setForeground(Color.decode("#E2B712"));
        accuracy.setBounds(250, 550, 500, 80);
        panel.add(accuracy);

        JLabel words = new JLabel("Words Per Minute (WPM): " + decfor.format(rslt.getAccuracy())
                + " words /minute");
        words.setFont(new Font("Monospaced", Font.PLAIN, 20));
        words.setForeground(Color.decode("#E2B712"));
        words.setBounds(250, 570, 500, 80);
        panel.add(words);
    }


}
