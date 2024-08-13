Feature: Provide feedback on purchased products and shared recipes

  Background:
    Given the user is on the feedback page
    And the list of purchased products and shared recipes includes:
      | itemType  | itemName       | purchaseDate  | recipeSharedDate | isPurchased |
      | Product   | Chocolate Cake | 2024-07-01    |                  | Yes         |
      | Recipe    | Vegan Brownies |               | 2024-07-05       | No          |
      | Product   | Almond Cookies | 2024-07-10    |                  | Yes         |
      | Recipe    | Gluten-Free Bread |           | 2024-07-12       | No          |
      | Product   | Macarons       | 2024-07-15    |                  | Yes         |

  Scenario Outline: User provides feedback on purchased products
    Given the user has the following details:
      | userID | username  | email      | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>    | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a product to provide feedback:
      | itemName       | purchaseDate  |
      | <itemName>     | <purchaseDate>|
    And the user submits feedback with the rating "<rating>" and comment "<comment>"
    Then the feedback log should be updated with:
      | itemName       | rating        | comment      | feedbackDate  | feedbackStatus |
      | <itemName>     | <rating>      | <comment>    | <feedbackDate>| Submitted      |
    And the user should see a confirmation message "<expectedMessage>"

    Examples:
      | userID | username | email         | phone      | age | password  | dietaryNeeds | foodAllergies | itemName       | purchaseDate  | rating | comment               | feedbackDate | expectedMessage              |
      | 1      | user1    | user1@mail.com| 0591234567 | 21  | password1 | Vegan        | Nuts          | Chocolate Cake | 2024-07-01    | 5      | Delicious cake!       | 2024-07-16   | Feedback submitted successfully |
      | 2      | user2    | user2@mail.com| 0597654321 | 22  | password2 | Gluten-Free  |               | Almond Cookies | 2024-07-10    | 4      | Loved the crunch!     | 2024-07-16   | Feedback submitted successfully |
      | 3      | user3    | user3@mail.com| 0599876543 | 23  | password3 | Low-Carb     |               | Macarons       | 2024-07-15    | 3      | A bit too sweet.      | 2024-07-16   | Feedback submitted successfully |

  Scenario Outline: User provides feedback on shared recipes
    Given the user has the following details:
      | userID | username  | email      | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>    | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a recipe to provide feedback:
      | itemName           | recipeSharedDate |
      | <itemName>         | <recipeSharedDate>|
    And the user submits feedback with the rating "<rating>" and comment "<comment>"
    Then the feedback log should be updated with:
      | itemName           | rating         | comment        | feedbackDate  | feedbackStatus |
      | <itemName>         | <rating>       | <comment>      | <feedbackDate>| Submitted      |
    And the user should see a confirmation message "<expectedMessage>"

    Examples:
      | userID | username | email          | phone      | age | password  | dietaryNeeds | foodAllergies | itemName        | recipeSharedDate | rating | comment            | feedbackDate | expectedMessage              |
      | 1      | user1    | user1@mail.com | 0591234567 | 21  | password1 | Vegan        | Nuts          | Vegan Brownies  | 2024-07-05       | 5      | Perfect texture!    | 2024-07-16   | Feedback submitted successfully |
      | 2      | user2    | user2@mail.com | 0597654321 | 22  | password2 | Gluten-Free  |               | Gluten-Free Bread | 2024-07-12     | 4      | Great for breakfast!| 2024-07-16   | Feedback submitted successfully |

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
      | userID | username | email         | phone      | age | password  | dietaryNeeds | foodAllergies | itemName       | purchaseDate  | rating | comment             | expectedErrorMessage           |
      | 1      | user1    | user1@mail.com| 0591234567 | 21  | password1 | Vegan        | Nuts          | Almond Cookies | 2024-07-20    | 5      | Delicious!           | Error: Cannot provide feedback on an unpurchased product |
