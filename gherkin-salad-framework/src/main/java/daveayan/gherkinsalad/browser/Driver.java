package daveayan.gherkinsalad.browser;

import org.openqa.selenium.WebDriver;

public class Driver {
	public static void main(String[] args) {
		WebDriver instance = ChromeBrowser.getDriver();
		System.out.println(instance);
	}
}