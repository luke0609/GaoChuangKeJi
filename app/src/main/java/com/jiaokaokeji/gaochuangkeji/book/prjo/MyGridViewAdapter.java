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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private static final String TAG = "MyGridViewAdapter";
    private Context context;
    ArrayList<Shipin.DataBean> shipinArrayList;
    private int layoutId;


    public MyGridViewAdapter(Context context, int layoutId,
                             ArrayList<Shipin.DataBean> shipinArrayList) {
        this.context = context;
        this.layoutId = layoutId;
        this.shipinArrayList=shipinArrayList;
    }

    @Override
    public int getCount() {
        return shipinArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return shipinArrayList.get(position);
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
        Picasso.with(context).load(shipinArrayList.get(position).getVideos_imge()).into(holder.imageView);
        holder.tv.setText(shipinArrayList.get(position).getVideos_title());
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView tv;
    }
}
