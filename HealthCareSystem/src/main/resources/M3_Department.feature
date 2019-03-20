#Author: s186111@student.dtu.dk
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
Feature: Represent the different departments available in hospitals: Health Care Staff, Managment, Receptionist

  Scenario: Available beds in Health Care department
    Given A Health Care department has 100 beds
    And 66 beds in use
    When Check available beds
    Then System displays the number of available Beds
		
  Scenario Outline: Staff member department is requested
    Given A staff member <staffMember> is in the <department>
    When I check department
    Then The System returns the <department>
    
    Examples:
    	| staffMember |    department    |
    	|     001     | Health Care      |
    	|     002     | Human Resources  |
	
  Scenario: A Patient is succesfully checked in
    Given A staff member belongs to the Receptionist department
    And The Health Care department has an available bed
    When Check-in Patient
    Then The system checks-in the patient
    Then the system updates the available beds