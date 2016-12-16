package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.home.Activity.Adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProblemActivity extends AppCompatActivity {
//常见问题
   private ListView summonerList;
    private ImageView back;
    List<Map<String, Object>> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        initData();
        initview();
    }
     private void initData() {
         Map<String, Object> listItem = new HashMap<String, Object>();

     }
    private void initview() {

        summonerList = (ListView)findViewById(R.id.listView);
        summonerList.setAdapter(new ListViewAdapter(listItem,ProblemActivity.this));

        back = ((ImageView) findViewById(R.id.iv_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
