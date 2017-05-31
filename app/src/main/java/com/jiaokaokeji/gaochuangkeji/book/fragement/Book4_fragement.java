package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.Mymistakes1Activity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.Order1Activity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.Radom1Activity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.SimulationTest1Activity;
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

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_8})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Intent intent=new Intent(getActivity(),Radom1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                showDiaog();
                break;
            case R.id.btn_3:
                Intent intent2=new Intent(getActivity(),Order1Activity.class);
                startActivity(intent2);
                break;
            case R.id.btn_4:
                Intent intent3=new Intent(getActivity(),Mymistakes1Activity.class);
                startActivity(intent3);
                break;
            case R.id.btn_8:
                Intent intent4=new Intent(getActivity(),SimulationTest1Activity.class);
                startActivity(intent4);
                break;
        }
    }
    public void showDiaog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog1);
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button queding = (Button) window.findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
