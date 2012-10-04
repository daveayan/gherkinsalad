package daveayan.gherkinsalad.components;

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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;
import daveayan.lang.Nullable;

public class Element extends BaseBrowserElement implements Nullable {
	private WebElement _webElement;
	
	public WebElement _nativeWebElement() {
		return _webElement;
	}
	
	public static Element newInstance(WebElement we, String name, By element_locator) {
		Element e = new Element();
		e._webElement = we;
		e.name(name);
		e.found(element_locator);
		return e;
	}
	
	public Element name(String name) {
		super.name(name);
		return this;
	}
	
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
	
	public boolean hasAllTexts(String... expectedTexts) {
		for(String e: expectedTexts) {
			if(! this.has(e)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasAnyText(String... expectedTexts) {
		for(String e: expectedTexts) {
			if(this.has(e)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean has(String text) {
		if(is_not_null(_webElement)) {
			boolean value = _webElement.getText().contains(text);
			if(value == true) {
				info("Element '" + this.name() + "' - has text '" + text + "'");
			} else {
				info("Element '" + this.name() + "' - does not have text '" + text + "'. It has '" + _webElement.getText() + "'");
			}
			return value;
		}
		info("Element '" + this.name() + "' - has a null _webelement, cannot do has(" + text + ")");
		return false;
	}
	
	public boolean is(String text) {
		if(is_not_null(_webElement)) {
			boolean value = StringUtils.equals(text, _webElement.getText());
			if(value == true) {
				info("Element '" + this.name() + "' - is text '" + text + "'");
			} else {
				info("Element '" + this.name() + "' - is not text '" + text + "'");
			}
			return value;
		}
		info("Element '" + this.name() + "' - has a null _webelement, cannot do is(" + text + ")");
		return false;
	}
	
	// Methods from WebElement interface
	public void clear() {
		if(is_not_null(_webElement)) {
			_webElement.clear();
			action("Cleared text in element " + name());
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do clear()");
		}
	}

	public void click() {
		if(is_not_null(_webElement)) {
			_webElement.click();
			action("Clicked on element " + name());
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do click()");
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
		return Element.newInstance(_webElement, by.toString(), by);
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
		Elements elements = new Elements();
		if(_webElements != null) {
			for(WebElement _we: _webElements) {
				elements.add(Element.newInstance(_we, by.toString(), by));
			}
		}
		return elements;
	}

	public String getAttribute(String arg0) {
		if(is_not_null(arg0)) {
			String value = _webElement.getAttribute(arg0);
			info("Element '" + this.name() + "' - Attribute '" + arg0 + "' has value '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do getAttribute(" + arg0 + ")");
		}
		return StringUtils.EMPTY;
	}

	public String getCssValue(String arg0) {
		if(is_not_null(arg0)) {
			String value = _webElement.getCssValue(arg0);
			info("Element '" + this.name() + "' - CSS '" + arg0 + "' has value '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do getCssValue(" + arg0 + ")");
		}
		return StringUtils.EMPTY;
	}

	public Point getLocation() {
		if(is_not_null(_webElement)) {
			Point value = _webElement.getLocation();
			info("Element '" + this.name() + "' - Location is '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do getLocation()");
		}
		return new Point(0, 0);
	}

	public Dimension getSize() {
		if(is_not_null(_webElement)) {
			Dimension value = _webElement.getSize();
			info("Element '" + this.name() + "' - Dimention is '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do getSize()");
		}
		return new Dimension(0, 0);
	}

	public String getTagName() {
		if(is_not_null(_webElement)) {
			String value = _webElement.getTagName();
			info("Element '" + this.name() + "' - Tag Name is '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do getTagName()");
		}
		return StringUtils.EMPTY;
	}

	public boolean isSelected() {
		if(is_not_null(_webElement)) {
			boolean value = _webElement.isSelected();
			info("Element '" + this.name() + "' - isSelected '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do isSelected()");
		}
		return Boolean.FALSE;
	}

	public void sendKeys(CharSequence... arg0) {
		if(is_not_null(_webElement)) {
			_webElement.sendKeys(arg0);
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do sendKeys(" + arg0 + ")");
		}
	}

	public void submit() {
		if(is_not_null(_webElement)) {
			_webElement.submit();
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do submit()");
		}
	}

	public String getText() {
		if(is_not_null(_webElement)) {
			String value = _webElement.getText();
			info("Element '" + this.name() + "' - Text is '" + value + "'");
			return value;
		} else {
			info("Element '" + this.name() + "' - has a null _webelement, cannot do getText()");
		}
		return StringUtils.EMPTY;
	}
}