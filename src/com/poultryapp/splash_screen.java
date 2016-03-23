package com.poultryapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

	 
public class splash_screen  extends Activity {
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splash_screen);
	         /****** Create Thread that will sleep for 5 seconds *************/        
	        Thread background = new Thread() {
	            public void run() {
	                 
	                try {
	                    // Thread will sleep for 5 seconds
	                    sleep(2000);
	                     
	                    // After 5 seconds redirect to another intent
	                    Intent i=new Intent(getBaseContext(),MainActivity.class);
	                    startActivity(i);
	                     
	                    //Remove activity
	                    finish();
	                     
	                } catch (Exception e) {
	                 
	                }
	            }
	        };
	         
	        // start thread
	        background.start();
	   }
	    
}
