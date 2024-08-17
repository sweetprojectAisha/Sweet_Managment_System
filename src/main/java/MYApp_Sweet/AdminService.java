package MYApp_Sweet;

public class AdminService {
    private boolean loggedIn;

    public boolean login() {
        loggedIn = true;
        return loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public StoreOwnerAccount createStoreOwnerAccount(String username, String email) {
        if (!loggedIn) {
            throw new IllegalStateException("Admin is not logged in.");
        }
        return new StoreOwnerAccount(username, email);
    }
}

