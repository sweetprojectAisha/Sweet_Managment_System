package MYApp_Sweet;
import java.io.*;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApp {
    private static MyApp instance;
    private static Map<String, User> users=new HashMap<>();
    private static final String USER_FILE = "users.txt";
    private String lastErrorMessage;
    private String currentPage;
    private static final Logger logger = Logger.getLogger(MyApp.class.getName());

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }
public MyApp(){
        loadUsers();
}




    public void addUser(String username, String email, String password, String confirmPassword, String phone, int age, String type,String city) {
        users.put(username, new User(username,email ,password, confirmPassword,phone,age,type,city));
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
    public void signupUser(User user) {
        logger.info("Attempting to sign up user: " + user);
        if (userExists(user.getUsername())) {
            String errorMessage = "User with name: " + user.getUsername() + " already exists.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()||user.getEmail() == null || user.getEmail().isEmpty()||user.getPhone()==null||user.getPhone().isEmpty()||user.getPassword()==null||user.getPassword().isEmpty()||user.getConfirmPassword()==null||user.getConfirmPassword().isEmpty()||user.getAge()<=0||user.getCity()==null||user.getCity().isEmpty() ){
            String errorMessage = "All fields is required.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }


        if (!isValidEmail(user.getEmail())) {
            String errorMessage = "Invalid email format: " + user.getEmail();
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            String errorMessage = "Password must be at least 8 characters long.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        if (user.getPhone() == null || user.getPhone().length() != 10) {
            String errorMessage = "Phone number must be exactly 10 characters long.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            String errorMessage = "Password and confirm password do not match.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        users.put(user.getUsername(), user);
        saveUsers();
        currentPage = "loginPage";
        logger.info("User with name: " + user.getUsername() + " signed up successfully.");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    private boolean userExists(String name) {
        return users.containsKey(name);
    }
    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String username = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    String confirmPassword = parts[3];
                    String phone = parts[4];
                    int age;
                    try {
                        age = Integer.parseInt(parts[5]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age format for user: " + username);
                        continue;
                    }
                    String type = parts[6];
                    String city = parts[7];


                    users.put(username, new User(username, email, password, confirmPassword, phone, age, type, city));
                } else {
                    System.out.println("Invalid user data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void saveUsers() {
        try (FileWriter writer = new FileWriter("users.txt")) {
            for (Map.Entry<String, MyApp.User> entry : users.entrySet()) {
                MyApp.User user = entry.getValue();
                writer.write(serializeUser(user) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeUser(User user) {
        return user.getUsername() + "," +
                user.getEmail() + "," +
                user.getPassword() + "," +
                user.getConfirmPassword() + "," +
                user.getPhone() + "," +
                user.getAge() + "," +
                user.getType() + "," +
                user.getCity();
    }

    private User deserializeUser(String data) {
        String[] parts = data.split(",");
        if (parts.length != 8) {
            throw new IllegalArgumentException("Invalid user data format: " + data);
        }
        try {
            return new User(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), parts[6], parts[7]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in user data: " + data, e);
        }
    }


    public static class User {
        private String username;
        private String email;
        private String password;
        private String confirmPassword;
        private String phone;
        private int age;
        private String type;
        private String city;
        public User(String username, String email, String password, String confirmPassword, String phone, int age, String type, String city) {
            this.username = username != null ? username : "";
            this.email = email != null ? email : "";
            this.password = password != null ? password : "";
            this.confirmPassword = confirmPassword != null ? confirmPassword : "";
            this.phone = phone != null ? phone : "";
            this.age = age > 0 ? age : 0;
            this.type = type != null ? type : "";
            this.city = city != null ? city : "";
        }


        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public String getPhone() {
            return phone;
        }

        public int getAge() {
            return age;
        }

        public String getType() {
            return type;
        }

        public String getCity() {
            return city;
        }

        public boolean isPasswordValid() {
            return password != null && password.length() >= 8;
        }

        public boolean isConfirmPasswordValid() {
            return confirmPassword != null && confirmPassword.equals(password);
        }

        public boolean isPhoneValid() {
            return phone != null && phone.matches("\\d{10}");
        }

        public boolean isEmailValid() {
            return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        }

        public boolean areAllFieldsFilled() {
            return username != null && !username.isEmpty() &&
                    email != null && !email.isEmpty() &&
                    password != null && !password.isEmpty() &&
                    confirmPassword != null && !confirmPassword.isEmpty() &&
                    phone != null && !phone.isEmpty() &&
                    age > 0 && type != null && !type.isEmpty();
        }

        public boolean isValidForSignUp() {
            return isPasswordValid() &&
                    isConfirmPasswordValid() &&
                    isPhoneValid() &&
                    isEmailValid() &&
                    areAllFieldsFilled();
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", age=" + age +
                    ", type='" + type + '\'' +
                    '}';
        }
    }



}