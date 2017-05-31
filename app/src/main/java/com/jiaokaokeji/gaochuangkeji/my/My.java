package com.jiaokaokeji.gaochuangkeji.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.my.Activity.ArchivesActivity;
import com.jiaokaokeji.gaochuangkeji.my.Activity.MyOrderActivity;
import com.jiaokaokeji.gaochuangkeji.my.Activity.PersonalActivity;
import com.jiaokaokeji.gaochuangkeji.my.Activity.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class My extends Fragment {
    View view;
    private RelativeLayout rl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);
        rl = ((RelativeLayout) view.findViewById(R.id.rl));
        ViewGroup.LayoutParams params = rl.getLayoutParams();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        params.height = (int) ((height) * 0.35);
        rl.setLayoutParams(params);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ib, R.id.ib2, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib:
                break;
            case R.id.ib2:
                break;
            case R.id.btn_1:
                Intent intent=new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent intent1=new Intent(getActivity(),ArchivesActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_3:
                Intent intent4=new Intent(getActivity(),MyOrderActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_4:
                Intent intent3=new Intent(getActivity(),SettingActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
