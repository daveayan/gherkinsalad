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
package daveayan.gherkinsalad.components;

import org.openqa.selenium.By;

import daveayan.lang.Nullable;
/** @author daveayan */
/**
 * Interface to describe any element on a browser. The browser element may have other composite elements. A browser element may be enabled or disabled.
 * This may be "null" if the requested element cannot be found on the browser. This class uses the <a href="http://industriallogic.com/xp/refactoring/nullObject.html">Null Object</a> Pattern.
 * 
 * Unless absolutely needed do not make your page object implement this interface directly. Instead make them extend BaseBrowserElement.
 */
public interface BrowserElement extends CanBeEnabled, CanBeDisabled, Nullable {
	public BrowserElement name(String name);
	public BrowserElement found(By element_locator);
	
	public String getText();
	
	public boolean isDisplayed();
	public boolean isNotDisplayed();
	public boolean has_text(String... expected_texts);
	
	public void should_be_enabled();
	public void should_be_disabled();
	
	public void should_be_displayed();
	public void should_not_be_displayed();
	
	public void should_have_text(String... expected_texts);
	public void should_not_have_text(String... expected_texts);
}