package ui.gui.mainwindow.search;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.AllPets;
import model.Pet;

/**
 * Represents a search window for finding pets by age.
 */
public class SearchByAgeWindow {

    /**
     * Creates a search window that displays pets matching the given age.
     * 
     * @requires pets != null, pet != null, input is a valid string representation of an integer
     * @modifies this, mainTimer
     * @effects creates a window displaying the search results and pauses the main timer
     */
    public SearchByAgeWindow(AllPets pets, Pet pet, String input, Timer mainTimer) {
        int age = parseAge(input);
        List<Pet> matchingPets = findPetsByAge(pets, age);
        displayResults(matchingPets, mainTimer);
    }

    /**
     * Parses the input string to an integer representing age.
     * 
     * @requires input is a valid string representation of an integer
     * @modifies none
     * @effects returns the integer representation of input
     */
    private int parseAge(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid age input: " + input, e);
        }
    }

    /**
     * Finds pets with the specified age.
     * 
     * @requires pets != null
     * @modifies none
     * @effects returns a list of pets whose age matches the given age
     */
    private List<Pet> findPetsByAge(AllPets pets, int age) {
        List<Pet> matchingPets = new ArrayList<>();
        for (Pet p : pets.getPets()) {
            if (p.getAge() == age) {
                matchingPets.add(p);
            }
        }
        return matchingPets;
    }

    /**
     * Displays the search results in a window.
     * 
     * @requires matchingPets != null, mainTimer != null
     * @modifies this, mainTimer
     * @effects creates a window to display matching pets and pauses the main timer
     */
    private void displayResults(List<Pet> matchingPets, Timer mainTimer) {
        JFrame resultWindow = createResultWindow(mainTimer);
        JPanel panel = createResultPanel(matchingPets);
        resultWindow.add(panel);
        resultWindow.setVisible(true);

        startCloseTimer(resultWindow, mainTimer);
    }

    /**
     * Creates the result window for displaying search results.
     * 
     * @requires mainTimer != null
     * @modifies mainTimer
     * @effects returns a JFrame configured to display search results
     */
    private JFrame createResultWindow(Timer mainTimer) {
        JFrame resultWindow = new JFrame("Search Result");
        resultWindow.setSize(400, 300);
        resultWindow.setLocationRelativeTo(null);
        resultWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resultWindow.dispose();
                mainTimer.start();
            }
        });
        return resultWindow;
    }

    /**
     * Creates the panel to display search results.
     * 
     * @requires matchingPets != null
     * @modifies none
     * @effects returns a JPanel with the search results
     */
    private JPanel createResultPanel(List<Pet> matchingPets) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addTitleToPanel(panel);
        addResultsToPanel(panel, matchingPets);

        return panel;
    }

    /**
     * Adds a title to the given panel.
     * 
     * @requires panel != null
     * @modifies panel
     * @effects adds a title label to the panel
     */
    private void addTitleToPanel(JPanel panel) {
        JLabel title = new JLabel("Search Results");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
    }

    /**
     * Adds search results to the given panel.
     * 
     * @requires panel != null, matchingPets != null
     * @modifies panel
     * @effects adds labels representing the search results to the panel
     */
    private void addResultsToPanel(JPanel panel, List<Pet> matchingPets) {
        if (matchingPets.isEmpty()) {
            JLabel noResult = createLabel("Sorry, no pets found with the given age!", Font.PLAIN, 16);
            panel.add(noResult);
        } else {
            for (Pet p : matchingPets) {
                JLabel result = createLabel("<html>Name: " + p.getName() + "<br>Age: " + p.getAge()
                        + "<br>Gender: " + p.getGender() + "<br>Status: " + p.getStatus() + "</html>",
                        Font.PLAIN, 16);
                panel.add(result);
            }
        }
    }

    /**
     * Creates a JLabel with the given text and style.
     * 
     * @requires text != null
     * @modifies none
     * @effects returns a JLabel with the specified text, style, and size
     */
    private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", style, size));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }

    /**
     * Starts a timer to close the result window and restart the main timer after 4 seconds.
     * 
     * @requires resultWindow != null, mainTimer != null
     * @modifies mainTimer
     * @effects closes the result window and restarts the main timer after a delay
     */
    private void startCloseTimer(JFrame resultWindow, Timer mainTimer) {
        Timer closeTimer = new Timer(4000, e -> {
            resultWindow.dispose();
            mainTimer.start();
        });
        closeTimer.setRepeats(false);
        closeTimer.start();
    }
}
