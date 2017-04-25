package com.jiaokaokeji.gaochuangkeji.book.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.book.Activity.Mymistakes1Activity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.Radom1Activity;
import com.jiaokaokeji.gaochuangkeji.book.Activity.SimulationTest1Activity;
import com.jiaokaokeji.gaochuangkeji.book.StaggeredGridView.VoteSubmitViewPager;
import com.jiaokaokeji.gaochuangkeji.book.database.DBManager;
import com.jiaokaokeji.gaochuangkeji.book.prjo.AnSwerInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.ErrorQuestionInfo;
import com.jiaokaokeji.gaochuangkeji.book.prjo.SaveQuestionInfo;
import com.jiaokaokeji.gaochuangkeji.book.util.ConstantUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExaminationSubmitAdapter5 extends PagerAdapter {
	SimulationTest1Activity mContext;
	// 传递过来的页面view的集合
	List<View> viewItems;
	// 每个item的页面view
	View convertView;
	// 传递过来的所有数据
	List<AnSwerInfo.DataBean> dataItems;

	String imgServerUrl="";
	VoteSubmitViewPager viewPager;
	private Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	private Map<Integer, Boolean> mapClick = new HashMap<Integer, Boolean>();
	private Map<Integer, String> mapMultiSelect = new HashMap<Integer, String>();

	boolean isClick=false;

	boolean isNext = false;

	StringBuffer answer=new StringBuffer();
	StringBuffer answerLast=new StringBuffer();
	StringBuffer answer1=new StringBuffer();

	DBManager dbManager;

	String isCorrect= ConstantUtil.isCorrect;//1对，0错

	int errortopicNum=0;

	String resultA="";
	String resultB="";
	String resultC="";
	String resultD="";
	String resultE="";
	int isA=2;
	int isB=2;
	int isC=2;
	int isD=2;
	int xuana;
    int xuanb;
    int xuanc;
    int xuand;
	List<String> a=new ArrayList<>();
	boolean isContains;
    private LinearLayout llqueren;

    public ExaminationSubmitAdapter5(SimulationTest1Activity context, List<View> viewItems, List<AnSwerInfo.DataBean> dataItems, String imgServerUrl) {
		this.mContext = context;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
		this.imgServerUrl = imgServerUrl;
		dbManager = new DBManager(context);
		dbManager.openDB();
	}

	public long getItemId(int position) {
		return position;
	}
	@Override
	public int getCount() {
		return 50;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View contentView = (View) object;
		container.removeView(contentView);
		this.viewItems.add(contentView);
	}
	@Override
	public Object instantiateItem(ViewGroup container,final int position) {
		final ViewHolder holder = new ViewHolder();
		convertView = viewItems.get(position);
		convertView.setTag(position);

		for(int i=0;i<dataItems.size();i++) {
			a.add(dataItems.get(i).getId());
	}
		isContains= Arrays.asList(a).contains(dataItems.get(position).getId());
		holder.questionType = (TextView) convertView.findViewById(R.id.activity_prepare_test_no);
		holder.question = (TextView) convertView.findViewById(R.id.activity_prepare_test_question);
		holder.previousBtn = (LinearLayout) convertView.findViewById(R.id.activity_prepare_test_upLayout);
		holder.nextBtn = (LinearLayout) convertView.findViewById(R.id.activity_prepare_test_nextLayout);
		holder.nextText = (TextView) convertView.findViewById(R.id.menu_bottom_nextTV);
		holder.iv = ((ImageView) convertView.findViewById(R.id.iv));
		holder.tvdaan = ((TextView) convertView.findViewById(R.id.tvdaan));
        holder.tx = ((TextView) convertView.findViewById(R.id.tx));
		holder.btqr = ((Button) convertView.findViewById(R.id.btqr));
        holder.errorBtn =(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_errorLayout);
		holder.nextImage = (ImageView) convertView.findViewById(R.id.menu_bottom_nextIV);
		holder.wrongLayout = (LinearLayout) convertView.findViewById(R.id.activity_prepare_test_wrongLayout);
		holder.explaindetailTv = (TextView) convertView.findViewById(R.id.activity_prepare_test_explaindetail);
		holder.layoutA=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_a);
		holder.layoutB=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_b);
		holder.layoutC=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_c);
		holder.layoutD=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_d);
		//holder.layoutE=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_e);
		holder.ivA=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_a);
		holder.ivB=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_b);
		holder.ivC=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_c);
		holder.ivD=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_d);
		//holder.ivE=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_e);
		holder.tvA=(TextView) convertView.findViewById(R.id.vote_submit_select_text_a);
		holder.tvB=(TextView) convertView.findViewById(R.id.vote_submit_select_text_b);
		holder.tvC=(TextView) convertView.findViewById(R.id.vote_submit_select_text_c);
		holder.tvD=(TextView) convertView.findViewById(R.id.vote_submit_select_text_d);
		//holder.tvE=(TextView) convertView.findViewById(R.id.vote_submit_select_text_e);
		holder.ivA_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_a_);
		holder.ivB_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_b_);
		holder.ivC_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_c_);
		holder.ivD_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_d_);
		//holder.ivE_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_e_);

		
		holder.errorBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(mContext,Mymistakes1Activity.class);
				mContext.startActivity(intent);
			}
		});
		
		if(dataItems.get(position).getItem1().equals("")){
			holder.layoutA.setVisibility(View.GONE);
		}if(dataItems.get(position).getItem2().equals("")){
			holder.layoutB.setVisibility(View.GONE);
		}if(dataItems.get(position).getItem3().equals("")){
			holder.layoutC.setVisibility(View.GONE);
		}if(dataItems.get(position).getItem4().equals("")){
			holder.layoutD.setVisibility(View.GONE);
		}
		
		//判断是否文字图片题目
			//文字题目
			holder.ivA_.setVisibility(View.GONE);
			holder.ivB_.setVisibility(View.GONE);
			holder.ivC_.setVisibility(View.GONE);
			holder.ivD_.setVisibility(View.GONE);
			//holder.ivE_.setVisibility(View.GONE);
			holder.tvA.setVisibility(View.VISIBLE);
			holder.tvB.setVisibility(View.VISIBLE);
			holder.tvC.setVisibility(View.VISIBLE);
			holder.tvD.setVisibility(View.VISIBLE);
			//holder.tvE.setVisibility(View.VISIBLE);
			holder.tvA.setText("A." + dataItems.get(position).getItem1());
			holder.tvB.setText("B." + dataItems.get(position).getItem2());
			holder.tvC.setText("C." + dataItems.get(position).getItem3());
			holder.tvD.setText("D." + dataItems.get(position).getItem4());

			
		//判断题型
		if(((!dataItems.get(position).getItem3().equals("")&&Integer.valueOf(dataItems.get(position).getAnswer())<7))){
			//单选题
			holder.question.setText("(单选题)"+(position+1)+"."+dataItems.get(position).getQuestion());
			if (!dataItems.get(position).getUrl().equals("")){
				holder.iv.setVisibility(View.VISIBLE);
				Glide.with(mContext).load(dataItems.get(position).getUrl()).into(holder.iv);
			}else {
				holder.iv.setVisibility(View.GONE);
			}
			xuanxiang(holder,position);
		}else if(dataItems.get(position).getAnswer().equals("7")||dataItems.get(position).getAnswer().equals("8")||
				dataItems.get(position).getAnswer().equals("9")||dataItems.get(position).getAnswer().equals("10")||
				dataItems.get(position).getAnswer().equals("11")||dataItems.get(position).getAnswer().equals("12")||
				dataItems.get(position).getAnswer().equals("13")||dataItems.get(position).getAnswer().equals("14")||
				dataItems.get(position).getAnswer().equals("15")||dataItems.get(position).getAnswer().equals("16")||dataItems.get(position).getAnswer().equals("17")){
			//多选题
			holder.question.setText("(多选题)"+(position+1)+"."+dataItems.get(position).getQuestion());
            holder.btqr.setVisibility(View.VISIBLE);
            if (!dataItems.get(position).getUrl().equals("")){
				holder.iv.setVisibility(View.VISIBLE);
				Glide.with(mContext).load(dataItems.get(position).getUrl()).into(holder.iv);
			}else {
				holder.iv.setVisibility(View.GONE);
			}
			holder.layoutA.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					xuana++;
					if(xuana%2==1){
						holder.ivA.setImageResource(R.drawable.selected);
						holder.tvA.setTextColor(Color.parseColor("#61bc31"));
						isA=1;
					}else {
						holder.ivA.setImageResource(R.drawable.ic_practice_test_normal);
                        holder.tvA.setTextColor(Color.parseColor("#9a9a9a"));
						isA=2;
					}
				}

			});
			holder.layoutB.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					xuanb++;
					if(xuanb%2==1){
						holder.ivB.setImageResource(R.drawable.selected);
						holder.tvB.setTextColor(Color.parseColor("#61bc31"));
						isB=1;
					}else {
						holder.ivB.setImageResource(R.drawable.ic_practice_test_normal);
                        holder.tvB.setTextColor(Color.parseColor("#9a9a9a"));
						isB=2;
					}
				}
			});
			holder.layoutC.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					xuanc++;
					if(xuanc%2==1){
						holder.ivC.setImageResource(R.drawable.selected);
						holder.tvC.setTextColor(Color.parseColor("#61bc31"));
						isC=1;
					}else {
						holder.ivC.setImageResource(R.drawable.ic_practice_test_normal);
                        holder.tvC.setTextColor(Color.parseColor("#9a9a9a"));
						isC=2;
					}
				}
			});
			holder.layoutD.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					xuand++;
					if(xuand%2==1){
						holder.ivD.setImageResource(R.drawable.selected);
						holder.tvD.setTextColor(Color.parseColor("#61bc31"));
						isD=1;
					}else {
						holder.ivD.setImageResource(R.drawable.ic_practice_test_normal);
                        holder.tvD.setTextColor(Color.parseColor("#9a9a9a"));
						isD=2;
					}
				}
			});
			holder.btqr.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
                    holder.btqr.setEnabled(false);
					if(map.containsKey(position)){
						return;
					}
					map.put(position, true);
					if (dataItems.get(position).getAnswer().equals("7")) {
                        if (isA == 1 && isB == 1 && isC == 2 && isD == 2) {
                            mContext.setCurrentView(position + 1);
                            holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isA == 2 || isB == 2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("AB");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivA.setImageResource(R.drawable.xuanze);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }
                    }
                        if (dataItems.get(position).getAnswer().equals("8")) {
                            if (isA == 1 && isB == 2 && isC == 1 && isD == 2) {
                                mContext.setCurrentView(position + 1);
                                holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                                isCorrect = ConstantUtil.isCorrect;
                            } else {
                                if (isA == 2 || isC == 2) {
                                    isCorrect = ConstantUtil.isError;
                                    errortopicNum += 1;
                                    holder.leldaan.setVisibility(View.VISIBLE);holder.tx.setVisibility(View.VISIBLE);
                                    holder.tvdaan.setVisibility(View.VISIBLE);
                                    holder.tvdaan.setText("AC");
                                    //自动添加错误题目
                                    ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                    errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                    errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                    errorQuestionInfo.setOptionType("1");
                                    errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                    errorQuestionInfo.setIsRight(isCorrect);
                                    errorQuestionInfo.setQuestionSelect("1");
                                    errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                    if (dataItems.get(position).getUrl() != null) {
                                        errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                        errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                        errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                        errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                        errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                    } else {
                                        errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                        errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                        errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                        errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                        //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                    }
                                    if (!isContains) {
                                        dbManager.insertErrorQuestion1(errorQuestionInfo);
                                    } else {
                                        Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                    }
                                    //提示
                                    holder.wrongLayout.setVisibility(View.VISIBLE);
                                    holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                    //显示正确选项
                                    holder.ivA.setImageResource(R.drawable.xuanze);
                                    holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                    holder.ivC.setImageResource(R.drawable.xuanze);
                                    holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                                }
                                //保存数据
                                SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                                questionInfo.setQuestionId(dataItems.get(position).getId());
                                questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                                questionInfo.setIs_correct(isCorrect);
                                mContext.questionInfos.add(questionInfo);
                            }
                        }
                    if (dataItems.get(position).getAnswer().equals("9")) {
                        if (isA == 1 && isB == 2 && isC == 2 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvdaan.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isA == 2 || isD == 2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("AD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivA.setImageResource(R.drawable.xuanze);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }
                    }
                    if (dataItems.get(position).getAnswer().equals("10")) {
                        if (isA == 2 && isB == 1 && isC == 1 && isD == 2) {
                            mContext.setCurrentView(position + 1);
                            holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isC == 2 || isB == 2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("BC");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivC.setImageResource(R.drawable.xuanze);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }
                    }
                    if (dataItems.get(position).getAnswer().equals("11")) {
                        if (isA == 2 && isB == 1 && isC == 2 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isD == 2 || isB == 2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("BD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }
                    }
                    if (dataItems.get(position).getAnswer().equals("12")) {
                        if (isA ==2 && isB == 2 && isC == 1 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isC == 2 || isD == 2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("CD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivC.setImageResource(R.drawable.xuanze);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }
                    }
                    if (dataItems.get(position).getAnswer().equals("13")) {
                        if (isA == 1 && isB == 1 && isC == 1 && isD == 2) {
                            mContext.setCurrentView(position + 1);
                            holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isA == 2 || isB == 2||isC==2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("ABC");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivA.setImageResource(R.drawable.xuanze);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivC.setImageResource(R.drawable.xuanze);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }}
                    if (dataItems.get(position).getAnswer().equals("14")) {
                        if (isA == 1 && isB == 1 && isC == 2 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isA == 2 || isB == 2||isD==2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("ABD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivA.setImageResource(R.drawable.xuanze);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }}
                    if (dataItems.get(position).getAnswer().equals("15")) {
                        if (isA == 1 && isB ==2 && isC == 1 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isA == 2 || isD == 2||isC==2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("ACD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivA.setImageResource(R.drawable.xuanze);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivC.setImageResource(R.drawable.xuanze);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }}
                    if (dataItems.get(position).getAnswer().equals("16")) {
                        if (isA == 2 && isB == 1 && isC == 1 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isD == 2 || isB == 2||isC==2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("BCD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivC.setImageResource(R.drawable.xuanze);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }}
                    if (dataItems.get(position).getAnswer().equals("17")) {
                        if (isA == 1 && isB == 1 && isC == 1 && isD == 1) {
                            mContext.setCurrentView(position + 1);
                            holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                            holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
                            holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            isCorrect = ConstantUtil.isCorrect;
                        } else {
                            if (isA == 2 || isB == 2||isC==2||isD==2) {
                                isCorrect = ConstantUtil.isError;
                                errortopicNum += 1;
                                holder.tx.setVisibility(View.VISIBLE);
                                holder.tvdaan.setVisibility(View.VISIBLE);
                                holder.tvdaan.setText("ABCD");
                                //自动添加错误题目
                                ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
                                errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
                                errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
                                errorQuestionInfo.setOptionType("1");
                                errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
                                errorQuestionInfo.setIsRight(isCorrect);
                                errorQuestionInfo.setQuestionSelect("1");
                                errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
                                if (dataItems.get(position).getUrl() != null) {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
                                    errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
                                } else {
                                    errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem1());
                                    errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem2());
                                    errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem3());
                                    errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("") ? "" : imgServerUrl + dataItems.get(position).getItem4());
                                    //errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
                                }
                                if (!isContains) {
                                    dbManager.insertErrorQuestion1(errorQuestionInfo);
                                } else {
                                    Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
                                }
                                //提示
                                holder.wrongLayout.setVisibility(View.VISIBLE);
                                holder.explaindetailTv.setText("" + dataItems.get(position).getExplains());
                                //显示正确选项
                                holder.ivA.setImageResource(R.drawable.xuanze);
                                holder.tvA.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivB.setImageResource(R.drawable.xuanze);
                                holder.tvB.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivC.setImageResource(R.drawable.xuanze);
                                holder.tvC.setTextColor(Color.parseColor("#61bc31"));
                                holder.ivD.setImageResource(R.drawable.xuanze);
                                holder.tvD.setTextColor(Color.parseColor("#61bc31"));
                            }
                            //保存数据
                            SaveQuestionInfo questionInfo = new SaveQuestionInfo();
                            questionInfo.setQuestionId(dataItems.get(position).getId());
                            questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
                            questionInfo.setIs_correct(isCorrect);
                            mContext.questionInfos.add(questionInfo);
                        }}
				}
			});
		}else if(dataItems.get(position).getItem1().equals("正确")){
			//判断题
			holder.question.setText("(判断题)"+(position+1)+"."+dataItems.get(position).getQuestion());
			if (!dataItems.get(position).getUrl().equals("")){
				holder.iv.setVisibility(View.VISIBLE);
				Glide.with(mContext).load(dataItems.get(position).getUrl()).into(holder.iv);
			}else {
				holder.iv.setVisibility(View.GONE);
			}
			holder.layoutA.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if(map.containsKey(position)){
						return;
					}
					map.put(position, true);
					if(dataItems.get(position).getAnswer().contains("1")){
						mContext.setCurrentView(position+1);
						holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvA.setTextColor(Color.parseColor("#61bc31"));
						isCorrect=ConstantUtil.isCorrect;
					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
						errorQuestionInfo.setOptionType("2");
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("1");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
						if(dataItems.get(position).getUrl()!=null){
							errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
							errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
							errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
							errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
							errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
						}else{
							errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("")?"":imgServerUrl+dataItems.get(position).getItem1());
							errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("")?"":imgServerUrl+dataItems.get(position).getItem2());
							errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("")?"":imgServerUrl+dataItems.get(position).getItem3());
							errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("")?"":imgServerUrl+dataItems.get(position).getItem4());
							//errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
						}
						if(!isContains){
							dbManager.insertErrorQuestion1(errorQuestionInfo);
						}else {
							Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
						}

						holder.ivA.setImageResource(R.drawable.ic_practice_test_wrong);
						holder.tvA.setTextColor(Color.parseColor("#d53235"));
						//提示
						holder.wrongLayout.setVisibility(View.VISIBLE);
						holder.explaindetailTv.setText(""+dataItems.get(position).getExplains());
						//显示正确选项
						if(dataItems.get(position).getAnswer().contains("1")){
							holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvA.setTextColor(Color.parseColor("#61bc31"));
						}else if(dataItems.get(position).getAnswer().contains("2")){
							holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvB.setTextColor(Color.parseColor("#61bc31"));
						}else if(dataItems.get(position).getAnswer().contains("3")){
							holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvC.setTextColor(Color.parseColor("#61bc31"));
						}else if(dataItems.get(position).getAnswer().contains("4")){
							holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvD.setTextColor(Color.parseColor("#61bc31"));
//						}else if(dataItems.get(position).getCorrectAnswer().contains("E")){
//							holder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//							holder.tvE.setTextColor(Color.parseColor("#61bc31"));
						}
					}
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getId());
					questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
				}
			});
			holder.layoutB.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if(map.containsKey(position)){
						return;
					}
					map.put(position, true);
					if(dataItems.get(position).getAnswer().contains("2")){
						mContext.setCurrentView(position+1);
						holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvB.setTextColor(Color.parseColor("#61bc31"));
						isCorrect=ConstantUtil.isCorrect;
					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
						errorQuestionInfo.setOptionType("2");
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("2");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
						if(dataItems.get(position).getUrl()!=null){
							errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
							errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
							errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
							errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
							errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
						}else{
							errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("")?"":imgServerUrl+dataItems.get(position).getItem1());
							errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("")?"":imgServerUrl+dataItems.get(position).getItem2());
							errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("")?"":imgServerUrl+dataItems.get(position).getItem3());
							errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("")?"":imgServerUrl+dataItems.get(position).getItem4());
							//errorQuestionInfo.setOptionE(dataItems.get(position).getOptionE().equals("")?"":imgServerUrl+dataItems.get(position).getOptionE());
						}
						if(!isContains){
							dbManager.insertErrorQuestion1(errorQuestionInfo);
						}else {
							Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
						}
						holder.ivB.setImageResource(R.drawable.ic_practice_test_wrong);
						holder.tvB.setTextColor(Color.parseColor("#d53235"));
						//提示
						holder.wrongLayout.setVisibility(View.VISIBLE);
						holder.explaindetailTv.setText(""+dataItems.get(position).getExplains());
						//显示正确选项
						if(dataItems.get(position).getAnswer().contains("1")){
							holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvA.setTextColor(Color.parseColor("#61bc31"));
						}else if(dataItems.get(position).getAnswer().contains("2")){
							holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvB.setTextColor(Color.parseColor("#61bc31"));
						}else if(dataItems.get(position).getAnswer().contains("3")){
							holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvC.setTextColor(Color.parseColor("#61bc31"));
						}else if(dataItems.get(position).getAnswer().contains("4")){
							holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
							holder.tvD.setTextColor(Color.parseColor("#61bc31"));
						}
					}
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getId());
					//questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
					//questionInfo.setScore(dataItems.get(position).getScore());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
				}
			});
		}

		ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.parseColor("#2b89e9"));
		SpannableStringBuilder builder1 = new SpannableStringBuilder(holder.question.getText().toString());
		builder1.setSpan(blueSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		holder.question.setText(builder1);
		
		// 最后一页修改"下一步"按钮文字
		if (position ==49) {
			holder.nextText.setText("提交");
			holder.nextImage.setImageResource(R.drawable.vote_submit_finish);
            holder.nextText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.showSubmitDialog();
                }
            });
		}
		holder.previousBtn.setOnClickListener(new LinearOnClickListener(position - 1, false,position,holder));
		holder.nextBtn.setOnClickListener(new LinearOnClickListener(position + 1,true,position,holder));
        if (container != null) {
            container.removeView(viewItems.get(position));
        }
        container.addView(viewItems.get(position));
		return viewItems.get(position);
	}
	
	/**
	 * @author  设置上一步和下一步按钮监听
	 * 
	 */
	class LinearOnClickListener implements OnClickListener {

		private int mPosition;
		private int mPosition1;
		private boolean mIsNext;
		private ViewHolder viewHolder;

		public LinearOnClickListener(int position, boolean mIsNext,int position1,ViewHolder viewHolder) {
			mPosition = position;
			mPosition1 = position1;
			this.viewHolder = viewHolder;
			this.mIsNext = mIsNext;
		}

		@Override
		public void onClick(View v) {
				if (mPosition == viewItems.size()) {
					//单选
					if(dataItems.get(mPosition).getAnswer().equals("1")||dataItems.get(mPosition).getAnswer().equals("2")||
							dataItems.get(mPosition).getAnswer().equals("3")||dataItems.get(mPosition).getAnswer().equals("4")){
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
						//mContext.uploadExamination(errortopicNum);
					}else if(dataItems.get(mPosition).getAnswer().equals("7")||dataItems.get(mPosition).getAnswer().equals("8")||
                            dataItems.get(mPosition).getAnswer().equals("9")||dataItems.get(mPosition).getAnswer().equals("10")||
                            dataItems.get(mPosition).getAnswer().equals("11")||dataItems.get(mPosition).getAnswer().equals("12")||
                            dataItems.get(mPosition).getAnswer().equals("13")||dataItems.get(mPosition).getAnswer().equals("14")||
                            dataItems.get(mPosition).getAnswer().equals("15")||dataItems.get(mPosition).getAnswer().equals("16")||dataItems.get(mPosition).getAnswer().equals("17")){
						//判断多选时的点击
						if(!map.containsKey(mPosition1)){
							if(!mapClick.containsKey(mPosition1)){
								Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
								return;
							}
						}
						map.put(mPosition1, true);
						
						if(mapMultiSelect.containsKey(mPosition1)){
							//提交答题
							//mContext.uploadExamination(errortopicNum);
						}else{
							String ssStr=dataItems.get(mPosition1).getAnswer();
							ssStr=ssStr.replace("|", "");
							
							if(mPosition == viewItems.size()){
								if(answerLast.toString().contains("1")){
									answer1.append("1");
								}if(answerLast.toString().contains("2")){
									answer1.append("2");
								}if(answerLast.toString().contains("3")){
									answer1.append("3");
								}if(answerLast.toString().contains("4")){
									answer1.append("4");
//								}if(answerLast.toString().contains("E")){
//									answer1.append("E");
								}
								
							}else{
								if(answer.toString().contains("1")){
									answer1.append("1");
								}if(answer.toString().contains("2")){
									answer1.append("2");
								}if(answer.toString().contains("3")){
									answer1.append("3");
								}if(answer.toString().contains("4")){
									answer1.append("4");
//								}if(answer.toString().contains("E")){
//									answer1.append("E");
								}
							}
							
							if(answer1.toString().equals(ssStr)){
								//清除答案
								answer.delete(0, answer.length());
								answer1.delete(0, answer1.length());
								isCorrect=ConstantUtil.isCorrect;
								mapMultiSelect.put(mPosition1, ConstantUtil.isCorrect);
								//保存数据
								SaveQuestionInfo questionInfo=new SaveQuestionInfo();
								questionInfo.setQuestionId(dataItems.get(mPosition1).getId());
								//questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
								questionInfo.setRealAnswer(dataItems.get(mPosition1).getAnswer());
								//questionInfo.setScore(dataItems.get(mPosition1).getScore());
								questionInfo.setIs_correct(isCorrect);
								mContext.questionInfos.add(questionInfo);
							}else{
								//清除答案
								answer.delete(0, answer.length());
								answer1.delete(0, answer1.length());
								errortopicNum+=1;
								isCorrect=ConstantUtil.isError;
								//自动添加错误题目
								ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
								errorQuestionInfo.setQuestionName(dataItems.get(mPosition1).getQuestion());
								//errorQuestionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
								errorQuestionInfo.setQuestionAnswer(dataItems.get(mPosition1).getAnswer());
								errorQuestionInfo.setIsRight(isCorrect);
								errorQuestionInfo.setQuestionSelect(answer.toString());
								errorQuestionInfo.setAnalysis(dataItems.get(mPosition1).getExplains());
								//errorQuestionInfo.setOptionType(dataItems.get(mPosition1).getOption_type());
								if(dataItems.get(mPosition1).getUrl()!=null){
									errorQuestionInfo.setOptionA(dataItems.get(mPosition1).getItem1());
									errorQuestionInfo.setOptionB(dataItems.get(mPosition1).getItem2());
									errorQuestionInfo.setOptionC(dataItems.get(mPosition1).getItem3());
									errorQuestionInfo.setOptionD(dataItems.get(mPosition1).getItem4());
									//errorQuestionInfo.setOptionE(dataItems.get(mPosition1).getOptionE());
								}else{
									errorQuestionInfo.setOptionA(dataItems.get(mPosition1).getItem1().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem1());
									errorQuestionInfo.setOptionB(dataItems.get(mPosition1).getItem2().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem2());
									errorQuestionInfo.setOptionC(dataItems.get(mPosition1).getItem3().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem3());
									errorQuestionInfo.setOptionD(dataItems.get(mPosition1).getItem4().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem4());
									//errorQuestionInfo.setOptionE(dataItems.get(mPosition1).getOptionE().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getOptionE());
								}
								if(!isContains){
									dbManager.insertErrorQuestion(errorQuestionInfo);
								}else {
									Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
								}
								isCorrect=ConstantUtil.isError;
								mapMultiSelect.put(mPosition1, ConstantUtil.isError);
								
								//提示
								viewHolder.wrongLayout.setVisibility(View.VISIBLE);
								viewHolder.explaindetailTv.setText(""+dataItems.get(mPosition1).getExplains());
								//保存数据
								SaveQuestionInfo questionInfo=new SaveQuestionInfo();
								questionInfo.setQuestionId(dataItems.get(mPosition1).getId());
								//questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
								questionInfo.setRealAnswer(dataItems.get(mPosition1).getAnswer());
								//questionInfo.setScore(dataItems.get(mPosition1).getScore());
								questionInfo.setIs_correct(isCorrect);
								mContext.questionInfos.add(questionInfo);
								//显示正确选项
								if(dataItems.get(mPosition1).getAnswer().contains("1")){
									viewHolder.ivA.setImageResource(R.drawable.ic_practice_test_right);
									viewHolder.tvA.setTextColor(Color.parseColor("#61bc31"));
								}
								if(dataItems.get(mPosition1).getAnswer().contains("2")){
									viewHolder.ivB.setImageResource(R.drawable.ic_practice_test_right);
									viewHolder.tvB.setTextColor(Color.parseColor("#61bc31"));
								}
								if(dataItems.get(mPosition1).getAnswer().contains("3")){
									viewHolder.ivC.setImageResource(R.drawable.ic_practice_test_right);
									viewHolder.tvC.setTextColor(Color.parseColor("#61bc31"));
								}
								if(dataItems.get(mPosition1).getAnswer().contains("4")){
									viewHolder.ivD.setImageResource(R.drawable.ic_practice_test_right);
									viewHolder.tvD.setTextColor(Color.parseColor("#61bc31"));
								}
//								if(dataItems.get(mPosition1).getCorrectAnswer().contains("E")){
//									viewHolder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//									viewHolder.tvE.setTextColor(Color.parseColor("#61bc31"));
//								}
//
							}
							
						}
					}else{
						if(!map.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
						//mContext.uploadExamination(errortopicNum);
					}
				} else {
					if(mPosition ==-1){
						Toast.makeText(mContext, "已经是第一题", Toast.LENGTH_SHORT).show();
						return;
					}else{
						//单选
						if(dataItems.get(mPosition).getAnswer().equals("1")||dataItems.get(mPosition).getAnswer().equals("2")||
								dataItems.get(mPosition).getAnswer().equals("3")||dataItems.get(mPosition).getAnswer().equals("4")){
							if(mIsNext){
								if(!map.containsKey(mPosition1)){
									Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
									return;
								}
							}
							isNext = mIsNext;
							mContext.setCurrentView(mPosition);
						}else if((!dataItems.get(mPosition).getAnswer().equals("1")||!dataItems.get(mPosition).getAnswer().equals("2")||
								!dataItems.get(mPosition).getAnswer().equals("3")||!dataItems.get(mPosition).getAnswer().equals("4"))&&
								(dataItems.get(mPosition).getAnswer()!=null&&dataItems.get(mPosition).getAnswer()!=null)){
							if(mIsNext){
								//判断多选时的点击
								if(!map.containsKey(mPosition1)){
									if(!mapClick.containsKey(mPosition1)){
										Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
										return;
									}
								}
								map.put(mPosition1, true);
								
								if(mapMultiSelect.containsKey(mPosition1)){
									//清除答案
									answer.delete(0, answer.length());
									//选过的，直接跳转下一题
									isNext = mIsNext;
									mContext.setCurrentView(mPosition);
								}else{
									String ssStr=dataItems.get(mPosition1).getAnswer();
									ssStr=ssStr.replace("|", "");
									if(answer.toString().contains("1")){
										answer1.append("1");
									}if(answer.toString().contains("2")){
										answer1.append("2");
									}if(answer.toString().contains("3")){
										answer1.append("3");
									}if(answer.toString().contains("4")){
										answer1.append("4");
//									}if(answer.toString().contains("E")){
//										answer1.append("E");
									}
									if(answer1.toString().equals(ssStr)){
										//清除答案
										answer.delete(0, answer.length());
										answer1.delete(0, answer1.length());
										isCorrect=ConstantUtil.isCorrect;
										mapMultiSelect.put(mPosition1, ConstantUtil.isCorrect);
										//保存数据
										SaveQuestionInfo questionInfo=new SaveQuestionInfo();
										questionInfo.setQuestionId(dataItems.get(mPosition1).getId());
										//questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
										questionInfo.setRealAnswer(dataItems.get(mPosition1).getAnswer());
										//questionInfo.setScore(dataItems.get(mPosition1).getScore());
										questionInfo.setIs_correct(isCorrect);
										mContext.questionInfos.add(questionInfo);
										isNext = mIsNext;
										mContext.setCurrentView(mPosition);
									}else{
										//清除答案
										answer.delete(0, answer.length());
										answer1.delete(0, answer1.length());
										errortopicNum+=1;
										isCorrect=ConstantUtil.isError;
										//自动添加错误题目
										ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
										errorQuestionInfo.setQuestionName(dataItems.get(mPosition1).getQuestion());
										//errorQuestionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
										errorQuestionInfo.setQuestionAnswer(dataItems.get(mPosition1).getAnswer());
										errorQuestionInfo.setIsRight(isCorrect);
										errorQuestionInfo.setQuestionSelect(answer.toString());
										errorQuestionInfo.setAnalysis(dataItems.get(mPosition1).getExplains());
										//errorQuestionInfo.setOptionType(dataItems.get(mPosition1).getOption_type());
										if(dataItems.get(mPosition1).getUrl()!=null){
											errorQuestionInfo.setOptionA(dataItems.get(mPosition1).getItem1());
											errorQuestionInfo.setOptionB(dataItems.get(mPosition1).getItem2());
											errorQuestionInfo.setOptionC(dataItems.get(mPosition1).getItem3());
											errorQuestionInfo.setOptionD(dataItems.get(mPosition1).getItem4());
											//errorQuestionInfo.setOptionE(dataItems.get(mPosition1).getOptionE());
										}else{
											errorQuestionInfo.setOptionA(dataItems.get(mPosition1).getItem1().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem1());
											errorQuestionInfo.setOptionB(dataItems.get(mPosition1).getItem2().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem2());
											errorQuestionInfo.setOptionC(dataItems.get(mPosition1).getItem3().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem3());
											errorQuestionInfo.setOptionD(dataItems.get(mPosition1).getItem4().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getItem4());
											//errorQuestionInfo.setOptionE(dataItems.get(mPosition1).getOptionE().equals("")?"":imgServerUrl+dataItems.get(mPosition1).getOptionE());
										}
										if(!isContains){
											dbManager.insertErrorQuestion1(errorQuestionInfo);
										}else {
											Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
										}
										isCorrect=ConstantUtil.isError;
										mapMultiSelect.put(mPosition1, ConstantUtil.isError);
										
										//提示
										viewHolder.wrongLayout.setVisibility(View.VISIBLE);
										viewHolder.explaindetailTv.setText(""+dataItems.get(mPosition1).getExplains());
										//保存数据
										SaveQuestionInfo questionInfo=new SaveQuestionInfo();
										questionInfo.setQuestionId(dataItems.get(mPosition1).getId());
										//questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
										questionInfo.setRealAnswer(dataItems.get(mPosition1).getAnswer());
										//questionInfo.setScore(dataItems.get(mPosition1).getScore());
										questionInfo.setIs_correct(isCorrect);
										mContext.questionInfos.add(questionInfo);
										//显示正确选项
										if(dataItems.get(mPosition1).getAnswer().contains("1")){
											viewHolder.ivA.setImageResource(R.drawable.ic_practice_test_right);
											viewHolder.tvA.setTextColor(Color.parseColor("#61bc31"));
										}
										if(dataItems.get(mPosition1).getAnswer().contains("2")){
											viewHolder.ivB.setImageResource(R.drawable.ic_practice_test_right);
											viewHolder.tvB.setTextColor(Color.parseColor("#61bc31"));
										}
										if(dataItems.get(mPosition1).getAnswer().contains("3")){
											viewHolder.ivC.setImageResource(R.drawable.ic_practice_test_right);
											viewHolder.tvC.setTextColor(Color.parseColor("#61bc31"));
										}
										if(dataItems.get(mPosition1).getAnswer().contains("4")){
											viewHolder.ivD.setImageResource(R.drawable.ic_practice_test_right);
											viewHolder.tvD.setTextColor(Color.parseColor("#61bc31"));
										}
//										if(dataItems.get(mPosition1).getCorrectAnswer().contains("E")){
//											viewHolder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//											viewHolder.tvE.setTextColor(Color.parseColor("#61bc31"));
//										}
									}
								}
							}else{
								mContext.setCurrentView(mPosition);
							}
							
						}else{
							if(mIsNext){
								if(!map.containsKey(mPosition1)){
									Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
									return;
								}
							}

							isNext = mIsNext;
							mContext.setCurrentView(mPosition);
						}
					}
				}
			
		}

	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	//错题数
	public int errorTopicNum(){
		if(errortopicNum!=0){
			return errortopicNum;
		}
		return 0;
	}

	public class ViewHolder {
		TextView questionType;
		TextView question;
		LinearLayout previousBtn, nextBtn,errorBtn;
		TextView nextText;
		ImageView nextImage;
		LinearLayout wrongLayout;
		TextView explaindetailTv;
		LinearLayout layoutA;
		LinearLayout layoutB;
		LinearLayout layoutC;
		LinearLayout layoutD;
		LinearLayout leldaan;
		TextView  tvdaan;
        TextView  tx;
		Button btqr;
        LinearLayout llqueren;
		//LinearLayout layoutE;
		ImageView ivA;
		ImageView ivB;
		ImageView ivC;
		ImageView ivD;
		//ImageView ivE;
		TextView tvA;
		TextView tvB;
		TextView tvC;
		TextView tvD;
		//TextView tvE;
		ImageView ivA_;
		ImageView ivB_;
		ImageView ivC_;
		ImageView ivD_;
		ImageView iv;
		//ImageView ivE_;
	}
	public void xuanxiang(final ViewHolder holder, final int position){
		holder.layoutA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(map.containsKey(position)){
					return;
				}
				map.put(position, true);
				if(dataItems.get(position).getAnswer().contains("1")){
					mContext.setCurrentView(position+1);
					holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
					holder.tvA.setTextColor(Color.parseColor("#61bc31"));
					isCorrect=ConstantUtil.isCorrect;
				}else{
					isCorrect=ConstantUtil.isError;
					errortopicNum+=1;
					//自动添加错误题目
					ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
					errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
					errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
					errorQuestionInfo.setOptionType("0");
					errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
					errorQuestionInfo.setIsRight(isCorrect);
					errorQuestionInfo.setQuestionSelect("1");
					errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
					if(dataItems.get(position).getUrl()!=null){
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
						errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
					}else{
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("")?"":imgServerUrl+dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("")?"":imgServerUrl+dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("")?"":imgServerUrl+dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("")?"":imgServerUrl+dataItems.get(position).getItem4());
					}
					if(!isContains){
						dbManager.insertErrorQuestion1(errorQuestionInfo);
					}else {
						Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
					}
					holder.ivA.setImageResource(R.drawable.ic_practice_test_wrong);
					holder.tvA.setTextColor(Color.parseColor("#d53235"));
					//提示
					holder.wrongLayout.setVisibility(View.VISIBLE);
					holder.explaindetailTv.setText(""+dataItems.get(position).getExplains());
					//显示正确选项
					if(dataItems.get(position).getAnswer().contains("1")){
						holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvA.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("2")){
						holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvB.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("3")){
						holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvC.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("4")){
						holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvD.setTextColor(Color.parseColor("#61bc31"));
//						}else if(dataItems.get(position).getCorrectAnswer().contains("E")){
//							holder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//							holder.tvE.setTextColor(Color.parseColor("#61bc31"));
					}
				}
				//保存数据
				SaveQuestionInfo questionInfo=new SaveQuestionInfo();
				questionInfo.setQuestionId(dataItems.get(position).getId());
				questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
				questionInfo.setIs_correct(isCorrect);
				mContext.questionInfos.add(questionInfo);
			}
		});
		holder.layoutB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(map.containsKey(position)){
					return;
				}
				map.put(position, true);
				if(dataItems.get(position).getAnswer().contains("2")){
					mContext.setCurrentView(position+1);
					holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
					holder.tvB.setTextColor(Color.parseColor("#61bc31"));
					isCorrect=ConstantUtil.isCorrect;
				}else{
					isCorrect=ConstantUtil.isError;
					errortopicNum+=1;
					//自动添加错误题目
					ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
					errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
					errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
					errorQuestionInfo.setOptionType("0");
					errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
					errorQuestionInfo.setIsRight(isCorrect);
					errorQuestionInfo.setQuestionSelect("2");
					errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
					if(dataItems.get(position).getUrl()!=null){
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
						errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
					}else{
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("")?"":imgServerUrl+dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("")?"":imgServerUrl+dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("")?"":imgServerUrl+dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("")?"":imgServerUrl+dataItems.get(position).getItem4());
					}
					if(!isContains){
						dbManager.insertErrorQuestion1(errorQuestionInfo);
					}else {
						Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
					}
					holder.ivB.setImageResource(R.drawable.ic_practice_test_wrong);
					holder.tvB.setTextColor(Color.parseColor("#d53235"));
					//提示
					holder.wrongLayout.setVisibility(View.VISIBLE);
					holder.explaindetailTv.setText(""+dataItems.get(position).getExplains());
					//显示正确选项
					if(dataItems.get(position).getAnswer().contains("1")){
						holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvA.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("2")){
						holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvB.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("3")){
						holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvC.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("4")){
						holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvD.setTextColor(Color.parseColor("#61bc31"));
//						}else if(dataItems.get(position).getCorrectAnswer().contains("E")){
//							holder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//							holder.tvE.setTextColor(Color.parseColor("#61bc31"));
					}
				}
				//保存数据
				SaveQuestionInfo questionInfo=new SaveQuestionInfo();
				questionInfo.setQuestionId(dataItems.get(position).getId());
				questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
				questionInfo.setIs_correct(isCorrect);
				mContext.questionInfos.add(questionInfo);
			}
		});
		holder.layoutC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(map.containsKey(position)){
					return;
				}
				map.put(position, true);
				if(dataItems.get(position).getAnswer().contains("3")){
					mContext.setCurrentView(position+1);
					holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
					holder.tvC.setTextColor(Color.parseColor("#61bc31"));
					isCorrect=ConstantUtil.isCorrect;
				}else{
					isCorrect=ConstantUtil.isError;
					errortopicNum+=1;
					//自动添加错误题目
					ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
					errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
					errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
					errorQuestionInfo.setOptionType("0");
					errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
					errorQuestionInfo.setIsRight(isCorrect);
					errorQuestionInfo.setQuestionSelect("3");
					errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
					if(dataItems.get(position).getUrl()!=null){
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
						errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
					}else{
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("")?"":imgServerUrl+dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("")?"":imgServerUrl+dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("")?"":imgServerUrl+dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("")?"":imgServerUrl+dataItems.get(position).getItem4());
					}
					if(!isContains){
						dbManager.insertErrorQuestion1(errorQuestionInfo);
					}else {
						Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
					}
					holder.ivC.setImageResource(R.drawable.ic_practice_test_wrong);
					holder.tvC.setTextColor(Color.parseColor("#d53235"));
					//提示
					holder.wrongLayout.setVisibility(View.VISIBLE);
					holder.explaindetailTv.setText(""+dataItems.get(position).getExplains());
					//显示正确选项
					if(dataItems.get(position).getAnswer().contains("1")){
						holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvA.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("2")){
						holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvB.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("3")){
						holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvC.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("4")){
						holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvD.setTextColor(Color.parseColor("#61bc31"));
//						}else if(dataItems.get(position).getCorrectAnswer().contains("E")){
//							holder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//							holder.tvE.setTextColor(Color.parseColor("#61bc31"));
					}
				}
				//保存数据
				SaveQuestionInfo questionInfo=new SaveQuestionInfo();
				questionInfo.setQuestionId(dataItems.get(position).getId());
				questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
				questionInfo.setIs_correct(isCorrect);
				mContext.questionInfos.add(questionInfo);
			}
		});
		holder.layoutD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(map.containsKey(position)){
					return;
				}
				map.put(position, true);
				if(dataItems.get(position).getAnswer().contains("4")){
					mContext.setCurrentView(position+1);
					holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
					holder.tvD.setTextColor(Color.parseColor("#61bc31"));
					isCorrect=ConstantUtil.isCorrect;
				}else{
					isCorrect=ConstantUtil.isError;
					errortopicNum+=1;
					//自动添加错误题目
					ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
					errorQuestionInfo.setQuestionId(dataItems.get(position).getId());
					errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestion());
					errorQuestionInfo.setOptionType("0");
					errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getAnswer());
					errorQuestionInfo.setIsRight(isCorrect);
					errorQuestionInfo.setQuestionSelect("4");
					errorQuestionInfo.setAnalysis(dataItems.get(position).getExplains());
					if(dataItems.get(position).getUrl()!=null){
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4());
						errorQuestionInfo.setUrl(dataItems.get(position).getUrl());
					}else{
						errorQuestionInfo.setOptionA(dataItems.get(position).getItem1().equals("")?"":imgServerUrl+dataItems.get(position).getItem1());
						errorQuestionInfo.setOptionB(dataItems.get(position).getItem2().equals("")?"":imgServerUrl+dataItems.get(position).getItem2());
						errorQuestionInfo.setOptionC(dataItems.get(position).getItem3().equals("")?"":imgServerUrl+dataItems.get(position).getItem3());
						errorQuestionInfo.setOptionD(dataItems.get(position).getItem4().equals("")?"":imgServerUrl+dataItems.get(position).getItem4());
					}
					if(!isContains){
						dbManager.insertErrorQuestion1(errorQuestionInfo);
					}else {
						Toast.makeText(mContext, "已添加", Toast.LENGTH_SHORT).show();
					}
					holder.ivD.setImageResource(R.drawable.ic_practice_test_wrong);
					holder.tvD.setTextColor(Color.parseColor("#d53235"));
					//提示
					holder.wrongLayout.setVisibility(View.VISIBLE);
					holder.explaindetailTv.setText(""+dataItems.get(position).getExplains());
					//显示正确选项
					if(dataItems.get(position).getAnswer().contains("1")){
						holder.ivA.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvA.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("2")){
						holder.ivB.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvB.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("3")){
						holder.ivC.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvC.setTextColor(Color.parseColor("#61bc31"));
					}else if(dataItems.get(position).getAnswer().contains("4")){
						holder.ivD.setImageResource(R.drawable.ic_practice_test_right);
						holder.tvD.setTextColor(Color.parseColor("#61bc31"));
//						}else if(dataItems.get(position).getCorrectAnswer().contains("E")){
//							holder.ivE.setImageResource(R.drawable.ic_practice_test_right);
//							holder.tvE.setTextColor(Color.parseColor("#61bc31"));
					}
				}
				//保存数据
				SaveQuestionInfo questionInfo=new SaveQuestionInfo();
				questionInfo.setQuestionId(dataItems.get(position).getId());
				questionInfo.setRealAnswer(dataItems.get(position).getAnswer());
				questionInfo.setIs_correct(isCorrect);
				mContext.questionInfos.add(questionInfo);
			}
		});
	}
}
