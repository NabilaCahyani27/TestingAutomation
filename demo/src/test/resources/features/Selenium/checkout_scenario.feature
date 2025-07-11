Feature: Checkout from SauceDemo

  Scenario: User logs in and completes a purchase
  Given User is on the SauceDemo login page
  When User logs in as "standard"
  Then User should see "inventory page"
  When User adds "Sauce Labs Backpack" to the cart
  And User proceeds to checkout
  And User fills in checkout information with first name "John", last name "Doe", and postal code "12345"
  Then User should see the order confirmation message "Thank you for your order!"

