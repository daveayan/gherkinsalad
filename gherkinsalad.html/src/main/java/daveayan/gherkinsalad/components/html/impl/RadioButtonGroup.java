package daveayan.gherkinsalad.components.html.impl;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.Utils;
import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Elements;
import daveayan.gherkinsalad.components.html.SingleOptionSelectable;

public class RadioButtonGroup extends Component implements SingleOptionSelectable {
	public static RadioButtonGroup find(By locator) {
		RadioButtonGroup rbg = new RadioButtonGroup();
		rbg.found(locator);
		return rbg;
	}
	
	public RadioButtonGroup name(String name) {
		super.name(name);
		return this;
	}

	public void select_option_if_enabled(final String option) {
		Elements root_elements = root_elements();
		final Elements all_labels = findElements(By.tagName("label"));
		Element element_to_select = root_elements.findFirstElementThatMatches(new Predicate<Element>() {
			public boolean apply(Element input) {
				final String id = input.getAttribute("id");
				Element label_for_radio_button = all_labels.findFirstElementThatMatches(new Predicate<Element>() {
					public boolean apply(Element input) {
						String label_for = input.getAttribute("for");
						return StringUtils.equals(label_for, id);
					}
				});
				return label_for_radio_button.is(option);
			}
		});
		element_to_select.click();
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
		Elements root_elements = root_elements();
		return toStrings(root_elements);
	}

	public String get_selected_option_code() {
		Element selected_element = getSelectedElement();
		if(selected_element.is_null()) {
			return StringUtils.EMPTY;
		}
		return selected_element.getAttribute("value");
	}
	
	public String get_selected_option_text() {
		final Element selected_element = getSelectedElement();
		if(selected_element.is_null()) {
			return StringUtils.EMPTY;
		}
		final Elements all_labels = findElements(By.tagName("label"));
		Element selected_element_label = all_labels.findFirstElementThatMatches(new Predicate<Element>() {
			public boolean apply(Element input) {
				String label_for = input.getAttribute("for");
				return StringUtils.equals(label_for, selected_element.getAttribute("id"));
			}
		});
		if(selected_element_label.is_null()) {
			return StringUtils.EMPTY;
		}
		return selected_element_label.getText();
	}
	
	public Strings get_selected_options() {
		return Strings.instance_from(get_selected_option_text());
	}

	public void should_have_this_option_selected(String option) {
		String selected_option = get_selected_option_text();
		if(Utils.equals(selected_option, option)) {
			action("Verified that the selected option in " + this + " is '" + option + "'");
		} else {
			error("Expected " + this + " to have selected option '" + option +"', found these instead '" + selected_option + "'");
		}
	}

	public void should_not_have_this_option_selected(String option) {
		String selected_option = get_selected_option_text();
		if(Utils.not_equals(selected_option, option)) {
			action("Verified that the selected option in " + this + " is NOT '" + option + "'");
		} else {
			error("Expected " + this + " to NOT have selected option '" + option +"', found it selected instead");
		}
	}
	
	private Strings toStrings(Elements elements) {
		return elements.toStrings(new Function<Element, String>() {
			public String apply(Element input) {
				return input.getAttribute("value");
			}});
	}
	
	private Element getSelectedElement() {
		Elements root_elements = root_elements();
		Element selected_element = root_elements.findFirstElementThatMatches(new Predicate<Element>() {
			public boolean apply(Element input) {
				String checked = input.getAttribute("checked");
				if(checked == null) {
					return Boolean.FALSE;
				}
				return input.isSelected();
			}
		});
		return selected_element;
	}
}
