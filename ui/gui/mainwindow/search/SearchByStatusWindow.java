package ui.gui.mainwindow.search;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.AllPets;
import model.Pet;

/**
 * Represents a search window for finding pets by status.
 */
public class SearchByStatusWindow {

    /**
     * Constructs the search window and processes pets based on their status.
     * 
     * @requires pets != null, pet != null, status is a valid, non-null string
     * @modifies this, mainTimer
     * @effects creates a window displaying pets with the specified status and pauses the main timer
     */
    public SearchByStatusWindow(AllPets pets, Pet pet, String status, Timer mainTimer) {
        processForStatusSearchPet(pets, status, mainTimer);
    }

    /**
     * Processes the pets by status and sets up the result display.
     * 
     * @requires pets != null, status != null, mainTimer != null
     * @modifies this, mainTimer
     * @effects creates a window displaying the search results for pets with the given status
     */
    private void processForStatusSearchPet(AllPets pets, String status, Timer mainTimer) {
        StringBuilder resultText = new StringBuilder("<html>");
        boolean found = false;

        // Search for pets with the given status
        for (Pet p : pets.getPets()) {
            String petStatus = String.valueOf(p.getStatus());
            if (petStatus.equals(status)) {
                found = true;
                resultText.append("<br>Name: ").append(p.getName())
                        .append("<br>Age: ").append(p.getAge())
                        .append("<br>Gender: ").append(p.getGender())
                        .append("<br>Status: ").append(p.getStatus())
                        .append("<br><br>");
            }
        }

        JFrame resultWindow = helper(status, mainTimer);
        resultWindow.setAlwaysOnTop(true);

        JPanel panel = helper2(status, resultText, found);

        resultWindow.add(panel);
        resultWindow.setVisible(true);

        // Set up a timer to close the window after 4 seconds
        Timer closeTimer = new Timer(4000, e -> resultWindow.dispose());
        closeTimer.setRepeats(false); // Ensure the timer only runs once
        closeTimer.start();

        // Restart the main timer
        mainTimer.start();
    }

    /**
     * Creates and configures the result panel for displaying the search results.
     * 
     * @requires status != null, resultText != null
     * @modifies none
     * @effects returns a JPanel displaying the search results or a no-result message
     */
    private JPanel helper2(String status, StringBuilder resultText, boolean found) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Search Results - Status: " + status);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        if (found) {
            JLabel resultLabel = new JLabel(resultText.toString());
            resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(resultLabel);
        } else {
            JLabel noResult = new JLabel("No pets found with status: " + status);
            noResult.setFont(new Font("Arial", Font.PLAIN, 16));
            noResult.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(noResult);
        }
        return panel;
    }

    /**
     * Creates and configures the result window for displaying the search results.
     * 
     * @requires status != null, mainTimer != null
     * @modifies mainTimer
     * @effects returns a JFrame configured for displaying search results
     */
    private JFrame helper(String status, Timer mainTimer) {
        JFrame resultWindow = new JFrame("Search Result - Status: " + status);
        resultWindow.setSize(400, 300);
        resultWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultWindow.setLocationRelativeTo(null);

        resultWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resultWindow.dispose();
                mainTimer.start();
            }
        });
        return resultWindow;
    }
}
