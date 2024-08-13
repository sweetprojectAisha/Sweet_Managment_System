Feature: Browse and search for dessert recipes
  As a user
  I want to browse and search for dessert recipes
  So that I can find and make delicious desserts

  Scenario Outline: Browse all recipes
    Given a user with the following details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | <username> | <email> | <phone> | <age> | <password> | <foodAllergies> |
    When the user clicks on browse all recipes
    And fills in the category with <category>
    Then the user should see <description>
    And the user should see <prepTime>
    And the user should see <difficulty>
    And the user should see <rating>

    Examples:
      | username  | email      | phone      | age | password  | foodAllergies | sweetname     | category  | description                   | imageUrl                   | prepTime | difficulty | rating |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten        | chocolate cake | Cakes    | Delicious chocolate cake      | images/chocolate_cake.jpg  | 60       | Medium     | 4.5    |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten        | lemon  | Cookies  | Tangy and sweet lemon cookies | images/lemon_cookies.jpg   | 30       | Easy       | 4.7    |

  Scenario Outline: Search for a recipe
    Given a user with the following details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | <username> | <email> | <phone> | <age> | <password> | <foodAllergies> |
    When the user searches for a recipe with <sweetname>
    Then the user should see  <category>
    Then the user should see <description>
    And the user should see <prepTime>
    And the user should see <difficulty>
    And the user should see <rating>

    Examples:
      | username  | email      | phone      | age | password  | foodAllergies | sweetname     | category  | description                   | imageUrl                   | prepTime | difficulty | rating |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten        | chocolate cake | Cakes    | Delicious chocolate cake      | images/chocolate_cake.jpg  | 60       | Medium     | 4.5    |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ | Gluten        | lemon cheesecake | Cookies  | Tangy and sweet lemon cookies | images/lemon_cookies.jpg   | 30       | Easy       | 4.7    |

