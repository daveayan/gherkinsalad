package daveayan.gherkinsalad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Strings extends AutomationObject {
	List<String> _nativeStrings = new ArrayList<String>();
	
	public static Strings instance_from(String string, String delimiter) {
		Strings _strings = new Strings();
		_strings.set(string.split(delimiter));
		return _strings;
	}
	
	public static Strings instance_from(String... strings) {
		Strings _strings = new Strings();
		_strings.set(strings);
		return _strings;
	}
	
	public static Strings instance_from(List<String> strings) {
		Strings _strings = new Strings();
		_strings._nativeStrings.addAll(strings);
		return _strings;
	}
	
	public static Strings new_instance() {
		Strings _strings = new Strings();
		return _strings;
	}

	public Iterator<String> iterator() {
		return _nativeStrings.iterator();
	}
	
	public Strings add(String newString) {
		this._nativeStrings().add(newString);
		return this;
	}
	
	public String[] asStringsArray() {
		return this._nativeStrings().toArray(new String[] {});
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("'");
		if(_nativeStrings != null) {
			for(int i = 0 ; i < _nativeStrings.size() ; i++) {
				sb.append(_nativeStrings.get(i));
				if(i != _nativeStrings.size() - 1) {
					sb.append(", ");
				}
			}
		}
		sb.append("'");
		return sb.toString();
	}
	
	public Strings toUpperCase(Strings strings) {
		Strings _newStrings = new Strings();
		for(String s: strings._nativeStrings) {
			_newStrings._nativeStrings.add(s.toUpperCase());
		}
		return _newStrings;
	}
	
	public Strings toLowerCase(Strings strings) {
		Strings _newStrings = new Strings();
		for(String s: strings._nativeStrings) {
			_newStrings._nativeStrings.add(s.toLowerCase());
		}
		return _newStrings;
	}
	
	public Strings has_all_these(String... expected_strings) {
		Strings strings_not_present = Strings.new_instance();
		if(expected_strings == null) {
			return strings_not_present;
		}
		for(String expected_string: expected_strings) {
			if(this.does_not_have(expected_string)) {
				strings_not_present._nativeStrings.add(expected_string);
			}
		}
		return strings_not_present;
	}
	
	public Strings has_any_of_these(String... expected_strings) {
		Strings strings_present = Strings.new_instance();
		if(expected_strings == null) {
			return strings_present;
		}
		for(String expected_string: expected_strings) {
			if(this.has(expected_string)) {
				strings_present.add(expected_string);
			}
		}
		return strings_present;
	}
	
	public Strings does_not_have_all_these(String... unexpected_strings) {
		Strings unexpected_strings_present = Strings.new_instance();
		if(unexpected_strings == null) {
			return unexpected_strings_present;
		}
		for(String unexpected_string: unexpected_strings) {
			if(this.has(unexpected_string)) {
				unexpected_strings_present._nativeStrings.add(unexpected_string);
			}
		}
		return unexpected_strings_present;
	}
	
	public Strings does_not_have_any_of_these(String... unexpected_strings) {
		Strings unexpected_strings_not_present = Strings.new_instance();
		if(unexpected_strings == null) {
			return unexpected_strings_not_present;
		}
		for(String unexpected_string: unexpected_strings) {
			if(this.does_not_have(unexpected_string)) {
				unexpected_strings_not_present.add(unexpected_string);
			}
		}
		return unexpected_strings_not_present;
	}
	
	public boolean has(String string) {
		for(String s: _nativeStrings()) {
			if(StringUtils.equals(s, string)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	public boolean does_not_have(String string) {
		return ! has(string);
	}
	
	public void should_be_empty() {
		if(is_empty()) {
			action("Verified list of strings is empty");
			return;
		}
		error("Expected the list of strings to be empty, found these values however '" + this.toString() + "'");
	}
	
	public void should_not_be_empty() {
		if(is_not_empty()) {
			action("Verified list of strings is NOT empty");
			return;
		}
		error("Expected the list of strings to be NOT empty, however it is");
	}
	
	private void set(String... strings) {
		_nativeStrings = null;
		_nativeStrings = new ArrayList<String>();
		for(String string: strings) {
			_nativeStrings.add(string);
		}
	}
	
	public List<String> _nativeStrings() {
		return _nativeStrings;
	}
	
	public boolean is_empty() {
		return _nativeStrings().isEmpty();
	}
	
	public boolean is_not_empty() {
		return ! is_empty();
	}
}