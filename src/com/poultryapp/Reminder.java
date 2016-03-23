package com.poultryapp;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

	@SuppressLint("SimpleDateFormat")public class Reminder{
        Calendar day = Calendar.getInstance();
	    Date today= new Date();
	    SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	    String date;
	    Context context;
		static PendingIntent pendingIntent;
		static AlarmManager alarmManager;
        
	    
public String getDate(int i) {
	        i=0-i;
	        date=String.valueOf(sdf.format(today));
	        try {
	            day.setTime(sdf.parse(date));
	        } catch (ParseException ex) {
	     Logger.getLogger(Reminder.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        day.add(Calendar.DATE,i);
	        return sdf.format(day.getTime());
	    }

	public String acName(String source){
		return source.split("\\.")[0];
	}

	}

