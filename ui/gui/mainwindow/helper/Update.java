package ui.gui.mainwindow.helper;

import java.util.Random;

import model.Pet;

// A class to update the pet's statis
public class Update {

    public Update(Pet pet) {
        update(pet);
    }

    // MODIFIES: pet, happylevel, cleanesslevel, fullness, ishealthy, age, status
    // EFFECTS: updates the diiferent levels of the pet
    public void update(Pet pet) {
        int low = 0;
        int high = 99;
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        if (pet.getHappyLevel() - result >= 0) {
            pet.setHappyLevel(pet.getHappyLevel() - result);
        } else {
            pet.setHappyLevel(pet.getHappyLevel() - pet.getHappyLevel());
        }

        updateCleanness(pet);
        updateFullness(pet);
        helperForupdate(pet);
        updateStatus(pet);
    }

    // MODIFIES: cleaness
    // EFFECTS: updates cleaness
    public void updateCleanness(Pet pet) {
        int low = 0;
        int high = 99;
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        if (pet.getCleanessLevel() - result >= 0) {
            pet.setCleanLevel(pet.getCleanessLevel() - result);
        } else {
            pet.setCleanLevel(pet.getCleanessLevel() - pet.getCleanessLevel());
        }
    }

    // MODIFIES: fullness
    // EFFECTS: updates fullness
    public void updateFullness(Pet pet) {
        int low = 0;
        int high = 99;
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        if (pet.getFullness() - result >= 0) {
            pet.setFullness(pet.getFullness() - result);
        } else {
            pet.setFullness(pet.getFullness() - pet.getFullness());
        }
    }

    // MODIFIES: pet, ishealthy, age
    // EFFECTS: updates health and age
    public void helperForupdate(Pet pet) {
        pet.setHealth(false);
        pet.setAge(pet.getAge() + 1);
    }

    // MODIFIES: pet, status
    // EFFECTS: updates status and age
    public void updateStatus(Pet pet) {
        if (pet.getHappyLevel() == 0 && pet.getCleanessLevel() == 0 && pet.getFullness() == 0
                && pet.getHealth() == false) {
            pet.setStatus("died");
        }

    }

}
