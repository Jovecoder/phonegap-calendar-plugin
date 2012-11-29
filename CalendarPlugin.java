package com.redobot.plugin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.provider.CalendarContract.Events;

public class CalendarPlugin extends CordovaPlugin {
	final static String ISO8601DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		if (action.equals("createEvent")) {
			try {
				this.createEvent(args.getString(0), args.getString(1), args.getString(2), args.getString(3), args.getString(4));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	private void createEvent(String title, String location, String description, String startDate, String endDate){
		Calendar calendarStart = CalendarPlugin.getCalendarFromISO(startDate);
		Calendar calendarEnd = CalendarPlugin.getCalendarFromISO(endDate);
		
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra(Events.TITLE, title);
		intent.putExtra(Events.EVENT_LOCATION, location);
		intent.putExtra(Events.DESCRIPTION, description);
		intent.putExtra("beginTime", calendarStart.getTimeInMillis());
		intent.putExtra("endTime", calendarEnd.getTimeInMillis());
		
		this.cordova.getActivity().startActivity(intent);
	}
	
	public static Calendar getCalendarFromISO(String dateString) {
		dateString = dateString.trim().replaceAll(":00$", "00"); // Changing format for SimpleDateFormat
		
	    Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
	    SimpleDateFormat dateformat = new SimpleDateFormat(ISO8601DATEFORMAT, Locale.getDefault());
	    
	    try {
	    	Date date = dateformat.parse(dateString);
	    	calendar.setTime(date);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return calendar;
  }
	
}
