package daveayan.gherkinsalad.components.jqueryui.selectmenu;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.SingleOptionSelectable;

public class DefaultSelectMenuTest extends BaseTest {
	@Test public void test_firefox() {
		super.launch_browser("firefox");
		goto_url("http://view.jqueryui.com/selectmenu/demos/selectmenu/default.html");
		test_select_menu();
	}
	
	@Test public void test_chrome() {
		super.launch_browser("chrome");
		goto_url("http://view.jqueryui.com/selectmenu/demos/selectmenu/default.html");
		test_select_menu();
	}
	
	private void test_select_menu() {
		SingleOptionSelectable jqueryui_selectmenu = null;
		String selected_option = null;
		
		jqueryui_selectmenu = DefaultSelectMenu.find(By.id("speed-button"));
		jqueryui_selectmenu.should_have_all_these("Slower", "Medium", "Slow", "Fast", "Faster");
		selected_option = jqueryui_selectmenu.get_selected_option_text();
		Assert.assertEquals("Expected option 'Medium' to be selected, found '" + selected_option + "'", "Medium", selected_option);
		jqueryui_selectmenu.select_option_if_enabled("Slower");
		selected_option = jqueryui_selectmenu.get_selected_option_text();
		Assert.assertEquals("Expected option 'Slower' to be selected, found '" + selected_option + "'", "Slower", selected_option);
	}
	
	@After public void teardown() {
		super.close_browser();
	}
}