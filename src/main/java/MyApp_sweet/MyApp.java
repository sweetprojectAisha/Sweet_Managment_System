package MyApp_sweet;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyApp {
    private static final Logger logger = Logger.getLogger(MyApp.class.getName());
    private List<String> validationErrors = new ArrayList<>();
    public boolean is_in_loginpage = false;
    public boolean is_in_signuppage = false;
    public boolean is_in_forgotpasswordpage = false;

    private Map<String, UserInfo> listvalidUsers = new HashMap<String, UserInfo>();
    private String currentPage;
    private String errorMessage;
    private String enteredUname;
    private String enteredPass;
    private String email;
    private String phone;
    private String age;
    private String confpass;

    private String message;
    private String exppage;


    public static class UserInfo {
        String password;
        String userType;
        String email;

        public UserInfo(String email, String password, String userType) {
            this.password = password;
            this.userType = userType;
            this.email = email;
        }
    }

    public void fillSignUppage(String name, String email, String phone, String age, String password, String confPassword) {
        this.enteredUname = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.enteredPass = password;
        this.confpass = confPassword;
    }







//    public void setValidUsers(List<Map<String, String>> users) {
//        for (Map<String, String> user : users) {
//            String username = user.get("username");
//            String password = user.get("password");
//            String userType = user.get("userType");
//            listvalidUsers.put(username, new UserInfo(password, userType));
//        }
//    }

    public void setValidUsers(List<Map<String, String>> users) {
        for (Map<String, String> user : users) {
            String username = user.get("username");
            String email = user.get("email");
            String password = user.get("password");
            String userType = user.get("userType");
            listvalidUsers.put(username, new UserInfo(email, password, userType));
        }
    }


    public void enterCredentials(String username, String password) {
        enteredUname = username;
        enteredPass = password;
    }
//    private boolean isPasswordexist(String pass) {
//        return listvalidUsers.containsValue(pass);
//    }

    public void loginsubmit() {
        if (enteredUname == null || enteredUname.isEmpty()) {
            message = "Username can not be empty";
            exppage = "login_page";
        } else if (enteredPass == null || enteredPass.isEmpty()) {
            message = "Password can not be empty";
            exppage = "login_page";
        } else if (!listvalidUsers.containsKey(enteredUname)) {
            if (!isPasswordExist(enteredPass)) {
                message = "Invalid username or password";
            } else {
                message = "Invalid username";
            }
            exppage = "login_page";
        } else if (!listvalidUsers.get(enteredUname).password.equals(enteredPass)) {
            message = "Invalid password";
            exppage = "login_page";
        } else {
            message = "Welcome, " + enteredUname;
            exppage = selectpage(listvalidUsers.get(enteredUname).userType);
        }
    }


//    public void submitSignup() {
//        validationErrors.clear();
//
//
//        if (enteredUname == null || enteredUname.isEmpty()) {
//            validationErrors.add("Name cannot be empty");
//        } else if (listvalidUsers.containsKey(enteredUname)) {
//            validationErrors.add("Name must be unique");
//        }
//
//        if (email == null || email.isEmpty()) {
//            validationErrors.add("Email cannot be empty");
//        } else if (!isValidEmail(email)) {
//            validationErrors.add("Email must be unique");
//        }
//
//        if (phone == null || phone.isEmpty()) {
//            validationErrors.add("Phone cannot be empty");
//        } else if (!isValidPhone(phone)) {
//            validationErrors.add("Phone must be 10 characters long");
//        }
//
//        if (age == null || age.isEmpty()) {
//            validationErrors.add("Age cannot be empty");
//        }
//
//        if (enteredPass == null || enteredPass.isEmpty()) {
//            validationErrors.add("Password cannot be empty");
//        } else if (!isValidPassword(enteredPass)) {
//            validationErrors.add("Password must be at least 8 characters long and include a special character");
//        }
//
//        if (confpass == null || confpass.isEmpty()) {
//            validationErrors.add("Confirm password cannot be empty");
//        } else if (!enteredPass.equals(confpass)) {
//            validationErrors.add("Password should equal confirm password");
//        }
//
//        System.out.println("Validation Errors: " + validationErrors); // Debugging statement
//
//        if (validationErrors.isEmpty()) {
//            listvalidUsers.put(enteredUname, new UserInfo(email, enteredPass, "user"));
//            message = "Sign up successfully";
//            exppage = "login_page";
//        } else {
//            message = String.join(", ", validationErrors);
//            exppage = "sign_up_page";
//        }
//
//        System.out.println("Message: " + message); // Debugging statement
//    }


    public void clickLink(String linkName) {
        linkName = linkName.trim();
        switch (linkName) {
            case "Sign_up":
                this.is_in_signuppage = true;
                break;
            case "Forgot Password":
                this.is_in_forgotpasswordpage = true;
                break;
            default:
                throw new IllegalArgumentException("Unknown link: " + linkName);
        }
    }









    public void submitSignup() {
        validationErrors.clear();


        if (enteredUname == null || enteredUname.isEmpty()) {
            validationErrors.add("Name cannot be empty");
        } else if (listvalidUsers.containsKey(enteredUname)) {
            validationErrors.add("Name must be unique");
        }


        if (email == null || email.isEmpty()) {
            validationErrors.add("Email cannot be empty");
        } else if (!isValidEmail(email)) {
            validationErrors.add("Email must be unique");
        }


        if (phone == null || phone.isEmpty()) {
            validationErrors.add("Phone cannot be empty");
        } else if (phone.length() != 10) {
            validationErrors.add("Phone must be 10 characters long");
        }

        if (age == null || age.isEmpty()) {
            validationErrors.add("Age cannot be empty");
        }

        boolean isEmptyPass = enteredPass == null || enteredPass.isEmpty();

        if (isEmptyPass) {
            validationErrors.add("Password cannot be empty");
        } else if (enteredPass.length() < 8 || !containsSpecialCharacter(enteredPass)) {
            validationErrors.add("Password must be at least 8 characters long and include a special character");
        }


        if (confpass == null || confpass.isEmpty()) {
            validationErrors.add("Confirm password cannot be empty");
        } else if (!isEmptyPass && !confpass.equals(enteredPass)) {
            validationErrors.add("Password should equal confirm password");
        }


        logger.info("Validation Errors: " + validationErrors);

        if (validationErrors.isEmpty()) {
            listvalidUsers.put(enteredUname, new UserInfo(email, enteredPass, "user"));
            message = "Sign up successfully";
            exppage = "login_page";
        } else {
            message = String.join(", ", validationErrors);
            exppage = "sign_up_page";
        }


        logger.info("Message: " + message);
    }




    public String getValidationErrors() {
        return String.join(", ", validationErrors);
    }


    private boolean isPasswordExist(String pass) {
        return listvalidUsers.values().stream().anyMatch(userInfo -> userInfo.password.equals(pass));
    }

    public String selectpage(String type) {
        if (type.equals("user")) {
            return "user_page";
        } else if (type.equals("owner")) {
            return "owner_page";
        } else {
            return "admin_page";
        }
    }

    public void setMessage(String message) {
        this.message = message;

        logger.info("Message set to: " + message);
    }

    public String getMessage() {

        System.out.println("Getting message from the application");

        String retrievedMessage = this.message;

        System.out.println("Message retrieved: " + (retrievedMessage != null ? retrievedMessage : "null"));
        return retrievedMessage;
    }

    public String getExppage() {
        return exppage;
    }


    public boolean isUserInLoginTable(String username, String password, String userType) {
        if (listvalidUsers.containsKey(username)) {
            UserInfo userInfo = listvalidUsers.get(username);
            return userInfo.password.equals(password) && userInfo.userType.equals(userType);
        }
        return false;
    }


    private boolean isValidEmail(String email) {
        return listvalidUsers.values().stream().noneMatch(userInfo -> userInfo.email.equals(email));
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.length() == 10;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && containsSpecialCharacter(password);
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[!@#$%^&*()].*");
    }





    public void emailForPassChanging(String email) {
        this.email = email;
    }


    public void submitForgotPass() {
        validationErrors.clear();
        if (email == null || email.isEmpty()) {
            validationErrors.add("Email field cannot be empty");
            message = "Email field cannot be empty";
            exppage = "forgot_password_page";
        } else if (!listvalidUsers.values().stream().anyMatch(userInfo -> userInfo.email.equals(email))) {
            validationErrors.add("No account found with that email");
            message = "No account found with that email";
            exppage = "forgot_password_page";
        } else {
            message = "A password reset link has been sent to your email";
            exppage = "login_page";
        }
        logger.info("Password reset request: " + validationErrors);
    }






    public boolean isInLoginPage() {
        return "login_page".equals(currentPage);
    }

    public void goToLoginPage() {
        currentPage = "login_page";
    }

    public String getCurrentPage() {
        return currentPage;
    }




}