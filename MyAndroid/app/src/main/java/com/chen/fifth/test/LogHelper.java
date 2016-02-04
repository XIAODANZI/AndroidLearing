package com.chen.fifth.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LogHelper extends SQLiteOpenHelper {

	final static String DB_NAME = "log.db";
	final static int VERSION = 1;
	final static String TABLE_LOG = "tbLog";

	// 构造方法
	public LogHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	// 当创建数据库的时候
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建表和视图等
		String stuSql = "create table " + TABLE_LOG + "("
				+ "id integer primary key autoincrement,"
				+ "title char(32) not null," + "content text,"
				+ "date varchar(50) not null )";
		db.execSQL(stuSql);// 执行sql语句创建student表

	}

	// 当更新数据库的时候
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			// 先删除
			db.execSQL("drop table if exists " + TABLE_LOG + ";");
			// 再创建
			onCreate(db);
		}
	}

}
