package example;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import daveayan.actions.VerifiesText;

public class SearchResults implements VerifiesText {
	protected static final Log logger = LogFactory.getLog(SearchResults.class);
	@FindBy(how = How.ID, using = "center") private WebElement search_results;

	public void hasText(String text) {
		Assert.assertTrue("Element does not have text", search_results.getText().contains(text));
	}

	public void doesNotHaveText(String text) {
		Assert.assertFalse("Element has text", search_results.getText().contains(text));
	}
}