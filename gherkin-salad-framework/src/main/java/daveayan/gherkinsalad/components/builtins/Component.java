package daveayan.gherkinsalad.components.builtins;

import daveayan.gherkinsalad.components.NavigationAware;
import daveayan.gherkinsalad.components.SelfValidating;

public abstract class Component extends BaseBrowserElement implements NavigationAware, SelfValidating {
	public boolean isEnabled() {
		return true;
	}
}