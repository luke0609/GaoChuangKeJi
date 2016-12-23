package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.jiaokaokeji.gaochuangkeji.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SettingActivity extends AppCompatActivity {

    @InjectView(R.id.et)
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);
        WindowManager wm = this.getWindowManager();
        int height= wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams params = et.getLayoutParams();
        params.height =(int) ((height) * 0.3);
        et.setLayoutParams(params);
    }
}
