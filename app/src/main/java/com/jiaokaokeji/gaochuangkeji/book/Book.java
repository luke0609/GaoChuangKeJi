package com.jiaokaokeji.gaochuangkeji.book;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book1_fragement;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book2_fragement;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book3_fragement;
import com.jiaokaokeji.gaochuangkeji.book.fragement.Book4_fragement;
import com.jiaokaokeji.gaochuangkeji.book.prjo.MyAnimations;
import com.jiaokaokeji.gaochuangkeji.book.prjo.PullScrollView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Book extends Fragment {
    View view1;
    @InjectView(R.id.button_photo)
    TextView buttonPhoto;
    @InjectView(R.id.button_photo1)
    TextView buttonPhoto1;
    @InjectView(R.id.button_people)
    TextView buttonPeople;
    private int unSelectTextColor;
    private ScrollIndicatorView tab;
    private ViewPager viewpage;
    private String[] names = {"科目一", "科目二", "科目三", "科目四"};
    private LayoutInflater inflate;
    List<Fragment> list = new ArrayList<Fragment>();
    private IndicatorViewPager indicatorViewPager;
    private ImageView iv;
    private PullScrollView pullsc;
    int width;
    private boolean isShowing;
    private RelativeLayout buttons_wrapper_layout;
    private TextView buttons_show_hide_button;
    private RelativeLayout buttons_show_hide_button_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1 = inflater.inflate(R.layout.fragment_book, container, false);
        iv = ((ImageView) view1.findViewById(R.id.iv));
        tab = ((ScrollIndicatorView) view1.findViewById(R.id.moretab_indicator));
        viewpage = ((ViewPager) view1.findViewById(R.id.moretab_viewPager));
        pullsc = ((PullScrollView) view1.findViewById(R.id.scroll_view));
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();

        ViewGroup.LayoutParams lp = iv.getLayoutParams();
        lp.width = width;
        lp.height = width / 3;
        iv.setLayoutParams(lp);
        buttons_wrapper_layout = (RelativeLayout) view1.findViewById(R.id.buttons_wrapper_layout);
        buttons_show_hide_button_layout = (RelativeLayout) view1.findViewById(R.id.buttons_show_hide_button_layout);
        buttons_show_hide_button = (TextView) view1.findViewById(R.id.buttons_show_hide_button);
        for (int i = 0; i < buttons_wrapper_layout.getChildCount(); i++) {
            buttons_wrapper_layout.getChildAt(i).setOnClickListener(new OnClickImageButton());
        }
        buttons_show_hide_button_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons_wrapper_layout.setVisibility(View.VISIBLE);
                Animations();
            }

        });

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
        unSelectTextColor = R.color.commo_text_color;
        tab.setOnTransitionListener(new OnTransitionTextListener().setColor(Color.parseColor("#4EAFAB"), unSelectTextColor));
        tab.setScrollBar(new ColorBar(getActivity(), Color.parseColor("#4EAFAB"), 4));
        viewpage.setOffscreenPageLimit(4);
        indicatorViewPager = new IndicatorViewPager(tab, viewpage);
        inflate = LayoutInflater.from(getActivity());
        indicatorViewPager.setAdapter(new MyAdapter(getFragmentManager()));
        indicatorViewPager.setPageOffscreenLimit(0);

        list.add(new Book1_fragement());
        list.add(new Book2_fragement());
        list.add(new Book3_fragement());
        list.add(new Book4_fragement());
        ButterKnife.inject(this, view1);
        return view1;
    }

    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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

    public class OnClickImageButton implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            switch (arg0.getId()) {
                case R.id.button_photo:
                    buttons_show_hide_button.setText(buttonPhoto.getText());
                    Animations();
                    buttons_wrapper_layout.setVisibility(View.GONE);
                    break;
                case R.id.button_people:
                    buttons_show_hide_button.setText(buttonPeople.getText());
                    Animations();
                    buttons_wrapper_layout.setVisibility(View.GONE);
                    break;
                case R.id.button_photo1:
                    buttons_show_hide_button.setText(buttonPhoto1.getText());
                    Animations();
                    buttons_wrapper_layout.setVisibility(View.GONE);
                    break;
            }
        }
    }
    public void Animations(){
        if (!isShowing) {
            MyAnimations.startAnimationsIn(buttons_wrapper_layout, 300);
            buttons_show_hide_button
                    .startAnimation(MyAnimations.getRotateAnimation(0,
                            -270, 300));
        } else {
            MyAnimations
                    .startAnimationsOut(buttons_wrapper_layout, 300);
            buttons_show_hide_button
                    .startAnimation(MyAnimations.getRotateAnimation(
                            -270, 0, 300));
        }
        isShowing = !isShowing;

    }
}
