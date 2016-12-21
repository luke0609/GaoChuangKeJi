package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ChapterActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.MymistakesActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.OrderActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.RadomActivity;
import com.jiaokaokeji.gaochuangkeji.myclass.Activity.SimulationActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Book1_fragement extends Fragment {
    View view1;
    int i = 1;
    ImageView mHeadImg;
    @InjectView(R.id.rb_rb4)
    RadioButton rbRb4;
    @InjectView(R.id.v1)
    View v1;
    @InjectView(R.id.rb_rb1)
    RadioButton rbRb1;
    @InjectView(R.id.rb_rb2)
    RadioButton rbRb2;
    @InjectView(R.id.rb_rb3)
    RadioButton rbRb3;
    @InjectView(R.id.rg_tab)
    RadioGroup rgTab;
    @InjectView(R.id.rl)
    RelativeLayout rl;
    @InjectView(R.id.scroll_view)
    ScrollView scrollView;
    private ScrollView scollview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book1_fragement, null);

        ButterKnife.inject(this, view1);
        return view1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Intent intent=new Intent(getActivity(), RadomActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent intent2=new Intent(getActivity(), ChapterActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:
                Intent intent3=new Intent(getActivity(), OrderActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_4:
                Intent intent4=new Intent(getActivity(), MymistakesActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_8:
                Intent intent8=new Intent(getActivity(),SimulationActivity.class);
                startActivity(intent8);
                break;
        }
    }
}
