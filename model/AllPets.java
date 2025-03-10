package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of pets
public class AllPets implements Writable {

    private ArrayList<Pet> pets;
    private String name;

    // EFFECTS: a list of pets
    public AllPets(String name) {
        pets = new ArrayList<>();
        this.name = name;

    }

    public String getName() {
        return name;
    }

    // EFFECTS: add a pet to the list of pets
    public void addPet(Pet pet) {
        pets.add(pet);
        EventLog.getInstance().logEvent(new Event("Pet added to list of pets."));
    }

    // EFFECTS: remove a pet to the list of pets
    public void removePet(Pet pet) {
        pets.remove(pet);
        EventLog.getInstance().logEvent(new Event("Pet removed to list of pets."));
    }

    // EFFECTS: return the list of pets
    public ArrayList<Pet> getPets() {
        return pets;
    }

    // EFFECTS: returns number of pets in this AllPets
    public int numPets() {
        return pets.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    // Referenced from the file JsonSerializationDemo with the link
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled
    // it according to my project
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pets", petsToJson());
        return json;
    }

    // Referenced from the file JsonSerializationDemo with the link
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled
    // it according to my project
    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray petsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pet p : pets) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}