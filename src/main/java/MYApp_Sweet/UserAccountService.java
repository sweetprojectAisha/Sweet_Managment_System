package MYApp_Sweet;

import java.util.HashMap;
import java.util.Map;
public class UserAccountService {

    private Map<String, Map<String, String>> userAccounts;

    public UserAccountService() {
        userAccounts = new HashMap<>();
        Map<String, String> user1 = new HashMap<>();
        user1.put("email", "amal@gmail.com");
        user1.put("name", "Amal");
        user1.put("phone", "0591234567");
        userAccounts.put("amal@gmail.com", user1);

        Map<String, String> user2 = new HashMap<>();
        user2.put("email", "fatima@gmail.com");
        user2.put("name", "Fatima");
        user2.put("phone", "0598765432");
        userAccounts.put("fatima@gmail.com", user2);
    }

    public Map<String, String> searchUserByEmail(String email) {
        return userAccounts.getOrDefault(email, null);
    }
}
