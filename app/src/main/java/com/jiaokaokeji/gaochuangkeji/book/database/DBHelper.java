package com.jiaokaokeji.gaochuangkeji.book.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * 数据库管理辅助类，主要实现数据库的创建，版本更新。
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	//数据库名称
	static final String DB_NAME = "error.db";
	static final int VERSION = 1;

	/**
	 * 构造方法
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table table_my_error_question(" +
				"_question_id PRIMARY KEY," +
				"_question_name varchar(200)," +
				"_question_answer varchar(20)," +
				"_question_option_type varchar(20),"+
				"_question_analysis varchar(200)," +
				"_question_selected varchar(20),"+
				"_question_isright varchar(20),"+
				"_question_option_a varchar(20)," +
				"_question_option_b varchar(20)," +
				"_question_option_c varchar(20)," +
				"_question_option_d varchar(20)," +
				"_question_url varchar(20)" +
				")";
String sql1="create table table_my_error_question1(" +
				"_question_id PRIMARY KEY," +
				"_question_name varchar(200)," +
				"_question_answer varchar(20)," +
				"_question_option_type varchar(20),"+
				"_question_analysis varchar(200)," +
				"_question_selected varchar(20),"+
				"_question_isright varchar(20),"+
				"_question_option_a varchar(20)," +
				"_question_option_b varchar(20)," +
				"_question_option_c varchar(20)," +
				"_question_option_d varchar(20)," +
				"_question_url varchar(20)" +
				")";
		db.execSQL(sql1);
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
