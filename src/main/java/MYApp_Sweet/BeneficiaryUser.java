package MYApp_Sweet;
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
    /// temp :
    private String username;
    private String password;
    private String confirmPassword;
    private String phone;
    private String age;
    private String type;
    private String foodallergies;
    /// temp ////
    public BeneficiaryUser(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    // purchased
    private boolean checkIfPurchaseSuccessful() {
        // Check if there is a selected product
        if (this.selectedProduct == null) return false; // No product selected
        // Check if the selected product is in stock
        if (!isProductInStock(this.selectedProduct.getItemName()))
            return false; // Product is out of stock
        // Check if the purchase has been recorded
        boolean purchaseRecorded = isPurchaseRecorded(this.selectedProduct.getItemName(), this.selectedProduct.getPurchaseDate());
        if (!purchaseRecorded) {
            return false;
        }
        return true;
    }

private boolean isPurchaseRecorded(String itemName, String purchaseDate) {
    if (itemName.equals(p.getItemName()) && purchaseDate.equals(p.getPurchaseDate())) {
        return true;
    }
    return false;
}


    public boolean isProductInStock(String itemName) {
        // Iterate through available desserts to find the matching item
        for (Dessert dessert : availableDesserts) {
            if (itemName.equals(dessert.getDessertName()) && "In Stock".equals(dessert.getAvailability())) {
                return true;
            }
        }
        return false;
    }

    public static List<Recipe> getRecipes() {
        return recipes;
    }

    public static void setRecipes(List<Recipe> recipes) {
        BeneficiaryUser.recipes = recipes;
    }

    public BeneficiaryUser() {
        errors = new HashMap<>();
        errorDisplayed = false;
    }

    ///////////////////////****************************//////////////////////////////
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


    public void fillname_category(String name, String category) {
        // Lazy initialization of the errors map
        if (errors == null) {
            errors = new HashMap<>();
        }

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
        if (searchName == null) {
            System.out.println("Search name is null.");
            return false;
        }

        for (Recipe recipe : recipes) {
            if (searchName.equals(recipe.getSweetname())) {
                return true;
            }
        }
        System.out.println("No recipe matches your dietary needs.");
        return false;
    }

    ///////////////////////////////////
    public Recipe filter_by_FoodAllergiesAndIngredient(String dietaryNeeds, String foodAllergies) {
        for (Recipe recipe : recipes) {
            // Check if dietaryNeeds is null or empty
            if (recipe.getDietaryNeeds() != null && recipe.getDietaryNeeds().equals(dietaryNeeds) &&
                    recipe.getFoodAllergies() != null && recipe.getFoodAllergies().equals(foodAllergies)) {

                System.out.println("This recipe matches your dietary needs: " + dietaryNeeds +
                        " and has no food allergies: " + foodAllergies +
                        "\nDescription: " + recipe.getDescription() +
                        "\nPrep Time: " + recipe.getPrepTime() +
                        "\nDifficulty: " + recipe.getDifficulty() +
                        "\nRating: " + recipe.getRating());
                return recipe;
            }
        }
        System.out.println("No recipe matches your dietary needs and food allergies.");
        return null;
    }


    public Recipe recipe_without_FoodAllergies(String foodAllergies) {
        for (Recipe recipe : recipes) {
            // Check if FoodAllergies in recipe is not null before comparing
            String recipeFoodAllergies = recipe.getFoodAllergies();
            if (recipeFoodAllergies != null && recipeFoodAllergies.equals(foodAllergies)) {
                System.out.println("This recipe avoids: " + foodAllergies + "\nDescription: "
                        + recipe.getDescription() + "\nPreparation Time: "
                        + recipe.getPrepTime() + "\nDifficulty: "
                        + recipe.getDifficulty() + "\nRating: "
                        + recipe.getRating());
                return recipe;
            }
        }
        System.out.println("No recipe matches your food allergies.");
        return null;
    }


    public Recipe recipe_with_ditearyNeeds(String dietaryNeeds) {
        for (Recipe recipe : recipes) {
            // Check if dietaryNeeds in recipe is not null before comparing
            String recipeDietaryNeeds = recipe.getDietaryNeeds();
            if (recipeDietaryNeeds != null && recipeDietaryNeeds.equals(dietaryNeeds)) {
                System.out.println("This recipe matches your dietary needs: "
                        + dietaryNeeds + "\nDescription: " + recipe.getDescription()
                        + "\nPreparation Time: " + recipe.getPrepTime()
                        + "\nDifficulty: " + recipe.getDifficulty()
                        + "\nRating: " + recipe.getRating());
                return recipe;
            }
        }
        System.out.println("No recipe matches your dietary needs.");
        return null;
    }


    public Recipe Filter_by_both(String dietaryNeeds, String foodAllergies) {
        for (Recipe recipe : recipes) {
            // Get the dietary needs and food allergies from the recipe
            String recipeDietaryNeeds = recipe.getDietaryNeeds();
            String recipeFoodAllergies = recipe.getFoodAllergies();

            // Check if both are not null before comparing
            if (recipeDietaryNeeds != null && recipeDietaryNeeds.equals(dietaryNeeds)
                    && recipeFoodAllergies != null && recipeFoodAllergies.equals(foodAllergies)) {
                System.out.println("This recipe matches your dietary needs: " + dietaryNeeds
                        + " and avoids food allergies: " + foodAllergies
                        + "\nDescription: " + recipe.getDescription()
                        + "\nPreparation Time: " + recipe.getPrepTime()
                        + "\nDifficulty: " + recipe.getDifficulty()
                        + "\nRating: " + recipe.getRating());
                return recipe;
            }
        }
        System.out.println("No recipe matches your criteria.");
        return null;
    }


    public void filterRecipe() {
        Recipe recipe = new Recipe();
        System.out.println("you will see filter recipe without filtering FoodAllergies and DietaryNeed ");

        System.out.println(" the recipe of: " +
                recipe.getDescription()
                + "\n" + recipe.getFoodAllergies() + "\n" + recipe.getPrepTime() +
                "\n" + recipe.getDifficulty() + "\n" + recipe.getRating());


    }

    public void applyFoodAllergiesFilter() {
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

    public void updateFoodAllergies(String foodAllergies) {
        Recipe recipe = new Recipe();
        if (foodAllergies == null || foodAllergies.isEmpty()) {
            errors.put("foodAllergies", "Food allergies cannot be empty");
            errorDisplayed = true;
        } else {
            recipe.setFoodAllergies(foodAllergies);
            errorDisplayed = false;
            System.out.println("Updated food allergies to: " + foodAllergies);
        }
    }

    public void updateDietaryNeeds(String dietaryNeeds) {
        Recipe recipe = new Recipe();
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

    //communicate **************************
    public void selectContact(String contactName) {
        this.selectedContact = contactName;
        System.out.println("Contact " + contactName + " has been selected.");
    }

    public void sendInquiry(String message) {
        this.lastInquiryMessage = message;
        // Assuming response is generated here
        this.response = "Inquiry sent to " + selectedContact + ": " + message;
        // Update the communication log
        this.communicationLog.add("Sent to " + selectedContact + ": " + message);
        System.out.println("Inquiry message sent: " + message);
    }

    public String getResponse() {
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
        return false;
    }

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
            displayError("Cannot provide feedback on an unpurchased product");
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
    return java.time.LocalDate.now().toString();
}


    public void verifyFeedbackLog(String itemName, String rating, String comment, String feedbackDate, String feedbackStatus) {
        if (selectedProduct == null) {
            System.out.println("Error: No product selected for feedback verification.");
            return;  // Early return if no product is selected
        }

        List<Feedback> feedbackList = selectedProduct.getFeedbackList();
        boolean feedbackFound = false;

        for (Feedback feedback : feedbackList) {
            if (feedback.matches(itemName, rating, comment, feedbackDate, feedbackStatus)) {
                feedbackFound = true;
                break;
            }
        }

        if (!feedbackFound) {
            System.out.println("Error: Feedback entry not found in the feedback log.");
            throw new AssertionError("Expected feedback entry not found.");
        }
    }


    public String getConfirmationMessage() {
        return "Your action was successful!"; // This could be dynamic based on the last action
    }


    public String getDisplayedErrorMessage() {
        return this.errorMessage; // Assuming errorMessage is a field that stores the last error message
    }

    //***********************************
    public void addPurchasedProduct(String itemName, double price, String currentDate) {
        PurchasedProduct product = new PurchasedProduct(itemName, price, currentDate);
        purchasedProducts.add(product);
    }


    public String getRedirectPage() {
        boolean purchaseSuccessful = checkIfPurchaseSuccessful();
        return  "order_summary" ;
    }

    public String getPurchaseMessage() {
        boolean purchaseSuccessful = checkIfPurchaseSuccessful();
        if (purchaseSuccessful) {
            return "Purchase successful";
        } else if (p.getAvailability()==" ") {
            return "Dessert is out of stock";
        } else {
            return "An issue occurred during purchase";
        }
    }

    public void updateDetails(String userID, String username, String email, String phone, int age, String password, String dietaryNeeds, String foodAllergies) {
        if (userID == null || userID.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }

    }
    /////////////////////////////////////
    public Recipe searchRecipeBySweetname(String sweetname) {
        if (sweetname == null || sweetname.isEmpty()) {
            System.err.println("Sweetname is null or empty.");
            return null;
        }

        for (Recipe recipe : recipes) {
            System.out.println("Checking recipe: " + recipe.getSweetname());

            if (recipe.getSweetname() != null && recipe.getSweetname().equalsIgnoreCase(sweetname)) {
                System.out.println("Found recipe: " + recipe.getSweetname());
                return recipe;
            }
        }

        System.err.println("No recipe found for sweet name: " + sweetname);
        return null;
    }


    ////////////////// temprory user values
   public String getUsername() {
    return username;
}

    public String getEmail() {
        return "validemail";}

    public String getPhone() {
        return "0599873421";}

    public String getAge() {
        return "21"; }

    public String getPassword()
    {
        return "12345678a@"; }

    public String getFoodAllergies() {
        return "Gluten"; }



}
