package daveayan.gherkinsalad.components.html;

import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;

import com.google.common.base.Predicate;

import daveayan.gherkinsalad.Strings;
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

	public void should_have_options(String... expected_strings) {
		Strings options = Strings.instance_from(get_all_options());
		Strings strings_not_present = options
				.should_have_all_these_strings(expected_strings);
		strings_not_present.should_be_empty();
	}

	@SuppressWarnings("unchecked")
	public List<String> get_all_options() {
		List<String> option_strings = ListUtils.EMPTY_LIST;
		if (isEnabled()) {
			Elements options = root_element().findElements(By.tagName("option"));
			option_strings = options.asString();
		}
		return option_strings;
	}

	public String get_selected_option() {
		if (isEnabled()) {
			Elements options = root_element().findElements(By.tagName("option"));
			Element selected_option = options.findFirstElementThatMatches(new Predicate<Element>() {
				public boolean apply(Element input) {
					String selected = input.getAttribute("selected");
					if(StringUtils.isNotBlank(selected)) {
						return selected.equals("selected");
					}
					return false;
				}
			});
			return selected_option.getText();
		}
		return StringUtils.EMPTY;
	}
}