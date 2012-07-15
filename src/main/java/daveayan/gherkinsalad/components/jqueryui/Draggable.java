package daveayan.gherkinsalad.components.jqueryui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class Draggable extends BaseBrowserElement {
	public Draggable() {
		super.found(By.id("draggable"));
	}
	
	public void drag() {
		Actions drag = new Actions(super.browser.driver());
		Actions hover = new Actions(super.browser.driver());
		WebElement e = fetch_element();
		
		WebElement logo = browser.driver().findElement(By.linkText("Resizable"));
		
		System.out.println(e.getLocation());
		
		hover.moveToElement(logo, 10, 10).build().perform();
		wait_for_seconds(5);
		
		drag.dragAndDropBy(e, 100, 100).build().perform();
		wait_for_seconds(4);
		
		e = fetch_element();
		System.out.println(e.getLocation());
	}
}