package daveayan.gherkinsalad.components.jqueryui.tabs;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;

public class DefaultTabSelectorTest extends BaseTest {
	@Test
	public void sample() {
		Tabs t = null;
		super.launch_browser("firefox");		
		
		goto_test_page("tabs-default.html");
		t = DefaultTabSelector.find(By.xpath("/html/body/div/ul"));
		verify_tabs(t);
		
		goto_test_page("tabs-vertical.html");
		t = DefaultTabSelector.find(By.xpath("/html/body/div/ul"));
		verify_tabs(t);
	}
	
	private void verify_tabs(Tabs t) {
		String tab1 = "Nunc tincidunt";
		String tab2 = "Proin dolor";
		String tab3 = "Aenean lacinia";
		
		t.remove_tab(tab1);
		t.remove_tab(tab2);
		t.remove_tab(tab3);
		
		t.select_tab(tab1);
		t.selected_tab_should_be(tab1);
		t.selected_tab_should_not_be(tab2);
		t.selected_tab_should_not_be(tab3);
		
		t.select_tab(tab2);
		t.selected_tab_should_not_be(tab1);
		t.selected_tab_should_be(tab2);
		t.selected_tab_should_not_be(tab3);
		
		t.select_tab(tab3);
		t.selected_tab_should_not_be(tab1);
		t.selected_tab_should_not_be(tab2);
		t.selected_tab_should_be(tab3);
	}
}