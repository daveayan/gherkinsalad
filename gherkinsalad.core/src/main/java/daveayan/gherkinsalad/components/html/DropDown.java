package daveayan.gherkinsalad.components.html;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.components.Elements;
import daveayan.gherkinsalad.components.Selectable;

public class DropDown extends BaseBrowserElement implements Selectable {
	
	public static DropDown find(By locator) {
		DropDown d = new DropDown();
		d.found(locator);
		return d;
	}
	
	public DropDown name(String name) {
		super.name(name);
		return this;
	}
	
	public boolean isEnabled() {
		Element element = root_element();
		return element.isEnabled();
	}

	public void select_option_if_enabled(String option) {
		Element element = root_element();
		if(this.isEnabled()) {
			Elements options = element.findElements(By.tagName("option"));
			Element option_to_select = options.findFirstElementWithText(option);
			option_to_select.click();
		}
	}
	
	public void select_code_if_enabled(String code) {
		Element element = root_element();
		if(this.isEnabled()) {
			Elements options = element.findElements(By.tagName("option"));
			for(Element o: options._nativeList()) {
				if(StringUtils.equals(o.getAttribute("value"), code)) {
					o.click();
					break;
				}
			}
		}
	}

	public void should_have_options(String... options) {
		throw new AssertionError("DropDown::has_options(" + options + ") is not implemented yet");
	}

	public List<String> get_all_options() {
		// TODO Auto-generated method stub
		return null;
	}

	public String get_selected_option() {
		// TODO Auto-generated method stub
		return null;
	}
}