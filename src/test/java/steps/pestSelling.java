package Admin;

import Admin_helperClass.AdminUser;
import Admin_helperClass.Store;
import Admin_helperClass.TopSellingItemsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;

public class pestSelling {

    private AdminUser admin;
    private Store selectedStore;
    private List<Map<String, Object>> topSellingItems;
    private TopSellingItemsService itemsService = new TopSellingItemsService();

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        admin = new AdminUser();
        admin.logIn();
        System.out.println("Admin is logged in.");
    }

    @When("the admin accesses the top-selling items page")
    public void the_admin_accesses_the_top_selling_items_page() {
        if (admin.isLoggedIn()) {
            System.out.println("Admin accessed the top-selling items page.");
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @When("chooses a store from the available options")
    public void chooses_a_store_from_the_available_options() {
        if (admin.isLoggedIn()) {
            selectedStore = new Store("store123", "Store Name");
            System.out.println("Admin chose the store: " + selectedStore.getName());
            topSellingItems = itemsService.getTopSellingItems(selectedStore.getId());
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @Then("the admin should view a list of the highest-selling items for the chosen store")
    public void the_admin_should_view_a_list_of_the_highest_selling_items_for_the_chosen_store() {
        if (selectedStore != null && topSellingItems != null) {
            System.out.println("List of top-selling items for " + selectedStore.getName() + ":");
            for (Map<String, Object> item : topSellingItems) {
                System.out.println("Product Name: " + item.get("productName"));
            }
        } else {
            throw new AssertionError("Store was not selected or no top-selling items available.");
        }
    }

    @Then("the list should display product names, quantities sold, and total revenue")
    public void the_list_should_display_product_names_quantities_sold_and_total_revenue() {
        if (selectedStore != null && topSellingItems != null) {
            for (Map<String, Object> item : topSellingItems) {
                System.out.println("Product Name: " + item.get("productName"));
                System.out.println("Quantities Sold: " + item.get("quantitiesSold"));
                System.out.println("Total Revenue: $" + item.get("totalRevenue"));
            }
        } else {
            throw new AssertionError("Store was not selected or no top-selling items available.");
        }
    }
}
