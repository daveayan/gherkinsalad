package daveayan;

import junit.framework.Assert;

import org.openqa.selenium.WebElement;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

public class CommonSteps extends AbstractSteps {
	@Given("^[I | You | User] launched the browser window$")
	public void launch_browser_window() {
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Given("^[I | You | User] clicked ([^\\.]*)$")
	@When("^[I click | You click | User clicks] ([^\\.]*)$")
	public void click(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		element.click();
	}
	
	@Given("^[I | You | User] clicked ([^\\.]*) on ([^\\.]*)$")
	@When("^[I click | You click | User clicks] ([^\\.]*) on ([^\\.]*)$")
	public void click(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		element.click();
	}
	
	@Given("^[I | You | User] selected ([^\\.]*) from ([^\\.]*)$")
	@When("^[I select | You select | User selects] ([^\\.]*) from ([^\\.]*)$")
	public void select (String option_text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Given("^[I | You | User] selected ([^\\.]*) from ([^\\.]*) on ([^\\.]*)$")
	@When("^[I select | You select | User selects] ([^\\.]*) from ([^\\.]*) on ([^\\.]*)$")
	public void select (String option_text, String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Given("^[I | You | User] [entered | filled in] ([^\\.]*) in ([^\\.]*)$")
	@When("^[I enter | I fill in | You enter | You fill in | User enters | User fills in] ([^\\.]*) in ([^\\.]*)$")
	public void enter_text(String text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		element.clear();
		element.sendKeys(text);
	}
	
	@Given("^[I | You | User] [entered | filled in] ([^\\.]*) in ([^\\.]*) on ([^\\.]*)$")
	@When("^[I enter | I fill in | You enter | You fill in | User enters | User fills in] ([^\\.]*) in ([^\\.]*) on ([^\\.]*)$")
	public void enter_text(String text, String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		element.clear();
		element.sendKeys(text);
	}
	
	@Given("^[I | You | User] [unchecked | checked] ([^\\.]*)$")
	@When("^[I check | I uncheck | You check | You uncheck | User checks | User unchecks] ([^\\.]*)$")
	public void check(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		element.click();
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Given("^[I | You | User] [unchecked | checked] ([^\\.]*) on ([^\\.]*)$")
	@When("^[I check | I uncheck | You check | You uncheck | User checks | User unchecks] ([^\\.]*) on ([^\\.]*)$")
	public void check(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		element.click();
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Then("^[I | You | User] should see the text ([^\\.]*)$")
	public void should_see_text(String text) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		assert_text_exists(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should see the text ([^\\.]*) on ([^\\.]*)$")
	public void should_see_text_on_component(String text, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component, component_name);
		assert_text_exists(component, text, component_name);
	}
	
	@Then("^[I | You | User] should see the text ([^\\.]*) in ([^\\.]*)$")
	public void should_see_text_in_element(String text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_text_exists(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should not see the text ([^\\.]*)$")
	public void should_not_see_text(String text) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		assert_text_does_not_exist(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should not see the text ([^\\.]*) on ([^\\.]*)$")
	public void should_not_see_text_on_component(String text, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component, component_name);
		assert_text_does_not_exist(component, text, component_name);
	}
	
	@Then("^[I | You | User] should not see the text ([^\\.]*) in ([^\\.]*)$")
	public void should_not_see_text_in_element(String text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_text_does_not_exist(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should see the enabled element ([^\\.]*)$")
	public void should_see_enabled_element(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_element_is_enabled(element, element_name, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should see the enabled element ([^\\.]*) on ([^\\.]*)$")
	public void should_see_enabled_element(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		assert_element_is_enabled(element, element_name, component_name);
	}
	
	@Then("^[I | You | User] should see the disabled element ([^\\.]*)$")
	public void should_see_disabled_element(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_element_is_disabled(element, element_name, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should see the disabled element ([^\\.]*) on ([^\\.]*)$")
	public void should_see_disabled_element(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		assert_element_is_disabled(element, element_name, component_name);
	}
	
	@Then("^[I | You | User] should not see element ([^\\.]*)$")
	public void should_not_see_element(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
	}
	
	@Then("^[I | You | User] should not see element ([^\\.]*) on ([^\\.]*)$")
	public void should_not_see_element(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
	}
	
	@Then("^[I close | You close | User closes] the browser window$")
	public void close_browser() {
		super.close_browser();
	}
}