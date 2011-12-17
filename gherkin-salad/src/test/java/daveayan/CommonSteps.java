package daveayan;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.actions.Clickable;
import daveayan.actions.TextEnterable;
import daveayan.actions.VerifiesText;

public class CommonSteps extends AbstractSteps {
	protected static final Log logger = LogFactory.getLog(CommonSteps.class);
	
	@Given("^User launched the browser window$")
	public void launch_browser_window() {
		Environment.init();
		Environment.launch();
	}
	
	@Given("^User visited website$")
	@When("^User visits website$")
	public void goto_site() {
		logger.info("visiting site");
		Assert.assertNotNull("WebSite not specified", Environment.urlToUse());
		Environment.driver().get(Environment.urlToUse());
	}
	
	@Given("^User visited website ([^\\.]*)$")
	@When("^User visits website ([^\\.]*)$")
	public void goto_site(String url) {
		System.out.println("In goto_site");
		Assert.assertNotNull("WebSite not specified", url);
		Environment.driver().get(url);
	}
	
	@Given("^User clicked ([^\\.]*)$")
	@When("^User clicks ([^\\.]*)$")
	public void click(String element_name) {
		wait_for_page_to_load();
		Class<?> clickable_class = ComponentObjectRegistry.page_component_class(element_name);
		Clickable component_object = (Clickable) PageFactory.initElements(Environment.driver(), clickable_class);
		component_object.click();
	}
	
	@Given("^User clicked ([^\\.]*) on ([^\\.]*)$")
	@When("^User clicks ([^\\.]*) on ([^\\.]*)$")
	public void click(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		element.click();
	}
	
	@Given("^User selected ([^\\.]*) from ([^\\.]*)$")
	@When("^User selects ([^\\.]*) from ([^\\.]*)$")
	public void select (String option_text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Given("^User selected ([^\\.]*) from ([^\\.]*) on ([^\\.]*)$")
	@When("^User selects ([^\\.]*) from ([^\\.]*) on ([^\\.]*)$")
	public void select (String option_text, String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Given("^User entered ([^\\.]*) in ([^\\.]*)$")
	@When("^User enters ([^\\.]*) in ([^\\.]*)$")
	public void enter_text(String text, String element_name) {
		wait_for_page_to_load();
		Class<?> clickable_class = ComponentObjectRegistry.page_component_class(element_name);
		Assert.assertNotNull("clickable_class is null", clickable_class);
		TextEnterable component_object = (TextEnterable) PageFactory.initElements(Environment.driver(), clickable_class);
		Assert.assertNotNull("component_object is null", component_object);
		component_object.enter_text(text);
	}
	
	@Given("^User entered ([^\\.]*) in ([^\\.]*) on ([^\\.]*)$")
	@When("^User enters ([^\\.]*) in ([^\\.]*) on ([^\\.]*)$")
	public void enter_text(String text, String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		element.clear();
		element.sendKeys(text);
	}
	
//	@Given("^User (unchecked|checked) ([^\\.]*)$")
//	@When("^(I check|I uncheck|You check|You uncheck|User checks|User unchecks) ([^\\.]*)$")
	public void check(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		element.click();
		Assert.assertFalse("Method not implemented", true);
	}
	
//	@Given("^User (unchecked|checked) ([^\\.]*) on ([^\\.]*)$")
//	@When("^(I check|I uncheck|You check|You uncheck|User checks|User unchecks) ([^\\.]*) on ([^\\.]*)$")
	public void check(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		element.click();
		Assert.assertFalse("Method not implemented", true);
	}
	
	@Then("^User should see the text ([^\\.]*)$")
	public void should_see_text(String text) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		assert_text_exists(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should see the text ([^\\.]*) in ([^\\.]*)$")
	public void should_see_text_on_component(String text, String component_name) {
		wait_for_page_to_load();
		Class<?> clazz = ComponentObjectRegistry.page_component_class(component_name);
		Assert.assertNotNull("clazz is null", clazz);
		VerifiesText object = (VerifiesText) PageFactory.initElements(Environment.driver(), clazz);
		Assert.assertNotNull("component_object is null", object);
		object.hasText(text);
	}
	
	@Then("^User should see the text ([^\\.]*) in element ([^\\.]*)$")
	public void should_see_text_in_element(String text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_text_exists(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should not see the text ([^\\.]*)$")
	public void should_not_see_text(String text) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		assert_text_does_not_exist(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should not see the text ([^\\.]*) on ([^\\.]*)$")
	public void should_not_see_text_on_component(String text, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component, component_name);
		assert_text_does_not_exist(component, text, component_name);
	}
	
	@Then("^User should not see the text ([^\\.]*) in ([^\\.]*)$")
	public void should_not_see_text_in_element(String text, String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_text_does_not_exist(component, text, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should see the enabled element ([^\\.]*)$")
	public void should_see_enabled_element(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_element_is_enabled(element, element_name, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should see the enabled element ([^\\.]*) on ([^\\.]*)$")
	public void should_see_enabled_element(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		assert_element_is_enabled(element, element_name, component_name);
	}
	
	@Then("^User should see the disabled element ([^\\.]*)$")
	public void should_see_disabled_element(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
		assert_element_is_disabled(element, element_name, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should see the disabled element ([^\\.]*) on ([^\\.]*)$")
	public void should_see_disabled_element(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
		assert_element_is_disabled(element, element_name, component_name);
	}
	
	@Then("^User should not see element ([^\\.]*)$")
	public void should_not_see_element(String element_name) {
		BaseComponentObject component = component_object(IMPLICIT_COMPONENT);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, IMPLICIT_COMPONENT);
	}
	
	@Then("^User should not see element ([^\\.]*) on ([^\\.]*)$")
	public void should_not_see_element(String element_name, String component_name) {
		BaseComponentObject component = component_object(component_name);
		assert_component_not_null(component);
		WebElement element = get_element_on_component(element_name, component);
		assert_element_not_null(element, element_name, component_name);
	}
	
	@Then("^User closes the browser window$")
	public void close_browser() {
		super.close_browser();
	}
}