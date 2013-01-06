package com.wiloon.enlab.model;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.wiloon.enlab.core.service.impl.GenericService;
import com.wiloon.enlab.domain.ECP;



public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4292115631703244026L;
	private GenericService<ECP, String> genericService;
	private final Logger log = Logger.getLogger(TestAction.class);

	public String execute() {
		log.info("en action");
		ECP ecp = new ECP();
		try {
			List<ECP> list = genericService.getList("selectECP", ecp);
			log.info(list.size());

			// genericService.insert("insertECP", ecp);
			genericService.update("updateEcp", ecp);
		} catch (Exception e) {
			log.info(e);
		}

		return SUCCESS;
	}

	public void setGenericService(GenericService<ECP, String> genericService) {
		this.genericService = genericService;
	}

}
