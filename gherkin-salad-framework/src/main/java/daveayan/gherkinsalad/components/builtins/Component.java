package daveayan.gherkinsalad.components.builtins;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class Component extends BaseBrowserElement {
	public static Component find(By locator) {
		Component c = new Component();
		c.found(locator);
		return c;
	}
	
	public String getAttribute(String name) {
		WebElement e = fetch_element();
		return e.getAttribute(name);
	}
}