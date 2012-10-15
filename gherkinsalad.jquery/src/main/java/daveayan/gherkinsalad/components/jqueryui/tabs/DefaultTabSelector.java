package daveayan.gherkinsalad.components.jqueryui.tabs;

import org.openqa.selenium.By;

import com.google.common.base.Predicate;

import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.components.Elements;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class DefaultTabSelector extends BaseBrowserElement implements Tabs {

	private DefaultTabSelector() {}
	
	public static Tabs find(By root) {
		DefaultTabSelector t = new DefaultTabSelector();
		t.found(root);
		return t;
	}
	
	public void select_tab(final String tab_name) {
		Element tab = find_tab_li(tab_name);
		if(tab_is_selected(tab)) {
			info("Tab '" + tab_name + "' is already selected.");
		} else {
			action("Selecting tab '" + tab_name + "', element '" + tab +"'");
			tab.findElement(By.tagName("a")).click();
		}
		takeScreenshot();
	}

	public void selected_tab_should_be(String expected_selected_tab) {
		Element tab = find_tab_li(expected_selected_tab);
		if(tab_is_selected(tab)) {
			action("Verified that tab '" + expected_selected_tab +"' is selected");
		} else {
			error("Expected tab '" + expected_selected_tab + "' to be selected, it is not");
		}
		takeScreenshot();
	}
	
	public void selected_tab_should_not_be(String expected_selected_tab) {
		Element tab = find_tab_li(expected_selected_tab);
		if(! tab_is_selected(tab)) {
			action("Verified that tab '" + expected_selected_tab +"' is not selected");
		} else {
			error("Expected tab '" + expected_selected_tab + "' to be not selected, it is");
		}
		takeScreenshot();
	}
	
	public void remove_tab(String tab_to_remove) {
		Element tab = find_tab_li(tab_to_remove);
		Element tab_close_icon = tab.findElement(By.className("ui-icon-close"));
		if(tab_close_icon.is_null()) {
			error("Tab '" + tab_to_remove +"' does not have the icon to close it. Cannot remove");
		} else {
			tab_close_icon.click();
			action("Tab '" + tab_to_remove + "' removed.");
		}
		takeScreenshot();
	}
	
	private boolean tab_is_selected(Element tab) {
		return tab.getCssValue("aria-selected").contains("true");
	}
	
	private Element find_tab_li(final String tab_name) {
		Elements tabs = root_element().findElements(By.tagName("li"));
		Element tab = tabs.findFirstElementThatMatches(new Predicate<Element>() {
			public boolean apply(Element arg0) {
				return arg0.is(tab_name);
			}
		});
		return tab;
	}

}