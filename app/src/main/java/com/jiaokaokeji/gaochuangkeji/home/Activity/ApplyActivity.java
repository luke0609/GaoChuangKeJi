package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jiaokaokeji.gaochuangkeji.R;

public class ApplyActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;

    //报名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        initview();

    }


    private void initview() {
        back = ((ImageView) findViewById(R.id.iv_back));

    }

    @Override
    public void onClick(View v) {

    }
}
