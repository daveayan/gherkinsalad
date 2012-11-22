package daveayan.gherkinsalad;

import org.junit.Test;

public class StringsTest {
	@Test public void test() {
		Strings empty_target = Strings.new_instance();
		Strings target = Strings.instance_from("AA", "BB", "CC", "DD", "EE");
		
		empty_target.should_be_empty();
		empty_target.should_not_be_empty();
		
//		empty_target.should_have_all_these("AA", "BB", "CC");
//		empty_target.should_have_all_these("AA", "BB", "ZZ");
//		
//		empty_target.should_have_any_of_these("AA", "BB", "ZZ");
//		empty_target.should_have_any_of_these("ZZ", "YY", "XX");
//		
//		empty_target.should_not_have_any_of_these("ZZ", "YY", "XX");
//		empty_target.should_not_have_any_of_these("AA", "BB", "ZZ");
//		
//		target.should_be_empty();
//		target.should_not_be_empty();
//		
//		target.should_have_all_these("AA", "BB", "CC");
//		target.should_have_all_these("AA", "BB", "ZZ");
//		
//		target.should_have_any_of_these("AA", "BB", "ZZ");
//		target.should_have_any_of_these("ZZ", "YY", "XX");
//		
//		target.should_not_have_any_of_these("ZZ", "YY", "XX");
//		target.should_not_have_any_of_these("AA", "BB", "ZZ");
	}
}