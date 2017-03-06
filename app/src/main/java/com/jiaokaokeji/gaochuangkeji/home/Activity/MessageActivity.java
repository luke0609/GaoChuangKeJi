package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity {
//资讯展示
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    private WebView webview;
    private TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.inject(this);
        webview = (WebView) findViewById(R.id.web);
        Title = ((TextView) findViewById(R.id.bar_title));
        WebSettings wv_setttig = webview.getSettings();
        wv_setttig.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_setttig.setJavaScriptEnabled(true);
        wv_setttig.setTextSize(WebSettings.TextSize.NORMAL);

        String url = "file:///android_asset/ww.html";
        //    webview.setInitialScale(200);
        webview.loadUrl(url);
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



}
