package daveayan.gherkinsalad;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.browser.PageElementKey;

public class Browser_locate_element_object_for_Test {
	private Browser browser;
	private WebDriver driver;
	
	@Before
	public void setup() {
		driver = Mockito.mock(WebDriver.class);
		
		browser = Browser.instance_with_name_and_page_structure_name("unittest", "unit.test.page.structure.csv");
		browser.launch_with_instance(driver);
	}
	
	@Test
	public void for_a_specific_role_component_element() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "Login", "username");
		MockBrowserElement actual_browser_element = (MockBrowserElement) browser.locate_element_object_for(pek);
		
		Assert.assertNotNull(actual_browser_element);
		Assert.assertNotNull(actual_browser_element.driver);
		Assert.assertNotNull(actual_browser_element.element_locators);
		
		Assert.assertFalse(actual_browser_element.isNull());
		
		Assert.assertEquals(driver, actual_browser_element.driver);
		Assert.assertEquals(1, actual_browser_element.element_locators.size());
		Assert.assertEquals("By.id: username", actual_browser_element.element_locators.get(0).toString());
		Assert.assertEquals("PageElement with key 'anyuser,Login,username'", actual_browser_element.pek.toString());
	}
	
	@Test
	public void for_a_specific_role_and_component() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "Search", "");
		MockBrowserElement actual_browser_element = (MockBrowserElement) browser.locate_element_object_for(pek);
		
		Assert.assertNotNull(actual_browser_element);
		Assert.assertNotNull(actual_browser_element.driver);
		Assert.assertNotNull(actual_browser_element.element_locators);
		
		Assert.assertFalse(actual_browser_element.isNull());
		
		Assert.assertEquals(driver, actual_browser_element.driver);
		Assert.assertEquals(1, actual_browser_element.element_locators.size());
		Assert.assertEquals("By.id: search_portlet", actual_browser_element.element_locators.get(0).toString());
		Assert.assertEquals("PageElement with key 'anyuser,Search,*'", actual_browser_element.pek.toString());
	}
	
	@Test
	public void for_a_specific_role_and_element() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "", "Edit");
		MockBrowserElement actual_browser_element = (MockBrowserElement) browser.locate_element_object_for(pek);
		
		Assert.assertNotNull(actual_browser_element);
		Assert.assertNotNull(actual_browser_element.driver);
		Assert.assertNotNull(actual_browser_element.element_locators);
		
		Assert.assertFalse(actual_browser_element.isNull());
		
		Assert.assertEquals(driver, actual_browser_element.driver);
		Assert.assertEquals(1, actual_browser_element.element_locators.size());
		Assert.assertEquals("By.id: edit_button", actual_browser_element.element_locators.get(0).toString());
		Assert.assertEquals("PageElement with key 'anyuser,*,Edit'", actual_browser_element.pek.toString());
	}
	
	@Test
	public void for_multiple_locators() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "", "Add");
		MockBrowserElement actual_browser_element = (MockBrowserElement) browser.locate_element_object_for(pek);
		
		Assert.assertNotNull(actual_browser_element);
		Assert.assertNotNull(actual_browser_element.driver);
		Assert.assertNotNull(actual_browser_element.element_locators);
		
		Assert.assertFalse(actual_browser_element.isNull());
		
		Assert.assertEquals(driver, actual_browser_element.driver);
		Assert.assertEquals(2, actual_browser_element.element_locators.size());
		Assert.assertEquals("By.id: menu", actual_browser_element.element_locators.get(0).toString());
		Assert.assertEquals("By.id: add_option", actual_browser_element.element_locators.get(1).toString());
		Assert.assertEquals("PageElement with key 'anyuser,*,Add'", actual_browser_element.pek.toString());
	}
	
	@Test
	public void for_no_locators_specified() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "", "");
		MockBrowserElement actual_browser_element = (MockBrowserElement) browser.locate_element_object_for(pek);
		
		Assert.assertNotNull(actual_browser_element);
		Assert.assertNotNull(actual_browser_element.driver);
		Assert.assertNotNull(actual_browser_element.element_locators);
		
		Assert.assertFalse(actual_browser_element.isNull());
		
		Assert.assertEquals(driver, actual_browser_element.driver);
		Assert.assertEquals(0, actual_browser_element.element_locators.size());
		Assert.assertEquals("PageElement with key 'anyuser,*,*'", actual_browser_element.pek.toString());
	}
	
	@Test
	public void for_no_element_type_specified_returns_NullBrowserElement() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "component", "element");
		try {
			browser.locate_element_object_for(pek);
			Assert.fail();
		} catch(AssertionError ae) {
			Assert.assertEquals("Did not find any element type name in the page structure file for page element key PageElement with key 'anyuser,component,element'", ae.getMessage());
		}
	}
	
	@Test
	public void for_element_not_defined_in_page_structure() {
		PageElementKey pek = PageElementKey.newInstance("anyuser", "", "no_such_element_in_page_structure");
		MockBrowserElement actual_browser_element = (MockBrowserElement) browser.locate_element_object_for(pek);
		
		Assert.assertNotNull(actual_browser_element);
		Assert.assertNotNull(actual_browser_element.driver);
		Assert.assertNotNull(actual_browser_element.element_locators);
		
		Assert.assertFalse(actual_browser_element.isNull());
		
		Assert.assertEquals(driver, actual_browser_element.driver);
		Assert.assertEquals(1, actual_browser_element.element_locators.size());
		Assert.assertEquals("By.linkText: no_such_element_in_page_structure", actual_browser_element.element_locators.get(0).toString());
		Assert.assertEquals("PageElement with key '*,*,*'", actual_browser_element.pek.toString());
	}
}