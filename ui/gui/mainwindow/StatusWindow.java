package ui.gui.mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Pet;

// Effects: creates a window
public class StatusWindow {

    // Effects: creates a window
    public StatusWindow(Pet pet, Timer mainTimer) {
        initilize(pet, mainTimer);
    }

    // Effects: creates a window
    public void initilize(Pet pet, Timer mainTimer) {

        JFrame timedWindow = getTimedWindow();

        extracted(mainTimer, timedWindow);
    
        // Instructions in JTextArea
        String updatedHappyLevel = String.valueOf(pet.getHappyLevel());
        String updatedCleaness = String.valueOf(pet.getCleanessLevel());
        String updatedFullness = String.valueOf(pet.getFullness());
        String updatedHealth = String.valueOf(pet.getHealth());

        JLabel happyLevel = getHappyLevel(updatedHappyLevel);

        JLabel cleanLevel = getCleanLevel(updatedCleaness);

        JLabel fullness = getFullness(updatedFullness);

        JLabel health = getHealth(updatedHealth);

        JLabel image = getI();

        JPanel panel = getPanel();
        
        JPanel panelImage = getPanelImage();

        extracted2(happyLevel, cleanLevel, fullness, health, image, panel, panelImage);

        extracted3(timedWindow, panel, panelImage);

        // Show the new window
        timedWindow.setVisible(true);

        getTimer2(mainTimer, timedWindow);

    }

    // Effects: creates a window
    private void getTimer2(Timer mainTimer, JFrame timedWindow) {
        Timer timer = getTimer(mainTimer, timedWindow);

        // Make sure the timer only runs once
        timer.setRepeats(false);
        timer.start();
    }

    // Effects: creates a window
    private void extracted3(JFrame timedWindow, JPanel panel, JPanel panelImage) {
        timedWindow.add(panel, BorderLayout.CENTER);
        timedWindow.add(panelImage, BorderLayout.SOUTH);
    }

    // Effects: creates a window
    private Timer getTimer(Timer mainTimer, JFrame timedWindow) {
        // Set up a Timer to close the window after 5 seconds
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                timedWindow.dispose(); // Close the window
                mainTimer.start();
            }
        });
        return timer;
    }

    // Effects: creates a window
    private void extracted2(JLabel happyLevel, JLabel cleanLevel, JLabel fullness, JLabel health, JLabel image,
            JPanel panel, JPanel panelImage) {
        panel.add(happyLevel);
        panel.add(cleanLevel);
        panel.add(fullness);
        panel.add(health);
        panelImage.add(image);
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
    private JLabel getI() {
        JLabel image = new JLabel();
        image.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        ImageIcon i = new ImageIcon(new ImageIcon("src/main/Image/StatusWindowImage/happyPet.png").getImage()
                .getScaledInstance(400, 300, Image.SCALE_SMOOTH));
        image.setIcon(i);
        return image;
    }

    // Effects: creates a window
    private JLabel getHealth(String updatedHealth) {
        JLabel health = new JLabel("Health Status: " + updatedHealth);
        health.setFont(new Font("Arial", Font.PLAIN, 15));
        health.setAlignmentX(Component.CENTER_ALIGNMENT);
        return health;
    }

    // Effects: creates a window
    private JLabel getFullness(String updatedFullness) {
        JLabel fullness = new JLabel("Fullness: " + updatedFullness);
        fullness.setFont(new Font("Arial", Font.PLAIN, 15));
        fullness.setAlignmentX(Component.CENTER_ALIGNMENT);
        return fullness;
    }

    // Effects: creates a window
    private JLabel getCleanLevel(String updatedCleaness) {
        JLabel cleanLevel = new JLabel("Cleanness: " + updatedCleaness);
        cleanLevel.setFont(new Font("Arial", Font.PLAIN, 15));
        cleanLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return cleanLevel;
    }

    // Effects: creates a window
    private JLabel getHappyLevel(String updatedHappyLevel) {
        JLabel happyLevel = new JLabel("HappyLevel: " + updatedHappyLevel);
        happyLevel.setFont(new Font("Arial", Font.PLAIN, 15));
        happyLevel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        happyLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return happyLevel;
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
        JFrame timedWindow = new JFrame("Levels Window");
        timedWindow.setSize(700, 450);
        timedWindow.setLocationRelativeTo(null);
        timedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return timedWindow;
    }

}
