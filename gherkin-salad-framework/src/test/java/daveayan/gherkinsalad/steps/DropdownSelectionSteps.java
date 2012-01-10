package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;

public class DropdownSelectionSteps extends BaseStep {
	@Given("^User selected option text ([^\\.]*) in drop down ([^\\.]*)$")
	@When("^User selects option text ([^\\.]*) in drop down ([^\\.]*)$")
	public void select_option(String option, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		select(option, on_page_element_with_key(current_role, "", element_name));
	}
	@Given("^User selected radio button text ([^\\.]*) in drop down ([^\\.]*) on ([^\\.]*)$")
	@When("^User selects radio button text ([^\\.]*) in drop down ([^\\.]*) on ([^\\.]*)$")
	public void select_option(String option, String element_name, String component_name) throws InterruptedException {
		wait_for_page_to_load();
		select(option, on_page_element_with_key(current_role, component_name, element_name));
	}
}