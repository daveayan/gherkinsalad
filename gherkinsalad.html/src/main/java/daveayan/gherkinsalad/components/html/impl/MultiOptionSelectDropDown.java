package daveayan.gherkinsalad.components.html.impl;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Elements;
import daveayan.gherkinsalad.components.html.MultiOptionSelectable;

public class MultiOptionSelectDropDown extends Component implements MultiOptionSelectable {
	
	public static MultiOptionSelectDropDown find(By locator) {
		MultiOptionSelectDropDown d = new MultiOptionSelectDropDown();
		d.found(locator);
		return d;
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
	
	public Strings get_selected_options() {
		Elements options = root_element().findElements(By.tagName("option"));
		Elements selected_options = options.findElementsThatMatch(new Predicate<Element>() {
			public boolean apply(Element input) {
				String selected = input.getAttribute("selected");
				if(StringUtils.isNotBlank(selected)) {
					return selected.equals("selected") || selected.equals("true");
				}
				return false;
			}
		});
		return selected_options.toStrings();
	}

	public void should_have_these_options_selected(String... options) {
		Strings selected_options = get_selected_options();
		Strings selected_options_not_found = Strings.new_instance();
		for(String option: options) {
			if(selected_options.does_not_have(option)) {
				selected_options_not_found.add(option);
			}
		}
		
		if(selected_options_not_found.is_empty()) {
			action("Verified that the selected options in " + this + " are " + Strings.instance_from(options).toString());
		} else {
			error("Expected dropdown " + this + " to have selected options " + Strings.instance_from(options).toString() +", did not find these selected " + selected_options_not_found);
		}
	}

	public void should_not_have_these_options_selected(String... options) {
		Strings selected_options = get_selected_options();
		Strings selected_options_found = Strings.new_instance();
		for(String option: options) {
			if(selected_options.has(option)) {
				selected_options_found.add(option);
			}
		}
		
		if(selected_options_found.is_empty()) {
			action("Verified that the selected options in " + this + " are NOT " + Strings.instance_from(options).toString());
		} else {
			error("Expected dropdown " + this + " to NOT have selected options " + Strings.instance_from(options).toString() +", found these selected " + selected_options_found);
		}
	}

}