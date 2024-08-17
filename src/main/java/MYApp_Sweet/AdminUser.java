package MYApp_Sweet;

public class AdminUser {
    private boolean loggedIn;

    public AdminUser() {
        this.loggedIn = false;
    }

    public void logIn() {
        this.loggedIn = true;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
