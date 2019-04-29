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
Feature: editting profile(s)

  @tag1
  Scenario: edit a profile profile
    Given A nurse and a patient
    Then A nurse edits the patients data
    And The new information is saved to the HMS

  @tag2
  Scenario Outline: verify the changes
    Given I have changed a Patients profile
    When I request their information
    Then The patients information has succesfully changed
