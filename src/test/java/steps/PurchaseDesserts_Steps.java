package sweet.pal.AcceptanaceTest;
import sweet.pal.BeneficiaryUser;
import sweet.pal.PurchasedProduct;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import sweet.pal.Dessert;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
       name = dessert.get("dessertName");
       date=dessert.get("date");
     price = Double.parseDouble(dessert.get("price"));
       availability = dessert.get("availability");
      dietaryInfo = dessert.get("dietaryInfo");
      PurchasedProduct product = new PurchasedProduct(name,date, price, availability, dietaryInfo);
      dessertsInventory.add(product);
    }
  }

  @When("the user selects a dessert:")
  public void theUserSelectsADessert(DataTable dataTable) {
    List<Map<String, String>> selectedDesserts = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> dessert : selectedDesserts) {
      String name = dessert.get("dessertName");
      String availability = dessert.get("availability");

      for (PurchasedProduct product : dessertsInventory) {
        if (product.getItemName().equals(name) && product.getAvailability().equals(availability)) {
          beneficiaryUser.selectRecipeForFeedback(name, ""); // purchaseDate is irrelevant in this case
        }
      }
    }
  }

 // @When("the user confirms the purchase")
  public  void theUserConfirmsThePurchase()
  {
    if (dessert.getDessertName() != null) {
      String availability = dessert.getDessertName()+ dessert.getAvailability();
      if (availability.equals("In Stock")) {
        // Simulate the purchase by updating the inventory
         dessert.setAvailability("Out of Stock");
        // Update the BeneficiaryUser state (e.g., add to purchased products)
          PurchasedProduct purchasedProduct = new PurchasedProduct(itemName, price, purchaseDate);
          System.out.println("Purchase successful");
      } else {
        System.out.println("Dessert is out of stock");
      }
    } else {
      System.out.println("No dessert selected");
    }
  }

  @Then("the user should see {string}")
  public void theUserShouldSee(String expectedMessage) {
    String actualMessage = beneficiaryUser.getPurchaseMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  @Then("the desserts inventory should reflect the changes")
  public void theDessertsInventoryShouldReflectTheChanges() {
    // Implement logic to verify that the inventory was updated correctly
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
}
