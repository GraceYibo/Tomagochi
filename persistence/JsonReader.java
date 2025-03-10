package persistence;

import model.Pet;
import model.AllPets;
import model.Status;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Referenced from the file JsonSerializationDemo with the link 
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled it according to my project

// Represents a reader that reads AllPets from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads allpets from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AllPets read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAllPets(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses AllPets from JSON object and returns it
    private AllPets parseAllPets(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        AllPets p = new AllPets(name);
        addPets(p, jsonObject);
        return p;
    }

    // MODIFIES: pj
    // EFFECTS: parses pets from JSON object and adds them to AllPets
    private void addPets(AllPets p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pets");
        for (Object json : jsonArray) {
            JSONObject nextPet = (JSONObject) json;
            addPet(p, nextPet);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses pet from JSON object and adds it to AllPets
    private void addPet(AllPets p, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String gender = jsonObject.getString("gender");
        Status status = Status.valueOf(jsonObject.getString("status"));
        String type = jsonObject.getString("type");
        String image = jsonObject.optString("image", ""); // Get image field or default to empty string

        // Updated constructor to include image
        Pet pet = new Pet(name, age, gender, status, type, image); 
        p.addPet(pet);
    }
}
