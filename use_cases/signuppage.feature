
Feature: Sign_up page

  Background:
    Given the person is on the login page
    And the list of registered user includes:
      | username  | email      | phone     | age | password   |
      | validuser | validemail | 0599873421 | 21  | 12345678a@ |

  Scenario Outline: Sign_up with different details
    When the user clicks on the "Sign_up " link
    And the user fills in sign_up form with:
      | name      | email              | phone      | age | password    | confPassword  |
      | <name>  | <email>          | <phone>  | <age> | <password> | <confPassword> |
    And the user submits the sign_up form
    Then the user should see "<expectedMessage>"
    And the login table should include:
      | username  | password    | userType  |
      | <name>  | <password> | user   |
    And the user should be redirected to the "<expectedPage>"

    Examples:
      | name      | email             | phone       | age | password    | confPassword | expectedMessage                          | expectedPage  |
      | newuser1  | newemail1@mail.com| 0591234567  | 25  | passWord1@  | passWord1@   | Sign up successfully                      | login_page    |
      | newuser2  | validemail        | 0597654321  | 22  | passWord2@  | passWord2@   | Email must be unique                      | sign_up_page  |
      | newuser3  | newemail3@mail.com| 059987342   | 23  | passWord3@  | passWord3@   | Phone must be 10 characters long          | sign_up_page  |
      | validuser | newemail4@mail.com| 0595555555  | 24  | passWord4@  | passWord4@   | Name must be unique                       | sign_up_page  |
      | newuser5  | newemail5@mail.com| 0594444444  | 20  | pass        | pass         | Password must be at least 8 characters long and include a special character | sign_up_page  |
      | newuser6  | newemail6@mail.com| 0594444444  | 20  | passWord6@  | passWord6    | Password should equal confirm password    | sign_up_page  |

  Scenario Outline: Sign_up with empty fields
    When the user clicks on the "Sign_up" link
    And the user fills in sign_up form with:
      | name      | email             | phone       | age | password    | confPassword  |
      | <name>  | <email>         | <phone>   | <age> | <password> | <confPassword> |
    And the user submits the sign_up form
    Then the user should see "<expectedMessage>"
    And the user should be redirected to the "<expectedPage>"

    Examples:
      | name     | email             | phone      | age | password    | confPassword  | expectedMessage                        | expectedPage  |
      |          | newemail1@mail.com| 0591234567 | 25  | passWord1@  | passWord1@    | Name cannot be empty                   | sign_up_page  |
      | newuser2 |                   | 0597654321 | 22  | passWord2@  | passWord2@    | Email cannot be empty                  | sign_up_page  |
      | newuser3 | newemail3@mail.com|            | 23  | passWord3@  | passWord3@    | Phone cannot be empty                  | sign_up_page  |
      | newuser4 | newemail4@mail.com| 0595555555 |     | passWord4@  | passWord4@    | Age cannot be empty                    | sign_up_page  |
      | newuser5 | newemail5@mail.com| 0594444444 | 20  |             | passWord5@    | Password cannot be empty               | sign_up_page  |
      | newuser6 | newemail6@mail.com| 0594444444 | 20  | passWord6@  |              | Confirm password cannot be empty       | sign_up_page  |
