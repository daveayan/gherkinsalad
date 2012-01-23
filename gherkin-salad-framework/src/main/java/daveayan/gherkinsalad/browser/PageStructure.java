package daveayan.gherkinsalad.browser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NullList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.browser.actions.BrowserElement;
import daveayan.gherkinsalad.browser.actions.NullBrowserElement;

public class PageStructure {
	private static final String NA = "NA";

	private Properties default_page_structure = null;

	public static PageStructure instanceFromFile(String file_name) {
		PageStructure page_structure = new PageStructure();
		page_structure.default_page_structure = page_structure.loadPageStructureIfPossible(file_name);
		return page_structure;
	}

	public BrowserElement getElement(WebDriver driver, PageElementKey page_element_key) {
		BrowserElement be = getElementWithBys(page_element_key);
		if (be instanceof NullBrowserElement) {
			PageElementKey pek = PageElementKey.instanceForAnyRoleComponentElement();
			be = getElementWithBys(pek);
			if (be instanceof NullBrowserElement) {
				be = new NullBrowserElement();
				be.page_element_key_is(page_element_key);
			} else {
				be.page_element_key_is(pek);
				be.driver_is(driver);
				List<By> element_locators = new ArrayList<By>();
				element_locators.add(By.linkText(page_element_key.element_name()));
				be.element_locators_are(element_locators);
			}
		} else {
			be.page_element_key_is(page_element_key);
			be.driver_is(driver);
		}
		return be;
	}

	private BrowserElement getElementWithBys(PageElementKey page_element_key) {
		String value = element_locators_for(page_element_key);
		if (StringUtils.isNotBlank(value)) {
			String[] value_elements = value.split("~");
			if (StringUtils.equalsIgnoreCase(value_elements[0], NA)) {
				throw new AssertionError("Did not find any element type name in the page structure file for page element key "
						+ page_element_key);
			}
			BrowserElement be = create_browser_element_for(value_elements[0]);
			List<By> bys = new ArrayList<By>();
			if (value_elements.length == 2) {
				bys = getBys(value_elements[1]);
			}
			be.element_locators_are(bys);
			return be;
		}
		return new NullBrowserElement();
	}

	private Properties loadPageStructureIfPossible(String file_name) {
		Properties props = new Properties();
		try {
			File file = new File(Path.TO_PAGE_STRUCTURE + file_name);
			if (file.exists()) {
				LineIterator it = FileUtils.lineIterator(new File(Path.TO_PAGE_STRUCTURE + file_name));
				try {
					while (it.hasNext()) {
						String line = it.nextLine();
						String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
						if (items != null && items.length == 7) {
							if (StringUtils.isNotBlank(items[0])) {
								String key = items[0] + "," + items[1] + "," + items[2];

								if (StringUtils.isBlank(items[3])) {
									items[3] = NA;
								}
								String value = items[3] + "~" + items[4];
								if (StringUtils.isNotBlank(items[5]))
									value = value + "," + items[5];
								if (StringUtils.isNotBlank(items[6]))
									value = value + "," + items[6];
								props.put(key, value);
							}
						}
					}
				} finally {
					it.close();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return props;
	}

	private BrowserElement create_browser_element_for(String class_name) {
		try {
			Class< ? > clazz = Class.forName(class_name);
			Object object = clazz.newInstance();
			return (BrowserElement) object;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new NullBrowserElement();
	}

	private List<By> getBys(String element_locators_comma_seperated) {
		List<By> bys = new ArrayList<By>();
		if (StringUtils.isNotBlank(element_locators_comma_seperated)) {
			String[] locators = element_locators_comma_seperated.split(",");
			for (String locator : locators) {
				String[] pair = locator.split(":");
				if (pair.length == 2) {
					bys.add(getBy(pair[0], pair[1]));
				} else {
					return new NullList<By>();
				}
			}
		}
		return bys;
	}

	private By getBy(String type, String value) {
		if ("id".equalsIgnoreCase(type.trim())) {
			return By.id(value);
		} else if ("xpath".equalsIgnoreCase(type.trim())) {
			return By.xpath(value);
		} else if ("tagName".equalsIgnoreCase(type.trim())) {
			return By.tagName(value);
		} else if ("className".equalsIgnoreCase(type.trim())) {
			return By.className(value);
		} else if ("cssSelector".equalsIgnoreCase(type.trim())) {
			return By.cssSelector(value);
		} else if ("name".equalsIgnoreCase(type.trim())) {
			return By.name(value);
		} else if ("linkText".equalsIgnoreCase(type.trim())) {
			return By.linkText(value);
		}
		return By.linkText(value);
	}

	private String element_locators_for(PageElementKey page_element_key) {
		String element_locator = (String) default_page_structure.getProperty(page_element_key.key());
		if (element_locator == null) {
			element_locator = (String) default_page_structure.getProperty(page_element_key.key_for_any_role());
		}
		return element_locator;
	}
}