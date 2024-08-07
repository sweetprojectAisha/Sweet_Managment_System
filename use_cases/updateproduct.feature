Feature: Update Product

  Background:
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to product management
    And the following products exist:
      | ProID | ProName  | Description | ProPrice | ProQuantity |
      | 1     | cake     | tasty       | 999.99   | 10          |
      | 2     | cookie   | well_backed | 49.99    | 50          |

  Scenario Outline: Update product details with valid data
    When the owner updates the product with the following detailss:
      | ProID   | ProName        | Description     | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the product with ID "<proid>" should have the updated details:
      | ProName        | Description     | ProPrice | ProQuantity |
      | <proname>      | <prodescription> | <proprice> | <proquantity> |

    Examples:
      | proid | proname  | prodescription | proprice | proquantity |
      | 1     | cake     | very tasty     | 1099.99  | 15          |
      | 2     | cookie   | super well_baked | 59.99    | 60          |

  Scenario Outline: Try to update a product with invalid data
    When the owner updates the product with the following detailss:
      | ProID   | ProName        | Description     | ProPrice   | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the system should display a validation error message
    And the product with ID "<proid>" should not be updated

    Examples:
      | proid | proname | prodescription | proprice | proquantity |
      | 1     | cake    | very tasty     | -1099.99 | 15          |
      | 2     | cookie  | super well_baked | 59.99  | -60         |

  Scenario Outline: Try to update a non-existent product
    Given the following products exist:
      | ProID | ProName | Description | ProPrice | ProQuantity |
      | 1     | Widget  | A useful widget | 19.99    | 100         |
    And the expected product ID is "<proid>"
    When the owner updates the product with the following details:
      | ProID   | ProName        | Description     | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the system should display a product not found error message
    And the product with ID "<proid>" should not be updated

    Examples:
      | proid | proname | prodescription | proprice | proquantity |
      | 3     | muffin  | freshly baked  | 29.99    | 40          |
      | 4     | donut   | sweet          | 19.99    | 30          |

  Scenario Outline: Try to update a product with missing fields
    When the owner updates the product with the following detailss:
      | ProID   | ProName        | Description     | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the system should display a validation error message
    And the product with ID "<proid>" should not be updated

    Examples:
      | proid | proname | prodescription | proprice | proquantity |
      | 1     |         | very tasty     | 1099.99  | 15          |
      | 2     | cookie  |                | 59.99    | 60          |