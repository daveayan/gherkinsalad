package daveayan.gherkinsalad.components;

import org.apache.chemistry.opencmis.util.content.loremipsum.LoremIpsum;
import org.junit.Test;

public class LoremIpsumTest {
	@Test public void test() {
		LoremIpsum li = new LoremIpsum();
		
		System.out.println(li.generateParagraph(false));
		System.out.println(li.generateParagraph(false));
		System.out.println(li.generateParagraph(false));
		System.out.println(li.generateParagraph(false));
		System.out.println(li.generateParagraph(false));
	}
}