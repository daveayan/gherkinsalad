package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.gherkinsalad.steps.BaseStep;

public class CommonSteps extends BaseStep {
	@Given("^User launched the ([^\\.]*) browser with page structure file (.*)$")
	public void launch_browser_with_page_structure(String browser_name, String page_structure_file_name) {
		launch_browser_with(browser_name, page_structure_file_name);
	}
	
	@Given("^User visited website (.*)$")
	public void goto_website(String url) {
		goto_url(url);
	}
	
	@Given("^User entered ([^\\.]*) in text box ([^\\.]*)$")
	@When("^User enters ([^\\.]*) in text box ([^\\.]*)$")
	public void enter_in_text_box(String text, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(text, on_element_with_key("", "", element_name));
	}
	
	@Given("^User entered ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	@When("^User enters ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	public void enter_in_text_box(String text, String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(text, on_element_with_key("", component_name, element_name));
	}
	
	@Given("^User clicked ([^\\.]*)$")
	@When("^User clicks ([^\\.]*)$")
	public void click(String element_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_element_with_key("", "", element_name));
	}
	
	@Given("^User clicked ([^\\.]*) on ([^\\.]*)$")
	@When("^User clicks ([^\\.]*) on ([^\\.]*)$")
	public void click(String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_element_with_key("", component_name, element_name));
	}
	
	@Given("^User checked ([^\\.]*)$")
	@When("^User checks ([^\\.]*)$")
	public void check_check_box(String element_name) {
		wait_for_page_to_load();
		click(on_element_with_key("", "", element_name));
	}
	
	@Given("^User checked ([^\\.]*) on ([^\\.]*)$")
	@When("^User checks ([^\\.]*) on ([^\\.]*)$")
	public void check_check_box(String element_name, String component_name) {
		wait_for_page_to_load();
		click(on_element_with_key("", component_name, element_name));
	}
	
	@Given("^User selected option ([^\\.]*) in drop down ([^\\.]*)$")
	@When("^User selects option ([^\\.]*) in drop down ([^\\.]*)$")
	public void select_option(String option, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		select(option, on_element_with_key("", "", element_name));
	}
	
	@Given("^User selected radio button ([^\\.]*)$")
	@When("^User selects radio button ([^\\.]*)$")
	public void select_radio_button(String element_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_element_with_key("", "", element_name));
	}
	
	@Given("^User selected radio button ([^\\.]*) in drop down ([^\\.]*) on ([^\\.]*)$")
	@When("^User selects radio button ([^\\.]*) in drop down ([^\\.]*) on ([^\\.]*)$")
	public void select_option(String option, String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		select(option, on_element_with_key("", component_name, element_name));
	}
	
	@Then("^([^\\.]*) has text ~([^\\.]*)~$")
	public void has_text(String component_name, String text) {
		wait_for_page_to_load();
		verify_text_exists(text.split(";"), on_element_with_key("", component_name, ""));
	}
	
	@Then("^([^\\.]*) does not have text ~([^\\.]*)~$")
	public void does_not_have_text(String component_name, String text) {
		wait_for_page_to_load();
		verify_text_does_not_exist(text.split(";"), on_element_with_key("", component_name, ""));
	}
	
	@Then("^([^\\.]*) on ([^\\.]*) has text ~([^\\.]*)~$")
	public void has_text(String element_name, String component_name, String text) {
		wait_for_page_to_load();
		verify_text_exists(text.split(";"), on_element_with_key("", component_name, element_name));
	}
	
	@Then("^([^\\.]*) on ([^\\.]*) does not have text ~([^\\.]*)~$")
	public void does_not_have_text(String element_name, String component_name, String text) {
		wait_for_page_to_load();
		verify_text_does_not_exist(text.split(";"), on_element_with_key("", component_name, element_name));
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
	
	@Then("^User closes the browser window$")
	public void close_browser_window() {
		wait_for_page_to_load();
		close_browser();
	}
}