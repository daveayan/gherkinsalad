package daveayan.gherkinsalad.components.html;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.impl.SingleOptionSelectableDropDown;

public class SingleOptionSelectableDropDownTest extends BaseTest {
	@Test public void test_enabled_dropdown_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_enabled_dropdown();
		super.close_browser();
	}
	
	@Test public void test_disabled_dropdown_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_disabled_dropdown();
		super.close_browser();
	}
	
	@Test public void test_hidden_dropdown_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_hidden_dropdown();
		super.close_browser();
	}
	
	@Test public void test_enabled_dropdown_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_enabled_dropdown();
		super.close_browser();
	}
	
	@Test public void test_disabled_dropdown_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_disabled_dropdown();
		super.close_browser();
	}
	
	@Test public void test_hidden_dropdown_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_hidden_dropdown();
		super.close_browser();
	}
	
	private void test_hidden_dropdown() {
		SingleOptionSelectable dropdown = SingleOptionSelectableDropDown.find(By.id("hidden-dropdown"));
		
		Assert.assertTrue(dropdown.isNotDisplayed());
		Assert.assertTrue(dropdown.isEnabled());
		Assert.assertTrue(dropdown.is_not_null());
		Assert.assertFalse(dropdown.isDisplayed());
		Assert.assertFalse(dropdown.isDisabled());
		Assert.assertFalse(dropdown.is_null());
	}
	
	private void test_disabled_dropdown() {
		SingleOptionSelectable dropdown = SingleOptionSelectableDropDown.find(By.id("disabled-dropdown"));
		
		Assert.assertTrue(dropdown.isDisplayed());
		Assert.assertTrue(dropdown.isDisabled());
		Assert.assertTrue(dropdown.is_not_null());
		Assert.assertFalse(dropdown.isNotDisplayed());
		Assert.assertFalse(dropdown.isEnabled());
		Assert.assertFalse(dropdown.is_null());
		
		Assert.assertEquals("', Volvo Option 2, Volvo, Saab, Mercedes, Audi'", dropdown.get_all_options().toString());
		Assert.assertEquals("Saab", dropdown.get_selected_option_text());
		
		dropdown.select_option_if_enabled("Volvo");
		Assert.assertEquals("Saab", dropdown.get_selected_option_text());
		
		dropdown.select_code_if_enabled("vo2");
		Assert.assertEquals("Saab", dropdown.get_selected_option_text());
	}
	
	private void test_enabled_dropdown() {
		SingleOptionSelectable dropdown = SingleOptionSelectableDropDown.find(By.id("enabled-dropdown"));
		
		Assert.assertTrue(dropdown.isDisplayed());
		Assert.assertTrue(dropdown.isEnabled());
		Assert.assertTrue(dropdown.is_not_null());
		Assert.assertFalse(dropdown.isNotDisplayed());
		Assert.assertFalse(dropdown.isDisabled());
		Assert.assertFalse(dropdown.is_null());
		
		Assert.assertEquals("', Volvo Option 2, Volvo, Saab, Mercedes, Audi'", dropdown.get_all_options().toString());
		Assert.assertEquals("", dropdown.get_selected_option_text());
		
		dropdown.select_option_if_enabled("Volvo");
		Assert.assertEquals("Volvo", dropdown.get_selected_option_text());
		
		dropdown.select_code_if_enabled("vo2");
		Assert.assertEquals("Volvo Option 2", dropdown.get_selected_option_text());
	}
	
	@After public void teardown() {
		super.close_browser();
	}
}