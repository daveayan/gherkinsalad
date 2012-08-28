package daveayan.gherkinsalad.components.tinymce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.TextEnterable;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class TinyMceEditor extends BaseBrowserElement implements TextEnterable {	
	public static TinyMceEditor find(By locator) {
		TinyMceEditor rte = new TinyMceEditor();
		rte.found(locator);
		return rte;
	}
	
	public String getText() {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(0);
		WebElement editable = browser.driver().switchTo().activeElement();
		return editable.getText();
	}
	
	public void click_if_enabled() {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(0);
		WebElement editable = browser.driver().switchTo().activeElement();
		editable.click();
		browser.driver().switchTo().defaultContent();
	}

	public boolean isEnabled() {
		return true;
	}

	public void enter_text_if_enabled(String text) {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(0);
		WebElement editable = browser.driver().switchTo().activeElement();
		editable.clear();
		editable.sendKeys(text);
		browser.driver().switchTo().defaultContent();
	}

	public void append_text_if_enabled(String text) {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(0);
		WebElement editable = browser.driver().switchTo().activeElement();
		editable.sendKeys(text);
		browser.driver().switchTo().defaultContent();
	}

	public void click_if_exists_and_enabled() {	
	}
}