package ui.gui.setup;

import java.io.IOException;

import javax.swing.*;
import model.AllPets;
import model.Pet;
import model.Status;
import persistence.JsonReader;
import ui.gui.mainwindow.MainWindow;

// Effects: Starts the game and manages game flow based on saved data
public class GameStart {

    private JsonReader jsonReader;
    private static final String JSON_STORE = "src/main/data/AllPetsForGUI.json";

    /**
     * Requires: `pets` is non-null.
     * Modifies: None.
     * Effects:  Initializes the game start process, including loading saved pets and determining
     *           whether to continue with an existing pet or start a new game.
     *
     * @param pets the AllPets collection managing all pets
     */
    public GameStart(AllPets pets) {
        this.jsonReader = new JsonReader(JSON_STORE);
        startGame(pets);
    }

    /**
     * Requires: `pets` is non-null.
     * Modifies: May update the list of pets in `pets` if saved data is loaded.
     * Effects:  Determines if a saved alive pet exists. If so, prompts the user to continue with
     *           the existing pet or start a new game. Otherwise, starts a new game.
     *
     * @param pets the AllPets collection managing all pets
     */
    public void startGame(AllPets pets) {
        loadAllPets(pets);
        Pet savedPet = getSavedAlivePet(pets);

        if (savedPet != null) {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "You have a pet named " + savedPet.getName() + " that is still alive.\n" 
                    + "Do you want to continue raising this pet?",
                    "Continue with Existing Pet",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                continueWithPet(pets, savedPet);
            } else {
                startNewGame(pets);
            }
        } else {
            startNewGame(pets);
        }
    }

    /**
     * Requires: `pets` is non-null.
     * Modifies: None.
     * Effects:  Searches through the list of pets in `pets` and returns the first pet
     *           with a status of `Status.alive`. If no such pet exists, returns null.
     *
     * @param pets the AllPets collection managing all pets
     * @return the first alive pet if one exists; otherwise, null
     */
    private Pet getSavedAlivePet(AllPets pets) {
        for (Pet pet : pets.getPets()) {
            if (pet.getStatus() == Status.alive) {
                return pet;
            }
        }
        return null;
    }

    /**
     * Requires: `pets` and `savedPet` are non-null.
     * Modifies: None.
     * Effects:  Displays a message welcoming the user back to their pet and launches
     *           the main game window with the saved pet.
     *
     * @param pets     the AllPets collection managing all pets
     * @param savedPet the saved Pet object to continue with
     */
    private void continueWithPet(AllPets pets, Pet savedPet) {
        JOptionPane.showMessageDialog(
                null,
                "Welcome back to your pet " + savedPet.getName() + "!",
                "Continue Game",
                JOptionPane.INFORMATION_MESSAGE);
        new MainWindow(pets, savedPet.getImage(), savedPet);
    }

    /**
     * Requires: `pets` is non-null.
     * Modifies: None.
     * Effects:  Displays a message informing the user that a new game will start,
     *           and launches the PullDataWindow to create a new pet.
     *
     * @param pets the AllPets collection managing all pets
     */
    private void startNewGame(AllPets pets) {
        JOptionPane.showMessageDialog(
                null,
                "Starting a new game. Let's raise a new pet!",
                "New Game",
                JOptionPane.INFORMATION_MESSAGE);
        AllPets petsNew = new AllPets("tomagochi");
        new PullDataWindow(petsNew);
    }

    /**
     * Requires: `pets` is non-null.
     * Modifies: Updates the name and list of pets in `pets` with data loaded from the JSON file.
     * Effects:  Loads the saved pets data from the JSON file and updates the `pets` object.
     *           If the file cannot be read, displays an error message.
     *
     * @param pets the AllPets collection to update with loaded data
     */
    private void loadAllPets(AllPets pets) {
        try {
            AllPets loadedPets = jsonReader.read();
            pets.setName(loadedPets.getName());
            pets.setPets(loadedPets.getPets());
            System.out.println("Loaded " + pets.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
