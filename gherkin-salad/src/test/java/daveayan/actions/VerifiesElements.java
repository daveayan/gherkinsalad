package daveayan.actions;

public interface VerifiesElements extends ApplicationWebElement {
	public void hasElement(ApplicationWebElement element);
	public void doesNotHaveElement(ApplicationWebElement element);
}