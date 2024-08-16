package Admin_helperClass;

import java.util.HashMap;
import java.util.Map;

public class UserStatisticsService {

    public Map<String, Integer> getUserCountsByCity() {
        Map<String, Integer> userCounts = new HashMap<>();
        userCounts.put("Nablus", 1500);
        userCounts.put("Salfeet", 1200);
        userCounts.put("Ramallha", 900);
        return userCounts;
    }
}
