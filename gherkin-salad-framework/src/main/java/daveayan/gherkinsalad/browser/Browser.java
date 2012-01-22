package daveayan.gherkinsalad.browser;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.browser.actions.BrowserElement;

public class Browser {
	private String name;

	private PageStructure page_structure;

	private static WebDriver instance;

	public static Browser instance_with_name_and_page_structure_name(String name, String page_structure_name) {
		Browser browser = new Browser();
		browser.name = name;
		browser.load_page_structure(page_structure_name);
		return browser;
	}

	public BrowserElement locate_element_object_for(String role, String component_name, String element_name) {
		PageElementKey pek = PageElementKey.newInstance(role, component_name, element_name);
		return locate_element_object_for(pek);
	}

	public BrowserElement locate_element_object_for(PageElementKey page_element_key) {
		return page_structure.getElement(instance, page_element_key);
	}

	public WebElement findElementBy(By by) {
		return instance.findElement(by);
	}

	public void close() {
		try {
			if (instance != null) {
				instance.close();
				instance.quit();
			}
		} catch (WebDriverException wde) {
			System.err.println(wde.getMessage());
		}
		instance = null;
		page_structure = null;
	}

	public void launch() {
		if (this.is_IE()) {
			instance = IeBrowser.getDriver();
		} else if (this.is_Chrome()) {
			instance = ChromeBrowser.getDriver();
		} else if (this.is_Firefox()) {
			instance = new FirefoxDriver();
		}
	}

	public void launch_with_instance(WebDriver driver) {
		Browser.instance = driver;
	}

	public void goto_url(String url) {
		instance.get(url);
	}

	private void load_page_structure(String page_structure_file_name) {
		if (page_structure == null) {
			this.page_structure = PageStructure.instanceFromFile(page_structure_file_name);
		}
	}

	public WebDriver driver() {
		return instance;
	}

	public void takeScreenshot() {
		try {
			File screenshot_file = ((TakesScreenshot) instance).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot_file, new File(Path.TO_SCREENSHOTS + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean is_IE() {
		return "ie".equalsIgnoreCase(this.name);
	}

	private boolean is_Chrome() {
		return "chrome".equalsIgnoreCase(this.name);
	}

	private boolean is_Firefox() {
		return "firefox".equalsIgnoreCase(this.name);
	}
}