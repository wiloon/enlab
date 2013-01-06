package com.wiloon.enlab.model.en.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.model.en.service.IWebDictService;

/**
 * 
 * @author wiloon
 * 
 * 
 */
public class YDAction extends ActionSupport {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(YDAction.class);

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
	return SUCCESS;
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
