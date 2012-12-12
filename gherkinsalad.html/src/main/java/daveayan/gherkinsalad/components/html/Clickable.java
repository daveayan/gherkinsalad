/**
 * Copyright (c) 2012 Ayan Dave http://daveayan.com
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
 * associated documentation files (the "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the 
 * following conditions:
 * 
 * 1) The above copyright notice and this permission notice shall be included without any changes or alterations 
 * in all copies or substantial portions of the Software.
 * 2) This software shall be used for Good, not Evil.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
**/
package daveayan.gherkinsalad.components.html;

import daveayan.gherkinsalad.components.core.BrowserElement;

/** @author daveayan */
/**
 * Interface that indicates that a browser element can be clicked.
 * Unless absolutely needed do not make your page object implement this interface directly. Instead make them extend Link.
 */
public interface Clickable extends BrowserElement {
	/**
	 * Click the element if it is enabled, i.e. isEnabled() returns true
	 * If the element is not displayed calling this method will throw AssertionError
	 */
	public void click_if_enabled();
	
	/**
	 * Click the element if it is displayed and enabled, i.e. isDisplayed() returns true and isEnabled() returns true
	 */
	public void click_if_exists_and_enabled();
}