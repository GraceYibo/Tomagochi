package ui.gui.mainwindow.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import model.AllPets;
import model.Pet;

public class ViewAllPetWindow {

    // Effects: creates a window
    public ViewAllPetWindow(Pet pet, AllPets pets, JFrame frame, Timer mainTimer) {
        initilize(pet, pets, frame, mainTimer);
    }

    // Effects: creates a window
    public JFrame initilize(Pet pet, AllPets pets, JFrame frame1, Timer mainTimer) {

        JFrame timedWindow = getTimedWindow();
        extracted(mainTimer, timedWindow);
        JPanel panel = getPanel();
        JPanel topPanel = getTopPanel();
        JPanel bottomPanel = getBottomPanel();
        getPrompt(pets, panel, topPanel);
        getQuestion(bottomPanel);
        getYes(pet, pets, frame1, mainTimer, timedWindow, bottomPanel);
        getNo(mainTimer, timedWindow, bottomPanel);
        extracted3(timedWindow, panel, topPanel, bottomPanel);
        return timedWindow;
    }

    private void getYes(Pet pet, AllPets pets, JFrame frame1, Timer mainTimer, JFrame timedWindow, JPanel bottomPanel) {
        JButton yes = new JButton("Yes");
        bottomPanel.add(yes);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current window
                timedWindow.dispose();

                // Create the new frame for removing the pet
                JFrame frame = getFrame(timedWindow);
                frame.setAlwaysOnTop(true);

                // Add a window listener to dispose of the frame and restart the timer when it's
                // closed
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.dispose();
                        mainTimer.start();
                    }
                });

                // Create a new panel for entering pet name
                JPanel panneText = getPanneText();
                getTextField(pet, pets, frame1, mainTimer, frame, panneText);

                // Create a question panel for user input
                JPanel quetionPanel = getQuetionPanel();
                getLabel(quetionPanel);

                // Add the panels to the frame and make it visible
                extracted(frame, panneText, quetionPanel);
                frame.setVisible(true); // Make sure the new frame is visible
            }
        });
    }

    // Effects: creates a window
    private void getNo(Timer mainTimer, JFrame timedWindow, JPanel bottomPanel) {
        JButton no = new JButton("No");
        bottomPanel.add(no);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timedWindow.dispose();
                mainTimer.start();
            }
        });
    }

    // Effects: creates a window
    private void extracted3(JFrame timedWindow, JPanel panel, JPanel topPanel, JPanel bottomPanel) {
        timedWindow.add(topPanel, BorderLayout.NORTH);
        timedWindow.add(panel, BorderLayout.CENTER);
        timedWindow.add(bottomPanel, BorderLayout.SOUTH);
        // Show the new window
        timedWindow.setVisible(true);
    }

    // Effects: creates a window
    private JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        return bottomPanel;
    }

    // Effects: creates a window
    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        return topPanel;
    }

    // Effects: creates a window
    private JFrame getTimedWindow() {
        JFrame timedWindow = new JFrame("View All Pets Window");
        timedWindow.setSize(700, 450);
        timedWindow.setLocationRelativeTo(null);
        timedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return timedWindow;
    }

    // Effects: creates a window
    private JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    // Effects: creates a window
    private void getPrompt(AllPets pets, JPanel panel, JPanel topPanel) {
        JLabel prompt = new JLabel("Below is all the pets you have raised till now:", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 17));
        topPanel.add(prompt);
        extracted2(pets, panel);
    }

    // Effects: creates a window
    private void getQuestion(JPanel bottomPanel) {
        JLabel question = new JLabel("Do you want to remove any Pet?", SwingConstants.CENTER);
        question.setFont(new Font("Arial", Font.PLAIN, 17));
        bottomPanel.add(question);
    }

    // Effects: creates a window
    private void extracted2(AllPets pets, JPanel panel) {
        for (int i = 0; i < pets.getPets().size(); i++) {
            Pet p = pets.getPets().get(i);
            JLabel label = new JLabel("Name: " + p.getName() + ", Age: " + p.getAge() + ", Gender: "
                    + p.getGender() + ", Status: " + p.getStatus() + ".");
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(label);
        }
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
    private void extracted(JFrame frame, JPanel panneText, JPanel quetionPanel) {
        frame.add(quetionPanel, BorderLayout.NORTH);
        frame.add(panneText, BorderLayout.SOUTH);
        // Show the new window
        frame.setVisible(true);
    }

    // Effects: creates a window
    private JPanel getQuetionPanel() {
        JPanel quetionPanel = new JPanel();
        quetionPanel.setBackground(Color.WHITE);
        return quetionPanel;
    }

    // Effects: creates a window
    private JPanel getPanneText() {
        JPanel panneText = new JPanel();
        panneText.setBackground(Color.WHITE);
        return panneText;
    }

    // Effects: creates a window
    private void getLabel(JPanel quetionPanel) {
        JLabel label = new JLabel("Enter the name of the pet that you would like to remove:",
                SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        quetionPanel.add(label);
    }

    // Effects: creates a window
    private JFrame getFrame(JFrame timedWindow) {
        // Create the new window
        timedWindow.dispose();
        JFrame frame = new JFrame("Remove Pet Window");
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    // Effects: creates a window
    private void getTextField(Pet pet, AllPets pets, JFrame frame1, Timer mainTimer, JFrame frame,
            JPanel panneText) {
        JTextField textField = new JTextField(50);
        panneText.add(textField);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemovePetWindow(pets, pet, textField.getText(), frame1, mainTimer);
                frame.dispose();
            }
        });
    }
}