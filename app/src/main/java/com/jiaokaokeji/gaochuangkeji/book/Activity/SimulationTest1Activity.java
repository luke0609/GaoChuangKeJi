package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import com.jiaokaokeji.gaochuangkeji.book.adapter.ExaminationSubmitAdapter4;
import com.jiaokaokeji.gaochuangkeji.book.adapter.ExaminationSubmitAdapter5;
import com.jiaokaokeji.gaochuangkeji.book.database.DBManager;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.SaveQuestionInfo;
import com.jiaokaokeji.gaochuangkeji.book.util.ViewPagerScroller;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SimulationTest1Activity extends AppCompatActivity {
    private ImageView iv;
    Timer timer;
    TimerTask timerTask;
    int minute =45;
    int second = 0;
    int isFirst;
    private ImageView leftIv;
    private TextView right;
    boolean isPause = false;
    VoteSubmitViewPager viewPager;
    int[] num = new int[100];
    ExaminationSubmitAdapter5 pagerAdapter;
    List<View> viewItems = new ArrayList<View>();
    List<AnSwerInfo.DataBean> dataItems = new ArrayList<AnSwerInfo.DataBean>();
    private Handler handler = new Handler();
    public List<Map<String, SaveQuestionInfo>> list = new ArrayList<Map<String, SaveQuestionInfo>>();
    public List<SaveQuestionInfo> questionInfos = new ArrayList<SaveQuestionInfo>();
    AnSwerInfo.DataBean[] info1;
    int ppstinoPage;
    String imgServerUrl = "";
    private int errortopicNums;

    Handler handlerTime = new Handler() {
        public void handleMessage(Message msg) {
            // 判断时间快到前2分钟字体颜色改变
            if (minute < 1) {
                right.setTextColor(Color.RED);
            } else {
                right.setTextColor(Color.WHITE);
            }
            if (minute == 0) {
                if (second == 0) {
                    isFirst+=1;
                    // 时间到
                    if(isFirst==1){
                        showTimeOutDialog(true, "0");
                    }
                    right.setText("00:00");
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    if (timerTask != null) {
                        timerTask = null;
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        right.setText("0" + minute + ":" + second);
                    } else {
                        right.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        right.setText(minute + ":" + second);
                    } else {
                        right.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            right.setText(minute + ":" + second);
                        } else {
                            right.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            right.setText(minute + ":0" + second);
                        } else {
                            right.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }
        };
    };

    private Handler handlerStopTime = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    stopTime();
                    break;
                case 1:
                    startTime();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_test);
        viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
        DBManager dbManager = new DBManager(SimulationTest1Activity.this);
        dbManager.openDB1();
        info1 = dbManager.queryAllData2();
        getShu();
        iv = ((ImageView) findViewById(R.id.iv));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        loadData();
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ppstinoPage = position+1;
                errortopicNums = pagerAdapter.errorTopicNum();
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount()) {
                    final Dialog builder = new Dialog(SimulationTest1Activity.this, R.style.dialog);
                    builder.setContentView(R.layout.my_dialog);
                    TextView title = (TextView) builder.findViewById(R.id.dialog_title);
                    TextView content = (TextView) builder.findViewById(R.id.dialog_content);
                    TextView content1 = (TextView) builder.findViewById(R.id.tv1);
                    content1.setText(100-errortopicNums*2+"");
                    final Button confirm_btn = (Button) builder
                            .findViewById(R.id.dialog_sure);
                    Button cancel_btn = (Button) builder.findViewById(R.id.dialog_cancle);
                    confirm_btn.setVisibility(View.GONE);
                    cancel_btn.setText("退出");
                    cancel_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            builder.dismiss();
                        }
                    });
                    builder.setCancelable(false);// 设置点击Dialog外部任意区域关闭Dialog
                    builder.show();
                }
            }
            @Override
            public void onPageSelected(int position) {
                }
            @Override
            public void onPageScrollStateChanged(int state) {

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
    public void initView() {
        leftIv = (ImageView) findViewById(R.id.iv);
        right = (TextView) findViewById(R.id.right);
        right.setText("15:00");
        viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                isPause = true;
                showTimeOutDialog(true, "1");
                Message msg = new Message();
                msg.what = 0;
                handlerStopTime.sendMessage(msg);
            }
        });
        initViewPagerScroll();
    }
    private void loadData() {
        if (info1 == null) {
            Toast.makeText(SimulationTest1Activity.this, "暂无数据，请检查网络连接是否正常，如正常请退出耐心等待",
                    Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i <15; i++) {
                AnSwerInfo.DataBean info = new AnSwerInfo.DataBean();
                info.setId(info1[num[i]].getId());// 试题主键
                info.setQuestion(info1[num[i]].getQuestion());// 试题题目
                info.setExplains(info1[num[i]].getExplains());// 试题分析
                info.setAnswer(info1[num[i]].getAnswer());// 正确答案
                info.setItem1(info1[num[i]].getItem1());// 试题选项A
                info.setItem2(info1[num[i]].getItem2());// 试题选项B
                info.setItem3(info1[num[i]].getItem3());// 试题选项C
                info.setItem4(info1[num[i]].getItem4());// 试题选项D
                info.setUrl(info1[num[i]].getUrl());
                dataItems.add(info);

            }
            for (int i = 0; i <15; i++) {
                viewItems.add(getLayoutInflater().inflate(
                        R.layout.vote_submit_viewpager_item1, null));
            }
            pagerAdapter = new ExaminationSubmitAdapter5(
                    SimulationTest1Activity.this, viewItems,
                    dataItems, imgServerUrl);
            viewPager.setAdapter(pagerAdapter);
            viewPager.getParent().requestDisallowInterceptTouchEvent(false);
            loadData1();
        }
    }
    private void loadData1() {

        new Thread() {
            @Override
            public void run() {//在run()方法实现业务逻辑；
                if (info1 == null) {
                    Toast.makeText(SimulationTest1Activity.this, "暂无数据",
                            Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 15; i <num.length; i++) {
                        AnSwerInfo.DataBean info = new AnSwerInfo.DataBean();
                        info.setId(info1[num[i]].getId());// 试题主键
                        info.setQuestion(info1[num[i]].getQuestion());// 试题题目
                        info.setExplains(info1[num[i]].getExplains());// 试题分析
                        info.setAnswer(info1[num[i]].getAnswer());// 正确答案
                        info.setItem1(info1[num[i]].getItem1());// 试题选项A
                        info.setItem2(info1[num[i]].getItem2());// 试题选项B
                        info.setItem3(info1[num[i]].getItem3());// 试题选项C
                        info.setItem4(info1[num[i]].getItem4());// 试题选项D
                        info.setUrl(info1[num[i]].getUrl());
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
    private void initViewPagerScroll( ){
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
            mScroller.set(viewPager, scroller);
        }catch(NoSuchFieldException e){

        }catch (IllegalArgumentException e){

        }catch (IllegalAccessException e){

        }
    }
    public void setCurrentView(int index) {
        viewPager.setCurrentItem(index);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        stopTime();
        minute = -1;
        second = -1;
        super.onDestroy();
    }
    // 弹出对话框通知用户答题时间到
    protected void showTimeOutDialog(final boolean flag, final String backtype) {
        final Dialog builder = new Dialog(this, R.style.dialog);
        builder.setContentView(R.layout.my_dialog);
        errortopicNums=pagerAdapter.errorTopicNum();
        TextView title = (TextView) builder.findViewById(R.id.dialog_title);
        TextView content = (TextView) builder.findViewById(R.id.dialog_content);
        TextView content1 = (TextView) builder.findViewById(R.id.tv);
        TextView content2 = (TextView) builder.findViewById(R.id.tv1);
        if (backtype.equals("0")) {
            content.setText("您的答题时间结束。");
            content2.setText(100-errortopicNums*2+"");
        } else if(backtype.equals("1")){
            content.setText("您要结束本次模拟答题吗？");
            content2.setText(100-errortopicNums*2+"");
        }
        final Button confirm_btn = (Button) builder
                .findViewById(R.id.dialog_sure);
        Button cancel_btn = (Button) builder.findViewById(R.id.dialog_cancle);
        if (backtype.equals("0")) {
            confirm_btn.setVisibility(View.GONE);
            cancel_btn.setText("退出");
        } else if(backtype.equals("1")){
            confirm_btn.setText("退出");
            cancel_btn.setText("继续答题");
        }else{
            confirm_btn.setText("确定");
            cancel_btn.setVisibility(View.GONE);
        }
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backtype.equals("0")){
                    builder.dismiss();
                    //uploadExamination(pagerAdapter.errorTopicNum());
                }else{
                    builder.dismiss();
                    finish();
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backtype.equals("0")) {
                    finish();
                    builder.dismiss();
                } else {
                    isPause = false;
                    builder.dismiss();
                    Message msg = new Message();
                    msg.what = 1;
                    handlerStopTime.sendMessage(msg);
                }
            }
        });
        builder.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {

                return flag;
            }
        });
        builder.show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isPause = true;
            showTimeOutDialog(true, "1");
            Message msg = new Message();
            msg.what = 0;
            handlerStopTime.sendMessage(msg);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = 0;
        handlerStopTime.sendMessage(msg);
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = 1;
        handlerStopTime.sendMessage(msg);
        super.onResume();
    }

    private void startTime() {
        if (timer == null) {
            timer = new Timer();
        }
        if (timerTask == null) {
            timerTask = new TimerTask() {

                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0;
                    handlerTime.sendMessage(msg);
                }
            };
        }
        if (timer != null && timerTask != null) {
            timer.schedule(timerTask, 0, 1000);
        }
    }

    private void stopTime(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(timerTask!=null){
            timerTask.cancel();
            timerTask=null;
        }
    }
    private  void  getShu(){
        int i=0;//计数器 指示当前要填加到的数组下标,并指示当前已经添加了几个数
        boolean b;//判断是否重复的辅助变量
        while(i<50){
            //生成一个随机数
            int j = (int)(Math.random()*1000+1);
            //将辅助变量设置为true 表示可以添加到数组
            b = true;
            //循环判断是否重复
            for(int n=0;n<i;n++){
                //如果重复,设置辅助变量为false且跳出循环
                //如果不重复则会一直将已添加的数组历遍一次
                if(num[n]==j){
                    b = false;
                    break;
                }
            }
            //如果可以添加 添加到存储数组 并将计数器i自加1
            if(b){
                num[i]=j;
                i++;
            }
        }
    }
    public void showSubmitDialog() {
        errortopicNums = pagerAdapter.errorTopicNum();
        if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
            final Dialog builder = new Dialog(SimulationTest1Activity.this, R.style.dialog);
            builder.setContentView(R.layout.my_dialog);
            TextView title = (TextView) builder.findViewById(R.id.dialog_title);
            TextView content = (TextView) builder.findViewById(R.id.dialog_content);
            TextView content1 = (TextView) builder.findViewById(R.id.tv1);
            content1.setText(100 - errortopicNums * 2 + "");
            final Button confirm_btn = (Button) builder
                    .findViewById(R.id.dialog_sure);
            Button cancel_btn = (Button) builder.findViewById(R.id.dialog_cancle);
            confirm_btn.setVisibility(View.GONE);
            cancel_btn.setText("退出");
            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    builder.dismiss();
                }
            });
            builder.setCancelable(false);// 设置点击Dialog外部任意区域关闭Dialog
            builder.show();
        }
    }
}
