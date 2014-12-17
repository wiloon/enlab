package com.wiloon.enlab.model.en.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;



import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.model.en.service.IWebDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDictService implements IWebDictService {
	/**
	 * Logger for this class
	 */
    private static Logger logger = LoggerFactory.getLogger(WebDictService.class);
	private ECP ecp;

	@Override
	public ECP translate(String english) {
		logger.info("search you dao dict: " + english);
		ecp = new ECP();
		boolean record = false;
		boolean pron = false;
		StringBuffer html = new StringBuffer();
		ecp.setEnglish(english);
		URL url;
		try {
			url = new URL("http://dict.youdao.com/search?q="
					+ english.replace(" ", "+"));
			logger.info("url: " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			logger.debug("charset: " + Charset.defaultCharset().displayName());
			InputStreamReader isr = new InputStreamReader(
					conn.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				logger.debug("read line: " + temp);

				// pronunciation
				if (pron == false
						&& temp.indexOf("<span class=\"phonetic\">") > -1) {
					temp = temp.replace("<span class=\"phonetic\">", "");
					temp = temp.replace("</span>", "");
					temp = temp.trim();
					ecp.setPronunciation(temp);
					pron = true;
				}
				if (temp.indexOf("trans-container") > -1) {
					record = true;
				}
				if (record && temp.indexOf("</ul>") > -1) {
					record = false;
					break;
				}
				if (record) {
					html.append(temp);
				}

			}
			br.close();
			isr.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String rtn = html.toString();
		rtn = rtn.replace("<div class=\"trans-container\">", "");
		rtn = rtn.replace("ul", "");
		rtn = rtn.replace("<", "");
		rtn = rtn.replace("/", "");
		rtn = rtn.replace(">", "");
		rtn = rtn.replace("li", "");
		rtn = rtn.replace("div", "");
		rtn = rtn.replace(" ", "");
		if (logger.isInfoEnabled()) {
			logger.debug("result:" + rtn); //$NON-NLS-1$
		}
		ecp.setChinese(rtn);
		return ecp;

	}
}
