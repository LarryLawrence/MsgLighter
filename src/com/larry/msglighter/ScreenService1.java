package com.larry.msglighter;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.os.PowerManager;

import com.larry.msglighter.db.DatabaseHelper;

public class ScreenService1 extends Service 
{
	//声明键盘管理器
	KeyguardManager mKeyguardManager = null;    
	//声明键盘锁
	private KeyguardLock mKeyguardLock = null;  
	//声明电源管理器
	private PowerManager pm;
	private PowerManager.WakeLock wakeLock;
	
	@Override  
	public IBinder onBind(Intent arg0) 
	{  
		return null;  
	}  
	@Override  
	public void onCreate() 
	{  
		//获取电源的服务
		pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		//获取系统服务
//		mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		super.onCreate();  
		
	}  
	@Override  
	public void onStart(Intent intent, int startId)
	{ 
		
		DatabaseHelper dbHelper = new DatabaseHelper(ScreenService1.this,"firstdb");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query(true, "user", new String[]{"id","time"}, "id=?", new String[]{"1"}, null, null, "1,2", null);
		cursor.moveToNext();
		String time = cursor.getString(cursor.getColumnIndex("time"));
		long ttt = Long.parseLong(time);//你妈逼
		long ttt2 = ttt*1000; 
		
//		Toast.makeText(ScreenService1.this, time+"鸡巴" , Toast.LENGTH_LONG).show();
		//点亮亮屏
		wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
		//上面这句构造函数实际上已经把屏幕锁给打开，但是没有点亮，如果这时候按一下锁屏键，不用解锁就可以进入桌面;而且只要进程不被杀死，一直是这个状态（屏幕会熄灭，但不会锁定）
		wakeLock.acquire(ttt2);//保持屏幕常亮
		//初始化键盘锁，可以锁定或解开键盘锁
//		mKeyguardLock = mKeyguardManager.newKeyguardLock("");  
		//禁用显示键盘锁定
//		mKeyguardLock.disableKeyguard();  //没有这句不亮
		
		
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
	public void onDestroy() 
	{
		wakeLock.release();//不是关掉屏幕，是关闭保持保持屏幕唤醒(释放屏幕常亮锁)
		super.onDestroy();
	}  
}
