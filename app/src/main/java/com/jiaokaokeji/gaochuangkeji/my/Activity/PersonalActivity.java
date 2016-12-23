package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.my.prjo.WheelView;

import java.util.ArrayList;
import java.util.Arrays;

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
    private RadioGroup rg;
    private static int checkedId1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.iv, R.id.rl_mytx, R.id.rl_mync, R.id.rl_myxm,R.id.rl_mysex, R.id.rl_myyear, R.id.rl_mysf, R.id.rl_mydz})
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
            case R.id.rl_mysex:
                String sex="请选择性别";
                showDiaog1(sex);
                break;
            case R.id.rl_myyear:
                String title4="请选择年龄";
                int i2=3;
                showDiaog(title4,i2);
                break;
            case R.id.rl_mysf:
                String title3="请输入身份证号";
                int i3=4;
                showDiaog(title3,i3);
                break;
            case R.id.rl_mydz:
                String title5="请输入地址";
                int i4=5;
                showDiaog(title5,i4);
                break;
        }
    }
    public void showDiaog(String title, final int i){
        final String[] b = new String[1];
        final AlertDialog alertDialog = new AlertDialog.Builder(PersonalActivity.this).create();
        alertDialog.setCancelable(false);
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
        WheelView wv = (WheelView) window.findViewById(R.id.wheel_view_wv);
        final TextView tv8= (TextView) window.findViewById(R.id.tv);
        if (i==1||i==2||i==4||i==5){
            tv_message.setVisibility(View.VISIBLE);
            wv.setVisibility(View.GONE);
        }else {
            wv.setVisibility(View.VISIBLE);
            tv_message.setVisibility(View.GONE);
        }
        if (i==1) {
            tv_message.setText(tv.getText().toString());
        }
        if (i==2) {
            tv_message.setText(tv1.getText().toString());
        }
        if (i==3){
            final String[] a = new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                    "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
                    "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66",
                    "67", "68", "69", "70"};
            wv.setOffset(2);
            wv.setItems(Arrays.asList(a));
            if (Integer.parseInt(tv4.getText().toString())-18>=0) {
                wv.setSeletion(Integer.parseInt(tv4.getText().toString()) - 18);
            }
            wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(int selectedIndex, String item) {
                    b[0] =item;
                }
            });
        }
        if (i==4){
            tv_message.setText(tv5.getText().toString());
        }
        if (i==5){
            tv_message.setText(tv6.getText().toString());
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
                    alertDialog.dismiss();
                }
                if (i==2) {
                    tv1.setText(nich);
                    alertDialog.dismiss();
                }
                if (i==3){
                     tv4.setText(b[0]);
                    alertDialog.dismiss();
                }
                if (i==4){
                    boolean judge = isMobile(nich);
                    if (judge=false){
                        tv8.setVisibility(View.VISIBLE);
                    }else {
                        tv5.setText(nich);
                        alertDialog.dismiss();
                    }
                }
                if (i==5) {
                    tv6.setText(nich);
                    alertDialog.dismiss();
                }
            }
        });
    }
    public void showDiaog1(String title){
        final AlertDialog alertDialog = new AlertDialog.Builder(PersonalActivity.this).create();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.show();
       final   Window window = alertDialog.getWindow();
        window.setContentView(rb_item);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        tv_title.setText(title);
        final RadioButton rb1 = (RadioButton) window.findViewById(R.id.rb1);
        final RadioButton rb2 = (RadioButton) window.findViewById(R.id.rb2);
        rg = ((RadioGroup) window.findViewById(R.id.rg));
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            private RadioButton rb;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                rb = ((RadioButton) window.findViewById(radioButtonId));
                tv3.setText(rb.getText().toString());
            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if (tv3.getText().equals(rb1.getText())){
            rb1.setChecked(true);
        }
        if (tv3.getText().equals(rb2.getText())){
            rb2.setChecked(true);
        }
    }
    public static boolean isMobile(String number) {
        String num = "(^\\\\d{15}$)|(^\\\\d{17}([0-9]|X)$)";
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

}
