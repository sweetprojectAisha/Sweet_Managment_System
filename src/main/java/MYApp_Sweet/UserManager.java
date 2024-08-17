package MYApp_Sweet;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private Map<String, Boolean> storeOwnerAccounts = new HashMap<>();
    private Map<String, Boolean> supplierAccounts = new HashMap<>();

    public void addStoreOwner(String username) {
        storeOwnerAccounts.put(username, true);
    }

    public void deleteStoreOwner(String username) {
        storeOwnerAccounts.put(username, false);
    }

    public boolean isStoreOwnerExists(String username) {
        return storeOwnerAccounts.getOrDefault(username, false);
    }

    public void addSupplier(String username) {
        supplierAccounts.put(username, true);
    }

    public void deleteSupplier(String username) {
        supplierAccounts.put(username, false);
    }

    public boolean isSupplierExists(String username) {
        return supplierAccounts.getOrDefault(username, false);
    }
}
