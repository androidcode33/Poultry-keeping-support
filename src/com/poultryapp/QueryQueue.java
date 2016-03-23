package com.poultryapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class QueryQueue extends Activity {
	Date dat = new Date();
	SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
    String date = sdf.format(dat);
    String r;
	SQLiteDatabase storeData;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_2);
        final ListView list =(ListView) findViewById(R.id.lv);
        
        final List<String> elements = new ArrayList<String>();
        storeData=openOrCreateDatabase("Poultry_data",MODE_PRIVATE,null);
        Cursor note=storeData.rawQuery("select * from AccountVaccine", null);
		note.moveToFirst();
		do{
				if(date.equals(note.getString(2))){
				r = note.getString(0)+".\nHas Pending Event";
				elements.add(r);
				}
			
		}while(note.moveToNext());
		
		final ArrayAdapter<String> arrayA = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,  elements );
        list.setAdapter(arrayA);
        list.setClickable(true);
        
        
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                Intent intent = new Intent(QueryQueue.this,Accounts.class);
                
                Iterator<String> itr = elements.iterator();
                int i=0,leng=elements.size();
                String[] items=new String[leng];// = null;
                while(itr.hasNext()) {
                items[i] = itr.next().toString();
                i++;
                }
                
                intent.putExtra("id", items[position]);
                startActivity(intent);

            }
        });
		
	}
}
