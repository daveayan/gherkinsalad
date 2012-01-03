package daveayan.gherkinsalad.browser.actions.builtins;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.NullWebElement;
import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.gherkinsalad.browser.actions.BrowserElement;

public abstract class BaseBrowserElement implements BrowserElement {
	protected PageElementKey page_element_key;

	protected List<By> element_locators;

	protected WebDriver driver;

	public void has_text(String[] expected_texts) {
		if (expected_texts != null) {
			List<String> expected_text_not_present = new ArrayList<String>();
			WebElement element = fetch_element(0);
			for(String expected_text: expected_texts) {
				if(! element.getText().contains(expected_text.trim())) {
					expected_text_not_present.add(expected_text.trim());
				}
			}
			if(! expected_text_not_present.isEmpty()) {
				throw new AssertionError("Component '" + page_element_key + "' does not have expected text(s) '" + expected_text_not_present + "'");
			}
		}
	}
	
	public void does_not_have_text(String[] unexpected_texts) {
		if (unexpected_texts != null) {
			List<String> unexpected_text_present = new ArrayList<String>();
			WebElement element = fetch_element(0);
			for(String expected_text: unexpected_texts) {
				if(element.getText().contains(expected_text.trim())) {
					unexpected_text_present.add(expected_text.trim());
				}
			}
			if(! unexpected_text_present.isEmpty()) {
				throw new AssertionError("Component '" + page_element_key + "' has unexpected text(s) '" + unexpected_text_present + "'");
			}
		}
	}

	public By get_by_at_id(int id) {
		return element_locators.get(id);
	}

	public WebElement fetch_element(int id) {
		if (id >= element_locators.size()) {
			return NullWebElement.newInstance(null, page_element_key);
		}
		By selector = element_locators.get(id);
		WebElement element = driver.findElement(selector);
		if (element == null) {
			element = NullWebElement.newInstance(selector, page_element_key);
		}
		return element;
	}

	public void assert_that_the_number_of_element_locators_is(int expected_number) {
		assert_that_the_number_of_element_locators_are(expected_number);
	}

	public void assert_that_the_number_of_element_locators_are(int expected_number) {
		if (element_locators == null) {
			throw new AssertionError("element_locators is null for '" + this.getClass().getName() + "', page element key '"
					+ page_element_key + "'");
		}
		if (element_locators.size() != expected_number) {
			throw new AssertionError("Size of element_locators is '" + element_locators.size() + "', expected '"
					+ expected_number + "' for '" + this.getClass().getName() + "', page element key '" + page_element_key + "'");
		}
	}

	public boolean isDisabled() {
		return !isEnabled();
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
}