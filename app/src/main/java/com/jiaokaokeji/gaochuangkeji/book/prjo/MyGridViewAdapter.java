package com.jiaokaokeji.gaochuangkeji.book.prjo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private static final String TAG = "MyGridViewAdapter";
    private Context context;
    private int layoutId;
    private String listData[];
    private String title[];


    public MyGridViewAdapter(Context context, int layoutId,
                             String[] listData,String title[]) {
        this.context = context;
        this.layoutId = layoutId;
        this.listData = listData;
        this.title=title;
    }

    @Override
    public int getCount() {
        return listData.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listData[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(context);
            convertView = layoutInflator.inflate(R.layout.item,
                    null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        //mLoader.DisplayImage(getItem(position), holder.imageView);
        Glide.with(context).load(listData[position]).fitCenter().into(holder.imageView);
        holder.tv.setText(title[position].toString());
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView tv;
    }
}
