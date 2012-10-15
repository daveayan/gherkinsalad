package daveayan.gherkinsalad.components;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.Link;

public class JavascriptAlertBoxTest extends BaseTest {
	@Test public void test_alert_box_handling_in_firefox() {
		super.launch_browser("Firefox");
		super.goto_test_page("test-javascript-alert-box.html");
		
		javascript_popup_click_ok();
		javascript_popup_dismiss();
		
		Clickable link = Link.find(By.linkText("here"));
		link.click_if_enabled();
		javascript_popup_click_ok();
		
		link.click_if_enabled();
		javascript_popup_dismiss();
	}
	
	@Test public void test_alert_box_handling_in_chrome() {
		super.launch_browser("Chrome");
		super.goto_test_page("test-javascript-alert-box.html");
		
		javascript_popup_click_ok();
		javascript_popup_dismiss();
		
		Clickable link = Link.find(By.linkText("here"));
		link.click_if_enabled();
		javascript_popup_click_ok();
		
		link.click_if_enabled();
		javascript_popup_dismiss();
	}
}