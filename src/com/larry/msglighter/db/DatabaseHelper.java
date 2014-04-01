package com.larry.msglighter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
//DatabaseHelper作为一个访问SQLite的助手类，提供两个方面的功能
//第一，getReadableDatabase(),getWritableDatabase(),获得SQLiteDatabase对象
//第二，提供了onCreate()和onUpgade()两个回调函数
public class DatabaseHelper extends SQLiteOpenHelper
{
	public static final int VERSION = 1 ;//设置默认版本1
	//在SQLiteOpenHelper的子类中必须有该构造函数
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) 
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	//接收两个参数的构造函数
	public DatabaseHelper(Context context , String name )
	{
		this(context , name , VERSION );
	}
	//接收三个参数的构造函数
	public DatabaseHelper(Context context, String name ,int version)
	{
		this(context , name , null , version);
	}
	//第一次创建数据库的时候执行，第一次得到SQLiteDatabase对象的时候调用
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
//		db.execSQL("create table user(id int , name varchar(20))");//varchar存储可变长字符，20代表最长20个字符
		db.execSQL("create table user(id int , mode int , time int , checked int)");
		System.out.println("create a new datebase");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		System.out.println("upgrade a database");
	}

}
