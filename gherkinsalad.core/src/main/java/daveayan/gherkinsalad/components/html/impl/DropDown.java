package daveayan.gherkinsalad.components.html.impl;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Elements;
import daveayan.gherkinsalad.components.html.Selectable;

public class DropDown extends Component implements Selectable {
	public static DropDown find(By locator) {
		DropDown d = new DropDown();
		d.found(locator);
		return d;
	}

	public DropDown name(String name) {
		super.name(name);
		return this;
	}

	public void select_option_if_enabled(String option) {
		Element element = root_element();
		if (this.isEnabled()) {
			Elements options = element.findElements(By.tagName("option"));
			Element option_to_select = options.findFirstElementWithText(option);
			option_to_select.click();
		}
	}

	public void select_code_if_enabled(String code) {
		Element element = root_element();
		if (this.isEnabled()) {
			Elements options = element.findElements(By.tagName("option"));
			for (Element o : options._nativeList()) {
				if (StringUtils.equals(o.getAttribute("value"), code)) {
					o.click();
					break;
				}
			}
		}
	}

	public void should_have_all_these(String... expected_options) {
		Strings option_strings = get_all_options();
		Strings strings_not_present = option_strings.has_all_these(expected_options);
		if(strings_not_present.is_empty()) {
			action("Verified dropdown " + this + " has all these options " + Strings.instance_from(expected_options).toString());
		} else {
			error("Expected dropdown " + this + " to have all these options " + Strings.instance_from(expected_options).toString() + " did not find these " + strings_not_present);
		}
	}
	
	public void should_have_any_of_these(String... expected_options) {
		Strings option_strings = get_all_options();
		Strings strings_present = option_strings.has_any_of_these(expected_options);
		if(strings_present.is_not_empty()) {
			action("Verified dropdown " + this + " has these options " + Strings.instance_from(expected_options).toString());
		} else {
			error("Expected dropdown " + this + " to have any of these options " + Strings.instance_from(expected_options).toString() + " did not find these " + strings_present);
		}
	}
	
	public void should_not_have_any_of_these(String... expected_options) {
		Strings option_strings = get_all_options();
		Strings strings_present = option_strings.has_any_of_these(expected_options);
		if(strings_present.is_empty()) {
			action("Verified dropdown " + this + " has none of these options " + Strings.instance_from(expected_options).toString());
		} else {
			error("Expected dropdown " + this + " to have none of these options " + Strings.instance_from(expected_options).toString() + " found these " + strings_present);
		}
	}

	public Strings get_all_options() {
		Strings option_strings = Strings.new_instance();
		Elements options = root_element().findElements(By.tagName("option"));
		option_strings = Strings.instance_from(options.asString());
		return option_strings;
	}

	public String get_selected_option() {
		Elements options = root_element().findElements(By.tagName("option"));
		Element selected_option = options.findFirstElementThatMatches(new Predicate<Element>() {
			public boolean apply(Element input) {
				String selected = input.getAttribute("selected");
				if(StringUtils.isNotBlank(selected)) {
					return selected.equals("selected") || selected.equals("true");
				}
				return false;
			}
		});
		return selected_option.getText();
	}
	
	public Strings get_selected_options() {
		return Strings.instance_from(get_selected_option());
	}

	public void should_have_this_option_selected(String option) {
		// TODO Auto-generated method stub
		
	}

	public void should_have_this_code_selected(String code) {
		// TODO Auto-generated method stub
		
	}

	public void should_not_have_this_option_selected(String option) {
		// TODO Auto-generated method stub
		
	}

	public void should_not_have_this_code_selected(String code) {
		// TODO Auto-generated method stub
		
	}
}