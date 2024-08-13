package MyApp_sweet;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApp {
    private static MyApp instance;
    private Map<String, User> users=new HashMap<>();
    private String currentPage;
    private static final Logger logger = Logger.getLogger(MyApp.class.getName());

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }
    public void addUser(String username, String password, String type) {
        users.put(username, new User(username, password, type));
    }

    public String login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            switch (user.getType()) {
                case "admin":
                    currentPage = "adminPage";
                    break;
                case "owner":
                    currentPage = "ownerPage";
                    break;
                case "user":
                    currentPage = "userPage";
                    break;
            }
            return currentPage;
        } else {
            currentPage = "loginPage";
            return currentPage;
        }
    }

    public boolean isOnLoginPage() {
        return "loginPage".equals(currentPage);
    }

    public String getExpectedPage(String username) {
        User user = users.get(username);
        if (user != null) {
            switch (user.getType()) {
                case "admin":
                    return "adminPage";
                case "owner":
                    return "ownerPage";
                case "user":
                    return "userPage";
            }
        }
        return "loginPage";
    }
    public static class User {
        private String username;
        private String password;
        private String type;

        public User(String username, String password, String type) {
            this.username = username;
            this.password = password;
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getType() {
            return type;
        }
    }



}