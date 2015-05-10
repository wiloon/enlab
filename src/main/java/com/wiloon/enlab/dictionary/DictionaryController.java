package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.ECP;
import com.wiloon.enlab.domain.EnInfo;
import com.wiloon.enlab.domain.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wiloon on 12/17/14.
 */

@Controller
public class DictionaryController {
    private static Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    //search
    @RequestMapping(value = "search")
    @ResponseBody
    public EnInfo search(@RequestParam(value = "word") String word) {
        logger.debug("request word=" + word);
        ECP ecp = new ECP();
        ecp.setEnglish(word);
        EnInfo enInfo = dictionaryService.searchDic(word);
        return enInfo;
    }

    @RequestMapping(value = "searchYD")
    @ResponseBody
    public ECP searchYD(@RequestParam(value = "word") String word) {
        ECP ecp = dictionaryService.searchYD(word);
        return ecp;
    }

    public DictionaryService getDictionaryService() {
        return dictionaryService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    //update word
    @RequestMapping(value = "enUpdateAction")
    @ResponseBody
    public EnInfo enUpdate(@RequestParam(value = "ID") String id,
                           @RequestParam(value = "english") String english,
                           @RequestParam(value = "chinese") String chinese,
                           @RequestParam(value = "pronunciation") String pronunciation,
                           @RequestParam(value = "count") String count) {
        logger.info("udpate word, ID:" + id + "; EN:" + english + "; CN:"
                + chinese + "; Pron:" + pronunciation);

        ECP ecp = new ECP();
        ecp.setChinese(chinese);
        ecp.setCount(count);
        ecp.setEnglish(english);
        ecp.setID(id);
        ecp.setPronunciation(pronunciation);

        if (Integer.parseInt(id) < 0) {
            //message = "id < 0 udpate error";

            if (logger.isDebugEnabled()) {
                logger.debug("enUpdate() - end");
            }
            return null;
        }

        // ecp formatter
        ecp = dictionaryService.ecpFormater(ecp);
        dictionaryService.ecpCountUpdate(ecp);
        // update the word
        dictionaryService.updateWord(ecp);
        // message = "update... " + ecp.getEnglish();
        dictionaryService.insertLog(ecp, LogType.Update);
        dictionaryService.insertLog(ecp, LogType.UpdateCount);
        // refresh EN info
        EnInfo enInfo = dictionaryService.searchDic(ecp.getEnglish());

        if (logger.isDebugEnabled()) {
            logger.debug("enUpdate() - end");
        }
        return enInfo;
    }

    //update count
    @RequestMapping(value = "updateCountAction")
    @ResponseBody
    public EnInfo updateCount(@RequestParam(value = "ID") String id, @RequestParam(value = "count") String count) {
        EnInfo enInfo = null;
        try {
            ECP ecp = new ECP();
            ecp.setID(id);
            ecp.setCount(count);
            dictionaryService.ecpCountUpdate(ecp);
            logger.info("update count," + ecp.getId()+" "+ecp.getEnglish()+" "+ ecp.getCount());
            enInfo = dictionaryService.getWordById(ecp);
            dictionaryService.insertLog(ecp, LogType.UpdateCount);
            enInfo.setLstTop10(dictionaryService.selectTop10("selectTop10", ecp));
        } catch (Exception e) {
            logger.error("failed to update count.");
            e.printStackTrace();
        }
        return enInfo;
    }

    @RequestMapping(value = "addWordAction")
    @ResponseBody
    public EnInfo addWord(@RequestParam(value = "english") String english,
                          @RequestParam(value = "chinese") String chinese,
                          @RequestParam(value = "pronunciation") String pronunciation)                         {
        EnInfo enInfo = null;
        try {
            ECP ecp = new ECP();
            ecp.setChinese(chinese);
            ecp.setEnglish(english);
            ecp.setPronunciation(pronunciation);

            enInfo = new EnInfo();
            logger.info("word not exist in dic, insert the word into DB.");
            // format before insert.
            ecp = dictionaryService.ecpFormater(ecp);
            // insert into DB
            dictionaryService.insert(ecp);
            enInfo.setMessage("Insert... " + ecp.getEnglish());
            logger.info("insert;" + ecp );
            // get word id , insert into log table.
ecp = dictionaryService.readWordByEnglish(ecp.getEnglish());
            dictionaryService.insertLog(ecp, LogType.Insert);
            enInfo = dictionaryService.searchDic(ecp.getEnglish());
        } catch (Exception e) {
            logger.error("failed to add word.");
            e.printStackTrace();
        }

        return enInfo;
    }
}
