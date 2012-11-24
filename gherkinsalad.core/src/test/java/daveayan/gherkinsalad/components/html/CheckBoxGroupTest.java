package daveayan.gherkinsalad.components.html;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.Selectable;

public class CheckBoxGroupTest extends BaseTest {
	@Test public void test_enabled_checkboxgroup_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_enabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_disabled_checkboxgroup_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_disabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_hidden_checkboxgroup_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_hidden_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_enabled_checkboxgroup_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_enabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_disabled_checkboxgroup_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_disabled_checkboxgroup();
		super.close_browser();
	}
	
	@Test public void test_hidden_checkboxgroup_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_hidden_checkboxgroup();
		super.close_browser();
	}
	
	private void test_hidden_checkboxgroup() {
		Selectable checkboxgroup = CheckBoxGroup.find(By.name("hidden_preselected_checkbox"));
		
		Assert.assertTrue(checkboxgroup.isNotDisplayed());
		Assert.assertFalse(checkboxgroup.isDisabled());
		Assert.assertTrue(checkboxgroup.is_not_null());
		Assert.assertFalse(checkboxgroup.isDisplayed());
		Assert.assertTrue(checkboxgroup.isEnabled());
		Assert.assertFalse(checkboxgroup.is_null());
	}
	
	private void test_disabled_checkboxgroup() {
		Selectable checkboxgroup = CheckBoxGroup.find(By.name("disabled_preselected_checkbox"));
		
		Assert.assertTrue(checkboxgroup.isDisplayed());
		Assert.assertTrue(checkboxgroup.isDisabled());
		Assert.assertTrue(checkboxgroup.is_not_null());
		Assert.assertFalse(checkboxgroup.isNotDisplayed());
		Assert.assertFalse(checkboxgroup.isEnabled());
		Assert.assertFalse(checkboxgroup.is_null());
		
		Assert.assertEquals("'Bike, Car'", checkboxgroup.get_all_options().toString());
		Assert.assertEquals("'Car'", checkboxgroup.get_selected_option());
		
		checkboxgroup.select_option_if_enabled("Bike");
		Assert.assertEquals("'Car'", checkboxgroup.get_selected_option());
	}
	
	private void test_enabled_checkboxgroup() {
		Selectable checkboxgroup = CheckBoxGroup.find(By.name("enabled_nonselected_checkbox"));
		
		Assert.assertTrue(checkboxgroup.isDisplayed());
		Assert.assertTrue(checkboxgroup.isEnabled());
		Assert.assertTrue(checkboxgroup.is_not_null());
		Assert.assertFalse(checkboxgroup.isNotDisplayed());
		Assert.assertFalse(checkboxgroup.isDisabled());
		Assert.assertFalse(checkboxgroup.is_null());
		
		Assert.assertEquals("'Bike, Car'", checkboxgroup.get_all_options().toString());
		Assert.assertEquals("''", checkboxgroup.get_selected_option().toString());
		Assert.assertEquals("''", checkboxgroup.get_selected_options().toString());
		
		checkboxgroup.select_option_if_enabled("Car");
		Assert.assertEquals("'Car'", checkboxgroup.get_selected_option().toString());
		Assert.assertEquals("'Car'", checkboxgroup.get_selected_options().toString());
		
		checkboxgroup.select_code_if_enabled("Bike");
		Assert.assertEquals("'Bike, Car'", checkboxgroup.get_selected_option().toString());
		Assert.assertEquals("'Bike, Car'", checkboxgroup.get_selected_options().toString());
		
		checkboxgroup = CheckBoxGroup.find(By.name("enabled_preselected_checkbox"));
		Assert.assertEquals("'Car'", checkboxgroup.get_selected_option().toString());
		Assert.assertEquals("'Car'", checkboxgroup.get_selected_options().toString());
	}
	
	@After public void teardown() {
		super.close_browser();
	}
}