package daveayan.gherkinsalad.browser.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.TextEnterable;

public class TextBox extends BaseBrowserElement implements TextEnterable {
	public boolean isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void enter_text_if_enabled(String text) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			wait_between_steps();
			element.clear();
			element.sendKeys(text);
		}
	}

	public void append_text_if_enabled(String text) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			wait_between_steps();
			element.sendKeys(text);
		}
	}

	public void click_if_enabled() {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.click();
		}
	}
	
	public void click_if_exists() {
		click_if_enabled();
	}

	public void click_if_exists_and_enabled() {
		// TODO Auto-generated method stub
		
	}
}