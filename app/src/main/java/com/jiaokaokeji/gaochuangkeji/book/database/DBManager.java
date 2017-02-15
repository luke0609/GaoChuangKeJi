package com.jiaokaokeji.gaochuangkeji.book.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.ErrorQuestionInfo;

/**
 * 数据库操作类
 * 
 * @author zm
 * 
 */
public class DBManager {
	private SQLiteDatabase database;
	private Context context;
	public DBManager(Context context) {
		this.context = context;
	}
	/**
	 * 打开数据库，如果不存在则创建一个数据库
	 */

	public void openDB() {
		DBHelper dbHelper = new DBHelper(context);
		if (database == null || !database.isOpen()) {
			database = dbHelper.getWritableDatabase();
		}
	}
	public void openDB1() {
		DBHelper1 dbHelper1 = new DBHelper1(context);
		if (database == null || !database.isOpen()) {
			database = dbHelper1.getWritableDatabase();
		}
	}
	/**
	 * 关闭数据库
	 */
	public void closeDB() {
		if (database != null && database.isOpen()) {
			database.close();
		}
	}

	//查询题库
	public AnSwerInfo[] queryAllData1() {
		Cursor result = database.query("examination_question1",
				null, null, null, null,
				null, null);
		return ConvertToQuestion1(result);
	}
	/**
	 * 添加一条我的错题数据
	 * 
	 * @param info
	 * @return
	 */
	public long insertErrorQuestion(ErrorQuestionInfo info) {
		ContentValues newValues = new ContentValues();

		newValues.put(DBHelper.MY_ERROR_QUESTION_NAME, info.getQuestionName());
		newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_TYPE, info.getOptionType());
		newValues.put(DBHelper.MY_ERROR_QUESTION_ANSWER, info.getQuestionAnswer());
		newValues.put(DBHelper.MY_ERROR_QUESTION_SELECTED, info.getQuestionSelect());
		newValues.put(DBHelper.MY_ERROR_QUESTION_ISRIGHT, info.getIsRight());
		newValues.put(DBHelper.MY_ERROR_QUESTION_ANALYSIS, info.getAnalysis());
		newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_A, info.getOptionA());
		newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_B, info.getOptionB());
		newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_C, info.getOptionC());
		newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_D, info.getOptionD());
		newValues.put(DBHelper.MY_ERROR_QUESTION_URL, info.getUrl());
		//newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_E, info.getOptionE());
		//newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_TYPE, info.getOptionType());

		return database.insert(DBHelper.TABLE_NAME_MY_ERROR_QUESTION, null,
				newValues);
	}
//	//添加题库
	public long insertQuestion(AnSwerInfo info) {
		ContentValues newValues = new ContentValues();
         newValues.put("_question_id",info.getQuestionId());
		newValues.put("_question_name", info.getQuestionName());
		//newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_TYPE, info.getOption_type());
		newValues.put("_question_answer", info.getCorrectAnswer());
		newValues.put("_question_analysis", info.getAnalysis());
		newValues.put("_question_option_a", info.getOptionA());
		newValues.put("_question_option_b", info.getOptionB());
		newValues.put("_question_option_c", info.getOptionC());
		newValues.put("_question_option_d", info.getOptionD());
		newValues.put("_question_url", info.getUrl());
		//newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_E, info.getOptionE());
		//newValues.put(DBHelper.MY_ERROR_QUESTION_OPTION_TYPE, info.getOption_type());

		return database.insert("examination_question1", null,
				newValues);
	}
	/**
	 * 删除我的错题所有数据
	 * 
	 * @return
	 */
//	public long deleteAllData() {
//		return database.delete(DBHelper.TABLE_NAME_MY_ERROR_QUESTION, null,
//				null);
//	}

	/**
	 * 查询全部我的错题数据
	 * 
	 * @return
	 */
	public ErrorQuestionInfo[] queryAllData() {
		Cursor result = database.query(DBHelper.TABLE_NAME_MY_ERROR_QUESTION,
				null, null, null, null,
				null, null);
		return ConvertToQuestion(result);
	}

	/**
	 * 错题
	 */
	private ErrorQuestionInfo[] ConvertToQuestion(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		ErrorQuestionInfo[] peoples = new ErrorQuestionInfo[resultCounts];
		for (int i = 0; i < resultCounts; i++) {
			peoples[i] = new ErrorQuestionInfo();
			peoples[i].questionId = cursor.getInt(0);
			peoples[i].questionName = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_NAME));
//			peoples[i].questionType = cursor.getString(cursor
//					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_TYPE));
			peoples[i].questionAnswer = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_ANSWER));
			peoples[i].questionSelect = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_SELECTED));
			peoples[i].isRight = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_ISRIGHT));
			peoples[i].Analysis = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_ANALYSIS));
			peoples[i].optionA = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_A));
			peoples[i].optionB = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_B));
			peoples[i].optionC = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_C));
			peoples[i].optionD = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_D));
//			peoples[i].optionE = cursor.getString(cursor
//					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_E));
			peoples[i].optionType = cursor.getString(cursor
					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_TYPE));
			cursor.moveToNext();
		}
		return peoples;
	}

	//所有题目
	private AnSwerInfo[] ConvertToQuestion1(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		AnSwerInfo[] peoples = new AnSwerInfo[resultCounts];
		for (int i = 0; i < resultCounts; i++) {
			peoples[i] = new AnSwerInfo();
			peoples[i].questionId =cursor.getInt(0);
			peoples[i].questionType="0";
			peoples[i].questionName =cursor.getString(cursor.getColumnIndex("_question_name"));
			peoples[i].analysis = cursor.getString(cursor.getColumnIndex("_question_analysis"));
			peoples[i].correctAnswer = cursor.getString(cursor.getColumnIndex("_question_answer"));
			peoples[i].optionA =cursor.getString(cursor.getColumnIndex("_question_option_a"));
			peoples[i].optionB = cursor.getString(cursor.getColumnIndex("_question_option_b"));
			peoples[i].optionC =cursor.getString(cursor.getColumnIndex("_question_option_c"));
			peoples[i].optionD =cursor.getString(cursor.getColumnIndex("_question_option_d"));
			peoples[i].option_type ="0";
			peoples[i].url=cursor.getString(cursor.getColumnIndex("_question_url"));
		}
		return peoples;
	}
}
