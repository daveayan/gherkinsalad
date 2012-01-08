package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.gherkinsalad.steps.BaseStep;

public class CommonSteps extends BaseStep {
	// ******************************************************************************************************************************************************
	// load up steps
	// ******************************************************************************************************************************************************
	@Given("^User launched the ([^\\.]*) browser$")
	public void launch_browser_with_page_structure(String browser_name) {
		launch_browser(browser_name);
	}
	@Given("^User uses the data source file ([^\\.]*) with driver ([^\\.]*)$")
	public void use_data_source(String data_source_file_name, String data_source_driver) {
		feature_data_source = load_data_source(data_source_file_name, data_source_driver);
	}
	@Given("^User visited website (.*)$")
	public void goto_website(String url) {
		goto_url(url);
	}
	@Given("^Data Management driver is (.*)$")
	public void data_management_driver(String dmd) {
		data_management_driver = dmd;
	}
	@Given("^Data Management file is (.*)$")
	public void data_management_file(String dmf) {
		data_management_file = dmf;
	}
	@Given("^Data is loaded$")
	public void load_data_management_module() {
		feature_data_source = load_data_source(data_management_file, data_management_driver);
	}
	@Given("^User is a ([^\\.]*)$")
	public void assume_role(String role_name) {
		current_role = role_name;
	}
	@Given("^User is testing feature ([^\\.]*)$")
	public void testing_feature(String feature_name) {
		feature_under_test = feature_name;
	}
	@Then("^User closes the browser window$")
	public void close_browser_window() {
		wait_for_page_to_load();
		close_browser();
	}
	
	// ******************************************************************************************************************************************************
	// TEXT STEPS
	// ******************************************************************************************************************************************************
	@Given("^User entered text ([^\\.]*) in text box ([^\\.]*)$")
	@When("^User enters text ([^\\.]*) in text box ([^\\.]*)$")
	public void enter_text_in_text_box(String text, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(text, on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User entered text ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	@When("^User enters text ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	public void enter_text_in_text_box(String text, String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(text, on_page_element_with_key(current_role, component_name, element_name));
	}
	@Given("^User clicked ([^\\.]*)$")
	@When("^User clicks ([^\\.]*)$")
	public void click(String element_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User clicked ([^\\.]*) on ([^\\.]*)$")
	@When("^User clicks ([^\\.]*) on ([^\\.]*)$")
	public void click(String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_page_element_with_key(current_role, component_name, element_name));
	}
	@Given("^User checked text ([^\\.]*)$")
	@When("^User checks text ([^\\.]*)$")
	public void check_check_box(String element_name) {
		wait_for_page_to_load();
		click(on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User checked text ([^\\.]*) on ([^\\.]*)$")
	@When("^User checks text ([^\\.]*) on ([^\\.]*)$")
	public void check_check_box(String element_name, String component_name) {
		wait_for_page_to_load();
		click(on_page_element_with_key(current_role, component_name, element_name));
	}
	@Given("^User selected option text ([^\\.]*) in drop down ([^\\.]*)$")
	@When("^User selects option text ([^\\.]*) in drop down ([^\\.]*)$")
	public void select_option(String option, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		select(option, on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User selected radio button text ([^\\.]*)$")
	@When("^User selects radio button text ([^\\.]*)$")
	public void select_radio_button(String element_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User selected radio button text ([^\\.]*) in drop down ([^\\.]*) on ([^\\.]*)$")
	@When("^User selects radio button text ([^\\.]*) in drop down ([^\\.]*) on ([^\\.]*)$")
	public void select_option(String option, String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		select(option, on_page_element_with_key(current_role, component_name, element_name));
	}
	@Then("^([^\\.]*) has text ~([^\\.]*)~$")
	public void has_text(String component_name, String text) {
		wait_for_page_to_load();
		verify_text_exists(text.split(";"), on_page_element_with_key(current_role, component_name, ""));
	}
	@Then("^([^\\.]*) does not have text ~([^\\.]*)~$")
	public void does_not_have_text(String component_name, String text) {
		wait_for_page_to_load();
		verify_text_does_not_exist(text.split(";"), on_page_element_with_key(current_role, component_name, ""));
	}
	@Then("^([^\\.]*) on ([^\\.]*) has text ~([^\\.]*)~$")
	public void has_text(String element_name, String component_name, String text) {
		wait_for_page_to_load();
		verify_text_exists(text.split(";"), on_page_element_with_key(current_role, component_name, element_name));
	}
	@Then("^([^\\.]*) on ([^\\.]*) does not have text ~([^\\.]*)~$")
	public void does_not_have_text(String element_name, String component_name, String text) {
		wait_for_page_to_load();
		verify_text_does_not_exist(text.split(";"), on_page_element_with_key(current_role, component_name, element_name));
	}
	@Then("^([^\\.]*) has enabled elements ~([^\\.]*)~$")
	public void these_elements_are_enabled(String component_name, String element_names) throws InterruptedException {
		wait_for_page_to_load();
		has_enabled_elements(component_name, element_names.split(";"));
	}
	@Then("^([^\\.]*) has disabled elements ~([^\\.]*)~$")
	public void these_elements_are_disabled(String component_name, String element_names) throws InterruptedException {
		wait_for_page_to_load();
		has_disabled_elements(component_name, element_names.split(";"));
	}
	@Then("^([^\\.]*) does not have elements ~([^\\.]*)~$")
	public void these_elements_are_not_displayed(String component_name, String element_names) throws InterruptedException {
		wait_for_page_to_load();
		elements_dont_exist(component_name, element_names.split(";"));
	}
	
	// ******************************************************************************************************************************************************
	// DATA STEPS
	// ******************************************************************************************************************************************************
	@Given("^User entered data ([^\\.]*) in text box ([^\\.]*)$")
	@When("^User enters data ([^\\.]*) in text box ([^\\.]*)$")
	public void enter_data_in_text_box(String symbolic_text, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(data_element_with_key(symbolic_text), on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User entered data ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	@When("^User enters data ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	public void enter_data_in_text_box(String symbolic_text, String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(data_element_with_key(symbolic_text), on_page_element_with_key(current_role, component_name, element_name));
	}
}