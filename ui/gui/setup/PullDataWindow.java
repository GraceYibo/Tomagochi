package ui.gui.setup;

import persistence.JsonReader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.AllPets;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents a window prompting the user to load saved game data.
 */
public class PullDataWindow {

    private JFrame frame;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "src/main/data/AllPetsForGUI.json";

    /**
     * Requires: `pets` is non-null.
     * Modifies: May modify the `AllPets` object if the user chooses to load saved data.
     * Effects: Creates a new PullDataWindow to let the user decide whether to load saved game data.
     *
     * @param pets the AllPets object managing all pets
     */
    public PullDataWindow(AllPets pets) {
        jsonReader = new JsonReader(JSON_STORE);
        initialize(pets);
    }

    /**
     * Initializes the window components and layout.
     *
     * @param pets the AllPets object managing all pets
     */
    private void initialize(AllPets pets) {
        setupFrame();

        JPanel questionPanel = new JPanel();
        JPanel answerPanel = createAnswerPanel();

        addQuestionToPanel(questionPanel);
        addYesButton(pets, answerPanel);
        addNoButton(pets, answerPanel);

        frame.add(answerPanel, BorderLayout.SOUTH);
        frame.add(questionPanel, BorderLayout.NORTH);
    }

    /**
     * Configures the main frame settings.
     */
    private void setupFrame() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(10, 5));
        frame.setTitle("Tomagochi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    /**
     * Creates a panel for the answer buttons.
     *
     * @return the JPanel for answers
     */
    private JPanel createAnswerPanel() {
        JPanel answerPanel = new JPanel();
        answerPanel.setPreferredSize(new Dimension(0, 250));
        return answerPanel;
    }

    /**
     * Adds the "Yes" button to load saved data.
     *
     * @param pets        the AllPets object managing all pets
     * @param answerPanel the panel to add the button to
     */
    private void addYesButton(AllPets pets, JPanel answerPanel) {
        JButton yes = new JButton("Yes");
        answerPanel.add(yes);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame timedWindow = createTimedWindow("Game loaded.");
                loadAllPets(pets);

                Timer timer = createTimer(() -> {
                    frame.dispose();
                    timedWindow.dispose();
                    new BeginningWindow(pets);
                });
                timer.start();
            }
        });
    }

    /**
     * Adds the "No" button to start a new game.
     *
     * @param pets        the AllPets object managing all pets
     * @param answerPanel the panel to add the button to
     */
    private void addNoButton(AllPets pets, JPanel answerPanel) {
        JButton no = new JButton("No");
        answerPanel.add(no);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame timedWindow = createTimedWindow("No previous game loaded.");

                Timer timer = createTimer(() -> {
                    frame.dispose();
                    timedWindow.dispose();
                    new BeginningWindow(pets);
                });
                timer.start();
            }
        });
    }

    /**
     * Creates a timer that executes a task after a delay.
     *
     * @param task the task to execute when the timer ends
     * @return the configured Timer
     */
    private Timer createTimer(Runnable task) {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                task.run();
            }
        });
        timer.setRepeats(false);
        return timer;
    }

    /**
     * Creates a temporary window with a given message.
     *
     * @param message the message to display
     * @return the created JFrame
     */
    private JFrame createTimedWindow(String message) {
        JFrame timedWindow = new JFrame("Load Game Window");
        timedWindow.setSize(200, 50);
        timedWindow.setLocationRelativeTo(frame);
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        timedWindow.add(label);
        timedWindow.setVisible(true);
        return timedWindow;
    }

    /**
     * Adds a question label to the panel.
     *
     * @param questionPanel the panel to add the question to
     */
    private void addQuestionToPanel(JPanel questionPanel) {
        JLabel question = new JLabel("Do you want to load your saved data?");
        question.setFont(new Font("Arial", Font.PLAIN, 18));
        question.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        questionPanel.add(question);
    }

    /**
     * Loads saved pet data from a JSON file.
     *
     * @param pets the AllPets object to populate with saved data
     */
    private void loadAllPets(AllPets pets) {
        try {
            AllPets loadedPets = jsonReader.read();
            pets.setName(loadedPets.getName());
            pets.setPets(loadedPets.getPets());
            System.out.println("Loaded " + pets.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
