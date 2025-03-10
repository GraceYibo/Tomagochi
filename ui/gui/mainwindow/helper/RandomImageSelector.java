package ui.gui.mainwindow.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Represents a GUI window for managing images
public class RandomImageSelector {

    private List<String> imageLinks;
    private Random random;

    /**
     * Constructs a RandomImageSelector and initializes the list of image links.
     */
    public RandomImageSelector() {
        imageLinks = new ArrayList<>();
        random = new Random();
        initializeImageLinks();
    }

    /**
     * Initializes the list of image links.
     */
    private void initializeImageLinks() {
        imageLinks.add("src/main/Image/RandomPets/r1.png");
        imageLinks.add("src/main/Image/RandomPets/r2.png");
        imageLinks.add("src/main/Image/RandomPets/r3.png");
        imageLinks.add("src/main/Image/RandomPets/r4.png");
        imageLinks.add("src/main/Image/RandomPets/r5.png");
        imageLinks.add("src/main/Image/RandomPets/r6.png");
        imageLinks.add("src/main/Image/RandomPets/r7.png");
        imageLinks.add("src/main/Image/RandomPets/r8.png");
        imageLinks.add("src/main/Image/RandomPets/r9.png");
        imageLinks.add("src/main/Image/RandomPets/r10.png");
        imageLinks.add("src/main/Image/RandomPets/r11.png");
        imageLinks.add("src/main/Image/RandomPets/r12.png");
        imageLinks.add("src/main/Image/RandomPets/r13.png");
        imageLinks.add("src/main/Image/RandomPets/r14.png");
        imageLinks.add("src/main/Image/RandomPets/r15.png");
        imageLinks.add("src/main/Image/RandomPets/r16.png");
        imageLinks.add("src/main/Image/RandomPets/r17.png");
        imageLinks.add("src/main/Image/RandomPets/r18.png");
        imageLinks.add("src/main/Image/RandomPets/r19.png");
        imageLinks.add("src/main/Image/RandomPets/r20.png");
        imageLinks.add("src/main/Image/RandomPets/r21.png");
        imageLinks.add("src/main/Image/RandomPets/r22.png");
        imageLinks.add("src/main/Image/RandomPets/r23.png");
        // Add more links as needed
    }

    /**
     * Selects a random image link from the list.
     *
     * @return the randomly selected image link
     */
    public String getRandomImageLink() {
        if (imageLinks.isEmpty()) {
            throw new IllegalStateException("No image links available");
        }
        int index = random.nextInt(imageLinks.size());
        return imageLinks.get(index);
    }

    /**
     * Adds a new image link to the list.
     *
     * @param link the new image link to add
     */
    public void addImageLink(String link) {
        imageLinks.add(link);
    }

    /**
     * Removes an image link from the list.
     *
     * @param link the image link to remove
     * @return true if the link was removed, false otherwise
     */
    public boolean removeImageLink(String link) {
        return imageLinks.remove(link);
    }

    /**
     * Gets the total number of image links.
     *
     * @return the size of the image links list
     */
    public int getTotalLinks() {
        return imageLinks.size();
    }
}
