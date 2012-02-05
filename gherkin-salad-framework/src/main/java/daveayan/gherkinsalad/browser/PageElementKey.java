package daveayan.gherkinsalad.browser;

import org.apache.commons.lang.StringUtils;

public class PageElementKey {
	private String role, component_name, element_name;
	
	public static PageElementKey instanceForAnyRoleComponentElement() {
		return newInstance(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY);
	}
	
	public static PageElementKey newInstance(String role, String component_name, String element_name) {
		PageElementKey key = new PageElementKey();
		key.role = role;
		key.component_name = component_name;
		key.element_name = element_name;
		return key;
	}
	
	public String key() {
		return create_key(role, component_name, element_name);
	}
	
	public String key_for_any_role() {
		return create_key(StringUtils.EMPTY, component_name, element_name);
	}
	
	public String element_name() {
		return element_name;
	}
	
	private String create_key(String role, String component_name, String element_name) {
		if(StringUtils.isBlank(role)) role = "*";
		if(StringUtils.isBlank(component_name)) component_name = "Page";
		if(StringUtils.isBlank(element_name)) element_name = "*";
		String key = role.trim() + "," + component_name.trim() + "," + element_name.trim();
		return key;
	}
	
	public PageElementKey on_element(String element_name) {
		this.element_name = element_name;
		return this;
	}
	
	public PageElementKey on_component(String component_name) {
		this.component_name = component_name;
		return this;
	}
	
	public PageElementKey for_role(String role) {
		this.role = role;
		return this;
	}
	
	public String toString() {
		return "PageElement with key '" + key() + "'";
	}
	
	private PageElementKey() {}
}