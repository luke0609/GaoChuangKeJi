package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import com.jiaokaokeji.gaochuangkeji.R;

public class ApplyActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;
    private Spinner spinner;

    //报名
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        initview();

    }


    private void initview() {
        back = ((ImageView) findViewById(R.id.iv_back));
        spinner = ((Spinner) findViewById(R.id.spinner));

        final ArrayAdapter<CharSequence> adapterspinner1 = ArrayAdapter
                .createFromResource(this, R.array.Tpye,
                        android.R.layout.simple_spinner_item);
        adapterspinner1.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapterspinner1);

    }

    @Override
    public void onClick(View v) {

    }
}
