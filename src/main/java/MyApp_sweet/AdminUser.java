package Admin_helperClass;

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
