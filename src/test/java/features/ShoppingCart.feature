
@tag
  Feature: Purchase the order from ecommerce website

    Background: Given I landed on ecommerce page

    @Regression
    Scenario Outline: Positive test of submitting the order
      Given logged in with email <email> and password <password>
      When I add product <productName> from cart
      And Checkout <productName> and submit the order
      Then "Your order on My Store is complete." message is displayed on ConfirmationPage

      Examples:
        | email           | password | productName           |
        | ilyas@email.com | password | Printed Chiffon Dress |

