package com.poultryapp;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CreateAccount extends Activity {
	Context context;
	static PendingIntent pendingIntent;
	static AlarmManager alarmManager;
	
	String acName, pType,growthStageType="DAYS";
	int growthStage, hold;
	SQLiteDatabase storeData;
	//boilers vaccines
	String new_1,new_2,gum_1,infect,gum_2;
	//layer vaccines
	String mar,fowl_pox,foul_t,infe_2,new_3,fowl_t2;
	Reminder dc= new Reminder();
	//Broilers
	String starter, grower, finisher;
	//layers
	String Chick_and_duck,Pre_grower,lgrower,Pre_layer,lfinisher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        
        context=CreateAccount.this;
		Intent intentsOpen = new Intent(this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(this,111, intentsOpen, 0);
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        
        final EditText aName=(EditText) findViewById(R.id.aName);
        final EditText gStage=(EditText) findViewById(R.id.gStage);
        final RadioGroup stageType= (RadioGroup) findViewById(R.id.stageType);
        	final RadioButton days=(RadioButton) findViewById(R.id.days);
        	final RadioButton weeks=(RadioButton) findViewById(R.id.weeks);
        final RadioGroup breed=(RadioGroup) findViewById(R.id.Poultrytype);
        	final RadioButton lay=(RadioButton) findViewById(R.id.layers);
        	final RadioButton bro=(RadioButton) findViewById(R.id.broilors);
        Button sub=(Button) findViewById(R.id.submit);
        Button can=(Button) findViewById(R.id.Cancel);
        
        sub.setOnClickListener(new v.OnClickListener() {
			
			public void onClick(View v) {
				
				try{
					acName=aName.getText().toString();
					
					//Growth stage
					hold=Integer.parseInt(gStage.getText().toString());
					int select2=stageType.getCheckedRadioButtonId();
						if(select2==days.getId()){
							growthStage=hold;
						}else if(select2==weeks.getId()){
							growthStage=hold*7;
						}
					//Radio group for breed or type of poultry
					int select1=breed.getCheckedRadioButtonId();
					if(select1==lay.getId()){
					pType="Layers";
					mar=dc.getDate(growthStage); setReminder(growthStage);
					new_1=dc.getDate(growthStage-7); setReminder(growthStage-7);
					gum_1=dc.getDate(growthStage-14); setReminder(growthStage-14);
					infect=dc.getDate(growthStage-21); setReminder(growthStage-21);
					gum_2=dc.getDate(growthStage-28); setReminder(growthStage-28);
					fowl_pox=dc.getDate(growthStage-42); setReminder(growthStage-42);
					foul_t=dc.getDate(growthStage-56); setReminder(growthStage-56);
					new_2=dc.getDate(growthStage-70); setReminder(growthStage-70);
					infe_2=dc.getDate(growthStage-91); setReminder(growthStage-91);
					new_3=dc.getDate(growthStage-112); setReminder(growthStage-112);
					fowl_t2=dc.getDate(growthStage-252); setReminder(growthStage-252);
					//feeds for layers
					Chick_and_duck=dc.getDate(growthStage);
					Pre_grower=dc.getDate(growthStage-56);
				     lgrower=dc.getDate(growthStage-63); setReminder(growthStage-63);
				    Pre_layer=dc.getDate(growthStage-112);
				    lfinisher=dc.getDate(growthStage-126); setReminder(growthStage-126);
					}else if(select1==bro.getId()){
					pType="Broilers";
					new_1=dc.getDate(growthStage); setReminder(growthStage);
					new_2=dc.getDate(growthStage-7); setReminder(growthStage-7);
					gum_1=dc.getDate(growthStage-14); setReminder(growthStage-14);
					infect=dc.getDate(growthStage-21); setReminder(growthStage-21);
					gum_2=dc.getDate(growthStage-28); setReminder(growthStage-28);
					//feeds for broilers
					starter=dc.getDate(growthStage);
					grower=dc.getDate(growthStage-14);
					finisher=dc.getDate(growthStage-28);
					
					}				
						//Data storage
						storeData=openOrCreateDatabase("Poultry_data",MODE_PRIVATE,null);
						storeData.execSQL("create table if not exists Daccount(accName varchar primary key, accType varchar,gStage int,gsType varchar);");
						storeData.execSQL("insert into Daccount values('"+ acName +"','"+ pType+"','"+growthStage+"','"+growthStageType+"');");
						
									if(pType.equals("Broilers")){
						storeData.execSQL("create table if not exists bVacDates(acc varchar primary key,newCa_1 date,newCas_2 date,gum_1 date, infe date, gum_2 date);");
						storeData.execSQL("create table if not exists bFeeds(accName varchar primary key, brStarter date, growers date, finishers date);");
						storeData.execSQL("insert into bFeeds values('"+acName+"','"+starter+"','"+grower+"','"+finisher+"');");
						storeData.execSQL("insert into bVacDates values('"+acName+"','"+new_1+"','"+new_2+"','"+gum_1+"','"+infect+"','"+gum_2+"');");
									}
									else if(pType.equals("Layers")){
										storeData.execSQL("create table if not exists LFeeds(accName varchar primary key, chick_and_duck date,Pre_grower date, growers date,Pre_layer date,lfinishers date);");
										storeData.execSQL("insert into LFeeds values('"+acName+"','"+Chick_and_duck+"','"+Pre_grower+"','"+lgrower+"','"+ Pre_layer+"','"+lfinisher+"');");
										storeData.execSQL("create table if not exists lVacDates(accName varchar primary key,mareks date,newCa_1 date,gum_1 date,infe date, gum_2 date,fowl_p date,foul_t1 date,newCas_2 date,infe_2 date,newcas3 date,fowl_t2 date);");				
										storeData.execSQL("insert into lVacDates values('"+acName+"','"+mar+"','"+new_1+"','"+gum_1+"','"+infect+"','"+gum_2+"','"+fowl_pox+"','"+foul_t+"','"+new_2+"','"+infe_2+"','"+new_3+"','"+fowl_t2+"');");	
									}
						storeData.close();
						aName.setText("");
						gStage.setText("");
					Toast.makeText(getBaseContext(),"Account Created", Toast.LENGTH_LONG).show();
					
					Intent go= new Intent(CreateAccount.this,MainActivity.class);
					startActivity(go);
				}catch (NumberFormatException e) {
					showMessage("Error In Creating Account!","Invalid Number entered as Growth Stage. Please Try again with a valid growth stage value");
				}
				
			}
		});
        can.setOnClickListener(new v.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent go= new Intent(CreateAccount.this,MainActivity.class);
				startActivity(go);
			}
		});
        }
public void showMessage(String title,String message){
    	Builder p = new Builder(this);
    	p.setCancelable(true);
    	p.setTitle(title);
    	p.setMessage(message);
    	p.show();
    }

public void setReminder(int a) {
	a=0-a;
	if(a>=0){
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.DATE,a);
	alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
	}
}
        
    }
