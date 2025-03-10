package ui.gui.setup;

import model.AllPets;
import ui.gui.setup.selection.PetSelectionWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Represents the starting window that lets the user decide whether to choose a
 * pet
 * or name their own pet to raise.
 */
public class BeginningWindow {

    /**
     * Constructs the BeginningWindow.
     *
     * @param pets the list of all pets
     */
    public BeginningWindow(AllPets pets) {
        createBeginningWindow(pets);
    }

    /**
     * Creates the main BeginningWindow.
     *
     * @param pets the list of all pets
     */
    private void createBeginningWindow(AllPets pets) {
        JFrame frame = new JFrame("Welcome to Pet Raising!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Would you like to choose a pet to raise or raise your own pet?",
                JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 0));

        JButton choosePetButton = new JButton("Choose a Pet");
        JButton namePetButton = new JButton("Raise My Own Pet");

        extracted(pets, frame, choosePetButton, namePetButton);

        buttonPanel.add(choosePetButton);
        buttonPanel.add(namePetButton);

        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void extracted(AllPets pets, JFrame frame, JButton choosePetButton, JButton namePetButton) {
        // Action for "Choose a Pet"
        choosePetButton.addActionListener((ActionEvent e) -> {
            frame.dispose(); // Close the current window
            new PetSelectionWindow(pets, "User"); // Open the PetSelectionWindow
        });

        // Action for "Raise My Own Pet"
        namePetButton.addActionListener((ActionEvent e) -> {
            frame.dispose(); // Close the current window
            new ChooseOwnPetWindow(pets); // Open the window for naming the pet
        });
    }
}
