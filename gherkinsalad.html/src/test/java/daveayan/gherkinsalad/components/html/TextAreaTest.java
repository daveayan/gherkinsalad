package daveayan.gherkinsalad.components.html;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.impl.TextArea;

public class TextAreaTest extends BaseTest {
	@Test public void test_enabled_textarea_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_enabled_textarea();
		super.close_browser();
	}
	
	@Test public void test_enabled_textarea_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_enabled_textarea();
		super.close_browser();
	}
	
	@Test public void test_disabled_textarea_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_disabled_textarea();
		super.close_browser();
	}
	
	@Test public void test_disabled_textarea_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_disabled_textarea();
		super.close_browser();
	}
	
	@Test public void test_hidden_textarea_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_hidden_textarea();
		super.close_browser();
	}
	
	@Test public void test_hidden_textarea_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_hidden_textarea();
		super.close_browser();
	}
	
	private void test_hidden_textarea() {
		TextEnterable ta = null;
		ta = TextArea.find(By.id("hidden_filled_textarea"));
		
		Assert.assertTrue(ta.isNotDisplayed());
		Assert.assertTrue(ta.isDisabled());
		Assert.assertTrue(ta.is_not_null());
		Assert.assertFalse(ta.isDisplayed());
		Assert.assertFalse(ta.isEnabled());
		Assert.assertFalse(ta.is_null());
		
		Assert.assertEquals("NOT_DISPLAYED", ta.getText());
		
		ta.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("NOT_DISPLAYED", ta.getText());
		
		ta.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("NOT_DISPLAYED", ta.getText());
		
		ta.enter_text_if_enabled("");
		Assert.assertEquals("NOT_DISPLAYED", ta.getText());
	}
	
	private void test_enabled_textarea() {
		TextEnterable ta = null;
		ta = TextArea.find(By.id("enabled_blank_textarea"));
		
		Assert.assertTrue(ta.isDisplayed());
		Assert.assertTrue(ta.isEnabled());
		Assert.assertTrue(ta.is_not_null());
		Assert.assertFalse(ta.isNotDisplayed());
		Assert.assertFalse(ta.isDisabled());
		Assert.assertFalse(ta.is_null());
		
		Assert.assertEquals("", ta.getText());
		
		ta.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("QWERTY", ta.getText());
		
		ta.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("_ASDFGHQWERTY", ta.getText());
		
		ta.enter_text_if_enabled("");
		Assert.assertEquals("", ta.getText());
		
		ta = TextArea.find(By.id("enabled_filled_textarea"));
		
		Assert.assertTrue(ta.isDisplayed());
		Assert.assertTrue(ta.isEnabled());
		Assert.assertTrue(ta.is_not_null());
		Assert.assertFalse(ta.isNotDisplayed());
		Assert.assertFalse(ta.isDisabled());
		Assert.assertFalse(ta.is_null());
		
		Assert.assertEquals("BBB", ta.getText());
	}
	
	private void test_disabled_textarea() {
		TextEnterable ta = null;
		ta = TextArea.find(By.id("disabled_blank_textarea"));
		
		Assert.assertTrue(ta.isDisplayed());
		Assert.assertTrue(ta.isDisabled());
		Assert.assertTrue(ta.is_not_null());
		Assert.assertFalse(ta.isNotDisplayed());
		Assert.assertFalse(ta.isEnabled());
		Assert.assertFalse(ta.is_null());
		
		Assert.assertEquals("", ta.getText());
		
		ta.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("", ta.getText());
		
		ta.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("", ta.getText());
		
		ta.enter_text_if_enabled("");
		Assert.assertEquals("", ta.getText());	
		
		ta = TextArea.find(By.id("disabled_filled_textarea"));
		
		Assert.assertTrue(ta.isDisplayed());
		Assert.assertTrue(ta.isDisabled());
		Assert.assertTrue(ta.is_not_null());
		Assert.assertFalse(ta.isNotDisplayed());
		Assert.assertFalse(ta.isEnabled());
		Assert.assertFalse(ta.is_null());
		
		Assert.assertEquals("BBB", ta.getText());
		
		ta.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("BBB", ta.getText());
		
		ta.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("BBB", ta.getText());
		
		ta.enter_text_if_enabled("");
		Assert.assertEquals("BBB", ta.getText());
	}
}