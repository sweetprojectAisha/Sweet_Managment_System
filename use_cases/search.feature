
Feature: Search for a recipe
  Scenario Outline: Search with no matching results
    Given a user with the following details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | <username> | <email> | <phone> | <age> | <password> | <foodAllergies> |
    When the user searches for a recipe with <sweetname>
    And fills in the category with <category>
    Then the user should see <msg>

    Examples:
      | sweetname        | category | msg                          |
      | Strawberry Cake  | Cakes    | This recipe doesn't exist    |
      | Lotus Cookie     | Cookies  | This recipe doesn't exist    |

  Scenario Outline: Search with an empty query
    Given a user with the following details:
      | username  | email      | phone      | age | password  | foodAllergies |
      | <username> | <email> | <phone> | <age> | <password> | <foodAllergies> |
    When the user clicks on search
    And fills in name with <sweetname> and category with <category>
    Then the field 'description' should show an error
    And the field 'prepTime' should show an error
    And the field 'difficulty' should show an error
    And the field 'rating' should show an error

    Examples:
      | sweetname | category |
      | ""        | ""       |
