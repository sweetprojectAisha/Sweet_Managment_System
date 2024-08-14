Feature: Sign-Up

  Scenario: :
    Given the following users exist in login sys:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |Type|
      | user1    | user1@mail.com | pass12345 | pass12345 | 1234567890 | 25  | admin      |
      | user2    | user2@mail.com | pass67890 | pass67890 | 0987654321 | 30  |  owner     |
      | user2    | user2@mail.com | pass67890 | pass67890 | 0987654321 | 30  |  user     |

  Scenario Outline: Successful sign-up with valid details
    When the person signs up with the following valid details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |
    Then the person should be redirected to the login page
    Examples:
      | uname   | email         | pass       | confirmPass | phone      | age |
      | newuser | new@mail.com  | pass78901  | pass78901   | 1111111111 | 22  |

  Scenario Outline: Unsuccessful sign-up with short password
    When the person signs up with the invalid password size :
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |
#    Then the person should see an error message "Password must be at least 8 characters"
    Examples:
      | uname   | email         | pass   | confirmPass | phone      | age |
      | newuser2 | new@mail.com  | pass12 | pass12      | 1234567891 | 22  |

  Scenario Outline: Unsuccessful sign-up with invalid phone number
    When the person signs up with the invalid phone size details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |
#    Then the person should see an error message "Phone number must be 10 digits"
    Examples:
      | uname   | email         | pass      | confirmPass | phone       | age |
      | newuser3 | new@mail.com  | pass78901 | pass78901   | 12345678    | 22  |

  Scenario Outline: Unsuccessful sign-up with password mismatch
    When the person signs up with password mismatch details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |
#    Then the person should see an error message "Passwords do not match"
    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age |
      | newuser4 | new@mail.com  | pass78901 | pass98701   | 1234567891 | 22  |

  Scenario Outline: Unsuccessful sign-up with existing username
    When the person signs up with existing username details:
      | UserName | Email        | Password | ConfirmPassword | Phone      | Age |
      | <uname>  | <email>      | <pass>   | <confirmPass>   | <phone>    | <age> |
#    Then the person should see an error message "Username already exists"
    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age |
      | user1   | new@mail.com  | pass78901 | pass78901   | 1111111111 | 22  |

  Scenario Outline: Unsuccessful sign-up with empty fields
    When the person signs up with empty fields details:
      | UserName | Email | Password | ConfirmPassword | Phone | Age |
      | <uname>  | <email> | <pass>  | <confirmPass>  | <phone> | <age> |
#    Then the person should see an error message "All fields are required"
    Examples:
      | uname   | email         | pass      | confirmPass | phone | age |
      |   user5      |               | pass78901 | pass78901   | 1234567890 | 22  |

