package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.jiaokaokeji.gaochuangkeji.R;

public class Book4_fragement extends Fragment {
    View view1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book4_fragement,null);
        return view1;
    }
}
