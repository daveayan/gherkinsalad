package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;

public class ClickSteps extends BaseStep {
	@Given("^(?:User|user|I|You|you) clicked ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) clicks ([^\\.]*)$")
	public void click(String element_name) throws InterruptedException {
		click(on_element(element_name));
	}
	@Given("^(?:User|user|I|You|you) clicked ([^\\.]*) on ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) clicks ([^\\.]*) on ([^\\.]*)$")
	public void click(String element_name, String component_name) throws InterruptedException {
		click(element(element_name).on_component(component_name));
//	click(on_page_element_with_key(component_name, element_name));
	}
	@Given("^(?:User|user|I|You|you) checked text ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) checks text ([^\\.]*)$")
	public void check_check_box(String element_name) {
		click(on_element(element_name));
	}
	@Given("^(?:User|user|I|You|you) checked text ([^\\.]*) on ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) checks text ([^\\.]*) on ([^\\.]*)$")
	public void check_check_box(String element_name, String component_name) {
		click(element(element_name).on_component(component_name));
//	click(on_page_element_with_key(component_name, element_name));
	}
	@Given("^(?:User|user|I|You|you) selected radio button text ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) selects radio button text ([^\\.]*)$")
	public void select_radio_button(String element_name) throws InterruptedException {
		click(on_element(element_name));
	}
}