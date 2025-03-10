package ui.gui.mainwindow.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.AllPets;
import model.Pet;
import persistence.JsonWriter;

public class SaveGameWindow {

    private static final String JSON_STORE = "src/main/data/AllPetsForGUI.json";
    private JsonWriter jsonWriter;

    // Effects: creates a window
    public SaveGameWindow(AllPets pets, Pet pet, Timer mainTimer) {
        this.jsonWriter = new JsonWriter(JSON_STORE);
        initialize(pets, pet, mainTimer);
    }

    // Effects: creates a window
    public void initialize(AllPets pets, Pet pet, Timer mainTimer) {
        saveAllPets(pets);

        JFrame confirmationWindow = getConfirmationWindow();

        extracted(mainTimer, confirmationWindow);

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setBackground(Color.WHITE);

        JLabel confirmationMessage = new JLabel("Game Saved!", SwingConstants.CENTER);
        confirmationMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        confirmationPanel.add(confirmationMessage);

        Timer timer = getTimer(mainTimer, confirmationWindow);

        timer.setRepeats(false); // Ensure the timer runs only once
        timer.start();

        confirmationWindow.add(confirmationPanel);
        confirmationWindow.setVisible(true);
    }

    // Effects: creates a window
    private Timer getTimer(Timer mainTimer, JFrame confirmationWindow) {
        // Set a timer to close the confirmation window and quit the game after 2
        // seconds
        Timer timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmationWindow.dispose(); // Close confirmation window
                mainTimer.start();
            }
        });
        return timer;
    }

    // Effects: creates a window
    private void extracted(Timer mainTimer, JFrame confirmationWindow) {
        confirmationWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the window
                confirmationWindow.dispose();

                // Start the timer
                mainTimer.start();
            }
        });
    }

    // Effects: creates a window
    private JFrame getConfirmationWindow() {
        // Create a small confirmation window to show "Game Saved"
        JFrame confirmationWindow = new JFrame("Game Saved");
        confirmationWindow.setSize(300, 150);
        confirmationWindow.setLocationRelativeTo(null);
        confirmationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
