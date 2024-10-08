
Feature: Purchase desserts directly from store owners

  Background:
    Given the user is on the desserts page
    And the list of available desserts includes:
      | dessertName     | price | availability | dietaryInfo      |
      | Chocolate Cake  | 5.00  | In Stock     |                  |
      | Vegan Brownie   | 3.50  | In Stock     | Vegan, Nut-Free  |
      | Apple Pie       | 4.00  | In Stock |                  |
      | Cheesecake      | 4.50  | In Stock     | Low-Carb         |
      | Banana Bread    | 3.00  | In Stock     |                  |

  Scenario Outline: Purchase dessert with different details
    When the user selects a dessert:
      | dessertName  | price | availability |
      | <dessertName>| <price>| <availability>|
    And the user confirms the purchase
    Then the user should see this msg "<expectedMessage>"
    And the desserts inventory should reflect the changes
    And the user should be redirected to the "<expectedPage>"

    Examples:
      | dessertName     | price | availability | expectedMessage                      | expectedPage  |
      | Chocolate Cake  | 5.00  | In Stock     | Purchase successful                  | order_summary |
      | Apple Pie       | 4.00  | In Stock | Purchase successful              | order_summary |
      | Vegan Brownie   | 3.50  | In Stock     | Purchase successful                  | order_summary |
      | Cheesecake      | 4.50  | In Stock     | Purchase successful                  | order_summary |
      | Banana Bread    | 3.00  | In Stock     | Purchase successful                  | order_summary |

  Scenario Outline: Attempt to purchase dessert with dietary restrictions
    Given the user has the following details:
      | userID | username  | email          | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>      | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a dessert:
      | dessertName  | price | availability | dietaryInfo      |
      | <dessertName>| <price>| <availability>| <dietaryInfo> |
    And the user confirms the purchase
    Then the user should see this msg "<expectedMessage>"
    And the user should be redirected to the "<expectedPage>"

    Examples:
      | userID | username  | email          | phone      | age | password  | dietaryNeeds | foodAllergies | dessertName   | price | availability | dietaryInfo      | expectedMessage                      | expectedPage  |
      | 1      | user1     | user1@mail.com | 0591234567 | 21  | password1 | Vegan        | Nuts          | Vegan Brownie | 3.50  | In Stock     | Vegan, Nut-Free  | Purchase successful                  | order_summary |
      | 2      | user2     | user2@mail.com | 0597654321 | 22  | password2 | Gluten-Free  |               | Apple Pie     | 4.00  | In Stock|                  | Dessert is out of stock              | order_summary |
      | 3      | user3     | user3@mail.com | 0599876543 | 23  | password3 | Low-Carb     |               | Cheesecake    | 4.50  | In Stock     | Low-Carb         | Purchase successful                  | order_summary |
      | 4      | user4     | user4@mail.com | 0591234321 | 24  | password4 |              | Dairy         | Chocolate Cake| 5.00  | In Stock     |                  | Dessert may contain allergens (Dairy)| order_summary |
