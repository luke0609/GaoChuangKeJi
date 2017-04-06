package com.jiaokaokeji.gaochuangkeji.login;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.login.pojo.ResultBean;
import com.jiaokaokeji.gaochuangkeji.login.widget.ClearWriteEditText;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static android.R.attr.key;
import static com.jiaokaokeji.gaochuangkeji.R.id.app_password1;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.app_phoneNum)
    ClearWriteEditText appPhoneNum;
    @InjectView(R.id.btn_post)
    Button btnPost;
    @InjectView(R.id.app_code)
    ClearWriteEditText appCode;
    @InjectView(R.id.app_password1)
    ClearWriteEditText appPassword1;

    @InjectView(R.id.app_regist_bt)
    Button appRegistBt;
    private static final String[] AVATARS = new String[400];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

    @OnClick({R.id.btn_post, R.id.app_regist_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post:
                if (vaildateinfo()) {
                    //启动获取验证码 86是中国
                    String zh = appPhoneNum.getText().toString().trim();
                    SMSSDK.getVerificationCode("86", zh);
                    timer.start();
                }
                break;
            case R.id.app_regist_bt:
              //  VaildateputInfo();
                doRegister();
                break;
        }
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
                Toast.makeText(RegisterActivity.this, "回调完成", Toast.LENGTH_SHORT).show();
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Toast.makeText(RegisterActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();

                    Toast.makeText(RegisterActivity.this, "注册完成", Toast.LENGTH_SHORT).show();

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    Toast.makeText(RegisterActivity.this, "返回支持发送验证码的国家列表", Toast.LENGTH_SHORT).show();
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    private void VaildateputInfo() {
        vaildatePassword();
    }

    //验证 验证码
    private void vaildatePassword() {
        String code = appCode.getText().toString().trim();
        String zh = appPhoneNum.getText().toString().trim();
        SMSSDK.submitVerificationCode("86", zh, code);
        putUserInfo("86", zh);
    }

    //提交用户信息
    private void putUserInfo(String country, String phone) {
        Random rnd = new Random();
        int id = Math.abs(rnd.nextInt());
        String uid = String.valueOf(id);
        String nickName = "SmsSDK_User_" + uid;
        String avatar = AVATARS[id % 12];
        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnPost.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            btnPost.setEnabled(true);
            btnPost.setText("获取验证码");
        }
    };

    private boolean vaildateinfo() {

        String zh = appPhoneNum.getText().toString().trim();
        String pwd = appPassword1.getText().toString().trim();
        //首先要判断是否为空
        if (!zh.equals("") || null != zh) {
            if (zh.length() == 11) {
                if (!pwd.equals("") || null != pwd) {
                    if (pwd.length() == 8) {
                        return true;
                    } else {
                        Toast.makeText(RegisterActivity.this, "密码不足8位", Toast.LENGTH_SHORT).show();
                        appPassword1.requestFocus();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    appPassword1.requestFocus();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "手机号不足11位", Toast.LENGTH_SHORT).show();
                appPhoneNum.requestFocus();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            appPhoneNum.requestFocus();
        }
        return false;
    }
    private void doRegister(){
        // user_name=18500551431&password=123456&code=801312
        String zh = appPhoneNum.getText().toString().trim();
        String pwd = appPassword1.getText().toString().trim();



        RequestParams requestParams = new RequestParams("http://192.168.0.113:8888/tianbaoApp/index.php/login/user/register");

        requestParams.addBodyParameter("key","tbjxappgaochuang");
        requestParams.addBodyParameter("iphone",zh);
        requestParams.addBodyParameter("password",pwd);
        Toast.makeText(this,requestParams+"" , Toast.LENGTH_SHORT).show();
        x.http().post(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Toast.makeText(RegisterActivity.this, "succ", Toast.LENGTH_SHORT).show();
                System.out.println(result);
                Gson gson=new Gson();
                ResultBean rb=gson.fromJson(result,ResultBean.class);
                Toast.makeText(RegisterActivity.this, rb.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(RegisterActivity.this,ex+ "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
