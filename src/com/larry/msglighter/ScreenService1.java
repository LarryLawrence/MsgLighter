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
	//�������̹�����
	KeyguardManager mKeyguardManager = null;    
	//����������
	private KeyguardLock mKeyguardLock = null;  
	//������Դ������
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
		//��ȡ��Դ�ķ���
		pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		//��ȡϵͳ����
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
		long ttt = Long.parseLong(time);//�����
		long ttt2 = ttt*1000; 
		
//		Toast.makeText(ScreenService1.this, time+"����" , Toast.LENGTH_LONG).show();
		//��������
		wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
		//������乹�캯��ʵ�����Ѿ�����Ļ�����򿪣�����û�е����������ʱ��һ�������������ý����Ϳ��Խ�������;����ֻҪ���̲���ɱ����һֱ�����״̬����Ļ��Ϩ�𣬵�����������
		wakeLock.acquire(ttt2);//������Ļ����
		//��ʼ��������������������⿪������
//		mKeyguardLock = mKeyguardManager.newKeyguardLock("");  
		//������ʾ��������
//		mKeyguardLock.disableKeyguard();  //û����䲻��
		
		
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
		wakeLock.release();//���ǹص���Ļ���ǹرձ��ֱ�����Ļ����(�ͷ���Ļ������)
		super.onDestroy();
	}  
}
