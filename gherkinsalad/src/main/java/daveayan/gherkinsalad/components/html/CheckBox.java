package daveayan.gherkinsalad.components.html;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.TimeoutException;

import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.components.Element;

public class CheckBox extends BaseBrowserElement implements Clickable {
	private static Log log = LogFactory.getLog(CheckBox.class);
	public boolean isEnabled() {
		Element element = root_element();
		return element.isEnabled();
	}

	public void click_if_enabled() {
		Element element = root_element();
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