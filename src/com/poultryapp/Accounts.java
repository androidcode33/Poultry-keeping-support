package com.poultryapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Accounts extends Activity {
	String pType;
	SQLiteDatabase storeData;
	Reminder dm=new Reminder();

	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts);
        TextView tView = (TextView) findViewById(R.id.textView1);
        
        	String b =getIntent().getExtras().getString("id");
        	String accountName=dm.acName(b);
        
        storeData=openOrCreateDatabase("Poultry_data",MODE_PRIVATE,null);
        
        Cursor a=storeData.rawQuery("select accName, accType from Daccount", null);
        a.moveToFirst();
		do{
			if(accountName.equalsIgnoreCase(a.getString(0))){
				pType=a.getString(1);
			}
		}while(a.moveToNext());
        
        //broiler vaccineDates
		if(pType.equals("Broilers")){
		Cursor c=storeData.rawQuery("select * from bVacDates", null);
		c.moveToFirst();
		do{
			if(accountName.equalsIgnoreCase(c.getString(0))){
		tView.setText("\n\t\t\t\t\tAccount: "+c.getString(0)+"\n\t!.........Vaccinating Schedule.........!\n"+"\tDate\t\t\t\t\t\t\t\tFeeds\n\t"+c.getString(1)+"\t\t\t\tNewcastle 1\n\t"+c.getString(2)+"\t\t\tNewcastle 2\n\t"+c.getString(3)+"\t\t\tGumburo 1\n\t"+c.getString(4)+"\t\t\tInfectious\n\t"+c.getString(5)+"\t\t\tGumburo 2");	
			}
		}while(c.moveToNext());
		//broiler feeds
		Cursor d=storeData.rawQuery("select * from bFeeds", null);
		d.moveToFirst();
		do{
			if(accountName.equalsIgnoreCase(d.getString(0))){
		tView.append("\n\n\t!...........Feeding Schedule...........!\n"+"\tDate\t\t\t\t\t\t\t\tFeeds\n\t"+d.getString(1)+"\t\t\tBroiler Starter\n\t"+d.getString(2)+"\t\t\tGrowers/Starter\n\t"+d.getString(3)+"\t\t\tBroiler Finisher\n\t");	
			}
		}while(d.moveToNext());
		}	else if(pType.equals("Layers")){
		//layer vaccineDates
		Cursor e=storeData.rawQuery("select * from lVacDates", null);
		e.moveToFirst();
		do{
			if(accountName.equalsIgnoreCase(e.getString(0))){
			tView.setText("\n\t\t\t\t\tAccount: "+e.getString(0)+"\n\t!.........Vaccinating Schedule.........!\n"+
					"\tDate\t\t\t\t\t\t\t\tVaccine\n\t"+e.getString(1)+"\t\t\tMareks\n\t"
					+e.getString(2)+"\t\t\tNewcastle\n\t"+e.getString(3)+"\t\t\tGumburo\n\t"+e.getString(4)+"\t\t\tInfectious\n\t"
					+e.getString(5)+"\t\t\tGumburo\n\t"+e.getString(6)+"\t\t\tFowl Pox\n\t"+e.getString(7)+"\t\t\tFowl Typhoid\n\t"
					+e.getString(8)+"\t\t\tNewcastle\n\t"+e.getString(9)+"\t\t\tInfectious 2\n\t"+e.getString(10)+"\t\t\tNewcastle\n\t"
					+e.getString(11)+"\t\t\tFowl Typhoid 2\n\n");
			}
		}while(e.moveToNext());
		//layers feed
       
		Cursor p=storeData.rawQuery("select * from LFeeds", null);
		p.moveToFirst();
		do{
			if(accountName.equalsIgnoreCase(p.getString(0))){
		tView.append("\t!...........Feeding Schedule...........!\n"+"\tDate\t\t\t\t\t\t\t\tFeeds\n\t"+p.getString(1)+"\t\t\tChick and Duck\n\t"+p.getString(2)+"\t\t\tPre-Grower mash\n\t"+p.getString(3)+"\t\t\tGrower mash\n\t"+p.getString(4)+"\t\t\tPre-Layer mash\n\t"+p.getString(5)+"\t\t\tLayer Finisher\n\t");	
			}
		}while(p.moveToNext());
		}
		storeData.close();   
	}
}
