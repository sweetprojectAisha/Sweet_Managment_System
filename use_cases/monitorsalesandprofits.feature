Feature: Monitor Sales and Profits

  Scenario:The following products exist:
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to product management
    When the owner adds a product with the following valid details:
#    And the following products exist:
      | ProID | ProName | Description | ProPrice | ProQuantity |
      | 1     | cake    | tasty       | 999.99   | 10          |
      | 2     | cookie  | well_backed | 49.99    | 50          |
    Then the product should be added to the list

  Scenario:
    When the owner adds a sold product with the following details:
      | ProID | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
      | 1     | 10           | 999.99       | 9999.90      | 50.0           | 0.0                |
      | 2     | 70           | 49.99        | 2499.50      | 30.0           | 0.0                |
    Then the product sold should be added to the list

  Scenario:
    When the owner cannot add a sold product that does not exist:
      | ProID | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
      | 3     | 15           | 19.99        | 5599.90      | 30.0           | 0.0                |
    Then the product sold should not be added to the list

  Scenario: View overall sales and profits
    Given the following products have been sold:
      | ProID | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage |
      | 1     | 10           | 999.99       | 9999.90      | 50.0           |
    Then the total sales revenue should be 9999.90

  Scenario: Attempt to view sales and profits with no sales
    Given the following products have not been sold:
      | ProID | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage |
      | 2     | 70           | 49.99        | 2499.50      | 30.0           |
#    Given no products have been sold
    Then the total revenue should be 9999.9
    And the profit should be 4999.95


  Scenario Outline: Delete an existing product
    When the owner deletes a product with the following details:
      | ProID   | ProName   | Description      | ProPrice   | ProQuantity   |
      | <proid> | <proname> | <prodescription> | <proprice> | <proquantity> |
    Then the product should be removed from the list
    Examples:
      | proid | proname | prodescription | proprice | proquantity |
      | 1     | cake    | tasty          | 999.99   | 10          |
      | 2     | cookie  | well_backed    | 49.99    | 50          |