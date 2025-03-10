package ui.gui.mainwindow.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import model.AllPets;
import model.Pet;
import ui.gui.setup.ChooseOwnPetWindow;

public class RemovePetWindow {
    private int num;

    // Effects: creates a window
    public RemovePetWindow(AllPets pets, Pet currentPet, String name, JFrame frame, Timer mainTimer) {
        initialize(currentPet, pets, name, frame, mainTimer);
        num = 0;
    }

    // Effects: creates a window
    private void initialize(Pet currentPet, AllPets pets, String name, JFrame frame, Timer mainTimer) {
        JFrame timedWindow = new JFrame("Remove Pet Window");
        timedWindow.setSize(700, 450);
        timedWindow.setLocationRelativeTo(null);
        timedWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timedWindow.setAlwaysOnTop(true);

        timedWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainTimer.start();
            }
        });

        extracted(currentPet, pets, name, frame, mainTimer, timedWindow);
    }

    // Effects: creates a window
    private void extracted(Pet currentPet, AllPets pets, String name, JFrame frame, Timer mainTimer,
            JFrame timedWindow) {
        for (Pet pet : pets.getPets()) {
            if (pet.getName().equals(name)) {
                num++;
            }
        }

        if (num == 0) {
            JOptionPane.showMessageDialog(
                    timedWindow,
                    "No pet with the name " + name + ".\nRemove unsuccessful.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            mainTimer.start();
        } else if (num > 1) {
            chooseDuplicatedNumber(name, pets, currentPet, timedWindow, frame, mainTimer);
        } else {
            removeSinglePet(currentPet, name, pets, timedWindow, frame, mainTimer);
        }
    }

    // Effects: creates a window
    private void chooseDuplicatedNumber(String name, AllPets pets, Pet currentPet, JFrame parent, JFrame frame,
            Timer mainTimer) {
        StringBuilder message = new StringBuilder("You have multiple pets named " + name + ":\n");
        int index = 1;

        extracted2(name, pets, message, index);

        String choice = getChoice(parent, message);

        if (choice != null) {
            try {
                int selectedIndex = Integer.parseInt(choice) - 1;
                if (selectedIndex >= 0 && selectedIndex < num) {
                    Pet selectedPet = getSelectedPet(pets, selectedIndex);

                    if (selectedPet.equals(currentPet)) {
                        handleCurrentPetRemoval(pets, frame);
                    } else {
                        extracted4(parent, mainTimer);
                    }
                } else {
                    extracted3(parent, mainTimer);
                }
            } catch (NumberFormatException e) {
                extracted5(parent);
                mainTimer.start();
            }
        }
    }

    // Effects: creates a window
    private void extracted5(JFrame parent) {
        JOptionPane.showMessageDialog(
                parent,
                "Invalid input. Please enter a valid number.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Effects: creates a window
    private Pet getSelectedPet(AllPets pets, int selectedIndex) {
        Pet selectedPet = pets.getPets().get(selectedIndex);
        pets.removePet(selectedPet);
        return selectedPet;
    }

    // Effects: creates a window
    private void extracted4(JFrame parent, Timer mainTimer) {
        JOptionPane.showMessageDialog(
                parent,
                "The selected pet has been successfully removed.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        mainTimer.start();
    }

    // Effects: creates a window
    private void extracted3(JFrame parent, Timer mainTimer) {
        JOptionPane.showMessageDialog(
                parent,
                "Invalid number. Operation canceled.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        mainTimer.start();
    }

    // Effects: creates a window
    private String getChoice(JFrame parent, StringBuilder message) {
        String choice = JOptionPane.showInputDialog(
                parent,
                message.append("\nEnter the number of the pet to remove:").toString(),
                "Choose Pet",
                JOptionPane.QUESTION_MESSAGE);
        return choice;
    }

    // Effects: creates a window
    private void extracted2(String name, AllPets pets, StringBuilder message, int index) {
        for (Pet pet : pets.getPets()) {
            if (pet.getName().equals(name)) {
                message.append(index++)
                        .append(". Name: ").append(pet.getName())
                        .append(", Age: ").append(pet.getAge())
                        .append(", Gender: ").append(pet.getGender())
                        .append(", Status: ").append(pet.getStatus()).append("\n");
            }
        }
    }
    
    // Effects: creates a window
    private void removeSinglePet(Pet currentPet, String name, AllPets pets, JFrame parent, JFrame frame,
            Timer mainTimer) {
        Pet toRemove = null;

        toRemove = extracted6(name, pets, toRemove);

        if (toRemove != null) {
            pets.removePet(toRemove);

            if (toRemove.equals(currentPet) || pets.getPets().isEmpty()) {
                handleCurrentPetRemoval(pets, frame);
            } else {
                JOptionPane.showMessageDialog(
                        parent,
                        "The pet with the name " + name + " has been successfully removed.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                mainTimer.start();
            }
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "No pet found with the name " + name + ".\nRemove unsuccessful.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            mainTimer.start();
        }
    }

    // Effects: creates a window
    private Pet extracted6(String name, AllPets pets, Pet toRemove) {
        for (Pet pet : pets.getPets()) {
            if (pet.getName().equals(name)) {
                toRemove = pet;
                break;
            }
        }
        return toRemove;
    }

    // Effects: creates a window
    private void handleCurrentPetRemoval(AllPets pets, JFrame frame) {
        JOptionPane.showMessageDialog(
                null,
                "You removed your current pet. The game will now close.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
        runTamagotchi(pets, frame);
    }

    // Effects: creates a window
    private void runTamagotchi(AllPets pets, JFrame frame) {
        new ChooseOwnPetWindow(pets);
        frame.dispose();
    }
}
