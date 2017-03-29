package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.adapter.MyErrorQuestionListAdapter;
import com.jiaokaokeji.gaochuangkeji.book.database.DBManager;
import com.jiaokaokeji.gaochuangkeji.book.prjo.ErrorQuestion;
import com.jiaokaokeji.gaochuangkeji.book.prjo.ErrorQuestionInfo;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MymistakesActivity extends AppCompatActivity {

    private ImageView left;
    private TextView title;

    private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();// 列表数据
    private ListView listView;

    private List<ErrorQuestion> list=new ArrayList<ErrorQuestion>();
    private ImageView iv;
    private MyErrorQuestionListAdapter adapter;

    ErrorQuestion question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymistakes);
        iv = ((ImageView) findViewById(R.id.iv));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));
        initView();
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        DBManager dbManager = new DBManager(MymistakesActivity.this);
        dbManager.openDB();

        ErrorQuestionInfo[] errorQuestionInfos = dbManager.queryAllData();
        if (errorQuestionInfos == null) {
            Toast.makeText(MymistakesActivity.this, "暂无数据",
                    Toast.LENGTH_SHORT).show();
        } else {
            Map<String, Object> map = null;
            for (int i = 0; i < errorQuestionInfos.length; i++) {
                ErrorQuestion errorQuestion = new ErrorQuestion();
                map = new HashMap<String, Object>();
                map.put("title", errorQuestionInfos[i].questionName);// 标题
                map.put("answer", errorQuestionInfos[i].questionAnswer);// 标题
                map.put("isright", errorQuestionInfos[i].isRight);//
                map.put("selected", errorQuestionInfos[i].questionSelect);//
                map.put("analysis", errorQuestionInfos[i].Analysis);//
                data.add(map);

                errorQuestion.setQuestionName(errorQuestionInfos[i].questionName);
                errorQuestion.setQuestionAnswer(errorQuestionInfos[i].questionAnswer);
                errorQuestion.setQuestionSelect(errorQuestionInfos[i].questionSelect);
                errorQuestion.setIsRight(errorQuestionInfos[i].isRight);
                errorQuestion.setAnalysis(errorQuestionInfos[i].Analysis);
                errorQuestion.setOptionA(errorQuestionInfos[i].optionA);
                errorQuestion.setOptionB(errorQuestionInfos[i].optionB);
                errorQuestion.setOptionC(errorQuestionInfos[i].optionC);
                errorQuestion.setOptionD(errorQuestionInfos[i].optionD);
                errorQuestion.setUrl(errorQuestionInfos[i].url);
                //errorQuestion.setOptionE(errorQuestionInfos[i].optionE);
                //errorQuestion.setOptionType(errorQuestionInfos[i].optionType);
                list.add(errorQuestion);
            }
            left.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    finish();
                }
            });

            adapter = new MyErrorQuestionListAdapter(this, data, listView);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(MymistakesActivity.this, MymistakesDetailActivity.class);
                    question = list.get(position);
                    intent.putExtra("questionName", question.getQuestionName());
                    intent.putExtra("option_type", question.getOptionType());
                    intent.putExtra("questionAnswer", question.getQuestionAnswer());
                    intent.putExtra("questionSelect", question.getQuestionSelect());
                    intent.putExtra("isRight", question.getIsRight());
                    intent.putExtra("Analysis", question.getAnalysis());
                    intent.putExtra("optionA", question.getOptionA());
                    intent.putExtra("optionB", question.getOptionB());
                    intent.putExtra("optionC", question.getOptionC());
                    intent.putExtra("optionD", question.getOptionD());
                    intent.putExtra("url", question.getUrl());
                    //intent.putExtra("optionE", question.getOptionE());
                    //intent.putExtra("optionType", question.getOptionType());
                    startActivity(intent);
                }
            });

        }
    }
    }

