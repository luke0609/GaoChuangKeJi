package com.jiaokaokeji.gaochuangkeji.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.login.widget.ClearWriteEditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * A login screen that offers login via email/password.
 */
public class RetrieveActivity extends AppCompatActivity {


    @InjectView(R.id.app_phoneNum)
    ClearWriteEditText appPhoneNum;
    @InjectView(R.id.app_password1)
    ClearWriteEditText appPassword1;
    @InjectView(R.id.app_code)
    ClearWriteEditText appCode;
    @InjectView(R.id.btn_post)
    Button btnPost;
    @InjectView(R.id.app_regist_bt)
    Button appRegistBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        ButterKnife.inject(this);
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = Message.obtain();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                Toast.makeText(RetrieveActivity.this, "回调完成", Toast.LENGTH_SHORT).show();
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Toast.makeText(RetrieveActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();

                    //doRegister();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Toast.makeText(RetrieveActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    Toast.makeText(RetrieveActivity.this, "返回支持发送验证码的国家列表", Toast.LENGTH_SHORT).show();
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    @OnClick({R.id.btn_post, R.id.app_regist_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_post:
                break;
            case R.id.app_regist_bt:
                break;
        }
    }
}




