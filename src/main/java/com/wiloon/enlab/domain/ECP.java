package com.wiloon.enlab.domain;

public class ECP {
	private int ID;
	private String english;
	private String chinese;
	private String pronunciation;

	private int count;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english.trim();
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public String getPronunciation() {
		if (null == pronunciation) {
			return "";
		}
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
