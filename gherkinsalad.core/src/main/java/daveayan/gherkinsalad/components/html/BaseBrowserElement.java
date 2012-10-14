/**
 * Copyright (c) 2012 Ayan Dave http://daveayan.com
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
 * associated documentation files (the "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the 
 * following conditions:
 * 
 * 1) The above copyright notice and this permission notice shall be included without any changes or alterations 
 * in all copies or substantial portions of the Software.
 * 2) This software shall be used for Good, not Evil.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **/
package daveayan.gherkinsalad.components.html;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NullElement;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import daveayan.gherkinsalad.AutomationObject;
import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.components.BrowserElement;
import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.test.Assert;

/**@author daveayan*/
/**
 * Base class for all the page objects. This class provides:
 * <ul>
 * <li>Default implementations for the methods from BrowserElement, CanBeEnabled, CanBeDisabled, Nullable
 * interfaces</li>
 * <li>Methods to find more browser elements within this one</li>
 * </ul>
 */
public abstract class BaseBrowserElement extends AutomationObject implements BrowserElement {
	private String _name = StringUtils.EMPTY;
	private By element_locator;

//	protected boolean is_not_null(Object o) {
//		return !is_null(o);
//	}
//
//	protected boolean is_null(Object o) {
//		if (o == null)
//			return true;
//		if (o instanceof Nullable) {
//			Nullable n = (Nullable) o;
//			return n.is_null();
//		}
//		return false;
//	}

	public Element root_element() {
		Element element = findElement(element_locator);
		validate_position_and_css(element);
		return element;
	}

	public Element findElement(final By by) {
		if (browser.driver() instanceof NullWebDriver) {
			throw new AssertionError("Cannot find any element '" + by + "' on a NullWebDriver");
		}
		Wait<WebDriver> wait = new FluentWait<WebDriver>(browser.driver())
				.withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
				.pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement _webElement;
		try {
			_webElement = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		} catch (TimeoutException toe) {
			return NullElement.newInstance(element_locator);
		}
		return Element.newInstance(_webElement, _name, by);
	}

	/**
	 * DEFAULT IMPLEMENTATIONS OF THE BROWSERELEMENT INTERFACE
	 */

	/**
	 * Default implementation. Returns the value from getText() method of WebElement
	 */
	public String getText() {
		Element element = root_element();
		return element.getText();
	}

	/**
	 * Default implementation. Verifies that the expected texts exists in the result of getText() Takes
	 * Screenshot if expected text were not found and returns Boolean.FALSE
	 * 
	 * @return Boolean.TRUE of all the expected texts are present. Boolean.FALSE otherwise.
	 */
	public boolean has_text(String... expected_texts) {
		if (expected_texts != null) {
			List<String> expected_text_not_present = new ArrayList<String>();
			String element_text = getText();
			for (String expected_text : expected_texts) {
				if (!element_text.contains(expected_text.trim())) {
					expected_text_not_present.add(expected_text.trim());
				}
			}
			if (!expected_text_not_present.isEmpty()) {
				takeScreenshot();
				return false;
			}
		}
		return true;
	}

	/**
	 * Default Implementation. Asserts that the expected texts exists in the result of getText() Throws single
	 * assertion error with all the texts that were expected but were not present. Takes Screenshot if expected
	 * text were not found and assertion error was thrown
	 */
	public void should_have_text(String... expected_texts) {
		if (expected_texts != null) {
			String element_text = getText();
			for (String expected_text : expected_texts) {
				if (!element_text.contains(expected_text.trim())) {
					error("Component '" + this + "' does not have expected text(s) '" + expected_text + "'It has '" + element_text + "'");
				}
			}
		}
	}

	/**
	 * Default Implementation. Asserts that the unexpected texts do not exist in the result of getText() Throws
	 * single assertion error with all the texts that were not expected but were present. Takes Screenshot if
	 * unexpected texts were found and assertion error was thrown
	 */
	public void should_not_have_text(String... unexpected_texts) {
		if (unexpected_texts != null) {
			String element_text = getText();
			for (String expected_text : unexpected_texts) {
				if (element_text.contains(expected_text.trim())) {
					error("Component '" + this + "' has unexpected text(s) '" + element_text + "'It has '" + element_text + "'");
				}
			}
		}
	}

	/**
	 * Default Implementation. Asserts that isDisplayed() method returns Boolean.TRUE
	 */
	public void should_be_displayed() {
		Assert.assertTrue("Expected '" + this + "' to be displayed, found it hidden", this.isDisplayed());
	}

	/**
	 * Default Implementation. Asserts that isNotDisplayed() method returns Boolean.TRUE
	 */
	public void should_not_be_displayed() {
		Assert.assertTrue("Expected '" + this + "' to be hidden, found it displayed", this.isNotDisplayed());
	}

	/**
	 * Default Implementation. Asserts that isEnabled() method returns Boolean.TRUE
	 */
	public void should_be_enabled() {
		Assert.assertTrue("Expected '" + this + "' to be enabled, found it disabled", this.isEnabled());
	}

	/**
	 * Default Implementation. Asserts that isDisabled() returns Boolean.TRUE
	 */
	public void should_be_disabled() {
		Assert.assertTrue("Expected '" + this + "' to be enabled, found it disabled", this.isDisabled());
	}

	public BrowserElement name(String name) {
		this._name = name;
		return this;
	}
	
	/**
	 * Sets the element locator for this browser element. Element Locator can be specified using the <a
	 * href="http://selenium.googlecode.com/svn/trunk/docs/api/java/org/openqa/selenium/By.html">By</a> object
	 * of selenium
	 */
	public BrowserElement found(By element_locator) {
		this.element_locator = element_locator;
		return this;
	}

	/**
	 * Default Implementation. Returns Boolean.TRUE
	 */
	public boolean isEnabled() {
		return Boolean.TRUE;
	}

	/**
	 * Default Implementation. Returns ! isEnabled()
	 */
	public boolean isDisabled() {
		return !isEnabled();
	}

	/**
	 * Default Implementation. Returns Boolean.TRUE
	 */
	public boolean isDisplayed() {
		return Boolean.TRUE;
	}

	/**
	 * Default Implementation. Returns ! isDisplayed()
	 */
	public boolean isNotDisplayed() {
		return !isDisplayed();
	}

	/**
	 * Default Implementation. Returns a formatted string with the element locator.
	 */
	public String toString() {
		return this.getClass().getName() + ", " + element_locator;
	}

	public String name() {
		if(StringUtils.isBlank(_name)) {
			if(element_locator != null) {
				return element_locator.toString();
			} else {
				return "No Name";
			}
		} else {
			return _name;
		}
	}
	
	private boolean thisIsNamedElement() {
		return StringUtils.isNotBlank(_name);
	}
	
	private void validate_position_and_css(Element element) {
		if(element.is_not_null()) {
			if(thisIsNamedElement()) {
				String expected = find_position_and_css(this._name);
				String actual = element.static_info();
				if(StringUtils.isNotBlank(expected)) {
					boolean match = StringUtils.equals(expected, actual);
					if(match) {
						action("Verified that position, size and css of '" + this._name + "' is ok");
					} else {
						error("Position, size and css of '" + this._name + "' is not verified. Expected '" + expected +"', actual '" + actual + "'");
					}
				} else {
					save_position_and_css(this._name, actual);
				}
			}
		}
	}
	
	private void save_position_and_css(String name, String info) {
		File file = new File(Config.execution_results_storage_location + "/" + sanitizeFilename(name) + ".info");
		try {
			FileUtils.writeStringToFile(file, info, false);
		} catch (IOException e) {
		}
	}
	
	private String find_position_and_css(String name) {
		File file = new File(Config.execution_results_storage_location + "/" + sanitizeFilename(name) + ".info");
		if(file.exists()) {
			try {
				String contents = FileUtils.readFileToString(file);
				return contents;
			} catch (IOException e) {
			}
		}
		return StringUtils.EMPTY;
	}
	
	private String sanitizeFilename(String name) {
    return name.replaceAll("[:\\\\/*?|<>]", "_");
  }
}