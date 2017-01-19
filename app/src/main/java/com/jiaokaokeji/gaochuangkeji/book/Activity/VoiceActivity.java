package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.pojo.Explain;


import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.R.attr.path;


public class VoiceActivity extends AppCompatActivity {
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.btn_1)
    CircleButton btn1;
    @InjectView(R.id.btn_2)
    CircleButton btn2;
    @InjectView(R.id.btn_3)
    CircleButton btn3;
    @InjectView(R.id.btn_4)
    CircleButton btn4;
    @InjectView(R.id.btn_5)
    CircleButton btn5;
    @InjectView(R.id.btn_6)
    CircleButton btn6;
    @InjectView(R.id.lv_dl)
    ListView lvDl;
    private  List<Explain> ExplainList;
    private MediaPlayer mp;
    private boolean flag = false;
    private int lastNum = 0;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        ButterKnife.inject(this);
        initDate();
        initView();


    }

    private void initDate() {
        ExplainList=new ArrayList<>();
        Explain ex1=new Explain();
        ex1.setExplain("正确操作:开启灯光");
        ex1.setInstructions("1.夜间在没有路灯，照明不良条件下行驶");
        ex1.setPhoto1(R.drawable.y1_1);
        ExplainList.add(ex1);

        Explain ex2=new Explain();
        ex2.setExplain("正确操作:远光灯");
        ex2.setInstructions("2.请将前照灯变换成远光");
        ex2.setPhoto1(R.drawable.y1_3);
        ex2.setPhoto2(R.drawable.y1_2);
        ExplainList.add(ex2);

        Explain ex3=new Explain();
        ex3.setExplain("正确操作:近光灯");
        ex3.setInstructions("3.夜间在窄路与非机动车会车");
        ExplainList.add(ex3);

        Explain ex4=new Explain();
        ex4.setExplain("正确操作:远光灯");
        ex4.setInstructions("4.请将前照灯变换成远光");
        ExplainList.add(ex4);
    }

    private void initView() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll1);

        adapter=new MyAdapter(ExplainList,getApplicationContext());
        lvDl.setAdapter(adapter);

    }


    @OnClick({R.id.iv, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv:
                finish();
                break;
            case R.id.btn_1:
                if (mp.isPlaying()) {
                    mp.stop();
                    reset(lastNum);
                    if (lastNum != 1) {
                        btn1.setImageResource(R.drawable.playing);
                        btn1.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll1);
                        mp.start();
                        lastNum = 1;
                    }
                } else {
                    btn1.setImageResource(R.drawable.playing);
                    btn1.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll1);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            reset(lastNum);
                        }
                    });
                    mp.start();
                    lastNum = 1;
                }
                break;
            case R.id.btn_2:
                if (mp.isPlaying()) {
                    mp.stop();
                    reset(lastNum);
                    if (lastNum != 2) {
                        btn2.setImageResource(R.drawable.playing);
                        btn2.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll2);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                reset(lastNum);
                            }
                        });
                        mp.start();
                        lastNum = 2;
                    }
                } else {
                    btn2.setImageResource(R.drawable.playing);
                    btn2.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll2);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            reset(lastNum);
                        }
                    });
                    mp.start();
                    lastNum = 2;
                }
                break;
            case R.id.btn_3:
                if (mp.isPlaying()) {
                    mp.stop();
                    reset(lastNum);
                    if (lastNum != 3) {
                        btn3.setImageResource(R.drawable.playing);
                        btn3.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll3);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                reset(lastNum);
                            }
                        });
                        mp.start();
                        lastNum = 3;
                    }
                } else {
                    btn3.setImageResource(R.drawable.playing);
                    btn3.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll3);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            reset(lastNum);
                        }
                    });
                    mp.start();
                    lastNum = 3;
                }
                break;
            case R.id.btn_4:
                if (mp.isPlaying()) {
                    mp.stop();
                    reset(lastNum);
                    if (lastNum != 4) {
                        btn4.setImageResource(R.drawable.playing);
                        btn4.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll4);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                reset(lastNum);
                            }
                        });
                        mp.start();
                        lastNum = 4;
                    }
                } else {
                    btn4.setImageResource(R.drawable.playing);
                    btn4.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll4);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            reset(lastNum);
                        }
                    });
                    mp.start();
                    lastNum = 4;
                }
                break;
            case R.id.btn_5:
                if (mp.isPlaying()) {
                    mp.stop();
                    reset(lastNum);
                    if (lastNum != 5) {
                        btn5.setImageResource(R.drawable.playing);
                        btn5.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll5);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                reset(lastNum);
                            }
                        });
                        mp.start();
                        lastNum = 5;
                    }
                } else {
                    btn5.setImageResource(R.drawable.playing);
                    btn5.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll5);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            reset(lastNum);
                        }
                    });
                    mp.start();
                    lastNum = 5;
                }
                break;
            case R.id.btn_6:
                if (mp.isPlaying()) {
                    mp.stop();
                    reset(lastNum);
                    if (lastNum != 6) {
                        btn6.setImageResource(R.drawable.playing);
                        btn6.setColor(Color.parseColor("#FAAF13"));
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.ll6);
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                reset(lastNum);
                            }
                        });
                        mp.start();
                        lastNum = 6;
                    }
                } else {
                    btn6.setImageResource(R.drawable.playing);
                    btn6.setColor(Color.parseColor("#FAAF13"));
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.ll6);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            reset(lastNum);
                        }
                    });
                    mp.start();
                    lastNum = 6;
                }
                break;
        }
    }

    private void reset(int Num) {
        switch (Num) {
            case 1:
                btn1.setImageResource(R.drawable.play);
                btn1.setColor(Color.parseColor("#03A89E"));
                break;
            case 2:
                btn2.setImageResource(R.drawable.play);
                btn2.setColor(Color.parseColor("#03A89E"));
                break;
            case 3:
                btn3.setImageResource(R.drawable.play);
                btn3.setColor(Color.parseColor("#03A89E"));
                break;
            case 4:
                btn4.setImageResource(R.drawable.play);
                btn4.setColor(Color.parseColor("#03A89E"));
                break;
            case 5:
                btn5.setImageResource(R.drawable.play);
                btn5.setColor(Color.parseColor("#03A89E"));
                break;
            case 6:
                btn6.setImageResource(R.drawable.play);
                btn6.setColor(Color.parseColor("#03A89E"));
                break;
        }
    }


    public class MyAdapter extends BaseAdapter {

        private List<Explain> exList;
        private LayoutInflater inflater;

        public MyAdapter() {
        }

        public MyAdapter(List<Explain> exList, Context context) {
            this.exList = exList;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return exList == null ? 0 : exList.size();
        }

        @Override
        public Explain getItem(int position) {
            return exList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //加载布局为一个视图
            View view = inflater.inflate(R.layout.layout_explain_item, null);
            Explain explain = getItem(position);
            //在view视图中查找id为image_photo的控件
            ImageView image1 = (ImageView) view.findViewById(R.id.iv_1);
            ImageView image2 = (ImageView) view.findViewById(R.id.iv_2);
            TextView tv1 = (TextView) view.findViewById(R.id.tv_1);
            TextView tv2 = (TextView) view.findViewById(R.id.tv_2);
            tv1.setText(explain.getInstructions());
            tv2.setText(explain.getExplain());
            if (explain.getPhoto1()!=0){
            image1.setVisibility(View.VISIBLE);
            image1.setImageResource(explain.getPhoto1());}
            if (explain.getPhoto1()!=0){
                image2.setVisibility(View.VISIBLE);
                image2.setImageResource(explain.getPhoto2());}

            return view;
        }
    }
}
