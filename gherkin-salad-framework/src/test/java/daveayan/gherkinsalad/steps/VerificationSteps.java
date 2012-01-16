package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Then;

public class VerificationSteps extends BaseStep{
	@Then("^([^\\.]*) has text ~([^\\.]*)~$")
	public void has_text(String component_name, String text) {
		verify_text_exists(text.split(";"), on_page_element_with_key(current_role, component_name, ""));
	}
	@Then("^([^\\.]*) does not have text ~([^\\.]*)~$")
	public void does_not_have_text(String component_name, String text) {
		verify_text_does_not_exist(text.split(";"), on_page_element_with_key(current_role, component_name, ""));
	}
	@Then("^([^\\.]*) on ([^\\.]*) has text ~([^\\.]*)~$")
	public void has_text(String element_name, String component_name, String text) {
		verify_text_exists(text.split(";"), on_page_element_with_key(current_role, component_name, element_name));
	}
	@Then("^([^\\.]*) on ([^\\.]*) does not have text ~([^\\.]*)~$")
	public void does_not_have_text(String element_name, String component_name, String text) {
		verify_text_does_not_exist(text.split(";"), on_page_element_with_key(current_role, component_name, element_name));
	}
	@Then("^([^\\.]*) has enabled elements ~([^\\.]*)~$")
	public void these_elements_are_enabled(String component_name, String element_names) throws InterruptedException {
		has_enabled_elements(component_name, element_names.split(";"));
	}
	@Then("^([^\\.]*) has disabled elements ~([^\\.]*)~$")
	public void these_elements_are_disabled(String component_name, String element_names) throws InterruptedException {
		has_disabled_elements(component_name, element_names.split(";"));
	}
	@Then("^([^\\.]*) does not have elements ~([^\\.]*)~$")
	public void these_elements_are_not_displayed(String component_name, String element_names) throws InterruptedException {
		elements_dont_exist(component_name, element_names.split(";"));
	}
}