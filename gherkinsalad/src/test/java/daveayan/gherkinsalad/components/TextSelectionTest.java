package daveayan.gherkinsalad.components;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class TextSelectionTest extends BaseTest {
	@Test public void select_text_using_firefox() {
		super.launch_browser("firefox");
		super.goto_test_page("test-text-selection.html");
		
		Element first_p = findElement(By.xpath("/html/body/p[1]"));
		Element second_p = findElement(By.xpath("/html/body/p[2]"));
		
		Actions actions = new Actions(browser.driver());
		
		actions.moveToElement(first_p._nativeWebElement()).build().perform();
		wait_for_seconds(3);
		System.out.println("Moved to element 1");
		
		actions.clickAndHold(first_p._nativeWebElement()).build().perform();
//		wait_for_seconds(3);
		System.out.println("Click and Hold on element 1");
		
		actions.moveToElement(second_p._nativeWebElement()).build().perform();
//		wait_for_seconds(4);
		
		System.out.println("Moved to element 2 - Selection");
		
		actions.release().build().perform();
		
		System.out.println("Release click on element 2 - Selection End");
		
		first_p.getText();
		
		second_p.getText();
		wait_for_seconds(5);
	}
}