Feature: Forgot Password

  Background:
    Given the person is on the login page
    And the list of registered users includes:
      | username    | email               |
      | validuser   | validuser@example.com |
      | validowner  | validowner@example.com |
      | validadmin  | validadmin@example.com |


  Scenario Outline: Request password reset with valid and invalid email addresses
    When the user clicks on the "Forgot Password" link
    And the user enters "<email>" in the email field
    And the user submits the password reset request
    Then the user should see "<resultMessage>"
    And the user should be redirected to the "<expectedPage>"

    Examples:
      | email                   | resultMessage                           | expectedPage      |
      | validuser@example.com   | A password reset link has been sent to your email | login_page        |
      | validowner@example.com  | A password reset link has been sent to your email | login_page        |
      | validadmin@example.com  | A password reset link has been sent to your email | login_page        |
      | invaliduser@example.com | No account found with that email         | forgot_password_page |
      |                        | Email field cannot be empty             | forgot_password_page |

  Scenario Outline: Password reset with empty fields
    When the user clicks on the "Forgot Password" link
    And the user submits the password reset request with empty email field
    Then the user should see "<resultMessage>"
    And the user should remain on the "<expectedPage>"

    Examples:
      | resultMessage                  | expectedPage      |
      | Email field cannot be empty    | forgot_password_page |
