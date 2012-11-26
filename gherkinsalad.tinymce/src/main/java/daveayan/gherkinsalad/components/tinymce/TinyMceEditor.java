package daveayan.gherkinsalad.components.tinymce;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.html.TextEnterable;

public class TinyMceEditor extends Component implements TextEnterable {
	private String iframe_name_or_id = "content_ifr";
	public static TinyMceEditor find_iframe(String nameOrId) {
		TinyMceEditor rte = new TinyMceEditor();
		rte.iframe_name_or_id = nameOrId;
		return rte;
	}
	
	public String getText() {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(iframe_name_or_id);
		WebElement editable = browser.driver().switchTo().activeElement();
		String content = editable.getText();
		browser.driver().switchTo().defaultContent();
		return content;
	}
	
	public void click_if_enabled() {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(iframe_name_or_id);
		WebElement editable = browser.driver().switchTo().activeElement();
		editable.click();
		browser.driver().switchTo().defaultContent();
	}

	public boolean isEnabled() {
		return true;
	}

	public void enter_text_if_enabled(String text) {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(iframe_name_or_id);
		WebElement editable = browser.driver().switchTo().activeElement();
		editable.clear();
		editable.sendKeys(text);
		browser.driver().switchTo().defaultContent();
	}

	public void append_text_if_enabled(String text) {
		wait_between_steps_plus(2);
		browser.driver().switchTo().frame(iframe_name_or_id);
		WebElement editable = browser.driver().switchTo().activeElement();
		editable.sendKeys(text);
		browser.driver().switchTo().defaultContent();
	}

	public void click_if_exists_and_enabled() {	
	}
}