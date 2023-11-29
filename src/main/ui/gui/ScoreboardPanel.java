package ui.gui;

import model.Entry;
import model.Event;
import model.EventLog;
import model.Scoreboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// A class that creates the Scoreboard Page of the game, showing all the previous attempts of the user

public class ScoreboardPanel extends JFrame {

    private JPanel panel;

    private Scoreboard scoreboard;
    private List<Entry> entryList;
    private static DecimalFormat decfor;

    private static final String JSON_STORE = "./data/scoreboard.json";

    // Constructs a scoreboard panel
    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the scoreboard page of the game
    public ScoreboardPanel(Scoreboard updatedScoreboard) {
        super("GorillaTypeApplication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        this.entryList = new ArrayList<>();
        this.scoreboard = updatedScoreboard;
        this.entryList = this.scoreboard.getListOfEntries();

        initialize();
        add(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event : eventLog) {
                    System.out.println(event);
                }
                e.getWindow().dispose();
            }
        });
    }

    // EFFECTS: initializing the panel and its visibility
    private void initialize() {
        this.panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(WIDTH * 2, HEIGHT * 2));
        panel.setBackground(Color.decode("#313437"));

        setVisible(true);

        printMultiple();
        panel.repaint();
    }

    // MODIFIES: this
    // EFFECTS: prints the scoreboard
    private void printMultiple() {
        JLabel title = new JLabel("SCOREBOARD");
        title.setFont(new Font("Monospaced", Font.BOLD, 30));
        title.setForeground(Color.decode("#E2B712"));
        title.setBounds(200, 50, 500, 80);

        panel.add(title);

        this.decfor = new DecimalFormat("0");
        for (int i = 0; i < this.entryList.size(); i++) {
            print(i);
        }
        panel.repaint();
    }

    // EFFECTS: goes through each entry of the scoreboard and prints them one by one
    private void print(int i) {
        int y = 200 * i + 20 * i;

        JLabel attempt = new JLabel("Attempt " + (i + 1));
        attempt.setFont(new Font("Monospaced", Font.BOLD, 20));
        attempt.setForeground(Color.decode("#E2B712"));
        attempt.setBounds(200, 130 + y, 500, 80);

        Entry rslt = this.entryList.get(i);

        JLabel time = new JLabel("Best time: " + rslt.getTime() + "s");
        time.setFont(new Font("Monospaced", Font.PLAIN, 20));
        time.setForeground(Color.decode("#E2B712"));
        time.setBounds(200, 180 + y, 500, 80);

        JLabel accuracy = new JLabel("Accuracy Percentage: " + rslt.getWPM() + "%");
        accuracy.setFont(new Font("Monospaced", Font.PLAIN, 20));
        accuracy.setForeground(Color.decode("#E2B712"));
        accuracy.setBounds(200, 230 + y, 500, 80);

        JLabel words = new JLabel(
                "Words Per Minute (WPM): " + decfor.format(rslt.getAccuracy()) + " words /minute");
        words.setFont(new Font("Monospaced", Font.PLAIN, 20));
        words.setForeground(Color.decode("#E2B712"));
        words.setBounds(200, 280 + y, 500, 80);

        panelAdd(attempt, time, accuracy, words);
    }

    // EFFECTS: adds attempt, time, accuracy, and words JLabels to the panel (helper method)
    private void panelAdd(JLabel attempt, JLabel time, JLabel accuracy, JLabel words) {
        panel.add(attempt);
        panel.add(time);
        panel.add(accuracy);
        panel.add(words);
    }
}
