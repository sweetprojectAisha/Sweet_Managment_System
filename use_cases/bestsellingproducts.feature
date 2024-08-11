Feature: Identify Best-Selling Products

  Scenario:The following products exist:
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to product management
    When the owner adds a product with the following valid details:
      | ProID | ProName | Description | ProPrice | ProQuantity |
      | 1     | cake    | tasty       | 999.99   | 10          |
      | 2     | cookie  | well_backed | 49.99    | 50          |
      | 3     | cake2   | well_backed | 39.99    | 80          |
    Then the product should be added to the list

  Scenario: View best-selling products when no products have been sold
    Then I should see an empty list

  Scenario:
    When the owner adds a sold product with the following details:
      | ProID | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage | DiscountPercentage |
      | 1     | 10           | 999.99       | 9999.90      | 50.0           | 0.0                |
      | 2     | 50           | 49.99        | 2499.50      | 30.0           | 0.0                |
      | 3     | 75           | 51.99        | 1800.90      | 45             | 0.0                |
    Then the product sold should be added to the list

  Scenario: View best-selling products when products have been sold
    Given the following products have been sold:
      | ProID | PricePerUnit | QuantitySold | CostPercentage | DiscountPercentage |
      | 1     | 10.00        | 10           | 20             | 0.0                |
      | 2     | 20.00        | 50           | 30             | 0.0                |
      | 3     | 15.00        | 75           | 25             | 0.0                |

    Then I should see the following products in order:
      | ProID | ProName   | QuantitySold |
      | 3     | cake2 | 75           |
      | 2     | cookie | 50           |
      | 1     | cake | 10           |

  Scenario Outline: Delete an existing product
    When the owner deletes a product with the following details:
      | ProID   | ProName   | Description      | ProPrice   | ProQuantity   |
      | <proid> | <proname> | <prodescription> | <proprice> | <proquantity> |
    Then the product should be removed from the list
    Examples:
      | proid | proname | prodescription | proprice | proquantity |
      | 1     | cake    | tasty          | 999.99   | 10          |
      | 2     | cookie  | well_backed    | 49.99    | 50          |
      | 3     | cake2   | well_backed    | 39.99    | 60          |