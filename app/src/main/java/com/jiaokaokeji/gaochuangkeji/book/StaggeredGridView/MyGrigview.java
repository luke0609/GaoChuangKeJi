package com.jiaokaokeji.gaochuangkeji.book.StaggeredGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/12/16.
 */

public class MyGrigview  extends GridView{
    public MyGrigview(Context context) {
        super(context);
    }

    public MyGrigview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGrigview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,expandSpec);
    }
}
