package com.wiloon.enlab.model.fxnews;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/16/12
 * Time: 5:26 PM
 */
public class CompareNews implements Comparator {
    public int compare(Object arg0, Object arg1) {
        NewsBean news0 = (NewsBean) arg0;
        NewsBean news1 = (NewsBean) arg1;
        int flag = news0.getTimeStamp().compareTo(news1.getTimeStamp());
        return flag;
    }
}
