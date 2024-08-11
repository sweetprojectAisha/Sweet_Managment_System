Feature: Apply Dynamic Discounts

  Scenario: Apply a discount to a product
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to the sales management page
    When the owner applies a discount to the product with the following details:
      | ProID | ProName |Description | QuantitySold | PricePerUnit | TotalRevenue | CostPercentage |DiscountPercentage |
      | 1     | cake    |tasty | 10           | 999.99       | 9999.90      | 50.0               |10.0|




    Then the product's price should be updated with the discount

  Scenario: View discounted product details
    Given the following products exist:
      | ProID | ProName | Price |
      | 1     | cake    | 999.99|
    When the owner views the details for the product with ID 1
    Then the product's price should reflect the applied discount

  Scenario: Apply discount to multiple products
    Given the following products exist:
      | ProID | ProName | Price |
      | 1     | cake    | 999.99|
      | 2     | cookie  | 49.99 |
    When the owner applies the following discounts:
      | ProID | DiscountPercentage |
      | 1     | 15.0               |
      | 2     | 5.0                |
    Then the products' prices should be updated with the respective discounts

  Scenario: Attempt to apply an invalid discount
    Given the following product exists:
      | ProID | ProName | Price |
      | 1     | cake    | 999.99|
    When the owner attempts to apply a discount with the following details:
      | ProID | DiscountPercentage |
      | 1     | -10.0              |
    Then an error message "Invalid discount percentage" should be displayed
