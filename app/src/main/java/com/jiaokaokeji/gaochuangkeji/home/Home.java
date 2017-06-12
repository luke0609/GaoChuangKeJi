package com.jiaokaokeji.gaochuangkeji.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.application.ListViewForScrollView;
import com.jiaokaokeji.gaochuangkeji.application.ObservableScrollView;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ApplyActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ProblemActivity;
import com.jiaokaokeji.gaochuangkeji.home.Activity.ProcessActivity;
import com.jiaokaokeji.gaochuangkeji.home.pojo.HtmlBean;
import com.jiaokaokeji.gaochuangkeji.home.utils.CommonAdapter;
import com.jiaokaokeji.gaochuangkeji.home.utils.ViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import at.markushi.ui.CircleButton;

import static android.R.attr.type;

public class Home extends Fragment implements View.OnClickListener {
    View view;
    private List images;
    private ListViewForScrollView lv;
    private CommonAdapter<HtmlBean.DataBean> commentsAdapter;
    CircleButton btn1, btn2, btn3;
    private List<String> titles;
    private RelativeLayout title_top;
    private List<HtmlBean.DataBean> htmlList;
    private TextView title_tv;
    private ObservableScrollView sv_main;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initData();
        initView();


        initEvent();
        return view;

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
        htmlList = new ArrayList<>();
        RequestParams params = new RequestParams("http://221.6.30.166:9098/tianbaoApp/index.php/subjects/videos/selecthtml");
        params.addQueryStringParameter("key", "tbjxappgaochuang");
        //获取首页列表
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {

        lv = ((ListViewForScrollView) view.findViewById(R.id.message_lv));
        sv_main = ((ObservableScrollView) view.findViewById(R.id.sv_main));

        title_top = ((RelativeLayout) view.findViewById(R.id.title_top));
        title_tv = ((TextView) view.findViewById(R.id.title_tv));
        btn1 = ((CircleButton) view.findViewById(R.id.button_bar).findViewById(R.id.btn_1));
        btn2 = ((CircleButton) view.findViewById(R.id.button_bar).findViewById(R.id.btn_2));
        btn3 = ((CircleButton) view.findViewById(R.id.button_bar).findViewById(R.id.btn_3));


        images = new ArrayList();
        images.add(R.drawable.banner2);
        images.add(R.drawable.car4);
        titles = new ArrayList();
        titles.add("二手车买卖");
        titles.add("驾考新规");
        //  images.add("http://pic.58pic.com/58pic/16/13/75/70658PICpiZ_1024.jpg");


        Banner banner = (Banner) view.findViewById(R.id.banner).findViewById(R.id.banner2);
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
        if (commentsAdapter == null) {

            commentsAdapter = new CommonAdapter<HtmlBean.DataBean>(getContext(), htmlList, R.layout.layout_student_item) {
                @Override
                public void convert(ViewHolder viewHolder, HtmlBean.DataBean dataBean, int position) {
                    ImageView img_bk = viewHolder.getViewById(R.id.image_photo);
                    TextView tv_title = viewHolder.getViewById(R.id.title);
                    TextView tv_content = viewHolder.getViewById(R.id.content);

                    tv_title.setText(dataBean.getHtmls_title());
                    tv_content.setText(dataBean.getHtmls_content());
                }
            };

            lv.setAdapter(commentsAdapter);
        } else {
            commentsAdapter.notifyDataSetChanged();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_1:
                Intent intent1 = new Intent(getActivity(), ApplyActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_2:
                Intent intent2 = new Intent(getActivity(), ProcessActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:
                Intent intent3 = new Intent(getActivity(), ProblemActivity.class);
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


}
