package ui.gui;

import model.Entry;
import model.Event;
import model.EventLog;
import model.Scoreboard;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// A class that creates the Menu Page of the game

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

    // Constructs a menu panel
    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the menu page of the game
    public MenuPanel() {
        super("GorillaTypeApplication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        createSplashScreen();

        menuPanel = new JPanel();
        menuPanel.setLayout(null);
        menuPanel.setPreferredSize(new Dimension(1000, 1000));
        menuPanel.setBackground(Color.decode("#313437"));

        makeButtons();

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

        this.scoreboard = new Scoreboard();
        this.entryList = new ArrayList<>();
        jsonReader = new JsonReader(JSON_STORE);
        runDisplayMenu();
    }

    private void createSplashScreen() {
        JWindow window = new JWindow();
        window.setSize(1000, 1000);
        centerOnScreen(window);

        ImageIcon logo = new ImageIcon("data/gorilla.png");
        window.getContentPane().add(new JLabel(scaleImage(logo, 400, 400 - 100)));
        window.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        window.dispose();
    }

    // MODIFIES: this
    // EFFECTS: center the application on the screen
    private void centerOnScreen(Window container) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        container.setLocationRelativeTo(null);
        container.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // EFFECTS: return a scale version of given imageIcon
    private ImageIcon scaleImage(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // EFFECTS: initializing JButtons
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

    // EFFECTS: creates the title and buttons graphics
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

    // EFFECTS: gives functionality to all of the exisiting buttons
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

    // MODIFIES: this
    // EFFECTS: loads all the attempts from the JSON file
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
