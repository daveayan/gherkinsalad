package daveayan.gherkinsalad.components.html;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.NullWebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import daveayan.gherkinsalad.BaseAutomationObject;
import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.components.BrowserElement;
import daveayan.lang.NullList;

public abstract class BaseBrowserElement extends BaseAutomationObject implements BrowserElement {
	private static Log log = LogFactory.getLog(BaseBrowserElement.class);
	
	protected By element_locator;
	
	public boolean is_null() {
		return false;
	}
	public boolean is_not_null() {
		return ! is_null();
	}
	
	public WebElement fetch_element() {
		WebElement element = findElement(element_locator);
		return element;
	}
	
	public String getText() {
		WebElement element = fetch_element();
		return element.getText();
	}
	
	public boolean has_text(String... expected_texts) {
		if (expected_texts != null) {
			List<String> expected_text_not_present = new ArrayList<String>();
			String element_text = getText();
			for(String expected_text: expected_texts) {
				if(! element_text.contains(expected_text.trim())) {
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
			String element_text = getText();
			for(String expected_text: expected_texts) {
				if(! element_text.contains(expected_text.trim())) {
					expected_text_not_present.add(expected_text.trim());
				}
			}
			if(! expected_text_not_present.isEmpty()) {
				takeScreenshot();
				throw new AssertionError("Component '" + this + "' does not have expected text(s) '" + expected_text_not_present + "'\n. It has text '" + element_text + "'");
			}
		}
	}
	
	public void should_not_have_text(String... unexpected_texts) {
		if (unexpected_texts != null) {
			List<String> unexpected_text_present = new ArrayList<String>();
			String element_text = getText();
			for(String expected_text: unexpected_texts) {
				if(element_text.contains(expected_text.trim())) {
					unexpected_text_present.add(expected_text.trim());
				}
			}
			if(! unexpected_text_present.isEmpty()) {
				takeScreenshot();
				throw new AssertionError("Component '" + this + "' has unexpected text(s) '" + unexpected_text_present + "'");
			}
		}
	}
	
	public WebElement findElement(final By by) {
		if(browser.driver() instanceof NullWebDriver) {
			throw new AssertionError("Cannot find any element '" + by + "' on a NullWebDriver");
		}
	   Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.driver())
	       .withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
	       .pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS)
	       .ignoring(NoSuchElementException.class);
	   WebElement element;
	   try {
		   element = wait.until(new Function<WebDriver, WebElement>() {
		     public WebElement apply(WebDriver driver) {
		       return driver.findElement(by);
		     }
		   });
	   } catch (TimeoutException toe) {
	  	 element = NullWebElement.newInstance(element_locator);
	   }
	   return element;
	}
	
	protected WebElement findElement(final By by, final WebElement in) {
		if(in == null || in instanceof NullWebElement) {
			throw new AssertionError("Cannot find any element '" + by + "' on a null web element '" + in + "'");
		}
	   Wait<WebElement> wait = new FluentWait<WebElement>(in)
	       .withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
	       .pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS)
	       .ignoring(NoSuchElementException.class);
	   WebElement element;
	   try {
	  	 element = wait.until(new Function<WebElement, WebElement>() {
		     public WebElement apply(WebElement find_within) {
		       return find_within.findElement(by);
		     }
		   });
	   } catch (TimeoutException toe) {
	  	 element = NullWebElement.newInstance(element_locator);
	   }
	   return element;
	}
	
	protected List<WebElement> findElements(final By by, final WebElement in) {
		if(browser.driver() instanceof NullWebDriver) {
			throw new AssertionError("Cannot find any element '" + by + "' on a NullWebDriver");
		}
	   Wait<WebElement> wait = new FluentWait<WebElement>(in)
	       .withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
	       .pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS)
	       .ignoring(NoSuchElementException.class);
	   List<WebElement> elements;
	   try {
		   elements = wait.until(new Function<WebElement, List<WebElement>>() {
		     public List<WebElement> apply(WebElement find_within) {
		       return find_within.findElements(by);
		     }
		   });
	   } catch (TimeoutException toe) {
	  	 elements = new NullList<WebElement>();
	   }
	   return elements;
	}

	public void should_be_displayed() {
		Assert.assertTrue("Expected '" + this + "' to be displayed, found it hidden", this.isDisplayed());
	}
	
	public void should_not_be_displayed() {
		Assert.assertFalse("Expected '" + this + "' to be hidden, found it displayed", this.isDisplayed());
	}
	
	public void should_be_enabled() {
		Assert.assertTrue("Expected '" + this + "' to be enabled, found it disabled", this.isEnabled());
	}
	public void should_be_disabled() {
		Assert.assertTrue("Expected '" + this + "' to be enabled, found it disabled", this.isDisabled());
	}

	public BrowserElement found(By element_locator) {
		this.element_locator = element_locator;
		return this;
	}
	
	public String toString() {
		return this.getClass().getName() + ", " + element_locator;
	}
	
	public boolean isEnabled() {
		return true;
	}
	
	public boolean isDisabled() {
		return !isEnabled();
	}
	
	public boolean isDisplayed() {
		return true;
	}
	
	public boolean isNotDisplayed() {
		return ! isDisplayed();
	}
}