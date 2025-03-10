package ui.gui.mainwindow.helper;

import java.awt.*;
import javax.swing.*;
import model.AllPets;
import model.Pet;
import ui.gui.setup.ChooseOwnPetWindow;

// Represents a GUI window for managing pet marriage decisions. 
// Allows the user to choose whether their pet should get married and select a partner for the pet.


public class MarryWindow {

    private static boolean beginningWindowOpen = false;
    private boolean userDecision;

    // Constructs a marry window 
    // Pets: the list of all pets
    // Pet: the current pet
    // Frame: the application frame passed in
    // Age: the age of pet
    public MarryWindow(AllPets pets, Pet pet, JFrame frame, int age) {
        userDecision = initialize(pets, pet, frame);
    }

    // initializes marry window
    // Requires: pets, pet, and frame must not be null.  
    // Modifies: This.  
    // Effects: Displays a GUI for the user to decide on pet marriage.
    public boolean initialize(AllPets pets, Pet pet, JFrame frame) {
        JFrame timedWindow = createTimedWindow();
        JLabel messageLabel = new JLabel("Your pet is at the age of getting married. Do you want it to be married?");
        JButton yesButton = createYesButton(pets, pet, frame, timedWindow);
        JButton noButton = createNoButton(timedWindow);

        addComponentsToWindow(timedWindow, messageLabel, yesButton, noButton);
        return userDecision;
    }

    // effects: Constructs and displays a JFrame for the marriage decision.
    private JFrame createTimedWindow() {
        JFrame timedWindow = new JFrame("Marry Window");
        timedWindow.setSize(700, 450);
        timedWindow.setLocationRelativeTo(null);
        timedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timedWindow.setLayout(null);
        timedWindow.setVisible(true);
        return timedWindow;
    }

    // effects: create a button
    private JButton createYesButton(AllPets pets, Pet pet, JFrame frame, JFrame timedWindow) {
        JButton yesButton = new JButton("Yes");
        yesButton.setBounds(200, 200, 100, 30);
        yesButton.addActionListener(e -> {
            frame.dispose();
            timedWindow.dispose();
            new YesWindow(pet, pets);
        });
        return yesButton;
    }

    // effects: creates a button
    private JButton createNoButton(JFrame timedWindow) {
        JButton noButton = new JButton("No");
        noButton.setBounds(400, 200, 100, 30);
        noButton.addActionListener(e -> timedWindow.dispose());
        return noButton;
    }

    // Effects: helper function to create componeints
    private void addComponentsToWindow(JFrame timedWindow, JLabel messageLabel, JButton yesButton, JButton noButton) {
        messageLabel.setBounds(50, 100, 600, 30);
        timedWindow.add(messageLabel);
        timedWindow.add(yesButton);
        timedWindow.add(noButton);
    }

    // effects: creates window
    public static class YesWindow {
        public YesWindow(Pet pet, AllPets pets) {
            JFrame yesWindow = createYesWindow();
            addContentToYesWindow(yesWindow, pet, pets);
        }

        // effects: creates window
        private JFrame createYesWindow() {
            JFrame yesWindow = new JFrame("Yes - Pet Marriage");
            yesWindow.setSize(700, 450);
            yesWindow.setLocationRelativeTo(null);
            yesWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            yesWindow.setLayout(new BorderLayout());
            yesWindow.setVisible(true);
            return yesWindow;
        }

        // effects: creates window
        private void addContentToYesWindow(JFrame yesWindow, Pet pet, AllPets pets) {
            JPanel topPanel = createPanel(Color.WHITE, new JLabel("Who do you want your pet to get married with?"));
            JPanel middlePanel = createPanel(Color.WHITE);
            JPanel bottomPanel = createPanel(Color.WHITE);

            addButtonsToPanel(middlePanel, bottomPanel, pet, yesWindow, pets);
            yesWindow.add(topPanel, BorderLayout.NORTH);
            yesWindow.add(middlePanel, BorderLayout.CENTER);
            yesWindow.add(bottomPanel, BorderLayout.SOUTH);
        }

        // effects: creates window
        private JPanel createPanel(Color color, JComponent... components) {
            JPanel panel = new JPanel();
            panel.setBackground(color);
            for (JComponent component : components) {
                panel.add(component);
            }
            return panel;
        }

        // effects: creates window
        private void addButtonsToPanel(JPanel middlePanel, JPanel bottomPanel, Pet pet, JFrame yesWindow,
                AllPets pets) {
            String[][] data = {
                    { "Bob", "bob.png" }, { "Lily", "lily.png" }, { "Mathiew", "mathiew.png" },
                    { "Sophie", "sophie.png" },
                    { "Kai", "kai.png" }, { "Joy", "joy.png" }, { "Nick", "nick.png" }, { "Grace", "grace.png" }
            };

            for (int i = 0; i < data.length; i++) {
                JButton button = createButton(data[i][0], "src/main/Image/MarryWindow/" + data[i][1], pet, yesWindow,
                        pets);
                if (i < 4) {
                    middlePanel.add(button);
                } else {
                    bottomPanel.add(button);
                }
            }
        }

        // effects: creates window
        private JButton createButton(String name, String imagePath, Pet pet, JFrame parentWindow, AllPets pets) {
            JButton button = new JButton(name);
            button.setIcon(createIcon(imagePath));
            button.addActionListener(e -> handleMarriage(name, pet, parentWindow, pets));
            return button;
        }

        // effects: creates window
        private ImageIcon createIcon(String imagePath) {
            return new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH));
        }

        // effects: creates window
        private void handleMarriage(String name, Pet pet, JFrame parentWindow, AllPets pets) {
            pet.setMarried();
            JFrame messageWindow = createMessageWindow(name, pets);
            closeAfterDelay(messageWindow, parentWindow, pets);
        }

        // effects: creates window
        private JFrame createMessageWindow(String name, AllPets pets) {
            JFrame messageWindow = new JFrame("Marriage Success");
            messageWindow.setSize(400, 200);
            messageWindow.setLocationRelativeTo(null);
            messageWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            messageWindow.add(new JLabel("Congratulations, your pet is married to " + name + "!", JLabel.CENTER));
            messageWindow.setVisible(true);
            return messageWindow;
        }

        // effects: creates window
        private void closeAfterDelay(JFrame messageWindow, JFrame parentWindow, AllPets pets) {
            new Timer(3000, e -> {
                messageWindow.dispose();
                if (!beginningWindowOpen) {
                    new ChooseOwnPetWindow(pets);
                    beginningWindowOpen = true;
                }
                parentWindow.dispose();
            }).start();
        }
    }

    // effects: helper for creates window
    public boolean getUserDecision() {
        return userDecision;
    }
}