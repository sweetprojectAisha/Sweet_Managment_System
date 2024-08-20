Feature: Managing Recipes

  Scenario: Creating a new recipe
    Given I am on the recipe creation page
    When I enter the recipe details "Chocolate Cake", ["Flour", "Sugar", "Cocoa"], "Mix ingredients and bake", ["Dessert"]
    Then I should see the recipe "Chocolate Cake" in the recipe list

  Scenario: Editing an existing recipe
#    Given I have an existing recipe "Chocolate Cake"
    When I update the recipe "Chocolate Cake" with new details "Vanilla Cake", ["Flour", "Sugar", "Vanilla"], "Mix ingredients and bake", ["Dessert"]
    Then I should see the recipe "Vanilla Cake" in the recipe list

  Scenario: Deleting a recipe
#    Given I have an existing recipe "Vanilla Cake"
    When I delete the recipe "Vanilla Cake"
    Then I should not see the recipe "Vanilla Cake" in the recipe list

  Scenario: Searching for a recipe
    Given I have the following recipes:
      | Title         | Ingredients               | Instructions            | Tags      |
      | Chocolate Cake | Flour, Sugar, Cocoa       | Mix ingredients and bake | Dessert   |
      | Vanilla Cake   | Flour, Sugar, Vanilla     | Mix ingredients and bake | Dessert   |
    Then I should see the recipe "Vanilla Cake" in the search results
