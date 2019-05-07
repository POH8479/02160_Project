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
  Scenario: Number of free beds
    Given A department in the hospital
    When I request the number of available beds
    Then an integer is returned with the number of free beds

  @tag2
  Scenario: Request list of beds 
    Given a department
    When I request the list of beds in that department
    Then A List of Bed objects is returned
    