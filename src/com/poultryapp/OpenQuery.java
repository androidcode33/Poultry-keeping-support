package com.poultryapp;

import java.util.ArrayList;
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


public class OpenQuery extends Activity {
	SQLiteDatabase storeData;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open);
        
        final ListView ab =(ListView) findViewById(R.id.lv);
        
        final List<String> aa = new ArrayList<String>();
        
        storeData=openOrCreateDatabase("Poultry_data",MODE_PRIVATE,null);
        Cursor c=storeData.rawQuery("select accName, accType from Daccount", null);
		c.moveToFirst();
		do{
			String r =c.getString(0)+"."+"\n"+c.getString(1);
			aa.add(r);
		}while(c.moveToNext());
		
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,  aa );
        ab.setAdapter(arrayAdapter);
        ab.setClickable(true);
        
        
        ab.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) 
            {
                Intent intent = new Intent(OpenQuery.this,Accounts.class);
                
                Iterator<String> itr = aa.iterator();
                int i=0,leng=aa.size();
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
