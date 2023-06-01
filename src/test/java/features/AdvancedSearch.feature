
@AdvancedSearch
Feature: Search an valid article


  Scenario Outline: Checking if suggestion is displayed when searching for an valid article
    Given I am on ecommerce page
    When I search for <article> that exists
    Then "Casual Dresses > Printed" suggestion is displayed

    Examples:
      | article |
      | dress   |
