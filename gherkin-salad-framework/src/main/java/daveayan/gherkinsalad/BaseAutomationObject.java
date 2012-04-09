package daveayan.gherkinsalad;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.gherkinsalad.browser.actions.BrowserElement;
import daveayan.gherkinsalad.browser.actions.Clickable;
import daveayan.gherkinsalad.browser.actions.NullBrowserElement;
import daveayan.gherkinsalad.browser.actions.Selectable;
import daveayan.gherkinsalad.browser.actions.TextEnterable;

public abstract class BaseAutomationObject {
	private static Log log = LogFactory.getLog(BaseAutomationObject.class);
	protected static String current_role = StringUtils.EMPTY;
	protected static Browser browser;
	
	public void click(PageElementKey page_element_key) {
		wait_between_steps();
		log.info("click on " + page_element_key);
		Clickable clickable_element = (Clickable) browser.locate_element_object_for(page_element_key);
		clickable_element.click_if_enabled();
		browser.takeScreenshot();
	}
	
	public void select(String text, PageElementKey page_element_key) {
		wait_between_steps();
		Selectable selectable_element = (Selectable) browser.locate_element_object_for(page_element_key);
		selectable_element.select_if_enabled(text);
	}

	public void enter(String text, PageElementKey page_element_key) {
		wait_between_steps();
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for(page_element_key);
		text_enterable_element.enter_text_if_enabled(text);
	}

	public void append(String text, PageElementKey page_element_key) {
		wait_between_steps();
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for(page_element_key);
		text_enterable_element.append_text_if_enabled(text);
	}
	
	protected PageElementKey on_component(String component_name) {
		return component(component_name);
	}

	protected PageElementKey on_element(String element_name) {
		return element(element_name);
	}

	protected PageElementKey in_component(String component_name) {
		return component(component_name);
	}

	protected PageElementKey in_element(String element_name) {
		return element(element_name);
	}

	protected PageElementKey component(String component_name) {
		return PageElementKey.newInstance(current_role, component_name, "");
	}

	protected PageElementKey element(String element_name) {
		return PageElementKey.newInstance(current_role, "", element_name);
	}
	
	protected void wait_between_steps() {
		wait_for_seconds(Config.seconds_wait_after_each_step);
	}

	public BrowserElement fetch(PageElementKey pek) {
		return browser.locate_element_object_for(pek);
	}
	
	public BrowserElement fetch(Class<?> clazz) {
		BrowserElement returnObject = new NullBrowserElement();
		try {
			Object object = clazz.newInstance();
			returnObject = (BrowserElement) object;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		returnObject.browser_is(browser);
		return returnObject;
	}
	
	protected void wait_for_seconds(int seconds) {
		try {
			log.info("User is waiting for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.info(e.getMessage());
		}
	}
	
	protected void takeScreenshot() {
		browser.takeScreenshot();
	}
}