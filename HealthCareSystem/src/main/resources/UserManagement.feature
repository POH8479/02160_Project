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
Feature: User Management

  @tag1
  Scenario: A User
    Given A User and a patient
    When the user trys to access a patients medical record
    Then An Exception is thrown

  @tag2
  Scenario: Nurse requesting other departments patient records
    Given A nurse and a patient from another department
    When The nurse requests the patient medical data
    Then An Exception is thrown
 
  @tag3
  Scenario: Admin requesting patient records
    Given An admin and a patient from another department
    When The admin requests the patient medical data
    Then No Exception is thrown

  @tag4
  Scenario: Nurse reading User data
    Given A nurse and a User
    When The nurse requests the Users data
    Then An Exception is thrown
 
  @tag3
  Scenario: Admin reading User data
    Given An admin and a User
    When The admin requests the Users data
    Then No Exception is thrown