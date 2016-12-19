package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.StaggeredGridView.MyGrigview;
import com.jiaokaokeji.gaochuangkeji.book.prjo.MyGridViewAdapter;
import com.jiaokaokeji.gaochuangkeji.book.prjo.MyScollview;

public class Book2_fragement extends Fragment {
    View view1;
    private int urls[] = {R.drawable.detail,R.drawable.detail,R.drawable.detail,R.drawable.detail,
            R.drawable.detail,R.drawable.detail,R.drawable.detail,R.drawable.detail};
    String title[]=new String[]{"倒车入库","倒车入库","倒车入库","倒车入库","倒车入库","倒车入库",
            "倒车入库","倒车入库"};
    private TextView tv1;
    private MyScollview myScollview;
    private View v1;
    private int buyLayoutHeight;
    private int buyLayoutTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book2_fragement,null);
        MyGrigview gridView = (MyGrigview) view1.findViewById(R.id.staggeredGridView1);
        v1 = ((View) view1.findViewById(R.id.v1));
        myScollview = ((MyScollview) view1.findViewById(R.id.myScollview));
        myScollview.setOnScrollListener(new MyScollview.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {

            }
        });


        MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), R.layout.item,
                urls,title);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    return view1;
}

}
