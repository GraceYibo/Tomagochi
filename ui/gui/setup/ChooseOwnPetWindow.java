package ui.gui.setup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.AllPets;

public class ChooseOwnPetWindow {

    private JFrame frame;
    private AllPets pets;

    /**
     * Requires: `pets` must be a non-null instance of AllPets.
     * Modifies: None.
     * Effects:  Initializes the window for the user to choose their own pet.
     *
     * @param pets the AllPets instance managing the user's pets
     */
    public ChooseOwnPetWindow(AllPets pets) {
        this.pets = pets;
        initialize(pets);
    }

    /**
     * Requires: `pets` must be a non-null instance of AllPets.
     * Modifies: Initializes and adds components to the GUI window.
     * Effects:  Creates a GUI for users to choose their pet, including an egg image and a text input.
     *
     * @param pets the AllPets instance
     */
    private void initialize(AllPets pets) {
        extracted();

        JPanel pannelCenter = getPanelCenter();
        JPanel panelText = getPanelText();

        getTextField(panelText);
        getEgg(pannelCenter);

        JLabel text = new JLabel("Are you ready to raise a pet? Give your pet a name: ", SwingConstants.CENTER);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        pannelCenter.add(text);

        this.frame.add(pannelCenter, BorderLayout.CENTER);
        this.frame.add(panelText, BorderLayout.SOUTH);
    }

    /**
     * Requires: None.
     * Modifies: None.
     * Effects:  Adds an egg image to the specified panel.
     *
     * @param panelCenter the panel where the egg image will be added
     */
    private void getEgg(JPanel panelCenter) {
        JLabel label = new JLabel();
        ImageIcon egg = new ImageIcon(new ImageIcon("src/main/Image/BeginningWindow/egg.jpg")
                .getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        label.setIcon(egg);
        panelCenter.add(label);
    }

    /**
     * Requires: None.
     * Modifies: Adds a text field to the specified panel.
     * Effects:  Allows the user to input a pet name via the text field.
     *
     * @param panelText the panel where the text field will be added
     */
    private void getTextField(JPanel panelText) {
        JTextField textField = createJTextField();
        panelText.add(textField);
    }

    /**
     * Requires: None.
     * Modifies: None.
     * Effects:  Creates and returns a new panel for text input.
     *
     * @return a JPanel for text input
     */
    private JPanel getPanelText() {
        JPanel panelText = new JPanel();
        panelText.setBackground(Color.WHITE);
        return panelText;
    }

    /**
     * Requires: None.
     * Modifies: None.
     * Effects:  Creates and returns a new panel for the main content.
     *
     * @return a JPanel for the main content
     */
    private JPanel getPanelCenter() {
        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        return panelCenter;
    }

    /**
     * Requires: None.
     * Modifies: Initializes the JFrame for the application.
     * Effects:  Creates and configures the main application window.
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

    /**
     * Requires: None.
     * Modifies: Creates and adds an ActionListener to a JTextField.
     * Effects:  Creates a text field for the user to input their pet's name. 
     *           Transitions to the GenderWindow when the user presses Enter.
     *
     * @return a JTextField with an attached ActionListener
     */
    public JTextField createJTextField() {
        JTextField textField = new JTextField(50);
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new GenderWindow(textField.getText(), pets); // Transition to GenderWindow with the input text
                frame.dispose(); // Close the current window
            }
        });
        return textField;
    }

}
