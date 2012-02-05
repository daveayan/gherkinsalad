package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;

public class DataEntrySteps extends BaseStep {
	@Given("^(?:User|user|I|You|you) entered text ([^\\.]*) in text box ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) enters text ([^\\.]*) in text box ([^\\.]*)$")
	public void enter_text_in_text_box(String text, String element_name) throws InterruptedException {
		enter(text, on_element(element_name));
	}
	@Given("^(?:User|user|I|You|you) entered text ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) enters text ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	public void enter_text_in_text_box(String text, String element_name, String component_name) throws InterruptedException {
		enter(text, in_element(element_name).on_component(component_name));
	}
	@Given("^(?:User|user|I|You|you) entered data ([^\\.]*) in text box ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) enters data ([^\\.]*) in text box ([^\\.]*)$")
	public void enter_data_in_text_box(String symbolic_text, String element_name) throws InterruptedException {
		enter(data_for(symbolic_text), on_element(element_name));
	}
	@Given("^(?:User|user|I|You|you) entered data ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	@When("^(?:User|user|I|You|you) enters data ([^\\.]*) in text box ([^\\.]*) on ([^\\.]*)$")
	public void enter_data_in_text_box(String symbolic_text, String element_name, String component_name) throws InterruptedException {
		enter(data(symbolic_text), in_element(element_name).on_component(component_name));
	}
}