package daveayan.gherkinsalad.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.actions.Clickable;

public class AmazonGoButton extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		assert_that_the_number_of_element_locators_is(2);
		WebElement button_div = fetch_element_assert_not_null(0);
		if(! button_div.isEnabled()) return false;
		WebElement input_element = button_div.findElement(get_by_at_id(1));
		return input_element.isEnabled();
	}

	public void click_if_enabled() {
		assert_that_the_number_of_element_locators_is(2);
		if(isEnabled()) {
			WebElement button_div = fetch_element_assert_not_null(0);
			WebElement input_element = button_div.findElement(get_by_at_id(1));
			input_element.click();
		}
	}
}