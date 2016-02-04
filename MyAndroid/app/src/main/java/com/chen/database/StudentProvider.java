package com.chen.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentProvider extends ContentProvider {

	SQLiteDatabase database;
	StudentSqlHelper helper;

	public final static String AUTHORITY = "com.nineteen.myandroid.StudentProvider";

	public final static Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + StudentSqlHelper.TABLE_STUDENT_NAME);
	public final static Uri COURSE_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + StudentSqlHelper.TABLE_COURSE_NAME);
	public final static Uri SC_URI = Uri.parse("content://" + AUTHORITY + "/"
			+ StudentSqlHelper.TABLE_SC_NAME);

	// 返回码，all:操作全部；one:条件
	public final static int STUDENT_ALL_CODE = 0;
	public final static int STUDENT_ONE_CODE = 1;
	public final static int COURSE_ALL_CODE = 2;
	public final static int COURSE_ONE_CODE = 3;
	public final static int SC_ALL_CODE = 4;
	public final static int SC_ONE_CODE = 5;

	static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

	static {
		// 添加Uri,#匹配数字，*匹配所有的值
		uriMatcher.addURI(AUTHORITY, StudentSqlHelper.TABLE_STUDENT_NAME,
				STUDENT_ALL_CODE);
		// 这个uri相当于可以在student后面加上/id。
		//例： uri:content://com.nineteen.myandroid.StudentProvider/student/123
		uriMatcher.addURI(AUTHORITY,
				StudentSqlHelper.TABLE_STUDENT_NAME + "/#", STUDENT_ONE_CODE);
		uriMatcher.addURI(AUTHORITY, StudentSqlHelper.TABLE_COURSE_NAME,
				COURSE_ALL_CODE);
		uriMatcher.addURI(AUTHORITY, StudentSqlHelper.TABLE_COURSE_NAME + "/#",
				COURSE_ONE_CODE);
		uriMatcher.addURI(AUTHORITY, StudentSqlHelper.TABLE_SC_NAME,
				SC_ALL_CODE);
		uriMatcher.addURI(AUTHORITY, StudentSqlHelper.TABLE_SC_NAME + "/#",
				SC_ONE_CODE);
	}

	// 创建数据源
	@Override
	public boolean onCreate() {
		helper = new StudentSqlHelper(getContext());
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
		case STUDENT_ALL_CODE:
			// 查询学生表中所有信息
			cursor = database
					.query(StudentSqlHelper.TABLE_STUDENT_NAME, projection,
							selection, selectionArgs, null, null, sortOrder);
			break;
		case STUDENT_ONE_CODE:
			cursor = queryRecordBySelection(uri, projection, selection,
					selectionArgs, sortOrder,
					StudentSqlHelper.TABLE_STUDENT_NAME);
			break;
		case COURSE_ALL_CODE:
			// 查询学生表中所有信息
			cursor = database
					.query(StudentSqlHelper.TABLE_COURSE_NAME, projection,
							selection, selectionArgs, null, null, sortOrder);
			break;
		case COURSE_ONE_CODE:
			cursor = queryRecordBySelection(uri, projection, selection,
					selectionArgs, sortOrder,
					StudentSqlHelper.TABLE_COURSE_NAME);
			break;
		case SC_ALL_CODE:
			// 查询学生表中所有信息
			cursor = database.query(StudentSqlHelper.TABLE_SC_NAME, projection,
					selection, selectionArgs, null, null, sortOrder);
			break;
		case SC_ONE_CODE:
			cursor = queryRecordBySelection(uri, projection, selection,
					selectionArgs, sortOrder, StudentSqlHelper.TABLE_SC_NAME);
			break;
		}
		return cursor;
	}

	/**
	 * 返回的是此uri对应资源的MIME类型
	 */
	@Override
	public String getType(Uri uri) {
		// 查询一张表：content://com.nineteen.myandroid.StudentProvider/student
		// 根据id查询该表中的数据：content://com.nineteen.myandroid.StudentProvider/student/123
		switch (uriMatcher.match(uri)) {
		case STUDENT_ALL_CODE: // uri:content://com.nineteen.myandroid.StudentProvider/student
			// 代表一张表
			return "vnd.android.cursor.dir/" + AUTHORITY;
		case STUDENT_ONE_CODE: // uri:content://com.nineteen.myandroid.StudentProvider/student/123
			// 代表一条记录
			return "vnd.android.cursor.item/" + AUTHORITY;
		case COURSE_ALL_CODE: // uri:content://com.nineteen.myandroid.StudentProvider/course
			return "vnd.android.cursor.dir/" + AUTHORITY;
		case COURSE_ONE_CODE: // uri:content://com.nineteen.myandroid.StudentProvider/course/23
			return "vnd.android.cursor.item/" + AUTHORITY;
		case SC_ALL_CODE: // uri:content://com.nineteen.myandroid.StudentProvider/sc
			return "vnd.android.cursor.dir/" + AUTHORITY;
		case SC_ONE_CODE: // uri:content://com.nineteen.myandroid.StudentProvider/sc/12
			return "vnd.android.cursor.item/" + AUTHORITY;

		}
		return "*/*";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		Uri insertUri = null;
		long id = -1;
		switch (uriMatcher.match(uri)) {
		case STUDENT_ALL_CODE:
		case STUDENT_ONE_CODE:
			// 插入一条记录
			// 返回的是新插入行的rowId，失败返回-1
			id = database.insert(StudentSqlHelper.TABLE_STUDENT_NAME, null,
					values);
			System.out.println("studetn_rowId = " + id);
			insertUri = ContentUris.withAppendedId(STUDENT_URI, id);
			break;
		case COURSE_ALL_CODE:
		case COURSE_ONE_CODE:
			// 插入一条记录
			// 返回的是新插入行的rowId，失败返回-1
			id = database.insert(StudentSqlHelper.TABLE_STUDENT_NAME, null,
					values);
			insertUri = ContentUris.withAppendedId(COURSE_URI, id);
			break;
		case SC_ALL_CODE:
		case SC_ONE_CODE:
			// 插入一条记录
			// 返回的是新插入行的rowId，失败返回-1
			id = database.insert(StudentSqlHelper.TABLE_STUDENT_NAME, null,
					values);
			insertUri = ContentUris.withAppendedId(SC_URI, id);
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
		case STUDENT_ALL_CODE:
			// 删除所有
			count = database.delete(StudentSqlHelper.TABLE_STUDENT_NAME,
					selection, selectionArgs);
			break;
		case STUDENT_ONE_CODE:
			count = deleteRecordBySelection(uri, selection, selectionArgs,
					StudentSqlHelper.TABLE_STUDENT_NAME);
			break;
		case COURSE_ALL_CODE:
			// 删除所有
			database.delete(StudentSqlHelper.TABLE_COURSE_NAME, selection,
					selectionArgs);
			break;
		case COURSE_ONE_CODE:
			count = deleteRecordBySelection(uri, selection, selectionArgs,
					StudentSqlHelper.TABLE_COURSE_NAME);
			break;
		case SC_ALL_CODE:
			count = database.delete(StudentSqlHelper.TABLE_SC_NAME, selection,
					selectionArgs);
			break;
		case SC_ONE_CODE:
			count = deleteRecordBySelection(uri, selection, selectionArgs,
					StudentSqlHelper.TABLE_SC_NAME);
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
		case STUDENT_ALL_CODE:
			count = database.update(StudentSqlHelper.TABLE_STUDENT_NAME,
					values, selection, selectionArgs);
			break;
		case STUDENT_ONE_CODE:
			// 更新某条信息
			count = updateRecordBySelection(uri, values, selection,
					selectionArgs, StudentSqlHelper.TABLE_STUDENT_NAME);
			break;
		case COURSE_ALL_CODE:
			count = database.update(StudentSqlHelper.TABLE_COURSE_NAME, values,
					selection, selectionArgs);
			break;
		case COURSE_ONE_CODE:
			count = updateRecordBySelection(uri, values, selection,
					selectionArgs, StudentSqlHelper.TABLE_COURSE_NAME);
			break;
		case SC_ALL_CODE:
			count = database.update(StudentSqlHelper.TABLE_SC_NAME, values,
					selection, selectionArgs);
			break;
		case SC_ONE_CODE:
			count = updateRecordBySelection(uri, values, selection,
					selectionArgs, StudentSqlHelper.TABLE_SC_NAME);
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
			selection = "stu_id = ?";
			selectionArgs_db = new String[1];
			selectionArgs_db[0] = "" + id;
		} else {
			// 有删除条件时，在原删除条件和删除条件对应的值的基础上加上表中主键和其对应的值
			selection = selection + "and stu_id = ?";
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
			selection = "stu_id = ?";
			selectionArgs_db = new String[1];
			selectionArgs_db[0] = "" + id;
		} else {
			// 有删除条件时，在原删除条件和删除条件对应的值的基础上加上表中主键和其对应的值
			selection = selection + "and stu_id = ?";
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
			selection = "stu_id = ?";
			selectionArgs_db = new String[1];
			selectionArgs_db[0] = "" + id;
		} else {
			// 有查询条件时，在原查询条件和查询条件对应的值的基础上加上stu_id和stu_id对应的值
			selection = selection + "and stu_id = ?";
			selectionArgs_db = new String[selectionArgs.length + 1];
			for (int i = 0; i < selectionArgs.length; i++) {
				selectionArgs_db[i] = selectionArgs[i];
			}
			selectionArgs_db[selectionArgs.length] = "" + id;
		}
		cursor = database.query(StudentSqlHelper.TABLE_STUDENT_NAME,
				projection, selection, selectionArgs_db, null, null, sortOrder);
		return cursor;
	}

}
