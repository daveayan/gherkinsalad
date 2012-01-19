package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;

public class ClickSteps extends BaseStep {
	@Given("^(?:User|user|I|You|you) clicked ([^\\.]*)")
	@When("^(?:User|user|I|You|you) clicks ([^\\.]*)")
	def click(element_name) throws InterruptedException {
		click(on_page_element_with_key(element_name));
	}
	@Given("^(?:User|user|I|You|you) clicked ([^\\.]*) on ([^\\.]*)")
	@When("^(?:User|user|I|You|you) clicks ([^\\.]*) on ([^\\.]*)")
	def click(element_name, component_name) {
		click(on_page_element_with_key(component_name, element_name));
	}
	@Given("^(?:User|user|I|You|you) checked text ([^\\.]*)")
	@When("^(?:User|user|I|You|you) checks text ([^\\.]*)")
	def check_check_box(element_name) {
		click(on_page_element_with_key(element_name));
	}
	@Given("^(?:User|user|I|You|you) checked text ([^\\.]*) on ([^\\.]*)")
	@When("^(?:User|user|I|You|you) checks text ([^\\.]*) on ([^\\.]*)")
	def check_check_box(element_name, component_name) {
		click(on_page_element_with_key(component_name, element_name));
	}
	@Given("^(?:User|user|I|You|you) selected radio button text ([^\\.]*)")
	@When("^(?:User|user|I|You|you) selects radio button text ([^\\.]*)")
	def select_radio_button(element_name) {
		click(on_page_element_with_key(element_name));
	}
}