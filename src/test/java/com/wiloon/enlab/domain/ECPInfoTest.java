package com.wiloon.enlab.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ECPInfoTest {
	EnInfo ei;

	@Before
	public void before() {
		ei = new EnInfo();
		ei.setWordCount(7719);
	}

	@Test
	public void wordCountToBin() {

		assertEquals("0001111000100111", ei.getWordCountBin());
	}
}
