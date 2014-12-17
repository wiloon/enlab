package com.wiloon.enlab.model.en.action;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.model.en.service.IWebDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wiloon
 * 
 * 
 */
public class YDAction  {
    /**
     * Logger for this class
     */
    private static Logger logger = LoggerFactory.getLogger(YDAction.class);

    /**
     * 
     */
    private static final long serialVersionUID = 2281937234650865015L;
    private ECP ecp;
    IWebDictService ydService;

    public String execute() {
	if (logger.isInfoEnabled()) {
	    logger.info("#######################search you dao action start..."); //$NON-NLS-1$
	}

	ecp = ydService.translate(ecp.getEnglish());
	return "";
    }

    public ECP getEcp() {
	return ecp;
    }

    public void setEcp(ECP ecp) {
	this.ecp = ecp;
    }

    public IWebDictService getYdService() {
	return ydService;
    }

    public void setYdService(IWebDictService ydService) {
	this.ydService = ydService;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

}
