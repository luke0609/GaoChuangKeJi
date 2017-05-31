package com.jiaokaokeji.gaochuangkeji.book.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.MainActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.Radom1Activity;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.ErrorQuestionInfo;

import java.util.ArrayList;

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
	public AnSwerInfo.DataBean[] queryAllData1() {
		Cursor result = database.query("examination_question1",
				null, null, null, null,
				null, null);
		return ConvertToQuestion1(result);
	}
	//查询题库
	public AnSwerInfo.DataBean[] queryAllData2() {
		Cursor result = database.query("examination_question",
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
		newValues.put("_question_id", info.getQuestionId());
		newValues.put("_question_name", info.getQuestionName());
		newValues.put("_question_option_type", info.getOptionType());
		newValues.put("_question_answer", info.getQuestionAnswer());
		newValues.put("_question_selected", info.getQuestionSelect());
		newValues.put("_question_isright", info.getIsRight());
		newValues.put("_question_analysis", info.getAnalysis());
		newValues.put("_question_option_a", info.getOptionA());
		newValues.put("_question_option_b", info.getOptionB());
		newValues.put("_question_option_c", info.getOptionC());
		newValues.put("_question_option_d", info.getOptionD());
		newValues.put("_question_url", info.getUrl());
		return database.insert("table_my_error_question", null,
				newValues);
	}
	public long insertErrorQuestion1(ErrorQuestionInfo info) {
		ContentValues newValues = new ContentValues();
		newValues.put("_question_id", info.getQuestionId());
		newValues.put("_question_name", info.getQuestionName());
		newValues.put("_question_option_type", info.getOptionType());
		newValues.put("_question_answer", info.getQuestionAnswer());
		newValues.put("_question_selected", info.getQuestionSelect());
		newValues.put("_question_isright", info.getIsRight());
		newValues.put("_question_analysis", info.getAnalysis());
		newValues.put("_question_option_a", info.getOptionA());
		newValues.put("_question_option_b", info.getOptionB());
		newValues.put("_question_option_c", info.getOptionC());
		newValues.put("_question_option_d", info.getOptionD());
		newValues.put("_question_url", info.getUrl());
		return database.insert("table_my_error_question1", null,
				newValues);
	}
//	//添加题库
public void insertQuestion(ArrayList<AnSwerInfo.DataBean> infoArrayList) {
	ContentValues newValues = new ContentValues();
	for (int i=0;i<infoArrayList.size();i++) {
		newValues.clear();
		newValues.put("_question_id", infoArrayList.get(i).getId());
		newValues.put("_question_name", infoArrayList.get(i).getQuestion());
		newValues.put("_question_answer", infoArrayList.get(i).getAnswer());
		newValues.put("_question_analysis", infoArrayList.get(i).getExplains());
		newValues.put("_question_option_a", infoArrayList.get(i).getItem1());
		newValues.put("_question_option_b",infoArrayList.get(i).getItem2());
		newValues.put("_question_option_c", infoArrayList.get(i).getItem3());
		newValues.put("_question_option_d", infoArrayList.get(i).getItem4());
		newValues.put("_question_url", infoArrayList.get(i).getUrl());
		database.insert("examination_question1", null,
				newValues);
	}
}
	public void insertQuestion1(ArrayList<AnSwerInfo.DataBean> infoArrayList) {
		Toast.makeText(context, infoArrayList.size()+"",
				Toast.LENGTH_LONG).show();
		ContentValues newValues = new ContentValues();
		for (int i=0;i<infoArrayList.size();i++) {
			newValues.clear();
			newValues.put("_question_id", infoArrayList.get(i).getId());
			newValues.put("_question_name", infoArrayList.get(i).getQuestion());
			newValues.put("_question_answer", infoArrayList.get(i).getAnswer());
			newValues.put("_question_analysis", infoArrayList.get(i).getExplains());
			newValues.put("_question_option_a", infoArrayList.get(i).getItem1());
			newValues.put("_question_option_b",infoArrayList.get(i).getItem2());
			newValues.put("_question_option_c", infoArrayList.get(i).getItem3());
			newValues.put("_question_option_d", infoArrayList.get(i).getItem4());
			newValues.put("_question_url", infoArrayList.get(i).getUrl());
			database.insert("examination_question", null,
					newValues);
		}
	}
	/**
	 * 删除我的错题所有数据
	 * 
	 * @return
	 */
	public void deleteAllData() {
         database.delete("table_my_error_question", null,
                null);
    }

	/**
	 * 查询全部我的错题数据
	 * 
	 * @return
	 */
	public ErrorQuestionInfo[] queryAllData() {
		Cursor result = database.query("table_my_error_question",
				null, null, null, null,
				null, null);
		return ConvertToQuestion(result);
	}
	public ErrorQuestionInfo[] queryAllData3() {
		Cursor result = database.query("table_my_error_question1",
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
			peoples[i].questionId = cursor.getString(cursor.getColumnIndex("_question_id"));
			peoples[i].questionName = cursor.getString(cursor
					.getColumnIndex("_question_name"));
			peoples[i].questionAnswer = cursor.getString(cursor
					.getColumnIndex("_question_answer"));
			peoples[i].questionSelect = cursor.getString(cursor
					.getColumnIndex("_question_selected"));
			peoples[i].isRight = cursor.getString(cursor
					.getColumnIndex("_question_isright"));
			peoples[i].Analysis = cursor.getString(cursor
					.getColumnIndex("_question_analysis"));
			peoples[i].optionA = cursor.getString(cursor
					.getColumnIndex("_question_option_a"));
			peoples[i].optionB = cursor.getString(cursor
					.getColumnIndex("_question_option_b"));
			peoples[i].optionC = cursor.getString(cursor
					.getColumnIndex("_question_option_c"));
			peoples[i].optionD = cursor.getString(cursor
					.getColumnIndex("_question_option_d"));
//			peoples[i].optionE = cursor.getString(cursor
//					.getColumnIndex(DBHelper.MY_ERROR_QUESTION_OPTION_E));
			peoples[i].optionType = cursor.getString(cursor
					.getColumnIndex("_question_option_type"));
            peoples[i].url=cursor.getString(cursor
                    .getColumnIndex("_question_url"));
			cursor.moveToNext();
		}
		return peoples;
	}

	//所有题目
	private AnSwerInfo.DataBean[] ConvertToQuestion1(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		AnSwerInfo.DataBean[] peoples = new AnSwerInfo.DataBean[resultCounts];
		for (int i = 0; i < resultCounts; i++) {
			peoples[i] = new AnSwerInfo.DataBean();
			peoples[i].setId(cursor.getString(cursor.getColumnIndex("_question_id")));
			peoples[i].setQuestion(cursor.getString(cursor.getColumnIndex("_question_name")));
			peoples[i].setExplains(cursor.getString(cursor.getColumnIndex("_question_analysis")));
			peoples[i].setAnswer(cursor.getString(cursor.getColumnIndex("_question_answer")));
			peoples[i].setItem1(cursor.getString(cursor.getColumnIndex("_question_option_a")));
			peoples[i].setItem2(cursor.getString(cursor.getColumnIndex("_question_option_b")));
			peoples[i].setItem3(cursor.getString(cursor.getColumnIndex("_question_option_c")));
			peoples[i].setItem4(cursor.getString(cursor.getColumnIndex("_question_option_d")));
			peoples[i].setUrl(cursor.getString(cursor.getColumnIndex("_question_url")));
			cursor.moveToNext();
		}
		return peoples;
	}

}
