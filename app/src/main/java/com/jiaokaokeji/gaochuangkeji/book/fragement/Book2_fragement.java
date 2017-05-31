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
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiaokaokeji.gaochuangkeji.Netutil;
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
import com.jiaokaokeji.gaochuangkeji.book.prjo.Shipin;
import com.jiaokaokeji.gaochuangkeji.my.Activity.PersonalActivity;
import com.jiaokaokeji.gaochuangkeji.my.prjo.Person;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Book2_fragement extends Fragment {
    View view1;
    ArrayList<Shipin.DataBean> shipinArrayList = new ArrayList<>();
    private TextView tv1;
    private MyScollview myScollview;
    private View v1;
    private CircleButton btn_2;
    MyGrigview gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book2_fragement, null);
        btn_2 = ((CircleButton) view1.findViewById(R.id.btn_2));
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), ReversingActivity.class);
                startActivity(intent2);
            }
        });
        getShipin();
        gridView = (MyGrigview) view1.findViewById(R.id.staggeredGridView1);
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
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), VideoActivity.class);
                        intent1.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent1.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent1.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), VideoActivity.class);
                        intent2.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent2.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent2.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getActivity(), VideoActivity.class);
                        intent3.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent3.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent3.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(getActivity(), VideoActivity.class);
                        intent4.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent4.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent4.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(getActivity(), VideoActivity.class);
                        intent5.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent5.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent5.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent5);
                        break;
                    case 5:
                        Intent intent6 = new Intent(getActivity(), VideoActivity.class);
                        intent6.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent6.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent6.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent6);
                        break;
                    case 6:
                        Intent intent7 = new Intent(getActivity(), VideoActivity.class);
                        intent7.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent7.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent7.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent7);
                        break;
                    case 7:
                        Intent intent8 = new Intent(getActivity(), VideoActivity.class);
                        intent8.putExtra("title", shipinArrayList.get(position).getVideos_title());
                        intent8.putExtra("url", shipinArrayList.get(position).getVideos_url());
                        intent8.putExtra("image",shipinArrayList.get(position).getVideos_imge());
                        startActivity(intent8);
                        break;
                    default:
                        break;

                }
            }
        });
        ButterKnife.inject(this, view1);
        return view1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6})
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

    public void getShipin() {
        RequestParams params = new RequestParams(Netutil.url + "/index.php/subjects/videos/selectvideos");
        params.addBodyParameter("key", "tbjxappgaochuang");
        params.addBodyParameter("videos_type", "2");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Shipin shipin = gson.fromJson(result, Shipin.class);
                shipinArrayList.addAll(shipin.getData());
                MyGridViewAdapter adapter = new MyGridViewAdapter(getActivity(), R.layout.item,shipinArrayList);
                gridView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast toast = Toast.makeText(getActivity(), "请检查网络连接", Toast.LENGTH_LONG);
                toast.show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
