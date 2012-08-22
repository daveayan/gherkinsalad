package daveayan.gherkinsalad.components;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Elements_should_have_all_of_these_texts_Test {
	
	@Test public void test_1() {
		elements.should_have_all_of_these_texts("String1");
		elements.should_have_all_of_these_texts("String1", "String2", "String3");
		elements.should_have_all_of_these_texts("String1", "String2", "String4");
		
		elements.should_have_any_of_these_texts("String1");
		elements.should_have_any_of_these_texts("String1", "String2", "String3");
		elements.should_have_any_of_these_texts("String4");
	}
	
	Elements elements = new Elements();
	
	@Before public void setup() {
		WebElement element1 = mock(WebElement.class);
		WebElement element2 = mock(WebElement.class);
		WebElement element3 = mock(WebElement.class);
		
		when(element1.getText()).thenReturn("String1");
		when(element2.getText()).thenReturn("String2");
		when(element3.getText()).thenReturn("String3");
		
		elements.add(Element.newInstance(element1));
		elements.add(Element.newInstance(element2));
		elements.add(Element.newInstance(element3));
	}
}