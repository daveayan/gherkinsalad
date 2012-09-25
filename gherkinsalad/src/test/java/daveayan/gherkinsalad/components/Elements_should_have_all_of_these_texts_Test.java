package daveayan.gherkinsalad.components;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.Strings;

public class Elements_should_have_all_of_these_texts_Test {
	
	@Test public void test_1() {
		elements.should_have_all_of_these_texts(Strings.new_instance().add("String1"));
		elements.should_have_all_of_these_texts(Strings.new_instance().add("String1").add("String2").add("String3"));
		elements.should_have_all_of_these_texts(Strings.new_instance().add("String1").add("String2").add("String4"));
		
		elements.should_have_any_of_these_texts(Strings.new_instance().add("String1"));
		elements.should_have_any_of_these_texts(Strings.new_instance().add("String1").add("String2").add("String3"));
		elements.should_have_any_of_these_texts(Strings.new_instance().add("String4"));
	}
	
	Elements elements = new Elements();
	
	@Before public void setup() {
		WebElement element1 = mock(WebElement.class);
		WebElement element2 = mock(WebElement.class);
		WebElement element3 = mock(WebElement.class);
		
		when(element1.getText()).thenReturn("String1");
		when(element2.getText()).thenReturn("String2");
		when(element3.getText()).thenReturn("String3");
		
		elements.add(Element.newInstance(element1, By.name("name")));
		elements.add(Element.newInstance(element2, By.name("name")));
		elements.add(Element.newInstance(element3, By.name("name")));
	}
}