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

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.BrowserElement;
/** @author daveayan */
/**
 * Interface that indicates that a browser element has multiple options from which *Multiple* can be selected. This is an interface for the Dropdowns
 * Unless absolutely needed do not make your page object implement this interface directly. Instead make them extend Dropdown.
 */
public interface MultiOptionSelectable extends BrowserElement {
	/**
	 * Use this method to select the option by text in the dropdown
	 * @param option
	 */
	public void select_option_if_enabled(String option);
	/**
	 * Use this method to select the option by code in the dropdown
	 * @param code
	 */
	public void select_code_if_enabled(String code);
	/**
	 * Asserts that all the options provided as input exist in the dropdown. The dropdown may have more options which is ok but it has to have these.
	 * @param options
	 */
	public void should_have_all_these(String... options);
	/**
	 * Use this method to get the text of the currently selected options if the control allows multiple selections. If the control allows single selection this returns 
	 * a Strings object with just one string in there
	 * @return
	 */
	/**
	 * Asserts that at least one of the options provided as input exist in the dropdown. The dropdown may have more options which is ok but it has to have at lease one of these.
	 * @param options
	 */
	public void should_have_any_of_these(String... options);
	/**
	 * Asserts that all the options provided as input do NOT exist in the dropdown. The dropdown may have more options which is ok but it has to have none of these.
	 * @param options
	 */
	public void should_not_have_any_of_these(String... options);
	
	/**
	 * Use this method to get a list of all the option texts in the dropdown
	 * @return
	 */
	public Strings get_all_options();
	
	public Strings get_selected_options();
	
	public void should_have_these_options_selected(String... options);
	
	public void should_not_have_these_options_selected(String... options);
}