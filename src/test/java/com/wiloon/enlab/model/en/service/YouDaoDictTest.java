package com.wiloon.enlab.model.en.service;

import junit.framework.Assert;

import org.junit.Test;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.model.en.service.impl.WebDictService;

public class YouDaoDictTest {

	@Test
	public void youDaoDictTest() {
		WebDictService wds = new WebDictService();
		ECP ecp = wds.translate("test");
		Assert.assertNotNull(ecp);
		Assert.assertEquals("test", ecp.getEnglish());
		Assert.assertEquals("n.试验；检验vt.试验；测试vi.试验；测试", ecp.getChinese());
		Assert.assertEquals("[test]", ecp.getPronunciation());
	}

	// @Test
	public void youDaoDictTestDef() {
		WebDictService wds = new WebDictService();
		ECP ecp = wds.translate("taliban");
		Assert.assertNotNull(ecp);
		Assert.assertEquals("taliban", ecp.getEnglish());
		Assert.assertEquals(
				"塔利班(阿富汗学生武装组织，于1995年由伊斯兰原教旨主义者创建，从1996年至2001年冬曾执掌阿富汗达6年之久，最后被击败)",
				ecp.getChinese());
		Assert.assertEquals("[taliban]", ecp.getPronunciation());
	}

	@Test
	public void youDaoDictBeanOil() {
		String word = "bean+oil";
		WebDictService wds = new WebDictService();
		ECP ecp = wds.translate(word);
		Assert.assertNotNull(ecp);
		Assert.assertEquals("bean+oil", ecp.getEnglish());
		Assert.assertEquals("[食品]豆油", ecp.getChinese());
		Assert.assertEquals("", ecp.getPronunciation());
	}
}
