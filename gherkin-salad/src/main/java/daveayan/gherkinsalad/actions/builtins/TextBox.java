package daveayan.gherkinsalad.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.actions.TextEnterable;

public class TextBox extends BaseBrowserElement implements TextEnterable {
	public boolean isEnabled() {
		assert_that_the_number_of_element_locators_is(1);
		WebElement element = fetch_element_assert_not_null(0);
		return element.isEnabled();
	}

	public void enter_text_if_enabled(String text) {
		assert_that_the_number_of_element_locators_is(1);
		WebElement element = fetch_element_assert_not_null(0);
		if(this.isEnabled()) {
			element.clear();
			element.sendKeys(text);
		}
	}

	public void append_text_if_enabled(String text) {
		assert_that_the_number_of_element_locators_is(1);
		WebElement element = fetch_element_assert_not_null(0);
		if(this.isEnabled()) {
			element.sendKeys(text);
		}
	}
}