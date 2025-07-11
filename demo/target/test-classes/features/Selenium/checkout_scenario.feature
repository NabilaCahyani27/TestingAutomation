Feature: Complete checkout process on SauceDemo

  Scenario: User logs in and completes a purchase
    Given User is on the SauceDemo login page
    When User logs in with username "standard_user" and password "secret_sauce"
    Then User should be redirected to the inventory page
    When User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    And User fills in checkout information with first name "Nabila", last name "Cahyani", and postal code "12345"
    Then User should see the order confirmation message "Thank you for your order!"
