package com.jiaokaokeji.gaochuangkeji.book;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book1_fragement;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book2_fragement;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book3_fragement;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book4_fragement;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

public class Book extends Fragment {
    View view1;
    private int unSelectTextColor;
    private ScrollIndicatorView tab;
    private ViewPager viewpage;
    private String[] names = {"科目一", "科目二", "科目三", "科目四"};
    private LayoutInflater inflate;
    List<Fragment> list = new ArrayList<Fragment>();
    private IndicatorViewPager indicatorViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book, container, false);
        tab = ((ScrollIndicatorView) view1.findViewById(R.id.moretab_indicator));
        viewpage = ((ViewPager) view1.findViewById(R.id.moretab_viewPager));
        tab.setScrollBar(new DrawableBar(getActivity(), R.drawable.round_border_green_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - dipToPix(12);
            }

            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(12);
            }
        });
        unSelectTextColor =R.color.white;
        tab.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.WHITE, unSelectTextColor));

        viewpage.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(tab, viewpage);
        inflate = LayoutInflater.from(getActivity());
        indicatorViewPager.setAdapter(new MyAdapter(getFragmentManager()));

        list.add(new Book1_fragement());
        list.add(new Book2_fragement());
        list.add(new Book3_fragement());
        list.add(new Book4_fragement());
        System.out.println(list.size()+"////////////");
        return view1;
    }

    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            convertView = inflate.inflate(R.layout.tab_fragment, container, false);
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment1 = list.get(position);
            return fragment1;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }
    }
}
