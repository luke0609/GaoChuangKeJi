package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import at.markushi.ui.CircleButton;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class VoiceActivity extends AppCompatActivity {
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.btn_1)
    CircleButton btn1;
    @InjectView(R.id.btn_2)
    CircleButton btn2;
    @InjectView(R.id.btn_3)
    CircleButton btn3;
    @InjectView(R.id.btn_4)
    CircleButton btn4;
    @InjectView(R.id.btn_5)
    CircleButton btn5;
    @InjectView(R.id.btn_6)
    CircleButton btn6;
    private MediaPlayer mp;
    private boolean flag=false;
    private int lastNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        ButterKnife.inject(this);


        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll1);
    }



    @OnClick({R.id.iv, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                finish();
                break;
            case R.id.btn_1:
                if (mp.isPlaying()){
                    mp.stop();
                    reset(lastNum);
                    if(lastNum!=1){
                        btn1.setImageResource(R.drawable.playing);
                        btn1.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll1);
                        mp.start();
                        lastNum = 1;
                    }
                }
                else {
                        btn1.setImageResource(R.drawable.playing);
                        btn1.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll1);
                        mp.start();
                        lastNum = 1;
                }
                break;
            case R.id.btn_2:
                if (mp.isPlaying()){
                    mp.stop();
                    reset(lastNum);
                    if(lastNum!=2){
                        btn2.setImageResource(R.drawable.playing);
                        btn2.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll2);
                        mp.start();
                        lastNum = 2;
                    }
                }
                else {
                        btn2.setImageResource(R.drawable.playing);
                        btn2.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll2);
                        mp.start();
                        lastNum = 2;
                }
                break;
            case R.id.btn_3:
                if (mp.isPlaying()){
                    mp.stop();
                    reset(lastNum);
                    if(lastNum!=3){
                        btn3.setImageResource(R.drawable.playing);
                        btn3.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll3);
                        mp.start();
                        lastNum = 3;
                    }
                }
                else {
                    btn3.setImageResource(R.drawable.playing);
                    btn3.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll3);
                    mp.start();
                    lastNum =3;
                }
                break;
            case R.id.btn_4:
                if (mp.isPlaying()){
                    mp.stop();
                    reset(lastNum);
                    if(lastNum!=4){
                        btn4.setImageResource(R.drawable.playing);
                        btn4.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll4);
                        mp.start();
                        lastNum = 4;
                    }
                }
                else {
                    btn4.setImageResource(R.drawable.playing);
                    btn4.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll4);
                    mp.start();
                    lastNum =4;
                }
                break;
            case R.id.btn_5:
                if (mp.isPlaying()){
                    mp.stop();
                    reset(lastNum);
                    if(lastNum!=5){
                        btn5.setImageResource(R.drawable.playing);
                        btn5.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll5);
                        mp.start();
                        lastNum = 5;
                    }
                }
                else {
                    btn5.setImageResource(R.drawable.playing);
                    btn5.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll5);
                    mp.start();
                    lastNum =5;
                }
                break;
            case R.id.btn_6:
                if (mp.isPlaying()){
                    mp.stop();
                    reset(lastNum);
                    if(lastNum!=6){
                        btn6.setImageResource(R.drawable.playing);
                        btn6.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll6);
                        mp.start();
                        lastNum = 6;
                    }
                }
                else {
                    btn6.setImageResource(R.drawable.playing);
                    btn6.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll6);
                    mp.start();
                    lastNum =6;
                }
                break;
        }
    }

    private void reset(int Num) {
        switch (Num){
            case 1:
                btn1.setImageResource(R.drawable.play);
                btn1.setColor(Color.parseColor("#03A89E"));
                break;
            case 2:
                btn2.setImageResource(R.drawable.play);
                btn2.setColor(Color.parseColor("#03A89E"));
                break;
            case 3:
                btn3.setImageResource(R.drawable.play);
                btn3.setColor(Color.parseColor("#03A89E"));
                break;
            case 4:
                btn4.setImageResource(R.drawable.play);
                btn4.setColor(Color.parseColor("#03A89E"));
                break;
            case 5:
                btn5.setImageResource(R.drawable.play);
                btn5.setColor(Color.parseColor("#03A89E"));
                break;
            case 6:
                btn6.setImageResource(R.drawable.play);
                btn6.setColor(Color.parseColor("#03A89E"));
                break;
        }
    }

}
