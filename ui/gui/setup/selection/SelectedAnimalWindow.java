package ui.gui.setup.selection;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.*;
import model.AllPets;
import model.Pet;
import model.Status;
import ui.gui.setup.IntroWindow;

public class SelectedAnimalWindow {

     /**
     * Requires: `animal`, `name`, and `gender` must be non-null strings. 
     *           `imagePath` must be a valid file path to an image.
     *           `pets` must be a non-null instance of AllPets.
     * Modifies: Creates and adds a new Pet object to the provided AllPets instance.
     * Effects:  Displays a new GUI window for the selected animal with the specified information
     *           and functionality to transition to the IntroWindow upon pressing "OK".
     *
     * @param animal    the type of animal selected (e.g., "Dog", "Cat")
     * @param name      the name of the selected animal
     * @param imagePath the file path to the image of the selected animal
     * @param gender    the gender of the selected animal
     * @param pets      the AllPets instance to which the selected Pet is added
     */
    public SelectedAnimalWindow(String animal, String name, String imagePath, String gender, AllPets pets) {
        // Create the main frame
        JFrame frame = new JFrame("Selected Animal");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a label for the name
        JLabel nameLabel = new JLabel("You selected: " + name, JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create a label for the image`
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(loadImageIcon(imagePath));

        // Use a panel with GridBagLayout to center the image
        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.add(imageLabel);

        Pet pet = new Pet(name, 0, gender, Status.alive, animal, imagePath);
        pets.addPet(pet);

        // Create the "OK" button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            frame.dispose(); // Close the current window
            new IntroWindow(pets, imagePath, pet); // Open PullDataWindow
        });

        // Add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(nameLabel, BorderLayout.NORTH);
        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(okButton, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    /**
     * Requires: `imagePath` must be a valid file path to an image.
     * Modifies: None.
     * Effects:  Loads and returns a scaled ImageIcon from the provided image path.
     *
     * @param imagePath the file path to the image
     * @return an ImageIcon object scaled to 200x200 pixels
     */
    private ImageIcon loadImageIcon(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
