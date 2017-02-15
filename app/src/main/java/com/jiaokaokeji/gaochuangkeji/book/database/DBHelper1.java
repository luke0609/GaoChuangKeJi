package com.jiaokaokeji.gaochuangkeji.book.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/2/14.
 */

public class DBHelper1 extends SQLiteOpenHelper {
    //数据库名称
    static final String DB_NAME = "examination_question1.db";
    static final int VERSION = 1;

    //题库数据
//    static final String TABLE_NAME_TEST_LIBRARY = "examination_question1";
//    //static final String TEST_LIBRARY_COL_ID = "iid";
//    static final String TEST_LIBRARY_QUESTION_ID ="_question_id";
//    static final String TEST_LIBRARY_QUESTION_NAME = "_question_name";
//    static final String TEST_LIBRARY_QUESTION_ANSWER = "_question_answer";
//    static final String TEST_LIBRARY_QUESTION_ANALYSIS = "_question_analysis";
//    static final String TEST_LIBRARY_QUESTION_OPTION_A = "_question_option_a";
//    static final String TEST_LIBRARY_QUESTION_OPTION_B = "_question_option_b";
//    static final String TEST_LIBRARY_QUESTION_OPTION_C = "_question_option_c";
//    static final String TEST_LIBRARY_QUESTION_OPTION_D = "_question_option_d";
//    static final String TEST_LIBRARY_QUESTION_URL= "_question_url";
    public DBHelper1(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建题库基本数据表语句
        String sql="create table examination_question1(" +
                "_question_id integer primary key autoincrement," +
                "_question_name varchar(200)," +
                "_question_answer varchar(20)," +
                "_question_analysis varchar(200)," +
                "_question_option_a varchar(20)," +
                "_question_option_b varchar(20)," +
                "_question_option_c varchar(20)," +
                "_question_option_d varchar(20)," +
                "_question_url varchar(20)" +
                ")";
//        String cerateTable_testLibrary = "CREATE TABLE "
//                + TABLE_NAME_TEST_LIBRARY + " ("
//                + TEST_LIBRARY_QUESTION_ID + " TEXT,"
//                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + TEST_LIBRARY_QUESTION_NAME + " TEXT,"
//                + TEST_LIBRARY_QUESTION_ANSWER + " TEXT,"
//                + TEST_LIBRARY_QUESTION_ANALYSIS + " TEXT,"
//                + TEST_LIBRARY_QUESTION_OPTION_A + " TEXT,"
//                + TEST_LIBRARY_QUESTION_OPTION_B + " TEXT,"
//                + TEST_LIBRARY_QUESTION_OPTION_C + " TEXT,"
//                +TEST_LIBRARY_QUESTION_URL+"TEXT,"
//                + TEST_LIBRARY_QUESTION_OPTION_D + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
