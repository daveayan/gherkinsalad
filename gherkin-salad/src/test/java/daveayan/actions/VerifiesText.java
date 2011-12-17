package daveayan.actions;

public interface VerifiesText extends ApplicationWebElement {
	public void hasText(String text);
	public void doesNotHaveText(String text);
}