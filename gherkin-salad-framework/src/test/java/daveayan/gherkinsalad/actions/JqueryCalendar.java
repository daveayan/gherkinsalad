package daveayan.gherkinsalad.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.builtins.BaseBrowserElement;

public class JqueryCalendar extends BaseBrowserElement {
	private static Log log = LogFactory.getLog(JqueryCalendar.class);
	public boolean isEnabled() {
		WebElement control_div = fetch_element(0);
		if(control_div == null) {
			log.info(this + " has control_div null, it is not enabled");
			return false;
		}
		if(control_div.getAttribute("style").contains("display: none")) {
			log.info(this + " has display: none, it is not enabled");
			return false;
		}
		if(control_div.getAttribute("style").contains("display: block")) {
			log.info(this + " has display: block, it is enabled");
			return true;
		}
		return false;
	}
}