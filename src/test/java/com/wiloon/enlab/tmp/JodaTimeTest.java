package com.wiloon.enlab.tmp;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/16/12
 * Time: 5:00 PM
 */
public class JodaTimeTest {
    @Test
    public void fxDateTime() {
        //yyyy-MM-dd HH:mm:ss
        DateTime dt2 = DateTimeFormat.forPattern("[MM月dd日 HH时mm分 ]").parseDateTime("[09月14日 23时09分 ]");
        dt2 = dt2.withYear(2012);
        System.out.println(dt2.toString("yyyy/mm/dd"));

    }
}
