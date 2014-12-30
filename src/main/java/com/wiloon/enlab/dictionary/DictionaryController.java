package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.EnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wiloon on 12/17/14.
 */

@Controller
public class DictionaryController {
    private static Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "search")
    @ResponseBody
    public EnInfo search(@RequestParam(value = "word") String word) {
        logger.debug("request word=" + word);
        ECP ecp = new ECP();
        ecp.setEnglish(word);
        EnInfo enInfo=dictionaryService.searchDic(word);
        return enInfo;
    }


    public DictionaryService getDictionaryService() {
        return dictionaryService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
