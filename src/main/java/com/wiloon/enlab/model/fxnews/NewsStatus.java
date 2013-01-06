package com.wiloon.enlab.model.fxnews;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/16/12
 * Time: 10:19 AM
 */
public class NewsStatus extends ActionSupport {
    private final Logger logger = Logger.getLogger(NewsStatus.class);

    FxNewsHelper fxNewsHelper;
    boolean newsUpdated;

    public String isUpdated() throws Exception {
        logger.info("******************** isUpdated *******************");
        logger.info("fx news helper obj: " + fxNewsHelper.toString());
        newsUpdated = fxNewsHelper.isNewsUpdated();
        return "success";
    }

    public void setFxNewsHelper(FxNewsHelper fxNewsHelper) {
        this.fxNewsHelper = fxNewsHelper;
    }

    public boolean isNewsUpdated() {
        return newsUpdated;
    }

}
