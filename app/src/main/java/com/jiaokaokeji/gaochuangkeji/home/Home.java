package com.jiaokaokeji.gaochuangkeji.home;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.application.ListViewForScrollView;
import com.jiaokaokeji.gaochuangkeji.application.ObservableScrollView;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ApplyActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.MessageActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ProblemActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ProcessActivity;
import com.jiaokaokeji.gaochuangkeji.home.pojo.Student;
import com.jiaokaokeji.gaochuangkeji.myclass.signview.StringUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.markushi.ui.CircleButton;

import static android.R.attr.y;
import static android.R.drawable.title_bar;
import static android.R.id.list;
import static com.jiaokaokeji.gaochuangkeji.R.attr.header;
import static com.jiaokaokeji.gaochuangkeji.R.id.image_photo;
import static com.jiaokaokeji.gaochuangkeji.R.id.title_tv;
import static com.jiaokaokeji.gaochuangkeji.R.id.v;

public class Home extends Fragment implements View.OnClickListener {
    View view;
    private List images;
    private ListViewForScrollView lv;
    private MyAdapter adapter;
    CircleButton  btn1,btn2,btn3;
    private  List<String> titles;
    private RelativeLayout title_top;
    private  List<Student> stuList;
    private TextView title_tv;
    private ObservableScrollView sv_main;
    private String url;
    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container, false);

        initData();
        initView();
        initEvent();

        return  view;

    }


    private void initEvent() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sv_main.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY <= 0) {
                        title_tv.setTextColor(Color.argb((int) 0, 86, 171, 228));
                        title_top.setBackgroundColor(Color.argb((int) 0, 86, 171, 228));//AGB由相关工具获得，或者美工提供
                    } else if (scrollY > 0 && scrollY <= 500) {
                        float scale = (float) scrollY / 500;
                        float alpha = (255 * scale);
                        // 只是layout背景透明(仿知乎滑动效果)
                        title_tv.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                        title_top.setBackgroundColor(Color.argb((int) alpha, 86, 171, 228));
                    } else {
                        title_tv.setTextColor(Color.argb((int) 255, 255, 255, 255));
                        title_top.setBackgroundColor(Color.argb((int) 255, 86, 171, 228));
                    }

                }
            });
        }


    }

    private void initData() {
         stuList=new ArrayList<>();
      // images = new int[]{R.drawable.gp1, R.drawable.gp2, R.drawable.gp3};
        Student stu1=new Student();
        stu1.setName("驾校简介");
        stu1.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
        stu1.setPhoto(R.drawable.l1);
        stuList.add(stu1);

        Student stu2=new Student();
        stu2.setName("教学特色");
        stu2.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
        stu2.setPhoto(R.drawable.l2);
        stuList.add(stu2);

        Student stu3=new Student();
        stu3.setName("班车路线");
        stu3.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
        stu3.setPhoto(R.drawable.l3);
        stuList.add(stu3);

        Student stu4=new Student();
        stu4.setName("驾校简介");
        stu4.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
        stu4.setPhoto(R.drawable.l4);
        stuList.add(stu4);

        Student stu5=new Student();
        stu5.setName("驾校简介");
        stu5.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
        stu5.setPhoto(R.drawable.l5);
        stuList.add(stu5);

        Student stu6=new Student();
        stu6.setName("驾校简介");
        stu6.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
        stu6.setPhoto(R.drawable.l6);
        stuList.add(stu6);


    

    }

    private void initView() {
        lv = ((ListViewForScrollView) view.findViewById(R.id.message_lv));
        sv_main = ((ObservableScrollView) view.findViewById(R.id.sv_main));

        title_top = ((RelativeLayout) view.findViewById(R.id.title_top));
        title_tv = ((TextView) view.findViewById(R.id.title_tv));
        btn1 = ((CircleButton) view.findViewById(R.id.button_bar).findViewById(R.id.btn_1));
        btn2 = ((CircleButton) view.findViewById(R.id.button_bar).findViewById(R.id.btn_2));
        btn3 = ((CircleButton) view.findViewById(R.id.button_bar).findViewById(R.id.btn_3));


        adapter=new MyAdapter(stuList,getContext());
        lv.setAdapter(adapter);
        images=new ArrayList();
        images.add(R.drawable.banner2);
        images.add(R.drawable.car4);
        titles=new ArrayList();
        titles.add("二手车买卖");
        titles.add("驾考新规");
      //  images.add("http://pic.58pic.com/58pic/16/13/75/70658PICpiZ_1024.jpg");


        Banner banner =(Banner) view.findViewById(R.id.banner).findViewById(R.id.banner2);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
          banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), position+"", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), MessageActivity.class);
                switch (position){
                    case 0:
                        url="file:///android_asset/jianjie.html";
                        title="驾校简介";
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 1:
                        url="file:///android_asset/tese.html";
                        title="驾校特色";
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 2:
                        url="file:///android_asset/tese.html";
                        title="收费标准";
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 3:
                        url="file:///android_asset/tese.html";
                        title="分校地图";
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 4:
                        url="file:///android_asset/tese.html";
                        title="班车路线";
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;
                    case 5:
                        url="file:///android_asset/tese.html";
                        title="车友联盟";
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        startActivity(intent);
                        break;


                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_1:
                Intent intent1 = new Intent(getActivity(),ApplyActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_2:
                Intent intent2 = new Intent(getActivity(),ProcessActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:
                Intent intent3 = new Intent(getActivity(),ProblemActivity.class);
                startActivity(intent3);
                break;
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            Glide.with(context).load(path).into(imageView);

        }
    }



    public class MyAdapter extends BaseAdapter {

        private List<Student> stuList;
        private LayoutInflater inflater;

        public MyAdapter() {
        }

        public MyAdapter(List<Student> stuList, Context context) {
            this.stuList = stuList;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return stuList == null ? 0 : stuList.size();
        }

        @Override
        public Student getItem(int position) {
            return stuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //加载布局为一个视图
            View view = inflater.inflate(R.layout.layout_student_item, null);
            Student student = getItem(position);
            //在view视图中查找id为image_photo的控件
            ImageView image_photo = (ImageView) view.findViewById(R.id.image_photo);
            TextView tv_name = (TextView) view.findViewById(R.id.name);
            TextView tv_age = (TextView) view.findViewById(R.id.age);
             image_photo.setImageResource(student.getPhoto());
            tv_name.setText(student.getName());
            tv_age.setText(String.valueOf(student.getAge()));
            return view;
        }
    }








}
