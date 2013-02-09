package daveayan.gherkinsalad.components.dojo;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.html.Clickable;
import daveayan.gherkinsalad.components.html.impl.Link;

public class Button extends Link implements Clickable {
	public static Button find(By locator) {
		Button b = new Button();
		b.found(locator);
		return b;
	}
}