package daveayan.gherkinsalad.test;

import daveayan.gherkinsalad.AutomationObject;
import daveayan.gherkinsalad.report.ReportFactory;

public class Assert extends AutomationObject{
	protected Assert() {
	}

	public static void assertTrue(String message, boolean condition) {
		if (!condition)
			fail(message);
	}
	/**
	 * Use this to assert that a condition is true. If it isn't it throws
	 * an AssertionFailedError.
	 */
	public static void assertTrue(boolean condition) {
		assertTrue(null, condition);
	}
	/**
	 * Use this to assert that a condition is false. If it isn't it throws
	 * an AssertionFailedError with the given message.
	 */
	public static void assertFalse(String message, boolean condition) {
		assertTrue(message, !condition);
	}
	/**
	 * Use this to assert that a condition is false. If it isn't it throws
	 * an AssertionFailedError.
	 */
	public static void assertFalse(boolean condition) {
		assertFalse(null, condition);
	}
	/**
	 * Fails a test with the given message.
	 */
	public static void fail(String message) {
		ReportFactory.reporter().error(message);
		browser.takeScreenshot();
	}
	/**
	 * Fails a test with no message.
	 */
	public static void fail() {
		fail(null);
	}
	/**
	 * Use this to assert that two objects are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertEquals(String message, Object expected, Object actual) {
		if (expected == null && actual == null)
			return;
		if (expected != null && expected.equals(actual))
			return;
		failNotEquals(message, expected, actual);
	}
	/**
	 * Use this to assert that two objects are equal. If they are not
	 * an AssertionFailedError is thrown.
	 */
	public static void assertEquals(Object expected, Object actual) {
	    assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two Strings are equal. 
	 */
	public static void assertEquals(String message, String expected, String actual) {
		if (expected == null && actual == null)
			return;
		if (expected != null && expected.equals(actual))
			return;
		ReportFactory.reporter().error(message + " Expected '" + expected + "', Found '" + actual +"'");
	}
	/**
	 * Use this to assert that two Strings are equal. 
	 */
	public static void assertEquals(String expected, String actual) {
	    assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two doubles are equal concerning a delta.  If they are not
	 * an AssertionFailedError is thrown with the given message.  If the expected
	 * value is infinity then the delta value is ignored.
	 */
	public static void assertEquals(String message, double expected, double actual, double delta) {
		if (Double.compare(expected, actual) == 0)
			return;
		if (!(Math.abs(expected-actual) <= delta))
			failNotEquals(message, new Double(expected), new Double(actual));
	}
	/**
	 * Use this to assert that two doubles are equal concerning a delta. If the expected
	 * value is infinity then the delta value is ignored.
	 */
	public static void assertEquals(double expected, double actual, double delta) {
	    assertEquals(null, expected, actual, delta);
	}
	/**
	 * Use this to assert that two floats are equal concerning a delta. If they are not
	 * an AssertionFailedError is thrown with the given message.  If the expected
	 * value is infinity then the delta value is ignored.
	 */
	public static void assertEquals(String message, float expected, float actual, float delta) {
		if (Float.isInfinite(expected)) {
			if (!(expected == actual))
				failNotEquals(message, new Float(expected), new Float(actual));
		} else if (!(Math.abs(expected-actual) <= delta))
      		failNotEquals(message, new Float(expected), new Float(actual));
	}
	/**
	 * Use this to assert that two floats are equal concerning a delta. If the expected
	 * value is infinity then the delta value is ignored.
	 */
	public static void assertEquals(float expected, float actual, float delta) {
		assertEquals(null, expected, actual, delta);
	}
	/**
	 * Use this to assert that two longs are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertEquals(String message, long expected, long actual) {
	    assertEquals(message, new Long(expected), new Long(actual));
	}
	/**
	 * Use this to assert that two longs are equal.
	 */
	public static void assertEquals(long expected, long actual) {
	    assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two booleans are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertEquals(String message, boolean expected, boolean actual) {
    		assertEquals(message, Boolean.valueOf(expected), Boolean.valueOf(actual));
  	}
	/**
	 * Use this to assert that two booleans are equal.
 	 */
	public static void assertEquals(boolean expected, boolean actual) {
		assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two bytes are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
  	public static void assertEquals(String message, byte expected, byte actual) {
		assertEquals(message, new Byte(expected), new Byte(actual));
	}
	/**
   	 * Use this to assert that two bytes are equal.
	 */
	public static void assertEquals(byte expected, byte actual) {
		assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two chars are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
  	public static void assertEquals(String message, char expected, char actual) {
    		assertEquals(message, new Character(expected), new Character(actual));
  	}
	/**
	 * Use this to assert that two chars are equal.
	 */
  	public static void assertEquals(char expected, char actual) {
		assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two shorts are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertEquals(String message, short expected, short actual) {
    		assertEquals(message, new Short(expected), new Short(actual));
	}
  	/**
	 * Use this to assert that two shorts are equal.
	 */
	public static void assertEquals(short expected, short actual) {
		assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that two ints are equal. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
  	public static void assertEquals(String message, int expected, int actual) {
		assertEquals(message, new Integer(expected), new Integer(actual));
  	}
  	/**
   	 * Use this to assert that two ints are equal.
	 */
  	public static void assertEquals(int expected, int actual) {
  		assertEquals(null, expected, actual);
	}
	/**
	 * Use this to assert that an object isn't null.
	 */
	public static void assertNotNull(Object object) {
		assertNotNull(null, object);
	}
	/**
	 * Use this to assert that an object isn't null. If it is
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertNotNull(String message, Object object) {
		assertTrue(message, object != null);
	}
	/**
	 * Use this to assert that an object is null.
	 */
	public static void assertNull(Object object) {
		assertNull(null, object);
	}
	/**
	 * Use this to assert that an object is null.  If it is not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertNull(String message, Object object) {
		assertTrue(message, object == null);
	}
	/**
	 * Use this to assert that two objects refer to the same object. If they are not
	 * an AssertionFailedError is thrown with the given message.
	 */
	public static void assertSame(String message, Object expected, Object actual) {
		if (expected == actual)
			return;
		failNotSame(message, expected, actual);
	}
	/**
	 * Use this to assert that two objects refer to the same object. If they are not
	 * the same an AssertionFailedError is thrown.
	 */
	public static void assertSame(Object expected, Object actual) {
	    assertSame(null, expected, actual);
	}
	/**
	 * Use this to assert that two objects do not refer to the same object. If they do
	 * refer to the same object an AssertionFailedError is thrown with the
	 * given message.
	 */
	public static void assertNotSame(String message, Object expected, Object actual) {
		if (expected == actual)
			failSame(message);
	}
	/**
	 * Use this to assert that two objects do not refer to the same object. If they do
	 * refer to the same object an AssertionFailedError is thrown.
	 */
	public static void assertNotSame(Object expected, Object actual) {
		assertNotSame(null, expected, actual);
	}

	public static void failSame(String message) {
		String formatted= "";
 		if (message != null)
 			formatted= message+" ";
 		fail(formatted+"expected not same");
	}

	public static void failNotSame(String message, Object expected, Object actual) {
		String formatted= "";
		if (message != null)
			formatted= message+" ";
		fail(formatted+"expected same:<"+expected+"> was not:<"+actual+">");
	}

	public static void failNotEquals(String message, Object expected, Object actual) {
		fail(format(message, expected, actual));
	}

	static String format(String message, Object expected, Object actual) {
		String formatted= "";
		if (message != null)
			formatted= message+" ";
		return formatted+"expected:<"+expected+"> but was:<"+actual+">";
	}
}