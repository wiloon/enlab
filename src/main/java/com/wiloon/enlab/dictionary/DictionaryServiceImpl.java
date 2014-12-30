package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.EnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        EnInfo enInfo = new EnInfo();
        enInfo.setLstEcp(dictionaryMapper.selectEnRagne(english));
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

    public DictionaryMapper getDictionaryMapper() {
        return dictionaryMapper;
    }

    public void setDictionaryMapper(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }
}
