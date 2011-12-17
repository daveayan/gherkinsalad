package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import daveayan.actions.Clickable;

public class Go implements Clickable {
	@FindBy(how = How.ID, using = "navGoButton") private WebElement go_button_div;
	public void click() {
		go_button_div.findElement(By.tagName("input")).click();
	}
}