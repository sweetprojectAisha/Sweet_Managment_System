Feature: Provide feedback on purchased products and shared recipes

  Background:
    Given the user is on the feedback page
    And the list of purchased products and shared recipes includes:
      | dessertName       | price | availability | dietaryInfo     |
      | Chocolate Cake    | 15.99 | Yes          | Contains Dairy  |
      | Vegan Brownies    | 0.00  | No           | Vegan           |
      | Almond Cookies    | 12.50 | Yes          | Contains Nuts   |
      | Gluten-Free Bread | 0.00  | No           | Gluten-Free     |
      | Macarons          | 18.00 | Yes          | Contains Dairy  |

  Scenario Outline: User provides feedback on purchased products
    Given the user has the following details:
      | userID | username  | email      | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>    | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a product to provide feedback:
      | itemName       | purchaseDate  |
      | <itemName>     | <purchaseDate>|
    And the user submits feedback with the rating "<rating>" and comment "<comment>"
    Then the feedback log should be updated with:
      | itemName       | rating        | comment      | feedback

    And the user should see a confirmation message "<expectedMessage>"

    Examples:
      | userID | username | email         | phone      | age | password  | dietaryNeeds | foodAllergies | itemName       | purchaseDate  | rating | comment               | feedbackDate | expectedMessage                 |
      | 1      | user1    | user1@mail.com| 0591234567 | 21  | password1 | Vegan        | Nuts          | Chocolate Cake | 2024-07-01    | 5      | Delicious cake!       | 2024-07-16   | Your action was successful! |
      | 2      | user2    | user2@mail.com| 0597654321 | 22  | password2 | Gluten-Free  |               | Almond Cookies | 2024-07-10    | 4      | Loved the crunch!     | 2024-07-16   | Your action was successful! |
      | 3      | user3    | user3@mail.com| 0599876543 | 23  | password3 | Low-Carb     |               | Macarons       | 2024-07-15    | 3      | A bit too sweet.      | 2024-07-16   | Your action was successful! |

  Scenario Outline: User attempts to provide feedback on an unpurchased product
    Given the user has the following details:
      | userID | username  | email      | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>    | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a product to provide feedback:
      | itemName       | purchaseDate  |
      | <itemName>     | <purchaseDate>|
    And the user submits feedback with the rating "<rating>" and comment "<comment>"
    Then the user should see an error message "<expectedErrorMessage>"

    Examples:
      | userID | username | email         | phone      | age | password  | dietaryNeeds | foodAllergies | itemName       | purchaseDate  | rating | comment             | expectedErrorMessage                               |
      | 1      | user1    | user1@mail.com| 0591234567 | 21  | password1 | Vegan        | Nuts          | Almond Cookies | 2024-07-20    | 5      | Delicious!          |  Cannot provide feedback on an unpurchased product |