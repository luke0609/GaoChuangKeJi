package com.jiaokaokeji.gaochuangkeji.myclass.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.myclass.Activity.Simulation.Km2ExamActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SimulationActivity extends AppCompatActivity {


    @InjectView(R.id.ll1)
    RelativeLayout ll1;
    @InjectView(R.id.ll2)
    RelativeLayout ll2;
    @InjectView(R.id.ll3)
    RelativeLayout ll3;
    @InjectView(R.id.ll4)
    RelativeLayout ll4;
    @InjectView(R.id.ll5)
    RelativeLayout ll5;
    @InjectView(R.id.ll6)
    RelativeLayout ll6;

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


    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                Intent intent1=new Intent(SimulationActivity.this, Km2ExamActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll2:
                break;
            case R.id.ll3:
                break;
            case R.id.ll4:
                break;
            case R.id.ll5:
                break;
            case R.id.ll6:
                break;
        }
    }
}
