package com.wiloon.enlab.domain;

import java.util.List;

public class EnInfo {
	private int wordCount;
	private List<ECP> lstEcp;
	private List<ECP> lstTop10;
	private ECP ecp;
	private int wordCountToday;
	private int countTodayUpdate;
	private String message;
	private String wordCountBin;
	private String wordCoountHex;

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
		long l = Long.parseLong(Integer.toBinaryString(wordCount));
		this.wordCountBin = String.format("%016d", l);
		this.wordCoountHex = "0x"
				+ Integer.toHexString(wordCount).toUpperCase();
	}

	public List<ECP> getLstEcp() {
		return lstEcp;
	}

	public void setLstEcp(List<ECP> lstEcp) {
		this.lstEcp = lstEcp;
	}

	public int getWordCountToday() {
		return wordCountToday;
	}

	public void setWordCountToday(int wordCountToday) {
		this.wordCountToday = wordCountToday;
	}

	public int getCountTodayUpdate() {
		return countTodayUpdate;
	}

	public void setCountTodayUpdate(int countTodayUpdate) {
		this.countTodayUpdate = countTodayUpdate;
	}

	public List<ECP> getLstTop10() {
		return lstTop10;
	}

	public void setLstTop10(List<ECP> lstTop10) {
		this.lstTop10 = lstTop10;
	}

	public ECP getEcp() {
		return ecp;
	}

	public void setEcp(ECP ecp) {
		this.ecp = ecp;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getWordCountBin() {
		return wordCountBin;
	}

	public void setWordCountBin(String wordCountBin) {
		this.wordCountBin = wordCountBin;
	}

	public String getWordCoountHex() {
		return wordCoountHex;
	}

	public void setWordCoountHex(String wordCoountHex) {
		this.wordCoountHex = wordCoountHex;
	}

}
