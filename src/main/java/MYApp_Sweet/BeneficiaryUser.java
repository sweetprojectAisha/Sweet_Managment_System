import MYApp_Sweet.PurchasedProduct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeneficiaryUser {
    public static List<Recipe> recipes = new ArrayList<>();
    private List<Dessert> availableDesserts = new ArrayList<>();
    private List<PurchasedProduct> purchasedProducts = new ArrayList<>();
    private boolean errorDisplayed;
    private Map<String, String> errors;
    private String selectedContact;
    private String lastInquiryMessage;
    private String response;
    private List<String> communicationLog = new ArrayList<>();
    private PurchasedProduct selectedProduct;
    private String errorMessage;
    private PurchasedProduct p;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String phone;
    private String age;
    private String type;
    private String foodAllergies;

    public BeneficiaryUser(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    private boolean checkIfPurchaseSuccessful() {
        if (this.selectedProduct == null) return false;
        if (!isProductInStock(this.selectedProduct.getItemName())) return false;
        return isPurchaseRecorded(this.selectedProduct.getItemName(), this.selectedProduct.getPurchaseDate());
    }

    private boolean isPurchaseRecorded(String itemName, String purchaseDate) {
        if (itemName.equals(p.getItemName()) && purchaseDate.equals(p.getPurchaseDate())) return true;
        return false;
    }

    public boolean isProductInStock(String itemName) {
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

    public Recipe filter_by_FoodAllergiesAndIngredient(String dietaryNeeds, String foodAllergies) {
        for (Recipe recipe : recipes) {
            if (dietaryNeeds != null && dietaryNeeds.equals(recipe.getDietaryNeeds()) &&
                foodAllergies != null && foodAllergies.equals(recipe.getFoodAllergies())) {
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

    public Recipe filter_by_both(String dietaryNeeds, String foodAllergies) {
        for (Recipe recipe : recipes) {
            String recipeDietaryNeeds = recipe.getDietaryNeeds();
            String recipeFoodAllergies = recipe.getFoodAllergies();
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
        System.out.println("No recipe matches your dietary needs and food allergies.");
        return null;
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public void sendInquiry(String message) {
        lastInquiryMessage = message;
        System.out.println("Inquiry sent: " + message);
    }

    public void communicate(String response) {
        communicationLog.add(response);
    }

    public void add_to_CommunicationLog(String response) {
        communicationLog.add(response);
    }

    public List<String> getCommunicationLog() {
        return communicationLog;
    }
}
