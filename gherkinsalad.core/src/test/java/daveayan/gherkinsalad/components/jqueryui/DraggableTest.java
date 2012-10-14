package daveayan.gherkinsalad.components.jqueryui;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.BaseTest;
import daveayan.gherkinsalad.components.Element;

public class DraggableTest extends BaseTest {
	@Test
	public void test_chrome() {
		super.launch_browser("chrome");
		super.goto_url("http://jqueryui.com/demos/draggable/");
		Element the_box = findElement(By.id("draggable"));
		the_box.move_by(10, 10);
		the_box.move_to(100, 100);
		the_box.move_by(-20, -20);
		the_box.move_to(-70, -70);
	}
	
	@Test
	public void test_firefox() {
		super.launch_browser("firefox");
		super.goto_url("http://jqueryui.com/demos/draggable/");
		Element the_box = findElement(By.id("draggable"));
		the_box.move_by(10, 10);
		the_box.move_to(100, 100);
		the_box.move_by(-20, -20);
		the_box.move_to(-70, -70);
	}
}