package com.wiloon.enlab.model.fxnews;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/5/12
 * Time: 10:14 PM
 */
public class FxNews extends ActionSupport {
    private final Logger logger = Logger.getLogger(FxNews.class);

    List<NewsBean> news;
    FxNewsHelper fxNewsHelper;

    public String fetch() {
        news = fxNewsHelper.getNewsIncremental();
        return SUCCESS;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setFxNewsHelper(FxNewsHelper fxNewsHelper) {
        this.fxNewsHelper = fxNewsHelper;
    }

    public String fetchAllNews() throws Exception {
        logger.info("fx news action.get all news");
        news = fxNewsHelper.getAllNews();
        return "success";
    }
}
