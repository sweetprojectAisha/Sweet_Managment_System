Feature: Apply Dynamic Discounts

  Scenario:The following products exist:
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to product management
    When the owner adds a product with the following valid details:
      | ProID | ProName | Description | ProPrice | ProQuantity |
      | 1     | cake    | tasty       | 999.99   | 10          |
      | 2     | cookie  | well_backed | 49.99    | 50          |
      | 3     | cake2   | well_backed | 39.99    | 80          |
    Then the product should be added to the list

  Scenario: Apply a discount to a product
    When the owner applies a discount of 10% to the product with ID 1
    Then the product's price should be updated to 899.99


  Scenario: Attempt to apply an invalid discount
#
    When the owner attempts to apply an invalid discount of -10% to the product with ID 2
    Then the product's price should remain 49.99

  Scenario Outline: Delete an existing product
    When the owner deletes a product with the following details:
      | ProID   | ProName   | Description      | ProPrice   | ProQuantity   |
      | <proid> | <proname> | <prodescription> | <proprice> | <proquantity> |
    Then the product should be removed from the list
    Examples:
      | proid | proname | prodescription | proprice | proquantity |
      | 1     | cake    | tasty          | 999.99   | 10          |
      | 2     | cookie  | well_backed    | 49.99    | 50          |
      | 3     | cake2   | well_backed    | 39.99    | 80          |