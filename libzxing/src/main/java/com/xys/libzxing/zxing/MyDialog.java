package com.xys.libzxing.zxing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xys.libzxing.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;

/**
 * Created by Jingsheng Liang on 2017/1/18.
 */

public class MyDialog extends Dialog{

   public MyDialog(CaptureActivity context, int width, int height, View layout,int style) {
        super(context, style);
        setContentView(layout);
        Window window=getWindow();
        WindowManager.LayoutParams params=window.getAttributes();
        params.gravity=Gravity.CENTER;
        window.setAttributes(params);
    }


}