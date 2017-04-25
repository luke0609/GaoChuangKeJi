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
import com.jiaokaokeji.gaochuangkeji.book.Activity.Radom1Activity;
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
    ArrayList<AnSwerInfo.DataBean> dataBeanArrayList = new ArrayList<>();
    AnSwerInfo.DataBean dataBean=new AnSwerInfo.DataBean();
    AnSwerInfo.DataBean dataBean1=new AnSwerInfo.DataBean();
    AnSwerInfo.DataBean dataBean2=new AnSwerInfo.DataBean();
    ArrayList<AnSwerInfo.DataBean> dataBeanArrayList1 = new ArrayList<>();
    private long exitTime = 0;
    Fragment[] fragments;
    Home home;
    Book book;
    //ArrayList<AnSwerInfo> gonggaoList = new ArrayList<>();
    //AnSwerInfo anSwerInfo1 = new AnSwerInfo();
    //AnSwerInfo anSwerInfo = new AnSwerInfo();
    My my;
    DBManager dbManager = new DBManager(MainActivity.this);
    MyClass myClass;
    ArrayList<AnSwerInfo> infoArrayList = new ArrayList<>();
    int oldIndex;//用户看到的item
    int newIndex;//用户即将看到的item
    String tag = "1";
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
    AnSwerInfo.DataBean[] info1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        dbManager.openDB1();
        dbManager.openDB();
        info1 = dbManager.queryAllData1();
        if(info1==null) {
            Toast.makeText(getApplicationContext(), "正在加载数据，请等待...", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        getTimu1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        getTimu2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
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
                newIndex = 0;//选中第一项
                break;
            case R.id.rb_rb2:
                newIndex = 1;//选中第二项
                break;
            case R.id.rb_rb3:
                newIndex = 2;//选中第三项
                break;
            case R.id.rb_rb4:
                newIndex = 3;//选中第四项
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
     *
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     *
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove(listener);
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

    public  void getTimu1() {
        RequestParams params = new RequestParams(Netutil.url1 + "/tianbao.php");
        params.addBodyParameter("num", "1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                AnSwerInfo dataBean = gson.fromJson(result, AnSwerInfo.class);
                dataBeanArrayList.addAll(dataBean.getData());
                dbManager.insertQuestion(dataBeanArrayList);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast toast = Toast.makeText(MainActivity.this,ex.toString(), Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getTimu2() {
        RequestParams params = new RequestParams(Netutil.url1+ "/tianbao.php");
        params.addBodyParameter("num", "4");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                AnSwerInfo dataBean = gson.fromJson(result, AnSwerInfo.class);
                dataBeanArrayList1.addAll(dataBean.getData());
                dbManager.insertQuestion1(dataBeanArrayList1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast toast = Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    public void timu(){
        String[] gonggaoList = new String[]{"1", "这个标志是何含义?", "7", "小型车车道", "小型车专用车道",
                "多乘员车辆专用车道", "机动车车道", "此为机动车车道,比多乘员车辆专用车道少俩人.", "http://images.juheapi.com/jztk/c1c2subject1/1.jpg"};
        String[] gonggaoList1 = new String[]{"2", "这个标志是何含义?", "10", "分向行驶车道", "掉头和左转合用车道",
                "", "", "左转和掉头合并在一个标志里,你应该能看到的.", "http://images.juheapi.com/jztk/c1c2subject1/10.jpg"};
        String[] gonggaoList2 = new String[]{"2", "这个标志是何含义?", "1", "分向行驶车道", "掉头和左转合用车道",
                "", "", "左转和掉头合并在一个标志里,你应该能看到的.", "http://images.juheapi.com/jztk/c1c2subject1/10.jpg"};
        for (int i = 0; i < gonggaoList.length; i++) {
           dataBean.setId (gonggaoList[0]);
            dataBean.setAnswer(gonggaoList[2]);
            dataBean.setQuestion(gonggaoList[1]);
            dataBean.setExplains(gonggaoList[7]);
            dataBean.setItem1(gonggaoList[3]);
            dataBean.setItem2(gonggaoList[4]);
            dataBean.setItem3(gonggaoList[5]);
            dataBean.setItem4(gonggaoList[6]);
            dataBean.setUrl(gonggaoList[8]);
        }
        dataBeanArrayList1.add(dataBean);
        for (int i = 0; i < gonggaoList2.length; i++) {
            dataBean1.setId (gonggaoList2[0]);
            dataBean1.setAnswer(gonggaoList2[2]);
            dataBean1.setQuestion(gonggaoList2[1]);
            dataBean1.setExplains(gonggaoList2[7]);
            dataBean1.setItem1(gonggaoList2[3]);
            dataBean1.setItem2(gonggaoList2[4]);
            dataBean1.setItem3(gonggaoList2[5]);
            dataBean1.setItem4(gonggaoList2[6]);
            dataBean1.setUrl(gonggaoList2[8]);
        }
        dataBeanArrayList1.add(dataBean1);
        for (int i = 0; i < gonggaoList1.length; i++) {
            dataBean2.setId (gonggaoList1[0]);
            dataBean2.setAnswer(gonggaoList1[2]);
            dataBean2.setQuestion(gonggaoList1[1]);
            dataBean2.setExplains(gonggaoList1[7]);
            dataBean2.setItem1(gonggaoList1[3]);
            dataBean2.setItem2(gonggaoList1[4]);
            dataBean2.setItem3(gonggaoList1[5]);
            dataBean2.setItem4(gonggaoList1[6]);
            dataBean2.setUrl(gonggaoList1[8]);
        }
        dataBeanArrayList1.add(dataBean2);
        dbManager.insertQuestion1(dataBeanArrayList1);
    }
}
