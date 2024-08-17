Feature: Sign-Up

  Scenario: :
    Given the following users exist in login sys:
      | UserName | Email         | Password  | ConfirmPassword | Phone      | Age | Type  | City    |
      | user1    | user1@mail.com | pass12345 | pass12345       | 1234567890 | 25  | admin | Nablus  |
      | user2    | user2@mail.com | pass67890 | pass67890       | 0987654321 | 30  | owner | Jenin   |
      | user3    | user3@mail.com | pass67890 | pass67890       | 0987654322 | 30  | user  | Ramallah|

  Scenario Outline: Successful sign-up with valid details
    When the person signs up with the following valid details:
      | UserName | Email        | Password  | ConfirmPassword | Phone      | Age | City   |
      | <uname>  | <email>      | <pass>    | <confirmPass>   | <phone>    | <age> | <city> |
    Then the person should be redirected to the login page
    Examples:
      | uname   | email         | pass       | confirmPass | phone      | age | city   |
      | newuser | new@mail.com  | pass78901  | pass78901   | 1111111111 | 22  | Nablus |

  Scenario Outline: Unsuccessful sign-up with short password
    When the person signs up with the invalid password size :
      | UserName | Email        | Password  | ConfirmPassword | Phone      | Age | City   |
      | <uname>  | <email>      | <pass>    | <confirmPass>   | <phone>    | <age> | <city> |
#    Then the person should see an error message "Password must be at least 8 characters"
    Examples:
      | uname   | email         | pass   | confirmPass | phone      | age | city   |
      | newuser2 | new2@mail.com | pass12 | pass12      | 1234567891 | 22  | Jenin  |

  Scenario Outline: Unsuccessful sign-up with invalid phone number
    When the person signs up with the invalid phone size details:
      | UserName | Email        | Password  | ConfirmPassword | Phone      | Age | City   |
      | <uname>  | <email>      | <pass>    | <confirmPass>   | <phone>    | <age> | <city> |
#    Then the person should see an error message "Phone number must be 10 digits"
    Examples:
      | uname   | email         | pass      | confirmPass | phone       | age | city   |
      | newuser3 | new3@mail.com | pass78901 | pass78901   | 12345678    | 22  | Ramallah |

  Scenario Outline: Unsuccessful sign-up with password mismatch
    When the person signs up with password mismatch details:
      | UserName | Email        | Password  | ConfirmPassword | Phone      | Age | City   |
      | <uname>  | <email>      | <pass>    | <confirmPass>   | <phone>    | <age> | <city> |
#    Then the person should see an error message "Passwords do not match"
    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age | city   |
      | newuser4 | new4@mail.com | pass78901 | pass98701   | 1234567891 | 22  | Nablus  |
  Scenario Outline: Unsuccessful sign-up with existing username
    When the person signs up with existing username details:
      | UserName | Email        | Password  | ConfirmPassword | Phone      | Age | City   |
      | <uname>  | <email>      | <pass>    | <confirmPass>   | <phone>    | <age> | <city> |
#    Then the person should see an error message "Username already exists"
    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age | city   |
      | user1   | new1@mail.com | pass78901 | pass78901   | 1111111111 | 22  | Jenin  |

  Scenario Outline: Unsuccessful sign-up with empty fields
    When the person signs up with empty fields details:
      | UserName | Email        | Password  | ConfirmPassword | Phone      | Age | City   |
      | <uname>  | <email>      | <pass>    | <confirmPass>   | <phone>    | <age> | <city> |
#    Then the person should see an error message "All fields are required"
    Examples:
      | uname   | email         | pass      | confirmPass | phone      | age | city   |
      | user5   |               | pass78901 | pass78901   | 1234567890 | 22  | Nablus  |

