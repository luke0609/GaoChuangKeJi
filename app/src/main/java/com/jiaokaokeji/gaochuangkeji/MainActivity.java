package com.jiaokaokeji.gaochuangkeji;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
    AnSwerInfo anSwerInfo = new AnSwerInfo();
    My my;
    DBManager dbManager=new DBManager(MainActivity.this);
    MyClass myClass;
    int oldIndex;//用户看到的item
    int newIndex;//用户即将看到的item
    RadioButton[] tabs;
    @InjectView(R.id.rb_rb1)
    RadioButton rbRb1;
    @InjectView(R.id.rb_rb2)
    RadioButton rbRb2;
    @InjectView(R.id.rb_rb3)
    RadioButton rbRb3;
    @InjectView(R.id.rb_rb4)
    RadioButton rbRb4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager.openDB1();
        getGonggao();
       // StatusBarCompat.compat(this, Color.parseColor("#4EAFAB"));
        ButterKnife.inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));


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
//        RequestParams params = new RequestParams("http://192.168.0.104:8080/ex/exa");
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Gson gson = new Gson();
//                AnSwerInfo bean = gson.fromJson(result, AnSwerInfo.class);
//                gonggaoList.clear();
//                gonggaoList.addAll(bean.ggList);
        String[] gonggaoList = new String[]{"1", "这个标志是何含义?", "D", "小型车车道", "小型车专用车道",
                "多乘员车辆专用车道", "机动车车道", "此为机动车车道,比多乘员车辆专用车道少俩人."};
        for (int i = 0; i < gonggaoList.length; i++) {
            anSwerInfo.setQuestionId(Integer.parseInt(gonggaoList[0]));
            // anSwerInfo.setQuestionType(gonggaoList.get(i).questionType);
            anSwerInfo.setCorrectAnswer(gonggaoList[2]);
            //anSwerInfo.setIsSelect(gonggaoList.get(i).isSelect);
            // anSwerInfo.setOption_type(gonggaoList.get(i).option_type);
            anSwerInfo.setQuestionName(gonggaoList[1]);
            anSwerInfo.setAnalysis(gonggaoList[7]);
            anSwerInfo.setOptionA(gonggaoList[3]);
            anSwerInfo.setOptionB(gonggaoList[4]);
            anSwerInfo.setOptionC(gonggaoList[5]);
            anSwerInfo.setOptionD(gonggaoList[6]);
            anSwerInfo.setUrl(null);
        }
        dbManager.insertQuestion(anSwerInfo);
    }
}
