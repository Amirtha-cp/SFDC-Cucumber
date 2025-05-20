Feature: Login Feature

  Background:
    Given User is on SFDC login page

  Scenario Outline: Verify successful login with valid credentials
    When User logs in with "<username>" and "<password>"
    Then User should land on home page with title 

    Examples:
      | username                          | password         |
      | amirtha.c.panneer126@agentforce.com | amirthajava123   |
      
      
    Scenario Outline: Verify successful login with invalid credentials
    When User logs in with "<username>" and "<password>"
    Then User should land on home page with title 

    Examples:
      | username                          | password         |
      | amirtha.c.panneer126@agentforce.com | amirtha   |