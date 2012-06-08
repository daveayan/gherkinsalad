package daveayan.gherkinsalad.browser;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.browser.factory.ChromeBrowser;
import daveayan.gherkinsalad.browser.factory.IeBrowser;

public class Browser {
	private static Log log = LogFactory.getLog(Browser.class);
	private String name;

	private static WebDriver instance;

	private static int screen_shot_count = 0;

	public void takeScreenshot() {
		screen_shot_count++;
		String file_name = Path.TO_SCREENSHOTS + "screenshot_" + screen_shot_count + ".png";
		try {
			File screenshot_file = ((TakesScreenshot) instance).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot_file, new File(file_name));
			log.info("Screenshot taken : " + file_name);
		} catch (Throwable th) {
			log.info(th.getMessage());
			log.info("Unable to take screenshot : " + file_name);
		}
	}

	public static Browser instance_of(String name) {
		Browser browser = new Browser();
		browser.name = name;
		return browser;
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

	public WebDriver driver() {
		return instance;
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