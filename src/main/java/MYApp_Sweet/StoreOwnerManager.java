package MYApp_Sweet;
import java.util.ArrayList;
import java.util.List;

public class StoreOwnerManager {
    private List<StoreOwner> owners = new ArrayList<>();

    public boolean addStoreOwner(String username, String email) {
        StoreOwner newOwner = new StoreOwner(username, email);
        return owners.add(newOwner);
    }

    public List<StoreOwner> getOwners() {
        return owners;
    }
}
