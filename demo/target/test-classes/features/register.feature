# apa yang dilakukan
# Feature: Is it Friday yet?
#   Everybody wants to know when it's Friday
# # desc test case
# # scenario
#   Scenario: Sunday isn't Friday
#   # variable yang di berikan 
#   # variabe today di set Sunday
#   # preperation
#     Given today is Sunday
# # ketika melakukan task 
#     When I ask whether it's Friday yet
# #assert
#     Then I should be told "Nope"
Feature: Employee API

  Scenario: Register a new employee
    When Send employee to register with body:
      """
      {
       "email": "albertsimanjuntak1122@gmail.com",
      "full_name": "Albert Simanjuntak",
      "password": "@admin123",
      "department": "Technology",
      "phone_number": "082264189134"
      }
      """
    Then The response status must be 200
    And The response schema should be match with schema "register-schema.json"

  Scenario: Login with registered employee
    When Send employee to login with body:
      """
      {
        "email": "albertsimanjuntak1122@gmail.com",
        "password": "@admin123"
      }
      """
    Then The response status must be 200
    And Save the token from the response to local storage
  # Scenario:
  #   Given Make sure token in local storage not empty
  #   When Send a request to update with body:
  #     """
  #     {
  #       "email": "test113@test.com",
  #       "password": "test",
  #       "full_name": "Ini nama yg udh diupdate ya",
  #       "department": "Tech",
  #       "title": "Backend Engineer"
  #     }
  #     """
  #   Then The response status must be 200
  #   And Full name in the response must be "Ini nama yg udh diupdate ya"
  #   And Department in the response must be "Tech"
  #   And Title in the response must be "Backend Engineer"
  # Scenario:
  #   Given Make sure token in local storage not empty
  #   When Send a request to get employee
  #   Then The response status must be 200
  #   And Full name in the response must be "Ini nama yg udh diupdate ya"
  #   And Department in the response must be "Tech"
  #   And Title in the response must be "Backend Engineer"
  # Scenario:
  #   Given Make sure token in local storage not empty
  #   When Send a request to delete employee
  #   Then The response status must be 200
