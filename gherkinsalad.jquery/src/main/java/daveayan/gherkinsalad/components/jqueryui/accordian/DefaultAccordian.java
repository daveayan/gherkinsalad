package daveayan.gherkinsalad.components.jqueryui.accordian;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class DefaultAccordian extends BaseBrowserElement implements Accordian {
private DefaultAccordian() {}
	
	public static Accordian find(By root) {
		DefaultAccordian t = new DefaultAccordian();
		t.found(root);
		return t;
	}
	
	public void select_section(String section_name) {
		
	}

	public void selected_section_should_be(String expected_selected_section) {
		
	}

	public void selected_section_should_not_be(String expected_selected_section) {
		
	}
}