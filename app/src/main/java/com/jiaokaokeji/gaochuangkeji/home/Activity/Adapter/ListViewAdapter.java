package com.jiaokaokeji.gaochuangkeji.home.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.silencedut.expandablelayout.ExpandableLayout;

import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * Created by SilenceDut on 16/6/7.
 */
public class ListViewAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<Map<String,Object>> datas;
    private HashSet<Integer> mExpandedPositionSet = new HashSet<>();
   public ListViewAdapter(List<Map<String,Object>> datas,Context context) {
        this.datas = datas;
        mLayoutInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }


    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

//        ExpandableLayout expandableLayout;
        final ViewHolder holder;
        if(convertView==null) {
            holder = new ViewHolder();
            convertView=mLayoutInflater.inflate( R.layout.item_jinx,parent,false);
            holder.expandableLayout = (ExpandableLayout) convertView.findViewById(R.id.expandable_layout);
            holder.summoner= ((TextView) convertView.findViewById(R.id.summoner));
            holder.summoner_story_tv= ((TextView) convertView.findViewById(R.id.summoner_story_tv));
            holder.expandableLayout.setExpandWithParentScroll(true);
            convertView.setTag(holder);
        }else {
            holder =(ViewHolder) convertView.getTag();
        }

        if(holder.expandableLayout !=null) {
            holder.expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position);
                }
            });
        }
        holder.summoner.setText(datas.get(position).get("problem").toString());
        holder.summoner_story_tv.setText(datas.get(position).get("answer").toString());
        holder.expandableLayout.setExpand(mExpandedPositionSet.contains(position));
        return convertView;
    }
    static class ViewHolder {
        ExpandableLayout expandableLayout;
        TextView summoner_story_tv;
        TextView summoner;
    }


    private void registerExpand(int position) {
        if (mExpandedPositionSet.contains(position)) {
            removeExpand(position);
        }else {
            addExpand(position);
        }
    }

    private void removeExpand(int position) {
        mExpandedPositionSet.remove(position);
    }

    private void addExpand(int position) {
        mExpandedPositionSet.add(position);
    }
}
