package com.jiaokaokeji.gaochuangkeji.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.login.widget.ClearWriteEditText;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.app_username_et)
    ClearWriteEditText appUsernameEt;
    @InjectView(R.id.app_password_et)
    ClearWriteEditText appPasswordEt;
    @InjectView(R.id.app_sign_in_bt)
    Button appSignInBt;
    @InjectView(R.id.de_login_forgot)
    TextView deLoginForgot;
    @InjectView(R.id.de_login_register)
    TextView deLoginRegister;
    private String sUserName, sPassword;
    private TextView mForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @OnClick({R.id.app_sign_in_bt, R.id.de_login_forgot,R.id.de_login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.app_sign_in_bt:
                checkLogin();
                break;
            case R.id.de_login_forgot:
                Intent intent = new Intent(LoginActivity.this, RetrieveActivity.class);
                startActivity(intent);
                break;
            case R.id.de_login_register:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void checkLogin() {
        sUserName = appUsernameEt.getText().toString().trim();
        sPassword = appPasswordEt.getText().toString().trim();

        if (isEmpty(sUserName)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            appUsernameEt.setShakeAnimation();
            return;
        }

        if (isEmpty(sPassword)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            appPasswordEt.setShakeAnimation();
            return;
        }
        RequestParams requestParams = new RequestParams("http://192.168.191.2:8888/tianbaoApp/index.php/login/user/login");
        requestParams.addBodyParameter("key", "tbjxappgaochuang");
        requestParams.addBodyParameter("iphone",sUserName );
        requestParams.addBodyParameter("password",sPassword);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {


                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }


    /**
     * 非空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 手机号
     *
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        CharSequence inputStr = phoneNumber;
        //正则表达式

        String phone="^1[34578]\\d{9}$" ;

        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
