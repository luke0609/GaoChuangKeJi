package com.jiaokaokeji.gaochuangkeji.book.prjo;


import java.util.ArrayList;

public class AnSwerInfo {
	public ArrayList<AnSwerInfo> ggList;
	public int questionId; // 试题主键
	public String questionName; // 试题题目
	public String analysis; // 试题分析
	public String questionType; // 试题类型
	public String correctAnswer; // 正确答案
	public String optionA; // 正确答案A
	public String optionB; // 正确答案B
	public String optionC; // 正确答案C
	public String optionD; // 正确答案D
	public String option_type; // 是否是图片题0是1否
	public String url;
	public String isSelect; // 是否选择0是1否

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getIsSelect() {
		return isSelect;
	}
	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}
	public String getOption_type() {
		return option_type;
	}
	public void setOption_type(String option_type) {
		this.option_type = option_type;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
}
