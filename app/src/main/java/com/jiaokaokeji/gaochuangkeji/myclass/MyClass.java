package com.jiaokaokeji.gaochuangkeji.myclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.MainActivity;
import com.jiaokaokeji.gaochuangkeji.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.yuyh.library.BubblePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sdaduanbilei.library.DashBoard;
import cn.sdaduanbilei.library.DashboardView;

public class MyClass extends Fragment {
    View view1;
    private ImageView open;
    private static final int RESULT_OK = 123;
    private static final int  SCANNIN_GREQUEST_CODE = 13;
    private DashboardView dash_borad;
    private float progressNum=80;
    private ImageView iv_saoma;
    private ListView list_pop;
    private List<Map<String, Object>> list;
    private SimpleAdapter sAdapter;
    private PopupWindow popup;
    private String[] names = new String[] { "扫一扫", "签到" };
    private int[] imag = new int[] { R.drawable.saoyisao,
            R.drawable.qiandao };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_my_class, null);
        ButterKnife.inject(this, view1);
        initview();
        MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    popup.dismiss();
                }

            }
        };

        // 将myTouchListener注册到分发列表
        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);

        return view1;
    }


    private void initview() {
        dash_borad = ((DashboardView) view1.findViewById(R.id.dash_board));
        dash_borad.setDashStyle(DashBoard.RING);
        iv_saoma = ((ImageView) view1.findViewById(R.id.saoma));
        popup();
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
//
//    @OnClick(R.id.saoma)
//    public void onClick(View view) {
//        Intent intent = new Intent(getActivity(), CaptureActivity.class);
//        startActivity(intent);
//    }

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

    public void list(View v) {
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("names", names[i]);
            map.put("imag", imag[i]);
            list.add(map);
        }
        sAdapter = new SimpleAdapter(getContext(), list,
                R.layout.list_item, new String[] { "names", "imag" },
                new int[] { R.id.tv_item, R.id.iv_item });
        list_pop = (ListView)v.findViewById(R.id.list_pop);
        //从不同于主xml(setContentView(R.layout.main);)中使用其他xml布局中的控件时，需声明其所在的VIew布局。
        list_pop.setAdapter(sAdapter);

        list_pop.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                popup.dismiss();
               if(position==0){
                   Intent intent = new Intent(getActivity(), CaptureActivity.class);
                   startActivity(intent);
               }
            }
        });
    }
    public void popup() {
        // 装载R.layout.popup对应的界面布局
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root =inflater.inflate(R.layout.layout_popup_view, null);
        // 创建PopupWindow对象
        popup = new PopupWindow(root,350,300);
        popup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        open = (ImageView) view1.findViewById(R.id.saoma);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 以下拉方式显示。
//                popup.showAtLocation(view1.findViewById(R.id.saoma), Gravity.TOP
//                        | Gravity.RIGHT, sWidth,20);
                popup.showAsDropDown(view1.findViewById(R.id.struct));
            }
        });
        list(root);
    }


}
