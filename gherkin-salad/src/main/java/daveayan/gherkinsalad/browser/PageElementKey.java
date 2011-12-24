package daveayan.gherkinsalad.browser;

import org.apache.commons.lang.StringUtils;

public class PageElementKey {
	private String key;
	private String element_name;
	
	public static PageElementKey newInstance(String role, String component_name, String element_name) {
		PageElementKey key = new PageElementKey();
		key.element_name = element_name;
		key.create_key(role, component_name, element_name);
		return key;
	}
	
	public String key() {
		return key;
	}
	
	public String element_name() {
		return element_name;
	}
	
	private void create_key(String role, String component_name, String element_name) {
		if(StringUtils.isBlank(role)) role = "*";
		if(StringUtils.isBlank(component_name)) component_name = "*";
		if(StringUtils.isBlank(element_name)) element_name = "*";
		key = role.trim() + "," + component_name.trim() + "," + element_name.trim();
	}
	
	public String toString() {
		return "PageElement with key '" + key + "'";
	}
	
	private PageElementKey() {}
}