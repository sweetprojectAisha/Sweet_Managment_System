package MYApp_Sweet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
public class PersonalAccount {
    private static PersonalAccount instance;
    private Map<String, PersonalProfile> profiles = new HashMap<>();
//    private static Map<String, PersonalAccount.PersonalProfile> users=new HashMap<>();
    private static final String USER_FILE = "userprofiles.txt";
    public static PersonalAccount getInstance() {

        if (instance == null) {
            instance = new PersonalAccount();
        }
        return instance;
    }
    public PersonalAccount(){
       loadProfiles();
    }
    public void addProfile( String name, int age, String email, String phone) {
        profiles.put(name, new PersonalProfile(name, age, email, phone));
       saveProfiles();
    }

    public PersonalProfile getProfile(String name) {
        return profiles.get(name);
    }


    public void updateProfile( String name, int age, String email, String phone) {
        if (profiles.containsKey(name)) {
            profiles.put(name, new PersonalProfile(name, age, email, phone));
            saveProfiles();
        }
    }


    public void deleteProfile(int id) {
        profiles.remove(id);
     saveProfiles();
    }

    public Map<String, PersonalProfile> getAllProfiles() {
        return profiles;
    }
    private void loadProfiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Adjusted length to match serialized format
                    String name = parts[0].trim();
                    String email = parts[1].trim();
                    String phone = parts[2].trim();
                    int age;
                    try {
                        age = Integer.parseInt(parts[3].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age format for profile: " + name);
                        continue;
                    }

                    PersonalProfile profile = new PersonalProfile(name, age, email, phone);
                    profiles.put(name, profile);
                    System.out.println("Loaded profile: " + name);
                } else {
                    System.out.println("Invalid profile data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void saveProfiles() {
        try (FileWriter writer = new FileWriter(USER_FILE)) {
            for (PersonalProfile profile : profiles.values()) {
                writer.write(serializeProfile(profile) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String serializeProfile(PersonalProfile profile) {
        return profile.getName() + "," +
                profile.getEmail() + "," +
                profile.getPhone() + "," +
                profile.getAge();
    }
    public static class PersonalProfile {
        private String name;
        private int age;
        private String email;
        private String phone;


        public PersonalProfile(String name, int age, String email, String phone) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.phone = phone;

        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }


    }
}
