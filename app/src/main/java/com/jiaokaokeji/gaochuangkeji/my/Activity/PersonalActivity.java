package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.jiaokaokeji.gaochuangkeji.R.layout.dialog;
import static com.jiaokaokeji.gaochuangkeji.R.layout.rb_item;

public class PersonalActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.tv1)
    TextView tv1;
    @InjectView(R.id.tv2)
    TextView tv2;
    @InjectView(R.id.tv3)
    TextView tv3;
    @InjectView(R.id.tv4)
    TextView tv4;
    @InjectView(R.id.tv5)
    TextView tv5;
    @InjectView(R.id.tv6)
    TextView tv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.iv, R.id.rl_mytx, R.id.rl_mync, R.id.rl_myxm, R.id.rl_myph, R.id.rl_mysex, R.id.rl_myyear, R.id.rl_mysf, R.id.rl_mydz})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                finish();
                break;
            case R.id.rl_mytx:
                break;
            case R.id.rl_mync:
                String title1="请输入昵称";
                int i=1;
                showDiaog(title1,i);
                break;
            case R.id.rl_myxm:
                String title2="请输入姓名";
                int i1=2;
                showDiaog(title2,i1);
                break;
            case R.id.rl_myph:
                break;
            case R.id.rl_mysex:
                String sex="请选择性别";
                break;
            case R.id.rl_myyear:
                break;
            case R.id.rl_mysf:
                String title3="请输入省份证号";
                break;
            case R.id.rl_mydz:
                break;
        }
    }
    public void showDiaog(String title, final int i){
        final AlertDialog alertDialog = new AlertDialog.Builder(PersonalActivity.this).create();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(dialog);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        tv_title.setText(title);
        final EditText tv_message = (EditText) window.findViewById(R.id.tv_dialog_message);
        if (i==1) {
            tv_message.setText(tv.getText().toString());
        }
        if (i==2) {
            tv_message.setText(tv1.getText().toString());
        }
        tv_message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });
        tv_message.requestFocus();
        Button quxiao = (Button) window.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        Button queding = (Button) window.findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nich= String.valueOf(tv_message.getText());
                if (i==1) {
                    tv.setText(nich);
                }
                if (i==2) {
                    tv1.setText(nich);
                }
                alertDialog.dismiss();
            }
        });
    }

}
