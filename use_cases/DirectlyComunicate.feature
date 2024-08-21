
Feature: Directly communicate with store owners and suppliers for inquiries or assistance

  Background:
    Given the user is on the communication page
    And the list of available contacts includes:
      | contactName  | contactType | availability | contactMethod |
      | StoreOwner1  | Store Owner | Available    | Email         |
      | Supplier1    | Supplier    | Available    | Phone         |
      | StoreOwner2  | Store Owner | Busy         | Email         |
      | Supplier2    | Supplier    | Available    | Phone         |
      | StoreOwner3  | Store Owner | Available    | Chat          |

  Scenario Outline: User sends an inquiry to a contact
    Given the user has the following details:
      | userID | username  | email          | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>        | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a contact:
      | contactName  | contactType | availability | contactMethod |
      | <contactName>| <contactType>| <availability>| <contactMethod>|
    And the user sends an inquiry with the message "<message>"
    Then the user should see a "<expectedResponse>"
    And the communication log should be updated with:
      | contactName  | message              | status          |
      | <contactName>| <message>            | <communicationStatus> |

    Examples:
      | userID | username  | email          | phone      | age | password  | dietaryNeeds | foodAllergies | contactName  | contactType  | availability | contactMethod | message                          | expectedResponse                      | communicationStatus |
      | 1      | user1     | user1@mail.com | 0591234567 | 21  | password1 | Vegan        | Nuts          | StoreOwner1  | Store Owner | Available    | Email         | Inquiry sent successfully       | Inquiry sent successfully            | Sent                |
      | 2      | user2     | user2@mail.com | 0597654321 | 22  | password2 | Gluten-Free  |               | Supplier1    | Supplier    | Available    | Phone         | Inquiry sent successfully     | Inquiry sent successfully            | Sent                |

  Scenario Outline: User attempts to contact an unavailable contact
    Given the user has the following details:
      | userID | username  | email          | phone      | age | password  | dietaryNeeds | foodAllergies |
      | <userID> | <username> | <email>        | <phone>    | <age> | <password> | <dietaryNeeds> | <foodAllergies> |
    When the user selects a contact:
      | contactName  | contactType | availability | contactMethod |
      | <contactName>| <contactType>| <availability>| <contactMethod>|
    And the user sends an inquiry with the message "<message>"
    Then the user should see a "<expectedResponse>"

    Examples:
      | userID | username  | email          | phone      | age | password  | dietaryNeeds | foodAllergies | contactName  | contactType  | availability | contactMethod | message                          | expectedResponse                                |
      | 1      | user1     | user1@mail.com | 0591234567 | 21  | password1 | Vegan        | Nuts          | StoreOwner2  | Store Owner | Busy         | Email         | Contact is currently busy, please try later       | Contact is currently busy, please try later  |
      | 2      | user2     | user2@mail.com | 0597654321 | 22  | password2 | Gluten-Free  |               | StoreOwner2  | Store Owner | Busy         | Email         | Contact is currently busy, please try later | Contact is currently busy, please try later  |
