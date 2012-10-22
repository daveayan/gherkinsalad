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
		List<String> newList = new ArrayList<String>();
		newList.addAll(_nativeStrings);
		newList.add(newString);
		
		Strings _strings = new Strings();
		_strings._nativeStrings = newList;
		return _strings;
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
	
	public Strings should_have_all_these_strings(String... expected_strings) {
		return should_have_all_these_strings(Strings.instance_from(expected_strings));
	}
	
	public Strings should_have_all_these_strings(List<String> expected_strings) {
		return should_have_all_these_strings(Strings.instance_from(expected_strings));
	}
	
	public Strings should_have_all_these_strings(Strings expected_strings) {
		Strings strings_not_present = Strings.new_instance();
		for(String s1: expected_strings._nativeStrings) {
			boolean found = false;
			for(String s2: _nativeStrings) {
				if(StringUtils.equals(s1, s2)) {
					found = true;
					break;
				}
			}
			if(! found) {
				strings_not_present._nativeStrings.add(s1);
			}
		}
		return strings_not_present;
	}
	
	public void should_be_empty() {
		if(this._nativeStrings().isEmpty()) {
			return;
		}
		error("Expected the list of strings to be empty, found these values however '" + this.toString() + "'");
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
}