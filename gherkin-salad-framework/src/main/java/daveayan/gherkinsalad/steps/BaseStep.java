package daveayan.gherkinsalad.steps;

import org.apache.commons.lang.StringUtils;

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
	protected static String current_role = StringUtils.EMPTY;
	protected static String feature_under_test = StringUtils.EMPTY;
	protected static DataSource feature_data_source = null;
	protected static String data_management_driver = StringUtils.EMPTY, data_management_file = StringUtils.EMPTY;
	protected static Browser browser;
	
	public DataSource load_data_source(String data_source_file_name, String data_source_driver) {
		try {
			Class<?> clazz = Class.forName(data_source_driver);
			DataSource data_source = (DataSource) clazz.newInstance();
			data_source.load_up_from_file_at_location(Path.TO_DATA_MANAGEMENT + data_source_file_name);
			return data_source;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String data_with_key(DataElementKey data_element_key) {
		return feature_data_source.get_data_for(data_element_key);
	}
	
	public DataElementKey data_element_with_key(String role_name, String feature_name, String symbolic_data_name) {
		return DataElementKey.newInstance(role_name, feature_name, symbolic_data_name);
	}
	
	public DataElementKey data_element_with_key(String symbolic_data_name) {
		return DataElementKey.newInstance(current_role, feature_under_test, symbolic_data_name);
	}
	
	public PageElementKey on_page_element_with_key(String role_name, String component_name, String element_name) {
		return PageElementKey.newInstance(role_name, component_name, element_name);
	}
	
	public PageElementKey to_element_with_key(String role_name, String component_name, String element_name) {
		return on_page_element_with_key(role_name, component_name, element_name);
	}
	
	public void has_enabled_elements(String component_name, String[] element_names) {
		if(element_names != null) {
			for(String element_name: element_names) {
				PageElementKey pek = on_page_element_with_key(current_role, component_name, element_name);
				BrowserElement element = (BrowserElement) browser.locate_element_object_for(pek);
				if(element.isDisabled()) {
					throw new AssertionError("Element '" + pek + "' is disabled, expected it to be enabled");
				}
			}
		}
	}
	
	public void has_disabled_elements(String component_name, String[] element_names) {
		if(element_names != null) {
			for(String element_name: element_names) {
				PageElementKey pek = on_page_element_with_key(current_role, component_name, element_name);
				BrowserElement element = (BrowserElement) browser.locate_element_object_for(pek);
				if(element.isDisabled()) {
					throw new AssertionError("Element '" + pek + "' is enabled, expected it to be disabled");
				}
			}
		}
	}
	
	public void elements_dont_exist(String component_name, String[] element_names) {
		if(element_names != null) {
			for(String element_name: element_names) {
				PageElementKey pek = on_page_element_with_key(current_role, component_name, element_name);
				BrowserElement element = (BrowserElement) browser.locate_element_object_for(pek);
				if(element.exists()) {
					throw new AssertionError("Element '" + pek + "' should not exist, found '" + element + "'");
				}
			}
		}
	}
	
	public void verify_text_exists(String[] expected_texts, PageElementKey page_element_key) {
		BrowserElement element = (BrowserElement) browser.locate_element_object_for(page_element_key);
		element.has_text(expected_texts);
	}
	
	public void verify_text_does_not_exist(String[] unexpected_texts, PageElementKey page_element_key) {
		BrowserElement element = (BrowserElement) browser.locate_element_object_for(page_element_key);
		element.does_not_have_text(unexpected_texts);
	}
	
	public void click(PageElementKey page_element_key) {
		Clickable go_button = (Clickable) browser.locate_element_object_for(page_element_key);
		go_button.click_if_enabled();
	}
	
	// ******************************************************************************************************************************************************
	// TEXT STEPS
	// ******************************************************************************************************************************************************
	public void select(String text, PageElementKey page_element_key) {
		Selectable selectable_element = (Selectable) browser.locate_element_object_for(page_element_key);
		selectable_element.select_if_enabled(text);
	}
	
	public void enter(String text, PageElementKey page_element_key) {
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for(page_element_key);
		text_enterable_element.enter_text_if_enabled(text);
	}
	
	public void append(String text, PageElementKey page_element_key) {
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for(page_element_key);
		text_enterable_element.append_text_if_enabled(text);
	}
	// ******************************************************************************************************************************************************
	
	// ******************************************************************************************************************************************************
	// DATA STEPS
	// ******************************************************************************************************************************************************
	public void select(DataElementKey data_element_key, PageElementKey page_element_key) {
		String text = data_with_key(data_element_key);
		select(text, page_element_key);
	}
	
	public void enter(DataElementKey data_element_key, PageElementKey page_element_key) {
		String text = data_with_key(data_element_key);
		enter(text, page_element_key);
	}
	
	public void append(DataElementKey data_element_key, PageElementKey page_element_key) {
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
}