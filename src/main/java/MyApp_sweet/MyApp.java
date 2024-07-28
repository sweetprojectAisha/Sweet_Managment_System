package MyApp_sweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MyApp {
    public boolean is_in_loginpage=false;
    private Map<String, UserInfo> listvalidUsers = new HashMap<String,UserInfo>();
    private String enteredUname;
    private String enteredPass;

    private String message;
    private String exppage;


    public static class UserInfo {
        String password;
        String userType;

        public UserInfo(String password, String userType) {
            this.password = password;
            this.userType = userType;
        }
    }

    public void setValidUsers(List<Map<String,String>>users){
        for (Map<String,String>user:users){
            String username = user.get("username");
            String password = user.get("password");
            String userType = user.get("userType");
            listvalidUsers.put(username, new UserInfo(password, userType));
        }
    }

    public void enterCredentials(String username,String password){
        enteredUname=username;
        enteredPass=password;
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

    private boolean isPasswordExist(String pass) {
        return listvalidUsers.values().stream().anyMatch(userInfo -> userInfo.password.equals(pass));
    }

    public String selectpage(String type){
    if (type.equals("user")){
    return "user_page";
} else if (type.equals("owner")) {
        return "owner_page";
    }else {
        return "admin_page";
    }
    }
    public String getMessage(){
        return message;
    }

    public String getExppage(){
        return exppage;
    }
}
