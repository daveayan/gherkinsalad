package daveayan.gherkinsalad.components.jqueryui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.BaseTest;
import daveayan.gherkinsalad.components.DatePicker;
import daveayan.gherkinsalad.components.TextEnterable;
import daveayan.gherkinsalad.components.html.TextField;

public class DatePickerTest extends BaseTest {
	@Test
	public void firefox_jquery_ui_default_date_picker_test() {
		super.launch_browser("chrome");
		super.goto_url("http://jqueryui.com/demos/datepicker/");
		
		DatePicker datepicker = new DefaultDatePicker();
		TextEnterable date_textbox = TextField.find(By.id("datepicker"));
		
		// #########
		
		final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date(), new Date());
		date_textbox.should_have_text(df.format(new Date()));
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/06/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/07/2012"), new Date());
		date_textbox.should_have_text("07/07/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/08/2012"), new Date());
		date_textbox.should_have_text("07/08/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/09/2012"), new Date());
		date_textbox.should_have_text("07/09/2012");
		
		// #########
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/06/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/07/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/08/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/09/2012"), new Date());
		date_textbox.should_have_text("07/09/2012");
		
		// #########
		Calendar two_years_from_today = Calendar.getInstance();
		two_years_from_today.add(Calendar.YEAR, 2);
		two_years_from_today.add(Calendar.MONTH, 2);
		two_years_from_today.set(Calendar.DAY_OF_MONTH, 1);
		date_textbox.click_if_enabled();
		datepicker.select_date(two_years_from_today.getTime(), new Date());
		date_textbox.should_have_text(df.format(two_years_from_today.getTime()));
		
		Calendar two_years_before_today = Calendar.getInstance();
		two_years_before_today.add(Calendar.YEAR, -2);
		two_years_before_today.add(Calendar.MONTH, -2);
		two_years_before_today.set(Calendar.DAY_OF_MONTH, 1);
		date_textbox.click_if_enabled();
		datepicker.select_date(two_years_before_today.getTime(), new Date());
		date_textbox.should_have_text(df.format(two_years_before_today.getTime()));
	}
}