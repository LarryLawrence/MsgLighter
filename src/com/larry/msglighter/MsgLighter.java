package com.larry.msglighter;


import java.io.File;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.larry.msglighter.db.DatabaseHelper;

public class MsgLighter extends Activity {
	private Button button1 = null ; 
	private Button button2 = null ; 
	private CheckBox box1 = null ; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg_lighter);
		Context ctx = MsgLighter.this;

		
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		button1.setOnClickListener(new Button1Listener());
		button2.setOnClickListener(new Button2Listener());
		box1 = (CheckBox)findViewById(R.id.box1);
		
		File dbFile = new File("/data/data/com.larry.msglighter/databases");
		if(!dbFile.exists() && !dbFile.isDirectory())
		{
			DatabaseHelper dbHelper = new DatabaseHelper(MsgLighter.this,"firstdb");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
//			Toast.makeText(ctx, "创建数据库！", Toast.LENGTH_LONG).show();
			ContentValues cvalues = new ContentValues();
			cvalues.put("id",1);
			cvalues.put("mode", 1);//程序默认使用mode1	
			cvalues.put("time", 7);
			cvalues.put("checked", 1);
			db.insert("user", null , cvalues);
		
			
			onDestroy();
			if(db!= null)   
		    {  
		       db.close();
		    } 
			
		}
		
		box1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
			{
				if(isChecked)
				{
					DatabaseHelper dbHelper = new DatabaseHelper(MsgLighter.this,"firstdb");
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					ContentValues cva = new ContentValues();
					cva.put("checked", 1);
					db.update("user", cva, "id=?", new String[]{"1"});
					
					onDestroy();
					if(db!= null)   
				    {  
				       db.close();
				    } 
					Toast.makeText(MsgLighter.this, "已启用！", Toast.LENGTH_SHORT).show();
				}
				else
				{
					DatabaseHelper dbHelper = new DatabaseHelper(MsgLighter.this,"firstdb");
					SQLiteDatabase db = dbHelper.getWritableDatabase();
					ContentValues cva = new ContentValues();
					cva.put("checked", 0);
					db.update("user", cva, "id=?", new String[]{"1"});
					
					onDestroy();
					if(db!= null)   
				    {  
				       db.close();
				    } 
					Toast.makeText(MsgLighter.this, "已禁用！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		DatabaseHelper dbHelper = new DatabaseHelper(MsgLighter.this,"firstdb");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(true, "user", new String[]{"id","checked"}, "id=?", new String[]{"1"}, null, null, "1,2", null);
		cursor.moveToNext();
		String check = cursor.getString(cursor.getColumnIndex("checked"));
		long int1 = Long.parseLong(check);
		if(int1 == 1)
			box1.setChecked(true);
		
		
		if (!cursor.isClosed()) 
		{
			cursor.close();
		}
		if(db!= null)   
	    {  
	       db.close();
	    }
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	class Button1Listener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			Context ctx = MsgLighter.this;
			DatabaseHelper dbHelper = new DatabaseHelper(ctx,"firstdb");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put("mode", 1);
			db.update("user", cv, "id=?", new String[]{"1"});
			
			onDestroy();
			if(db!= null)   
		    {  
		       db.close();
		    } 
			Toast.makeText(ctx, "设置成功！", Toast.LENGTH_LONG).show();
		}
	}

	public class Button2Listener implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			DatabaseHelper dbHelper = new DatabaseHelper(MsgLighter.this,"firstdb");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues cva = new ContentValues();
			cva.put("mode", 2);
			db.update("user", cva, "id=?", new String[]{"1"});
			
			onDestroy();
			if(db!= null)   
		    {  
		       db.close();
		    } 
			Context ctx = MsgLighter.this;
			Toast.makeText(ctx, "设置成功！", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.msg_lighter, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
			case R.id.menu_settings:
				showSettings();	
				break;
//			case R.id.menu_about:
//					break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void showSettings()
	{
		final Intent intent = new Intent(this, SettingsActivity.class);

		startActivity(intent);
	}
	
	
}
