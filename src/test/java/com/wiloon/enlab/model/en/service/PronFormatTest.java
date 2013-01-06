package com.wiloon.enlab.model.en.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.model.en.service.impl.EnService;

public class PronFormatTest {
	ECP ecp;

	@Before
	public void before() {
		ecp = new ECP();
		ecp.setEnglish("testE");
		ecp.setChinese("testC");

	}

	@Test
	public void pronFormater() {
		ecp.setPronunciation("*[\\/prontest/\\]");
		EnService es = new EnService(null,null);

		ecp = es.ecpFormater(ecp);
		assertEquals("/prontest/", ecp.getPronunciation());
	}

	@Test
	public void pronFormaterNull() {
		ecp.setPronunciation("");
		EnService es = new EnService(null,null);
		ecp = es.ecpFormater(ecp);
		assertEquals("", ecp.getPronunciation());
	}
}
