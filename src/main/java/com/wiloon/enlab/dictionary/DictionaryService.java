package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.EnInfo;
import com.wiloon.enlab.domain.LogType;

import java.util.List;

/**
 * Created by wiloon on 12/18/14.
 */
public interface DictionaryService {
    public EnInfo searchDic(String english);
    public ECP searchYD(String word);
    public ECP ecpFormater(ECP ecp);
    public void ecpCountUpdate(ECP ecp);
    public void updateWord(ECP ecp);
    public void insertLog(ECP ecp, LogType logType);
    public EnInfo getWordById(ECP ecp);
    public ECP readWordByEnglish(String english);

    List<ECP> selectTop10(String selectTop10, ECP ecp);

    void insert(ECP ecp);
}
