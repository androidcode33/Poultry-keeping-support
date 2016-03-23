package com.poultryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Button createAccount=(Button) findViewById(R.id.button1);
      
      createAccount.setOnClickListener(new v.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent go= new Intent(MainActivity.this,CreateAccount.class);
			startActivity(go);
			
		}
	});
      
      Button open = (Button) findViewById(R.id.button2);
      	open.setOnClickListener(new v.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent go = new Intent(MainActivity.this,OpenQuery.class);
				startActivity(go);
				
			}
		});
      
      Button help=(Button) findViewById(R.id.button6);
      help.setOnClickListener(new v.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent go= new Intent(MainActivity.this,Help.class);
			startActivity(go);
			
		}
	});
    }

    
}
