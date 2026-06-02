Feature: Snapshot Monitoring

  Scenario: Latest snapshot should update continuously
    Given User is on login page
    When User login with correct credentials
    And User navigate to snapshot page
    Then Latest snapshot should updated continuously within 60