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

public class ManageOwnerAccountsC {
    private static ManageOwnerAccountsC instance;
    public Map<String, ManageOwnerAccountsC.UserAccount> owndeatails = new HashMap<>();
    private static final Logger logger = Logger.getLogger(ManageOwnerAccountsC.class.getName());
    private static final String USER_FILE = "ownerprofiles.txt";
    public String errormessage = "";
    private boolean is_an_admin;
    public static ManageOwnerAccountsC getInstance() {
        if (instance == null) {
            instance = new ManageOwnerAccountsC();
        }
        return instance;
    }
    private ManageOwnerAccountsC() {
        loadProfiles();
    }
    private void loadProfiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
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

                    UserAccount profile = new UserAccount(name, email, phone, age);
                    owndeatails.put(name, profile);
                    System.out.println("Loaded profile: " + name);
                } else {
                    System.out.println("Invalid profile data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save user profiles to a file
    private void saveProfiles() {
        try (FileWriter writer = new FileWriter(USER_FILE)) {
            for (UserAccount profile : owndeatails.values()) {
                writer.write(serializeProfile(profile) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Serialize a user account to a string
    private String serializeProfile(UserAccount profile) {
        return profile.getName() + "," +
                profile.getEmail() + "," +
                profile.getPhone() + "," +
                profile.getAge();
    }

    public boolean isIs_an_admin() {
        return is_an_admin;
    }
    public void setIs_an_admin(boolean is_an_admin) {
        this.is_an_admin = is_an_admin;
    }
    public ManageOwnerAccountsC.UserAccount getUser(String uname) {
        return owndeatails.get(uname);
    }
    public String getErrorMessage() {
        return errormessage;
    }


    public boolean userAccountExists(String name) {
        return owndeatails.containsKey(name);
    }
    public void addUserAccount(UserAccount userAccount) {
        logger.info("Attempting to add user account: " + userAccount);

        if (userAccountExists(userAccount.getEmail())) {
            errormessage = "User account with email: " + userAccount.getEmail() + " already exists.";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }

        if (userAccount.getName() == null || userAccount.getName().isEmpty() ||
                userAccount.getEmail() == null || userAccount.getEmail().isEmpty() ||
                userAccount.getPhone() == null || userAccount.getPhone().isEmpty() ||
                userAccount.getAge()<=0) {
            logger.warning("Invalid user account details: " + userAccount);
            errormessage = "Invalid user account details.";
            throw new IllegalStateException(errormessage);
        }
        owndeatails.put(userAccount.getName(), userAccount);
        saveProfiles();
        logger.info("User account with email: " + userAccount.getEmail() + " added successfully.");
    }


    public void updateUserAccount(UserAccount userAccount) {
        String name = userAccount.getName();

        if (!owndeatails.containsKey(name)) {
            errormessage = "Cannot update. User account with email: " + name + " does not exist.";
            logger.severe(errormessage);
            throw new IllegalStateException(errormessage);
        } else {
            if (userAccount.getName() == null || userAccount.getName().isEmpty() ||
                    userAccount.getEmail() == null || userAccount.getEmail().isEmpty() ||
                    userAccount.getPhone() == null || userAccount.getPhone().isEmpty() ||
                   userAccount.getAge()<=0) {

                logger.warning("Invalid user account details: " + userAccount);
                errormessage = "Invalid user account details.";
                throw new IllegalStateException(errormessage);
            }

            owndeatails.put(name, userAccount);
            saveProfiles();
            logger.info("User account with email: " + name + " updated successfully.");
        }
    }
    public void deleteUserAccount(String name) {
        logger.info("Attempting to delete user account with name: " + name);


        if (!owndeatails.containsKey(name)) {
            errormessage = "User account with email " + name + " does not exist.";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }

        owndeatails.remove(name);
        saveProfiles();




        logger.info("User account with name: " + name + " deleted successfully.");
    }

    public UserAccount getUserr(String name) {
        return owndeatails.get(name);
    }

    public static class UserAccount {
        private String name;
        private String email;
        private String phone;
        private int age;

        public UserAccount(String name, String email, String phone, int age) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public int getAge() {
            return age;
        }
    }


}
