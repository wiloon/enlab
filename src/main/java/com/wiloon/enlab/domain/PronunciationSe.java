package com.wiloon.enlab.domain;

public class PronunciationSe extends Pronunciation {
	protected String pronFormator(String pron) {
		pron = super.pronFormator(pron);
		pron = splitPronunciation(pron);
		return pron;
	}

	private String splitPronunciation(String pron) {

		int index = pron.indexOf(";");
		if (index >= 0) {
			String returnString = pron.substring(index + 1);

			return returnString;
		}

		return pron;
	}

}
