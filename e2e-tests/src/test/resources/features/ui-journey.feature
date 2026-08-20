Feature: Registering and getting a reflection through the UI

  Scenario: A new user registers and submits an excuse
    Given the user is on the registration page
    When they register with a unique email and a valid password
    And they submit an excuse about missing the gym
    Then they should see an AI-generated reflection