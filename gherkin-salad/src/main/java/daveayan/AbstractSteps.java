package daveayan;

import junit.framework.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractSteps {
	public static final String IMPLICIT_COMPONENT = "Implicit Component";
	protected WebDriver driver;
	
	public BaseComponentObject component_object(String component_name) {
		Class<?> component_object_clazz = ComponentObjectRegistry.page_component_class(component_name);
		Object component_object = PageFactory.initElements(driver, component_object_clazz);
		Assert.assertTrue("Component Object created is not of type BaseComponentObject, component name is '" + component_name + "'", component_object instanceof BaseComponentObject);
		return (BaseComponentObject) component_object;
	}
	
	public WebElement get_element_on_component(String element_name, BaseComponentObject component) {
		return component.element_for_name(element_name);
	}
	
	public void assert_element_not_null(WebElement element, String element_name, String component_name) {
		Assert.assertNotNull("Expected '" + element_name + "' on '" + component_name + "' to be not null", element);
	}
	
	public void assert_element_null(WebElement element, String element_name, String component_name) {
		Assert.assertNull("Expected '" + element_name + "' on '" + component_name + "' to be not null", element);
	}
	
	public void assert_component_not_null(BaseComponentObject component) {
		Assert.assertNotNull("Expected default component to be not null", component);
	}
	
	public void assert_component_not_null(BaseComponentObject component, String component_name) {
		Assert.assertNotNull("Expected '" + component_name + "' to be not null", component);
	}
	
	public void assert_text_exists(BaseComponentObject component, String expected_sub_text, String component_name) {
		Assert.assertNotNull("Expected component root on '" + component_name + "' to be not null", component.component_root());
		String actual_text = component.component_root().getText();
		Assert.assertTrue("Expected text '" + expected_sub_text + "' on '" + component_name + "', not found", actual_text.contains(expected_sub_text));
	}
	
	public void assert_text_does_not_exist(BaseComponentObject component, String expected_sub_text, String component_name) {
		Assert.assertNotNull("Expected component root on '" + component_name + "' to be not null", component.component_root());
		String actual_text = component.component_root().getText();
		Assert.assertFalse("Expected text '" + expected_sub_text + "' on '" + component_name + "' to not exist, found anyways", actual_text.contains(expected_sub_text));
	}
	
	public void assert_element_is_enabled(WebElement element, String element_name, String component_name) {
		Assert.assertTrue("Expected element '" + element_name + "' on component '" + component_name + "' to be enabled", element.isEnabled());
	}
	
	public void assert_element_is_disabled(WebElement element, String element_name, String component_name) {
		Assert.assertFalse("Expected element '" + element_name + "' on component '" + component_name + "' to be disabled", element.isEnabled());
	}
	
	public void close_browser() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}
}