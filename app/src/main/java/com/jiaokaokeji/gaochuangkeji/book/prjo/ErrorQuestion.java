package com.jiaokaokeji.gaochuangkeji.book.prjo;

import android.os.Parcel;
import android.os.Parcelable;

public class ErrorQuestion implements Parcelable{
	
	public String  questionId;
	public String questionName;
	public String questionAnswer;
	public String questionSelect;
	public String isRight;
	public String Analysis;
	public String optionA;
	public String optionB;
	public String optionC;
	public String optionD;
	public String optionType;
	public  String url;

	public ErrorQuestion(Parcel in) {
		questionId = in.readString();
		questionName = in.readString();
		questionAnswer = in.readString();
		questionSelect = in.readString();
		isRight = in.readString();
		Analysis = in.readString();
		optionA = in.readString();
		optionB = in.readString();
		optionC = in.readString();
		optionD = in.readString();
		optionType = in.readString();
		url=in.readString();
	}

	public static final Creator<ErrorQuestion> CREATOR = new Creator<ErrorQuestion>() {
		@Override
		public ErrorQuestion createFromParcel(Parcel in) {
			return new ErrorQuestion(in);
		}

		@Override
		public ErrorQuestion[] newArray(int size) {
			return new ErrorQuestion[size];
		}
	};

	public ErrorQuestion() {

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO Auto-generated method stub
		dest.writeString(questionName);
		dest.writeString(questionAnswer);
		dest.writeString(questionSelect);
		dest.writeString(isRight);
		dest.writeString(Analysis);
		dest.writeString(optionA);
		dest.writeString(optionB);
		dest.writeString(optionC);
		dest.writeString(optionD);
		dest.writeString(optionType);
		dest.writeString(url);
	}
	
	

}
