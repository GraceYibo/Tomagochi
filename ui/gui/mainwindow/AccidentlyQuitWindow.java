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

public class AccidentlyQuitWindow {

    private static final String JSON_STORE = "src/main/data/AllPetsForGUI.json";
    private JsonWriter jsonWriter;

    // Effects: creates a window
    public AccidentlyQuitWindow(AllPets pets, Pet pet, Timer mainTimer) {
        jsonWriter = new JsonWriter(JSON_STORE);
        initialize(pets, pet, mainTimer);
    }

    // Effects: creates a window
    public void initialize(AllPets pets, Pet pet, Timer mainTimer) {
        JFrame quitWindow = new JFrame("Quit Window");
        quitWindow.setSize(700, 450);
        quitWindow.setLocationRelativeTo(null);
        quitWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Don't close automatically on 'X' click

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Initial prompt asking if the user clicked quit by mistake
        JLabel prompt = new JLabel("Did you accidentally click 'Quit'?", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(prompt, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        extracted(mainTimer, quitWindow, yesButton);

        extracted2(pets, quitWindow, noButton);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        quitWindow.add(panel);
        quitWindow.setVisible(true);
    }

    // Effects: creates a window
    private void extracted2(AllPets pets, JFrame quitWindow, JButton noButton) {
        // If "No" clicked, show the quit confirmation and proceed
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuitConfirmation(pets, quitWindow);
            }
        });
    }

    // Effects: creates a window
    private void extracted(Timer mainTimer, JFrame quitWindow, JButton yesButton) {
        // If "Yes" clicked, return to the main window
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitWindow.dispose(); 
                mainTimer.start();
            }
        });
    }

    // Effects: creates a window
    private void showQuitConfirmation(AllPets pets, JFrame quitWindow) {
        // Prompt the user to save game before quitting
        JFrame confirmQuitWindow = new JFrame("Quit Window");
        confirmQuitWindow.setSize(700, 450);
        confirmQuitWindow.setLocationRelativeTo(null);
        confirmQuitWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        getPrompt(panel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        extracted3(pets, quitWindow, confirmQuitWindow, yesButton);

        extracted4(confirmQuitWindow, noButton);

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        confirmQuitWindow.add(panel);
        confirmQuitWindow.setVisible(true);

        quitWindow.dispose(); // Close the initial window when proceeding to quit confirmation
    }

    // Effects: creates a window
    private void getPrompt(JPanel panel) {
        JLabel prompt = new JLabel("Do you want to save your game before quitting?", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(prompt, BorderLayout.CENTER);
    }

    // Effects: creates a window
    private void extracted4(JFrame confirmQuitWindow, JButton noButton) {
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Closing game...");
                confirmQuitWindow.dispose();
                System.exit(0);
            }
        });
    }

    // Effects: creates a window
    private void extracted3(AllPets pets, JFrame quitWindow, JFrame confirmQuitWindow, JButton yesButton) {
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Saving data...");
                saveAllPets(pets);

                JFrame confirmationWindow = getConfirmationWindow();

                getConfirmationMessage(confirmationWindow);

                // Set a timer to close the confirmation window and quit the game after 2
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmationWindow.dispose(); // Close confirmation window
                        System.exit(0); // Exit the application
                    }
                });

                timer.setRepeats(false); // Ensure the timer runs only once
                timer.start();

                quitWindow.dispose(); // Close the main quit window
                confirmQuitWindow.dispose(); // Close the quit confirmation window
            }
        });
    }

    // Effects: creates a window
    private void getConfirmationMessage(JFrame confirmationWindow) {
        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setBackground(Color.WHITE);

        JLabel confirmationMessage = new JLabel("Game Saved! Exiting...", SwingConstants.CENTER);
        confirmationMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmationPanel.add(confirmationMessage);

        confirmationWindow.add(confirmationPanel);
        confirmationWindow.setVisible(true);
    }

    // Effects: creates a window
    private JFrame getConfirmationWindow() {
        // Create a small confirmation window to show "Game Saved"
        JFrame confirmationWindow = new JFrame("Game Saved");
        confirmationWindow.setSize(300, 150);
        confirmationWindow.setLocationRelativeTo(null);
        return confirmationWindow;
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