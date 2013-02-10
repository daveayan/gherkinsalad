package daveayan.gherkinsalad.components;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.core.Element;

public class DraggableTest extends BaseTest {
	@Test
	public void test_chrome() {
		if(is_chrome_disabled()) return;
		super.launch_browser("chrome");
		super.goto_test_page("draggable-test.html");
		Element the_box = findElement(By.id("draggable"));
		the_box.move_by(10, 10);
		the_box.move_to(100, 100);
		the_box.move_by(-20, -20);
		the_box.move_to(-70, -70);
	}
	
	@Test
	public void test_firefox() {
		if(is_firefox_disabled()) return;
		super.launch_browser("firefox");
		super.goto_test_page("draggable-test.html");
		Element the_box = findElement(By.id("draggable"));
		the_box.move_by(10, 10);
		the_box.move_to(100, 100);
		the_box.move_by(-20, -20);
		the_box.move_to(-70, -70);
	}
}