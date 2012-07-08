package daveayan.gherkinsalad.components.jqueryui;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.components.DatePicker;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;
import daveayan.gherkinsalad.components.html.Link;

public class JqueryUIDefaultDatePicker extends BaseBrowserElement implements DatePicker {
	private static Log log = LogFactory.getLog(BaseBrowserElement.class);
	
	public Clickable next_month = Link.find(By.className("ui-datepicker-next"));
	public Clickable prev_month = Link.find(By.className("ui-datepicker-prev"));
	
	public void select_date_except_weekends(Date date_to_select, Date current_date) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date_to_select);
		cal2.setTime(current_date);
		
		select_date_except_weekends(cal1, cal2);
	}
	
	public void select_date(Date date_to_select, Date current_date) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date_to_select);
		cal2.setTime(current_date);
		
		select_date(cal1, cal2);
	}
	
	private void select_date_except_weekends(Calendar date_to_select, Calendar current_date) {
		if(date_to_select.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			date_to_select.add(Calendar.DATE, -1);
		}
		if(date_to_select.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			date_to_select.add(Calendar.DATE, -2);
		}
		
		select_date(date_to_select, current_date);
	}
	
	private void select_date(Calendar date_to_select, Calendar current_date) {
		int day = date_to_select.get(Calendar.DAY_OF_MONTH);
		int year = date_to_select.get(Calendar.YEAR);
		String month_name = date_to_select.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		
		log.info("date_to_select = " + date_to_select.getTime() + ", current_date = " + current_date.getTime());
		
		while(true) {
			WebElement cal_month = fetch_element().findElement(By.className("ui-datepicker-month"));
			WebElement cal_year = fetch_element().findElement(By.className("ui-datepicker-year"));
			log.info("month_name = " + month_name + ", cal_month.getText() = " + cal_month.getText() + ", year = " + year+"" + ", cal_year.getText() = " + cal_year.getText());
			if(StringUtils.equalsIgnoreCase(month_name, cal_month.getText())
					&& StringUtils.equalsIgnoreCase(year+"", cal_year.getText())) {
				break;
			}
			if(date_to_select.after(current_date)) {	
				next_month.click_if_enabled();
			} else if(date_to_select.before(current_date)) {
				prev_month.click_if_enabled();
			}
		}
		log.info("Found month and year, finding day");
		Link.find(By.linkText(day+"")).click_if_enabled();
	}
	
	public JqueryUIDefaultDatePicker() {
		found(By.id("ui-datepicker-div"));
		next_month.found(By.className("ui-datepicker-next"));
		prev_month.found(By.className("ui-datepicker-prev"));
	}
}