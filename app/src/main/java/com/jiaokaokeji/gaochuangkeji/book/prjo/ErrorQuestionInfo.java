package com.jiaokaokeji.gaochuangkeji.book.prjo;

public class ErrorQuestionInfo {
	
	public String questionId;
	public String questionName;
	public String questionAnswer;
	public String questionSelect;
	public String isRight;
	public String optionType;
	public String Analysis;
	public String optionA;
	public String optionB;
	public String optionC;
	public String optionD;
	public String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}




	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public String getQuestionSelect() {
		return questionSelect;
	}
	public void setQuestionSelect(String questionSelect) {
		this.questionSelect = questionSelect;
	}
	public String getIsRight() {
		return isRight;
	}
	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}
	public String getAnalysis() {
		return Analysis;
	}
	public void setAnalysis(String analysis) {
		Analysis = analysis;
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

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	@Override
	public String toString() {
		return "ErrorQuestionInfo{" +
				"questionId=" + questionId +
				", questionName='" + questionName + '\'' +
				", questionAnswer='" + questionAnswer + '\'' +
				", questionSelect='" + questionSelect + '\'' +
				", isRight='" + isRight + '\'' +
				", optionType='" + optionType + '\'' +
				", Analysis='" + Analysis + '\'' +
				", optionA='" + optionA + '\'' +
				", optionB='" + optionB + '\'' +
				", optionC='" + optionC + '\'' +
				", optionD='" + optionD + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
