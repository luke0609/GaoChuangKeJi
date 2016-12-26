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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ApplyActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ProblemActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ProcessActivity;
import com.jiaokaokeji.gaochuangkeji.home.pojo.Student;
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
import static com.jiaokaokeji.gaochuangkeji.R.id.title_tv;

public class Home extends Fragment implements View.OnClickListener {
    View view;
    private List images;
    private ListView lv;
    private MyAdapter adapter;
    CircleButton  btn1,btn2,btn3;
    private  List<String> titles;
    private RelativeLayout title_top;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container, false);


        initView();
        initData();
        initEvent();

        return  view;

    }

    private void initEvent() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                if (firstVisibleItem >= 1) {
                    title_top.setVisibility(View.VISIBLE);
                } else {

                    title_top.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initData() {

      // images = new int[]{R.drawable.gp1, R.drawable.gp2, R.drawable.gp3};


    }

    private void initView() {
        lv = ((ListView) view.findViewById(R.id.message_lv));
        View header = View.inflate(getContext(), R.layout.home_head_layout, null);//头部内容
        View header2 = View.inflate(getContext(), R.layout.home_head2_layout, null);
        lv.addHeaderView(header);//添加头部
        lv.addHeaderView(header2);
        title_top = ((RelativeLayout) view.findViewById(R.id.title_top));
        btn1 = ((CircleButton) header2.findViewById(R.id.btn_1));
        btn2 = ((CircleButton) header2.findViewById(R.id.btn_2));
        btn3 = ((CircleButton) header2.findViewById(R.id.btn_3));
        List<Student> stuList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Student stu=new Student();
            stu.setAge("南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。");
            stu.setName("驾校简介");
            stu.setPhoto(R.drawable.car2);
            stuList.add(stu);


        }

        adapter=new MyAdapter(stuList,getContext());
        lv.setAdapter(adapter);
        images=new ArrayList();
        images.add(R.drawable.car2);
        images.add(R.drawable.car4);
        titles=new ArrayList();
        titles.add("学车指南");
        titles.add("驾考新规");
      //  images.add("http://pic.58pic.com/58pic/16/13/75/70658PICpiZ_1024.jpg");


        Banner banner = (Banner) header.findViewById(R.id.banner);
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
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();


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


//    public void titleAnima(int y) {
//        int scrollHeight = lv.getChildAt(0).getHeight()
//                - lv.getHeight();
//        float scrollPercent = (float) y / scrollHeight;
//        System.out.println(scrollPercent+"==123");
//        title_bar.getBackground().setAlpha((int) (255 * scrollPercent));
//
//        int color = title_tv.getTextColors().getDefaultColor();
//        int r = Color.red(color);
//        int g = Color.green(color);
//        int b = Color.blue(color);
//        int changeToColor = Color.argb((int) (255 * scrollPercent), r, g, b);
//
//        title_tv.setTextColor(changeToColor);
//
//
//
//
//    }

//    public int getScrollY() {
//        View c = lv.getChildAt(1);
//        if (c == null) {
//            return 0;
//        }
//        int firstVisiblePosition = lv.getFirstVisiblePosition();
//        int top = c.getTop();
//        return -top + firstVisiblePosition * c.getHeight() ;
//    }
}
