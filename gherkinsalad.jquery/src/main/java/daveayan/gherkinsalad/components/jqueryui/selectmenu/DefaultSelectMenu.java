package daveayan.gherkinsalad.components.jqueryui.selectmenu;

import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Elements;
import daveayan.gherkinsalad.components.html.SingleOptionSelectable;
import daveayan.gherkinsalad.components.html.impl.SingleOptionSelectableDropDown;

public class DefaultSelectMenu extends SingleOptionSelectableDropDown implements SingleOptionSelectable {

	public static DefaultSelectMenu find(By locator) {
		DefaultSelectMenu m = new DefaultSelectMenu();
		m.found(locator);
		return m;
	}
	
	@SuppressWarnings("unchecked")
	public Strings get_all_options() {
		List<String> options = ListUtils.EMPTY_LIST;
		if(isEnabled()) {
			click_if_enabled();
			Element ul = find_ul_element();
			Elements li_a_s = ul.findElements(By.tagName("a"));
			options = li_a_s.asString();
			click_if_enabled();
		}
		return Strings.instance_from(options);
	}
	
	public String get_selected_option() {
		String option = StringUtils.EMPTY;
		if(isEnabled()) {
			click_if_enabled();
			Element ul = find_ul_element();
			Elements li_s = ul.findElements(By.tagName("a"));
			
	    Element selected_element = li_s.findFirstElementThatMatches(new Predicate<Element>() {
				public boolean apply(Element li_a) {
					String value = li_a.getAttribute("aria-selected");
					if(StringUtils.isBlank(value)) {
						return false;
					}
					return Boolean.parseBoolean(value);
				}
	    });
	    if(selected_element.is_not_null()) {
	    	option = selected_element.getText();
	    }
	    click_if_enabled();
		}
    return option;
	}

	public void select_code_if_enabled(String arg0) {
		
	}

	public void select_option_if_enabled(String option) {
		if(isEnabled()) {
			click_if_enabled();
			Element ul = find_ul_element();
			Element element_to_select = ul.findElement(By.partialLinkText(option));
			element_to_select.click();
		}
	}
	
	public boolean isEnabled() {
		if(this.isDisplayed()) {
			Element a_link = root_element();
			return ! StringUtils.equalsIgnoreCase(a_link.getAttribute("aria-disabled"), "true");
		}
		return false;
	}
	
	private Element find_ul_element() {
		Element a_link = root_element();
		String a_link_id = a_link.getAttribute("id");
		String ul_id = a_link_id.split("-button")[0] + "-menu";
		Element ul = findElement(By.id(ul_id));
		return ul;
	}
	
	private void click_if_enabled() {
		if(isEnabled()) {
			root_element().click();
			wait_for_seconds(1);
		}
	}
}