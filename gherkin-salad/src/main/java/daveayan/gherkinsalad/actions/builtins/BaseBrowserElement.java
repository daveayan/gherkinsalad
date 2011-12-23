package daveayan.gherkinsalad.actions.builtins;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.PageElementKey;
import daveayan.gherkinsalad.actions.BrowserElement;

public abstract class BaseBrowserElement implements BrowserElement {
	protected PageElementKey page_element_key;
	protected List<By> element_locators;
	protected WebDriver driver;
	
	public By get_by_at_id(int id) {
		return element_locators.get(id);
	}
	
	public WebElement fetch_element_assert_not_null(int id) {
		By selector = element_locators.get(id);
		WebElement element = driver.findElement(selector);
		if(element == null) { 
			throw new AssertionError("Element fetched with the selector '" + selector + "' is null. PageElementKey is '" + page_element_key + "'");
		}
		return element;
	}
	
	public void assert_that_the_number_of_element_locators_is(int expected_number) {
		assert_that_the_number_of_element_locators_are(expected_number);
	}
	
	public void assert_that_the_number_of_element_locators_are(int expected_number) {
		if(element_locators == null) {
			throw new AssertionError("element_locators is null for '" + this.getClass().getName() + "', page element key '" + page_element_key + "'");
		}
		if(element_locators.size() != expected_number) {
			throw new AssertionError("Size of element_locators is '" + element_locators.size() + "', expected '" + expected_number + "' for '" + this.getClass().getName() + "', page element key '" + page_element_key + "'");
		}
	}
	
	public boolean isDisabled() {
		return ! isEnabled();
	}
	public void page_element_key_is(PageElementKey pek) {
		this.page_element_key = pek;
	}
	public void element_locators_are(List<By> element_locators) {
		this.element_locators = element_locators;
	}
	public void driver_is(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isNull() {
		return false;
	}
}