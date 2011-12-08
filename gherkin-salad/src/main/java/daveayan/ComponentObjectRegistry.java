package daveayan;

import java.util.HashMap;
import java.util.Map;

public class ComponentObjectRegistry {
	private static Map <String, Class<?>> registry_map = null;
	
	public static Class<?> page_component_class(String component_name) {
		init();
		return registry_map.get(component_name);
	}
	
	private static void init() {
		if(registry_map == null) {
			registry_map = new HashMap<String, Class<?>>();
		}
	}
	
	private ComponentObjectRegistry() {}
}