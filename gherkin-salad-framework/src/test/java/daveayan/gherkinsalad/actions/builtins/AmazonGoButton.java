package daveayan.gherkinsalad.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.Clickable;
import daveayan.gherkinsalad.browser.actions.builtins.BaseBrowserElement;

public class AmazonGoButton extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		assert_that_the_number_of_element_locators_is(2);
		WebElement button_div = fetch_element(0);
		if(! button_div.isEnabled()) return false;
		WebElement input_element = button_div.findElement(get_by_at_id(1));
		return input_element.isEnabled();
	}

	public void click_if_enabled() {
		assert_that_the_number_of_element_locators_is(2);
		if(isEnabled()) {
			WebElement button_div = fetch_element(0);
			WebElement input_element = button_div.findElement(get_by_at_id(1));
			input_element.click();
		}
	}

	public void click_if_exists() {
		click_if_enabled();
	}

	public void click_if_exists_and_enabled() {
		// TODO Auto-generated method stub
		
	}
}