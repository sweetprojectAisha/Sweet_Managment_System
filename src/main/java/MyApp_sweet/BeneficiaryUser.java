package sweet.pal;
import sweet.pal.PurchasedProduct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class BeneficiaryUser {
    public static List<Recipe> recipes = new ArrayList<>(); // Initialize the static list
    private List<Dessert> availableDesserts = new ArrayList<>();
    private List<PurchasedProduct> purchasedProducts = new ArrayList<>(); // Declare and initialize purchasedProducts list
    private boolean errorDisplayed;
    private Map<String, String> errors;
    private String selectedContact;
    private String lastInquiryMessage;
    private String response;
    private List<String> communicationLog = new ArrayList<>();
    private PurchasedProduct selectedProduct; // Assuming this is required
    private String errorMessage;
    private PurchasedProduct p;
    private boolean checkIfPurchaseSuccessful()
    {
        // Check if there is a selected product
        if (this.selectedProduct == null) {
            return false; // No product selected
        }

        // Check if the selected product is in stock
        if (!isProductInStock(this.selectedProduct.getItemName())) {
            return false; // Product is out of stock
        }

        // Check if the purchase has been recorded
        boolean purchaseRecorded = isPurchaseRecorded(this.selectedProduct.getItemName(), this.selectedProduct.getPurchaseDate());
        if (!purchaseRecorded) {
            return false; // Purchase was not recorded
        }

        return true;
    }

    private boolean isPurchaseRecorded(String itemName, String purchaseDate)
    {
        if(itemName==p.getItemName()&&p.getPurchaseDate()==purchaseDate)
            return  true;
        return false;
    }

    private boolean isProductInStock(String itemName)
    { if(itemName==p.getItemName()&&p.getAvailability()==availableDesserts)
    return true;
        else
            return false;
    }

    public static List<Recipe> getRecipes() {
        return recipes;
    }

    public static void setRecipes(List<Recipe> recipes)
    {
        BeneficiaryUser.recipes = recipes;
    }

    public BeneficiaryUser() {
        errors = new HashMap<>();
        errorDisplayed = false;
    }

    public Recipe clickBrowseAllRecipes(String category) {
        for (Recipe recipe : recipes) {
            if (recipe.getCategory().equals(category)) {
                System.out.println("Browsing all recipes for category: " + category);
                System.out.println("Description: " + recipe.getDescription());
                System.out.println("Prep Time: " + recipe.getPrepTime() + " minutes");
                System.out.println("Difficulty: " + recipe.getDifficulty());
                System.out.println("Rating: " + recipe.getRating());
                return recipe;
            }
        }
        System.out.println("No recipes found for category: " + category);
        return null;
    }

    public void clickSearch() {
        // Simulate clicking search
        System.out.println("Search button clicked");
    }

    public Recipe searchRecipe(String name) {
       // System.out.println(" the recipe of: " + name);
        for (Recipe recipe : recipes) {
            if (recipe.getSweetname().equals(name)) {
                System.out.println( " the recipe of: " + name+ recipe.getDescription() +
                        "\n" + recipe.getFoodAllergies() + "\n" + recipe.getPrepTime() +
                        "\n" + recipe.getDifficulty() + "\n" + recipe.getRating());
                return recipe;
            } System.out.println(" doesnt exist" );
        }
        return null;
    }



    public void fillname_category(String name, String category) {
        if (name == null || name.isEmpty()) {
            errors.put("name", "Name cannot be empty");
            errorDisplayed = true;
        }
        if (category == null || category.isEmpty()) {
            errors.put("category", "Category cannot be empty");
            errorDisplayed = true;
        }
        if (!errors.containsKey("name") && !errors.containsKey("category")) {
            errorDisplayed = false;
        }
    }

    public boolean isErrorDisplayed(String field) {
        return errors.containsKey(field);
    }

    public boolean checkIfRecipeExists(String searchName) {
        for (Recipe recipe : recipes) {
            if (searchName.equals(recipe.getSweetname())) {
                return true;
            }
        }
        return false;
    }

    public Recipe filter_by_FoodAllergiesAndIngredient(String FoodAllergies,String Ingredient )
    {
        for (Recipe recipe : recipes) {
            if (recipe.getDietaryNeeds().equals(Ingredient)) {
                System.out.println( " Recipe filter_by: " +  recipe.getDietaryNeeds()+
                        "\n" + recipe.getFoodAllergies() );
                return recipe;
            } System.out.println(" doesnt exist" );
        }
        return null;
    }

    public Recipe recipe_without_FoodAllergies(String FoodAllergies) {
        for (Recipe recipe : recipes) {
            if (recipe.getFoodAllergies().equals(FoodAllergies)) {
                System.out.println(" this recipe without: " + FoodAllergies + recipe.getDescription() +
                        "\n" + recipe.getPrepTime() +
                        "\n" + recipe.getDifficulty() + "\n" + recipe.getRating());
                return recipe;
            }
            System.out.println(" doesnt exist");
        }
        return null;
    }

    public Recipe recipe_with_ditearyNeeds(String dietaryNeeds)
    {
        for (Recipe recipe : recipes) {
            if (recipe.getDietaryNeeds().equals(dietaryNeeds)) {
                System.out.println(" this recipe with your dietaryNeed : " + dietaryNeeds + recipe.getDescription() +
                        "\n" + recipe.getPrepTime() +
                        "\n" + recipe.getDifficulty() + "\n" + recipe.getRating());
                return recipe;
            }
            System.out.println(" doesnt exist");
        }
        return null;
    }

    public Recipe Filter_by_both(String dietaryNeeds, String foodAllergies)
    {
        for (Recipe recipe : recipes) {
            if (recipe.getDietaryNeeds().equals(dietaryNeeds)&&recipe.getFoodAllergies().equals(foodAllergies)) {
                System.out.println(" this recipe with your dietaryNeed : " + dietaryNeeds +"Without foodAllergies"+recipe.getFoodAllergies()+ recipe.getDescription() +
                        "\n" + recipe.getPrepTime() +
                        "\n" + recipe.getDifficulty() + "\n" + recipe.getRating());
                return recipe;
            }
            System.out.println(" doesnt exist");
        }
        return null;

    }

    public void filterRecipe()

    {Recipe recipe=new Recipe();
        System.out.println("you will see filter recipe without filtering FoodAllergies and DietaryNeed ");

            System.out.println( " the recipe of: " +
                             recipe.getDescription()
                    + "\n" + recipe.getFoodAllergies() + "\n" + recipe.getPrepTime() +
                    "\n" + recipe.getDifficulty() + "\n" + recipe.getRating());



    }

    public void applyFoodAllergiesFilter()
    {
        if (recipes == null || recipes.isEmpty()) {
            System.out.println("No recipes available to filter.");
            return;
        }

        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getFoodAllergies() != null && !recipe.getFoodAllergies().contains(recipe.getFoodAllergies())) {
                filteredRecipes.add(recipe);
            }
        }

        if (filteredRecipes.isEmpty()) {
            System.out.println("No recipes found after applying the food allergies filter.");
        } else {
            System.out.println("Filtered recipes based on food allergies:");
            for (Recipe filteredRecipe : filteredRecipes) {
                System.out.println("Recipe: " + filteredRecipe.getSweetname());
            }
        }
    }

    public void updateFoodAllergies(String foodAllergies)
    {       Recipe recipe=new Recipe();
        if (foodAllergies == null || foodAllergies.isEmpty()) {
            errors.put("foodAllergies", "Food allergies cannot be empty");
            errorDisplayed = true;
        } else {
            recipe.setFoodAllergies(foodAllergies);
            errorDisplayed = false;
            System.out.println("Updated food allergies to: " + foodAllergies);
        }
    }

    public void updateDietaryNeeds(String dietaryNeeds)
    { Recipe recipe=new Recipe();
        if (dietaryNeeds == null || dietaryNeeds.isEmpty()) {
            errors.put("dietaryNeeds", "Dietary needs cannot be empty");
            errorDisplayed = true;
        } else {
            // Assuming there's a dietaryNeeds field in BeneficiaryUser or Recipe
            recipe.setDietaryNeeds(dietaryNeeds); // if dietaryNeeds are stored in Recipe for simplicity
            errorDisplayed = false;
            System.out.println("Updated dietary needs to: " + dietaryNeeds);
        }
    }
//communicate ****************************
    public void selectContact(String contactName) {
        this.selectedContact = contactName;
        System.out.println("Contact " + contactName + " has been selected.");
    }

    public void sendInquiry(String message)
    {
        this.lastInquiryMessage = message;
        // Assuming response is generated here
        this.response = "Inquiry sent to " + selectedContact + ": " + message;
        // Update the communication log
        this.communicationLog.add("Sent to " + selectedContact + ": " + message);
        System.out.println("Inquiry message sent: " + message);
    }

    public String getResponse()
    {
return this.response;
    }

    public List<String> getCommunicationLog() {
        return this.communicationLog;
    }

    public void setAvailableDesserts(List<Dessert> availableDesserts) {
        this.availableDesserts = availableDesserts;

    }


//******************************

    public boolean isProductPurchased(String itemName, String purchaseDate) {
        for (PurchasedProduct product : purchasedProducts) { //  list named purchasedProducts
            if (product.getItemName().equals(itemName) && product.getPurchaseDate().equals(purchaseDate)) {
                return true;
            }
        }
        return false;}

    public void selectRecipeForFeedback(String itemName, String purchaseDate) {
        for (PurchasedProduct product : purchasedProducts) {
            if (product.getItemName().equals(itemName) && product.getPurchaseDate().equals(purchaseDate)) {
                this.selectedProduct = product; // field named selectedProduct
                return;
            }
        }
        displayError("Product not found or not purchased on the specified date.");
    }

    public void displayError(String errorMessage) {
        this.errorMessage = errorMessage; //  field named errorMessage
        System.out.println("Error: " + errorMessage);
    }

    public void submitFeedback(String rating, String comment) {
        if (this.selectedProduct == null) {
            displayError("No product selected for feedback.");
            return;
        }

        Feedback feedback = new Feedback(); //  Feedback class
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setFeedbackDate(getCurrentDate()); // Implement getCurrentDate() to return today's date
        feedback.setFeedbackStatus("Submitted");

        //  method to add feedback
        this.selectedProduct.addFeedback(feedback);

        System.out.println("Feedback submitted successfully.");
    }

    private String getCurrentDate() {
        return  getCurrentDate();
    }

    public void verifyFeedbackLog(String itemName, String rating, String comment, String feedbackDate, String feedbackStatus) {
        for (Feedback feedback : selectedProduct.getFeedbackList()) { //  list of feedback in selectedProduct
            if (feedback.getRating().equals(rating) &&
                    feedback.getComment().equals(comment) &&
                    feedback.getFeedbackDate().equals(feedbackDate) &&
                    feedback.getFeedbackStatus().equals(feedbackStatus)) {
                System.out.println("Feedback verified successfully.");
                return;
            }
        }
        displayError("Feedback verification failed.");
    }

    public String getConfirmationMessage() {
        return "Your action was successful!"; // This could be dynamic based on the last action
    }


    public String getDisplayedErrorMessage() {
        return this.errorMessage; // Assuming errorMessage is a field that stores the last error message
    }
//***********************************
public void addPurchasedProduct(String itemName, double price, String currentDate)
{
    PurchasedProduct product = new PurchasedProduct(itemName, price, currentDate);
    purchasedProducts.add(product);
}

    public String getRedirectPage()
    {
        boolean purchaseSuccessful = checkIfPurchaseSuccessful(); // Implement this method as needed

        if (purchaseSuccessful) {
            return "order_summary"; // Page to show order summary
        } else {
            return "desserts_page"; // Page to go back to desserts if something went wrong
        }

    }


    public String getPurchaseMessage()
    {
        boolean purchaseSuccessful = checkIfPurchaseSuccessful();

        if (purchaseSuccessful) {
            return "Purchase successful";
        } else {
            return "Dessert is out of stock or some other issue occurred";
        }
    }

    public void updateDetails(String userID, String username, String email, String phone, int age, String password, String dietaryNeeds, String foodAllergies)
    {

    }
}
