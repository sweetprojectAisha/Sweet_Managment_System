Feature: Order Management

  Scenario: the following orders exist

    When the ordered have been added
      | OrderID | Status  |
      | 1       | WAITING |
      | 2       | INITIAL |
    Then the orders should be added

  Scenario: Track an order
    Given an order with ID 1 exists with status "WAITING"
    Then process order with ID 1
    And an order with ID 1 exists with status "ACCEPTED"

  Scenario: Track an order with invalid status
    Given an order with ID 2 exists with status "INITIAL"
    Then process order with ID 2 and invalid status "Order with id 2 state isn't WAITING, so it cannot be processed."
    And an order with ID 2 exists with status "INITIAL"

  Scenario: Track an order that doesn't exist
    Then process order with ID 3 and invalid status "Order not found."


