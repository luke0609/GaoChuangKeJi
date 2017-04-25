package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jiaokaokeji.gaochuangkeji.R;

public class Shezhi extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        iv = ((ImageView) findViewById(R.id.iv));
        bt = ((Button) findViewById(R.id.bt));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv:
                finish();
                break;
            case R.id.bt:
                break;
        }
    }
}
