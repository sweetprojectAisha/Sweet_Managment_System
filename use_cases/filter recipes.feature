
Feature: Filter Recipes Based on Dietary Needs or Food Allergies

  Scenario: User with specific dietary needs filters recipes
    Given the user details:
      | username  | email      | phone      | age | password  | dietaryNeeds |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Vegan        |
    When the user filters recipes by dietary needs
    Then the user should see recipes that are:
      | dietaryNeeds |
      | Vegan        |

  Scenario: User with food allergies filters recipes
    Given the user details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Nuts          |
    When the user filters recipes by food allergies
    Then the user should see recipes without:
      | foodAllergies |
      | Nuts          |

  Scenario: User with both dietary needs and food allergies filters recipes
    Given the user details:
      | username  | email      | phone      | age | password  | dietaryNeeds | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten-Free  | Dairy         |
    When the user filters recipes by both dietary needs and food allergies
    Then the user should see recipes that are:
      | dietaryNeeds |
      | Gluten-Free  |
    And the user should see recipes without:
      | foodAllergies |
      | Dairy         |

  Scenario: User filters recipes with no dietary needs or food allergies
    Given the user details:
      | username  | email      | phone      | age | password  | dietaryNeeds | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ |              |               |
    When the user filters recipes
    Then the user should see all available recipes

  Scenario: User changes dietary needs and filters recipes
    Given the user details:
      | username  | email      | phone      | age | password  | dietaryNeeds |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Vegetarian   |
    When the user updates dietary needs to:
      | dietaryNeeds |
      | Vegan        |
    And the user filters recipes by dietary needs
    Then the user should see recipes that are:
      | dietaryNeeds |
      | Vegan        |

  Scenario: User changes food allergies and filters recipes
    Given the user details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Shellfish     |
    When the user updates food allergies to:
      | foodAllergies |
      | Peanuts       |
    And the user filters recipes by food allergies
    Then the user should see recipes without:
      | foodAllergies |
      | Peanuts       |

  Scenario: User searches for a specific recipe and applies dietary needs filter
    Given the user details:
      | username  | email      | phone      | age | password  | dietaryNeeds |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Paleo        |
    And the user searches for:
      | recipeName   |
      | Chicken Salad|
    When the user applies dietary needs filter
    Then the user should see recipes that are:
      | dietaryNeeds |
      | Paleo        |
    And the user should see recipes with:
      | recipeName   |
      | Chicken Salad|

  Scenario: User searches for a specific recipe and applies food allergies filter
    Given the user details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Soy           |
    And the user searches for:
      | recipeName    |
      | Tofu Stir Fry |
    When the user applies food allergies filter
    Then the user should see recipes without:
      | foodAllergies |
      | Soy           |
    And the user should see recipes with:
      | recipeName    |
      | Tofu Stir Fry |

  Scenario: User filters recipes by a combination of dietary needs and a specific ingredient
    Given the user details:
      | username  | email      | phone      | age | password  | dietaryNeeds |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Low-Carb     |
    And the user wants recipes with:
      | ingredient   |
      | Chicken      |
    When the user filters recipes by dietary needs and ingredient
    Then the user should see recipes that are:
      | dietaryNeeds |
      | Low-Carb     |
    And the user should see recipes with:
      | ingredient   |
      | Chicken      |

  Scenario: User filters recipes by a combination of food allergies and a specific ingredient
    Given the user details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten        |
    And the user wants recipes with:
      | ingredient    |
      | Beef          |
    When the user filters recipes by food allergies and ingredient
    Then the user should see recipes without:
      | foodAllergies |
      | Gluten        |
    And the user should see recipes with:
      | ingredient    |
      | Beef          |
