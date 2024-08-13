Feature: Account Management

  Scenario: person adds personal profile information

    When the person adds personal profile information with the following valid details:
      | Name | Age           | email  | phone |
      | name1  | 18 | newemail1@example.com | 0599976439       |
      | name2   | 20        | newemail2@example.com | 0599977654       |
    Then the info should be added to the profile


  Scenario Outline: Person updates profile information
    When the person updates the info with the following valid details:
      | Name   | Age         | email    | phone |
      | <name> | <age>       | <email>         | <phone>      |
    Then the personal information should be updated successfully

    Examples:
      | name          | age        | email            | phone |
      | name1    | 21  | newemail3@example.com   | 05999876543   |
      | name2    | 23 | newemail14@example.com   | 05898765343  |

#   Scenario Outline: Person updates personal information with invalid details
#    When the person updates the info with the following invalid details:
#      | Name   | Age         | email    | phone |
#      | <name> | <age>       | <email>         | <phone>      |
#    Then the personal information update should fail due to invalid details
#
#    Examples:
#      | name          | age        | email            | phone |
#      | name1  | -10| newemail1@example.com | 0599980876   |
#
