package ui.gui.setup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.AllPets;
import model.Pet;
import model.Status;
import ui.gui.mainwindow.helper.RandomImageSelector;
import ui.gui.mainwindow.helper.Update;

// Effects: Creates a window to assign gender to the pet
public class GenderWindow {

    private JFrame frame;
    private Pet pet;
    private AllPets pets;
    private RandomImageSelector image;
    private String imagePath;

    /**
     * Requires: `petName` and `pets` are non-null.
     * Modifies: Adds a new pet to `pets`.
     * Effects:  Initializes the gender selection window and creates a new pet with a default
     *           gender ("Girl"), updating it based on user selection.
     *
     * @param petName the name of the pet
     * @param pets    the AllPets collection managing all pets
     */
    public GenderWindow(String petName, AllPets pets) {
        this.pets = pets;
        image = new RandomImageSelector();
        imagePath = image.getRandomImageLink();
        initilize(petName, pets);
        init(petName);
    }

    /**
     * Requires: `petName` is non-null.
     * Modifies: Adds a new pet to `pets`.
     * Effects:  Creates a new `Pet` object with the given name, default gender ("Girl"),
     *           alive status, and a randomly selected image. Updates the `pets` collection
     *           and saves the pet data.
     *
     * @param petName the name of the pet
     */
    private void init(String petName) {
        pet = new Pet(petName, 0, "Girl", Status.alive, "pet", imagePath);
        new Update(pet);
        pets.addPet(pet);
    }

    /**
     * Requires: `petName` and `pets` are non-null.
     * Modifies: Creates and initializes a new JFrame for gender selection.
     * Effects:  Sets up the main window layout and UI components for selecting the pet's gender.
     *
     * @param petName the name of the pet
     * @param pets    the AllPets collection managing all pets
     */
    private void initilize(String petName, AllPets pets) {
        extracted();

        JPanel northPanel = getNorthPanel();
        getText(petName, northPanel);

        JPanel southPanel = getSouthPanel();

        getEgg(southPanel);

        getFemale(petName, southPanel, pets);
        getMale(petName, southPanel, pets);

        this.frame.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Requires: `petName`, `southPanel`, and `pets` are non-null.
     * Modifies: Sets the gender of the pet to "male" and updates the pet data.
     * Effects:  Adds a "male" button to the south panel. On button click, updates the pet's
     *           gender and transitions to the `EggHatchingWindow`.
     *
     * @param petName   the name of the pet
     * @param southPanel the panel to which the button is added
     * @param pets      the AllPets collection managing all pets
     */
    private void getMale(String petName, JPanel southPanel, AllPets pets) {
        JButton male = new JButton("male");
        southPanel.add(male);
        male.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pet.setGender("male");
                new EggHatchingWindow(imagePath, pet, pets);
                frame.dispose();
            }
        });
    }

    /**
     * Requires: `petName`, `southPanel`, and `pets` are non-null.
     * Modifies: Sets the gender of the pet to "female" and updates the pet data.
     * Effects:  Adds a "female" button to the south panel. On button click, updates the pet's
     *           gender and transitions to the `EggHatchingWindow`.
     *
     * @param petName   the name of the pet
     * @param southPanel the panel to which the button is added
     * @param pets      the AllPets collection managing all pets
     */
    private void getFemale(String petName, JPanel southPanel, AllPets pets) {
        JButton female = new JButton("female");
        southPanel.add(female);
        female.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pet.setGender("female");
                new EggHatchingWindow(imagePath, pet, pets);
                frame.dispose();
            }
        });
    }

    /**
     * Requires: `southPanel` is non-null.
     * Modifies: Adds an image label to the panel.
     * Effects:  Displays an egg image in the south panel.
     *
     * @param southPanel the panel to which the egg image is added
     */
    private void getEgg(JPanel southPanel) {
        JLabel label = new JLabel();
        ImageIcon egg = new ImageIcon(new ImageIcon("src/main/Image/BeginningWindow/egg.jpg").getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        label.setIcon(egg);
        label.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        southPanel.add(label);
    }

    /**
     * Requires: None.
     * Modifies: Creates a new JPanel.
     * Effects:  Sets up the south panel for layout and styling.
     *
     * @return the configured south panel
     */
    private JPanel getSouthPanel() {
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.WHITE);
        southPanel.setPreferredSize(new Dimension(100, 400));
        return southPanel;
    }

    /**
     * Requires: None.
     * Modifies: Creates a new JPanel.
     * Effects:  Sets up the north panel for layout and styling.
     *
     * @return the configured north panel
     */
    private JPanel getNorthPanel() {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.setBackground(Color.WHITE);
        return northPanel;
    }

    /**
     * Requires: `petName` and `northPanel` are non-null.
     * Modifies: Adds text labels to the north panel.
     * Effects:  Displays a welcome message and instructions for gender selection.
     *
     * @param petName   the name of the pet
     * @param northPanel the panel to which the text is added
     */
    private void getText(String petName, JPanel northPanel) {
        JLabel welcomeLabel = new JLabel("Hello, " + petName + "! Let's start your pet journey!",
                SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel text = new JLabel("Give your pet a gender: ", SwingConstants.CENTER);
        text.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        text.setFont(new Font("Arial", Font.PLAIN, 18));
        northPanel.add(welcomeLabel);
        northPanel.add(text);
        frame.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Requires: None.
     * Modifies: Creates and configures a new JFrame.
     * Effects:  Sets up the main frame for the gender selection window.
     */
    private void extracted() {
        frame = new JFrame();
        this.frame.setLayout(new BorderLayout(10, 5));
        this.frame.setTitle("Tomagochi");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(700, 450);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);
        this.frame.setVisible(true);
    }
}
