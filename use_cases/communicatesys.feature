Feature: Owner Communication System
  As an owner, I want to communicate with other owners and users to manage business operations effectively.

Scenario:
    Given the following users exist:
      | userId | username | role   |
      | 1      | Alice    | Owner  |
      | 2      | Bob      | Owner  |
      | 3      | Charlie  | User   |

  Scenario: Owner sends a message to another owner
    When the exist owner with ID 1 send a message "Meeting at 3 PM" to exist owner with ID 2
    Then the message should be delivered to the owner with ID 2
    And the owner with ID 2 should receive a notification

  Scenario: Owner sends a message to a user
#    Given I am logged in as "Bob" with role "Owner"
    When the exist owner with ID 2 send a message "Welcome to our service!" to exist user with ID 3
    Then the message should be delivered to the user with ID 3
    And the user with ID 3 should receive a notification

#  Scenario: Owner views messages from users
#    Given the following users exist:
#      | userId | username | role |
#      | 1      | Alice    | Owner|
#      | 2      | Bob      | Owner|
#      | 3      | Charlie  | User |
#    When the exist user with ID 3 sends a message "I need help with my order." to exist owner with ID 2
#    Then the exist owner with ID 2 should view messages from exist user with ID 3 "I need help with my order."
