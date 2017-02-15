package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.CurveActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ParallelActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.ReversingActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.RightActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.TworuleActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.UpActivity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.VideoActivity;
import com.jiaokaokeji.gaochuangkeji.book.StaggeredGridView.MyGrigview;
import com.jiaokaokeji.gaochuangkeji.book.prjo.MyGridViewAdapter;
import com.jiaokaokeji.gaochuangkeji.book.prjo.MyScollview;

import at.markushi.ui.CircleButton;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Book2_fragement extends Fragment {
    View view1;
    int height;
    int width;
    private int urls[] = {R.drawable.video1, R.drawable.video1, R.drawable.video1, R.drawable.video1,
            R.drawable.video1, R.drawable.video1, R.drawable.video1, R.drawable.video1};
    String title[] = new String[]{"倒车入库", "倒车入库", "倒车入库", "倒车入库", "倒车入库", "倒车入库",
            "倒车入库", "倒车入库"};
    private TextView tv1;
    private MyScollview myScollview;
    private View v1;
    private CircleButton btn_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book2_fragement, null);
        btn_2 = ((CircleButton) view1.findViewById(R.id.btn_2));
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), ReversingActivity.class);
                startActivity(intent2);
            }
        });
        MyGrigview gridView = (MyGrigview) view1.findViewById(R.id.staggeredGridView1);
        v1 = ((View) view1.findViewById(R.id.v1));
        myScollview = ((MyScollview) view1.findViewById(R.id.myScollview));
        myScollview.fullScroll(MyScollview.FOCUS_UP);
        myScollview.setOnScrollListener(new MyScollview.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {

            }
        });
        gridView.setFocusable(false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                      Intent intent1=new Intent(getActivity(), VideoActivity.class);
                        intent1.putExtra("title","倒车入库");
                        intent1.putExtra("url","");
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(getActivity(), VideoActivity.class);
                        intent2.putExtra("title","倒车入库");
                        intent2.putExtra("url","");
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(getActivity(), VideoActivity.class);
                        intent3.putExtra("title","倒车入库");
                        intent3.putExtra("url","");
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(getActivity(), VideoActivity.class);
                        intent4.putExtra("title","倒车入库");
                        intent4.putExtra("url","");
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5=new Intent(getActivity(), VideoActivity.class);
                        intent5.putExtra("title","倒车入库");
                        intent5.putExtra("url","");
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6=new Intent(getActivity(), VideoActivity.class);
                        intent6.putExtra("title","倒车入库");
                        intent6.putExtra("url","");
                        startActivity(intent6);
                        break;
                    default:
                        break;

                }
            }
        });
        MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), R.layout.item,
                urls, title);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        ButterKnife.inject(this, view1);
        return view1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_1,R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Intent intent = new Intent(getActivity(), TworuleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_3:
                Intent intent3 = new Intent(getActivity(), UpActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_4:
                Intent intent4 = new Intent(getActivity(), ParallelActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_5:
                Intent intent5 = new Intent(getActivity(), CurveActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_6:
                Intent intent6 = new Intent(getActivity(), RightActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
