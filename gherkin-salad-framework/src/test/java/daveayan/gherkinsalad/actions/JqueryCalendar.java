package daveayan.gherkinsalad.actions;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.builtins.BaseBrowserElement;

public class JqueryCalendar extends BaseBrowserElement {
	public boolean isEnabled() {
		WebElement control_div = fetch_element(0);
		if(control_div == null) {
			System.out.println(this + " has control_div null, it is not enabled");
			return false;
		}
		if(control_div.getAttribute("style").contains("display: none")) {
			System.out.println(this + " has display: none, it is not enabled");
			return false;
		}
		if(control_div.getAttribute("style").contains("display: block")) {
			System.out.println(this + " has display: block, it is enabled");
			return true;
		}
		return false;
	}
}