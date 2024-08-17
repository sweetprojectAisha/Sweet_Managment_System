package MYApp_Sweet;
import java.util.HashMap;
import java.util.Map;
public class StoreProfitService {
    private Map<String, Double> storeProfits;

    public StoreProfitService() {
        storeProfits = new HashMap<>();
        storeProfits.put("Store A", 12000.00);
        storeProfits.put("Store B", 15000.00);
        storeProfits.put("Store C", 18000.00);
    }

    public Map<String, Double> getStoreProfits() {
        return storeProfits;
    }
}
