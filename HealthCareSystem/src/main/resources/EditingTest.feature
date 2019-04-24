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
  I want to use this template for editting profile(s)

  @tag1
  Scenario: edit a profile
    Given A user or patient has changed his personal info
    And A patient has received a new treatment
    And A patient is being transfered to another department
    When I open the profile, then make the change
    Then I confirm the changes
    And save to the database

  @tag2
  Scenario Outline: verify the changes
    Given I want to create a step with <name> etc 
    When I type for the <name> in step
    Then I verify the <patient number> is unique
    And I want to verify <birth date> is in valid format
