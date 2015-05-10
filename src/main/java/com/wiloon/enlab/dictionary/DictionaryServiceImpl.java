package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.ECPLog;
import com.wiloon.enlab.domain.EnInfo;
import com.wiloon.enlab.domain.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by wiloon on 12/18/14.
 */

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private static Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;


    @Override
    public EnInfo searchDic(String english) {
        logger.info("search dic key: " + english);
        ECP ecp = new ECP();
        ecp.setEnglish(english.toLowerCase());
        EnInfo enInfo = new EnInfo();
        enInfo.setLstEcp(dictionaryMapper.selectEnRagne(ecp));
        enInfo.setLstTop10(dictionaryMapper.getTop10(english));
        enInfo.setWordCount(dictionaryMapper.getWordCount());
        enInfo.setWordCountToday(dictionaryMapper.getWordCountToday());
        enInfo.setCountTodayUpdate(dictionaryMapper.getWordCountTodayUpdate());
        logger.info("word num total: " + enInfo.getWordCount() + "; today: "
                + enInfo.getWordCountToday());
        // TODO refactor: for each action , do not return the unused data,...
        // return whole enInfo is a bad practice
        return enInfo;
    }

    public ECP searchYD(String english) {
        logger.info("search you dao dict: " + english);
        ECP ecp = new ECP();
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
            logger.debug("result:" + rtn);

        ecp.setChinese(rtn);
        logger.debug("ecp="+ecp);
        return ecp;

    }

    @Override
    public ECP ecpFormater(ECP ecp) {

        // replace ' to ''
        ecp.setEnglish(ecp.getEnglish().replace("'", "''"));

        ecp.setChinese(ecp.getChinese().trim());
        String chinese = ecp.getChinese();
        if (null != chinese && !chinese.trim().isEmpty()) {
            chinese = chinese.replace("1.", "");
            for (int i = 2; i < 5; i++) {

                chinese = chinese.replace(i + ".", ",");
            }
            // chinese = chinese.replace(" ", "");
            chinese = chinese.replace("【", "[");
            chinese = chinese.replace("】", "]");
            chinese = chinese.replace(";", ",");
            chinese = chinese.replace("；", ",");
            chinese = chinese.replace("，", ",");
            ecp.setChinese(chinese);
        }
        String pronunciation;
        pronunciation = ecp.getPronunciation();
        if (null == pronunciation || pronunciation.trim().isEmpty()) {
            // do nothing
        } else {
            pronunciation = pronunciation.replace(" ", "");
            pronunciation = pronunciation.replace("*", "");
            pronunciation = pronunciation.replace("[", "");
            pronunciation = pronunciation.replace("]", "");
            pronunciation = pronunciation.replace("/", "");
            pronunciation = pronunciation.replace("\\", "");
            // pronunciation = pronunciation.replace(",", "");
            // pronunciation = splitPronunciation(pronunciation, ";");
            // pronunciation = splitPronunciation(pronunciation, ",");
            pronunciation = "/" + pronunciation + "/";
            ecp.setPronunciation(pronunciation.trim());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("ecpFormater(ECP) - end");
        }
        return ecp;
    }
    public DictionaryMapper getDictionaryMapper() {
        return dictionaryMapper;
    }

    public void setDictionaryMapper(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    @Override
    public void ecpCountUpdate(ECP ecp) {
        ecp.setCount(ecp.getCount() + 1);
        dictionaryMapper.updateEcpCount(ecp);
    }

    @Override
    public void updateWord(ECP ecp) {
        logger.info("before update" + ecp.getCount());

        dictionaryMapper.updateEcp(ecp);
        logger.info("update count ..." + ecp.getCount());
    }
    @Override
    public void insertLog(ECP ecp, LogType logType) {
        ECPLog log = new ECPLog(ecp.getId(), logType);
        logger.debug("insert log;"+log);
        dictionaryMapper.insertLog(log);
    }

    @Override
    @Deprecated
    public EnInfo getWordById(ECP ecp) {
        logger.debug("get word by id, id="+ecp.getId());
        EnInfo enInfo = new EnInfo();
        enInfo.setEcp(dictionaryMapper.readWordByID(ecp.getId()));
        enInfo.setMessage("update count... " + enInfo.getEcp().getCount());
        return enInfo;

    }

    @Override
    public ECP readWordByEnglish(String english) {
        return dictionaryMapper.getWordByEnglish(english);
    }

    @Override
    public List<ECP> selectTop10(String selectTop10, ECP ecp) {
        return null;
    }

    @Override
    public void insert(ECP ecp) {
        dictionaryMapper.insertWord(ecp);
    }
}
