package steps;
import MYApp_Sweet.CommunicationSys;
import MYApp_Sweet.DisplayStatisticsByCityC;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DisplayStatisticsByCity {
    DisplayStatisticsByCityC ds;
    private List<DisplayStatisticsByCityC.User> currentUsers;
    public DisplayStatisticsByCity() {
        ds = DisplayStatisticsByCityC.getInstance();

    }
    @Given("the following users are registered:")
    public void the_following_users_are_registered(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            String name = columns.get("Name");
            String city = columns.get("City");
            try{
            ds.addUser(name, city);
            assertTrue(true);
            }catch (Exception ex){
                fail();
            }
        }
    }


    @Then("the statistics should be displayed as follows:")
    public void the_statistics_should_be_displayed_as_follows(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        Map<String, Integer> expectedStatistics = new HashMap<>();
        for (Map<String, String> row : expectedData) {
            String city = row.get("City");
            int count = Integer.parseInt(row.get("Number of Users"));
            expectedStatistics.put(city, count);
        }


        Map<String, Integer> actualStatistics = ds.gatherStatisticsByCity();
        assertEquals(expectedStatistics, actualStatistics);
    }
}
