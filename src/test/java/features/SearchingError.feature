
@SearchingError
Feature: Search an invalid article


  Scenario Outline: Checking if error message is displayed when searching for an invalid article
    Given I am on ecommerce page
    When I search for <article> that does not exist
    Then "No results were found for your search" alert is displayed

    Examples:
      | article |
      | abcd    |



