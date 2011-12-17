package example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import daveayan.actions.TextEnterable;

public class SearchBox implements TextEnterable {
	@FindBy(how = How.ID, using = "twotabsearchtextbox") private WebElement search_box;
	public void append_text(String text) {
		search_box.sendKeys(text);
	}

	public void enter_text(String text) {
		search_box.clear();
		search_box.sendKeys(text);
	}
}