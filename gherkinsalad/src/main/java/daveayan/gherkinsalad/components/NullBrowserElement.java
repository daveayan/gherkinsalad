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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.Browser;
/** @author daveayan */
/**
 * Implementing the <a href="http://industriallogic.com/xp/refactoring/nullObject.html">Null Object</a> Pattern this object is returned back if
 * an element cannot be found on the browser. Calling any method on this class will result in an assertion error.
 */
public class NullBrowserElement implements Clickable, Selectable, TextEnterable, BrowserElement {
	public String getText() {
		throw new AssertionError("operation public String getText() not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean isNotDisplayed() {
		throw new AssertionError("operation public boolean isNotDisplayed() not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_have_options(String... options) {
		throw new AssertionError("operation has_options not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean exists() {
		return false;
	}
	public boolean does_not_exist() {
		return !exists();
	}
	public boolean is_null() {
		return true;
	}
	public boolean is_not_null() {
		return ! is_null();
	}
	public boolean exists_immediate() {
		throw new AssertionError("operation exists_immediate not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean isEnabled() {
		throw new AssertionError("operation isEnabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean isDisabled() {
		throw new AssertionError("operation isDisabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void enter_text_if_enabled(String text) {
		throw new AssertionError("operation enter_text_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void append_text_if_enabled(String text) {
		throw new AssertionError("operation append_text_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void select_option_if_enabled(String text) {
		throw new AssertionError("operation select_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void select_code_if_enabled(String text) {
		throw new AssertionError("operation select_code_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void click_if_enabled() {
		throw new AssertionError("operation click_if_enabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void click_if_exists() {
	}
	public void should_have_text(String... expected_texts) {
		throw new AssertionError("operation should_have_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_not_have_text(String... expected_texts) {
		throw new AssertionError("operation should_not_have_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean has_text(String... expected_texts) {
		throw new AssertionError("operation has_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public BrowserElement found(By element_locator) {
		throw new AssertionError("operation found By not allowed on a NullBrowserElement '" + this + "'");
	}
	public BrowserElement browser_is(Browser browser) {
		throw new AssertionError("operation browser_is not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_be_enabled() {
		throw new AssertionError("operation should_be_enabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_be_disabled() {
		throw new AssertionError("operation should_be_disabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_have_hover_text(String text) {
		throw new AssertionError("operation should_have_hover_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public void driver_is(WebDriver driver) {
	}
	public String toString() {
		return this.getClass().getName();
	}
	public void click_if_exists_and_enabled() {
	}
	public boolean isDisplayed() {
		throw new AssertionError("operation isDisplayed not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_be_displayed() {
		throw new AssertionError("operation should_be_displayed not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_not_be_displayed() {
		throw new AssertionError("operation should_not_be_displayed not allowed on a NullBrowserElement '" + this + "'");
	}
	public List<String> get_all_options() {
		throw new AssertionError("operation get_all_options not allowed on a NullBrowserElement '" + this + "'");
	}
}