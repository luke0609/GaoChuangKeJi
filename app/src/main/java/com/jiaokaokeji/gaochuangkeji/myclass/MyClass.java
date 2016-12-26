package com.jiaokaokeji.gaochuangkeji.myclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyClass extends Fragment {
    View view1;
    @InjectView(R.id.saoma)
    ImageView saoma;
    private static final int RESULT_OK = 123;
    private static final int  SCANNIN_GREQUEST_CODE = 13;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_my_class, null);
        ButterKnife.inject(this, view1);
        return view1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.saoma)
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivity(intent);
    }
}
