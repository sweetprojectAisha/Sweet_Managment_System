Feature: Browse and search for dessert recipes
  As a user
  I want to browse and search for dessert recipes
  So that I can find and make delicious desserts

  Background:
    Given a user with the following details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten        |

  Scenario Outline: Browse all recipes by category
    Given the user has the following recipes:
      | sweetname        | category | description                   | imageUrl                   | prepTime | difficulty | rating |
      | <sweetname>      | <category> | <description>                | <imageUrl>                 | <prepTime> | <difficulty> | <rating> |
    When the user clicks on browse all recipes
    And filters by category <category>
    Then the user should see recipe with description <description>
    And the user should see preparation time <prepTime>
    And the user should see difficulty level <difficulty>
    And the user should see rating <rating>

    Examples:
      | sweetname        | category | description                   | imageUrl                   | prepTime | difficulty | rating |
      | chocolate cake   | Cakes    | Delicious chocolate cake      | images/chocolate_cake.jpg  | 60       | Medium     | 4.5    |
      | lemon cookies    | Cookies  | Tangy and sweet lemon cookies | images/lemon_cookies.jpg   | 30       | Easy       | 4.7    |

  Scenario Outline: Search for a recipe by sweet name
    Given the user has the following recipes:
      | sweetname        | category | description                   | imageUrl                   | prepTime | difficulty | rating |
      | <sweetname>      | <category> | <description>                | <imageUrl>                 | <prepTime> | <difficulty> | <rating> |
    When the user searches for a recipe with sweet name <sweetname>
    Then the user should see category <category>
    And the user should see description <description>
    And the user should see preparation time <prepTime>
    And the user should see difficulty level <difficulty>
    And the user should see rating <rating>

    Examples:
      | sweetname        | category | description                   | imageUrl                   | prepTime | difficulty | rating |
      | chocolate cake   | Cakes    | Delicious chocolate cake      | images/chocolate_cake.jpg  | 60       | Medium     | 4.5    |
      | lemon cookies    | Cookies  | Tangy and sweet lemon cookies | images/lemon_cookies.jpg   | 30       | Easy       | 4.7    |