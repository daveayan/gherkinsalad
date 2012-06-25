package daveayan.gherkinsalad.components.builtins;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.Selectable;

public class DropDown extends BaseBrowserElement implements Selectable {
	
	public static DropDown find(By... locators) {
		DropDown d = new DropDown();
		d.found(locators);
		return d;
	}
	
	public boolean isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void select_option_if_enabled(String option) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			List<WebElement> options = element.findElements(By.tagName("option"));
			for(WebElement o: options) {
				if(StringUtils.equals(o.getText(), option)) {
					o.click();
					break;
				}
			}
		}
	}
	
	public void select_code_if_enabled(String code) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			List<WebElement> options = element.findElements(By.tagName("option"));
			for(WebElement o: options) {
				if(StringUtils.equals(o.getAttribute("value"), code)) {
					o.click();
					break;
				}
			}
		}
	}

	public void has_options(String[] options) {
		throw new AssertionError("DropDown::has_options(" + options + ") is not implemented yet");
	}
}