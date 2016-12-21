package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiaokaokeji.gaochuangkeji.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.iv)
    public void onClick() {
        finish();
    }
}
