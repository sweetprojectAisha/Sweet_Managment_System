package MYApp_Sweet;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
public class PersonalAccount {
    private static PersonalAccount instance;
    private Map<String, PersonalProfile> profiles = new HashMap<>();

    public static PersonalAccount getInstance() {

        if (instance == null) {
            instance = new PersonalAccount();
        }
        return instance;
    }
    public void addProfile( String name, int age, String email, String phone) {
        profiles.put(name, new PersonalProfile(name, age, email, phone));
    }

    public PersonalProfile getProfile(String name) {
        return profiles.get(name);
    }


    public void updateProfile( String name, int age, String email, String phone) {
        if (profiles.containsKey(name)) {
            profiles.put(name, new PersonalProfile(name, age, email, phone));
        }
    }


    public void deleteProfile(int id) {
        profiles.remove(id);
    }

    public Map<String, PersonalProfile> getAllProfiles() {
        return profiles;
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
