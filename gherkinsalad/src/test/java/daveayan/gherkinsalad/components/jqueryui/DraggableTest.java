package daveayan.gherkinsalad.components.jqueryui;

import org.junit.Test;

import daveayan.gherkinsalad.components.BaseTest;

public class DraggableTest extends BaseTest {
	@Test
	public void test() {
		super.launch_browser("Firefox");
		super.goto_url("http://jqueryui.com/demos/draggable/");
		
		Draggable d = new Draggable();
		d.drag();
	}
}