package com.chen.fifth.test;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class LogContentProvider extends ContentProvider {

	SQLiteDatabase database;
	LogHelper helper;

	public final static String AUTHORITY = "com.nineteen.myandroid.LogContentProvider";

	public final static Uri LOG_URI = Uri.parse("content://" + AUTHORITY + "/"
			+ LogHelper.TABLE_LOG);

	// 返回码，all:操作全部；one:条件
	public final static int LOG_TABLE = 0;
	public final static int LOG_RECORD = 1;

	static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static {
		// 添加Uri,#匹配数字，*匹配所有的值
		uriMatcher.addURI(AUTHORITY, LogHelper.TABLE_LOG, LOG_TABLE);
		// 这个uri相当于可以在student后面加上/id。
		// 例： uri:content://com.nineteen.myandroid.LogContentProvider/tbLog/123
		uriMatcher.addURI(AUTHORITY, LogHelper.TABLE_LOG + "/#", LOG_RECORD);
	}

	// 创建数据源
	@Override
	public boolean onCreate() {
		helper = new LogHelper(getContext());
		database = helper.getWritableDatabase();
		return database == null ? false : true;
	}

	// 查询操作
	// projection：列的名字，为 null：查询所有列
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case LOG_TABLE:
			// 查询学生表中所有信息
			cursor = database.query(LogHelper.TABLE_LOG, projection, selection,
					selectionArgs, null, null, sortOrder);
			break;
		case LOG_RECORD:
			cursor = queryRecordBySelection(uri, projection, selection,
					selectionArgs, sortOrder, LogHelper.TABLE_LOG);
			break;
		}
		return cursor;
	}

	/**
	 * 返回的是此uri对应资源的MIME类型
	 */
	@Override
	public String getType(Uri uri) {
		// 查询一张表：content://com.nineteen.myandroid.LogContentProvider/tbLog
		// 根据id查询该表中的数据：content://com.nineteen.myandroid.LogContentProvider/tbLog/123
		switch (uriMatcher.match(uri)) {
		case LOG_TABLE:
			// 代表一张表
			return "vnd.android.cursor.dir/" + AUTHORITY;
		case LOG_RECORD:
			// 代表一条记录
			return "vnd.android.cursor.item/" + AUTHORITY;
		}
		return "*/*";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		Uri insertUri = null;
		long id = -1;
		switch (uriMatcher.match(uri)) {
		case LOG_TABLE:
		case LOG_RECORD:
			// 插入一条记录
			// 返回的是新插入行的rowId，失败返回-1
			id = database.insert(LogHelper.TABLE_LOG, null, values);
			System.out.println("studetn_rowId = " + id);
			insertUri = ContentUris.withAppendedId(LOG_URI, id);
			break;
		}
		if (id > 0 && insertUri != null) {
			// 插入成功
			getContext().getContentResolver().notifyChange(insertUri, null);
		}
		return insertUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		int count = 0;
		switch (uriMatcher.match(uri)) {
		case LOG_TABLE:
			// 删除所有
			count = database.delete(LogHelper.TABLE_LOG, selection,
					selectionArgs);
			break;
		case LOG_RECORD:
			count = deleteRecordBySelection(uri, selection, selectionArgs,
					LogHelper.TABLE_LOG);
			break;
		}
		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
		case LOG_TABLE:
			count = database.update(LogHelper.TABLE_LOG, values, selection,
					selectionArgs);
			break;
		case LOG_RECORD:
			// 更新某条信息
			count = updateRecordBySelection(uri, values, selection,
					selectionArgs, LogHelper.TABLE_LOG);
			break;

		}
		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	public int updateRecordBySelection(Uri uri, ContentValues values,
			String selection, String[] selectionArgs, String tableName) {
		int count = 0;
		// 根据条件更新某些行
		// 从uri中解析出id值
		long id = ContentUris.parseId(uri);
		String[] selectionArgs_db = null;
		// 根据stu_id和selection查询学生表中某条信息
		if (selection == null) {
			// 删除条件，使用占位符
			selection = "id = ?";
			selectionArgs_db = new String[1];
			selectionArgs_db[0] = "" + id;
		} else {
			// 有删除条件时，在原删除条件和删除条件对应的值的基础上加上表中主键和其对应的值
			selection = selection + "and id = ?";
			selectionArgs_db = new String[selectionArgs.length + 1];
			for (int i = 0; i < selectionArgs.length; i++) {
				selectionArgs_db[i] = selectionArgs[i];
			}
			selectionArgs_db[selectionArgs.length] = "" + id;
		}
		count = database.update(tableName, values, selection, selectionArgs_db);
		return count;
	}

	public int deleteRecordBySelection(Uri uri, String selection,
			String[] selectionArgs, String tableName) {
		int count = 0;
		// 删除某一行
		// 从uri中解析出id值
		long id = ContentUris.parseId(uri);
		String[] selectionArgs_db = null;
		// 根据stu_id和selection查询学生表中某条信息
		if (selection == null) {
			// 删除条件，使用占位符
			selection = "id = ?";
			selectionArgs_db = new String[1];
			selectionArgs_db[0] = "" + id;
		} else {
			// 有删除条件时，在原删除条件和删除条件对应的值的基础上加上表中主键和其对应的值
			selection = selection + "and id = ?";
			selectionArgs_db = new String[selectionArgs.length + 1];
			for (int i = 0; i < selectionArgs.length; i++) {
				selectionArgs_db[i] = selectionArgs[i];
			}
			selectionArgs_db[selectionArgs.length] = "" + id;
		}
		count = database.delete(tableName, selection, selectionArgs_db);

		return count;
	}

	public Cursor queryRecordBySelection(Uri uri, String[] projection,
			String selection, String[] selectionArgs, String sortOrder,
			String tableName) {
		// 从uri中解析出id值
		Cursor cursor = null;
		long id = ContentUris.parseId(uri);
		String[] selectionArgs_db = null;
		// 根据stu_id和selection查询学生表中某条信息
		if (selection == null) {
			// 查询条件，使用占位符
			selection = "id = ?";
			selectionArgs_db = new String[1];
			selectionArgs_db[0] = "" + id;
		} else {
			// 有查询条件时，在原查询条件和查询条件对应的值的基础上加上stu_id和stu_id对应的值
			selection = selection + "and id = ?";
			selectionArgs_db = new String[selectionArgs.length + 1];
			for (int i = 0; i < selectionArgs.length; i++) {
				selectionArgs_db[i] = selectionArgs[i];
			}
			selectionArgs_db[selectionArgs.length] = "" + id;
		}
		cursor = database.query(tableName, projection, selection,
				selectionArgs_db, null, null, sortOrder);
		return cursor;
	}

}
