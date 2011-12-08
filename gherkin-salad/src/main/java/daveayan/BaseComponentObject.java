package daveayan;

import org.openqa.selenium.WebElement;

public abstract class BaseComponentObject {
	public abstract WebElement component_root();
	public abstract WebElement element_for_name(String element_name);
}