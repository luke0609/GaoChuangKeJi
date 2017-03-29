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

	
	//错误题数据
//		static final String TABLE_NAME_MY_ERROR_QUESTION = "table_my_error_question";
//		static final String MY_ERROR_QUESTION_ID = "_question_id";
//		static final String MY_ERROR_QUESTION_NAME = "_question_name";
//	    static final String MY_ERROR_QUESTION_OPTION_TYPE = "_question_option_type";
//		static final String MY_ERROR_QUESTION_ANSWER = "_question_answer";
//		static final String MY_ERROR_QUESTION_SELECTED = "_question_selected";
//	    //static final String MY_ERROR_QUESTION_TYPE = "_question_type";
//		static final String MY_ERROR_QUESTION_ISRIGHT = "_question_isright";
//		static final String MY_ERROR_QUESTION_ANALYSIS = "_question_analysis";
//		static final String MY_ERROR_QUESTION_OPTION_A = "_question_option_a";
//		static final String MY_ERROR_QUESTION_OPTION_B = "_question_option_b";
//		static final String MY_ERROR_QUESTION_OPTION_C = "_question_option_c";
//		static final String MY_ERROR_QUESTION_OPTION_D = "_question_option_d";
//	    static final String MY_ERROR_QUESTION_URL= "_question_url";

	
	
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
////创建错题数据表
//		String cerateTable_myErrorQuestion = "CREATE TABLE "
//				+ TABLE_NAME_MY_ERROR_QUESTION + " ("
//				+ MY_ERROR_QUESTION_ID
//				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
//				+ MY_ERROR_QUESTION_NAME + " TEXT,"
//				+ MY_ERROR_QUESTION_ANSWER + " TEXT,"
//				//+ MY_ERROR_QUESTION_TYPE + " TEXT,"
//				+ MY_ERROR_QUESTION_SELECTED + " TEXT,"
//				+ MY_ERROR_QUESTION_ISRIGHT + " TEXT,"
//				+ MY_ERROR_QUESTION_ANALYSIS + " TEXT,"
//				+ MY_ERROR_QUESTION_OPTION_A + " TEXT,"
//				+ MY_ERROR_QUESTION_OPTION_B + " TEXT,"
//				+ MY_ERROR_QUESTION_OPTION_C + " TEXT,"
//				+ MY_ERROR_QUESTION_OPTION_TYPE + " TEXT,"
//				+ MY_ERROR_QUESTION_URL+"TEXT,"
//				+ MY_ERROR_QUESTION_OPTION_D + " TEXT)";
		
		

		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
