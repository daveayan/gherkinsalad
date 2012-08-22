package daveayan.gherkinsalad.components.html;

import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.components.Element;

public class RadioButton extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		Element element = root_element();
		return element.isEnabled();
	}

	public void click_if_enabled() {
		Element element = root_element();
		if(this.isEnabled()) {
			element.click();
		}
	}
	public void click_if_exists() {
		click_if_enabled();
	}

	public void click_if_exists_and_enabled() {
		// TODO Auto-generated method stub
		
	}
}