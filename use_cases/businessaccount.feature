Feature: Business Account Management

  Scenario: Owner adds business profile information
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to the business profile
    When the owner adds business profile information with the following valid details:
      | Address | Contact           | BusinessName  | BusinessID |
      | Nablus  | newemail1@example.com | New Business 1 | 1         |
      | Jenin   | 059997896         | New Business 2 | 2         |
    Then the details should be added to the profile

#  Scenario Outline: Owner views business information
#    When the owner views the business information
#    Then the business details should be:
#      | Address   | Contact         | BusinessName    | BusinessID |
#      | <address> | <contact>       | <bname>         | <bid>      |
#
#    Examples:
#      | address          | contact        | bname            | bid |
#      | New Address 1    | New Contact 1  | New Business 1   | 1   |
#      | New Address 2    | New Contact 2  | New Business 2   | 2   |

  Scenario Outline: Owner updates business information
    When the owner updates the info with the following valid details:
      | Address   | Contact         | BusinessName    | BusinessID |
      | <address> | <contact>       | <bname>         | <bid>      |
    Then the business information should be updated successfully

    Examples:
      | address          | contact        | bname            | bid |
      | New Address 1    | New Contact 1  | New Business 1   | 1   |
      | New Address 2    | New Contact 2  | New Business 2   | 2   |

#  Scenario Outline: Owner updates business information with invalid details
#    When the owner updates the info with the following invalid details:
#      | Address   | Contact         | BusinessName    | BusinessID |
#      | <address> | <contact>       | <bname>         | <bid>      |
#    Then the business information update should fail due to invalid details
#
#    Examples:
#      | address          | contact        | bname            | bid |
#      | Invalid Address  | Invalid Contact| Invalid Business | 1   |
#      | Invalid Address  | Invalid Contact| Invalid Business | 2   |
