Feature: Login to SauceDemo with multiple users

  Scenario Outline: Login with different users
    Given User is on the SauceDemo login page
    When User logs in with username "<username>" and password "<password>"
    Then User should see "<expectedResult>"

    Examples:
      | username                | password       | expectedResult                     |
      | standard_user           | secret_sauce   | inventory page                     |
      | locked_out_user         | secret_sauce   | Epic sadface: Sorry, this user has been locked out. |
      | problem_user            | secret_sauce   | inventory page                     |
      | performance_glitch_user | secret_sauce   | inventory page                     |
      | error_user              | secret_sauce   | inventory page                     |
      | visual_user             | secret_sauce   | inventory page                     |
