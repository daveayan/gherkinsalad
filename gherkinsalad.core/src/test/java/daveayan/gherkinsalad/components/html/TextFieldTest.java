package daveayan.gherkinsalad.components.html;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.impl.TextField;

public class TextFieldTest extends BaseTest {
	@Test public void test_enabled_textfield_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_enabled_textfield();
		super.close_browser();
	}
	
	@Test public void test_enabled_textfield_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_enabled_textfield();
		super.close_browser();
	}
	
	@Test public void test_disabled_textfield_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_disabled_textfield();
		super.close_browser();
	}
	
	@Test public void test_disabled_textfield_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_disabled_textfield();
		super.close_browser();
	}
	
	@Test public void test_hidden_textfield_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("html-test.html");
		test_hidden_textfield();
		super.close_browser();
	}
	
	@Test public void test_hidden_textfield_chrome() {
		super.launch_browser("chrome");
		super.goto_test_page("html-test.html");
		test_hidden_textfield();
		super.close_browser();
	}
	
	private void test_hidden_textfield() {
		TextEnterable tf = null;
		tf = TextField.find(By.id("hidden_filled_textfield"));
		
		Assert.assertTrue(tf.isNotDisplayed());
		Assert.assertTrue(tf.isEnabled());
		Assert.assertTrue(tf.is_not_null());
		Assert.assertFalse(tf.isDisplayed());
		Assert.assertFalse(tf.isDisabled());
		Assert.assertFalse(tf.is_null());
		
		Assert.assertEquals("NOT_DISPLAYED", tf.getText());
		
		tf.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("NOT_DISPLAYED", tf.getText());
		
		tf.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("NOT_DISPLAYED", tf.getText());
		
		tf.enter_text_if_enabled("");
		Assert.assertEquals("NOT_DISPLAYED", tf.getText());
	}
	
	private void test_enabled_textfield() {
		TextEnterable tf = null;
		tf = TextField.find(By.id("enabled_blank_textfield"));
		
		Assert.assertTrue(tf.isDisplayed());
		Assert.assertTrue(tf.isEnabled());
		Assert.assertTrue(tf.is_not_null());
		Assert.assertFalse(tf.isNotDisplayed());
		Assert.assertFalse(tf.isDisabled());
		Assert.assertFalse(tf.is_null());
		
		Assert.assertEquals("", tf.getText());
		
		tf.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("QWERTY", tf.getText());
		
		tf.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("_ASDFGHQWERTY", tf.getText());
		
		tf.enter_text_if_enabled("");
		Assert.assertEquals("", tf.getText());
		
		tf = TextField.find(By.id("enabled_filled_textfield"));
		
		Assert.assertTrue(tf.isDisplayed());
		Assert.assertTrue(tf.isEnabled());
		Assert.assertTrue(tf.is_not_null());
		Assert.assertFalse(tf.isNotDisplayed());
		Assert.assertFalse(tf.isDisabled());
		Assert.assertFalse(tf.is_null());
		
		Assert.assertEquals("AAA", tf.getText());
	}
	
	private void test_disabled_textfield() {
		TextEnterable tf = null;
		tf = TextField.find(By.id("disabled_blank_textfield"));
		
		Assert.assertTrue(tf.isDisplayed());
		Assert.assertTrue(tf.isDisabled());
		Assert.assertTrue(tf.is_not_null());
		Assert.assertFalse(tf.isNotDisplayed());
		Assert.assertFalse(tf.isEnabled());
		Assert.assertFalse(tf.is_null());
		
		Assert.assertEquals("", tf.getText());
		
		tf.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("", tf.getText());
		
		tf.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("", tf.getText());
		
		tf.enter_text_if_enabled("");
		Assert.assertEquals("", tf.getText());
		
		tf = TextField.find(By.id("disabled_filled_textfield"));
		
		Assert.assertTrue(tf.isDisplayed());
		Assert.assertTrue(tf.isDisabled());
		Assert.assertTrue(tf.is_not_null());
		Assert.assertFalse(tf.isNotDisplayed());
		Assert.assertFalse(tf.isEnabled());
		Assert.assertFalse(tf.is_null());
		
		Assert.assertEquals("AAA", tf.getText());
		
		tf.enter_text_if_enabled("QWERTY");
		Assert.assertEquals("AAA", tf.getText());
		
		tf.append_text_if_enabled("_ASDFGH");
		Assert.assertEquals("AAA", tf.getText());
		
		tf.enter_text_if_enabled("");
		Assert.assertEquals("AAA", tf.getText());
	}
}