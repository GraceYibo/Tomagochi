package ui.gui.mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Pet;

// Effects: creates a window
public class MedicationWindow {

    // Effects: creates a window
    public MedicationWindow(Pet pet, Timer mainTimer) {

        initilize(pet, mainTimer);
    }

    // Effects: creates a window
    public void initilize(Pet pet, Timer mainTimer) {
        JFrame timedWindow = new JFrame("Medication Window");
        timedWindow.setSize(700, 450);
        timedWindow.setLocationRelativeTo(null);
        timedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        timedWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Dispose the window
                timedWindow.dispose();

                // Start the timer
                mainTimer.start();
            }
        });

        heal(pet, timedWindow);

        // Show the new window
        timedWindow.setVisible(true);

        // Set up a Timer to close the window after 2 seconds
        Timer timer = new Timer(4000, e -> {
            timedWindow.dispose();
            mainTimer.start();
        });

        // Make sure the timer only runs once
        timer.setRepeats(false);
        timer.start();
    }

    // Effects: creates a window
    public void heal(Pet pet, JFrame timedWindow) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.WHITE);
        if (pet.getHealth()) {
            JLabel label = new JLabel("I am already healthy!");
            ImageIcon notEat = new ImageIcon(new ImageIcon("src/main/Image/MedicationWindow/healthyPet.png").getImage()
                    .getScaledInstance(350, 300, Image.SCALE_SMOOTH));
            label.setIcon(notEat);
            panel.add(label);
            timedWindow.add(panel, BorderLayout.CENTER);

        } else {
            JLabel label = new JLabel("Thank you for treating me, I feel much better.");
            pet.treat();
            ImageIcon notEat = new ImageIcon(new ImageIcon("src/main/Image/MedicationWindow/treatPet.png").getImage()
                    .getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            label.setIcon(notEat);
            panel.add(label);
            timedWindow.add(panel, BorderLayout.CENTER);

        }
    }

}
