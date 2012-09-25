package daveayan.gherkinsalad.components.html;

import org.openqa.selenium.By;
import org.openqa.selenium.NullElement;

import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.components.Element;

public class Link extends BaseBrowserElement implements Clickable {
	public static Link find(By locator) {
		Link l = new Link();
		l.found(locator);
		return l;
	}
	
	public boolean isEnabled() {
		Element element = root_element();
		return element.isEnabled();
	}

	public void click_if_enabled() {
		wait_between_steps();
		Element element = root_element();
		if(this.isEnabled()) {
			element.click();
		} else {
			action("Did not click disabled " + this);
			takeScreenshot();
		}
	}
	
	public void click_if_exists() {
		click_if_enabled();
	}
	
	public boolean isDisplayed() {
		Element element = root_element();
		return element.is_not_null();
	}
	
	public void click_if_exists_and_enabled() {
		wait_between_steps();
		Element element = root_element();
		if(! (element instanceof NullElement)) {
			if(this.isEnabled()) {
				element.click();
			} else {
				action("Did not click disabled " + this);
				takeScreenshot();
			}
		} else {
			action("Did not click non existent " + this);
			takeScreenshot();
		}
	}
}