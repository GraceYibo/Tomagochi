package ui.gui.mainwindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.AllPets;
import model.Pet;
import persistence.JsonWriter;
import ui.ShowLogWindow;

// Effects: creates a window
public class QuitWindow {

    private static final String JSON_STORE = "src/main/data/AllPetsForGUI.json";
    private JsonWriter jsonWriter;

    // Effects: creates a window
    public QuitWindow(AllPets pets, Pet pet) {
        jsonWriter = new JsonWriter(JSON_STORE);
        initilize(pets, pet);
    }

    // Effects: creates a window
    public void initilize(AllPets pets, Pet pet) {
        JFrame quitWindow = getQuitWindow();

        JPanel panel = getPanel();

        getPrompt(panel);

        JPanel buttonPanel = getButtonPanel();

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        extracted(pets, quitWindow, yesButton);

        extracted2(quitWindow, noButton);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        quitWindow.add(panel);
        quitWindow.setVisible(true);

    }

    private void extracted2(JFrame quitWindow, JButton noButton) {
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Showing event log and exiting game...");
                quitWindow.dispose();
                new ShowLogWindow(); // Show event log window
            }
        });
    }

    // Effects: creates a window
    private void extracted(AllPets pets, JFrame quitWindow, JButton yesButton) {
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving data...");
                saveAllPets(pets);

                JFrame confirmationWindow = getConfirmationWindow();

                JPanel confirmationPanel = new JPanel();
                confirmationPanel.setBackground(Color.WHITE);

                getConfirmationMessage(confirmationPanel);

                extracted(confirmationWindow, confirmationPanel);

                Timer timer = getTimer(confirmationWindow);

                timer.setRepeats(false); // Ensure the timer runs only once
                timer.start();

                quitWindow.dispose(); // Close the main quit window
            }

            private void extracted(JFrame confirmationWindow, JPanel confirmationPanel) {
                confirmationWindow.add(confirmationPanel);
                confirmationWindow.setVisible(true);
            }
        });
    }

    // Effects: creates a window
    private Timer getTimer(JFrame confirmationWindow) {
        // Set a timer to close the confirmation window and quit the game after 2
        // seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationWindow.dispose(); // Close confirmation window
                System.exit(0); // Exit the application
            }
        });
        return timer;
    }

    // Effects: creates a window
    private void getConfirmationMessage(JPanel confirmationPanel) {
        JLabel confirmationMessage = new JLabel("Game Saved! Exiting...", SwingConstants.CENTER);
        confirmationMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmationPanel.add(confirmationMessage);
    }

    // Effects: creates a window
    private JFrame getConfirmationWindow() {
        // Create a small confirmation window to show "Game Saved"
        JFrame confirmationWindow = new JFrame("Game Saved");
        confirmationWindow.setSize(300, 150);
        confirmationWindow.setLocationRelativeTo(null);
        return confirmationWindow;
    }

    // Effects: creates a window
    private JPanel getButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        return buttonPanel;
    }

    // Effects: creates a window
    private void getPrompt(JPanel panel) {
        JLabel prompt = new JLabel("Do you want to save your game before quitting?", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(prompt, BorderLayout.CENTER);
    }

    // Effects: creates a window
    private JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private JFrame getQuitWindow() {
        JFrame quitWindow = new JFrame("Quit Window");
        quitWindow.setSize(700, 450);
        quitWindow.setLocationRelativeTo(null);
        quitWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return quitWindow;
    }


    // Referenced from the file JsonSerializationDemo with the link
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled
    // it according to my project
    // EFFECTS: saves the workroom to file
    private void saveAllPets(AllPets pets) {
        try {
            jsonWriter.open();
            jsonWriter.write(pets);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

}
