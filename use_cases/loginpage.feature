Feature: Login page


  Scenario:
    Given the following users exist in table:
      | UserName | Password |Type|
      | name1 | password1 |admin |
      | name2 | password2 |owner |
      | name3 | password3 |user |
  Scenario Outline:  : Successful login with valid credentials
    When the person logged in with the following valid details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Examples:
      | uname | pass      |
      | name1     | password1         |
      | name2     | password2       |

  Scenario Outline: : Unsuccessful login with invalid credentials
    When the person logged in with the following invalid details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      | name3     | password3         |


  Scenario Outline: : Unsuccessful login with empty username
    When the person logged in with the following empty username details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      |      | password1         |

  Scenario Outline: : Unsuccessful login with empty password
    When the person logged in with the following empty password details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      |  name1    |          |




  Scenario Outline: : Unsuccessful login with both fields empty
    When the person logged in with the following both empty details:
      | UserName   | Password        |
      | <uname> | <pass>      |
    Then the person should be still in login_page
    Examples:
      | uname | pass      |
      |      |          |