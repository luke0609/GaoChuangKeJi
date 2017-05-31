package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.StaggeredGridView.VoteSubmitViewPager;
import com.jiaokaokeji.gaochuangkeji.book.adapter.ExaminationSubmitAdapter1;
import com.jiaokaokeji.gaochuangkeji.book.database.DBManager;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.SaveQuestionInfo;
import com.jiaokaokeji.gaochuangkeji.book.util.ViewPagerScroller;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Radom1Activity extends AppCompatActivity  {
    private ImageView iv;
    VoteSubmitViewPager viewPager;
    ExaminationSubmitAdapter1 pagerAdapter;
    List<View> viewItems = new ArrayList<View>();
    List<AnSwerInfo.DataBean> dataItems = new ArrayList<AnSwerInfo.DataBean>();
    private Handler handler = new Handler();
    public List<Map<String, SaveQuestionInfo>> list = new ArrayList<Map<String, SaveQuestionInfo>>();
    public List<SaveQuestionInfo> questionInfos = new ArrayList<SaveQuestionInfo>();
    AnSwerInfo.DataBean[] info1;
    int ppstinoPage;
    String imgServerUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radom1);
        DBManager dbManager = new DBManager(Radom1Activity.this);
        dbManager.openDB1();
        info1 = dbManager.queryAllData2();
        iv = ((ImageView) findViewById(R.id.iv));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));
        initViewPagerScroll();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ppstinoPage=position+1;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //initView();

        loadData();
        //loadData1();
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

    private void loadData() {
        if (info1 == null) {
            Toast.makeText(Radom1Activity.this, "暂无数据，请检查网络连接是否正常，如正常请退出耐心等待",
                    Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i <15; i++) {
                AnSwerInfo.DataBean info = new AnSwerInfo.DataBean();
                info.setId(info1[i].getId());// 试题主键
                info.setQuestion(info1[i].getQuestion());// 试题题目
                info.setExplains(info1[i].getExplains());// 试题分析
                info.setAnswer(info1[i].getAnswer());// 正确答案
                info.setItem1(info1[i].getItem1());// 试题选项A
                info.setItem2(info1[i].getItem2());// 试题选项B
                info.setItem3(info1[i].getItem3());// 试题选项C
                info.setItem4(info1[i].getItem4());// 试题选项D
                info.setUrl(info1[i].getUrl());
                dataItems.add(info);

            }
            for (int i = 0; i <15; i++) {
                viewItems.add(getLayoutInflater().inflate(
                        R.layout.vote_submit_viewpager_item1, null));
            }
            pagerAdapter = new ExaminationSubmitAdapter1(
                    Radom1Activity.this,info1, viewItems,
                    dataItems, imgServerUrl);
            viewPager.setAdapter(pagerAdapter);
            viewPager.getParent()
                    .requestDisallowInterceptTouchEvent(false);
            loadData1();
        }
    }
    private void loadData1() {

        new Thread() {
            @Override
            public void run() {//在run()方法实现业务逻辑；
                if (info1 == null) {
                    Toast.makeText(Radom1Activity.this, "暂无数据",
                            Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 15; i < info1.length; i++) {
                        AnSwerInfo.DataBean info = new AnSwerInfo.DataBean();
                        info.setId(info1[i].getId());// 试题主键
                        info.setQuestion(info1[i].getQuestion());// 试题题目
                        info.setExplains(info1[i].getExplains());// 试题分析
                        info.setAnswer(info1[i].getAnswer());// 正确答案
                        info.setItem1(info1[i].getItem1());// 试题选项A
                        info.setItem2(info1[i].getItem2());// 试题选项B
                        info.setItem3(info1[i].getItem3());// 试题选项C
                        info.setItem4(info1[i].getItem4());// 试题选项D
                        info.setUrl(info1[i].getUrl());
                        dataItems.add(info);
                    }

                    for (int i =15; i < dataItems.size(); i++) {
                        viewItems.add(getLayoutInflater().inflate(
                                R.layout.vote_submit_viewpager_item1, null));
                    }
                    //更新UI操作；
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                           pagerAdapter.notifyDataSetChanged();
                        }
                    });
                }
                }
            }.start();

    }
    /**
     * 设置ViewPager的滑动速度
     */
    private void initViewPagerScroll() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }

    /**
     * @param index 根据索引值切换页面
     */
    public void setCurrentView(int index) {
        viewPager.setCurrentItem(index);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    }

