package com.jiaokaokeji.gaochuangkeji.my.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jiaokaokeji.gaochuangkeji.R;

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
    }

    @OnClick(R.id.iv)
    public void onClick() {
        finish();
    }
}
