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
 * Represents a search window for finding pets by name.
 */
public class SearchByNameWindow {

    /**
     * Creates a search window that displays pets matching the given name.
     * 
     * @requires pets != null, pet != null, input is a valid non-null string
     * @modifies this, mainTimer
     * @effects creates a window displaying the search results and pauses the main timer
     */
    public SearchByNameWindow(AllPets pets, Pet pet, String input, Timer mainTimer) {
        List<Pet> matchingPets = findPetsByName(pets, input);
        displayResults(matchingPets, mainTimer);
    }

    /**
     * Finds pets with the specified name.
     * 
     * @requires pets != null, name != null
     * @modifies none
     * @effects returns a list of pets whose name matches (case-insensitive) the given name
     */
    private List<Pet> findPetsByName(AllPets pets, String name) {
        List<Pet> matchingPets = new ArrayList<>();
        for (Pet p : pets.getPets()) {
            if (p.getName().equalsIgnoreCase(name)) {
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
        resultWindow.setAlwaysOnTop(true);

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
        resultWindow.setAlwaysOnTop(true);
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
        JLabel title = createLabel("Search Results", Font.BOLD, 20);
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
            JLabel noResult = createLabel("Sorry, no pets found with the given name!", Font.PLAIN, 16);
            panel.add(noResult);
        } else {
            for (Pet p : matchingPets) {
                JLabel result = createLabel(formatPetInfo(p), Font.PLAIN, 16);
                panel.add(result);
            }
        }
    }

    /**
     * Formats the information of a pet into an HTML string for display.
     * 
     * @requires pet != null
     * @modifies none
     * @effects returns a formatted string containing pet information
     */
    private String formatPetInfo(Pet pet) {
        return String.format("<html>Name: %s<br>Age: %d<br>Gender: %s<br>Status: %s</html>",
                pet.getName(), pet.getAge(), pet.getGender(), pet.getStatus());
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
