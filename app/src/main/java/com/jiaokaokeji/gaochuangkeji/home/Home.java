package com.jiaokaokeji.gaochuangkeji.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;
import com.youth.banner.loader.ImageLoader;

public class Home extends Fragment {
    View view;
    private int[] images;
    private ListView lv;

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

    }

    private void initData() {

      // images = new int[]{R.drawable.gp1, R.drawable.gp2, R.drawable.gp3};


    }

    private void initView() {
        lv = ((ListView) view.findViewById(R.id.message_lv));
        View header = View.inflate(getContext(), R.layout.home_head_layout, null);//头部内容
        lv.addHeaderView(header);//添加头部
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            Glide.with(context).load(path).into(imageView);

        }
    }



}
