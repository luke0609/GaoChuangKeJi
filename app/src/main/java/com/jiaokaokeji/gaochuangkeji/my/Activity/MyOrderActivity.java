package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.my.prjo.PayRadioGroup;
import com.jiaokaokeji.gaochuangkeji.my.prjo.PayRadioPurified;
import com.jiaokaokeji.gaochuangkeji.zhifubao.AuthResult;
import com.jiaokaokeji.gaochuangkeji.zhifubao.OrderInfoUtil2_0;
import com.jiaokaokeji.gaochuangkeji.zhifubao.PayResult;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Map;

public class MyOrderActivity extends AppCompatActivity {

    private Button bt;
    private PayRadioGroup rg1;
    public static final String APPID = "2016080400165106";

    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "2088102169910135";
    /** 支付宝账户登录授权业务：入参target_id值 */
    public static final String TARGET_ID = "nalmel6995@sandbox.com";
String price="20";
    String name="fdfdf";
    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwNIFCAwuo7+n4+FZm41Ap9JzBuHaEJyzcyl+NHXZZY6BbYeKNFtdFjINlvTIJeoyg/wKC8OSs6LKxv4xnh3eAknnVbKEdqUM1NVV6iSa4skfU+QK3t4fxHYHVEOWFyP0dHEfbp+RSvMkJLI2M3sLLLAzxYAHB6pFTTYQn5V8TKc93+isgOCptqLr5andXTXk1kq9sTLq+/MAVsAEEFxQqXA1Zc8zqXrur6N/7FHr7sxCtL2IIko0H0YUQB+kuRBMIa5+mmk15TjtIpwkeBtAWsxAFXxmbb6HEdJEI2KyPY5npiZNo2qNfQFE7K3/7Gr2wzqqFupN8R34f5+lJGdXBAgMBAAECggEADR17WXx8i7B/y8ZAOBxHRTgtysV+HMwFrNFrV0eQHYsqzmJ0yGeg0k5pIVN5u/bhLMKOX0yq2KvhURWez73niSxd4SLawyhsFjPIDvzSYwDyE8oNB7ujZLD7ju5ZByI0pMwMFS9j8TyYAhTM3GVgPlsjA3xP5S0ecALfCVErSHO+k23YzHPe/7OBR5xfo43TwlKITpvUDVk5pyjZ5zHf4xAkfV8cFRd5TeRzfr55gAenJ3kpK+Db5Vht7Ly1p9o52nYhs4w/6mlu04VuWmFc0aump6v9aot2CwyNDfpLN4eZMPN7scO9ny6GsQj/RNk7RoWqANumLe1x8mTJvfqguQKBgQDfRpHrzsXRfuYXV7PA+N0S48UIC/O+P9Llo9dsOnRx9XWISae4T5v3ZBcp4dMvgpAQXxST8SgoQgeCfnD2tvjnffI9sp4hbmEWYIhcCVP3aQ1Yiz9C5N3MHVhGC6Mu7BrIsSzRAEreD0brmvO7h7jDPyMS//CmhTFqVR8kee/7hwKBgQDKB9IJP1u0YE09wstt2DjObty8NCbinVNCpp1urzJfnLSU77M7vpNok+3EoCGwUHTPBO4pqv0++dUsmMx05zA15V8HY3TM6cr3g3LciHxPBSkBgpelqxU3B6S8oljMyahLQtKzpf5TsjXBmEHT41mTLN8ZpZw2kKn5WbPf2VNGdwKBgCPTmw6Pe0aGaj1MoVdRl4e0bKVB5badXyXiCGshZhXzIQzhZLw6afhkYyMhTVXOSXeXCiJDDAKJtdLGnzG3YnEi0H/97UnIAGQq0TaTeJm2jaGrllcRilV6T/PNYj3hPNr/MwXGWj0Pjn23u2xX9tm9g89EqkTOEoPRbNHZjxx5AoGAQ703iW46+1HG1Ex6FQyIj37QeDIsT5NclV50LuaDR1etnLp6KDAQEOJ4uJLQeOOhJsCKcmIoo3L1LQzWkOPrYEbWBA4u3X4OVcs3OgZxV7VtOFpnKl8gr+DaNX1htNhwmzt+lkmz6rA4If9BIyB5qkVDUMAcjpcWxspy6z1dFKUCgYEAlcyD8sFYrQkH5dLJaUeXcyLagiUd/P+SmvoGNW58Vgl1sqcOi8ylVlg/IwqTWKnff/ipvURwxuiEhcRyt5q4oVeaqzQiha6EVmJ7yioAP8S+Cf9aRAWzdCoH5xtbX+G5sd+TJuenhP7Mce3MoSFkEPfSrdyCNZuJanGe0M9q11w=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(MyOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MyOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(MyOrderActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(MyOrderActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));
        bt = ((Button) findViewById(R.id.bt));
        iv = ((ImageView) findViewById(R.id.iv));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDiaog2();
            }
        });
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    public void showDiaog2() {
        final AlertDialog alertDialog = new AlertDialog.Builder(MyOrderActivity.this).create();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.show();
        final Window window = alertDialog.getWindow();
        window.setContentView(R.layout.test);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
        tv_title.setText("请选择支付方式");
        final PayRadioPurified rb1 = (PayRadioPurified) window.findViewById(R.id.p1);
        final PayRadioPurified rb2 = (PayRadioPurified) window.findViewById(R.id.p2);
        final PayRadioPurified rb3 = (PayRadioPurified) window.findViewById(R.id.p3);
        final PayRadioPurified rb4 = (PayRadioPurified) window.findViewById(R.id.p4);
        rg1 = ((PayRadioGroup) window.findViewById(R.id.genderGroup));

        rg1.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            private PayRadioPurified rb;
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                rb = ((PayRadioPurified) window.findViewById(radioButtonId));
            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payV2();
                alertDialog.dismiss();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaog();
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaog();
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaog();
            }
        });
    }
    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MyOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        payThread.start();
    }
    public void showDiaog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(MyOrderActivity.this).create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog1);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button queding = (Button) window.findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
