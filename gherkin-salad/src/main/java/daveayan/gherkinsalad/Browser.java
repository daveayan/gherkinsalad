package daveayan.gherkinsalad;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import daveayan.gherkinsalad.actions.BrowserElement;

public class Browser {
	private String name;

	private PageStructure page_structure;

	private static WebDriver instance;

	public static Browser instance_with_name_and_page_structure_path(String name, String page_structure_path) {
		Browser browser = new Browser();
		browser.name = name;
		browser.load_page_structure(page_structure_path);
		return browser;
	}

	public static Browser instance(String name) {
		Browser browser = new Browser();
		browser.name = name;
		browser.load_page_structure();
		return browser;
	}

	public BrowserElement locate_element_object_for(String role, String component_name, String element_name) {
		PageElementKey pek = PageElementKey.newInstance(role, component_name, element_name);
		return locate_element_object_for(pek);
	}

	public BrowserElement locate_element_object_for(PageElementKey page_element_key) {
		return page_structure.getElement(instance, page_element_key);
	}

	public void close() {
		if (instance != null) {
			instance.close();
			instance.quit();
			instance = null;
			page_structure = null;
		}
	}

	public void launch() {
		if (instance == null) {
			if (this.is_IE()) {
				instance = new InternetExplorerDriver();
			} else if (this.is_Chrome()) {
				DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

				System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
				String chromeBinary = System.getProperty("webdriver.chrome.driver");
				if (chromeBinary == null || chromeBinary.equals("")) {
					String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
					chromeBinary = "lib/chromedriver-" + os + (os.equals("win") ? ".exe" : "");
					System.setProperty("webdriver.chrome.driver", chromeBinary);
				}
				instance = new ChromeDriver(chromeCapabilities);
				instance.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			} else if (this.is_Firefox()) {
				instance = new FirefoxDriver();
			}
		}
	}

	public void launch_with_instance(WebDriver driver) {
		Browser.instance = driver;
	}

	public void goto_url(String url) {
		instance.get(url);
	}

	private void load_page_structure(String page_structure_file_path) {
		if (page_structure == null) {
			this.page_structure = PageStructure.instanceFromFile(page_structure_file_path);
		}
	}

	private void load_page_structure() {
		if (page_structure == null) {
			if (this.is_IE()) {
				this.page_structure = PageStructure.instanceForIE();
			} else if (this.is_Chrome()) {
				this.page_structure = PageStructure.instanceForChrome();
			} else if (this.is_Firefox()) {
				this.page_structure = PageStructure.instanceForFirefox();
			}
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