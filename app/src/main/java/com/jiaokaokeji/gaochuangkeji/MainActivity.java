package com.jiaokaokeji.gaochuangkeji;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.book.Book;
import com.jiaokaokeji.gaochuangkeji.home.Home;
import com.jiaokaokeji.gaochuangkeji.my.My;
import com.jiaokaokeji.gaochuangkeji.myclass.MyClass;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private long exitTime = 0;
    Fragment[] fragments;
    Home home;
    Book book;
    My my;
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
       // StatusBarCompat.compat(this, Color.parseColor("#4EAFAB"));
        ButterKnife.inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#4EAFAB"));


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
}
