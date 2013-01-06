package com.wiloon.enlab.model.en.service;

import java.util.List;

import com.wiloon.enlab.core.service.IGenericService;
import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.EnInfo;
import com.wiloon.enlab.domain.LogType;

public interface IEnService extends IGenericService<ECP, Long> {
	public void updateWord(ECP ecp);

	public EnInfo searchDic(ECP ecp) throws Exception;

	public ECP ecpFormater(ECP ecp);

	public void insertLog(ECP ecp, LogType logType);

	public List<ECP> getTop10();

	public void ecpCountUpdate(ECP ecp);

	public EnInfo getWordById(ECP ecp);
}
