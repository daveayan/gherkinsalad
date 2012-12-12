package daveayan.gherkinsalad.components.html.impl;

import org.openqa.selenium.By;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Elements;
import daveayan.gherkinsalad.components.html.MultiOptionSelectable;

public class CheckBoxGroup extends Component implements MultiOptionSelectable {

	public static CheckBoxGroup find(By locator) {
		CheckBoxGroup cbg = new CheckBoxGroup();
		cbg.found(locator);
		return cbg;
	}
	
	public void select_option_if_enabled(final String option) {
		select_code_if_enabled(option);
	}

	public void select_code_if_enabled(final String code) {
		Elements root_elements = root_elements();
		Element element_to_select = root_elements.findFirstElementThatMatches(new Predicate<Element>() {
			public boolean apply(Element input) {
				String value = input.getAttribute("value");
				if(value == null) return Boolean.FALSE;
				return value.trim().equals(code);
			}
		});
		element_to_select.click();
	}

	public Strings get_all_options() {
		Elements root_elements = root_elements();
		return toStrings(root_elements);
	}

	public String get_selected_option() {
		return get_selected_options().toString();
	}
	
	public Strings get_selected_options() {
		Elements root_elements = root_elements();
		Elements selected_elements = root_elements.findElementsThatMatch(new Predicate<Element>() {
			public boolean apply(Element input) {
				String checked = input.getAttribute("checked");
				if(checked == null) {
					return Boolean.FALSE;
				}
				return input.isSelected();
			}
		});
		return toStrings(selected_elements);
	}
	
	private Strings toStrings(Elements elements) {
		return elements.toStrings(new Function<Element, String>() {
			public String apply(Element input) {
				return input.getAttribute("value");
			}});
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