package MYApp_Sweet;
import java.util.List;
import java.util.Map;
public class TopSellingItemsService {
    public List<Map<String, Object>> getTopSellingItems(String storeId) {
        return List.of(
                Map.of(
                        "productName", "Kunafa",
                        "quantitiesSold", 120,
                        "totalRevenue", 2400
                ),
                Map.of(
                        "productName", "Baqlawa",
                        "quantitiesSold", 85,
                        "totalRevenue", 1700
                ),
                Map.of(
                        "productName", "cookie",
                        "quantitiesSold", 89,
                        "totalRevenue", 1300
                )
        );
    }
}
