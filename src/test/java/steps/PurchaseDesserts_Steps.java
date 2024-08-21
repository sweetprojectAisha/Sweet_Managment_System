
package steps;
import MYApp_Sweet.BeneficiaryUser;
import MYApp_Sweet.PurchasedProduct;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import MYApp_Sweet.Dessert;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PurchaseDesserts_Steps {
    BeneficiaryUser beneficiaryUser;
    PurchasedProduct purchasedProduct;
    Dessert dessert;
    List<PurchasedProduct> dessertsInventory;
    private String itemName;
    private double price;
    String name ;
    String availability ;
    String dietaryInfo;
    String date;

    private String purchaseDate;
    public String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    public PurchaseDesserts_Steps(BeneficiaryUser beneficiaryUser) {
        this.beneficiaryUser = beneficiaryUser;
        this.dessertsInventory = new ArrayList<>();
    }

    @Given("the user is on the desserts page")
    public void theUserIsOnTheDessertsPage() {
        // Initialization code or navigation logic if necessary
    }

    @Given("the list of available desserts includes:")
    public void theListOfAvailableDessertsIncludes(DataTable dataTable) {
        List<Map<String, String>> dessertsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dessert : dessertsList) {
            String name = dessert.get("dessertName");
            String date = dessert.get("date");
            double price = Double.parseDouble(dessert.get("price"));
            String availability = dessert.get("availability");
            String dietaryInfo = dessert.get("dietaryInfo");
            PurchasedProduct product = new PurchasedProduct(name, date, price, availability, dietaryInfo);
            dessertsInventory.add(product);
            System.out.println("Added to inventory: " + product.getItemName() + " - " + product.getAvailability());
        }
    }



    @When("the user selects a dessert:")
    public void theUserSelectsADessert(DataTable dataTable) {
        List<Map<String, String>> selectedDesserts = dataTable.asMaps(String.class, String.class);
        boolean found = false;

        for (Map<String, String> dessertMap : selectedDesserts) {
            String name = dessertMap.get("dessertName");
            String availability = dessertMap.get("availability");

            System.out.println("Trying to select dessert: " + name + " - " + availability); // Debugging statement

            for (PurchasedProduct product : dessertsInventory) {
                System.out.println("Checking inventory item: " + product.getItemName() + " - " + product.getAvailability()); // Debugging statement

                if (product.getItemName().equals(name) && product.getAvailability().equals(availability)) {
                    dessert = convertToDessert(product); // Convert to Dessert
                    itemName = name;
                    price = product.getPrice();
                    found = true;
                    break; // Exit loop once dessert is found
                }
            }

            // Assertion here to check if the dessert was found
            assertTrue("Selected dessert not found in the inventory: " + name + " - " + availability, found);
        }
    }



    private Dessert convertToDessert(PurchasedProduct product)
    {
        Dessert dessert = new Dessert();
        dessert.setDessertName(product.getItemName());
        dessert.setAvailability(product.getAvailability());
        return dessert;
    }


    @When("the user confirms the purchase")
    public void theUserConfirmsThePurchase() {
        assertNotNull("No dessert selected", dessert);

        // Check dessert availability and perform actions
        assertEquals("Dessert availability should be 'In Stock'", "In Stock", dessert.getAvailability());

        dessert.setAvailability("Out of Stock");
        purchasedProduct = new PurchasedProduct(itemName, price, getCurrentDate());
        beneficiaryUser.addPurchasedProduct(itemName, price, getCurrentDate());
        System.out.println("Added purchased product: " + purchasedProduct);
    }






    @Then("the desserts inventory should reflect the changes")
    public void theDessertsInventoryShouldReflectTheChanges() {
        boolean productFound = false;
        for (PurchasedProduct product : dessertsInventory) {
            if (product.getItemName().equals(itemName) && product.getPrice() == price) {
                productFound = true;
                break;
            }
        }
        assertTrue("Purchased product not found in inventory", productFound);
    }



    @Then("the user should be redirected to the {string}")
    public void theUserShouldBeRedirectedToThe(String expectedPage) {
        String actualPage = beneficiaryUser.getRedirectPage();
        assertEquals(expectedPage, actualPage);
    }

    @Given("the user has the following details:")
    public void theUserHasTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> userDetailsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> userDetails : userDetailsList)
        {
            String userID = userDetails.get("userID");
            String username = userDetails.get("username");
            String email = userDetails.get("email");
            String phone = userDetails.get("phone");
            int age = Integer.parseInt(userDetails.get("age"));
            String password = userDetails.get("password");
            String dietaryNeeds = userDetails.get("dietaryNeeds");
            String foodAllergies = userDetails.get("foodAllergies");

            beneficiaryUser.updateDetails(userID, username, email, phone, age, password, dietaryNeeds, foodAllergies);
        }
    }

    @Then("the user should see this msg {string}")
    public void theUserShouldSeeThisMsg(String string) {
        // Write code here that turns the phrase above into concrete actions

    }


}
