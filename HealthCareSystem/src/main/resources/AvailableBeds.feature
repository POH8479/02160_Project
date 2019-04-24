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
Feature: check for available beds
  I want to use this template for available beds in department(s)

  @tag1
  Scenario: Title of your scenario
    Given I want to check available beds
    And total amount of beds
    And beds are in use
    When I click on facility under specific department(s)
    And I choose to see available beds
    And I shoose to see total amount of beds
    And I choose to see beds that are in occupied
    Then I have a list of available beds
    And I have a list of occupied beds with patients' <name>, <patient number>

  @tag2
  Scenario Outline: data 
    Given I want to write a step with <department>
    When I check for the <beds> in step
    Then I verify the <status> in step
    
    Examples: 
      | department  | total beds | available beds  |
      | department1 |     50 | 30    |
      | department2 |     70 | 20    |
