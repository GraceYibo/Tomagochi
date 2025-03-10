package ui.gui.mainwindow;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.*;

import model.AllPets;
import model.Pet;
import model.Status;
import ui.gui.mainwindow.helper.MarryWindow;
import ui.gui.mainwindow.helper.Update;
import ui.gui.mainwindow.search.SearchPetWindow;
import ui.gui.mainwindow.view.SaveGameWindow;
import ui.gui.mainwindow.view.ViewAllPetWindow;
import ui.gui.setup.ChooseOwnPetWindow;

// Effects: creates a window
public class MainWindow {

    private JFrame frame;
    private Timer mainTimer;
    private JLabel label;

    // Effects: creates a window
    public MainWindow(AllPets pets, String imagePath, Pet pet) {
        Random r = new Random();
        int low = 6;
        int high = 7;
        int age = r.nextInt(high - low) + low;

        initilize(pets, imagePath, pet);
        startMainTimer(pets, pet, age);
    }

    // Effects: creates a window
    private void initilize(AllPets pets, String imagePath, Pet pet) {
        extracted();

        extracted2(pets, pet);

        JPanel pannelTop = getPannelTop();

        JPanel pannelBottom = getPannelBottom();

        JPanel pannelCenter = getPannelCenter();

        getPetImage(imagePath, pannelCenter);

        JButton feed = getFeedim(pet);

        JButton clean = getCleanim(pet);

        JButton play = getPlayim(pet);

        JButton med = getMedim(pet);

        JButton cs = getCsim(pet);

        JButton vtp = getVtpim(pet);

        JButton vap = getVapim(pets, pet);

        JButton search = getSearchim(pets, pet);

        JButton save = getSaveim(pets, pet);

        JButton quit = getQuitim(pets, pet);

        extracted3(pannelTop, pannelBottom, pannelCenter, feed, clean, play, med, cs, vtp, vap, search, save, quit);

    }

    // Effects: creates a window
    private void extracted3(JPanel pannelTop, JPanel pannelBottom, JPanel pannelCenter, JButton feed, JButton clean,
            JButton play, JButton med, JButton cs, JButton vtp, JButton vap, JButton search, JButton save,
            JButton quit) {
        pannelTop.add(feed);
        pannelTop.add(clean);
        pannelTop.add(play);
        pannelTop.add(med);
        pannelTop.add(cs);
        pannelBottom.add(vtp);
        pannelBottom.add(vap);
        pannelBottom.add(search);
        pannelBottom.add(save);
        pannelBottom.add(quit);

        this.frame.add(pannelTop, BorderLayout.NORTH);
        this.frame.add(pannelBottom, BorderLayout.SOUTH);
        this.frame.add(pannelCenter, BorderLayout.CENTER);
    }

    // Effects: creates a window
    private JButton getQuitim(AllPets pets, Pet pet) {
        JButton quit = new JButton("Quit Game");
        quit.setSize(30, 50);
        ImageIcon quitim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/quit.png").getImage().getScaledInstance(50, 50, 50));
        quit.setIcon(quitim);
        quit.setToolTipText("Quit the game. All progress will be deleted unless you saved the game");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainTimer.stop();
                new QuitWindow(pets, pet);
                frame.dispose();
            }
        });
        return quit;
    }

    // Effects: creates a window
    private JButton getSaveim(AllPets pets, Pet pet) {
        JButton save = new JButton("Save Game");
        save.setSize(30, 50);
        ImageIcon saveim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/save.png").getImage().getScaledInstance(50, 50, 50));
        save.setIcon(saveim);
        save.setToolTipText("Save the pets you have raise to the game");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SaveGameWindow(pets, pet, mainTimer);
                mainTimer.stop();
            }
        });
        return save;
    }

    // Effects: creates a window
    private JButton getSearchim(AllPets pets, Pet pet) {
        JButton search = new JButton("Search Pet");
        search.setSize(30, 50);
        ImageIcon searchim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/searchPet.png").getImage().getScaledInstance(50, 50, 50));
        search.setIcon(searchim);
        search.setToolTipText("Search for a precious pet that you have raised.");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchPetWindow(pets, pet, mainTimer);
                mainTimer.stop();
            }
        });
        return search;
    }

    // Effects: creates a window
    private JButton getVapim(AllPets pets, Pet pet) {
        JButton vap = new JButton("View all pets");
        vap.setSize(30, 50);
        ImageIcon vapim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/vap.jpg").getImage().getScaledInstance(50, 50, 50));
        vap.setIcon(vapim);
        vap.setToolTipText("View all the pets you have raise so far");
        vap.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewAllPetWindow(pet, pets, frame, mainTimer);
                mainTimer.stop();
            }
        });
        return vap;
    }

    // Effects: creates a window
    private JButton getVtpim(Pet pet) {
        JButton vtp = new JButton("View this pet");
        vtp.setSize(30, 50);
        ImageIcon vtpim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/vtp.png").getImage().getScaledInstance(50, 50, 50));
        vtp.setIcon(vtpim);
        vtp.setToolTipText("View the name, age and status of the pet");
        vtp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPetWindow(pet, mainTimer);
                mainTimer.stop();
            }
        });
        return vtp;
    }

    // Effects: creates a window
    private JButton getCsim(Pet pet) {
        JButton cs = new JButton("Check Status");
        cs.setSize(30, 50);
        ImageIcon csim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/cs.jpg").getImage().getScaledInstance(50, 50, 50));
        cs.setIcon(csim);
        cs.setToolTipText("Check the current levels of the pet");
        cs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatusWindow(pet, mainTimer);
                mainTimer.stop();
            }
        });
        return cs;
    }

    // Effects: creates a window
    private JButton getMedim(Pet pet) {
        JButton med = new JButton("Medication");
        med.setSize(30, 50);
        ImageIcon medim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/heal.jpg").getImage().getScaledInstance(50, 50, 50));
        med.setIcon(medim);
        med.setToolTipText("Heal the pet");
        med.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MedicationWindow(pet, mainTimer);
                mainTimer.stop();
            }
        });
        return med;
    }

    // Effects: creates a window
    private JButton getPlayim(Pet pet) {
        JButton play = new JButton("Play");
        play.setSize(30, 50);
        ImageIcon playim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/play.jpg").getImage().getScaledInstance(50, 50, 50));
        play.setIcon(playim);
        play.setToolTipText("Play with the pet");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayWindow(pet, mainTimer);
                mainTimer.stop();
            }
        });
        return play;
    }

    // Effects: creates a window
    private JButton getCleanim(Pet pet) {
        JButton clean = new JButton("Clean");
        clean.setSize(50, 50);
        ImageIcon cleanim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/clean.jpg").getImage().getScaledInstance(50, 50, 50));
        clean.setIcon(cleanim);
        clean.setToolTipText("Clean the pet");
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CleanWindow(pet, mainTimer);
                mainTimer.stop();
            }
        });
        return clean;
    }

    // Effects: creates a window
    private JButton getFeedim(Pet pet) {
        JButton feed = new JButton("Feed");
        feed.setSize(50, 50);
        ImageIcon feedim = new ImageIcon(
                new ImageIcon("src/main/Image/MainWindow/feed.jpg").getImage().getScaledInstance(50, 50, 50));
        feed.setIcon(feedim);
        feed.setToolTipText("Feed the pet");
        feed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FeedWindow(pet, mainTimer);
                mainTimer.stop();
            }
        });
        return feed;
    }

    // Effects: creates a window
    private JPanel getPannelCenter() {
        JPanel pannelCenter = new JPanel();
        pannelCenter.setBackground(Color.WHITE);
        return pannelCenter;
    }

    // Effects: creates a window
    private JPanel getPannelBottom() {
        JPanel pannelBottom = new JPanel();
        pannelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pannelBottom.setBackground(Color.PINK);
        return pannelBottom;
    }

    // Effects: creates a window
    private JPanel getPannelTop() {
        JPanel pannelTop = new JPanel();
        pannelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pannelTop.setBackground(Color.PINK);
        return pannelTop;
    }

    // Effects: creates a window
    private void getPetImage(String imagePath, JPanel pannelCenter) {
        label = new JLabel();
        ImageIcon petImage = new ImageIcon(
                new ImageIcon(imagePath).getImage().getScaledInstance(250, 250, 250));

        label.setIcon(petImage);
        pannelCenter.add(label);
    }

    // Effects: creates a window
    private void extracted2(AllPets pets, Pet pet) {
        // Add a WindowListener to handle the close action
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                new AccidentlyQuitWindow(pets, pet, mainTimer);
                mainTimer.stop();
            }
        });
    }

    // Effects: creates a window
    private void extracted() {
        frame = new JFrame();
        this.frame.setLayout(new BorderLayout(10, 5));
        this.frame.setTitle("Tomagochi");
        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.frame.setSize(700, 450);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(true);
        this.frame.setVisible(true);
    }

    // Effects: creates a window
    private void startMainTimer(AllPets pets, Pet pet, int age) {
        if (mainTimer != null && mainTimer.isRunning()) {
            mainTimer.stop();
        }

        mainTimer = new Timer(20000, e -> {
            if (pet.getStatus() == Status.died || pet.getAge() == age) {
                mainTimer.stop();
                return;
            }
            updateWindow(pets, pet, age);
        });

        mainTimer.setRepeats(false);
        mainTimer.start();
    }

    // Effects: creates a window
    private void updateWindow(AllPets pets, Pet pet, int age) {
        new Update(pet);
        if (marrie(pets, pet, age) || died(pets, pet) || oldDied(pets, pet, age)) {
            return;
        }

        JFrame cardFrame = getCardFrame();

        // CardLayout reference
        CardLayout cardLayout = (CardLayout) cardFrame.getContentPane().getLayout();

        JPanel updatePanel = getUpdateLabel();

        // Create the status panel
        JPanel statusPanel = getStatusPanel();

        JLabel happyLevel = getHappyLevel(pet);

        JLabel cleanLevel = getCleanLevel(pet);

        JLabel fullness = getFullness(pet);

        JLabel health = getHealth(pet);

        JLabel image = getIcon();

        extracted4(statusPanel, happyLevel, cleanLevel, fullness, health, image);

        extracted5(cardFrame, updatePanel, statusPanel);

        // Show the frame
        cardFrame.setVisible(true);

        getSwitchToStatusTimer2(pets, pet, age, cardFrame, cardLayout);
    }

    // Effects: creates a window
    private void getSwitchToStatusTimer2(AllPets pets, Pet pet, int age, JFrame cardFrame, CardLayout cardLayout) {
        // Timer to switch to the status panel
        Timer switchToStatusTimer = getSwitchToStatusTimer(pets, pet, age, cardFrame, cardLayout);
        switchToStatusTimer.setRepeats(false);
        switchToStatusTimer.start();
    }

    // Effects: creates a window
    private Timer getSwitchToStatusTimer(AllPets pets, Pet pet, int age, JFrame cardFrame, CardLayout cardLayout) {
        Timer switchToStatusTimer = new Timer(2500, e -> {
            cardLayout.show(cardFrame.getContentPane(), "Status"); // Switch to the "Status" card

            // Timer to close the window and restart the main timer
            Timer closeWindowTimer = new Timer(6500, evt -> {
                cardFrame.dispose(); // Close the card window
                startMainTimer(pets, pet, age); // Restart the main timer
            });
            closeWindowTimer.setRepeats(false);
            closeWindowTimer.start();
        });
        return switchToStatusTimer;
    }

    // Effects: creates a window
    private void extracted5(JFrame cardFrame, JPanel updatePanel, JPanel statusPanel) {
        // Add panels to the CardLayout
        cardFrame.add(updatePanel, "Update");
        cardFrame.add(statusPanel, "Status");
    }

    // Effects: creates a window
    private void extracted4(JPanel statusPanel, JLabel happyLevel, JLabel cleanLevel, JLabel fullness, JLabel health,
            JLabel image) {
        statusPanel.add(happyLevel);
        statusPanel.add(cleanLevel);
        statusPanel.add(fullness);
        statusPanel.add(health);
        statusPanel.add(image);
    }

    // Effects: creates a window
    private JLabel getIcon() {
        JLabel image = new JLabel();
        image.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        ImageIcon icon = new ImageIcon(new ImageIcon("src/main/Image/StatusWindowImage/happyPet.png").getImage()
                .getScaledInstance(400, 300, Image.SCALE_SMOOTH));
        image.setIcon(icon);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        return image;
    }

    // Effects: creates a window
    private JLabel getHealth(Pet pet) {
        JLabel health = new JLabel("Health Status: " + pet.getHealth());
        health.setFont(new Font("Arial", Font.PLAIN, 15));
        health.setAlignmentX(Component.CENTER_ALIGNMENT);
        return health;
    }

    // Effects: creates a window
    private JLabel getFullness(Pet pet) {
        JLabel fullness = new JLabel("Fullness: " + pet.getFullness());
        fullness.setFont(new Font("Arial", Font.PLAIN, 15));
        fullness.setAlignmentX(Component.CENTER_ALIGNMENT);
        return fullness;
    }

    // Effects: creates a window
    private JLabel getCleanLevel(Pet pet) {
        JLabel cleanLevel = new JLabel("Cleanliness: " + pet.getCleanessLevel());
        cleanLevel.setFont(new Font("Arial", Font.PLAIN, 15));
        cleanLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return cleanLevel;
    }

    // Effects: creates a window
    private JLabel getHappyLevel(Pet pet) {
        JLabel happyLevel = new JLabel("Happy Level: " + pet.getHappyLevel());
        happyLevel.setFont(new Font("Arial", Font.PLAIN, 15));
        happyLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        happyLevel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return happyLevel;
    }

    // Effects: creates a window
    private JPanel getStatusPanel() {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBackground(Color.WHITE);
        return statusPanel;
    }

    // Effects: creates a window
    private JPanel getUpdateLabel() {
        // Create the update panel
        JPanel updatePanel = new JPanel(new BorderLayout());
        JLabel updateLabel = new JLabel("The next day...", SwingConstants.CENTER);
        updatePanel.add(updateLabel, BorderLayout.CENTER);
        return updatePanel;
    }

    // Effects: creates a window
    private JFrame getCardFrame() {
        // Create the main frame with CardLayout
        JFrame cardFrame = new JFrame("Pet Status");
        cardFrame.setSize(700, 450);
        cardFrame.setLocationRelativeTo(frame);
        cardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cardFrame.setLayout(new CardLayout()); // Set CardLayout for the frame
        return cardFrame;
    }

    // Effects: creates a window
    public boolean marrie(AllPets pets, Pet pet, int age) {
        if (pet.getAge() == 5) {
            MarryWindow marryWindow = new MarryWindow(pets, pet, frame, age);
            return marryWindow.getUserDecision();
        }
        return false;
    }

    // Effects: creates a window
    public boolean died(AllPets pets, Pet pet) {
        if (pet.getStatus() == Status.died) {
            frame.dispose();
            JFrame deathWindow = getDeathWindow();

            JLabel message = new JLabel("You didn't take care of your pet, and it died!", SwingConstants.CENTER);
            message.setFont(new Font("Arial", Font.PLAIN, 18));
            deathWindow.add(message, BorderLayout.CENTER);

            // Show the window
            deathWindow.setVisible(true);

            // Step 2: Use a Timer to close the death window after 3 seconds and then open
            // the next window
            Timer deathTimer = new Timer(4000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deathWindow.dispose(); // Close the death window
                    new ChooseOwnPetWindow(pets);
                }
            });
            deathTimer.setRepeats(false); // Ensure the timer runs only once
            deathTimer.start();
            return true;
        }
        return false;
    }

    // Effects: creates a window
    public boolean oldDied(AllPets pets, Pet pet, int age) {
        if (pet.getAge() == age) {
            frame.dispose();
            JFrame deathWindow = getDeathWindow();

            getMessage(pet, deathWindow);

            // Show the window
            deathWindow.setVisible(true);

            Timer deathTimer = getDeathTimer(pets, deathWindow);
            deathTimer.setRepeats(false); // Ensure the timer runs only once
            deathTimer.start();
            return true;
        }
        return false;
    }

    // Effects: creates a window
    private Timer getDeathTimer(AllPets pets, JFrame deathWindow) {
        // Step 2: Use a Timer to close the death window after 3 seconds and then open
        // the next window
        Timer deathTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deathWindow.dispose(); // Close the death window
                new ChooseOwnPetWindow(pets);
            }
        });
        return deathTimer;
    }

    // Effects: creates a window
    private void getMessage(Pet pet, JFrame deathWindow) {
        String ageString = String.valueOf(pet.getAge());
        JLabel message = new JLabel("Your pet lived a happy life. It died at the age of " + ageString + ".",
                SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.PLAIN, 18));
        deathWindow.add(message, BorderLayout.CENTER);
    }

    // Effects: creates a window
    private JFrame getDeathWindow() {
        // Step 1: Create the "You didn't take care of your pet, and it died!" window
        JFrame deathWindow = new JFrame("Pet Death");
        deathWindow.setSize(400, 200);
        deathWindow.setLocationRelativeTo(null); // Center the window on the screen
        deathWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return deathWindow;
    }

}
