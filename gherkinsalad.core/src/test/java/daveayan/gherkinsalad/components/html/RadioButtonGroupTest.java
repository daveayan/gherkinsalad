package daveayan.gherkinsalad.components.html;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.impl.RadioButtonGroup;

public class RadioButtonGroupTest extends BaseTest {
	@Test public void test_enabled_radiobuttongroup_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_enabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_disabled_radiobuttongroup_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_disabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_hidden_radiobuttongroup_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_hidden_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_enabled_radiobuttongroup_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_enabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_disabled_radiobuttongroup_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_disabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_hidden_radiobuttongroup_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_hidden_checkboxgroup();
		super.close_browser();
	}
	
	private void test_hidden_checkboxgroup() {
		Selectable radiobuttongroup = RadioButtonGroup.find(By.name("hidden_preselected_radio"));
		
		Assert.assertTrue(radiobuttongroup.isNotDisplayed());
		Assert.assertFalse(radiobuttongroup.isDisabled());
		Assert.assertTrue(radiobuttongroup.is_not_null());
		Assert.assertFalse(radiobuttongroup.isDisplayed());
		Assert.assertTrue(radiobuttongroup.isEnabled());
		Assert.assertFalse(radiobuttongroup.is_null());
	}
	
	private void test_disabled_checkboxgroup() {
		Selectable radiobuttongroup = RadioButtonGroup.find(By.name("disabled_preselected_radio"));
		
		Assert.assertTrue(radiobuttongroup.isDisplayed());
		Assert.assertTrue(radiobuttongroup.isDisabled());
		Assert.assertTrue(radiobuttongroup.is_not_null());
		Assert.assertFalse(radiobuttongroup.isNotDisplayed());
		Assert.assertFalse(radiobuttongroup.isEnabled());
		Assert.assertFalse(radiobuttongroup.is_null());
		
		Assert.assertEquals("'Bike, Car'", radiobuttongroup.get_all_options().toString());
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option());
		
		radiobuttongroup.select_option_if_enabled("Bike");
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option());
	}
	
	private void test_enabled_checkboxgroup() {
		Selectable radiobuttongroup = RadioButtonGroup.find(By.name("enabled_nonselected_radio"));
		
		Assert.assertTrue(radiobuttongroup.isDisplayed());
		Assert.assertTrue(radiobuttongroup.isEnabled());
		Assert.assertTrue(radiobuttongroup.is_not_null());
		Assert.assertFalse(radiobuttongroup.isNotDisplayed());
		Assert.assertFalse(radiobuttongroup.isDisabled());
		Assert.assertFalse(radiobuttongroup.is_null());
		
		Assert.assertEquals("'Bike, Car'", radiobuttongroup.get_all_options().toString());
		Assert.assertEquals("", radiobuttongroup.get_selected_option().toString());
		Assert.assertEquals("", radiobuttongroup.get_selected_option().toString());
		
		radiobuttongroup.select_option_if_enabled("Car");
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option().toString());
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option().toString());
		
		radiobuttongroup.select_code_if_enabled("Bike");
		Assert.assertEquals("Bike", radiobuttongroup.get_selected_option().toString());
		Assert.assertEquals("Bike", radiobuttongroup.get_selected_option().toString());
		
		radiobuttongroup = RadioButtonGroup.find(By.name("enabled_preselected_radio"));
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option().toString());
		Assert.assertEquals("Car", radiobuttongroup.get_selected_option().toString());
	}
	
	@After public void teardown() {
		super.close_browser();
	}
}