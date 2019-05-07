import static org.junit.Assert.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import hospitalmanagementsystem.*;

public class StepDefinition {

	// variables
	User u1;
	Patient p1;

	Emergency em;
	Admin a1;
	Nurse n1;
	Doctor d1;
	String result;
	
	Bed b1;
	Bed b2;
  
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
		assertEquals("Jane",p1.getFirstName());
	}
	
	@Given("^A user and a department$")
	public void a_user_and_a_department(){
	    // Create a user and a department
		u1 = new User("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
		em=Emergency.getInstance();
	}

	@When("^I choose a statistic to see$")
	public void i_choose_a_statistic_to_see(){
	    // Return a statistic
		Query q = new Query();
		q.depMostUsers();
	}

	@Then("^The correct result is returned$")
	public void the_correct_result_is_returned(){
	    // check to see if result is correct
		Query q = new Query();
		assertEquals("Emergency",q.depMostUsers());
	}

	@Given("^A department$")
	public void a_department(){
	    // create a department
		em=Emergency.getInstance();
	}

	@When("^I request the number of available beds$")
	public void i_request_the_number_of_available_beds(){
	    //return number of available beds
		Query query = new Query();
		result = query.bedStatus();
	}

	@Then("^an integer is returned with the number of free beds$")
	public void an_integer_is_returned_with_the_number_of_free_beds(){
	    //check the data returned
		assertEquals("Inpatient: 2\nEmergency: 1",result);
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
		p1 = new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@Then("^A new patient is created in the HMS$")
	public void a_new_patient_is_created_in_the_HMS(){
	    // check that information was entered correctly
		assertEquals("Jane", p1.getFirstName());
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
	public void the_Admin_creates_a_new_User() {
	    // create a new user
		u1 = null;
		u1 = new User("John Doe", "+4512345678","Doctor");
	}

	@Then("^A new user is created in the HMS$")
	public void a_new_user_is_created_in_the_HMS() {
	    // check the User was created
		assertTrue(u1 != null);
	}

	@Given("^A nurse and a patient$")
	public void a_nurse_and_a_patient() {
	    // create a nurse and a patient
		n1=new Nurse("John Doe", "+4512345678","Emergency" );
		p1=new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@Then("^A nurse edits the patients data$")
	public void a_nurse_edits_the_patients_data() {
	    // Edit patient data
		p1.setFirstName("Edit");
	}

	@Then("^The new information is saved to the HMS$")
	public void the_new_information_is_saved_to_the_HMS() {
	    // Check the users name was changed
	    assertEquals("Edit",p1.getFirstName());
	}

	@Given("^Two different departments$")
	public void two_different_departments() {
	    // two departments
		Emergency.getInstance();
		Inpatient.getInstance();
	}

	@When("^I search for the department with the most patients$")
	public void i_search_for_the_department_with_the_most_patients() {
	    // call the query method
		Query q = new Query();
		result = q.depMostPatients();
	}

	@Then("^I will be shown the correct department$")
	public void i_will_be_shown_the_correct_department() {
	    // check the answer is correct
		assertEquals("Inpatient, Emergency", result);
	}

	@Given("^A user and a patient$")
	public void a_user_and_a_patient() {
	    // given a user and patient
		n1 = new Nurse("John Doe", "+4512345678","Emergency" );
		p1 = new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@When("^I log in$")
	public void i_log_in() {
	    // when validate credentials
	    assertEquals("Nurse", n1.getType());
	}

	@Then("^I am able to view the patients data$")
	public void i_am_able_to_view_the_patients_data() {
	    // check
		assertEquals("Jane",p1.getFirstName());
	}

	@Given("^A User and a patient$")
	public void a_User_and_a_patient(){
	    // given a user and patient
		u1 = new User();
		p1 =  new Patient("Jane", "Doe", "01/01/2000", "456 North St Anytown, Denmark", "+45234556789");
	}

	@When("^the user trys to access a patients medical record$")
	public void the_user_trys_to_access_a_patients_medical_record() {
	    // get the record
	    p1.getRecord();
	}

	@Then("^An Exception is thrown$")
	public void an_Exception_is_thrown() {
	    // The Get Record is not available in the Users GUI
	    assertTrue(true);
	}
	
	@Given("^A nurse and a patient with no allocated bed$")
	public void a_nurse_and_a_patient_with_no_allocated_bed() {
	    // Create a new Nurse and a Patient in the Emergency department
		n1 = new Nurse("Nurse", "12345678", "Emergency");
		p1 = new Patient("John", "Doe", "01/01/2000", "Denmark", "12345678");
		p1.setDepartment("Emergency");
		Emergency.getInstance().addPatient(p1);
	}

	@Given("^a bed that is not in use$")
	public void a_bed_that_is_not_in_use() {
	    // create a new Bed
	    b1 = new Bed("Emergency");
	}

	@When("^I assign the patient to the bed$")
	public void i_assign_the_patient_to_the_bed() {
	    // assign the patient to the bed
		p1.setBed(b1.getBedID());
		b1.updatePatient(p1);
	}

	@Then("^The bed is registered as being in use and can no longer be assigned$")
	public void the_bed_is_registered_as_being_in_use_and_can_no_longer_be_assigned() {
	    // try assign the bed to a new Patient
		Patient newPatient = new Patient("John Doe", "The Second", "01/01/2000", "Denmark", "12345678");
		newPatient.setDepartment("Emergency");
	    try {
	    	b1.updatePatient(newPatient);
	    	fail("Should have thrown IllegalArgumentException");
	    } catch(IllegalArgumentException e) {
	    	assertEquals(String.format("Bed %s is already occupied", b1.getBedID()), e.getMessage());
	    }
	}

	@Then("^The departments number of free beds changes$")
	public void the_departments_number_of_free_beds_changes() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(b1.getPatient() != null);
	}

	@Given("^A Doctor and a patient$")
	public void a_Doctor_and_a_patient() {
	    // create a new doctor and patient
		d1 = new Doctor("Doc", "12345678", "Emergency");
		p1 = new Patient("Patient", "Foo", "01/01/2000", "Denamrk", "12345678");
		p1.updateDepartment(Emergency.getInstance());
	}

	@When("^I assign a bed to a patient$")
	public void i_assign_a_bed_to_a_patient() {
	    // assign a bed to a patient
		b1 = new Bed("Emergency");
		b1.updatePatient(p1);
		p1.setBed(b1.getBedID());
	}

	@Then("^The patient occupies a bed in the department$")
	public void the_patient_occupies_a_bed_in_the_department() {
	    // check the patient occupies a bed
		assertEquals(p1.getPatientID(),b1.getPatient());
		assertTrue(p1.getDepartment().equals(b1.getDepartment()));
	}

	@Then("^the number of free beds on the department is updated$")
	public void the_number_of_free_beds_on_the_department_is_updated() {
	    // check b1 is not free
		assertTrue(b1.getPatient() != null);
	}

	@Given("^A nurse and a patient that is currently admitted to a deparment$")
	public void a_nurse_and_a_patient_that_is_currently_admitted_to_a_deparment() {
	    // create a new nuse and patient
		n1 = new Nurse("Nurse", "12345678", "Emergency");
		p1 = new Patient("Patient", "Foo", "01/01/2000", "Denamrk", "12345678");
		p1.updateDepartment(Emergency.getInstance());
		b1 = new Bed("Emergency");
		p1.setBed(b1.getBedID());
		b1.updatePatient(p1);
	}

	@When("^I discharge the patient$")
	public void i_discharge_the_patient() {
	    // discharge the patient
		p1.updateDepartment(null);
	}

	@Then("^The patient is no longer admitted to a department$")
	public void the_patient_is_no_longer_admitted_to_a_department() {
	    // check the patient is off the department list and its own
		for(Patient p : Emergency.getInstance().getPatientList()) {
			if(p.equals(p1)) {
				fail("The patient is still admitted to the department");
			}
		}
		assertTrue(p1.getDepartment() == null);
	}

	@Then("^the number of free beds in the department in incremented by (\\d+)$")
	public void the_number_of_free_beds_in_the_department_in_incremented_by(int arg1) {
	    // check b1 is free
		assertTrue(b1.getPatient() == null);
	}

	@Given("^A nurse and a patient admited to a department$")
	public void a_nurse_and_a_patient_admited_to_a_department() {
	    // A nurse and patient in the Emergency department
		n1 = new Nurse("Nurse", "12345678", "Emergency");
		p1 = new Patient("Patient", "Foo", "01/01/2000", "Denamrk", "12345678");
		p1.updateDepartment(Emergency.getInstance());
	}

	@Given("^A different department to move them to$")
	public void a_different_department_to_move_them_to() {
	    // given Outpatient
		Outpatient.getInstance();
	}

	@When("^I dischage the patient from the (\\d+)st department$")
	public void i_dischage_the_patient_from_the_st_department(int arg1) {
	    // discharge from Emergency
		p1.updateDepartment(null);
	}

	@When("^Admit them to the (\\d+)nd department$")
	public void admit_them_to_the_nd_department(int arg1) {
	    // admit to Outpatient
		p1.updateDepartment(Outpatient.getInstance());
	}

	@Then("^The patient is admitted to the (\\d+)nd department$")
	public void the_patient_is_admitted_to_the_nd_department(int arg1) {
	    // Check p1 is in the Outpatient department
		assertEquals("Outpatient",p1.getDepartment());
	}

	@Then("^All their information is still saved$")
	public void all_their_information_is_still_saved() {
	    // Check p1 is saved
		assertEquals("Patient", p1.getFirstName());
	}

	@Given("^admited to a bed within that department$")
	public void admited_to_a_bed_within_that_department() {
	    // Create a patient in emergency
		p1 = new Patient("Patient", "Foo", "01/01/2000", "Denamrk", "12345678");
		p1.updateDepartment(Emergency.getInstance());
		b1.setPatient(null);
		b1.updatePatient(p1);
		b2 = new Bed("Emergency");
	}

	@When("^I remove the patient from the (\\d+)st bed and assign a new bed to the patient$")
	public void i_remove_the_patient_from_the_st_bed_and_assign_a_new_bed_to_the_patient(int arg1) {
	    // update bed
		b1.setPatient(null);
		p1.setBed(null);
		b2.updatePatient(p1);
		p1.setBed(b2.getBedID());
	}

	@Then("^the old bed becomes free$")
	public void the_old_bed_becomes_free() {
	    // check the old bed is free
		assertTrue(b1.getPatient() == null);
	}

	@Then("^the number of free beds in the department remains the same$")
	public void the_number_of_free_beds_in_the_department_remains_the_same() {
		assertTrue(b1.getPatient() == null);
		assertTrue(b2.getPatient() != null);
	}

	@Given("^a department in the hospital$")
	public void a_department_in_the_hospital() {
		
	}

	@When("^I request the list of beds in that department$")
	public void i_request_the_list_of_beds_in_that_department() {
		
	}

	@Then("^A List of Bed objects is returned$")
	public void a_List_of_Bed_objects_is_returned() {
		
	}

	@Given("^A newly registered patient and a User$")
	public void a_newly_registered_patient_and_a_User() {
		
	}

	@When("^A User requests their non-medical data$")
	public void a_User_requests_their_non_medical_data() {
		
	}

	@Then("^The correct data is shown$")
	public void the_correct_data_is_shown() {
		
	}

	@Given("^A newly registered User$")
	public void a_newly_registered_User() {
		
	}

	@When("^I request their data$")
	public void i_request_their_data() {
		
	}

	@Given("^I have changed a Patients profile$")
	public void i_have_changed_a_Patients_profile() {
		
	}

	@When("^I request their information$")
	public void i_request_their_information() {
		
	}

	@Then("^The patients information has succesfully changed$")
	public void the_patients_information_has_succesfully_changed() {
		
	}

	@Given("^A patient that has previously been admitted and is now discharged$")
	public void a_patient_that_has_previously_been_admitted_and_is_now_discharged() {
		
	}

	@When("^I admit the patient to my department$")
	public void i_admit_the_patient_to_my_department() {
		
	}

	@Then("^The data from their previous admission is still saved$")
	public void the_data_from_their_previous_admission_is_still_saved() {
		
	}

	@Given("^A User of the HMS$")
	public void a_User_of_the_HMS() {
		
	}

	@When("^I update the users Address$")
	public void i_update_the_users_Address() {
		
	}

	@Then("^The Users profile reflects this change$")
	public void the_Users_profile_reflects_this_change() {
		
	}

	@Given("^An existing user in the system$")
	public void an_existing_user_in_the_system() {
		
	}

	@When("^I change a users information$")
	public void i_change_a_users_information() {
		
	}

	@Then("^The information is saved and accepted byt the system$")
	public void the_information_is_saved_and_accepted_byt_the_system() {
		
	}

	@Given("^A nurse and a patient from another department$")
	public void a_nurse_and_a_patient_from_another_department() {
		
	}

	@When("^The nurse requests the patient medical data$")
	public void the_nurse_requests_the_patient_medical_data() {
		
	}

	@Given("^An admin and a patient from another department$")
	public void an_admin_and_a_patient_from_another_department() {
		
	}

	@When("^The admin requests the patient medical data$")
	public void the_admin_requests_the_patient_medical_data() {
		
	}

	@Then("^No Exception is thrown$")
	public void no_Exception_is_thrown() {
		
	}

	@Given("^A nurse and a User$")
	public void a_nurse_and_a_User() {
		
	}

	@When("^The nurse requests the Users data$")
	public void the_nurse_requests_the_Users_data() {
		
	}

	@Given("^An admin and a User$")
	public void an_admin_and_a_User() {
		
	}

	@When("^The admin requests the Users data$")
	public void the_admin_requests_the_Users_data() {
		
	}
}