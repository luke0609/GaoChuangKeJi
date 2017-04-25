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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ChapterActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.MymistakesActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.OrderActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.RadomActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.SimulationTestActivity;
import com.jiaokaokeji.gaochuangkeji.my.Activity.PersonalActivity;
import com.jiaokaokeji.gaochuangkeji.my.prjo.WheelView;
import com.jiaokaokeji.gaochuangkeji.myclass.Activity.SimulationActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.jiaokaokeji.gaochuangkeji.R.layout.dialog;

public class Book1_fragement extends Fragment {
    private View view1;
    @InjectView(R.id.v1)
    View v1;
    @InjectView(R.id.rl)
    RelativeLayout rl;
    @InjectView(R.id.scroll_view)
    ScrollView scrollView;

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
                showDiaog();
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
                Intent intent8=new Intent(getActivity(),SimulationTestActivity.class);
                startActivity(intent8);
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
