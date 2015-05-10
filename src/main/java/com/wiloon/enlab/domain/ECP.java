package com.wiloon.enlab.domain;

public class ECP {
	private int id;
	private String english;
	private String chinese;
	private String pronunciation;

	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setID(String iD) {
		id = Integer.parseInt(iD);
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
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
	}
	public String toString(){
		return "id="+id+",english="+english;
	}
}
