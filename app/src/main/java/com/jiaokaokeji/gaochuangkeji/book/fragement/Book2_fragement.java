package com.jiaokaokeji.gaochuangkeji.book.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.adapt.ImageAdapter;
import com.jiaokaokeji.gaochuangkeji.book.prjo.Product;
import com.jiaokaokeji.gaochuangkeji.book.prjo.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Book2_fragement extends Fragment {
    View view1;
    private ImageAdapter adapter;
    private XRecyclerView recyclerView;
    private List<Product> data;
    private ArrayList<String> titles=new ArrayList<>();
    int img[]=new int[]{R.drawable.detail,R.drawable.detail,R.drawable.detail,R.drawable.detail,
            R.drawable.detail,R.drawable.detail,R.drawable.detail,R.drawable.detail};
    String title[]=new String[]{"倒车入库","倒车入库","倒车入库","倒车入库","倒车入库","倒车入库",
            "倒车入库","倒车入库"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book2_fragement,null);
        recyclerView = (XRecyclerView)view1.findViewById(R.id.recyclerView);
        initView();
        return view1;
    }
    private void initView() {
        data=new ArrayList<>();
        for (int i=0;i<img.length;i++){
            Product product=new Product(img[i],title[i]);
            data.add(product);
        }
        adapter = new ImageAdapter(data, getActivity(), R.layout.item);
        adapter.notifyDataSetChanged();
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.addItemDecoration(decoration);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }
}
