package daveayan.gherkinsalad.components.builtins;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.Clickable;

public class CheckBox extends BaseBrowserElement implements Clickable {
	private static Log log = LogFactory.getLog(CheckBox.class);
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
		try {
			click_if_enabled();
			log.info("Clicked on " + this);
		} catch(TimeoutException te) {
			log.info("Element does not exist " + this);
		}
	}
}