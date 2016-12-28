package com.jiaokaokeji.gaochuangkeji.myclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sdaduanbilei.library.DashBoard;
import cn.sdaduanbilei.library.DashboardView;

public class MyClass extends Fragment {
    View view1;
    @InjectView(R.id.saoma)
    ImageView saoma;
    private static final int RESULT_OK = 123;
    private static final int  SCANNIN_GREQUEST_CODE = 13;
    private DashboardView dash_borad;
    private float progressNum=80;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_my_class, null);
        ButterKnife.inject(this, view1);
        initview();

        return view1;
    }

    private void initview() {
        dash_borad = ((DashboardView) view1.findViewById(R.id.dash_board));
        dash_borad.setDashStyle(DashBoard.RING);

    }

    @Override
    public void onResume() {
        super.onResume();
        progressBarChange(progressNum);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println("123"+hidden);
        if (!hidden) {
            progressBarChange(progressNum);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.saoma)
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivity(intent);
    }

    private void progressBarChange(final float progressNum) {
        //开启一个线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //得到progeressBar的最大长度
                float currentProgress=0;
                int count=0;
                try {
                    //progressBar当前的长度没有达到他的最长度,让循环一直进行
                    while (currentProgress !=progressNum ) {
                        count++;
                        //拿到一个每次前进的进度值,因为是要2s完成,所以分为100份
                        float stepProgress = progressNum / 100;
                        currentProgress=stepProgress*count;
                        dash_borad.setDashProgress(currentProgress);
                        //前进一次,睡眠一秒
                        Thread.sleep(10);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        //开启线程
        thread.start();
    }


}
