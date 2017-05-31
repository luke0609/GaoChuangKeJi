package com.jiaokaokeji.gaochuangkeji.book.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.util.ConstantUtil;


/**
 * 错题详情
 * 
 * @author 金钟焕
 */
public class MymistakesDetailActivity extends Activity {

	private TextView questionTypeTV;
	private TextView questionNameTV;
	private LinearLayout layoutA;
	private LinearLayout layoutB;
	private LinearLayout layoutC;
	private LinearLayout layoutD;
	private LinearLayout layoutE;
	private ImageView ivA;
	private ImageView ivB;
	private ImageView ivC;
	private ImageView ivD;
	private ImageView ivE;
	private TextView tvA;
	private TextView tvB;
	private TextView tvC;
	private TextView tvD;
	private TextView tvE;
	private ImageView ivA_;
	private ImageView ivB_;
	private ImageView ivC_;
	private ImageView ivD_;
	//private ImageView ivE_;
	private LinearLayout wrongLayout;
	private TextView explaindetailTv;
	
	private String questionName="";
	private String option_type="";
	private String questionAnswer="";
	private String questionSelect="";
	private String isRight="";
	private String Analysis="";
	private String optionA="";
	private String optionB="";
	private String optionC="";
	private String optionD="";
	private String url="";
	private ImageView iv;
	private ImageView iv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_error_question_detail);
		iv = ((ImageView) findViewById(R.id.iv));
		iv1 = ((ImageView) findViewById(R.id.iv1));
		iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		initView();
	}
	
	private void initView(){
		questionName=getIntent().getStringExtra("questionName");
		option_type=getIntent().getStringExtra("option_type");
		questionAnswer=getIntent().getStringExtra("questionAnswer");
		questionSelect=getIntent().getStringExtra("questionSelect");
		isRight=getIntent().getStringExtra("isRight");
		Analysis=getIntent().getStringExtra("Analysis");
		optionA=getIntent().getStringExtra("optionA");
		optionB=getIntent().getStringExtra("optionB");
		optionC=getIntent().getStringExtra("optionC");
		optionD=getIntent().getStringExtra("optionD");
		url=getIntent().getStringExtra("url");
		questionTypeTV=(TextView) findViewById(R.id.activity_prepare_test_no);
		questionNameTV=(TextView) findViewById(R.id.activity_prepare_test_question);
		layoutA=(LinearLayout) findViewById(R.id.activity_prepare_test_layout_a);
		layoutB=(LinearLayout) findViewById(R.id.activity_prepare_test_layout_b);
		layoutC=(LinearLayout) findViewById(R.id.activity_prepare_test_layout_c);
		layoutD=(LinearLayout) findViewById(R.id.activity_prepare_test_layout_d);
		//layoutE=(LinearLayout) findViewById(R.id.activity_prepare_test_layout_e);
		ivA=(ImageView) findViewById(R.id.vote_submit_select_image_a);
		ivB=(ImageView) findViewById(R.id.vote_submit_select_image_b);
		ivC=(ImageView) findViewById(R.id.vote_submit_select_image_c);
		ivD=(ImageView) findViewById(R.id.vote_submit_select_image_d);
		//ivE=(ImageView) findViewById(R.id.vote_submit_select_image_e);
		tvA=(TextView) findViewById(R.id.vote_submit_select_text_a);
		tvB=(TextView) findViewById(R.id.vote_submit_select_text_b);
		tvC=(TextView) findViewById(R.id.vote_submit_select_text_c);
		tvD=(TextView) findViewById(R.id.vote_submit_select_text_d);
		//tvE=(TextView) findViewById(R.id.vote_submit_select_text_e);
		ivA_=(ImageView) findViewById(R.id.vote_submit_select_image_a_);
		ivB_=(ImageView) findViewById(R.id.vote_submit_select_image_b_);
		ivC_=(ImageView) findViewById(R.id.vote_submit_select_image_c_);
		ivD_=(ImageView) findViewById(R.id.vote_submit_select_image_d_);
		//ivE_=(ImageView) findViewById(R.id.vote_submit_select_image_e_);
		wrongLayout=(LinearLayout) findViewById(R.id.activity_prepare_test_wrongLayout);
		explaindetailTv=(TextView) findViewById(R.id.activity_prepare_test_explaindetail);
		
			questionNameTV.setText(""+questionName);
			
			if(optionA.equals("")){
				layoutA.setVisibility(View.GONE);
			}if(optionB.equals("")){
				layoutB.setVisibility(View.GONE);
			}if(optionC.equals("")){
				layoutC.setVisibility(View.GONE);
			}if(optionD.equals("")){
				layoutD.setVisibility(View.GONE);
//			}if(optionE.equals("")){
//				layoutE.setVisibility(View.GONE);
			}
			
				//文字题目
				ivA_.setVisibility(View.GONE);
				ivB_.setVisibility(View.GONE);
				ivC_.setVisibility(View.GONE);
				ivD_.setVisibility(View.GONE);
				//ivE_.setVisibility(View.GONE);
				tvA.setVisibility(View.VISIBLE);
				tvB.setVisibility(View.VISIBLE);
				tvC.setVisibility(View.VISIBLE);
				tvD.setVisibility(View.VISIBLE);
				//tvE.setVisibility(View.VISIBLE);
				tvA.setText("A." + optionA);
				tvB.setText("B." + optionB);
				tvC.setText("C." + optionC);
				tvD.setText("D." + optionD);
				//tvE.setText("E." + optionE);
		if(!url.equals("")){
			iv1.setVisibility(View.VISIBLE);
			Glide.with(MymistakesDetailActivity.this).load(url).into(iv1);
		}else {
			iv1.setVisibility(View.GONE);
		}
		cuowu();
	}
public void cuowu(){
	if(option_type.equals("0")){
		questionTypeTV.setText("(单选题)");
		//显示正确选项

		if(questionAnswer.contains("1")){
			ivA.setImageResource(R.drawable.ic_practice_test_right);
			tvA.setTextColor(Color.parseColor("#61bc31"));
		}else if(questionAnswer.contains("2")){
			ivB.setImageResource(R.drawable.ic_practice_test_right);
			tvB.setTextColor(Color.parseColor("#61bc31"));
		}else if(questionAnswer.contains("3")){
			ivC.setImageResource(R.drawable.ic_practice_test_right);
			tvC.setTextColor(Color.parseColor("#61bc31"));
		}else if(questionAnswer.contains("4")){
			ivD.setImageResource(R.drawable.ic_practice_test_right);
			tvD.setTextColor(Color.parseColor("#61bc31"));
		}

		if(questionSelect.contains("1")){
			ivA.setImageResource(R.drawable.ic_practice_test_wrong);
			tvA.setTextColor(Color.parseColor("#d53235"));
		}else if(questionSelect.contains("2")){
			ivB.setImageResource(R.drawable.ic_practice_test_wrong);
			tvB.setTextColor(Color.parseColor("#d53235"));
		}else if(questionSelect.contains("3")){
			ivC.setImageResource(R.drawable.ic_practice_test_wrong);
			tvC.setTextColor(Color.parseColor("#d53235"));
		}else if(questionSelect.contains("4")){
			ivD.setImageResource(R.drawable.ic_practice_test_wrong);
			tvD.setTextColor(Color.parseColor("#d53235"));
		}

	}else if(option_type.equals("1")){
		questionTypeTV.setText("(多选题)");
		//显示正确选项
		if(questionAnswer.contains("1")){
			ivA.setImageResource(R.drawable.ic_practice_test_right);
			tvA.setTextColor(Color.parseColor("#61bc31"));
		}if(questionAnswer.contains("2")){
			ivB.setImageResource(R.drawable.ic_practice_test_right);
			tvB.setTextColor(Color.parseColor("#61bc31"));
		}if(questionAnswer.contains("3")){
			ivC.setImageResource(R.drawable.ic_practice_test_right);
			tvC.setTextColor(Color.parseColor("#61bc31"));
		}if(questionAnswer.contains("4")){
			ivD.setImageResource(R.drawable.ic_practice_test_right);
			tvD.setTextColor(Color.parseColor("#61bc31"));
		}

		if(questionSelect.contains("1")){
			ivA.setImageResource(R.drawable.ic_practice_test_wrong);
			tvA.setTextColor(Color.parseColor("#d53235"));
		}if(questionSelect.contains("2")){
			ivB.setImageResource(R.drawable.ic_practice_test_wrong);
			tvB.setTextColor(Color.parseColor("#d53235"));
		}if(questionSelect.contains("3")){
			ivC.setImageResource(R.drawable.ic_practice_test_wrong);
			tvC.setTextColor(Color.parseColor("#d53235"));
		}if(questionSelect.contains("4")){
			ivD.setImageResource(R.drawable.ic_practice_test_wrong);
			tvD.setTextColor(Color.parseColor("#d53235"));
		}
	}else if(option_type.equals("2")){
		questionTypeTV.setText("(判断题)");
		//显示正确选项
		if(questionAnswer.contains("1")){
			ivA.setImageResource(R.drawable.ic_practice_test_right);
			tvA.setTextColor(Color.parseColor("#61bc31"));
		}else if(questionAnswer.contains("2")){
			ivB.setImageResource(R.drawable.ic_practice_test_right);
			tvB.setTextColor(Color.parseColor("#61bc31"));
		}else if(questionAnswer.contains("3")){
			ivC.setImageResource(R.drawable.ic_practice_test_right);
			tvC.setTextColor(Color.parseColor("#61bc31"));
		}else if(questionAnswer.contains("4")){
			ivD.setImageResource(R.drawable.ic_practice_test_right);
			tvD.setTextColor(Color.parseColor("#61bc31"));
		}
		if(questionSelect.contains("1")){
			ivA.setImageResource(R.drawable.ic_practice_test_wrong);
			tvA.setTextColor(Color.parseColor("#d53235"));
		}else if(questionSelect.contains("2")){
			ivB.setImageResource(R.drawable.ic_practice_test_wrong);
			tvB.setTextColor(Color.parseColor("#d53235"));
		}else if(questionSelect.contains("3")){
			ivC.setImageResource(R.drawable.ic_practice_test_wrong);
			tvC.setTextColor(Color.parseColor("#d53235"));
		}else if(questionSelect.contains("4")){
			ivD.setImageResource(R.drawable.ic_practice_test_wrong);
			tvD.setTextColor(Color.parseColor("#d53235"));
		}

		layoutC.setVisibility(View.GONE);
		layoutD.setVisibility(View.GONE);
	}

	if(isRight.equals(ConstantUtil.isError)){
		wrongLayout.setVisibility(View.VISIBLE);
		explaindetailTv.setText(""+Analysis);
	}else{
		wrongLayout.setVisibility(View.GONE);
	}
}
}
