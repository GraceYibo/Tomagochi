package ui;

import model.Pet;
import model.AllPets;
import model.Status;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TomagachiConsule {
    private static final String JSON_STORE = "src/main/data/AllPets.json";
    private Pet pet;
    private AllPets pets;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the tomagachi
    public TomagachiConsule() throws FileNotFoundException {
        pets = new AllPets("Saved pets");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTomagachi();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTomagachi() {
        Boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            init();
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("no")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processInput(String user) {
        helperDisplayProcessInput(user);
        Boolean cont = true;
        while (cont) {
            optionsForProcessInput();
            System.out.println();
            String userInput = input.next();

            if (userInput.equals("fm")) {
                dealWithFemalChoice();
                cont = false;
            } else if (userInput.equals("ml")) {
                System.out.println();
                pet.setGender("Male");
                System.out.println("Your pet is Male.");
                start();
                cont = false;
            } else {
                invalidDisplay();
            }
        }
    }

    // EFFECTS: helper of options for processes user input
    private void optionsForProcessInput() {
        System.out.println();
        System.out.println("Choose the gender of your pet: ");
        System.out.println();
        System.out.println("\tfm -> Female");
        System.out.println();
        System.out.println("\tml -> Male");

    }

    // EFFECTS: helper for processes user input
    private void helperDisplayProcessInput(String user) {
        pet.setName(user);
        System.out.println();
        System.out.println("Your pet name is " + user + ".");
    }

    // EFFECTS: helper for processes user input of displaying invalid input
    private void invalidDisplay() {
        System.out.println();
        System.out.println("Selection not valid... Please try again!");
    }

    // MODIFIES: this
    // EFFECTS: helper for processes user input
    private void dealWithFemalChoice() {
        System.out.println();
        pet.setGender("Female");
        System.out.println("Your pet is Female.");
        start();

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("yes")) {
            System.out.println();
            dealWithLoadPets();
            System.out.println();
            System.out.println("Let's Continue, you have a new pet");
            System.out.println();
            System.out.println("Give your pet a name");
            System.out.println();
            String userinput = input.next();
            processInput(userinput);
        } else {
            System.out.println();
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: processes load pets
    private void dealWithLoadPets() {
        Boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Would you like to load your saved pet?");
            System.out.println();
            System.out.println("\ty -> yes");
            System.out.println("\tn -> no");
            String userinput = input.next();
            userinput = userinput.toLowerCase();
            if (userinput.equals("y")) {
                System.out.println();
                loadAllPets();
                pets.addPet(pet);
                keepGoing = false;
            } else if (userinput.equals("n")) {
                keepGoing = false;
            } else {
                System.out.println();
                System.out.println("Invalid input, try again");
                System.out.println();
            }
        }

    }

    // shows the explainations of marry
    private boolean marryExplainDisplay() {
        System.out.println();
        System.out.println("Your pet is now the age of getting married, do you want to let it get married?");
        System.out.println();
        System.out.println("You will get a new pet if you let it get married.");
        System.out.println();
        System.out.println("If you don't let it get married,you will continue to raise it.");
        return processMarry();
    }

    // MODIFIES: this
    // EFFECTS: change this status of the tomagochi
    private boolean processMarry() {
        Boolean keep = true;
        while (keep) {
            yesOrNoOption();
            String user = input.next();
            user = user.toLowerCase();
            if (user.equals("y")) {
                keep = false;
                return true;
            } else if (user.equals("n")) {
                System.out.println();
                System.out.println("Continue to interact with your pet.");
                pet.setAge(6);
                keep = false;
                return false;
            } else {
                System.out.println();
                System.out.println("Selection not valid...");
                System.out.println();
            }
        }
        return false;

    }

    // EFFECTS: handles option for process marry
    private void yesOrNoOption() {
        System.out.println();
        System.out.println("y -> Yes");
        System.out.println("n -> No");
        System.out.println();
    }

    // EFFECTS: handles text for process marry
    private void displayForYesCase() {
        System.out.println();
        System.out.println("Who do you want your pet to get married to?");
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: change this status of the tomagochi
    private void died() {
        System.out.println();
        System.out.println("You did't take care of your pet, your pet has died...");
        System.out.println();
        pet.setDied();
    }

    // MODIFIES: this
    // EFFECTS: change this status of the tomagochi
    private void oldDied() {
        System.out.println();
        String age = String.valueOf(pet.getAge());
        System.out.println("Your pet lived a happy life. It died at the age of " + age + ".");
        System.out.println();
        pet.setDied();
    }

    // EFFECTS: display for start method
    private void interactionDisplay() {
        System.out.println();
        System.out.println("Select the following to interact:");
        System.out.println();
        System.out.println("\tf -> Feed");
        System.out.println("\tc -> Clean");
        System.out.println("\tp -> Play");
        System.out.println("\tm -> Medication");
        System.out.println("\tcs -> Check Status");
        System.out.println("\tvtp -> View this Pet");
        System.out.println("\tvap -> View All Pets");
        System.out.println("\tsearch -> Search for a past pet");
        System.out.println("\ts -> save current pets to file");
        System.out.println("\tq -> quit game");
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: starts playing the tomagochi
    private void start() {
        Random r = new Random();
        int low = 8;
        int high = 15;
        int age = r.nextInt(high - low) + low;

        Boolean going = true;

        secondDisplayMenu();
        int loops = 0;
        while (going) {
            if (pet.getStatus() == Status.died) {
                died();
                continue;
            } else if (pet.getAge() == 5) {
                helperForMarry();
            } else if (pet.getAge() == age) {
                oldDied();
                runTomagachi();
            }
            interactions();
            loops++;
            if (loops % 5 == 0) {
                endDayDiaplay();
            }
        }
    }

    // EFFECTS: top function for dealing married
    private void helperForMarry() {
        boolean married = marryExplainDisplay(); // return a boolean
        if (married) {
            displayForYesCase();
            String person = input.next();
            pet.setMarried();
            System.out.println();
            System.out.println("Your pet got married to " + person + ". Congradulations!");
            runTomagachi();

        }
    }

    // EFFECTS: helper end day display for start method
    private void endDayDiaplay() {
        update();
        System.out.println();
        System.out.println("The day had ended.");
        System.out.println();
        System.out.println("The next day...");
        System.out.println();
        String nextDayAge = String.valueOf(pet.getAge());
        System.out.println("Age: " + nextDayAge);
        String nextDayHappyLevel = String.valueOf(pet.getHappyLevel());
        System.out.println("HappyLevel: " + nextDayHappyLevel);
        String nextDayCleaness = String.valueOf(pet.getCleanessLevel());
        System.out.println("Cleanness: " + nextDayCleaness);
        String nextDayHungryLevel = String.valueOf(pet.getFullness());
        System.out.println("Fullness: " + nextDayHungryLevel);
        String nextDayHealth = String.valueOf(pet.getHealth());
        System.out.println("Health Status: " + nextDayHealth);
        System.out.println();
    }

    // EFFECTS: helper method of displaying the levels of the pet for start method
    private void levelsDisplay() {
        System.out.println();
        String updatedHappyLevel = String.valueOf(pet.getHappyLevel());
        System.out.println("HappyLevel: " + updatedHappyLevel);
        String updatedCleaness = String.valueOf(pet.getCleanessLevel());
        System.out.println("Cleanness: " + updatedCleaness);
        String updatedFullness = String.valueOf(pet.getFullness());
        System.out.println("Fullness: " + updatedFullness);
        String updatedHealth = String.valueOf(pet.getHealth());
        System.out.println("Health Status: " + updatedHealth);
        System.out.println();
    }

    // EFFECTS: helper method of displaying current pet for start method
    private void currentPetDisplay() {
        System.out.println();
        String petName = String.valueOf(pet.getName());
        System.out.println("Name: " + petName);
        String petAge = String.valueOf(pet.getAge());
        System.out.println("Age: " + petAge);
        String petGender = String.valueOf(pet.getGender());
        System.out.println("Gender: " + petGender);
        String petStatus = String.valueOf(pet.getStatus());
        System.out.println("Current Status: " + petStatus);
        System.out.println();
    }

    // EFFECTS: helper method for processing feed option from interactions method
    private void feedPetProcess(String userinput) {
        if (userinput.equals("f")) {
            System.out.println();
            if (pet.getFullness() == 100) {
                System.out.println("Pet is full, wait for it to be hungry then feed it!");
                System.out.println();

            } else {
                pet.feed();
                System.out.println("You fed your pet.");
                String hungry = String.valueOf(pet.getFullness());
                System.out.println();
                System.out.println("Your pet's fullness is: " + hungry);
                System.out.println();
            }
        }
    }

    // EFFECTS: helper method for processing clean option from interactions method
    private void cleanPetProcess(String userinput) {
        if (userinput.equals("c")) {
            System.out.println();
            if (pet.getCleanessLevel() == 100) {
                System.out.println("Pet is clean, wait for it to be dirty then clean it!");
                System.out.println();

            } else {
                pet.clean();
                System.out.println("You cleaned your pet.");
                String clean = String.valueOf(pet.getCleanessLevel());
                System.out.println();
                System.out.println("Your pet's cleaness is: " + clean);
                System.out.println();
            }

        }
    }

    // EFFECTS: helper method for processing play option from interactions method
    private void playPetProcess(String userinput) {
        if (userinput.equals("p")) {
            System.out.println();
            if (pet.getHappyLevel() == 100) {
                System.out.println("Your pet is very happy.");
                System.out.println();
            } else {
                pet.play();
                System.out.println("You played with your pet.");
                System.out.println();
                String play = String.valueOf(pet.getHappyLevel());
                System.out.println("Your pet's happy level is: " + play);
                System.out.println();
            }
        }
    }

    // EFFECTS: helper method for processing medication option from interactions
    // method
    private void medicationPetProcess(String userinput) {
        if (userinput.equals("m")) {
            System.out.println();
            if (pet.getHealth() == true) {
                System.out.println();
                System.out.println("Pet is healthy, cannot cure it!");
                System.out.println();

            } else {
                pet.treat();
                System.out.println();
                System.out.println("You cured with your pet.");
                System.out.println();
                System.out.println("Your pet is now healthy.");
                System.out.println();
            }

        }
    }

    // EFFECTS: helper method for view all pets option from interactions method
    private void viewAllPets(String userinput) {
        if (userinput.equals("vap")) {
            System.out.println();
            System.out.println("Below is all the pets you have raised till now.");
            System.out.println();
            for (int i = 0; i < pets.getPets().size(); i++) {
                Pet pet = pets.getPets().get(i);
                System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge() + ", Gender: "
                        + pet.getGender() + ", Status: " + pet.getStatus() + ".");

            }
            System.out.println();
            removePetDisplay();
        }
    }

    // EFFECTS: helper function that display the options for removing a pet from
    // view all pets method
    private void removePetDisplay() {
        Boolean go = true;
        while (go) {
            System.out.println("Do you want to remove any Pet?");
            System.out.println();
            System.out.println("\ty -> yes");
            System.out.println("\tn -> no");
            System.out.println();
            String user = input.next();
            user = user.toLowerCase();
            System.out.println();
            removePet(user);
            go = false;
        }
    }

    // EFFECTS: helper function that remove a pet from view all pets method
    private void removePet(String user) {
        dealWithYesCase(user);
        if (user.equals("n")) {
            System.out.println();
            System.out.println("ok, let's continue");
        } else if (!user.equals("n") && !user.equals("y")) {
            System.out.println();
            System.out.println("Bad input try again.");
            System.out.println();
            removePetDisplay();
        }
    }

    // EFFECTS: helper function to deal with yes case of removing a pet
    private void dealWithYesCase(String user) {
        if (user.equals("y")) {
            System.out.println("Enter the name of the pet that you would like to remove.");
            System.out.println();
            String in = input.next();
            in = in.toLowerCase();
            int num = 0;
            for (int i = 0; i < pets.numPets(); i++) {
                if (pets.getPets().get(i).getName().equals(in)) {
                    num++;
                }
            }
            helperChoosePet(in, num);
            showListOfPets();
        }
    }

    // EEFECTS: second helper for chooing the number of pet to remove
    private void helperChoosePet(String in, int num) {
        if (num == 0) {
            System.out.println();
            System.out.println("Did not find a pet with the name " + in);
            System.out.println();
            System.out.println("Remove unsuccess.");
        } else if (num > 1) {
            System.out.println();
            System.out.println("You have " + num + " pets with the entered name " + in);
            System.out.println();
            chooseDuplicatedNumber(in, num);
        } else if (num == 1) {
            Boolean success = false;
            for (int i = 0; i < pets.numPets(); i++) {
                Pet pet = pets.getPets().get(i);
                if (pet.getName().equals(in)) {
                    pets.removePet(pet);
                    success = true;
                }
                screenForRemovePet(in, success);
                onlyPetCase(success);
            }
        }
    }

    // EFFECTS: helper function to choose the number of duplicated pets
    private void chooseDuplicatedNumber(String in, int num) {
        boolean keepGping = true;
        while (keepGping) {
            System.out.println("choose the number that you want to remove");
            System.out.println();
            int n = 1;
            for (int i = 0; i < pets.numPets(); i++) {
                if (pets.getPets().get(i).getName().equals(in)) {
                    Pet pet = pets.getPets().get(i);
                    System.out
                            .println(n + " -> Name: " + pet.getName() + ", Age: " + pet.getAge() + ", Gender: "
                                    + pet.getGender() + ", Status: " + pet.getStatus() + ".");
                    n++;

                }
            }
            if (dealWithDuplicateNames(num)) {
                keepGping = false;
            }
        }
    }

    // EFFECTS: helper function for dealing with duplicated names
    private boolean dealWithDuplicateNames(int num) {
        System.out.println();
        String inp = input.next();
        inp = inp.toLowerCase();
        System.out.println();
        if (Integer.parseInt(inp) > num) {
            System.out.println("Invalid number, try again.");
            System.out.println();
            return false;
        } else {
            pets.getPets().remove(Integer.parseInt(inp) - 1);
            return true;
        }
    }

    // EFFECTS: helper function of showing the list of pets from remove pet method
    private void showListOfPets() {
        System.out.println();
        System.out.println("Here is your new list of pets:");
        System.out.println();
        for (int i = 0; i < pets.getPets().size(); i++) {
            Pet pet = pets.getPets().get(i);
            System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge() + ", Gender: "
                    + pet.getGender() + ", Status: " + pet.getStatus() + ".");

        }
    }

    // EFFECTS: helper method of the only pet
    private void onlyPetCase(Boolean success) {
        if (success && pets.getPets().size() == 0) {
            System.out.println();
            System.out.println("You removed your only pet.");
            System.out.println();
            System.out.println("You will now raise a new pet.");
            runTomagachi();

        }
    }

    // EFFECTS: helper method of the sceen for removing pet
    private void screenForRemovePet(String in, Boolean success) {
        if (success) {
            System.out.println();
            System.out.println("The pet is successfully removed from the list of pets.");
        } else if (!success) {
            System.out.println();
            System.out.println("Did not find a pet with the name " + in);
            System.out.println();
            System.out.println("Remove unsuccess.");
        }
    }

    // EFFECTS: helper method of interactions with pet for start method
    private void interactions() {
        String userinput = null;

        interactionDisplay();
        userinput = input.next();
        userinput = userinput.toLowerCase();

        feedPetProcess(userinput);
        cleanPetProcess(userinput);
        playPetProcess(userinput);
        medicationPetProcess(userinput);
        dealWithSave(userinput);
        dealWithQuit(userinput);
        if (userinput.equals("cs")) {
            levelsDisplay();
        } else if (userinput.equals("vtp")) {
            currentPetDisplay();
        }
        viewAllPets(userinput);
        if (userinput.equals("search")) {
            searchPet();
        }

    }

    // helper funtion to save pets
    private void dealWithSave(String userinput) {
        if (userinput.equals("s")) {
            saveAllPets();
        }
    }

    // helper funtion to save pets
    private void dealWithQuit(String userinput) {
        if (userinput.equals("q")) {
            Boolean go = true;
            while (go) {
                System.out.println();
                System.out.println("Do you want to save your pets before you quit?");
                System.out.println();
                System.out.println("\ty -> yes");
                System.out.println("\tn -> no");
                dealWithSaveBeforeQuit();
                go = false;
            }
        }
    }

    // helper function of saving data before quit
    private void dealWithSaveBeforeQuit() {
        String userinput = null;
        userinput = input.next();
        userinput = userinput.toLowerCase();
        if (userinput.equals("y")) {
            saveAllPets();
            System.out.println("\nGoodbye!");
            System.exit(0);
        } else if (userinput.equals("n")) {
            System.out.println("\nGoodbye!");
            System.exit(0);
        } else {
            System.out.println("Invalid input, try again!");
        }
    }

    // EFFECTS: print display for searchPet method
    private void displayForsearchPet() {
        System.out.println();
        System.out.println("Choose the information you want to search by: ");
        System.out.println();
        System.out.println("\tname -> Name");
        System.out.println("\tage -> Age");
        System.out.println("\tstatus -> Status");
        System.out.println();

    }

    // EFFECTS: print display for searchPet method
    private void statusDisplayForsearchPet() {
        System.out.println();
        System.out.println("What's the status of the pet?");
        System.out.println();
        System.out.println("\ta -> alive");
        System.out.println("\tm -> married");
        System.out.println("\td -> died");
        System.out.println();

    }

    // EFFECTS: print display for searchPet method
    private void nameInputForsearchPet() {
        String userinput = null;
        System.out.println();
        System.out.println("What's the name of the pet?");
        System.out.println();
        userinput = input.next();
        userinput = userinput.toLowerCase();
        processForNameSearchPet(userinput);
    }

    // EFFECTS: process data for searchPet method
    private void ageInputForsearchPet() {
        String userinput = null;
        System.out.println();
        System.out.println("What's the age of the pet?");
        System.out.println();
        userinput = input.next();
        userinput = userinput.toLowerCase();
        processForAgeSearchPet(userinput);

    }

    // EFFECTS: process data for searchPet method
    private void processForNameSearchPet(String userinput) {
        Pet foundPet = null; // To store the found pet

        for (int i = 0; i < pets.getPets().size(); i++) {
            Pet pet = pets.getPets().get(i);
            String petName = pet.getName();

            if (petName.equals(userinput)) {
                foundPet = pet; // Store the found pet
                break; // Exit loop once a match is found
            }
        }

        if (foundPet != null) {
            System.out.println();
            System.out.println("Found the pet! Here is the pet you wanted to find:");
            System.out.println();
            System.out.println("Name: " + foundPet.getName() + ", Age: " + foundPet.getAge()
                    + ", Gender: " + foundPet.getGender() + ", Status: " + foundPet.getStatus() + ".");
        } else {
            System.out.println();
            System.out.println("Sorry, pet Not Found!");
            System.out.println();
        }
    }

    // EFFECTS: process data for searchPet method
    private void processForAgeSearchPet(String userinput) {
        int age = Integer.parseInt(userinput);
        boolean foundAnyPet = false; // To track if any pet is found

        for (int i = 0; i < pets.getPets().size(); i++) {
            Pet pet = pets.getPets().get(i);
            if (pet.getAge() == age) {
                if (!foundAnyPet) { // Print message only once before listing pets
                    System.out.println();
                    System.out.println("Found the pets! Here are the pets you wanted to find: ");
                    System.out.println();
                }
                foundAnyPet = true;

                // Print the details of the matching pet
                System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge() + ", Gender: "
                        + pet.getGender() + ", Status: " + pet.getStatus() + ".");
            }
        }

        // If no pets found, print the "not found" message
        if (!foundAnyPet) {
            System.out.println();
            System.out.println("Sorry, pet Not Found!");
            System.out.println();
        }
    }

    // EFFECTS: process data for searchPet method
    private void aliveStatusInput(String userinput) {
        if (userinput.equals("a")) {
            boolean foundAny = false; // Flag to check if any pet was found

            for (int i = 0; i < pets.getPets().size(); i++) {
                Pet pet = pets.getPets().get(i);
                String petStatus = String.valueOf(pet.getStatus());

                if (petStatus.equals("alive")) {
                    if (!foundAny) { // Print this message only once
                        System.out.println();
                        System.out.println("Found the pets! Here are the pets you wanted to find:");
                        System.out.println();
                        foundAny = true; // Set the flag to true after finding the first pet
                    }

                    // Print pet details
                    System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge()
                            + ", Gender: " + pet.getGender() + ", Status: " + pet.getStatus() + ".");
                }
            }

            // If no pets were found, print a message
            if (!foundAny) {
                System.out.println();
                System.out.println("Sorry, no pets with status 'alive' found.");
                System.out.println();
            }
        }
    }

    // EFFECTS: process data for searchPet method
    private void marriedStatusInput(String userinput) {
        if (userinput.equals("m")) {
            boolean foundAny = false; // Flag to check if any pet was found

            for (int i = 0; i < pets.getPets().size(); i++) {
                Pet pet = pets.getPets().get(i);
                String petStatus = String.valueOf(pet.getStatus());

                if (petStatus.equals("married")) {
                    if (!foundAny) { // Print this message only once
                        System.out.println();
                        System.out.println("Found the pets! Here are the pets you wanted to find:");
                        System.out.println();
                        foundAny = true; // Set flag to true after finding the first pet
                    }

                    // Print pet details
                    System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge()
                            + ", Gender: " + pet.getGender() + ", Status: " + pet.getStatus() + ".");
                }
            }

            // If no pets were found, print a message
            if (!foundAny) {
                System.out.println();
                System.out.println("Sorry, no pets with status 'married' found.");
                System.out.println();
            }
        }
    }

    // EFFECTS: process data for searchPet method
    private void diedStatusInput(String userinput) {
        if (userinput.equals("d")) {
            boolean foundAny = false; // Flag to check if any pet was found

            for (int i = 0; i < pets.getPets().size(); i++) {
                Pet pet = pets.getPets().get(i);
                String petStatus = String.valueOf(pet.getStatus());

                if (petStatus.equals("died")) {
                    if (!foundAny) { // Print this message only once
                        System.out.println();
                        System.out.println("Found the pets! Here are the pets you wanted to find:");
                        System.out.println();
                        foundAny = true; // Set flag to true after finding the first pet
                    }

                    // Print pet details
                    System.out.println("Name: " + pet.getName() + ", Age: " + pet.getAge()
                            + ", Gender: " + pet.getGender() + ", Status: " + pet.getStatus() + ".");
                }
            }

            // If no pets were found, print a message
            if (!foundAny) {
                System.out.println();
                System.out.println("Sorry, no pets with status 'died' found.");
                System.out.println();
            }
        }
    }

    // EFFECTS: helper function for searching a givem pet in the start method
    private void searchPet() {
        String userinput = null;
        displayForsearchPet();
        userinput = input.next();
        userinput = userinput.toLowerCase();

        if (userinput.equals("name")) {
            nameInputForsearchPet();
        } else if (userinput.equals("age")) {
            ageInputForsearchPet();
        } else if (userinput.equals("status")) {
            statusDisplayForsearchPet();
            userinput = input.next();
            userinput = userinput.toLowerCase();
            aliveStatusInput(userinput);
            marriedStatusInput(userinput);
            diedStatusInput(userinput);
        } else {
            System.out.println();
            System.out.println("Selection not valid...");
            System.out.println();
        }
    }

    // display menu of options to user
    private void displayMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Are you ready to raise a Pet?");
        System.out.println();
        System.out.println("\tyes -> Yes!");
        System.out.println("\tno -> no...");
        System.out.println();
    }

    // display menu of options to user
    private void secondDisplayMenu() {
        System.out.println();
        System.out.println("The following is your beginning pet status, interact with your pet to keep it alive.");
        System.out.println();
        String newage = String.valueOf(pet.getAge());
        System.out.println("Age: " + newage);
        String currenthappy = String.valueOf(pet.getHappyLevel());
        System.out.println("HappyLevel: " + currenthappy);
        String currentclean = String.valueOf(pet.getCleanessLevel());
        System.out.println("Cleanness: " + currentclean);
        String currenthungry = String.valueOf(pet.getFullness());
        System.out.println("Fullness: " + currenthungry);
        String currenthealth = String.valueOf(pet.getHealth());
        System.out.println("Health Status: " + currenthealth);
        helperForSecondDisplayMenu();
    }

    // display menu of options to user
    private void helperForSecondDisplayMenu() {
        System.out.println();
        System.out.println("If your pet's all level reaches zero and is unhealthy it will die.");
        System.out.println("Feeding your pet increase its fullness by 30 and increase happy level by 5.");
        System.out.println("Cleaning your pet increase its cleaness by 30 and increase happy level by 5.");
        System.out.println("Playing with your pet increase its happy level by 20.");
        System.out.println("If your pet is sick, you can cure it by using medication.");
        System.out.println("You cannot use medication on a healthy pet.");
        System.out
                .println("If fullness, cleaness level reaches 100, your pet no longer excepts food or being cleaned.");
        System.out.println(
                "If happy level reaches 100, you can still play with your pet.");
        System.out.println("But your pet's happy level will not increase anymore.");
        System.out.println(
                "When your pet reaches the age of 5, you can choose to let it get married or continue to raise it.");
        System.out.println();
    }

    // MODIFIES: pet, status
    // EFFECTS: updates status and age
    public void updateStatus() {
        if (pet.getHappyLevel() == 0 && pet.getCleanessLevel() == 0 && pet.getFullness() == 0
                && pet.getHealth() == false) {
            pet.setStatus("died");
        }

    }

    // MODIFIES: pet, happylevel, cleanesslevel, fullness, ishealthy, age, status
    // EFFECTS: updates the diiferent levels of the pet
    public void update() {
        int low = 0;
        int high = 99;
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        if (pet.getHappyLevel() - result >= 0) {
            pet.setHappyLevel(pet.getHappyLevel() - result);
        } else {
            pet.setHappyLevel(pet.getHappyLevel() - pet.getHappyLevel());
        }

        updateCleanness();
        updateFullness();
        helperForupdate();
        updateStatus();
    }

    // MODIFIES: cleaness
    // EFFECTS: updates cleaness
    public void updateCleanness() {
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
    public void updateFullness() {
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
    public void helperForupdate() {
        pet.setHealth(false);
        pet.setAge(pet.getAge() + 1);
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        pet = new Pet("Pet", 0, "Girl", Status.alive, "pet", "");
        pets.addPet(pet);
        input = new Scanner(System.in);
    }

    // Referenced from the file JsonSerializationDemo with the link
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled
    // it according to my project
    // EFFECTS: saves the workroom to file
    private void saveAllPets() {
        try {
            jsonWriter.open();
            jsonWriter.write(pets);
            jsonWriter.close();
            System.out.println();
            System.out.println("Saved " + pets.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Referenced from the file JsonSerializationDemo with the link
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled
    // it according to my project
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadAllPets() {
        try {
            pets = jsonReader.read();
            System.out.println();
            System.out.println("Loaded " + pets.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println();
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
