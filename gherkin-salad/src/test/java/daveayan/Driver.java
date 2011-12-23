package daveayan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {
	public static void main(String[] args) {
//		try_chrome();
		try_firefox();
	}
	public static void try_firefox() {
		WebDriver instance = new FirefoxDriver();
		instance.get("http://www.amazon.com");
	}
	public static void try_chrome() {
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

		System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
		String chromeBinary = System.getProperty("webdriver.chrome.driver");
		if (chromeBinary == null || chromeBinary.equals("")) {
			String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
			chromeBinary = "lib/chromedriver-" + os + (os.equals("win") ? ".exe" : "");
			System.setProperty("webdriver.chrome.driver", chromeBinary);
		}
		WebDriver instance = new ChromeDriver(chromeCapabilities);
		instance.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
	}
}