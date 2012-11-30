Phonegap Calendar Plugin for Android
====================================

Simple calendar plugin for Phonegap, which adds events to Android Calendar application.

How to use
----------
Change build target for your project to at least version 14. Copy CalendarPlugin.java to `<your project>/src/com/redobot/plugin/` , 
add following line to "plugins" group in `<your project>/res/xml/config.xml`:
 
    <plugin name="CalendarPlugin" value="com.redobot.plugin.CalendarPlugin"/>. 

Add `calendar-plugin.js` to you HTML project and use following to call:

	var title = "Test Event";
	var location = "Nashville, TN";
	var description = "Very interesting event.";
	var startDateTime = "2015-09-09T16:00:00-06:00"; // ISO 8601 date
	var endDateTime = "2015-09-09T18:00:00-06:00";
	var cal = new CalendarPlugin;
	cal.createEvent(title, location, description, startDateTime, endDateTime);

