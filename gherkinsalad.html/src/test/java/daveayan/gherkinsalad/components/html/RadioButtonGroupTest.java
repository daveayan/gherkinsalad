package daveayan.gherkinsalad.components.html;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.impl.RadioButtonGroup;

public class RadioButtonGroupTest extends BaseTest {
	@Test public void test_enabled_radiobuttongroup_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_enabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_disabled_radiobuttongroup_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_disabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_hidden_radiobuttongroup_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_hidden_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_enabled_radiobuttongroup_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_enabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_disabled_radiobuttongroup_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_disabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_hidden_radiobuttongroup_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_hidden_checkboxgroup();
		super.close_browser();
	}
	
	private void test_hidden_checkboxgroup() {
		SingleOptionSelectable radiobuttongroup = RadioButtonGroup.find(By.name("hidden_preselected_radio"));
		
		Assert.assertTrue(radiobuttongroup.isNotDisplayed());
		Assert.assertFalse(radiobuttongroup.isDisabled());
		Assert.assertTrue(radiobuttongroup.is_not_null());
		Assert.assertFalse(radiobuttongroup.isDisplayed());
		Assert.assertTrue(radiobuttongroup.isEnabled());
		Assert.assertFalse(radiobuttongroup.is_null());
	}
	
	private void test_disabled_checkboxgroup() {
		SingleOptionSelectable radiobuttongroup = RadioButtonGroup.find(By.name("disabled_preselected_radio"));
		
		Assert.assertTrue(radiobuttongroup.isDisplayed());
		Assert.assertTrue(radiobuttongroup.isDisabled());
		Assert.assertTrue(radiobuttongroup.is_not_null());
		Assert.assertFalse(radiobuttongroup.isNotDisplayed());
		Assert.assertFalse(radiobuttongroup.isEnabled());
		Assert.assertFalse(radiobuttongroup.is_null());
		
		Assert.assertEquals("'Bike, Car'", radiobuttongroup.get_all_options().toString());
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option_code());
		Assert.assertEquals("I have a car", radiobuttongroup.get_selected_option_text());
		
		radiobuttongroup.select_option_if_enabled("I have a bike");
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option_code());
		Assert.assertEquals("I have a car", radiobuttongroup.get_selected_option_text());
		
		radiobuttongroup.select_code_if_enabled("Bike");
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option_code());
		Assert.assertEquals("I have a car", radiobuttongroup.get_selected_option_text());
	}
	
	private void test_enabled_checkboxgroup() {
		SingleOptionSelectable radiobuttongroup = RadioButtonGroup.find(By.name("enabled_nonselected_radio"));
		
		Assert.assertTrue(radiobuttongroup.isDisplayed());
		Assert.assertTrue(radiobuttongroup.isEnabled());
		Assert.assertTrue(radiobuttongroup.is_not_null());
		Assert.assertFalse(radiobuttongroup.isNotDisplayed());
		Assert.assertFalse(radiobuttongroup.isDisabled());
		Assert.assertFalse(radiobuttongroup.is_null());
		
		Assert.assertEquals("'Bike, Car'", radiobuttongroup.get_all_options().toString());
		Assert.assertEquals("", radiobuttongroup.get_selected_option_code().toString());
		Assert.assertEquals("", radiobuttongroup.get_selected_option_text());
		
		radiobuttongroup.select_option_if_enabled("I have a car");
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option_code().toString());
		Assert.assertEquals("I have a car", radiobuttongroup.get_selected_option_text());
		
		radiobuttongroup.select_code_if_enabled("Bike");
		Assert.assertEquals("Bike", radiobuttongroup.get_selected_option_code().toString());
		Assert.assertEquals("I have a bike", radiobuttongroup.get_selected_option_text());
		
		radiobuttongroup = RadioButtonGroup.find(By.name("enabled_preselected_radio"));
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option_code().toString());
		Assert.assertEquals("I have a car", radiobuttongroup.get_selected_option_text());
	}
	
	@After public void teardown() {
		super.close_browser();
	}
}