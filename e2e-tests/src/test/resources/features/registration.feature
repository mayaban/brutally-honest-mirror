Feature: User registration

  Scenario: A new user registers successfully
    Given a unique email and a valid password
    When the user registers via the API
    Then the response should contain a valid token