package daveayan.gherkinsalad;

import org.apache.commons.lang.StringUtils;

public class Utils {
	public static boolean not_equals(String str1, String str2) {
		return ! equals(str1, str2);
	}
	
	public static boolean equals(String str1, String str2) {
		return StringUtils.equals(StringUtils.trimToEmpty(str1), StringUtils.trimToEmpty(str2));
	}
}