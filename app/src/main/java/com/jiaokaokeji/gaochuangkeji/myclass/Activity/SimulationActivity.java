package com.jiaokaokeji.gaochuangkeji.myclass.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jiaokaokeji.gaochuangkeji.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimulationActivity extends AppCompatActivity {
    @InjectView(R.id.ll1)
    LinearLayout ll1;
    @InjectView(R.id.ll2)
    LinearLayout ll2;
    @InjectView(R.id.ll3)
    LinearLayout ll3;
    @InjectView(R.id.ll4)
    LinearLayout ll4;
    @InjectView(R.id.ll5)
    LinearLayout ll5;
    @InjectView(R.id.ll6)
    LinearLayout ll6;

    //模拟考试
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_simulation);
        ButterKnife.inject(this);

    }


}
