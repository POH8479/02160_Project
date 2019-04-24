import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.Hashtable;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hospitalmanagementsystem.*;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.*;
import java.time.format.DateTimeFormatter;

public class StepDefinition {
	
	User anyUser;
	Patient newPatient;
	Management man;
	String statistic;
	Hashtable<String,String> relevantInfo;

	@Given("^Any user of the program and a patient that has not been admitted$")
	public void any_user_of_the_program_and_a_patient_that_has_not_been_admitted() {
	    // create the a user
		anyUser = new User("John Doe", "123 Main St Anytown, Denmark", "+4512345678");
	}

	@When("^They enter a new patient's non-medical data$")
	public void they_enter_a_new_patient_s_non_medical_data() {
	    // create the patient
		newPatient = new Patient("name", "surname", LocalDate.of(2000, 1,1), "address", "phoneNo");
	}

	@Then("^The data is saved in the system and a confirmation is displayed to the user$")
	public void the_data_is_saved_in_the_system_and_a_confirmation_is_displayed_to_the_user() {
	    // the patient is saved if the surname is retrieved
		assertEquals("surname",newPatient.getPatientInfo().get("Last Name"));
	}

	@Given("^A user and a department$")
	public void a_user_and_a_department() {
	    // create a user and a department
		anyUser = new User("Managment User", "123 Main St Anytown, Denmark", "+4512345678");
		man = Management.getInstance();
	}

	@When("^I choose a statistic to see$")
	public void i_choose_a_statistic_to_see() {
	    // chose to see a users unique user ID
		statistic = anyUser.getUserInfo().get("User ID");
	}

	@Then("^I want to see what this statistic is$")
	public void i_want_to_see_what_this_statistic_is() {
	    // statistic should not be null
	    assertFalse(statistic.equals(null));
	}

	@Given("^patient to register$")
	public void patient_to_register() {
	    // reset the newpatient
		newPatient = new Patient(null, null, null, null, null);
	}

	@Given("^relevant info \\(name, surname, birth-date, home address, phone number, tribe, alive/dead, etc\\.\\.\\)$")
	public void relevant_info_name_surname_birth_date_home_address_phone_number_tribe_alive_dead_etc() {
	    // Store the relevant information in the relevantInfo Hashtable
		relevantInfo.put("First Name", "Jeff");
		relevantInfo.put("Last Name", "Bezos");
		relevantInfo.put("Birthday", "1964-01-12");
		relevantInfo.put("Address", "1246 Big St Seattle, USA");
		relevantInfo.put("Phone Number", "+12069220880");
	}

	@When("^information is entered$")
	public void information_is_entered() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^patient is saved$")
	public void patient_is_saved() {
		// The saved name is the same as the entered name and the data has been saved
		assertEquals(relevantInfo.get("First Name"),newPatient.getPatientInfo().get("First Name"));
	}

	@Then("^incremential patient number is generated$")
	public void incremential_patient_number_is_generated() {
		// Patient ID should not be null
	    assertFalse(newPatient.getPatientInfo().get("Patient ID").equals(null));
	}

	@Given("^I want to check available beds$")
	public void i_want_to_check_available_beds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^total amount of beds$")
	public void total_amount_of_beds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^beds are in use$")
	public void beds_are_in_use() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click on facility under specific department\\(s\\)$")
	public void i_click_on_facility_under_specific_department_s() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I choose to see available beds$")
	public void i_choose_to_see_available_beds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I shoose to see total amount of beds$")
	public void i_shoose_to_see_total_amount_of_beds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I choose to see beds that are in occupied$")
	public void i_choose_to_see_beds_that_are_in_occupied() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I have a list of available beds$")
	public void i_have_a_list_of_available_beds() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I have a list of occupied beds with patients' <name>, <patient number>$")
	public void i_have_a_list_of_occupied_beds_with_patients_name_patient_number() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I want to write a step with department(\\d+)$")
	public void i_want_to_write_a_step_with_department(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I check for the <beds> in step$")
	public void i_check_for_the_beds_in_step() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I verify the <status> in step$")
	public void i_verify_the_status_in_step() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A new patient comes for treatment\\(s\\)$")
	public void a_new_patient_comes_for_treatment_s() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a new staff is employed$")
	public void a_new_staff_is_employed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I want to create profile\\(s\\)$")
	public void i_want_to_create_profile_s() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The system pops dialog window for inputing relevant information$")
	public void the_system_pops_dialog_window_for_inputing_relevant_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^sytem save the profile in data base after typing and confirmation by the user$")
	public void sytem_save_the_profile_in_data_base_after_typing_and_confirmation_by_the_user() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A user or patient has changed his personal info$")
	public void a_user_or_patient_has_changed_his_personal_info() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A patient has received a new treatment$")
	public void a_patient_has_received_a_new_treatment() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A patient is being transfered to another department$")
	public void a_patient_is_being_transfered_to_another_department() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I open the profile, then make the change$")
	public void i_open_the_profile_then_make_the_change() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I confirm the changes$")
	public void i_confirm_the_changes() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^save to the database$")
	public void save_to_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a travel card with a balance of (\\d+)$")
	public void a_travel_card_with_a_balance_of(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a check in status that is false$")
	public void a_check_in_status_that_is_false() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a check in automaton a \"([^\"]*)\"$")
	public void a_check_in_automaton_a(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I check in$")
	public void i_check_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The automaton displays a message that the card is checked in$")
	public void the_automaton_displays_a_message_that_the_card_is_checked_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I want to write a step with name(\\d+)$")
	public void i_want_to_write_a_step_with_name(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I check for the (\\d+) in step$")
	public void i_check_for_the_in_step(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I verify the success in step$")
	public void i_verify_the_success_in_step() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I verify the Fail in step$")
	public void i_verify_the_Fail_in_step() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A user that has entered data into the system$")
	public void a_user_that_has_entered_data_into_the_system() throws Throwable {
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

	@Then("^The data I entered is still saved$")
	public void the_data_I_entered_is_still_saved() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A patient that has previously been admitted and is now discharged$")
	public void a_patient_that_has_previously_been_admitted_and_is_now_discharged() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I admit the patient to my department$")
	public void i_admit_the_patient_to_my_department() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^The data from their previous admission is still saved$")
	public void the_data_from_their_previous_admission_is_still_saved() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I want to search a patient profile$")
	public void i_want_to_search_a_patient_profile() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click search$")
	public void i_click_search() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^type <name> for search$")
	public void type_name_for_search() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^type<email> for search$")
	public void type_email_for_search() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I will be shown the relevant info such as, <birthday>, <address>, <patient number> etc\\.$")
	public void i_will_be_shown_the_relevant_info_such_as_birthday_address_patient_number_etc() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A new staff is emplyed$")
	public void a_new_staff_is_emplyed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I create a staff account with <name>, <position>, <birthday>, <contact info>$")
	public void i_create_a_staff_account_with_name_position_birthday_contact_info() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^An account is created$")
	public void an_account_is_created() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^saved to database$")
	public void saved_to_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A user$")
	public void a_user() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I log in$")
	public void i_log_in() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am able to access the system$")
	public void i_am_able_to_access_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^An existing user in the system$")
	public void an_existing_user_in_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I interact with the system$")
	public void i_interact_with_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am able to access and change patient and staff information$")
	public void i_am_able_to_access_and_change_patient_and_staff_information() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^A User$")
	public void a_User() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I use the system$")
	public void i_use_the_system() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am not able to see any patients health data$")
	public void i_am_not_able_to_see_any_patients_health_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am not able to see any staff members personal data$")
	public void i_am_not_able_to_see_any_staff_members_personal_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^An admin user$")
	public void an_admin_user() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I can see all patients in all departments health data$")
	public void i_can_see_all_patients_in_all_departments_health_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I can see and change staff members personal data$")
	public void i_can_see_and_change_staff_members_personal_data() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I can see all departments$")
	public void i_can_see_all_departments() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
