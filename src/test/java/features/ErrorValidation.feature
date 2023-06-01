
@ErrorValidation
Feature: Error validation


  Scenario Outline: Positive test of login with wrong credentials
    Given I landed on ecommerce page
    When logged in with email <email> and password <password>
    Then "Authentication failed." message is displayed

    Examples:
      | email          | password |
      | iyas@email.com | password |

