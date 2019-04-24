#Author: Kun Zhu
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: create a profile
  I want to use this template for creating a profile

  @tag1
  Scenario: New profile Creation Test
    Given A new patient comes for treatment(s)
    And a new staff is employed
    When I want to create profile(s)
    Then The system pops dialog window for inputing relevant information
    And sytem save the profile in data base after typing and confirmation by the user

  @tag2
  Scenario Outline: verify patient data
    Given I want to create a step with <ID number>, <name>, <birth date>, <email> etc 
    When I type for the <ID number>, <name>, <birth date> in step
    Then I verify the <ID number>, <email> are unique
    And I want to verify <birth date> is in valid format
