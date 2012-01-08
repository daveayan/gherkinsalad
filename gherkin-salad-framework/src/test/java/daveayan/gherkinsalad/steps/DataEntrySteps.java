package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;

public class DataEntrySteps extends BaseStep {
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