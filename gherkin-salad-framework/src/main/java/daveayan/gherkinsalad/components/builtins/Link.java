package daveayan.gherkinsalad.components.builtins;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.report.Report;

public class Link extends BaseBrowserElement implements Clickable {
	private static Log log = LogFactory.getLog(Link.class);
	
	public static Link find(By locator) {
		Link l = new Link();
		l.found(locator);
		return l;
	}
	
	public boolean isEnabled() {
		WebElement element = fetch_element();
		return element.isEnabled();
	}

	public void click_if_enabled() {
		wait_between_steps();
		WebElement element = fetch_element();
		if(this.isEnabled()) {
			element.click();
		}
		Report.action("Clicked " + this);
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