Feature: User Menu Dropdown and Related Functionalities

  Background:
    Given User logs in with valid credentials

  Scenario: Verify User Menu dropdown options
    When User opens the User Menu dropdown
    Then Dropdown should display all expected options

  Scenario: Verify My Profile functionality
    When User opens the User Menu dropdown
    And User clicks on "My Profile"
    Then User should be on the profile page with correct name
    When User edits the last name in the profile
    Then User profile name should be updated
    When User posts a comment
    Then Comment should be displayed on profile
    When User uploads a file
    Then File upload should be successful

  Scenario: Verify My Settings functionality
    When User opens the User Menu dropdown
    And User clicks on "My Settings"
    Then My Settings page should be displayed
    When User downloads login history
    Then Login history file should be downloaded
    When User adds "Reports" tab to SalesForce app
    Then Reports tab should be present in selected tabs
    When User tests activity reminders
    Then Reminder popup should appear and be dismissed

  Scenario: Verify Developer Console launch
    When User opens the User Menu dropdown
    And User clicks on "Developer Console"
    Then Developer Console should open in a new window
