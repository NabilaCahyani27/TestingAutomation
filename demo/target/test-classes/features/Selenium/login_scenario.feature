Feature: Login to SauceDemo with multiple users

  # Scenario Outline: Login with different users
  #   Given User is on the SauceDemo login page
  #   When User logs in with username "<username>" and password "<password>"
  #   Then User should see "<expectedResult>"

  #   Examples:
  #     | username                | password       | expectedResult                     |
  #     | standard_user           | secret_sauce   | inventory page                     |
  #     | locked_out_user         | secret_sauce   | Epic sadface: Sorry, this user has been locked out. |
 Scenario: Login as standard user
    Given User is on the SauceDemo login page
    When User logs in as "standard"
    Then User should see "inventory page"

  Scenario: Login as locked_out user
    Given User is on the SauceDemo login page
    When User logs in as "locked_out"
    Then User should see "Epic sadface: Sorry, this user has been locked out."

