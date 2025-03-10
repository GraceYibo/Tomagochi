package ui.gui.mainwindow.search;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.AllPets;
import model.Pet;

/**
 * Represents a search window to allow the user to search for pets by name, age, or status.
 */
public class SearchPetWindow {
    private JFrame frame;

    /**
     * Constructs a SearchPetWindow and initializes its components.
     * 
     * @requires pets != null, pet != null, mainTimer != null
     * @modifies this
     * @effects creates and displays the main search window
     */
    public SearchPetWindow(AllPets pets, Pet pet, Timer mainTimer) {
        initialize(pets, pet, mainTimer);
    }

    /**
     * Initializes the components of the search window.
     * 
     * @requires pets != null, pet != null, mainTimer != null
     * @modifies this
     * @effects sets up the main frame and its panels
     */
    private void initialize(AllPets pets, Pet pet, Timer mainTimer) {
        frame = new JFrame("Search Pet");
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupWindowCloseAction(mainTimer);

        frame.setLayout(new BorderLayout());
        frame.add(createTopPanel(), BorderLayout.NORTH);
        frame.add(createCenterPanel(), BorderLayout.CENTER);
        frame.add(createBottomPanel(pets, pet, mainTimer), BorderLayout.SOUTH);
        frame.setAlwaysOnTop(true);

        frame.setVisible(true);
    }

    /**
     * Sets up the action to restart the main timer when the window is closed.
     * 
     * @requires mainTimer != null
     * @modifies mainTimer
     * @effects ensures the mainTimer restarts when the window closes
     */
    private void setupWindowCloseAction(Timer mainTimer) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                mainTimer.start();
            }
        });
    }

    /**
     * Creates the top panel with instructions for the user.
     * 
     * @requires none
     * @modifies none
     * @effects returns a panel with instructional text
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);

        JLabel prompt = new JLabel("Choose the information you want to search by:", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 17));
        topPanel.add(prompt);

        return topPanel;
    }

    /**
     * Creates the center panel displaying an image.
     * 
     * @requires none
     * @modifies none
     * @effects returns a panel containing a scaled image
     */
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel();
        ImageIcon searchIcon = new ImageIcon(
                new ImageIcon("src/main/Image/SearchWindow/searchWindow.png")
                        .getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH));
        label.setIcon(searchIcon);
        centerPanel.add(label);

        return centerPanel;
    }

    /**
     * Creates the bottom panel with buttons for search options.
     * 
     * @requires pets != null, pet != null, mainTimer != null
     * @modifies none
     * @effects returns a panel with buttons to open specific search windows
     */
    private JPanel createBottomPanel(AllPets pets, Pet pet, Timer mainTimer) {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        bottomPanel.add(createButton("Name", e -> openSearchByNameWindow(pets, pet, mainTimer)));
        bottomPanel.add(createButton("Age", e -> openSearchByAgeWindow(pets, pet, mainTimer)));
        bottomPanel.add(createButton("Status", e -> openSearchByStatusWindow(pets, pet, mainTimer)));

        return bottomPanel;
    }

    /**
     * Creates a button with the specified text and action.
     * 
     * @requires text != null, action != null
     * @modifies none
     * @effects returns a JButton with the given label and action listener
     */
    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    /**
     * Opens a window to search for pets by name.
     * 
     * @requires pets != null, pet != null, mainTimer != null
     * @modifies mainTimer
     * @effects opens a text-input window for name-based search
     */
    private void openSearchByNameWindow(AllPets pets, Pet pet, Timer mainTimer) {
        openSearchWindow("Search Pet By Name", "Enter the name of the pet:", pets, pet, mainTimer,
                input -> new SearchByNameWindow(pets, pet, input, mainTimer));
    }

    /**
     * Opens a window to search for pets by age.
     * 
     * @requires pets != null, pet != null, mainTimer != null
     * @modifies mainTimer
     * @effects opens a text-input window for age-based search
     */
    private void openSearchByAgeWindow(AllPets pets, Pet pet, Timer mainTimer) {
        openSearchWindow("Search Pet By Age", "Enter the age of the pet:", pets, pet, mainTimer,
                input -> new SearchByAgeWindow(pets, pet, input, mainTimer));
    }

    /**
     * Opens a window to search for pets by status.
     * 
     * @requires pets != null, pet != null, mainTimer != null
     * @modifies mainTimer
     * @effects opens a new window with buttons for specific statuses
     */
    private void openSearchByStatusWindow(AllPets pets, Pet pet, Timer mainTimer) {
        frame.dispose();

        JFrame statusFrame = createFrame("Search Pet By Status");
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(createButton("Alive", e -> new SearchByStatusWindow(pets, pet, "alive", mainTimer)));
        bottomPanel.add(createButton("Married", e -> new SearchByStatusWindow(pets, pet, "married", mainTimer)));
        bottomPanel.add(createButton("Died", e -> new SearchByStatusWindow(pets, pet, "died", mainTimer)));

        statusFrame.add(createQuestionPanel("Choose the status of the pet:"), BorderLayout.NORTH);
        statusFrame.add(createCenterPanel(), BorderLayout.CENTER);
        statusFrame.add(bottomPanel, BorderLayout.SOUTH);
        statusFrame.setAlwaysOnTop(true);
        statusFrame.setVisible(true);
    }

    /**
     * Opens a generic search window with a text field for user input.
     * 
     * @requires title != null, prompt != null, pets != null, pet != null, mainTimer != null
     * @modifies mainTimer
     * @effects opens a text-input window for searching
     */
    private void openSearchWindow(String title, String prompt, AllPets pets, Pet pet, Timer mainTimer,
            java.util.function.Consumer<String> searchAction) {
        frame.dispose();

        JFrame searchFrame = createFrame(title);
        JPanel bottomPanel = new JPanel();

        JTextField textField = new JTextField(30);
        textField.addActionListener(e -> {
            searchAction.accept(textField.getText());
            searchFrame.dispose();
        });

        bottomPanel.add(textField);
        searchFrame.add(createQuestionPanel(prompt), BorderLayout.NORTH);
        searchFrame.add(createCenterPanel(), BorderLayout.CENTER);
        searchFrame.add(bottomPanel, BorderLayout.SOUTH);
        searchFrame.setVisible(true);
        searchFrame.setAlwaysOnTop(true);
    }

    /**
     * Creates a new frame with the given title.
     * 
     * @requires title != null
     * @modifies none
     * @effects returns a configured JFrame
     */
    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    /**
     * Creates a panel containing a question label.
     * 
     * @requires question != null
     * @modifies none
     * @effects returns a panel with the given question label
     */
    private JPanel createQuestionPanel(String question) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(question, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(label);

        return panel;
    }
}
