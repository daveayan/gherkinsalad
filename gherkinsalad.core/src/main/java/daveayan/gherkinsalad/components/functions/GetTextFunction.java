package daveayan.gherkinsalad.components.functions;

import com.google.common.base.Function;

import daveayan.gherkinsalad.components.Element;

public class GetTextFunction implements Function<Element, String> {
	public String apply(Element element) {
		return element.getText();
	}
	
	public static final GetTextFunction apply = new GetTextFunction();
}