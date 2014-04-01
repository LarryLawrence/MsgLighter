package com.larry.msglighter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//DatabaseHelper��Ϊһ������SQLite�������࣬�ṩ��������Ĺ���
//��һ��getReadableDatabase(),getWritableDatabase(),���SQLiteDatabase����
//�ڶ����ṩ��onCreate()��onUpgade()�����ص�����
public class DatabaseHelper extends SQLiteOpenHelper
{
	public static final int VERSION = 1 ;//����Ĭ�ϰ汾1
	//��SQLiteOpenHelper�������б����иù��캯��
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) 
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	//�������������Ĺ��캯��
	public DatabaseHelper(Context context , String name )
	{
		this(context , name , VERSION );
	}
	//�������������Ĺ��캯��
	public DatabaseHelper(Context context, String name ,int version)
	{
		this(context , name , null , version);
	}
	//��һ�δ������ݿ��ʱ��ִ�У���һ�εõ�SQLiteDatabase�����ʱ�����
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
//		db.execSQL("create table user(id int , name varchar(20))");//varchar�洢�ɱ䳤�ַ���20�����20���ַ�
		db.execSQL("create table user(id int , mode int , time int , checked int)");
		System.out.println("create a new datebase");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		System.out.println("upgrade a database");
	}

}
