package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.StaggeredGridView.VoteSubmitViewPager;
import com.jiaokaokeji.gaochuangkeji.book.adapter.ExaminationSubmitAdapter;
import com.jiaokaokeji.gaochuangkeji.book.database.DBManager;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.SaveQuestionInfo;
import com.jiaokaokeji.gaochuangkeji.book.util.ConstantUtil;
import com.jiaokaokeji.gaochuangkeji.book.util.ViewPagerScroller;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class RadomActivity extends AppCompatActivity {
    private ImageView leftIv;
    private TextView titleTv;
    private TextView right;
    private ImageView iv;
    AnSwerInfo anSwerInfo = new AnSwerInfo();
    VoteSubmitViewPager viewPager;
    ExaminationSubmitAdapter pagerAdapter;
    List<View> viewItems = new ArrayList<View>();
    List<AnSwerInfo> dataItems = new ArrayList<AnSwerInfo>();
    private ProgressDialog progressDialog;

    private String pageCode;
    private int pageScore;
    private int errortopicNums;// 错题数
    private int errortopicNums1;// 错题数
    private String isPerfectData = "1";// 是否完善资料0完成 1未完成
    private String type = "0";// 0模拟 1竞赛
    private String errorMsg = "";
    //Dialog builderSubmit;

    public List<Map<String, SaveQuestionInfo>> list = new ArrayList<Map<String, SaveQuestionInfo>>();
    public Map<String, SaveQuestionInfo> map2 = new HashMap<String, SaveQuestionInfo>();
    public List<SaveQuestionInfo> questionInfos = new ArrayList<SaveQuestionInfo>();

    Timer timer;
    TimerTask timerTask;
    int minute = 2;
    int second = 0;

    boolean isPause = false;
    int isFirst;

    DBManager dbManager;

    String dateStr = "";
    String imgServerUrl = "";

    private boolean isUpload = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radom);
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
        //initView();
        loadData();
//        ErrorQuestionInfo[] errorQuestionInfos = dbManager.queryAllData();
//        if (errorQuestionInfos != null) {
//            // 删除上次保存的我的错题
//            int colunm = (int) dbManager.deleteAllData();
//        }
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
        DBManager dbManager = new DBManager(RadomActivity.this);
        dbManager.openDB1();
        AnSwerInfo[] info1 = dbManager.queryAllData1();
        if (info1 == null) {
            Toast.makeText(RadomActivity.this, "暂无数据",
                    Toast.LENGTH_SHORT).show();
        }else {
            for (int i = 0; i < info1.length; i++) {
                AnSwerInfo info = new AnSwerInfo();
                info.setQuestionId(info1[i].questionId);// 试题主键
                info.setQuestionName(info1[i].questionName);// 试题题目
                info.setAnalysis(info1[i].analysis);// 试题分析
                info.setQuestionType("0");
                info.setCorrectAnswer(info1[i].correctAnswer);// 正确答案
                info.setOptionA(info1[i].optionA);// 试题选项A
                info.setOptionB(info1[i].optionB);// 试题选项B
                info.setOptionC(info1[i].optionC);// 试题选项C
                info.setOptionD(info1[i].optionD);// 试题选项D
                info.setOption_type("0");
                dataItems.add(info);
            }
        }

        for (int i = 0; i < dataItems.size(); i++) {
            viewItems.add(getLayoutInflater().inflate(
                    R.layout.vote_submit_viewpager_item, null));
        }
        pagerAdapter = new ExaminationSubmitAdapter(
                RadomActivity.this, viewItems,
                dataItems, imgServerUrl);
        viewPager.setAdapter(pagerAdapter);
        viewPager.getParent()
                .requestDisallowInterceptTouchEvent(false);
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

