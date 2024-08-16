package Admin_helperClass;

public class StoreOwnerAccount {

    private String username;
    private String email;

    public StoreOwnerAccount(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
