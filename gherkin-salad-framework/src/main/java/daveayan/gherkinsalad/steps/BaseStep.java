package daveayan.gherkinsalad.steps;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.gherkinsalad.browser.actions.Clickable;
import daveayan.gherkinsalad.browser.actions.Selectable;
import daveayan.gherkinsalad.browser.actions.TextEnterable;

public class BaseStep {
	protected static Browser browser;
	
	public PageElementKey on_element_with_key(String role_name, String component_name, String element_name) {
		return PageElementKey.newInstance(role_name, component_name, element_name);
	}
	
	public PageElementKey to_element_with_key(String role_name, String component_name, String element_name) {
		return on_element_with_key(role_name, component_name, element_name);
	}
	
	public void select(String text, PageElementKey page_element_key) {
		Selectable selectable_element = (Selectable) browser.locate_element_object_for(page_element_key);
		selectable_element.select_if_enabled(text);
	}
	
	public void append(String text, PageElementKey page_element_key) {
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for(page_element_key);
		text_enterable_element.append_text_if_enabled(text);
	}
	
	public void click(PageElementKey page_element_key) {
		Clickable go_button = (Clickable) browser.locate_element_object_for(page_element_key);
		go_button.click_if_enabled();
	}
	
	public void enter_text(String text, PageElementKey page_element_key) {
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for(page_element_key);
		text_enterable_element.enter_text_if_enabled(text);
	}
	
	public void select_text(String text, PageElementKey page_element_key) {
		Selectable selectable_element = (Selectable) browser.locate_element_object_for(page_element_key);
		selectable_element.select_if_enabled(text);
	}
	
	public void launch_browser(String browser_name) {
		browser = Browser.instance(browser_name);
		browser.launch();
	}
	
	public void launch_browser_with(String name, String page_structure_file_name) {
		browser = Browser.instance_with_name_and_page_structure_name(name, page_structure_file_name);
		browser.launch();
	}

	public void close_browser() {
		if (browser != null) {
			browser.close();
		}
	}
	
	public void goto_url(String url) {
		browser.goto_url(url);
	}
	
	public void wait_for_page_to_load() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new AssertionError("wait_for_page_to_load interrupted");
		}
	}
}