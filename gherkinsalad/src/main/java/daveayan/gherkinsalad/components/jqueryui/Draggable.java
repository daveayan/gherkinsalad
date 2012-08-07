package daveayan.gherkinsalad.components.jqueryui;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class Draggable extends BaseBrowserElement {
	public Draggable() {
		super.found(By.id("draggable"));
	}
	
	public void drag() {
		Actions drag = new Actions(super.browser.driver());
		Actions hover = new Actions(super.browser.driver());
		Element e = fetch_element();
		
		Element logo = findElement(By.linkText("Resizable"));
		
		System.out.println(e.getLocation());
		
		hover.moveToElement(logo._nativeWebElement(), 10, 10).build().perform();
		wait_for_seconds(5);
		
		drag.dragAndDropBy(e._nativeWebElement(), 100, 100).build().perform();
		wait_for_seconds(4);
		
		e = fetch_element();
		System.out.println(e.getLocation());
	}
}