package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jiaokaokeji.gaochuangkeji.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ArchivesActivity extends AppCompatActivity {

    @InjectView(R.id.ll)
    LinearLayout ll;
    @InjectView(R.id.ll1)
    LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archives);
        ButterKnife.inject(this);
        WindowManager wm = this.getWindowManager();
        int height1 = wm.getDefaultDisplay().getHeight();
        int height = ll.getMeasuredHeight();
        int height2 = height1 - height;
        ViewGroup.LayoutParams params = ll1.getLayoutParams();
        params.height = height2;
        ll1.setLayoutParams(params);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#4EAFAB"));
    }

    @OnClick(R.id.iv)
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
