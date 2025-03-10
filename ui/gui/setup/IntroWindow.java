package ui.gui.setup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.AllPets;
import model.Pet;
import ui.gui.mainwindow.MainWindow;

// Effects: Creates an introduction window for the game
public class IntroWindow {

    private JFrame frame;

    /**
     * Requires: `pets`, `imagePath`, and `pet` are non-null.
     * Modifies: Initializes and displays the intro window for the pet game.
     * Effects:  Shows the welcome screen, instructions, and a "Start" button
     *           to proceed to the main game.
     *
     * @param pets      the AllPets collection managing all pets
     * @param imagePath the file path of the pet's image
     * @param pet       the pet object being introduced
     */
    public IntroWindow(AllPets pets, String imagePath, Pet pet) {
        initilize(pets, imagePath, pet);
    }

    /**
     * Requires: `pets`, `imagePath`, and `pet` are non-null.
     * Modifies: Sets up the JFrame and its components for the intro window.
     * Effects:  Displays the welcome message, instructions, and the "Start" button.
     *
     * @param pets      the AllPets collection managing all pets
     * @param imagePath the file path of the pet's image
     * @param pet       the pet object being introduced
     */
    public void initilize(AllPets pets, String imagePath, Pet pet) {
        extracted();

        getWelcomeLabel(pet);

        JPanel southPanel = getEgg(imagePath);

        getStart(pets, imagePath, pet, southPanel);

        JTextArea instructions = getInstructions();

        // Add JTextArea to a JScrollPane in case of overflow
        JScrollPane scrollPane = new JScrollPane(instructions);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Requires: None.
     * Modifies: Creates and configures a JTextArea for displaying instructions.
     * Effects:  Displays game instructions in a readable format with line wrapping.
     *
     * @return the configured JTextArea containing instructions
     */
    private JTextArea getInstructions() {
        JTextArea instructions = new JTextArea();
        instructions.setText("Instructions:\n"
                + "Your pet will die if all its levels hit zero and it is unhealthy.\n"
                + "Feed: +30 Fullness, +5 Happiness.\n"
                + "Clean: +30 Cleanliness, +5 Happiness.\n"
                + "Play: +20 Happiness.\n"
                + "If your pet is sick, you can cure it with medication.\n"
                + "Fullness/Cleanliness at 100: No more feeding/cleaning.\n"
                + "Happiness at 100: Playable but won't increase further.\n"
                + "At age 5, choose to let your pet marry or keep raising it.");
        instructions.setFont(new Font("Arial", Font.PLAIN, 16));
        instructions.setEditable(false);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        return instructions;
    }

    /**
     * Requires: `pets`, `imagePath`, and `pet` are non-null. `southPanel` is initialized.
     * Modifies: Adds a "Start" button to the south panel.
     * Effects:  Clicking the button transitions to the `MainWindow` and closes the intro window.
     *
     * @param pets      the AllPets collection managing all pets
     * @param imagePath the file path of the pet's image
     * @param pet       the pet object being introduced
     * @param southPanel the panel where the button will be added
     */
    private void getStart(AllPets pets, String imagePath, Pet pet, JPanel southPanel) {
        JButton start = new JButton("Start");
        southPanel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainWindow(pets, imagePath, pet);
            }
        });
    }

    /**
     * Requires: `imagePath` is non-null.
     * Modifies: Creates and configures a JPanel with an image label.
     * Effects:  Displays the pet's egg image in the south panel.
     *
     * @param imagePath the file path of the pet's image
     * @return the configured JPanel containing the egg image
     */
    private JPanel getEgg(String imagePath) {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.WHITE);
        southPanel.setPreferredSize(new Dimension(100, 170));
        JLabel label = new JLabel();
        ImageIcon egg = new ImageIcon(new ImageIcon(imagePath).getImage()
                .getScaledInstance(170, 170, Image.SCALE_SMOOTH));
        label.setIcon(egg);
        southPanel.add(label);
        return southPanel;
    }

    /**
     * Requires: `pet` is non-null.
     * Modifies: Creates and adds a welcome label to the north panel.
     * Effects:  Displays a message introducing the pet.
     *
     * @param pet the pet object being introduced
     */
    private void getWelcomeLabel(Pet pet) {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.PINK);
        JLabel welcomeLabel = new JLabel(
                "You have a " + pet.getGender() + " " + pet.getType() + " with the name " + pet.getName() + " .",
                SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        northPanel.add(welcomeLabel);
        frame.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Requires: None.
     * Modifies: Configures the JFrame for the intro window.
     * Effects:  Sets the layout, size, and default close operation for the frame.
     */
    private void extracted() {
        frame = new JFrame("Tomagochi");
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
