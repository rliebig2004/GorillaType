package ui.gui;

import model.Entry;
import model.Scoreboard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JFrame implements ActionListener {
    private static final int titleSize = 30;

    private JButton playButton;
    private JButton continueButton;
    private JButton quitButton;
    private JButton loadScoreboard;
    private JPanel menuPanel;

    private Scoreboard scoreboard;
    private List<Entry> entryList;

    private static final String JSON_STORE = "./data/scoreboard.json";
    private JsonReader jsonReader;


    public MenuPanel() {
        super("GorillaTypeApplication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(1000, 1000));
        menuPanel.setBackground(Color.decode("#313437"));

        makeButtons();

        this.scoreboard = new Scoreboard();
        this.entryList = new ArrayList<>();
        jsonReader = new JsonReader(JSON_STORE);
        runDisplayMenu();
    }

    public void makeButtons() {
        playButton = new JButton("Play");
        playButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        playButton.setForeground(Color.GRAY);

        continueButton = new JButton("Scoreboard");
        continueButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        continueButton.setForeground(Color.GRAY);

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        quitButton.setForeground(Color.GRAY);

        loadScoreboard = new JButton("Load Progress");
        loadScoreboard.setFont(new Font("Monospaced", Font.BOLD, 20));
        loadScoreboard.setForeground(Color.GRAY);
    }

    public void runDisplayMenu() {
        JLabel title = new JLabel("Welcome to Gorilla Type!");
        title.setFont(new Font("Monospaced", Font.PLAIN, titleSize));
        title.setForeground(Color.decode("#E2B712"));
        title.setBounds(280, 250, 600, 50);
        menuPanel.add(title);

        playButton.addActionListener(this);
        playButton.setBounds(200, 350, 600, 50);
        menuPanel.add(playButton);

        continueButton.addActionListener(this);
        continueButton.setBounds(200, 400, 600, 50);
        menuPanel.add(continueButton);

        quitButton.addActionListener(this);
        quitButton.setBounds(200, 450, 600, 50);
        menuPanel.add(quitButton);

        loadScoreboard.addActionListener(this);
        loadScoreboard.setBounds(200, 500, 600, 50);
        menuPanel.add(loadScoreboard);

        menuPanel.repaint();
        add(menuPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            dispose();
            new GamePanel();
        } else if (e.getSource() == continueButton) {
            new ScoreboardPanel(this.scoreboard);
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        } else if (e.getSource() == loadScoreboard) {
            loadScoreboard.setEnabled(false);
            load();
        }
    }

    public void load() {
        try {
            this.scoreboard = jsonReader.read();
            this.entryList = this.scoreboard.getListOfEntries();

            JLabel scbrd = new JLabel("Scoreboard Loaded!");
            scbrd.setFont(new Font("Monospaced", Font.PLAIN, 20));
            scbrd.setForeground(Color.decode("#E2B712"));
            scbrd.setBounds(400, 550, 500, 80);

            menuPanel.add(scbrd);

        } catch (IOException e) {
            JLabel fail = new JLabel("NO ATTEMPTS YET");
            fail.setFont(new Font("Monospaced", Font.PLAIN, 20));
            fail.setForeground(Color.decode("#E2B712"));
            fail.setBounds(250, 550, 500, 80);

            menuPanel.add(fail);
        }

        menuPanel.repaint();
    }

}
