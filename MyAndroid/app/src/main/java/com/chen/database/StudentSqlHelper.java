package com.chen.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentSqlHelper extends SQLiteOpenHelper {

	final static String DB_NAME = "student.db";
	final static int VERSION = 1;
	final static String TABLE_STUDENT_NAME = "student";
	final static String TABLE_COURSE_NAME = "course";
	final static String TABLE_SC_NAME = "sc";

	// 构造方法
	public StudentSqlHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	// 当创建数据库的时候
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建表和视图等
		String stuSql = "create table " + TABLE_STUDENT_NAME + "("
				+ "stu_id integer primary key autoincrement,"
				+ "stu_name char(32) unique not null,"
				+ "stu_gender tinyint default 0," + "stu_class int" + ")";
		String couSql = "create table " + TABLE_COURSE_NAME + "("
				+ "c_id integer primary key autoincrement,"
				+ "c_name char(32) not null)";
		String scSql = "create table " + TABLE_SC_NAME + "("
				+ "stu_id integer not null," + "c_id integer not null,"
				+ "primary key (stu_id, c_id))";
		db.execSQL(couSql);
		db.execSQL(stuSql);// 执行sql语句创建student表
		db.execSQL(scSql);

	}

	// 当更新数据库的时候
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			// 先删除
			db.execSQL("drop table if exists " + TABLE_SC_NAME + ";");
			db.execSQL("drop table if exists " + TABLE_COURSE_NAME + ";");
			db.execSQL("drop table if exists " + TABLE_STUDENT_NAME + ";");
			// 再创建
			onCreate(db);
		}
	}

}
