package ui.gui.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AllPets;
import model.Pet;

public class EggHatchingWindow {
    private JFrame frame;
    private JLabel countdownLabel;
    private int countdownTime = 10; // 10 seconds countdown

    /**
     * Requires: `imagePath` must be a valid path to an image.
     *           `pet` and `pets` must be non-null.
     * Modifies: None.
     * Effects:  Creates and displays a countdown window for the egg hatching process.
     *
     * @param imagePath the path to the egg image
     * @param pet       the Pet object associated with the egg
     * @param pets      the AllPets collection managing all pets
     */
    public EggHatchingWindow(String imagePath, Pet pet, AllPets pets) {
        setupCountdownWindow(imagePath, pet, pets);
    }

    /**
     * Requires: `imagePath` must be a valid path to an image.
     *           `pet` and `pets` must be non-null.
     * Modifies: Initializes and configures the main countdown window.
     * Effects:  Displays the egg hatching countdown with a timer and egg image.
     *
     * @param imagePath the path to the egg image
     * @param pet       the Pet object associated with the egg
     * @param pets      the AllPets collection managing all pets
     */
    private void setupCountdownWindow(String imagePath, Pet pet, AllPets pets) {
        frame = new JFrame("Egg Hatching");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Set background color of the frame to white
        frame.getContentPane().setBackground(Color.WHITE);

        // Message Label
        JLabel messageLabel = new JLabel("Your egg will hatch after 10 seconds!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.BLACK); // Set text color to black for contrast
        frame.add(messageLabel, BorderLayout.NORTH);

        // Countdown Label
        countdownLabel = new JLabel(String.valueOf(countdownTime), SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 48));
        countdownLabel.setForeground(Color.BLACK); // Set text color to black
        frame.add(countdownLabel, BorderLayout.CENTER);

        // Image Label (for the egg)
        JLabel imageLabel = new JLabel();
        ImageIcon eggImage = new ImageIcon(new ImageIcon(
                "src/main/Image/BeginningWindow/egg.jpg").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        imageLabel.setIcon(eggImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(imageLabel, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Start Countdown
        startCountdown(pets, imagePath, pet);
    }

    /**
     * Requires: `pets`, `imagePath`, and `pet` must be non-null.
     * Modifies: Updates the countdown time on the countdown label.
     *           Stops the timer and transitions to the hatched window when the countdown ends.
     * Effects:  Handles the countdown timer functionality.
     *
     * @param pets      the AllPets collection managing all pets
     * @param imagePath the path to the egg image
     * @param pet       the Pet object associated with the egg
     */
    private void startCountdown(AllPets pets, String imagePath, Pet pet) {
        Timer timer = new Timer(1000, new ActionListener() { // Timer updates every 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                countdownTime--;
                countdownLabel.setText(String.valueOf(countdownTime));

                if (countdownTime == 0) {
                    ((Timer) e.getSource()).stop(); // Stop the timer
                    frame.dispose(); // Close the current window
                    showHatchedWindow(pets, imagePath, pet); // Show the "hatched" window
                }
            }
        });
        timer.start();
    }

    /**
     * Requires: `pets`, `imagePath`, and `pet` must be non-null.
     * Modifies: None.
     * Effects:  Displays a new window showing the hatched pet with its image and a "Next" button.
     *
     * @param pets      the AllPets collection managing all pets
     * @param imagePath the path to the hatched pet's image
     * @param pet       the Pet object associated with the egg
     */
    private void showHatchedWindow(AllPets pets, String imagePath, Pet pet) {
        JFrame hatchedFrame = new JFrame("Egg Hatched!");
        hatchedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hatchedFrame.setSize(700, 450);
        hatchedFrame.setLocationRelativeTo(null);
        hatchedFrame.setLayout(new BorderLayout());

        // Set background color of the frame to white
        hatchedFrame.getContentPane().setBackground(Color.WHITE);

        // Message Label
        JLabel hatchedMessage = new JLabel("Your egg has hatched! Here is your pet!", SwingConstants.CENTER);
        hatchedMessage.setFont(new Font("Arial", Font.BOLD, 16));
        hatchedMessage.setForeground(Color.BLACK); // Set text color to black
        hatchedFrame.add(hatchedMessage, BorderLayout.NORTH); // Move to the top section (NORTH)

        // Image of the pet (below the message)
        JLabel petImageLabel = new JLabel();
        ImageIcon petImage = new ImageIcon(
                new ImageIcon(imagePath).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        petImageLabel.setIcon(petImage);
        petImageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image
        hatchedFrame.add(petImageLabel, BorderLayout.CENTER); // Add the image below the message

        getNextButton(pets, imagePath, pet, hatchedFrame);

        hatchedFrame.setVisible(true);
    }

    /**
     * Requires: `pets`, `imagePath`, `pet`, and `hatchedFrame` must be non-null.
     * Modifies: Closes the current window when the button is clicked.
     * Effects:  Adds a "Next" button to the frame and transitions to the IntroWindow.
     *
     * @param pets         the AllPets collection managing all pets
     * @param imagePath    the path to the pet's image
     * @param pet          the Pet object associated with the egg
     * @param hatchedFrame the JFrame displaying the hatched pet
     */
    private void getNextButton(AllPets pets, String imagePath, Pet pet, JFrame hatchedFrame) {
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 14));
        nextButton.addActionListener(e -> {
            hatchedFrame.dispose(); // Close the current window
            new IntroWindow(pets, imagePath, pet); // Open the IntroWindow
        });
        hatchedFrame.add(nextButton, BorderLayout.SOUTH); // Position the Next button at the bottom
    }
}
