package ui.run;

import java.io.FileNotFoundException;
import ui.TomagachiConsule;

/**
 * Main entry point for the console-based Tomagachi application.
 */
public class ConsoleRun {

    /**
     * Main method to launch the console application.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            new TomagachiConsule(); // Launch the console-based Tomagachi application
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run the application: file not found.");
        }
    }
}
