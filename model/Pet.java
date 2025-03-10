package model;

import org.json.JSONObject;

import persistence.Writable;

// Pet class that makes a pet with name, age, gender, type, status, image
public class Pet implements Writable {

    static final int IncreaseHappyValue = 20;
    static final int food = 20;
    static final int clean = 20;
    static final int decreaselevel = 50;

    private String name;
    private String gender;
    private int age;
    private boolean ishealthy;
    private int happylevel;
    private int cleanesslevel;
    private int fullness;
    private Status status;
    private String type;
    private String image;

    // EFFECTS: a pet with a name, an age, a gender, healthy or not and a status
    // saying
    // whether is is alive, married or died. It also has a happy level, cleaness
    // level
    // and fullness level of 100 in the beginning.

    public Pet(String name, int age, String gender, Status status, String type, String image) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ishealthy = true;
        this.happylevel = 100;
        this.cleanesslevel = 100;
        this.fullness = 100;
        this.status = status;
        this.type = type;
        this.image = image;
    }

    // EFFECTS: returns the name of the pet
    public String getName() {
        return this.name; // stub
    }

    // EFFECTS: returns the name of the pet
    public String getGender() {
        return this.gender; // stub
    }

    // EFFECTS: returns the age of the pet
    public int getAge() {
        return this.age;
    }

    // EFFECTS: returns the current happylevel of the pet
    public double getHappyLevel() {
        return this.happylevel;
    }

    // EFFECTS: returns the current cleaness of the pet
    public double getCleanessLevel() {
        return this.cleanesslevel;
    }

    // EFFECTS: returns the health of the pet
    public boolean getHealth() {
        return this.ishealthy;
    }

    // EFFECTS: returns the fullness of the pet
    public int getFullness() {
        return this.fullness;

    }

    // EFFECTS: returns the status of the pet, whether it is alive, married or died
    public Status getStatus() {
        return this.status;

    }

    // EFFECTS: returns the type of the pet
    public String getType() {
        return this.type;
    }

    // EFFECTS: returns the image link of the pet
    public String getImage() {
        return this.image;
    }

    // MODIFIES: this
    // EFFECTS: sets the image link of the pet
    public void setImage(String image) {
        this.image = image;
    }
    
    // EFFECTS: sets the type of the pet
    public void setType(String type) {
        this.type = type;
    }

    // The following are setters setting different values for the pet

    // MODIFIES: pet, name
    // EFFECTS: set the name of the pet
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Pet name changed to " + name + "."));
    }

    // MODIFIES: pet, age
    // EFFECTS: set the age of the pet
    public void setAge(int age) {
        this.age = age;
        EventLog.getInstance().logEvent(new Event("Pet age changed to " + age + "."));
    }

    // MODIFIES: pet, gender
    // EFFECTS: set the gender of the pet
    public void setGender(String gender) {
        this.gender = gender;
        EventLog.getInstance().logEvent(new Event("Pet gender changed to " + gender + "."));
    }

    // MODIFIES: pet, status
    // EFFECTS: change the status of the pet to married
    public void setMarried() {
        this.status = Status.married;
    }

    // MODIFIES: pet, status
    // EFFECTS: change the status of the pet to died
    public void setDied() {
        this.status = Status.died;
    }

    // MODIFIES: pet, happylevel
    // EFFECTS: change the happy level of the pet with the given number
    public void setHappyLevel(double num) {
        happylevel = (int) Math.round(num);
    }

    // MODIFIES: pet, clean level
    // EFFECTS: change the clean level of the pet with the given number
    public void setCleanLevel(double num) {
        cleanesslevel = (int) Math.round(num);
    }

    // MODIFIES: pet, full level
    // EFFECTS: change the full level of the pet with the given number
    public void setFullness(int num) {
        fullness = (int) Math.round(num);
    }

    // MODIFIES: pet, ishealthy
    // EFFECTS: change the health of the pet with the given boolean
    public void setHealth(Boolean health) {
        ishealthy = health;
    }

    // MODIFIES: pet, status
    // EFFECTS: change the status of the pet with the given string
    public void setStatus(String status) {
        if (status.equals("died")) {
            this.status = Status.died;
            EventLog.getInstance().logEvent(new Event("Pet status changed to died."));
        } else if (status.equals("married")) {
            this.status = Status.married;
            EventLog.getInstance().logEvent(new Event("Pet status changed to married."));
        } else {
            this.status = Status.alive;
            EventLog.getInstance().logEvent(new Event("Pet status changed to alive."));
        }

    }

    // below are methods changing the pet's different level

    // MODIFIES: hunger level, happy level
    // EFFECTS: increase the fullnesss level and the happy level
    public void feed() {
        if (100 - this.fullness >= food) {
            this.fullness += food;
            EventLog.getInstance().logEvent(new Event("Pet fullness changed."));
        } else {
            this.fullness = this.fullness + (100 - this.fullness);
            EventLog.getInstance().logEvent(new Event("Pet fullness changed."));
        }

        if (100 - this.happylevel < IncreaseHappyValue) {
            this.happylevel = this.happylevel + (100 - this.happylevel);
            EventLog.getInstance().logEvent(new Event("Pet fullness changed."));
        } else {
            this.happylevel += IncreaseHappyValue;
            EventLog.getInstance().logEvent(new Event("Pet fullness changed."));
        }
    }

    // MODIFIES: clean level, happy level
    // EFFECTS: increase the clean level and the happy level
    public void clean() {
        if (100 - this.cleanesslevel >= clean) {
            this.cleanesslevel += clean;
            EventLog.getInstance().logEvent(new Event("Pet cleaness changed."));
        } else {
            this.cleanesslevel = this.cleanesslevel + (100 - this.cleanesslevel);
            EventLog.getInstance().logEvent(new Event("Pet cleaness changed."));
        }
        if (100 - this.happylevel < IncreaseHappyValue) {
            this.happylevel = this.happylevel + (100 - this.happylevel);
            EventLog.getInstance().logEvent(new Event("Pet cleaness changed."));
        } else {
            this.happylevel += IncreaseHappyValue;
            EventLog.getInstance().logEvent(new Event("Pet cleaness changed."));
        }

    }

    // MODIFIES: happy level
    // EFFECTS: increase the happy level
    public void play() {
        if (100 - this.happylevel >= IncreaseHappyValue) {
            this.happylevel += IncreaseHappyValue;
            EventLog.getInstance().logEvent(new Event("Pet happyness changed."));
        } else {
            this.happylevel = this.happylevel + (100 - this.happylevel);
            EventLog.getInstance().logEvent(new Event("Pet happyness changed."));
        }
    }

    // MODIFIES: pet, ishealthy
    // EFFECTS: heal a sick pet
    public void treat() {
        ishealthy = true;
        EventLog.getInstance().logEvent(new Event("Pet's health level has changed."));
    }

    // EFFECTS: returns string representation of this pet
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Status: " + status
                + ", Type: " + type + ", Image: " + image + ".";
    }

    // Referenced from the file JsonSerializationDemo with the link
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git modeled
    // it according to my project
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("gender", gender);
        json.put("status", status);
        json.put("type", type);
        json.put("image", image); // Include the image in the JSON representation
        return json;
    }
}
