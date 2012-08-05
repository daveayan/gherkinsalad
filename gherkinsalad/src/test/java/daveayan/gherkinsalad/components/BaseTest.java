package daveayan.gherkinsalad.components;

import org.junit.After;

import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class BaseTest extends BaseBrowserElement {

	@After
	public void close_browser() {
		super.close_browser();
	}
}