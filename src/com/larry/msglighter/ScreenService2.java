package com.larry.msglighter;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

public class ScreenService2 extends Service 
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
		mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		super.onCreate();  
	}  
	@Override  
	public void onStart(Intent intent, int startId)
	{ 
		//��������
		wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "My Tag");
		//������乹�캯��ʵ�����Ѿ�����Ļ�����򿪣�����û�е����������ʱ��һ�������������ý����Ϳ��Խ�������;����ֻҪ���̲���ɱ����һֱ�����״̬����Ļ��Ϩ�𣬵�����������
		wakeLock.acquire(7000);//������Ļ����
		//��ʼ��������������������⿪������
		mKeyguardLock = mKeyguardManager.newKeyguardLock("");  
		//������ʾ��������
		mKeyguardLock.disableKeyguard();  //û����䲻��
	}
	
	@Override
	public void onDestroy() 
	{
		wakeLock.release();//���ǹص���Ļ���ǹرձ��ֱ�����Ļ����(�ͷ���Ļ������)
		super.onDestroy();
	}  
}
