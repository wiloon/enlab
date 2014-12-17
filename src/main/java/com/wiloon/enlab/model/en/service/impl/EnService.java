package com.wiloon.enlab.model.en.service.impl;

import java.util.List;



import com.wiloon.enlab.core.dao.IGenericDao;
import com.wiloon.enlab.core.service.impl.GenericService;
import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.EnInfo;
import com.wiloon.enlab.domain.ECPLog;
import com.wiloon.enlab.domain.LogType;
import com.wiloon.enlab.model.en.service.IEnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnService extends GenericService<ECP, Long> implements IEnService {
    /**
     * Logger for this class
     */
    private static Logger logger = LoggerFactory.getLogger(EnService.class);
    private IGenericDao<ECPLog, Long> logDao;

    public EnService(IGenericDao<ECP, Long> genericDao, IGenericDao<ECPLog, Long> logDao) {
	super(genericDao);
	this.logDao = logDao;
    }

    public void ecpCountUpdate(ECP ecp) {
	ecp.setCount(ecp.getCount() + 1);
	genericDao.update("updateEcpCount", ecp);
    }

    @Override
    public void updateWord(ECP ecp) {
	logger.info("before update" + ecp.getCount());

	genericDao.update("updateEcp", ecp);
	logger.info("update count ..." + ecp.getCount());
    }

    /**
     * @param ecp
     *            only the field english is available, for fetching the word.
     * @return enInfo which include all data that displayed on page.
     *         {@link com.wiloon.enlab.domain.EnInfo EnInfo}
     * @throws Exception
     *             throw exception test
     */
    @Override
    public EnInfo searchDic(ECP ecp) throws Exception {
	logger.info("search dic key: " + ecp.getEnglish());
	EnInfo enInfo = new EnInfo();
	enInfo.setLstEcp(genericDao.getList("selectEnRagne", ecp));
	enInfo.setLstTop10(genericDao.getList("selectTop10", ecp));
	enInfo.setWordCount(genericDao.getAInt("selectWordCount"));
	enInfo.setWordCountToday(genericDao.getAInt("selectWordCountToday"));
	enInfo.setCountTodayUpdate(genericDao.getAInt("selectWordCountTodayUpdate"));
	logger.info("word num total: " + enInfo.getWordCount() + "; today: "
		+ enInfo.getWordCountToday());
	// TODO refactor: for each action , do not return the unused data,...
	// return whole enInfo is a bad practice
	return enInfo;
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

    public void insertLog(ECP ecp, LogType logType) {
	ECPLog log = new ECPLog(ecp.getID(), logType);
	logDao.insert("insertLog", log);
    }

    public List<ECP> getTop10() {
	return genericDao.getList("selectTop10", null);

    }

    @Override
    @Deprecated
    public EnInfo getWordById(ECP ecp) {
	EnInfo enInfo = new EnInfo();
	enInfo.setEcp(genericDao.getList("selectWordById", ecp).get(0));
	enInfo.setMessage("update count... " + enInfo.getEcp().getCount());
	return enInfo;

    }
}
