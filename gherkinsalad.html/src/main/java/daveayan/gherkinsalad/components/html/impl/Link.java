package daveayan.gherkinsalad.components.html.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.NullElement;

import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.html.Clickable;

public class Link extends Component implements Clickable {
	public static Link find(By locator) {
		Link l = new Link();
		l.found(locator);
		return l;
	}
	
	public Link name(String name) {
		super.name(name);
		return this;
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