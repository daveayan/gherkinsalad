package daveayan.gherkinsalad.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.actions.Clickable;

public class Link extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		assert_that_the_number_of_element_locators_is(1);
		WebElement element = fetch_element_assert_not_null(0);
		return element.isEnabled();
	}

	public void click_if_enabled() {
		assert_that_the_number_of_element_locators_is(1);
		WebElement element = fetch_element_assert_not_null(0);
		if(this.isEnabled()) {
			element.click();
		}
	}
}