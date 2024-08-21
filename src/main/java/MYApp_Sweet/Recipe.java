
package MYApp_Sweet;

import static MYApp_Sweet.BeneficiaryUser.recipes;

public class Recipe {
    private String category;
    private String description;
    private String imageUrl;
    private int prepTime;
    private String difficulty;
    private double rating;
    private String username;
    private String email;
    private String phone;
    private String password;
    private int age;
    private String foodAllergies;
    private String sweetname;
    private String dietaryNeeds;

    public Recipe()
    {


      /*  this.username="validuser";
        this.email="validemail";
        this.phone="2348597";
        this.password="12345678@";
        this.foodAllergies="Gloten";
        this.sweetname="Choclate Cake";
        this.category = "Cakes";
        this.description = "TangyAndSweetLemonCookies ";
        //this.imageUrl = imageUrl;
        this.prepTime = 60;
        this.difficulty = "Medium ";
        this.rating = 4.5;
        this.dietaryNeeds="vegan";*/
        // this.description = "TangyAndSweetLemonCookies";
    }

    public Recipe(String sweetname, String category, String description, int prepTime, String difficulty, double rating)
    {

        this.sweetname = sweetname;  // Ensure this line is included
        this.category = category;
        this.description = description;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
        this.rating = rating;

    }

    /*public Recipe(String farah, String mail, String number, String sweet24, String gloten, String cakes, String deliciousChocolateCake, int i, String medium, double v)
    {

        this.username=username;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.foodAllergies=foodAllergies;
        this.sweetname=sweetname;
        this.category = category;
        this.description = description;
        //this.imageUrl = imageUrl;
        this.prepTime = prepTime;
        this.difficulty = difficulty;
        this.rating = rating;




    }*/

    public void setCategory(String category)
    {      //this.username="validuser";
        this.category = category;
    }

    public void setDescription(String description) {
        this.description =description;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFoodAllergies(String foodAllergies) {
        this.foodAllergies = foodAllergies;
    }

    public void setSweetname(String sweetname) {
        this.sweetname =sweetname;
    }

    public void setDietaryNeeds(String dietaryNeeds) {
        this.dietaryNeeds = dietaryNeeds;
    }
//getter

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

  /*  public String getImageUrl() {
        return imageUrl;
    }*/

    public int getPrepTime() {
        return prepTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public double getRating() {
        return rating;
    }
    public String getSweetname() {
        return sweetname;
    }
    public String getFoodAllergies() {
        return foodAllergies;
    }
    public String getUsername() {
        return sweetname;
    }

    public String getDietaryNeeds() {
        return dietaryNeeds;
    }
    public Recipe searchRecipeBySweetname(String sweetname) {
        for (Recipe recipe : recipes) {
            if (recipe.getSweetname() != null && recipe.getSweetname().equalsIgnoreCase(sweetname)) {
                return recipe;
            }
        }
        return null; // or throw an exception if appropriate
    }

    /* public Object findRecipeByName(String name) {
        // For now, return a dummy Recipe object or find the actual one
        return new Recipe("username", "email", "phone", "password", "foodAllergies", name, "category", "description", 30, "medium", 4.5);
    }*/
}
