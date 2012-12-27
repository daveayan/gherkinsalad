package daveayan.gherkinsalad.components.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NullElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.Utils;


/** @author daveayan */
/**
 * This class is a wrapper around the WebElement object that Webdriver provides. It has the same methods as the WebElement and has a lot of more methods.
 */
public class Element extends BaseBrowserElement implements Nullable {
	private WebElement _webElement;
	
	/**
	 * Use this method to get back the native WebElement under the hood
	 * @return WebElement
	 */
	public WebElement _nativeWebElement() {
		return _webElement;
	}
	
	/**
	 * Use this factory method to create a new instance of Element that wraps WebElement object.
	 * NOTE: Do not use this method unless explicitly needed.
	 * 
	 * @param we - WebElement
	 * @param name - Name of the element, a generic string
	 * @param element_locator, How was this element located. This is used to find child elements under it.
	 * @return Element
	 */
	public static Element newInstance(WebElement we, String name, By element_locator) {
		Element e = new Element();
		e._webElement = we;
		e.name(name);
		e.found(element_locator);
		return e;
	}
	
	/**
	 * Use this method to give this element a good readable name.
	 * @return self with the updated name
	 */
	public Element name(String name) {
		this._name = name;
		return this;
	}
	
	/**
	 * This method is called internally by the framework to get the information about the element like location, size and CSS. This information can then be used to do validations on each run.
	 * NOTE: This method is still under development.
	 * @return
	 */
	public String static_info() {
		String info = "";
		Point point = getLocation();
		info += "X = " + point.getX();
		info += ", Y = " + point.getY();
		Dimension dimension = getSize();
		info += ", Height = " + dimension.getHeight();
		info += ", Width = " + dimension.getWidth();
		return info;
	}
	
	/**
	 * Use this method to check if all the supplied text strings exist in the element's text. Internally this method calls the "has(text)" method. Verifies with case sensitivity.
	 * @param expectedTexts
	 * @return false - if at least one supplied text string is not present
	 * 				 true - if all the supplied text strings are present
	 */
	public boolean hasAllTexts(String... expectedTexts) {
		for(String e: expectedTexts) {
			if(! this.has(e)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Use this method to check if any of the supplied text strings exist in the element's text. Internally this method calls the "has(text)" method. Verifies with case sensitivity.
	 * @param expectedTexts
	 * @return false - if none of the supplied text string is present
	 * 				 true - if at least the supplied text strings are present
	 */
	public boolean hasAnyText(String... expectedTexts) {
		for(String e: expectedTexts) {
			if(this.has(e)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Use this method to verify if the element has a certain text. Verifies with case sensitivity.
	 * @param text
	 * @return false - if the text is not present
	 * 				 true - if the text is present
	 */
	public boolean has(String text) {
		String text_to_verify = StringUtils.trimToEmpty(text);
		if(is_not_null(_webElement)) {
			String webelement_text = StringUtils.trimToEmpty(_webElement.getText());
			boolean value = webelement_text.contains(text_to_verify);
			if(value == true) {
				info("Element '" + super.name() + "' - has text '" + text_to_verify + "'");
			} else {
				info("Element '" + super.name() + "' - does not have text '" + text_to_verify + "'. It has '" + webelement_text + "'");
			}
			return value;
		}
		info("Element '" + super.name() + "' - has a null _webelement, cannot do has(" + text + ")");
		return false;
	}
	
	/**
	 * Use this method to verify if the element text is exactly the same as the text passed in. Verifies with case sensitivity.
	 * @param text
	 * @return false - if the webElement.getText 
	 * 				 true - if the text is present
	 */
	public boolean is(String text) {
		String text_to_verify = StringUtils.trimToEmpty(text);
		if(is_not_null(_webElement)) {
			String webelement_text = StringUtils.trimToEmpty(_webElement.getText());
			boolean value = StringUtils.equals(text_to_verify, webelement_text);
			if(value == true) {
				info("Element '" + super.name() + "' - is text '" + text_to_verify + "'");
			} else {
				info("Element '" + super.name() + "' - is not text '" + text_to_verify + "'");
			}
			return value;
		}
		info("Element '" + super.name() + "' - has a null _webelement, cannot do is(" + text_to_verify + ")");
		return false;
	}
	
	// Methods from WebElement interface
	public void clear() {
		if(is_not_null(_webElement)) {
			_webElement.clear();
			action("Cleared text in element " + name());
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do clear()");
		}
	}

	public void click() {
		if(is_not_null(_webElement)) {
			_webElement.click();
			action("Clicked on element " + name());
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do click()");
		}
	}

	public Element findElement(final By by) {
		WebElement in = _nativeWebElement();
		if (in == null || in instanceof NullElement) {
			throw new AssertionError("Cannot find any element '" + by + "' on a null web element '" + in + "'");
		}
		Wait<WebElement> wait = new FluentWait<WebElement>(in).withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
				.pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement _webElement = null;
		try {
			_webElement = wait.until(new Function<WebElement, WebElement>() {
				public WebElement apply(WebElement find_within) {
					return find_within.findElement(by);
				}
			});
		} catch (TimeoutException toe) {
			return NullElement.newInstance(by);
		}
		if(_webElement == null) {
			return NullElement.newInstance(by);
		} else {
			return Element.newInstance(_webElement, by.toString(), by);
		}
	}
	
	public Elements findElements(final By by) {
		WebElement in = _nativeWebElement();
		Wait<WebElement> wait = new FluentWait<WebElement>(in).withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
				.pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		List<WebElement> _webElements;
		try {
			_webElements = wait.until(new Function<WebElement, List<WebElement>>() {
				public List<WebElement> apply(WebElement find_within) {
					return find_within.findElements(by);
				}
			});
		} catch (TimeoutException toe) {
			return Elements.nullInstance();
		}
		Elements elements = convertToElements(_webElements, by);
		return elements;
	}
	
	public void move_by(int x, int y) {
		takeScreenshot();
		Point current_location = getLocation();
		action("Current position of element '" + current_location + "', Moving by (x, y) = (" + x + ", " + y + ")");
		
		Actions drag = new Actions(browser.driver());
		try {
			drag.dragAndDropBy(_nativeWebElement(), x, y).build().perform();
			Point new_location = getLocation();
			action("New position of element '" + new_location + "', Moved by (x, y) = (" + x + ", " + y + ")");
		} catch (org.openqa.selenium.interactions.MoveTargetOutOfBoundsException mtoobe) {
			error("Cannot move element '" + current_location + "', to (x, y) = (" + x + ", " + y + "), got org.openqa.selenium.interactions.MoveTargetOutOfBoundsException");
		}
		
		takeScreenshot();
	}
	
	public void move_to(int x, int y) {
		takeScreenshot();
		Point current_location = getLocation();
		int offset_x = x - current_location.getX();
		int offset_y = y - current_location.getY();
		
		action("Current position of element '" + current_location + "', Moving to (x, y) = (" + x + ", " + y + ")");
		Actions drag = new Actions(browser.driver());
		try {
			drag.dragAndDropBy(_nativeWebElement(), offset_x, offset_y).build().perform();
			Point new_location = getLocation();
			action("New position of element '" + new_location + "', Moved to (x, y) = (" + x + ", " + y + ")");
		} catch (org.openqa.selenium.interactions.MoveTargetOutOfBoundsException mtoobe) {
			error("Cannot move element '" + current_location + "', to (x, y) = (" + x + ", " + y + "), got org.openqa.selenium.interactions.MoveTargetOutOfBoundsException");
		}
		
		takeScreenshot();
	}
	
	public void move_to(Element other_element) {
		takeScreenshot();
		action("Moving element '" + this + "' to element '" + other_element + "'");
		Actions drag = new Actions(browser.driver());
		try {
			drag.dragAndDrop(_nativeWebElement(), other_element._nativeWebElement());
			action("Moved element '" + this + "' to element '" + other_element + "'");
		} catch (org.openqa.selenium.interactions.MoveTargetOutOfBoundsException mtoobe) {
			error("Cannot move element '" + this + "' to element '" + other_element + "', got org.openqa.selenium.interactions.MoveTargetOutOfBoundsException");
		}
		takeScreenshot();
	}

	public void select_upto(Element other_element) {
		Actions actions = new Actions(browser.driver());
		
		actions.moveToElement(_nativeWebElement()).build().perform();
		wait_between_steps_plus(1);
		
		actions.clickAndHold(_nativeWebElement()).build().perform();
		wait_between_steps_plus(1);
		
		actions.moveToElement(other_element._nativeWebElement()).build().perform();
		wait_between_steps_plus(1);
		
		actions.release().build().perform();
	}
	
	private Elements convertToElements(List<WebElement> _webElements, By locator) {
		Elements elements = new Elements();
		if(_webElements != null) {
			for(WebElement _we: _webElements) {
				elements.add(Element.newInstance(_we, locator.toString(), locator));
			}
		}
		return elements;
	}
	
	public boolean isNotHidden() {
		return ! isHidden();
	}
	
	public boolean isHidden() {
		String hidden = getAttribute("hidden");
		if(hidden != null && hidden.equals("true")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public boolean isStyleNotDisplayed() {
		return ! isStyleDisplayed();
	}
	
	public boolean isStyleDisplayed() {
		String style = getAttribute("style");
		if(StringUtils.isNotBlank(style)) {
			if(style.contains("display: none")) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
	
//	ALL ARIA METHODS - START 
	
//	ARIA DISPLAYED
	public boolean isArisEnabled() {
		return ! isAriaDisabled();
	}
	
	public boolean isAriaDisabled() {
		String ariadisabled = getAttribute("aria-disabled");
		if(StringUtils.isNotBlank(ariadisabled)) {
			if(Utils.equals(ariadisabled, "true")) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
//	ARIA HIDDEN
	public boolean isAriaNotHidden() {
		return ! isAriaHidden();
	}
	
	public boolean isAriaHidden() {
		String ariahidden = getAttribute("aria-hidden");
		if(StringUtils.isNotBlank(ariahidden)) {
			if(Utils.equals(ariahidden, "true")) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
//	ARIA READ ONLY
	public boolean isAriaNotReadonly() {
		return ! isAriaReadonly();
	}
	
	public boolean isAriaReadonly() {
		String ariareadonly = getAttribute("aria-readonly");
		if(StringUtils.isNotBlank(ariareadonly)) {
			if(Utils.equals(ariareadonly, "true")) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}	
	
//	ARIA SELECTED
	public boolean isAriaNotSelected() {
		return ! isAriaSelected();
	}
	
	public boolean isAriaSelected() {
		String ariareadonly = getAttribute("aria-selected");
		if(StringUtils.isNotBlank(ariareadonly)) {
			if(Utils.equals(ariareadonly, "true")) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}	
	
//	ALL ARIA METHODS - END
	
	public String getAttribute(String arg0) {
		if(is_not_null(arg0)) {
			String value = _webElement.getAttribute(arg0);
			info("Element '" + super.name() + "' - Attribute '" + arg0 + "' has value '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do getAttribute(" + arg0 + ")");
		}
		return StringUtils.EMPTY;
	}

	public String getCssValue(String arg0) {
		if(is_not_null(arg0)) {
			String value = _webElement.getCssValue(arg0);
			info("Element '" + super.name() + "' - CSS '" + arg0 + "' has value '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do getCssValue(" + arg0 + ")");
		}
		return StringUtils.EMPTY;
	}

	public Point getLocation() {
		if(is_not_null(_webElement)) {
			Point value = _webElement.getLocation();
			info("Element '" + super.name() + "' - Location is '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do getLocation()");
		}
		return new Point(0, 0);
	}

	public Dimension getSize() {
		if(is_not_null(_webElement)) {
			Dimension value = _webElement.getSize();
			info("Element '" + super.name() + "' - Dimention is '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do getSize()");
		}
		return new Dimension(0, 0);
	}

	public String getTagName() {
		if(is_not_null(_webElement)) {
			String value = _webElement.getTagName();
			info("Element '" + super.name() + "' - Tag Name is '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do getTagName()");
		}
		return StringUtils.EMPTY;
	}

	public boolean isSelected() {
		if(is_not_null(_webElement)) {
			boolean value = _webElement.isSelected();
			info("Element '" + super.name() + "' - isSelected '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do isSelected()");
		}
		return Boolean.FALSE;
	}

	public void sendKeys(CharSequence... arg0) {
		if(is_not_null(_webElement)) {
			_webElement.sendKeys(arg0);
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do sendKeys(" + arg0 + ")");
		}
	}

	public void submit() {
		if(is_not_null(_webElement)) {
			_webElement.submit();
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do submit()");
		}
	}

	public String getText() {
		if(is_not_null(_webElement)) {
			String value = StringUtils.trimToEmpty(_webElement.getText());
			info("Element '" + super.name() + "' - Text is '" + value + "'");
			return value;
		} else {
			info("Element '" + super.name() + "' - has a null _webelement, cannot do getText()");
		}
		return StringUtils.EMPTY;
	}
	
	public boolean isEnabled() {
		if(is_not_null(_webElement)) {
			boolean isEnabled = _webElement.isEnabled();
			info("Element '" + super.name() + "' - isEnabled is '" + isEnabled + "'");
			return isEnabled;
		}
		return Boolean.FALSE;
	}
	
	public boolean isDisplayed() {
		if(is_not_null(_webElement)) {
			boolean isDisplayed = _webElement.isDisplayed();
			info("Element '" + super.name() + "' - isDisplayed is '" + isDisplayed + "'");
			return isDisplayed;
		}
		return Boolean.FALSE;
	}
}