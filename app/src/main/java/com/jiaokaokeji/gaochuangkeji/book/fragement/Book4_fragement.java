package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ChapterActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.CurveActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.MymistakesActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.OrderActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ParallelActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.RadomActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ReversingActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.RightActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.SkinActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.UpActivity;
import com.jiaokaokeji.gaochuangkeji.myclass.Activity.SimulationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Book4_fragement extends Fragment {
    View view1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book4_fragement, null);
        ButterKnife.inject(this, view1);
        return view1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Intent intent=new Intent(getActivity(),OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent intent1=new Intent(getActivity(),RadomActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_3:
                Intent intent2=new Intent(getActivity(),ChapterActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_4:
                Intent intent3=new Intent(getActivity(),SimulationActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_5:
                Intent intent4=new Intent(getActivity(),MymistakesActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_6:
                Intent intent5=new Intent(getActivity(),SkinActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
