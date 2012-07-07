package daveayan.gherkinsalad.components.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.Clickable;

public class RadioButton extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		WebElement element = fetch_element();
		return element.isEnabled();
	}

	public void click_if_enabled() {
		WebElement element = fetch_element();
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