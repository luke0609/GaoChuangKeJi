package com.jiaokaokeji.gaochuangkeji.home.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        ExpandableLayout expandableLayout;
        TextView summoner;
        TextView label;
        TextView summoner_story_tv;
        if(convertView==null) {
            convertView=mLayoutInflater.inflate( R.layout.item_jinx,parent,false);
            expandableLayout = (ExpandableLayout) convertView.findViewById(R.id.expandable_layout);
            summoner= ((TextView) convertView.findViewById(R.id.summoner));
            label= ((TextView) convertView.findViewById(R.id.label));
            summoner_story_tv= ((TextView) convertView.findViewById(R.id.summoner_story_tv));
            expandableLayout.setExpandWithParentScroll(false);
            convertView.setTag(expandableLayout);
        }else {
            expandableLayout =(ExpandableLayout) convertView.getTag();
        }

        if(expandableLayout !=null) {
            expandableLayout.setOnExpandListener(new ExpandableLayout.OnExpandListener() {
                @Override
                public void onExpand(boolean expanded) {
                    registerExpand(position);
                }
            });
        }
        expandableLayout.setExpand(mExpandedPositionSet.contains(position));
        return convertView;
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
