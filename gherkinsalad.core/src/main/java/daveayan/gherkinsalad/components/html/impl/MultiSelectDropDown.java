package daveayan.gherkinsalad.components.html.impl;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Elements;
import daveayan.gherkinsalad.components.html.MultiSelectable;

public class MultiSelectDropDown extends DropDown implements MultiSelectable {
	
	public static MultiSelectDropDown find(By locator) {
		MultiSelectDropDown d = new MultiSelectDropDown();
		d.found(locator);
		return d;
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