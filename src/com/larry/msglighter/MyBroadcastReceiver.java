package com.larry.msglighter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.larry.msglighter.db.DatabaseHelper;

public class MyBroadcastReceiver extends BroadcastReceiver {
	
    // action 名称
    String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED" ;
    
 
    public void onReceive(Context context, Intent intent) {
    	DatabaseHelper dbHelper = new DatabaseHelper(context,"firstdb");
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	Cursor cursor = db.query(true, "user", new String[]{"id","mode","checked"}, "id=?", new String[]{"1"}, null, null, "1,2", null);
    		cursor.moveToNext();
    		String mode = cursor.getString(cursor.getColumnIndex("mode"));
    		String check = cursor.getString(cursor.getColumnIndex("checked"));
    		long int1 = Long.parseLong(mode);
    		long int2 = Long.parseLong(check);

		
		
       if ((  int2 == 1  ) && (  intent.getAction().equals( SMS_RECEIVED )  )) 
       {
           // 相关处理 : 地域变换、电量不足、来电来信；
    	   //Toast.makeText(context, "来短信了", Toast.LENGTH_LONG).show();
    	   
    	   if(int1 == 1)//点亮但不解锁
    	   {
    		   Toast.makeText(context, "New Message!" , Toast.LENGTH_SHORT).show();
    		   Intent serIntent1 = new Intent();
        	   serIntent1.setClass(context, ScreenService1.class);
        	   context.startService(serIntent1);
    	   }
    	   else if(int1 == 2)//点亮并解锁
           {
        	   Toast.makeText(context, "New Message!" , Toast.LENGTH_LONG).show();
        	   Intent serIntent2 = new Intent();
               serIntent2.setClass(context, ScreenService2.class);
               context.startService(serIntent2);
           }
    	   else
    	   {
    		   Toast.makeText(context, "something's wrong.." , Toast.LENGTH_SHORT).show();
    	   }
       }
   		if (!cursor.isClosed()) 
		{
			cursor.close();
		}
		if(db!= null)   
	    {  
	       db.close();
	    } 
    }
}