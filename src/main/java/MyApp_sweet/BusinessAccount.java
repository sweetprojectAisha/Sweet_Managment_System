package MyApp_sweet;
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

    public static BusinessAccount getInstance() {

        if (instance == null) {
            instance = new BusinessAccount();
        }
        return instance;
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
    }

    public BusinessProfile getProfile(int id) {
        return profiles.get(id);
    }

    public void updateProfile(int id, String address, String contact, String name) {
        if (profiles.containsKey(id)) {
            profiles.put(id, new BusinessProfile(address, contact, name, id));
        }
    }

    public void deleteProfile(int id) {
        profiles.remove(id);
    }

    public Map<Integer, BusinessProfile> getAllProfiles() {
        return profiles;
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
    }
}
