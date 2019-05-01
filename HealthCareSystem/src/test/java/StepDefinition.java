import static org.junit.Assert.*;



import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hospitalmanagementsystem.*;


import hospitalmanagementsystem.departments.Emergency;

import hospitalmanagementsystem.users.*;

public class StepDefinition {

	// variables
	User u1;
	Patient p1;

	Emergency d1;
	Inpatient d2;
	Admin a1;
	Nurse n1;
  
	@Given("^Any user of the program and a patient that has not been admitted$")
	public void any_user_of_the_program_and_a_patient_that_has_not_been_admitted() {
	    // create a user
		u1 = new User("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
	}

	@When("^They enter a new patient's non-medical data$")
	public void they_enter_a_new_patient_s_non_medical_data() {
    // create a new patient
		p1 = new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@Then("^The data is saved in the system and a confirmation is displayed to the user$")
	public void the_data_is_saved_in_the_system_and_a_confirmation_is_displayed_to_the_user() {
	    // Check the data has been saved
		assertEquals("John",p1.getPatientInfo().get("First Name"));
	}

	@Given("^A user and a department$")
	public void a_user_and_a_department() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I choose a statistic to see$")
	public void i_choose_a_statistic_to_see() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The correct result is returned$")
	public void the_correct_result_is_returned() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A department$")
	public void a_department(){
	    // create a department
		d1=Emergency.getInstance();
	}

	@When("^I request the number of available beds$")
	public void i_request_the_number_of_available_beds(){
	    //return number of available beds
		
	}

	@Then("^an integer is returned with the number of free beds$")
	public void an_integer_is_returned_with_the_number_of_free_beds(){
	    //check the data returned
		//assertEquals(10,d1.capacity);
	}

	@Given("^A new patient$")
	public void a_new_patient() {
	    // create a new patient
		p1=new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");	

	}

	@Given("^a User$")
	public void a_User(){
	    // create a new user
		u1=new User("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
	}

	@When("^The User registers a patient with their non medical data$")
	public void the_User_registers_a_patient_with_their_non_medical_data(){
	    // register patient data
		u1.registerPatient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@Then("^A new patient is created in the HMS$")
	public void a_new_patient_is_created_in_the_HMS(){
	    // check that information was entered correctly
		assertEquals("Jane", p1.getPatientInfo().get("First Name"));
	}

	@Given("^A new user$")
	public void a_new_user(){
		//create a new user
		u1=new User("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
	}

	@Given("^An Admin$")
	public void an_Admin(){
	    // create a new admin
		a1=new Admin("John Doe", "+4512345678");
	}

	@When("^The Admin creates a new User$")
	public void the_Admin_creates_a_new_User() throws Throwable {
	    // create a new user
		a1.addUser("John Doe", "123 Main St Anytown, Denmark", "+4512345678","Doctor");
	}

	@Then("^A new user is created in the HMS$")
	public void a_new_user_is_created_in_the_HMS() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A nurse and a patient$")
	public void a_nurse_and_a_patient() throws Throwable {
	    // create a nurse and a patient
		n1=new Nurse("John Doe", "+4512345678","Emergency" );
		p1=new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@Then("^A nurse edits the patients data$")
	public void a_nurse_edits_the_patients_data() throws Throwable {
	    // Edit patient data
		n1.editMedicalData(p1, "Jane");
	}

	@Then("^The new information is saved to the HMS$")
	public void the_new_information_is_saved_to_the_HMS() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A user that has registerd a new patient$")
	public void a_user_that_has_registerd_a_new_patient() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I close the system$")
	public void i_close_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^reopen the system$")
	public void reopen_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The User and Patient are still saved$")
	public void the_User_and_Patient_are_still_saved() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^Two different departments$")
	public void two_different_departments(){
	    // create two new departments
		d1=Emergency.getInstance();
		d2=Inpatient.getInstance();
	}

	@When("^I search for the department with the most patients$")
	public void i_search_for_the_department_with_the_most_patients(){
	    //return department with most patients
		Query.depMostPatients();
		
	}

	@Then("^I will be shown the correct department$")
	public void i_will_be_shown_the_correct_department() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A user and a patient$")
	public void a_user_and_a_patient() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I log in$")
	public void i_log_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am able to view the patients data$")
	public void i_am_able_to_view_the_patients_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A User and a patient$")
	public void a_User_and_a_patient() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^the user trys to access a patients medical record$")
	public void the_user_trys_to_access_a_patients_medical_record() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^An Exception is thrown$")
	public void an_Exception_is_thrown() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
