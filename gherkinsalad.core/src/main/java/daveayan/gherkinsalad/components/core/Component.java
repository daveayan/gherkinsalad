package daveayan.gherkinsalad.components.core;

import org.openqa.selenium.By;


public class Component extends BaseBrowserElement {
	public static Component find(By locator) {
		Component c = new Component();
		c.found(locator);
		return c;
	}
}