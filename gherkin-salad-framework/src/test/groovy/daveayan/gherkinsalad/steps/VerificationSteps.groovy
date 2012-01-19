package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Then;

public class VerificationSteps extends BaseStep{
	@Then("^([^\\.]*) has text ~([^\\.]*)~")
	defhas_text(component_name, text) {
		verify_text_exists(text.split(";"), on_page_element_with_key(component_name, ""));
	}
	@Then("^([^\\.]*) does not have text ~([^\\.]*)~")
	defdoes_not_have_text(component_name, text) {
		verify_text_does_not_exist(text.split(";"), on_page_element_with_key(component_name, ""));
	}
	@Then("^([^\\.]*) on ([^\\.]*) has text ~([^\\.]*)~")
	defhas_text(element_name, component_name, text) {
		verify_text_exists(text.split(";"), on_page_element_with_key(component_name, element_name));
	}
	@Then("^([^\\.]*) on ([^\\.]*) does not have text ~([^\\.]*)~")
	defdoes_not_have_text(element_name, component_name, text) {
		verify_text_does_not_exist(text.split(";"), on_page_element_with_key(component_name, element_name));
	}
	@Then("^([^\\.]*) has enabled elements ~([^\\.]*)~")
	defthese_elements_are_enabled(component_name, element_names) {
		has_enabled_elements(component_name, element_names.split(";"));
	}
	@Then("^([^\\.]*) has disabled elements ~([^\\.]*)~")
	defthese_elements_are_disabled(component_name, element_names) {
		has_disabled_elements(component_name, element_names.split(";"));
	}
	@Then("^([^\\.]*) does not have elements ~([^\\.]*)~")
	defthese_elements_are_not_displayed(component_name, element_names) {
		elements_dont_exist(component_name, element_names.split(";"));
	}
}