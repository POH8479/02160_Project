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
Feature: Persistency Layer

  @tag1
  Scenario: Open/close system
    Given A user that has registerd a new patient
    When I close the system
    And reopen the system
    Then The User and Patient are still saved

  @tag2
  Scenario Outline: Moving patients
    Given A patient that has previously been admitted and is now discharged
    When I admit the patient to my department
    Then The data from their previous admission is still saved
