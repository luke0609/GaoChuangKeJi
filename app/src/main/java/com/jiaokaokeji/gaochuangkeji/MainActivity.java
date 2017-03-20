package com.jiaokaokeji.gaochuangkeji;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiaokaokeji.gaochuangkeji.book.Book;
import com.jiaokaokeji.gaochuangkeji.book.database.DBManager;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.home.Home;
import com.jiaokaokeji.gaochuangkeji.my.My;
import com.jiaokaokeji.gaochuangkeji.myclass.MyClass;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;
    Fragment[] fragments;
    Home home;
    Book book;
    //ArrayList<AnSwerInfo> gonggaoList = new ArrayList<>();
    AnSwerInfo anSwerInfo1 = new AnSwerInfo();
    AnSwerInfo anSwerInfo = new AnSwerInfo();
    My my;
    DBManager dbManager = new DBManager(MainActivity.this);
    MyClass myClass;
    ArrayList<AnSwerInfo> infoArrayList = new ArrayList<>();
    int oldIndex;//用户看到的item
    int newIndex;//用户即将看到的item
    String tag="1";
    RadioButton[] tabs;
    @InjectView(R.id.rb_rb1)
    RadioButton rbRb1;
    @InjectView(R.id.rb_rb2)
    RadioButton rbRb2;
    @InjectView(R.id.rb_rb3)
    RadioButton rbRb3;
    @InjectView(R.id.rb_rb4)
    RadioButton rbRb4;
    private boolean isFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        dbManager.openDB1();
        SharedPreferences setting = getSharedPreferences("share", 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            getGonggao();
        }



        home = new Home();
        book = new Book();
        my = new My();
        myClass = new MyClass();
        fragments = new Fragment[]{home, myClass, book, my};

        //设置按钮的数组
        tabs = new RadioButton[4];
        tabs[0] = (RadioButton) findViewById(R.id.rb_rb1);//主页的button
        tabs[1] = (RadioButton) findViewById(R.id.rb_rb2);//课程的button
        tabs[2] = (RadioButton) findViewById(R.id.rb_rb3);//题库的button
        tabs[3] = (RadioButton) findViewById(R.id.rb_rb4);//个人的button
        //界面初始显示第一个fragment;添加第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.f1_content, fragments[0]).commit();
        //初始时，按钮1选中
        tabs[0].setChecked(true);
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

    public void addshow(int newIndex) {
        FragmentTransaction transaction;

        //如果选择的项不是当前选中项，则替换；否则，不做操作
        if (newIndex != oldIndex) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments[oldIndex]);//隐藏当前显示项


            //如果选中项没有加过，则添加
            if (!fragments[newIndex].isAdded()) {
                //添加fragment
                transaction.add(R.id.f1_content, fragments[newIndex]);
            }
            //显示当前选择项
            transaction.show(fragments[newIndex]).commit();
        }


        //之前选中的项，取消选中
        tabs[oldIndex].setSelected(false);
        //当前选择项，按钮被选中
        tabs[newIndex].setSelected(true);


        //当前选择项变为选中项
        oldIndex = newIndex;

    }

    @OnClick({R.id.rb_rb1, R.id.rb_rb2, R.id.rb_rb3, R.id.rb_rb4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_rb1:
                newIndex=0;//选中第一项
                break;
            case R.id.rb_rb2:
                newIndex=1;//选中第二项
                break;
            case R.id.rb_rb3:
                newIndex=2;//选中第三项
                break;
            case R.id.rb_rb4:
                newIndex=3;//选中第四项
                break;
        }
        addshow(newIndex);
    }


    //再按一次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }
    public void getGonggao() {

        String[] gonggaoList = new String[]{"1", "这个标志是何含义?", "D", "小型车车道", "小型车专用车道",
                "多乘员车辆专用车道", "机动车车道", "此为机动车车道,比多乘员车辆专用车道少俩人.","http://images.juheapi.com/jztk/c1c2subject1/1.jpg"};
        String[] gonggaoList1 = new String[]{"2", "这个标志是何含义?", "B", "分向行驶车道", "掉头和左转合用车道",
                "禁止左转和掉头车道", "直行和左转合用车道", "左转和掉头合并在一个标志里,你应该能看到的.","http://images.juheapi.com/jztk/c1c2subject1/10.jpg"};
        for (int i = 0; i < gonggaoList.length; i++) {
            anSwerInfo1.setQuestionId(Integer.parseInt(gonggaoList[0]));
            anSwerInfo1.setCorrectAnswer(gonggaoList[2]);
            anSwerInfo1.setQuestionName(gonggaoList[1]);
            anSwerInfo1.setAnalysis(gonggaoList[7]);
            anSwerInfo1.setOptionA(gonggaoList[3]);
            anSwerInfo1.setOptionB(gonggaoList[4]);
            anSwerInfo1.setOptionC(gonggaoList[5]);
            anSwerInfo1.setOptionD(gonggaoList[6]);
            anSwerInfo1.setUrl(gonggaoList[8]);
        }
        infoArrayList.add(anSwerInfo1);
        for (int i = 0; i < gonggaoList.length; i++) {
            anSwerInfo.setQuestionId(Integer.parseInt(gonggaoList1[0]));
            anSwerInfo.setCorrectAnswer(gonggaoList1[2]);
            anSwerInfo.setQuestionName(gonggaoList1[1]);
            anSwerInfo.setAnalysis(gonggaoList1[7]);
            anSwerInfo.setOptionA(gonggaoList1[3]);
            anSwerInfo.setOptionB(gonggaoList1[4]);
            anSwerInfo.setOptionC(gonggaoList1[5]);
            anSwerInfo.setOptionD(gonggaoList1[6]);
            anSwerInfo.setUrl(gonggaoList1[8]);
        }
        infoArrayList.add(anSwerInfo);
        dbManager.insertQuestion(infoArrayList);
    }

}
