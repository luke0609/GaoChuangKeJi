package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jiaokaokeji.gaochuangkeji.R;

public class ProblemActivity extends AppCompatActivity {
//常见问题
private final String[] array = {"Hello", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome", "World", "Android", "is", "Awesome"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, array);
//        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

      //  expandableLayoutListView.setAdapter(arrayAdapter);
    }
}
