Feature: Verify customer successfully search for products, login to site and checkout product

  @earlylogin @all
  Scenario Outline: As a customer i should be able to search, select product, add it to cart and it checkout
    Given I am on amazon
    When I successfully do early login to site
    Then I search for "<item>"
    And I select a random item from results
    Then I add random item to cart
    And I should be able to check out the product
    Examples:
      | item       |
      | 65-inch TV |

  @latelogin @all
  Scenario Outline: As a customer i should be able to search, select product, add it to cart and it checkout
    Given I am on amazon
    When I search for "<item>"
    And I select a random item from results
    When I request item to be added to cart
    Then I should be prompted to login to Amazon
    When I successfully login to site item should be added to cart
    Examples:
      | item       |
      | 65-inch TV |