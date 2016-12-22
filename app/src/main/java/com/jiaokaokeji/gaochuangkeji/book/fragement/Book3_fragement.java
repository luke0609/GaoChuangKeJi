package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.LightActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.NightActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.TestActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ThreeRuleActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ThreeSkillActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.TworuleActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.VoiceActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Book3_fragement extends Fragment {
    View view1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book3_fragement, null);
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
                Intent intent=new Intent(getActivity(),ThreeRuleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent intent2=new Intent(getActivity(),TestActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:
                Intent intent3=new Intent(getActivity(),NightActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_4:
                Intent intent4=new Intent(getActivity(),LightActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_5:
                Intent intent5=new Intent(getActivity(),VoiceActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_6:
                Intent intent6=new Intent(getActivity(),ThreeSkillActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
