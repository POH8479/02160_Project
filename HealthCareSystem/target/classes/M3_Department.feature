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
Feature: Represent the different departments available in hospitals: Medical, Managment, Receptionist
		
  Scenario Outline: Staff member department is requested
    Given Staff member <staff> is in the <department> department
    When I check the staff members department
    Then The System returns the <staffDepartment>
    
    Examples:
    	| staff |    department    | staffDepartment |
    	|  001  | Medical          | Medical         |
    	|  002  | Human Resources  | Human Resources |
    	|  003  | Receptionist     | Receptionist    |
	
  Scenario: There are available beds in Medical department
    Given A Medical department has 100 beds
    And 66 beds in use
    When Check available beds
    Then System displays that there are 44 available Beds
    
  Scenario: Receptionist succesfully checks-in a new patient
    Given The staff member belongs to the Receptionist department
    And The Medical department has an available bed
    When Check-in Patient
    Then the number of available beds is decreased by 1
    
  Scenario: A
    Given A
    And A
    When A
    Then A