package daveayan.gherkinsalad.components;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.core.Element;

public class JavascriptAlertBoxTest extends BaseTest {
	@Test public void test_alert_box_handling_in_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("Firefox");
		super.goto_test_page("test-javascript-alert-box.html");
		
		javascript_popup_click_ok();
		javascript_popup_dismiss();
		
		Element link = findElement(By.linkText("here"));
		link.click();
		javascript_popup_click_ok();
		
		link.click();
		javascript_popup_dismiss();
	}
	
	@Test public void test_alert_box_handling_in_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("Chrome");
		super.goto_test_page("test-javascript-alert-box.html");
		
		javascript_popup_click_ok();
		javascript_popup_dismiss();
		
		Element link = findElement(By.linkText("here"));
		link.click();
		javascript_popup_click_ok();
		
		link.click();
		javascript_popup_dismiss();
	}
}