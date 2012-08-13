package daveayan.gherkinsalad.components;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class TextSelectionTest extends BaseTest {
	@Test public void select_text_using_firefox() {
		super.launch_browser("chrome");
		super.goto_test_page("test-text-selection.html");
		
		Element first_p = findElement(By.xpath("/html/body/p[1]"));
		Element second_p = findElement(By.xpath("/html/body/p[2]"));
		
		Actions actions = new Actions(browser.driver());
		
		actions.moveToElement(first_p._nativeWebElement()).build().perform();
		wait_for_seconds(3);
		
		actions.clickAndHold(first_p._nativeWebElement()).build().perform();
		wait_for_seconds(3);
		
		actions.moveToElement(second_p._nativeWebElement()).build().perform();
		wait_for_seconds(4);
		
		actions.release().build().perform();
		wait_for_seconds(5);
	}
}