#Author: s174406@student.dtu.dk
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
Feature: User Interface

  @tag1
  Scenario: Log in
    Given A user and a patient
    When I log in
    Then I am able to view the patients data

  @tag2
  Scenario: User Interface
    Given An existing user in the system
    When I change a users information
    Then The information is saved and accepted byt the system
