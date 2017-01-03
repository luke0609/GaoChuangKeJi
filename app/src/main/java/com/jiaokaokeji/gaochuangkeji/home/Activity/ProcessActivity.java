package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProcessActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    private WebView webview;

    //流程
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        ButterKnife.inject(this);
        webview = (WebView) findViewById(R.id.web);

        WebSettings wv_setttig = webview.getSettings();
        wv_setttig.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_setttig.setJavaScriptEnabled(true);
        wv_setttig.setTextSize(WebSettings.TextSize.NORMAL);
        //   wv_setttig.setUseWideViewPort(true); //将图片调整到适合webview的大小
        //  wv_setttig.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //  wv_setttig.setDefaultTextEncodingName("utf-8");//设置编码格式
        String url = "file:///android_asset/ww.html";
        //    webview.setInitialScale(200);
        webview.loadUrl(url);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            if (webview.canGoBack())
                webview.goBack();
            else
                finish();
            return true;
        }
        return false;
    }


    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
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
}
