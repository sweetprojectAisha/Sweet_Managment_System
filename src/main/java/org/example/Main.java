package org.example;

import MYApp_Sweet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MyApp app = MyApp.getInstance();
        BusinessAccount businessAccount = BusinessAccount.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMainMenu();

            String option = scanner.nextLine();


            switch (option) {
                case "1":
                    handleSignUp(app, scanner);
                    break;

                case "2":

                    handleLogIn(app, scanner);
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Welcome to Sweet Management System");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        System.out.print("Please choose an option: ");
    }

    private static void handleSignUp(MyApp app, Scanner scanner) {
        System.out.print("Enter username: ");
        String signupUsername = scanner.nextLine();

        System.out.print("Enter email: ");
        String signupEmail = scanner.nextLine();

        System.out.print("Enter phone: ");
        String signupPhone = scanner.nextLine();

        System.out.print("Enter age: ");
        int signupAge = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter city: ");
        String signupCity = scanner.nextLine();

        System.out.print("Enter password: ");
        String signupPassword = scanner.nextLine();

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        String userType = "user";
        try {
            MyApp.User newUser = new MyApp.User(
                    signupUsername,
                    signupEmail,
                    signupPassword,
                    confirmPassword,
                    signupPhone,
                    signupAge,
                    userType,
                    signupCity
            );
            app.signupUser(newUser);
            System.out.println("Sign-up successful! You can now log in.");
        } catch (IllegalStateException e) {
            System.out.println("Sign-up failed: " + e.getMessage());
        }
    }

    private static void handleLogIn(MyApp app, Scanner scanner) {
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        String currentPage = app.login(loginUsername, loginPassword);

        if (!"loginPage".equals(currentPage)) {
            System.out.println("Login successful! Redirecting to " + currentPage);

            switch (currentPage) {
                case "adminPage":
                    handleAdminPage(app, loginUsername);
                    break;
                case "ownerPage":
                    handleOwnerPage(app, loginUsername);
                    break;
                case "userPage":
                    handleUserPage(app, loginUsername);
                    break;
                default:
                    System.out.println("Unknown page: " + currentPage);
                    break;
            }
        } else {
            System.out.println("Login failed! Please check your credentials.");
        }
    }

    private static void handleAdminPage(MyApp app, String username) {
        System.out.println("Welcome to the Admin Dashboard.");
        Scanner scanner = new Scanner(System.in);
        ManageOwnerAccountsC account = ManageOwnerAccountsC.getInstance();
        ProfitsAndFinantialReportC finan = ProfitsAndFinantialReportC.getInstance();
        DisplayStatisticsByCityC statsManager = DisplayStatisticsByCityC.getInstance();
        ManageRecipeC recipeManager=ManageRecipeC.getInstance();
        statsManager.addUser("Alice", "New York");
        statsManager.addUser("Bob", "Los Angeles");
        statsManager.addUser("Charlie", "New York");
        recipeManager.addRecipe("Spaghetti Bolognese", "spaghetti, minced meat, tomato sauce", "Cook for 30 minutes", "4", "30 minutes", List.of("Italian", "Pasta"));
        recipeManager.addPost("Recipe Blog", "This is a blog post about recipes.", List.of("Food", "Recipes"));
        recipeManager.addFeedback("Spaghetti Bolognese", "Delicious!", 5);

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Owners");
            System.out.println("2. Financial Reports");
            System.out.println("3. Display User Statistics by City");
            System.out.println("4. Manage Recipes");
            System.out.println("5. Manage Posts");
            System.out.println("6. Manage Feedback");
            System.out.println("7. Log Out");
            System.out.print("Please choose an option: ");


            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    manageOwners(account, scanner);
                    break;
                case "2":
                    handleFinancialDashboard(finan);
                    break;
                case "3":
                    displayUserStatisticsByCity(statsManager);
                    break;
                case "4":
                    manageRecipes(recipeManager, scanner);
                    break;
                case "5":
                    managePosts(recipeManager, scanner);
                    break;
                case "6":
                    manageFeedback(recipeManager, scanner);
                    break;
                case "7":
                    System.out.println("Logging out...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
    }


    private static void manageOwners(ManageOwnerAccountsC app, Scanner scanner) {
        while (true) {
            System.out.println("Owner Management Menu:");
            System.out.println("1. Add Owner");
            System.out.println("2. Update Owner");
            System.out.println("3. Delete Owner");
            System.out.println("4. View All Owners");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Please choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addOwner(app, scanner);
                    break;
                case "2":
                    viewProducts(ProductManagement.getInstance());
                    break;
                case "3":
                    viewSalesReport(ProductManagement.getInstance());
                    break;
                case "4":
                    applyDiscount(ProductManagement.getInstance());
                    break;
                case "5":
                    identifyBestSellingProduct(ProductManagement.getInstance());
                    break;
                case "6":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
    }


    private static void addOwner(ManageOwnerAccountsC app, Scanner scanner) {
        System.out.print("Enter owner username: ");
        String username = scanner.nextLine();

        System.out.print("Enter owner email: ");
        String email = scanner.nextLine();

        System.out.print("Enter owner phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter owner age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter owner city: ");
        String city = scanner.nextLine();

        System.out.print("Enter owner password: ");
        String password = scanner.nextLine();

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        try {
            ManageOwnerAccountsC.UserAccount newOwner = new ManageOwnerAccountsC.UserAccount(
                    username, email, phone, age
            );
            app.addUserAccount(newOwner);
            System.out.println("Owner added successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateOwner(ManageOwnerAccountsC app, Scanner scanner) {
        System.out.print("Enter owner username to update: ");
        String username = scanner.nextLine();

        // Fetch existing owner details
        ManageOwnerAccountsC.UserAccount existingOwner = app.getUser(username);
        if (existingOwner == null) {
            System.out.println("Owner with username " + username + " does not exist.");
            return;
        }


        System.out.println("Current details:");
        System.out.println("Username: " + existingOwner.getName());
        System.out.println("Email: " + existingOwner.getEmail());
        System.out.println("Phone: " + existingOwner.getPhone());
        System.out.println("Age: " + existingOwner.getAge());


        System.out.print("Enter new email (or press Enter to keep current): ");
        String newEmail = scanner.nextLine();
        if (newEmail.isEmpty()) {
            newEmail = existingOwner.getEmail();
        }

        System.out.print("Enter new phone (or press Enter to keep current): ");
        String newPhone = scanner.nextLine();
        if (newPhone.isEmpty()) {
            newPhone = existingOwner.getPhone();
        }

        System.out.print("Enter new age (or press Enter to keep current): ");
        String ageInput = scanner.nextLine();
        int newAge = existingOwner.getAge();
        if (!ageInput.isEmpty()) {
            try {
                newAge = Integer.parseInt(ageInput);
                if (newAge <= 0) {
                    System.out.println("Invalid age. Age must be positive.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age format.");
                return;
            }
        }

        ManageOwnerAccountsC.UserAccount updatedOwner = new ManageOwnerAccountsC.UserAccount(
                existingOwner.getName(),
                newEmail,
                newPhone,
                newAge
        );


        try {
            app.updateUserAccount(updatedOwner);
            System.out.println("Owner details updated successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error updating owner: " + e.getMessage());
        }
    }


    private static void deleteOwner(ManageOwnerAccountsC app, Scanner scanner) {
        System.out.print("Enter owner username to delete: ");
        String username = scanner.nextLine();

        try {
            app.deleteUserAccount(username);
            System.out.println("Owner deleted successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllOwners(ManageOwnerAccountsC app) {
        ManageOwnerAccountsC owner = ManageOwnerAccountsC.getInstance();

        List<ManageOwnerAccountsC.UserAccount> owners = new ArrayList<>(owner.owndeatails.values());

        if (owners.isEmpty()) {
            System.out.println("No owners found.");
        } else {
            System.out.println("Owner List:");
            for (ManageOwnerAccountsC.UserAccount userAccount : owners) {
                System.out.println("Username: " + userAccount.getName() +
                        ", Email: " + userAccount.getEmail() +
                        ", Phone: " + userAccount.getPhone() +
                        ", Age: " + userAccount.getAge());
            }
        }
    }


    private static void handleFinancialDashboard(ProfitsAndFinantialReportC reportManager) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Financial Dashboard:");
            System.out.println("1. Record Sale");
            System.out.println("2. Set Selling Price");
            System.out.println("3. Set Cost Price");
            System.out.println("4. View Financial Report");
            System.out.println("5. View Best Selling Products");
            System.out.println("6. Log Out");
            System.out.print("Please choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    recordSale(reportManager, scanner);
                    break;
                case "2":
                    setSellingPrice(reportManager, scanner);
                    break;
                case "3":
                    setCostPrice(reportManager, scanner);
                    break;
                case "4":
                    viewFinancialReport(reportManager);
                    break;
                case "5":
                    viewBestSellingProducts(reportManager);
                    break;
                case "6":
                    System.out.println("Logging out...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
    }

    private static void recordSale(ProfitsAndFinantialReportC reportManager, Scanner scanner) {
        System.out.print("Enter store name: ");
        String store = scanner.nextLine();

        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity sold: ");
        int quantitySold = Integer.parseInt(scanner.nextLine());

        try {
            reportManager.recordSale(store, productId, productName, quantitySold);
            System.out.println("Sale recorded successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error recording sale: " + e.getMessage());
        }
    }

    private static void setSellingPrice(ProfitsAndFinantialReportC reportManager, Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter selling price: ");
        double price = Double.parseDouble(scanner.nextLine());

        reportManager.setSellingPrice(productId, price);
        System.out.println("Selling price set successfully.");
    }

    private static void setCostPrice(ProfitsAndFinantialReportC reportManager, Scanner scanner) {
        System.out.print("Enter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter cost price: ");
        double price = Double.parseDouble(scanner.nextLine());

        reportManager.setCostPrice(productId, price);
        System.out.println("Cost price set successfully.");
    }

    private static void viewFinancialReport(ProfitsAndFinantialReportC reportManager) {
        Map<String, Double> report = reportManager.generateFinancialReport();
        System.out.println("Financial Report:");
        System.out.println("Total Sales: " + report.get("Total Sales"));
        System.out.println("Total Profit: " + report.get("Total Profit"));
    }

    private static void viewBestSellingProducts(ProfitsAndFinantialReportC reportManager) {
        Map<String, String> bestSellingProducts = reportManager.getBestSellingProductsByStore();
        System.out.println("Best Selling Products by Store:");
        for (Map.Entry<String, String> entry : bestSellingProducts.entrySet()) {
            System.out.println("Store: " + entry.getKey() + ", Best Selling Product: " + entry.getValue());
        }
    }
    private static void displayUserStatisticsByCity(DisplayStatisticsByCityC statsManager) {
        Map<String, Integer> statistics = statsManager.gatherStatisticsByCity();
        System.out.println("User Statistics by City:");
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            System.out.println("City: " + entry.getKey() + ", User Count: " + entry.getValue());
        }
    }
    private static void manageRecipes(ManageRecipeC recipeManager, Scanner scanner) {
        System.out.println("Manage Recipes:");
        System.out.println("1. Add Recipe");
        System.out.println("2. Update Recipe");
        System.out.println("3. Delete Recipe");
        System.out.println("4. Search Recipes");
        System.out.println("5. Back");
        System.out.print("Please choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                System.out.print("Enter ingredients (comma separated): ");
                String ingredients = scanner.nextLine();
                System.out.print("Enter preparation: ");
                String preparation = scanner.nextLine();
                System.out.print("Enter servings: ");
                String servings = scanner.nextLine();
                System.out.print("Enter cooking time: ");
                String cookingTime = scanner.nextLine();
                System.out.print("Enter tags (comma separated): ");
                List<String> tags = List.of(scanner.nextLine().split(", "));
                recipeManager.addRecipe(title, ingredients, preparation, servings, cookingTime, tags);
                break;
            case "2":
                System.out.print("Enter old title: ");
                String oldTitle = scanner.nextLine();
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new ingredients (comma separated): ");
                List<String> newIngredients = List.of(scanner.nextLine().split(", "));
                System.out.print("Enter new preparation: ");
                String newPreparation = scanner.nextLine();
                System.out.print("Enter new servings: ");
                String newServings = scanner.nextLine();
                System.out.print("Enter new cooking time: ");
                String newCookingTime = scanner.nextLine();
                System.out.print("Enter new tags (comma separated): ");
                List<String> newTags = List.of(scanner.nextLine().split(", "));
                try {
                    recipeManager.updateRecipe(oldTitle, newTitle, newIngredients, newPreparation, newServings, newCookingTime, newTags);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "3":
                System.out.print("Enter title to delete: ");
                String deleteTitle = scanner.nextLine();
                try {
                    recipeManager.deleteRecipe(deleteTitle);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "4":
                System.out.print("Enter search keyword: ");
                String keyword = scanner.nextLine();
                List<ManageRecipeC.Recipe> searchResults = recipeManager.searchRecipes(keyword);
                System.out.println("Search Results:");
                for (ManageRecipeC.Recipe recipe : searchResults) {
                    System.out.println(recipe.getTitle());
                }
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }

    private static void managePosts(ManageRecipeC recipeManager, Scanner scanner) {
        System.out.println("Manage Posts:");
        System.out.println("1. Add Post");
        System.out.println("2. Update Post");
        System.out.println("3. Delete Post");
        System.out.println("4. Search Posts");
        System.out.println("5. Back");
        System.out.print("Please choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                System.out.print("Enter content: ");
                String content = scanner.nextLine();
                System.out.print("Enter tags (comma separated): ");
                List<String> tags = List.of(scanner.nextLine().split(", "));
                recipeManager.addPost(title, content, tags);
                break;
            case "2":
                System.out.print("Enter old title: ");
                String oldTitle = scanner.nextLine();
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new content: ");
                String newContent = scanner.nextLine();
                System.out.print("Enter new tags (comma separated): ");
                List<String> newTags = List.of(scanner.nextLine().split(", "));
                try {
                    recipeManager.updatePost(oldTitle, newTitle, newContent, newTags);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "3":
                System.out.print("Enter title to delete: ");
                String deleteTitle = scanner.nextLine();
                try {
                    recipeManager.deletePost(deleteTitle);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "4":
                System.out.print("Enter search keyword: ");
                String keyword = scanner.nextLine();
                List<ManageRecipeC.Post> searchResults = recipeManager.searchPosts(keyword);
                System.out.println("Search Results:");
                for (ManageRecipeC.Post post : searchResults) {
                    System.out.println(post.getTitle());
                }
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }

    private static void manageFeedback(ManageRecipeC recipeManager, Scanner scanner) {
        System.out.println("Manage Feedback:");
        System.out.println("1. Add Feedback");
        System.out.println("2. Respond to Feedback");
        System.out.println("3. Delete Feedback");
        System.out.println("4. View Feedback");
        System.out.println("5. Back");
        System.out.print("Please choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.print("Enter recipe title: ");
                String recipeTitle = scanner.nextLine();
                System.out.print("Enter comment: ");
                String comment = scanner.nextLine();
                System.out.print("Enter rating (1-5): ");
                int rating = Integer.parseInt(scanner.nextLine());
                recipeManager.addFeedback(recipeTitle, comment, rating);
                break;
            case "2":
                System.out.print("Enter recipe title: ");
                String feedbackTitle = scanner.nextLine();
                System.out.print("Enter response: ");
                String response = scanner.nextLine();
                try {
                    recipeManager.respondToFeedback(feedbackTitle, response);
                } catch (IllegalStateException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "3":
                System.out.print("Enter comment to delete: ");
                String feedbackComment = scanner.nextLine();
                try {
                    recipeManager.deleteFeedback(feedbackComment);
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "4":
                List<ManageRecipeC.Feedback> feedbackList = recipeManager.getFeedbackList();
                System.out.println("Feedback List:");
                for (ManageRecipeC.Feedback feedback : feedbackList) {
                    System.out.println("Recipe Title: " + feedback.getRecipeTitle());
                    System.out.println("Comment: " + feedback.getComment());
                    System.out.println("Rating: " + feedback.getRating());
                    System.out.println("Response: " + feedback.getResponse());
                    System.out.println();
                }
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }

    private static void handleOwnerPage(MyApp app, String username) {
        ProductManagement productManagement = ProductManagement.getInstance();
        BusinessAccount businessAccount=BusinessAccount.getInstance();
        PersonalAccount profile=PersonalAccount.getInstance();
        OrderManagement order=OrderManagement.getInstance();
        productManagement.setNavagates_to_pmpage(true);
        productManagement.setIs_an_owner(true);
        CommunicationSys com=CommunicationSys.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Owner Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View Sales Report");
            System.out.println("6. Apply Discount");
            System.out.println("7. Identify Best-Selling Product");
            System.out.println("8. Send Message");
            System.out.println("9. View Messages");
            System.out.println("10. View Business Profile");
            System.out.println("11. Update Business Profile");
            System.out.println("12. Delete Business Profile");
            System.out.println("13. View Personal Account");
            System.out.println("14. Update Personal Account");
            System.out.println("15. Delete Personal Account");
            System.out.println("15. Delete Personal Account");
            System.out.println("16. Handle Orders"); // New option
            System.out.println("17. Log Out");
            System.out.print("Please choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    viewProducts(productManagement);
                    break;
                case "2":
                    addProduct(productManagement);
                    break;
                case "3":
                    updateProduct(productManagement);
                    break;
                case "4":
                    deleteProduct(productManagement);
                    break;
                case "5":
                    viewSalesReport(productManagement);
                    break;
                case "6":
                    applyDiscount(productManagement);
                    break;
                case "7":
                    identifyBestSellingProduct(productManagement);
                    break;
                case "8":
                    sendMessage(com, username, scanner);
                    break;
                case "9":
                    viewMessages(com, username);
                    break;
                case "10":
                    viewBusinessProfile(businessAccount, username);
                    break;
                case "11":
                    updateBusinessProfile(businessAccount, username, scanner);
                    break;
                case "12":
                    deleteBusinessProfile(businessAccount, username);
                    break;
                case "13":
                    viewPersonalAccount(app, username);
                    break;
                case "14":
                    updatePersonalAccount(app, username, scanner);
                    break;
                case "15":
                    deletePersonalAccount(app, username);
                    break;
                case "16":
                    handleOrders(order);
                    break;
                case "17":
                    System.out.println("Logging out...");
                    productManagement.setNavagates_to_pmpage(false);
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
    }

    private static void viewProducts(ProductManagement productManagement) {
        System.out.println("Product List:");
        productManagement.getProducts().forEach((id, product) -> {
            System.out.println("ID: " + id + ", Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Quantity: " + product.getQuantity());
        });
    }

    private static void addProduct(ProductManagement productManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter product quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        ProductManagement.Product product = new ProductManagement.Product(id, name, description, price, quantity);
        try {
            productManagement.addproduct(product);
            System.out.println("Product added successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateProduct(ProductManagement productManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to update: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric value.");
            return;
        }


        ProductManagement.Product existingProduct = productManagement.getProductById(id);
        if (existingProduct == null) {
            System.out.println("Error: Product with ID " + id + " does not exist.");
            return;
        }


        System.out.println("Current product details:");
        System.out.println("Name: " + existingProduct.getName());
        System.out.println("Description: " + existingProduct.getDescription());
        System.out.println("Price: " + existingProduct.getPrice());
        System.out.println("Quantity: " + existingProduct.getQuantity());

        System.out.print("Enter new product name (leave empty to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingProduct.setName(name);
        }

        System.out.print("Enter new product description (leave empty to keep current): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            existingProduct.setDescription(description);
        }

        System.out.print("Enter new price (leave empty to keep current): ");
        String priceInput = scanner.nextLine();
        if (!priceInput.isEmpty()) {
            try {
                double price = Double.parseDouble(priceInput);
                existingProduct.setPrice(price);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Please enter a numeric value.");
                return;
            }
        }

        System.out.print("Enter new quantity (leave empty to keep current): ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) {
            try {
                int quantity = Integer.parseInt(quantityInput);
                existingProduct.setQuantity(quantity);
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format. Please enter a numeric value.");
                return;
            }
        }

        try {

            productManagement.updateProduct2(existingProduct);
            System.out.println("Product updated successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void deleteProduct(ProductManagement productManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            productManagement.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewSalesReport(ProductManagement productManagement) {
        System.out.println("Total Sales: " + productManagement.calculateTotalSalesRevenue());
        System.out.println("Total Profit: " + productManagement.getTotalProfit());
    }

    private static void applyDiscount(ProductManagement productManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID for discount: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter discount percentage: ");
        double discountPercentage = Double.parseDouble(scanner.nextLine());

        try {
            productManagement.applyDiscount(id, discountPercentage);
            System.out.println("Discount applied successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void sendMessage(CommunicationSys com, String senderUsername, Scanner scanner) {
        System.out.print("Enter the username of the receiver: ");
        String receiverUsername = scanner.nextLine();


        CommunicationSys.User sender = com.getUserById(senderUsername.hashCode());  // Adjust as needed
        CommunicationSys.User receiver = com.getUserById(receiverUsername.hashCode());

        if (receiver == null) {
            System.out.println("Error: User " + receiverUsername + " does not exist.");
            return;
        }

        System.out.print("Enter your message: ");
        String messageContent = scanner.nextLine();

        try {

            com.sendMessage(sender.getId(), receiver.getId(), messageContent);
            System.out.println("Message sent successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    private static void viewMessages(CommunicationSys com, String username) {

        int userId = username.hashCode();
        List<CommunicationSys.Message> messages = com.getMessagesForUser(userId);
        if (messages.isEmpty()) {
            System.out.println("No messages found.");
        } else {
            for (CommunicationSys.Message message : messages) {
                System.out.println("From: " + message.getSender().getUsername());
                System.out.println("To: " + message.getReceiver().getUsername());
                System.out.println("Message: " + message.getContent());
                System.out.println("----------------------------");
            }
        }
    }
    private static void identifyBestSellingProduct(ProductManagement productManagement) {
        List<ProductManagement.SoldProduct> bestSellingProducts = productManagement.getBestSellingProducts();


        if (bestSellingProducts.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Best-Selling Products:");
            for (ProductManagement.SoldProduct soldProduct : bestSellingProducts) {
                System.out.println("ID: " + soldProduct.getId() +

                        ", Quantity Sold: " + soldProduct.getQuantitySold());
            }
        }
    }

    private static void viewBusinessProfile(BusinessAccount app, String username) {
        BusinessAccount businessAccount = BusinessAccount.getInstance();
        int userId = username.hashCode();


        if (businessAccount != null) {
            BusinessAccount.BusinessProfile profile = businessAccount.getProfile(userId);


            if (profile != null) {
                System.out.println("Business Profile:");
                System.out.println("Name: " + profile.getBusinessName());
                System.out.println("Address: " + profile.getAddress());
                System.out.println("Contact: " + profile.getContact());
            } else {
                System.out.println("No business profile found for user ID: " + userId + " (Username: " + username + ")");
            }
        } else {
            System.out.println("Business account instance is not available.");
        }
    }



    private static void updateBusinessProfile(BusinessAccount app, String username, Scanner scanner) {
        BusinessAccount businessAccount=BusinessAccount.getInstance();
        int userId = username.hashCode();
        if (businessAccount != null) {
            BusinessAccount.BusinessProfile profile = businessAccount.getProfile(userId);

            System.out.println("Current Business Profile:");
            System.out.println("Name: " + profile.getBusinessName());
            System.out.println("Address: " + profile.getAddress());
            System.out.println("Contact: " + profile.getContact());

            System.out.print("Enter new business name (leave empty to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                profile.setBusinessName(name);
            }

            System.out.print("Enter new address (leave empty to keep current): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                profile.setAddress(address);
            }

            System.out.print("Enter new contact info (leave empty to keep current): ");
            String contact = scanner.nextLine();
            if (!contact.isEmpty()) {
                profile.setContact(contact);
            }

            try {
                businessAccount.updateProfile(userId,address,contact,name);
                System.out.println("Business profile updated successfully.");
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No business profile found for user: " + username);
        }
    }

    private static void deleteBusinessProfile(BusinessAccount app, String username) {
        BusinessAccount businessAccount=BusinessAccount.getInstance();
        int userId = username.hashCode();
        if (businessAccount != null) {
            try {
                businessAccount.deleteProfile(userId);
                System.out.println("Business profile deleted successfully.");
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No business profile found for user: " + username);
        }
    }

    private static void viewPersonalAccount(MyApp app, String username) {
        PersonalAccount personalAccount = PersonalAccount.getInstance();
        PersonalAccount.PersonalProfile profile = personalAccount.getProfile(username);

        if (profile != null) {
            System.out.println("Personal Account:");
            System.out.println("Username: " + profile.getName());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Phone: " + profile.getPhone());
            System.out.println("Age: " + profile.getAge());
        } else {
            System.out.println("No personal account found for username: " + username);
        }
    }


    private static void updatePersonalAccount(MyApp app, String username, Scanner scanner) {
        PersonalAccount personalAccount = PersonalAccount.getInstance();
        PersonalAccount.PersonalProfile profile = personalAccount.getProfile(username);

        if (profile == null) {
            System.out.println("No personal account found for username: " + username);
            return;
        }

        System.out.println("Current Account Details:");
        System.out.println("Username: " + profile.getName());
        System.out.println("Email: " + profile.getEmail());
        System.out.println("Phone: " + profile.getPhone());
        System.out.println("Age: " + profile.getAge());

        System.out.print("Enter new email (leave empty to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            profile = new PersonalAccount.PersonalProfile(profile.getName(), profile.getAge(), email, profile.getPhone());
        }

        System.out.print("Enter new phone (leave empty to keep current): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            profile = new PersonalAccount.PersonalProfile(profile.getName(), profile.getAge(), profile.getEmail(), phone);
        }

        System.out.print("Enter new age (leave empty to keep current): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                int age = Integer.parseInt(ageInput);
                profile = new PersonalAccount.PersonalProfile(profile.getName(), age, profile.getEmail(), profile.getPhone());
            } catch (NumberFormatException e) {
                System.out.println("Invalid age format. Please enter a numeric value.");
                return;
            }
        }

        personalAccount.updateProfile(profile.getName(), profile.getAge(), profile.getEmail(), profile.getPhone());
        System.out.println("Account updated successfully.");
    }


    private static void deletePersonalAccount(MyApp app, String username) {
        PersonalAccount personalAccount = PersonalAccount.getInstance();
        PersonalAccount.PersonalProfile profile = personalAccount.getProfile(username);
        int userid=username.hashCode();
        if (profile == null) {
            System.out.println("No personal account found for username: " + username);
            return;
        }

        personalAccount.deleteProfile(userid);
        System.out.println("Personal account deleted successfully.");
    }

    private static void handleOrders(OrderManagement order) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Order Management:");
        System.out.println("1. View Orders");
        System.out.println("2. Process Order");
        System.out.println("3. Cancel Order");
        System.out.print("Please choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                viewOrders(order);
                break;
            case "2":
                processOrder(order, scanner);
                break;
            case "3":
                cancelOrder(order, scanner);
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }
    private static void viewOrders(OrderManagement orderManagement) {
        System.out.println("Orders List:");
        for (OrderManagement.Order order : orderManagement.orders.values()) {
            System.out.println("Order ID: " + order.getId() +
                    ", Status: " + order.getStatus());
        }
    }


    private static void processOrder(OrderManagement order, Scanner scanner) {
        System.out.print("Enter order ID to process: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        try {
            order.processOrder(orderId);
            System.out.println("Order processed successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelOrder(OrderManagement order, Scanner scanner) {
        System.out.print("Enter order ID to cancel: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        try {
            order.deleteOrder(orderId);
            System.out.println("Order canceled successfully.");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleUserPage(MyApp app, String username) {
        System.out.println("Welcome to the User Home Page.");

    }
}