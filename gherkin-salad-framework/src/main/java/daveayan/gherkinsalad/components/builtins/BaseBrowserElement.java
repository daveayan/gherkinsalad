package daveayan.gherkinsalad.components.builtins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.NullWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import daveayan.gherkinsalad.BaseAutomationObject;
import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.components.BrowserElement;

public abstract class BaseBrowserElement extends BaseAutomationObject implements BrowserElement {
	private static Log log = LogFactory.getLog(BaseBrowserElement.class);
	
	protected List<By> element_locators;
//	protected Browser browser;
	
	public boolean exists_immediate() {
		WebElement element = fetch_element_immediate(0);
		return ! (element instanceof NullWebElement);
	}
	
	public boolean exists() {
		return true;
	}
	
	public boolean does_not_exist() {
		return !exists();
	}
	
	public boolean is_null() {
		return false;
	}
	public boolean is_not_null() {
		return ! is_null();
	}
	
	public boolean has_text(String... expected_texts) {
		if (expected_texts != null) {
			List<String> expected_text_not_present = new ArrayList<String>();
			WebElement element = fetch_element(0);
			for(String expected_text: expected_texts) {
				if(! element.getText().contains(expected_text.trim())) {
					expected_text_not_present.add(expected_text.trim());
				}
			}
			if(! expected_text_not_present.isEmpty()) {
				takeScreenshot();
				return false;
			}
		}
		return true;
	}
	
	public void should_have_text(String... expected_texts) {
		if (expected_texts != null) {
			List<String> expected_text_not_present = new ArrayList<String>();
			WebElement element = fetch_element(0);
			for(String expected_text: expected_texts) {
				if(! element.getText().contains(expected_text.trim())) {
					expected_text_not_present.add(expected_text.trim());
				}
			}
			if(! expected_text_not_present.isEmpty()) {
				takeScreenshot();
				throw new AssertionError("Component ' ?? ' does not have expected text(s) '" + expected_text_not_present + "'\n. It has text '" + element.getText() + "'");
			}
		}
	}
	
	public void should_not_have_text(String... unexpected_texts) {
		if (unexpected_texts != null) {
			List<String> unexpected_text_present = new ArrayList<String>();
			WebElement element = fetch_element(0);
			for(String expected_text: unexpected_texts) {
				if(element.getText().contains(expected_text.trim())) {
					unexpected_text_present.add(expected_text.trim());
				}
			}
			if(! unexpected_text_present.isEmpty()) {
				takeScreenshot();
				throw new AssertionError("Component ' ?? ' has unexpected text(s) '" + unexpected_text_present + "'");
			}
		}
	}
	
	public void should_have_hover_text(String expected_text) {
		if(StringUtils.isNotBlank(expected_text)) {
//			WebElement element = fetch_element(0);
//			 TODO: to implement
		}
	}

	public By get_by_at_id(int id) {
		return element_locators.get(id);
	}

	public WebElement fetch_element(int id) {
		if (id >= element_locators.size()) {
			return NullWebElement.newInstance(null);
		}
		By selector = element_locators.get(id);
		try {
			WebElement element = findElement(selector);
			if(element == null) {
				element = NullWebElement.newInstance(selector);
			} 
			return element;
		} catch (NoSuchElementException nsee) {
			log.info(nsee.getMessage());
		}
		return NullWebElement.newInstance(selector);
	}
	
	private WebElement fetch_element_immediate(int id) {
		if (id >= element_locators.size()) {
			return NullWebElement.newInstance(null);
		}
		By selector = element_locators.get(id);
		try {
			WebElement element = browser.driver().findElement(selector);
			if(element == null) {
				element = NullWebElement.newInstance(selector);
			} 
			return element;
		} catch (NoSuchElementException nsee) {
			log.info(nsee.getMessage());
		}
		return NullWebElement.newInstance(selector);
	}
	
	private WebElement findElement(final By by) {
		if(browser.driver() instanceof NullWebDriver) {
			throw new AssertionError("Cannot find any element '" + by + "' on a NullWebDriver");
		}
		log.info("Looking for element " + by);
	   Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.driver())
	       .withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
	       .pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS)
	       .ignoring(NoSuchElementException.class);
	   WebElement element = wait.until(new Function<WebDriver, WebElement>() {
	     public WebElement apply(WebDriver driver) {
	       return driver.findElement(by);
	     }
	   });
	   return element;
	}

	public boolean isDisabled() {
		return !isEnabled();
	}
	
	public void should_be_enabled() {
		Assert.assertTrue("Expected '" + this + "' to be enabled, found it disabled", this.isEnabled());
	}
	public void should_be_disabled() {
		Assert.assertTrue("Expected '" + this + "' to be enabled, found it disabled", this.isDisabled());
	}

	public BrowserElement element_locators_are(List<By> element_locators) {
		this.element_locators = element_locators;
		return this;
	}
	
	public BrowserElement element_locators_are(By... element_locators) {
		this.element_locators = Arrays.asList(element_locators);
		return this;
	}
	
//	public BrowserElement browser_is(Browser browser) {
//		this.browser = browser;
//		return this;
//	}
	
	public String toString() {
		return this.getClass().getName() + ", " + element_locators;
	}
}