package com.wiloon.enlab.model.fxnews;

import com.wiloon.enlab.utils.Utils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/15/12
 * Time: 10:24 PM
 */
public class FxNewsHelper {
    private final Logger logger = Logger.getLogger(FxNewsHelper.class);

    private List<NewsBean> news;
    private List<NewsBean> newsIncremental;

    private static String datePatternFx = "[MM月dd日 HH时mm分 ]";
    private static String datePattern = "MM/dd HH:mm";

    public FxNewsHelper() {
        news = new ArrayList<NewsBean>();
    }

    public boolean isNewsUpdated() {
        logger.info("is news updated start.");
        boolean updated = false;
        List<NewsBean> latest = getLatestNews();
        logger.info("latest size" + latest.size());

        newsIncremental = getIncremental(news, latest);
        logger.info("newsIncremental size" + newsIncremental.size());


        logger.info("news size" + news.size());

        if (newsIncremental.size() > 0) {
            updated = true;
            news = latest;
        }
        logger.info("is news updated:" + updated);
        return updated;
    }

    private boolean isEqual(List<NewsBean> news, List<NewsBean> latest) {
        boolean equal = false;
        String news0 = news.get(0).getContent();
        String latest0 = latest.get(0).getContent();
        if (news0 != null && latest0 != null) {
            if (news0.equals(latest0)) {
                equal = true;
            } else {
                equal = false;
            }
        }
        return equal;

    }

    public List<NewsBean> getLatestNews() {
        List<NewsBean> list = new ArrayList<NewsBean>();
        String strUrl = "http://www.fx678.com/fxnews/textBanner.xml?time=" + System.currentTimeMillis();
        strUrl = "http://www.fx678.com/news/flash/default.shtml";
        System.out.println(strUrl);
        String content;
        URL url = null;
        try {
            url = new URL(strUrl);
            logger.info("connecting " + strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            content = Utils.streamReader(conn.getInputStream(), "GBK");
            list = getNews(content);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CompareNews comparator = new CompareNews();
        Collections.sort(list, comparator);
        return list;
    }

    private List<NewsBean> getIncremental(List<NewsBean> original, List<NewsBean> latest) {
        List<NewsBean> listRtn = new ArrayList<NewsBean>();
        for (NewsBean objL : latest) {
            String titleL = objL.getTitle();
            boolean exist = false;
            for (NewsBean objO : original) {
                String titleO = objO.getTitle();
                if (titleL.equals(titleO)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                listRtn.add(objL);
            }
        }
        CompareNews comparator = new CompareNews();
        Collections.sort(listRtn, comparator);
        return listRtn;
    }

    private static List<NewsBean> getNews(String content) {
        List<NewsBean> list = new ArrayList();
        String pattern;
        pattern = "list_content01_titlel\"><a href=\"(.*?)\" target=\"_blank\">(.*?)</a>.*?list_content01_titler\">(.*?)</div>";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(content);
        int index = 0;
        int count = 0;
        boolean match = matcher.find(index);

        while (match) {
            NewsBean bean = new NewsBean();
            index = matcher.end();

            String url = matcher.group(1);
            //bean.setUrl(url);
            String title = matcher.group(2);

            title = title.replace("&nbsp;", "");
            bean.setTitle(title);
            String date = matcher.group(3);
            DateTime datetime = DateTimeFormat.forPattern(datePatternFx).parseDateTime(date);
            datetime = datetime.withYear(2012);
            bean.setTimeStamp(datetime.toString(datePattern));
            list.add(bean);
            match = matcher.find(index);
            count++;
        }
        return list;
    }

    public List<NewsBean> getAllNews() {
        logger.info("get all news start.");
        if (news == null || news.size() <= 0) {
            news = getLatestNews();
            logger.info("get all news. news size:" + news.size());
        }
        return news;
    }

    public List<NewsBean> getNews() {
        return news;
    }


    public List<NewsBean> getNewsIncremental() {
        return newsIncremental;
    }


}
