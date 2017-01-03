package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jiaokaokeji.gaochuangkeji.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ApplyActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.rg_type)
    RadioGroup rgType;
    @InjectView(R.id.spinner)
    Spinner spinner;
    @InjectView(R.id.rl_p1)
    RelativeLayout rlP1;
    @InjectView(R.id.et_name)
    MaterialEditText etName;
    @InjectView(R.id.et_phone)
    MaterialEditText etPhone;
    @InjectView(R.id.et_address)
    MaterialEditText etAddress;
    @InjectView(R.id.et_tips)
    MaterialEditText etTips;
    @InjectView(R.id.rl_p2)
    RelativeLayout rlP2;
    @InjectView(R.id.tv_goods_kefu)
    TextView tvGoodsKefu;
    @InjectView(R.id.btn_question)
    Button btnQuestion;
    @InjectView(R.id.btn_next)
    Button btnNext;
    @InjectView(R.id.rb_1)
    RadioButton rb1;
    @InjectView(R.id.rb_2)
    RadioButton rb2;
    @InjectView(R.id.rb_3)
    RadioButton rb3;
    @InjectView(R.id.tv_menu)
    TextView tvMenu;
    @InjectView(R.id.rb_4)
    RadioButton rb4;
    @InjectView(R.id.rb_5)
    RadioButton rb5;
    @InjectView(R.id.rg_se)
    RadioGroup rgSe;

    private boolean pageFlag = true;

    private int type=0;
    private int type2=0;
    private int service=0;

    //报名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        ButterKnife.inject(this);
        initview();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义颜色
        tintManager.setTintColor(Color.parseColor("#56ABE4"));
    }


    private void initview() {
        rb1.setChecked(true);
        rb4.setChecked(true);
        final ArrayAdapter<CharSequence> adapterspinner1 = ArrayAdapter
                .createFromResource(this, R.array.Tpye,
                        android.R.layout.simple_spinner_item);
        adapterspinner1.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapterspinner1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   Toast.makeText(ApplyActivity.this, position + "", Toast.LENGTH_SHORT).show();
                //学生 3030  团体 3280 普通 3380  贵宾A 5530 贵宾B 8830
                switch (position) {
                    case 0:
                        tvGoodsKefu.setText("￥3030");
                        type2=0;
                        break;
                    case 1:
                        tvGoodsKefu.setText("￥3280");
                        type2=1;
                        break;
                    case 2:
                        tvGoodsKefu.setText("￥3380");
                        type2=2;
                        break;
                    case 3:
                        tvGoodsKefu.setText("￥5530");
                        type2=3;
                        break;
                    case 4:
                        tvGoodsKefu.setText("￥8830");
                        type2=4;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

               // Toast.makeText(ApplyActivity.this, checkedId+"", Toast.LENGTH_SHORT).show();
                switch (checkedId){
                    case R.id.rb_1:
                        type=0;
                        break;
                    case R.id.rb_2:
                        type=1;
                        break;
                    case R.id.rb_3:
                        type=2;
                        break;

                }
            }
        });
        rgSe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_4:
                        service=0;
                        break;
                    case R.id.rb_5:
                        service=1;
                        break;

                }

            }
        });

    }


    @OnClick({R.id.iv_back, R.id.btn_question, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if(pageFlag){
                    finish();
                }else {
                    rlP2.setVisibility(View.GONE);
                    rlP1.setVisibility(View.VISIBLE);
                    pageFlag=true;
                }
                break;
            case R.id.btn_question:
                break;
            case R.id.btn_next:
               // Toast.makeText(this,type+"-"+type2+"-"+service +"-", Toast.LENGTH_SHORT).show();
                if(pageFlag){
                    rlP1.setVisibility(View.GONE);
                    rlP2.setVisibility(View.VISIBLE);
                    pageFlag=false;
                    btnNext.setText("提交报名");
                }else {

                    Toast.makeText(this, "提交报名信息成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);




    }
}
