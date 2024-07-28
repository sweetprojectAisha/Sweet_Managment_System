Feature: Login page

  Background:
    Given the person is on the login page
    And the list of valid users includes:
      | username    | password    | userType |
      | validuser   | validpass   | user     |
      | validowner  | validpass   | owner    |
      | validadmin  | validpass   | admin    |

  Scenario Outline: Login with credentials
    When the user enters "<username>" and "<password>" as "<userType>"
    And the user submits the login form
    Then the user should see "<resultMessage>"
    And the user should be redirected to the "<expectedPage>"

    Examples:
      | username    | password     | userType | resultMessage                | expectedPage        |
      | validuser   | validpass    | user     | Welcome, validuser           | user_page      |
      | validowner  | validpass    | owner    | Welcome, validowner          | owner_page     |
      | validadmin  | validpass    | admin    | Welcome, validadmin          | admin_page     |
      | validuser   | invalidpass  | user     | Invalid password             | login_page          |
      | invaliduser | validpass    | user     | Invalid username             | login_page          |
      | invaliduser | invalidpass  | user     | Invalid username or password | login_page          |

  Scenario Outline: Login with empty fields
    When the user enters "<username>" and "<password>"
    And the user submits the login form
    Then the user should see "<resultMessage>"

    Examples:
      | username | password | resultMessage          |
      |    ""      |    ""      | Username can not be empty  |
      | validuser|     ""     | Password can not be empty   |
      |    ""      | validpass| Username can not be empty   |
