package com.larry.msglighter;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.larry.msglighter.db.DatabaseHelper;

public class RadioGroup1 extends Activity 
{
	private RadioGroup groupOne = null ; 
	private RadioButton seven = null;
	private RadioButton ten = null;
	private RadioButton thirty = null ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_groups_1);
		groupOne = (RadioGroup)findViewById(R.id.timeGroup);
		seven = (RadioButton)findViewById(R.id.seven);
		ten = (RadioButton)findViewById(R.id.ten);
		thirty = (RadioButton)findViewById(R.id.thirty);
		
		groupOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) 
			{
				// TODO Auto-generated method stub
			if(seven.getId()==checkedId) 
			{
				DatabaseHelper dbHelper = new DatabaseHelper(RadioGroup1.this,"firstdb");
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues cva = new ContentValues();
				cva.put("time", 7);
				//第一个参数：要更新的表名。第二个参数：一个ContentValues对象。第三个参数：where子句
				db.update("user", cva, "id=?", new String[]{"1"});
				
				onDestroy();
				if(db!= null)   
			    {  
			       db.close();
			    } 
				Toast.makeText(RadioGroup1.this, "设置成功！", Toast.LENGTH_LONG).show();
			}
			else if(ten.getId() == checkedId)
			{
				DatabaseHelper dbHelper = new DatabaseHelper(RadioGroup1.this,"firstdb");
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues cva = new ContentValues();
				cva.put("time", 10);
				//第一个参数：要更新的表名。第二个参数：一个ContentValues对象。第三个参数：where子句
				db.update("user", cva, "id=?", new String[]{"1"});
				
				onDestroy();
				if(db!= null)   
			    {  
			       db.close();
			    } 
				Toast.makeText(RadioGroup1.this, "设置成功！", Toast.LENGTH_LONG).show();
			}
			else if(thirty.getId() == checkedId)
			{
				DatabaseHelper dbHelper = new DatabaseHelper(RadioGroup1.this,"firstdb");
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues cva = new ContentValues();
				cva.put("time", 30);
				//第一个参数：要更新的表名。第二个参数：一个ContentValues对象。第三个参数：where子句
				db.update("user", cva, "id=?", new String[]{"1"});
				
				onDestroy();
				if(db!= null)   
			    {  
			       db.close();
			    } 
				Toast.makeText(RadioGroup1.this, "设置成功！", Toast.LENGTH_LONG).show();
			}
			else{}
			}
		});
	}
	
	
}
