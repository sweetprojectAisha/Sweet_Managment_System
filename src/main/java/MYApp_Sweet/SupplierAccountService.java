package MYApp_Sweet;

public class SupplierAccountService {
    private boolean accountCreated = false;

    public boolean createSupplierAccount(String username, String email) {
        if (username != null && email != null) {
            accountCreated = true;
            return true;
        }
        return false;
    }

    public boolean isAccountCreated() {
        return accountCreated;
    }
}
