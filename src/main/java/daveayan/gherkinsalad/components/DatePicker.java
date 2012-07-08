package daveayan.gherkinsalad.components;

import java.util.Date;

public interface DatePicker {
	public void select_date_except_weekends(Date date_to_select, Date current_date) ;
	
	public void select_date(Date date_to_select, Date current_date) ;
}