package daveayan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;

public abstract class BaseComponentObject {
	public abstract String getName();
	public abstract WebElement component_root();
	public abstract WebElement element_for_name(String element_name);
	public String toString() {
		return "Component Name : '" + getName() + "'";
	}
	public void wait_for_page_to_load() throws InterruptedException {
		System.out.print("Waiting ... ");
		Thread.sleep(10 * 1000);
		System.out.println("OK");
	}
	public void click(String element_name) {
		try {
			element_name = element_name.toLowerCase();
			element_name = element_name.replace(' ', '_');
			Method method = this.getClass().getMethod("click_" + element_name);
			method.invoke(this);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}