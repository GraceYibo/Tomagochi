package ui.gui.setup;

import org.json.JSONObject;
import model.AllPets;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class to check the contents of a JSON file and determine the flow of the application.
 */
public class JsonFileChecker {

    private static final String FILE_PATH = "src/main/data/AllPetsForGUI.json"; // Path to the JSON file

    /**
     * Requires: `pets` is non-null.
     * Modifies: Launches the appropriate window based on the JSON file's state.
     * Effects:  Reads the JSON file and opens either `BeginningWindow` or `GameStart`.
     *
     * @param pets the AllPets object managing all pets
     */
    public JsonFileChecker(AllPets pets) {
        checkJsonFile(FILE_PATH, pets);
    }

    /**
     * Requires: `filePath` is non-null, `pets` is non-null.
     * Modifies: Launches the appropriate window based on the file's state and contents.
     * Effects:  Checks if the file exists and is non-empty, then determines:
     *           - Launch `BeginningWindow` if the file is empty or invalid.
     *           - Launch `GameStart` if the file contains valid data.
     *
     * @param filePath the path to the JSON file
     * @param pets     the AllPets object managing all pets
     */
    public static void checkJsonFile(String filePath, AllPets pets) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.length() > 0) {
                // File exists and is non-empty, read the data
                JSONObject jsonData = readJsonFile(filePath);
                if (jsonData.isEmpty()) {
                    // JSON data is empty, open BeginningWindow
                    new BeginningWindow(pets);
                } else {
                    // JSON data has content, open GameStart
                    new GameStart(pets);
                }
            } else {
                // File does not exist or is empty, open BeginningWindow
                new BeginningWindow(pets);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // In case of an error, default to BeginningWindow
            new BeginningWindow(pets);
        }
    }

    /**
     * Requires: `filePath` is non-null and points to a valid file.
     * Modifies: Reads the content of the file.
     * Effects:  Returns a JSONObject containing the parsed JSON data from the file.
     *
     * @param filePath the path to the JSON file
     * @return a JSONObject representing the contents of the file
     * @throws IOException if there is an issue reading the file
     */
    public static JSONObject readJsonFile(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            stringBuilder.append((char) character);
        }
        reader.close();

        return new JSONObject(stringBuilder.toString());
    }
}
