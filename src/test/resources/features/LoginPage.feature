Feature: Login page feature

  Scenario: Login page title
    Given User is on login page
    When User gets the title of the page
    Then Page title should be "TrackForce"

  Scenario: Forgot password link
    Given User is on login page
    Then Forgot password link should be displayed

  Scenario: Login with correct credentials
    Given User is on login page
    When User enters username "kabir@ibos.io"
    And User enters password "Admin24@7"
    And User clicks on login button
    Then User gets the title of the page
    And The page title should be "TrackForce"