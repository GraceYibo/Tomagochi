package ui.run;

import javax.swing.SwingUtilities;
import model.AllPets;
import ui.gui.setup.JsonFileChecker;

/**
 * Main entry point for the GUI-based Tomagachi application.
 */
public class MainRun {

    /**
     * Launches the GUI application.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AllPets pets = new AllPets("Pets");
            new JsonFileChecker(pets); // Check and load JSON data or start fresh
        });
    }
}
