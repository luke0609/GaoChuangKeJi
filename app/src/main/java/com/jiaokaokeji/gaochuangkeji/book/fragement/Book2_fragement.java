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
    String title[] = new String[]{"倒车入库", "空驾练习", "直角转弯", "曲线行驶", "坡道定点停车和起步", "侧方停车",
            "起步，上车动作", "天保驾校全国首创：电动教学训练仪"};
    String image[]=new String[]{"http://www.tbqjx.com/loadimage.php?id=54","http://www.tbqjx.com/loadimage.php?id=55",
    "http://www.tbqjx.com/loadimage.php?id=48","http://www.tbqjx.com/loadimage.php?id=49",
    "http://www.tbqjx.com/loadimage.php?id=50","http://www.tbqjx.com/loadimage.php?id=51",
    "http://www.tbqjx.com/loadimage.php?id=52","http://www.tbqjx.com/loadimage.php?id=53"};
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
                        intent1.putExtra("title",title[position]);
                        intent1.putExtra("url","http://www.tbqjx.com/files/video/7.mp4");
                        intent1.putExtra("image",image[position]);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(getActivity(), VideoActivity.class);
                        intent2.putExtra("title",title[position]);
                        intent2.putExtra("url","http://www.tbqjx.com/files/video/8.mp4");
                        intent2.putExtra("image",image[position]);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(getActivity(), VideoActivity.class);
                        intent3.putExtra("title",title[position]);
                        intent3.putExtra("url","http://www.tbqjx.com/files/video/6.mp4");
                        intent3.putExtra("image",image[position]);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4=new Intent(getActivity(), VideoActivity.class);
                        intent4.putExtra("title",title[position]);
                        intent4.putExtra("url","http://www.tbqjx.com/files/video/5.mp4");
                        intent4.putExtra("image",image[position]);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5=new Intent(getActivity(), VideoActivity.class);
                        intent5.putExtra("title",title[position]);
                        intent5.putExtra("url","http://www.tbqjx.com/files/video/4.mp4");
                        intent5.putExtra("image",image[position]);
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6=new Intent(getActivity(), VideoActivity.class);
                        intent6.putExtra("title",title[position]);
                        intent6.putExtra("url","http://www.tbqjx.com/files/video/3.mp4");
                        intent6.putExtra("image",image[position]);
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7=new Intent(getActivity(), VideoActivity.class);
                        intent7.putExtra("title",title[position]);
                        intent7.putExtra("url","http://www.tbqjx.com/files/video/2.mp4");
                        intent7.putExtra("image",image[position]);
                        startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8=new Intent(getActivity(), VideoActivity.class);
                        intent8.putExtra("title",title[position]);
                        intent8.putExtra("url","http://www.tbqjx.com/files/video/1.mp4");
                        intent8.putExtra("image",image[position]);
                        startActivity(intent8);
                        break;
                    default:
                        break;

                }
            }
        });
        MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), R.layout.item,
                image, title);
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
