package com.poultryapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		generateNotification(context,"Check app for Required Activity");
	}
	
	@SuppressWarnings("deprecation")
	private void generateNotification(Context context, String message) {
		  String title = context.getString(R.string.app_name)+" Reminder";
		  String subTitle = "Activity Pending. Tap to view details";
		  int icon = R.drawable.ic_launcher;
		  long when = System.currentTimeMillis();
		  NotificationManager notificationManager = (NotificationManager) context
		    .getSystemService(Context.NOTIFICATION_SERVICE);
		  
		  Notification notification = new Notification(icon, message, when);
		  
		  Intent notificationIntent = new Intent(context, QueryQueue.class);
		  notificationIntent.putExtra("content", message);
		  PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
		  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
		    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		 
		  notification.setLatestEventInfo(context, title, subTitle, intent);
		  //To play the default sound with your notification:
		  notification.defaults |= Notification.DEFAULT_SOUND;
		  notification.flags |= Notification.FLAG_AUTO_CANCEL;
		  notification.defaults |= Notification.DEFAULT_VIBRATE;
		  notificationManager.notify(0, notification);
		 
    }

}
