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
	
	public void should_have_all_these(String... options) {
		// TODO Auto-generated method stub
		
	}

	public void should_have_any_of_these(String... options) {
		// TODO Auto-generated method stub
		
	}

	public void should_not_have_any_of_these(String... options) {
		// TODO Auto-generated method stub
		
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

	public void should_have_these_options_selected(Strings option) {
		// TODO Auto-generated method stub
		
	}

	public void should_have_these_codes_selected(Strings code) {
		// TODO Auto-generated method stub
		
	}

	public void should_not_have_these_options_selected(Strings option) {
		// TODO Auto-generated method stub
		
	}

	public void should_not_have_these_codes_selected(Strings code) {
		// TODO Auto-generated method stub
		
	}
}