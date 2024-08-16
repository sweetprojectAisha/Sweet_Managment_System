package Admin_helperClass;


public class SupplierAccountManager {

    private boolean adminLoggedIn = false;
    private boolean supplierAccountExists = false;
    private boolean accountDeleted = false;

    // log in the admin
    public void logInAdmin() {
        adminLoggedIn = true;
    }

    public void createSupplierAccount(String username) {
        if (adminLoggedIn) {
            supplierAccountExists = true;
            System.out.println("Raw material supplier account with username " + username + " created.");
        } else {
            throw new IllegalStateException("Admin is not logged in.");
        }
    }

    public void deleteSupplierAccount() {
        if (supplierAccountExists) {
            accountDeleted = true;
            supplierAccountExists = false;
            System.out.println("Raw material supplier account has been deleted.");
        } else {
            throw new IllegalStateException("Raw material supplier account does not exist.");
        }
    }

    // Getters
    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

    public boolean isSupplierAccountExists() {
        return supplierAccountExists;
    }

    public boolean isAccountDeleted() {
        return accountDeleted;
    }
}
