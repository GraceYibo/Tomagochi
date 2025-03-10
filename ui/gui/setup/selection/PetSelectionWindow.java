package ui.gui.setup.selection;

import javax.swing.*;
import model.AllPets;
import model.Pet;
import model.Status;

import java.awt.*;

/**
 * Represents a window for selecting an animal type, which leads to another
 * window with images of the chosen animal,
 * and clicking an image opens a detailed view.
 */
public class PetSelectionWindow {

    /**
     * Constructs the PetSelectionWindow.
     *
     * @param pets the list of all pets
     * @param name the name of the user
     */
    public PetSelectionWindow(AllPets pets, String name) {
        createPetSelectionWindow(pets, name);
    }

    /**
     * Creates the main pet selection window.
     *
     * @param pets the list of all pets
     * @param name the name of the user
     */
    private void createPetSelectionWindow(AllPets pets, String name) {
        JFrame frame = getFrame();
        String[] animals = { "Cat", "Dog", "Rabbit", "Turtle", "Fish", "Bird", "Snake", "Mouse" };

        for (String animal : animals) {
            JButton button = createAnimalButton(animal, pets, name, frame);
            frame.add(button);
        }

        frame.setVisible(true);
    }

    /**
     * Creates a button for an animal type with an image.
     *
     * @param animal the name of the animal
     * @param pets   the list of all pets
     * @param name   the name of the user
     * @param frame  the parent frame
     * @return the JButton object
     */
    private JButton createAnimalButton(String animal, AllPets pets, String name, JFrame frame) {
        JButton button = new JButton(animal);
        String imagePath = "src/main/Image/SelectPetType/" + animal.toLowerCase() + ".png";

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        button.addActionListener(e -> {
            frame.dispose(); // Close the pet selection window
            new AnimalImageSelectionWindow(animal, pets, name); // Open the specific animal selection window
        });

        return button;
    }

    /**
     * Creates the main frame for the pet selection window.
     *
     * @return the JFrame object
     */
    private JFrame getFrame() {
        JFrame frame = new JFrame("Select Your Pet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(2, 4, 10, 10));
        frame.setLocationRelativeTo(null);
        return frame;
    }

    /**
     * Represents a window showing images of the selected animal type.
     */
    public static class AnimalImageSelectionWindow {

        /**
         * Constructs the AnimalImageSelectionWindow.
         *
         * @param animal the name of the selected animal
         * @param pets   the list of all pets
         * @param name   the name of the user
         */
        public AnimalImageSelectionWindow(String animal, AllPets pets, String name) {
            JFrame frame = getFrame(animal);

            // Main panel for displaying images and buttons
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            // Grid panel for animal images
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(3, 4, 10, 10));

            String[][] animalData = getAnimalData(animal);

            for (String[] data : animalData) {
                JButton button = createImageButton(data[0], data[1], data[2], data[3], frame, pets);
                gridPanel.add(button);
            }

            // Back button panel
            JPanel backPanel = new JPanel();
            backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JButton backButton = new JButton("Back");
            backButton.addActionListener(e -> {
                frame.dispose(); // Close the current window
                new PetSelectionWindow(pets, name); // Reopen the pet selection window
            });

            backPanel.add(backButton);

            // Add panels to the main panel
            mainPanel.add(gridPanel, BorderLayout.CENTER);
            mainPanel.add(backPanel, BorderLayout.SOUTH);

            // Add main panel to frame
            frame.add(mainPanel);
            frame.setVisible(true);
        }

        private JFrame getFrame(String animal) {
            JFrame frame = new JFrame("Select a " + animal);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            return frame;
        }

        private String[][] getAnimalData(String animal) {
            switch (animal.toLowerCase()) {
                case "rabbit":
                    return extracted();
                case "cat":
                    return extracted2();
                case "dog":
                    return extracted3();
                case "bird":
                    return extracted4();
                case "fish":
                    return extracted5();
                case "snake":
                    return extracted6();
                case "turtle":
                    return extracted7();
                case "mouse":
                    return extracted8();
                default:
                    return new String[][] { { "No Data", "" } };
            }
        }

        private String[][] extracted8() {
            return new String[][] {
                    { "mouse", "Buddy", "male", "src/main/Image/SelectPetType/Mouse/r1.png" },
                    { "mouse", "Bella", "female", "src/main/Image/SelectPetType/Mouse/r2.png" },
                    { "mouse", "Max", "male", "src/main/Image/SelectPetType/Mouse/r3.png" },
                    { "mouse", "Luna", "female", "src/main/Image/SelectPetType/Mouse/r4.png" },
                    { "mouse", "Max", "male", "src/main/Image/SelectPetType/Mouse/r5.png" },
                    { "mouse", "Luna", "female", "src/main/Image/SelectPetType/Mouse/r6.png" }
            };
        }

        private String[][] extracted7() {
            return new String[][] {
                    { "turtle", "Buddy", "male", "src/main/Image/SelectPetType/Turtles/r1.png" },
                    { "turtle", "Bella", "female", "src/main/Image/SelectPetType/Turtles/r2.png" },
                    { "turtle", "Max", "male", "src/main/Image/SelectPetType/Turtles/r3.png" },
                    { "turtle", "Luna", "female", "src/main/Image/SelectPetType/Turtles/r4.png" },
                    { "turtle", "Buddy", "male", "src/main/Image/SelectPetType/Turtles/r5.png" },
                    { "turtle", "Bella", "female", "src/main/Image/SelectPetType/Turtles/r6.png" },
                    { "turtle", "Max", "male", "src/main/Image/SelectPetType/Turtles/r7.png" },
                    { "turtle", "Luna", "female", "src/main/Image/SelectPetType/Turtles/r8.png" },
                    { "turtle", "Buddy", "male", "src/main/Image/SelectPetType/Turtles/r9.png" },
                    { "turtle", "Bella", "female", "src/main/Image/SelectPetType/Turtles/r10.png" },
                    { "turtle", "Max", "male", "src/main/Image/SelectPetType/Turtles/r11.png" },
                    { "turtle", "Luna", "female", "src/main/Image/SelectPetType/Turtles/r12.png" }
            };
        }

        private String[][] extracted6() {
            return new String[][] {
                    { "snake", "Buddy", "male", "src/main/Image/SelectPetType/Snakes/r1.png" },
                    { "snake", "Bella", "female", "src/main/Image/SelectPetType/Snakes/r2.png" },
                    { "snake", "Max", "male", "src/main/Image/SelectPetType/Snakes/r3.png" },
                    { "snake", "Luna", "female", "src/main/Image/SelectPetType/Snakes/r4.png" },
                    { "snake", "Max", "male", "src/main/Image/SelectPetType/Snakes/r5.png" },
                    { "snake", "Luna", "female", "src/main/Image/SelectPetType/Snakes/r6.png" }
            };
        }

        private String[][] extracted5() {
            return new String[][] {
                    { "fish", "Buddy", "male", "src/main/Image/SelectPetType/Fishes/r1.png" },
                    { "fish", "Bella", "female", "src/main/Image/SelectPetType/Fishes/r2.png" },
                    { "fish", "Max", "male", "src/main/Image/SelectPetType/Fishes/r3.png" },
                    { "fish", "Luna", "female", "src/main/Image/SelectPetType/Fishes/r4.png" },
                    { "fish", "Buddy", "male", "src/main/Image/SelectPetType/Fishes/r5.png" },
                    { "fish", "Bella", "female", "src/main/Image/SelectPetType/Fishes/r6.png" },
                    { "fish", "Max", "male", "src/main/Image/SelectPetType/Fishes/r7.png" },
                    { "fish", "Luna", "female", "src/main/Image/SelectPetType/Fishes/r8.png" },
                    { "fish", "Buddy", "male", "src/main/Image/SelectPetType/Fishes/r9.png" },
                    { "fish", "Bella", "female", "src/main/Image/SelectPetType/Fishes/r10.png" },
                    { "fish", "Max", "male", "src/main/Image/SelectPetType/Fishes/r11.png" },
                    { "fish", "Luna", "female", "src/main/Image/SelectPetType/Fishes/r12.png" }
            };
        }

        private String[][] extracted4() {
            return new String[][] {
                    { "bird", "Buddy", "male", "src/main/Image/SelectPetType/Birds/r1.png" },
                    { "bird", "Bella", "female", "src/main/Image/SelectPetType/Birds/r2.png" },
                    { "bird", "Max", "male", "src/main/Image/SelectPetType/Birds/r3.png" },
                    { "bird", "Luna", "female", "src/main/Image/SelectPetType/Birds/r4.png" },
                    { "bird", "Buddy", "male", "src/main/Image/SelectPetType/Birds/r5.png" },
                    { "bird", "Bella", "female", "src/main/Image/SelectPetType/Birds/r6.png" },
                    { "bird", "Max", "male", "src/main/Image/SelectPetType/Birds/r7.png" },
                    { "bird", "Luna", "female", "src/main/Image/SelectPetType/Birds/r8.png" },
                    { "bird", "Luna", "female", "src/main/Image/SelectPetType/Birds/r9.png" }
            };
        }

        private String[][] extracted3() {
            return new String[][] {
                    { "dog", "Buddy", "male", "src/main/Image/SelectPetType/Dogs/r1.png" },
                    { "dog", "Bella", "female", "src/main/Image/SelectPetType/Dogs/r2.png" },
                    { "dog", "Max", "male", "src/main/Image/SelectPetType/Dogs/r3.png" },
                    { "dog", "Lazzy", "female", "src/main/Image/SelectPetType/Dogs/r4.png" },
                    { "dog", "Bobby", "male", "src/main/Image/SelectPetType/Dogs/r5.png" },
                    { "dog", "Visa", "female", "src/main/Image/SelectPetType/Dogs/r6.png" }
            };
        }

        private String[][] extracted2() {
            return new String[][] {
                    { "cat", "Whiskers", "male", "src/main/Image/SelectPetType/Cats/r1.png" },
                    { "cat", "Mittens", "male", "src/main/Image/SelectPetType/Cats/r2.png" },
                    { "cat", "Simba", "male", "src/main/Image/SelectPetType/Cats/r3.png" },
                    { "cat", "Nala", "female", "src/main/Image/SelectPetType/Cats/r4.png" },
                    { "cat", "tiger", "male", "src/main/Image/SelectPetType/Cats/r5.png" },
                    { "cat", "cat", "female", "src/main/Image/SelectPetType/Cats/r6.png" }
            };
        }

        private String[][] extracted() {
            return new String[][] {
                    { "rabbit", "Fluffy", "female", "src/main/Image/SelectPetType/Rabbit/r1.png" },
                    { "rabbit", "Coco", "female", "src/main/Image/SelectPetType/Rabbit/r2.png" },
                    { "rabbit", "Thumper", "male", "src/main/Image/SelectPetType/Rabbit/r3.png" },
                    { "rabbit", "Snowball", "male", "src/main/Image/SelectPetType/Rabbit/r4.png" },
                    { "rabbit", "Bunny", "female", "src/main/Image/SelectPetType/Rabbit/r5.png" },
                    { "rabbit", "Daisy", "female", "src/main/Image/SelectPetType/Rabbit/r6.png" },
                    { "rabbit", "Luna", "female", "src/main/Image/SelectPetType/Rabbit/r7.png" },
                    { "rabbit", "Hopper", "male", "src/main/Image/SelectPetType/Rabbit/r8.png" },
                    { "rabbit", "Patches", "male", "src/main/Image/SelectPetType/Rabbit/r9.png" },
                    { "rabbit", "Hazel", "female", "src/main/Image/SelectPetType/Rabbit/r10.png" },
                    { "rabbit", "Mochi", "female", "src/main/Image/SelectPetType/Rabbit/r11.png" },
                    { "rabbit", "Velvet", "female", "src/main/Image/SelectPetType/Rabbit.png" }
            };
        }

        private JButton createImageButton(String animal, String name, String gender, String imagePath,
                JFrame parentFrame, AllPets pets) {
            JButton button = new JButton(name);
            button.setIcon(loadImageIcon(imagePath));
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);

            button.addActionListener(e -> {
                parentFrame.dispose(); // Close the specific animal selection window
                new SelectedAnimalWindow(animal, name, imagePath, gender, pets);
            });

            return button;
        }

        private ImageIcon loadImageIcon(String imagePath) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
    }

}
