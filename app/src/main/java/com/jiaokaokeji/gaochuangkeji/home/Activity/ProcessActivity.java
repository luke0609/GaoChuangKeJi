package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jiaokaokeji.gaochuangkeji.R;

public class ProcessActivity extends AppCompatActivity {

   private WebView webview;
    //流程
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
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



    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            final View contentView = LayoutInflater.from(ArticleActivity.this).inflate(
//                    R.layout.article_comment_popupwindow, null);
//            final LinearLayout ll_comment_popup = ((LinearLayout) contentView.findViewById(R.id.ll_comment_popup));
//            ll_comment_popup.setVisibility(View.GONE);
//            if(popupWindow!=null && popupWindow.isShowing() ){
//                popupWindow.dismiss();
//                return true;
//            }
            if (webview.canGoBack())
                webview.goBack();
            else
                finish();
            return true;
        }
        return false;
    }



}
