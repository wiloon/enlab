package com.wiloon.enlab.model.en.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.EnInfo;
import com.wiloon.enlab.domain.LogType;
import com.wiloon.enlab.model.en.service.IEnService;

/**
 * 
 * @author wiloon
 * @author www.wiloon.com
 * @version 2.0
 * 
 *          <B>test</B>
 * @see com.wiloon.enlab.core.dao.impl.GenericDao
 * 
 */
public class EnAction extends ActionSupport {

    private static final long serialVersionUID = -4292115631703244026L;
    private IEnService enService;
    private final Logger logger = Logger.getLogger(EnAction.class);
    private EnInfo enInfo;
    private ECP ecp;
    private String strKey;
    private String message;

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute() ;insert new word
     * into DB; count ++ for existing word;
     */
    public String execute() {

	return SUCCESS;
    }

    /**
     * 
     * @return string success
     * @throws Exception
     * 
     */
    public String addWord() throws Exception {
	logger.info("word not exist in dic, insert the word into DB.");
	// format before insert.
	ecp = enService.ecpFormater(ecp);
	// insert into DB
	enService.insert("insertECP", ecp);
	message = "Insert... " + ecp.getEnglish();
	logger.info("insert.." + ecp.getEnglish() + " " + ecp.getChinese() + " "
		+ ecp.getPronunciation());
	// get word id , insert into log table.
	List<ECP> lstEn = enService.getList("selectECP", ecp);
	enService.insertLog(lstEn.get(0), LogType.Insert);
	enInfo = enService.searchDic(ecp);
	return SUCCESS;
    }

    // search the word in dict according to the character user input
    public String searchDic() throws Exception {
	logger.info("#######################search##########################");
	logger.info("search word: " + ecp.getEnglish());
	enInfo = enService.searchDic(ecp);
	logger.info("result size: " + enInfo.getLstEcp().size());

	message = "search successfully.";
	return SUCCESS;
    }

    public String enUpdate() throws Exception {
	logger.info("############################update###################");
	if (logger.isDebugEnabled()) {
	    logger.debug("enUpdate() - start");
	}

	logger.info("udpate word, ID:" + ecp.getID() + "; EN:" + ecp.getEnglish() + "; CN:"
		+ ecp.getChinese() + "; Pron:" + ecp.getPronunciation());

	if (ecp.getID() < 0) {
	    message = "id < 0 udpate error";

	    if (logger.isDebugEnabled()) {
		logger.debug("enUpdate() - end");
	    }
	    return SUCCESS;
	}

	// ecp formatter
	ecp = enService.ecpFormater(ecp);
	enService.ecpCountUpdate(ecp);
	// update the word
	enService.updateWord(ecp);
	message = "update... " + ecp.getEnglish();
	enService.insertLog(ecp, LogType.Update);
	enService.insertLog(ecp, LogType.UpdateCount);
	// refresh EN info
	enInfo = enService.searchDic(ecp);

	if (logger.isDebugEnabled()) {
	    logger.debug("enUpdate() - end");
	}
	return SUCCESS;
    }

    public String updateCount() {
	enService.ecpCountUpdate(ecp);
	logger.info("update count... " + ecp.getCount() + " " + ecp.getID());
	enInfo = enService.getWordById(ecp);
	enService.insertLog(ecp, LogType.UpdateCount);
	enInfo.setLstTop10(enService.getList("selectTop10", ecp));
	return SUCCESS;
    }

    // get/set

    public ECP getEcp() {
	return ecp;
    }

    public void setEnService(IEnService enService) {
	this.enService = enService;
    }

    public void setEcp(ECP ecp) {
	this.ecp = ecp;
    }

    public String getStrKey() {
	return strKey;
    }

    public void setStrKey(String strKey) {
	this.strKey = strKey;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public EnInfo getEnInfo() {
	return enInfo;
    }

    public void setEnInfo(EnInfo enInfo) {
	this.enInfo = enInfo;
    }

}
