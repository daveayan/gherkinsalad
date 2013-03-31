package daveayan.gherkinsalad;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringsTest {
	Strings empty_target;
	Strings filled_target;
	
	@Before public void setup() {
		empty_target = Strings.new_instance();
		filled_target = Strings.instance_from("AA", "BB", "CC", "DD", "EE");
	}
	
	@Test public void test_does_not_have_any_of_these() {
		assertEquals("''", empty_target.does_not_have_any_of_these((String[]) null).toString());
		assertEquals("', '", empty_target.does_not_have_any_of_these("", null).toString());
		assertEquals("', '", empty_target.does_not_have_any_of_these(null, null).toString());
		assertEquals("'AA, BB'", empty_target.does_not_have_any_of_these("AA", "BB").toString());
		
		assertEquals("''", filled_target.does_not_have_any_of_these((String[]) null).toString());
		assertEquals("', '", filled_target.does_not_have_any_of_these("", null).toString());
		assertEquals("', '", filled_target.does_not_have_any_of_these(null, null).toString());
		assertEquals("''", filled_target.does_not_have_any_of_these("AA", "BB").toString());
		assertEquals("'ZZ'", filled_target.does_not_have_any_of_these("AA", "BB", "ZZ").toString());
		assertEquals("'ZZ, YY'", filled_target.does_not_have_any_of_these("ZZ", "BB", "YY", "AA").toString());
	}
	
	@Test public void test_does_not_have_all_these() {
		assertEquals("''", empty_target.does_not_have_all_these((String[]) null).toString());
		assertEquals("''", empty_target.does_not_have_all_these("", null).toString());
		assertEquals("''", empty_target.does_not_have_all_these(null, null).toString());
		assertEquals("''", empty_target.does_not_have_all_these("AA", "BB").toString());
		
		assertEquals("''", filled_target.does_not_have_all_these((String[]) null).toString());
		assertEquals("''", filled_target.does_not_have_all_these("", null).toString());
		assertEquals("''", filled_target.does_not_have_all_these(null, null).toString());
		assertEquals("'AA, BB'", filled_target.does_not_have_all_these("AA", "BB").toString());
		assertEquals("'AA, BB'", filled_target.does_not_have_all_these("AA", "BB", "ZZ").toString());
		assertEquals("'BB, AA'", filled_target.does_not_have_all_these("ZZ", "BB", "YY", "AA").toString());
	}
	
	@Test public void test_has_any_of_these() {
		assertEquals("''", empty_target.has_any_of_these((String[]) null).toString());
		assertEquals("''", empty_target.has_any_of_these("", null).toString());
		assertEquals("''", empty_target.has_any_of_these(null, null).toString());
		assertEquals("''", empty_target.has_any_of_these("AA", "BB").toString());
		
		assertEquals("''", filled_target.has_any_of_these((String[]) null).toString());
		assertEquals("''", filled_target.has_any_of_these("", null).toString());
		assertEquals("''", filled_target.has_any_of_these(null, null).toString());
		assertEquals("'AA, BB'", filled_target.has_any_of_these("AA", "BB").toString());
		assertEquals("'AA, BB'", filled_target.has_any_of_these("AA", "BB", "ZZ").toString());
		assertEquals("'BB, AA'", filled_target.has_any_of_these("ZZ", "BB", "YY", "AA").toString());
	}
	
	@Test public void test_has_all_these() {
		assertEquals("''", empty_target.has_all_these((String[]) null).toString());
		assertEquals("', '", empty_target.has_all_these("", null).toString());
		assertEquals("', '", empty_target.has_all_these(null, null).toString());
		assertEquals("'AA, BB'", empty_target.has_all_these("AA", "BB").toString());
		
		assertEquals("''", filled_target.has_all_these((String[]) null).toString());
		assertEquals("', '", filled_target.has_all_these("", null).toString());
		assertEquals("', '", filled_target.has_all_these(null, null).toString());
		assertEquals("''", filled_target.has_all_these("AA", "BB").toString());
		assertEquals("'ZZ'", filled_target.has_all_these("AA", "BB", "ZZ").toString());
		assertEquals("'ZZ, YY'", filled_target.has_all_these("ZZ", "BB", "YY", "AA").toString());
	}
	
	@Test public void test_has_does_not_have() {
		assertEquals(false, empty_target.has(null));
		assertEquals(false, empty_target.has(""));
		assertEquals(false, empty_target.has("AA"));
		
		assertEquals(false, filled_target.has(null));
		assertEquals(false, filled_target.has(""));
		assertEquals(true, filled_target.has("AA"));
		assertEquals(false, filled_target.has("ZZ"));
		
		assertEquals(true, empty_target.does_not_have(null));
		assertEquals(true, empty_target.does_not_have(""));
		assertEquals(true, empty_target.does_not_have("AA"));
		
		assertEquals(true, filled_target.does_not_have(null));
		assertEquals(true, filled_target.does_not_have(""));
		assertEquals(false, filled_target.does_not_have("AA"));
		assertEquals(true, filled_target.does_not_have("ZZ"));
	}
	
	@Test public void test_emptiness() {
		assertEquals(true, empty_target.is_empty());
		assertEquals(false, empty_target.is_not_empty());
		
		assertEquals(true, filled_target.is_not_empty());
		assertEquals(false, filled_target.is_empty());
	}
}