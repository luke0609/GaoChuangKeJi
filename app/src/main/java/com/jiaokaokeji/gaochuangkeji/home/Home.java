package com.jiaokaokeji.gaochuangkeji.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

import static android.R.id.list;

public class Home extends Fragment implements View.OnClickListener {
    View view;
    private List images;
    private ListView lv;
    private MyAdapter adapter;
    CircleButton  btn1,btn2,btn3;
    private  List<String> titles;
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
    }

    private void initData() {

      // images = new int[]{R.drawable.gp1, R.drawable.gp2, R.drawable.gp3};


    }

    private void initView() {
        lv = ((ListView) view.findViewById(R.id.message_lv));
        View header = View.inflate(getContext(), R.layout.home_head_layout, null);//头部内容
        lv.addHeaderView(header);//添加头部
        btn1 = ((CircleButton) header.findViewById(R.id.btn_1));
        btn2 = ((CircleButton) header.findViewById(R.id.btn_2));
        btn3 = ((CircleButton) header.findViewById(R.id.btn_3));
        List<Student> stuList=new ArrayList<>();
        for(int i=0;i<10;i++){
            Student stu=new Student();
            stu.setAge(10+i);
            stu.setName("name"+i);
            stu.setPhoto(R.mipmap.ic_launcher);
            stuList.add(stu);


        }

        adapter=new MyAdapter(stuList,getContext());
        lv.setAdapter(adapter);
        images=new ArrayList();
        images.add(R.drawable.car2);
        images.add(R.drawable.car4);
        titles=new ArrayList();
        titles.add("123");
        titles.add("321");
      //  images.add("http://pic.58pic.com/58pic/16/13/75/70658PICpiZ_1024.jpg");


        Banner banner = (Banner) header.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
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
}
