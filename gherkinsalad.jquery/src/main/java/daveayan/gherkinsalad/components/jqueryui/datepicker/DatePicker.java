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
package daveayan.gherkinsalad.components.jqueryui.datepicker;

import java.util.Date;
/** @author daveayan */
/**
 * Interface that describes a date picker.
 */
public interface DatePicker {
	/**
	 * Use this method to navigate to the month and year that needs to be selected and then selects the date. 
	 * If the date is on a weekend this method does not select the date. The exact implementation of what needs to be done in that
	 * case is left up to the implementors of this interface.
	 * @param date_to_select
	 * @param current_date
	 */
	public void select_date_except_weekends(Date date_to_select, Date current_date) ;
	
	/**
	 * Use this method to navigate to the month and year that needs to be selected and then selects the date. 
	 * This method selects the date even if it falls on the weekend.
	 * @param date_to_select
	 * @param current_date
	 */
	public void select_date(Date date_to_select, Date current_date) ;
}