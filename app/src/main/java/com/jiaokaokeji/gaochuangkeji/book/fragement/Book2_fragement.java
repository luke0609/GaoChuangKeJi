package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiaokaokeji.gaochuangkeji.R;

public class Book2_fragement extends Fragment {
    View view1;
    private String urls[] = {
            "http://farm7.staticflickr.com/6101/6853156632_6374976d38_c.jpg",
            "http://farm8.staticflickr.com/7232/6913504132_a0fce67a0e_c.jpg",
            "http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg",
            "http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg",
            "http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg",
            "http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg",
            "http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg",
            "http://farm9.staticflickr.com/8060/8173387478_a117990661.jpg",};
    String title[]=new String[]{"倒车入库","倒车入库","倒车入库","倒车入库","倒车入库","倒车入库",
            "倒车入库","倒车入库"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book2_fragement,null);
    return view1;
}
}
