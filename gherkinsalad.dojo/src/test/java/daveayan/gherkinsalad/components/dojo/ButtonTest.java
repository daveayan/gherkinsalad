package daveayan.gherkinsalad.components.dojo;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.Clickable;

public class ButtonTest extends BaseTest {
	@Test public void firefox_test() {
		super.launch_browser("firefox");
		super.goto_url("http://download.dojotoolkit.org/release-1.6.1/dojo-release-1.6.1/dijit/themes/themeTester.html");
		
//		test_em();
	}
	
	@Test public void chrome_test() {
//		super.launch_browser("chrome");
//		super.goto_url("http://download.dojotoolkit.org/release-1.6.1/dojo-release-1.6.1/dijit/themes/themeTester.html");
//		
//		test_em();
	}
	
	private void test_em() {
		Clickable enabled_simple = Button.find(By.id("dijit_form_Button_1"));
		Assert.assertTrue(enabled_simple.isDisplayed());
		Assert.assertTrue(enabled_simple.isEnabled());
		
		Clickable enabled_dropdown_button = Button.find(By.id("dijit_form_DropDownButton_1"));
		Assert.assertTrue(enabled_dropdown_button.isDisplayed());
		Assert.assertTrue(enabled_dropdown_button.isEnabled());
		
		Clickable enabled_combo_button = Button.find(By.id("dijit_form_ComboButton_0_button"));
		Assert.assertTrue(enabled_combo_button.isDisplayed());
		Assert.assertTrue(enabled_combo_button.isEnabled());
		
		Clickable enabled_toggle_button = Button.find(By.id("dijit_form_ToggleButton_0"));
		Assert.assertTrue(enabled_toggle_button.isDisplayed());
		Assert.assertTrue(enabled_toggle_button.isEnabled());
		
		Clickable disabled_simple = Button.find(By.id("dijit_form_Button_2"));
		Assert.assertTrue(disabled_simple.isDisplayed());
		Assert.assertTrue(disabled_simple.isDisabled());
		
		Clickable disabled_dropdown_button = Button.find(By.id("dijit_form_DropDownButton_2"));
		Assert.assertTrue(disabled_dropdown_button.isDisplayed());
		Assert.assertTrue(disabled_dropdown_button.isDisabled());
		
		Clickable disabled_combo_button = Button.find(By.id("dijit_form_ComboButton_1_button"));
		Assert.assertTrue(disabled_combo_button.isDisplayed());
		Assert.assertTrue(disabled_combo_button.isDisabled());
		
		Clickable disabled_toggle_button = Button.find(By.id("dijit_form_ToggleButton_1"));
		Assert.assertTrue(disabled_toggle_button.isDisplayed());
		Assert.assertTrue(disabled_toggle_button.isDisabled());
	}
}