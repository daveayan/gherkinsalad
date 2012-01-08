package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;

public class ClickSteps extends BaseStep {
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
	@Given("^User selected radio button text ([^\\.]*)$")
	@When("^User selects radio button text ([^\\.]*)$")
	public void select_radio_button(String element_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_page_element_with_key(current_role, "", element_name));
	}
}