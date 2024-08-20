package MYApp_Sweet;
import java.io.*;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class BusinessAccount {
    private static BusinessAccount instance;
    private boolean is_an_owner;
    private boolean navagates_to_businessprofile;
    private Map<Integer, BusinessProfile> profiles = new HashMap<>();
    private final String fileName = "business_accounts.txt";

    public static BusinessAccount getInstance() {

        if (instance == null) {
            instance = new BusinessAccount();
        }
        return instance;
    }
    public BusinessAccount(){
        loadBusinessAccounts();
    }
    public boolean isIs_an_owner() {
        return is_an_owner;
    }

    public void setIs_an_owner(boolean is_an_owner) {
        this.is_an_owner = is_an_owner;
    }
    public boolean isNavagates_to_salespage() {
        return navagates_to_businessprofile;
    }

    public void setNavagates_to_salespage(boolean navagates_to_businessprofile) {
        this.navagates_to_businessprofile = navagates_to_businessprofile;
    }
    public void addProfile(int id, String address, String contact, String name) {
        profiles.put(id, new BusinessProfile(address, contact, name, id));
        saveChanges();
    }

    public BusinessProfile getProfile(int id) {
        return profiles.get(id);
    }

    public void updateProfile(int id, String address, String contact, String name) {
        if (profiles.containsKey(id)) {
            profiles.put(id, new BusinessProfile(address, contact, name, id));
            saveChanges();
        }
    }

    public void deleteProfile(int id) {
        profiles.remove(id);
        saveChanges();
    }

    public Map<Integer, BusinessProfile> getAllProfiles() {
        return profiles;
    }
    public void loadBusinessAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 4) {
                    String address = parts[0].trim();
                    String contact = parts[1].trim();
                    String businessName = parts[2].trim();
                    int id;
                    try {
                        id = Integer.parseInt(parts[3].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format: " + parts[3]);
                        continue;
                    }
                    BusinessProfile profile = new BusinessProfile(address, contact, businessName, id);
                    profiles.put(id, profile);
                    System.out.println("Loaded profile: " + profile.getBusinessName() + " with ID: " + id);
                } else {
                    System.out.println("Invalid business account data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void saveChanges() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (BusinessProfile profile : profiles.values()) {
                writer.write(profile.getAddress() + "," +
                        profile.getContact() + "," +
                        profile.getBusinessName() + "," +
                        profile.getId());
                writer.newLine();
            }

        } catch (IOException e) {

        }
    }

    public static class BusinessProfile {
        private String address;
        private String contact;
        private String businessName;
        private int id;

        public BusinessProfile(String address, String contact, String businessName, int id) {
            this.address = address;
            this.contact = contact;
            this.businessName = businessName;
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public String getContact() {
            return contact;
        }

        public String getBusinessName() {
            return businessName;
        }

        public int getId() {
            return id;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
