Feature: Delete Product

  Scenario:The following products exist:
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to product management
    When the owner adds a product with the following valid details:
#    And the following products exist:
      | ProID | ProName  | Description | ProPrice | ProQuantity |
      | 1     | cake     | tasty       | 999.99   | 10          |
      | 2     | cookie   | well_backed | 49.99    | 50          |
    Then the product should be added to the list


  Scenario Outline: Delete an existing product
    When the owner deletes a product with the following details:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the product should be removed from the list

    Examples:
      | proid | proname      | prodescription | proprice | proquantity |
      | 1     | cake         | tasty          | 999.99   | 10         |
      | 2     | cookie       | well_backed    | 49.99    | 50         |

  Scenario Outline: Attempt to delete a non-existing product
    When the owner attempts to delete a product with the following details:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the product should not be found, and no deletion should occur

    Examples:
      | proid | proname      | prodescription | proprice | proquantity |
      | 3     | pastry       | sweet          | 499.99   | 20         |
      | 4     | muffin       | chocolatey     | 79.99    | 15         |
