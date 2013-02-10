package daveayan.gherkinsalad.components;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.core.Element;

public class JavascriptNewWindowTest extends BaseTest {
	@Test public void test_new_named_window_with_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("test-javascript-new-windows.html");
		Element link = findElement(By.id("namedwindow"));
		link.click();
		
		System.out.println("Switching");
		switch_to_default_window();
		System.out.println("Switched");
	}
	
	@Test public void test_new_named_window_with_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("test-javascript-new-windows.html");
		Element link = findElement(By.id("namedwindow"));
		link.click();
		
		System.out.println("Switching");
		switch_to_default_window();
		System.out.println("Switched");
	}
}