package daveayan.gherkinsalad.components.jqueryui.selectmenu;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.Selectable;
import daveayan.gherkinsalad.test.Assert;

public class DefaultSelectMenuTest extends BaseTest {
	@Test public void test_firefox() {
		super.launch_browser("firefox");
		goto_url("http://jquery-ui.googlecode.com/svn/branches/labs/selectmenu/index.html");
		test_select_menu();
	}
	
	private void test_select_menu() {
		Selectable jqueryui_selectmenu = null;
		String selected_option = null;
		
		jqueryui_selectmenu = DefaultSelectMenu.find(By.id("speedA-button"));
		jqueryui_selectmenu.should_have_options("Slower", "Medium", "Slow", "Fast", "Faster");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'Medium' to be selected, found '" + selected_option + "'", "Medium", selected_option);
		jqueryui_selectmenu.select_option_if_enabled("Slower");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'Slower' to be selected, found '" + selected_option + "'", "Slower", selected_option);
		
		jqueryui_selectmenu = DefaultSelectMenu.find(By.id("speedAa-button"));
		jqueryui_selectmenu.should_have_options("Slower", "Medium", "Slow", "Fast", "Faster");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'Medium' to be selected, found '" + selected_option + "'", "Medium", selected_option);
		jqueryui_selectmenu.select_option_if_enabled("Slower");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'Slower' to be selected, found '" + selected_option + "'", "Slower", selected_option);
		
		jqueryui_selectmenu = DefaultSelectMenu.find(By.id("speedC-button"));
		jqueryui_selectmenu.should_have_options("Slower", "Medium", "Slow", "Fast", "Faster");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'Medium' to be selected, found '" + selected_option + "'", "Medium", selected_option);
		jqueryui_selectmenu.select_option_if_enabled("Slower");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'Slower' to be selected, found '" + selected_option + "'", "Slower", selected_option);
		
		jqueryui_selectmenu = DefaultSelectMenu.find(By.id("filesC-button"));
		jqueryui_selectmenu.should_have_options("jQuery Logo", "jQuery UI Logo", "jQuery.js");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'jQuery UI Logo' to be selected, found '" + selected_option + "'", "jQuery UI Logo", selected_option);
		jqueryui_selectmenu.select_option_if_enabled("jQuery.js");
		selected_option = jqueryui_selectmenu.get_selected_option();
		Assert.assertEquals("Expected option 'jQuery.js' to be selected, found '" + selected_option + "'", "jQuery.js", selected_option);
	}
}