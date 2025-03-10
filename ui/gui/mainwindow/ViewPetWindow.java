package ui.gui.mainwindow;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import model.Pet;

// Effects: creates a window
public class ViewPetWindow {

    // Effects: creates a window
    public ViewPetWindow(Pet pet, Timer mainTimer) {

        initilize(pet, mainTimer);
    }

    // Effects: creates a window
    public void initilize(Pet pet, Timer mainTimer) {

        JFrame timedWindow = getTimedWindow();

        extracted(mainTimer, timedWindow);

        JPanel panel = getPanel();

        JPanel panelImage = getPanelImage();

        String petName = String.valueOf(pet.getName());
        String petAge = String.valueOf(pet.getAge());
        String petGender = String.valueOf(pet.getGender());
        String petStatus = String.valueOf(pet.getStatus());

        JLabel nameLabel = getNameLabel(petName);

        JLabel ageLabel = getAgeLabel(petAge);

        JLabel genderLabel = getGenderLabel(petGender);
        
        JLabel statusLabel = getStatusLabel(petStatus);

        JLabel image = getI();

        extracted2(panel, panelImage, nameLabel, ageLabel, genderLabel, statusLabel, image);
        
        timedWindow.add(panel, BorderLayout.CENTER);
        timedWindow.add(panelImage, BorderLayout.SOUTH);
        // Show the new window
        timedWindow.setVisible(true);

        getTimer(mainTimer, timedWindow);
    }

    // Effects: creates a window
    private void extracted2(JPanel panel, JPanel panelImage, JLabel nameLabel, JLabel ageLabel, JLabel genderLabel,
            JLabel statusLabel, JLabel image) {
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(genderLabel);
        panel.add(statusLabel);

        panelImage.add(image);
    }

    // Effects: creates a window
    private void getTimer(Timer mainTimer, JFrame timedWindow) {
        // Set up a Timer to close the window after 2 seconds
        Timer timer = new Timer(5000, e -> {
            timedWindow.dispose();
            mainTimer.start();
        });

        // Make sure the timer only runs once
        timer.setRepeats(false);
        timer.start();
    }

    // Effects: creates a window
    private JLabel getI() {
        JLabel image = new JLabel();
        image.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        ImageIcon i = new ImageIcon(new ImageIcon("src/main/Image/ViewWindow/viewPet.png").getImage()
                .getScaledInstance(400, 300, Image.SCALE_SMOOTH));
        image.setIcon(i);
        return image;
    }

    // Effects: creates a window
    private JLabel getStatusLabel(String petStatus) {
        JLabel statusLabel = new JLabel("Current Status: " + petStatus, SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return statusLabel;
    }

    // Effects: creates a window
    private JLabel getGenderLabel(String petGender) {
        JLabel genderLabel = new JLabel("Gender: " + petGender, SwingConstants.CENTER);
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return genderLabel;
    }

    // Effects: creates a window
    private JLabel getAgeLabel(String petAge) {
        JLabel ageLabel = new JLabel("Age: " + petAge, SwingConstants.CENTER);
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return ageLabel;
    }

    // Effects: creates a window
    private JLabel getNameLabel(String petName) {
        JLabel nameLabel = new JLabel("Name: " + petName, SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return nameLabel;
    }

    // Effects: creates a window
    private JPanel getPanelImage() {
        JPanel panelImage = new JPanel();
        panelImage.setBackground(Color.WHITE);
        return panelImage;
    }

    // Effects: creates a window
    private JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return panel;
    }

    // Effects: creates a window
    private void extracted(Timer mainTimer, JFrame timedWindow) {
        timedWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the window
                timedWindow.dispose();

                // Start the timer
                mainTimer.start();
            }
        });
    }

    // Effects: creates a window
    private JFrame getTimedWindow() {
        JFrame timedWindow = new JFrame("View Window");
        timedWindow.setSize(700, 450);
        timedWindow.setLocationRelativeTo(null);
        timedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return timedWindow;
    }
}
