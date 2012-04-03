package daveayan.gherkinsalad.steps;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.TimeoutException;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.gherkinsalad.browser.actions.BrowserElement;
import daveayan.gherkinsalad.browser.actions.Clickable;
import daveayan.gherkinsalad.browser.actions.Selectable;
import daveayan.gherkinsalad.browser.actions.TextEnterable;
import daveayan.gherkinsalad.datamanagement.DataElementKey;
import daveayan.gherkinsalad.datamanagement.DataSource;

public class BaseStep {
	private static Log log = LogFactory.getLog(BaseStep.class);

	protected static String current_role = StringUtils.EMPTY;

	protected static DataSource feature_data_source = null;

	protected static String data_management_driver = StringUtils.EMPTY, data_management_file = StringUtils.EMPTY;

	protected static Browser browser;

	public DataSource load_data_source(String data_source_file_name, String data_source_driver) {
		try {
			Class< ? > clazz = Class.forName(data_source_driver);
			DataSource data_source = (DataSource) clazz.newInstance();
			data_source.load_up_from_file_at_location(Path.TO_DATA_MANAGEMENT + data_source_file_name);
			return data_source;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// DATA METHODS

	protected String data_with_key(DataElementKey data_element_key) {
		String data = feature_data_source.get_data_for(data_element_key);
		System.out.println("==> " + data_element_key + " -- Data is '" + data + "'");
		return data;
	}

	protected DataElementKey data_element_with_key(String role_name, String symbolic_data_name) {
		return DataElementKey.newInstance(role_name, symbolic_data_name);
	}

	protected DataElementKey data_for(String symbolic_data_name) {
		return data_element_with_key(current_role, symbolic_data_name);
	}

	protected DataElementKey data(String symbolic_data_name) {
		return data_element_with_key(current_role, symbolic_data_name);
	}

	// COMPONENT ELEMENT METHODS

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

	public void is_enabled(PageElementKey pek) {
		wait_between_steps();
		BrowserElement element = browser.locate_element_object_for(pek);
		if (element.isDisabled()) {
			throw new AssertionError(pek + " is disabled, expected it to be enabled");
		}
	}

	public void has_enabled_elements(String component_name, String[] element_names) {
		wait_between_steps();
		if (element_names != null) {
			for (String element_name : element_names) {
				PageElementKey pek = on_element(element_name).on_component(component_name);
				BrowserElement element = (BrowserElement) browser.locate_element_object_for(pek);
				log.info("Verifying " + element);
				if (element.isDisabled()) {
					browser.takeScreenshot();
					throw new AssertionError("Element '" + pek + "' is disabled, expected it to be enabled");
				}
			}
		}
	}

	public void has_disabled_elements(String component_name, String[] element_names) {
		wait_between_steps();
		if (element_names != null) {
			for (String element_name : element_names) {
				PageElementKey pek = on_element(element_name).on_component(component_name);
				BrowserElement element = (BrowserElement) browser.locate_element_object_for(pek);
				if (element.isEnabled()) {
					browser.takeScreenshot();
					throw new AssertionError("Element '" + pek + "' is enabled, expected it to be disabled");
				}
			}
		}
	}

	public void elements_dont_exist(String component_name, String[] element_names) {
		wait_for_seconds(10);
		if (element_names != null) {
			for (String element_name : element_names) {
				PageElementKey pek = on_element(element_name).on_component(component_name);
				BrowserElement element = (BrowserElement) browser.locate_element_object_for(pek);
				if (element.exists_immediate()) {
					browser.takeScreenshot();
					throw new AssertionError("Element '" + pek + "' should not exist, found '" + element + "'");
				}
			}
		}
	}

	public void verify_text_exists(String[] expected_texts, PageElementKey page_element_key) {
		wait_between_steps();
		BrowserElement element = (BrowserElement) browser.locate_element_object_for(page_element_key);
		element.has_text(expected_texts);
	}

	public void verify_text_does_not_exist(String[] unexpected_texts, PageElementKey page_element_key) {
		wait_between_steps();
		BrowserElement element = (BrowserElement) browser.locate_element_object_for(page_element_key);
		element.does_not_have_text(unexpected_texts);
	}

	public void click(PageElementKey page_element_key) {
		wait_between_steps();
		log.info("click on " + page_element_key);
		Clickable clickable_element = (Clickable) browser.locate_element_object_for(page_element_key);
		clickable_element.click_if_enabled();
		browser.takeScreenshot();
	}

	public void if_it_exists_click(PageElementKey page_element_key) {
		wait_between_steps();
		log.info("click_if_exists on " + page_element_key);
		Clickable clickable_element = (Clickable) browser.locate_element_object_for(page_element_key);
		try {
			if (clickable_element.exists()) {
				log.info("Element exists " + clickable_element);
				clickable_element.click_if_enabled();
			}
		} catch (TimeoutException te) {
			log.info("Element does not exist " + clickable_element);
		}
		browser.takeScreenshot();
	}

	public void click(DataElementKey data_element_key) {
		wait_between_steps();
		log.info("click on " + data_element_key);
		String link_text = data_with_key(data_element_key);
		log.info("full link text : " + link_text);
		String[] data_items = link_text.split("~");
		if (data_items.length == 2) {
			Clickable clickable_element = browser.locate_clickable_element_for(data_items[1]);
			clickable_element.click_if_enabled();
		}
	}

	public void verify_there_are(String[] options, PageElementKey page_element_key) {
		wait_between_steps();
		Selectable selectable_element = (Selectable) browser.locate_element_object_for(page_element_key);
		selectable_element.has_options(options);
	}

	// ******************************************************************************************************************************************************
	// TEXT STEPS
	// ******************************************************************************************************************************************************
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

	// ******************************************************************************************************************************************************

	// ******************************************************************************************************************************************************
	// DATA STEPS
	// ******************************************************************************************************************************************************
	public void select(DataElementKey data_element_key, PageElementKey page_element_key) {
		wait_between_steps();
		String text = data_with_key(data_element_key);
		select(text, page_element_key);
	}

	public void enter(DataElementKey data_element_key, PageElementKey page_element_key) {
		wait_between_steps();
		String text = data_with_key(data_element_key);
		enter(text, page_element_key);
	}

	public void append(DataElementKey data_element_key, PageElementKey page_element_key) {
		wait_between_steps();
		String text = data_with_key(data_element_key);
		append(text, page_element_key);
	}

	// ******************************************************************************************************************************************************

	public void launch_browser_with(String name, String page_structure_file_name) {
		browser = Browser.instance_with_name_and_page_structure_name(name, page_structure_file_name);
		browser.launch();
	}

	public void close_browser() {
		current_role = StringUtils.EMPTY;
		feature_data_source = null;
		if (browser != null) {
			browser.close();
		}
	}

	public void goto_url(String url) {
		browser.goto_url(url);
	}

	protected void wait_between_steps() {
		wait_for_seconds(Config.seconds_wait_after_each_step);
	}

	protected void wait_for_seconds(int seconds) {
		try {
			log.info("User is waiting for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.info(e.getMessage());
		}
	}
}