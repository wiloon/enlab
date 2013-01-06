package com.wiloon.enlab.utils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 9/9/12
 * Time: 9:55 PM
 */
public class Utils {
    public static String streamReader(InputStream in, String encoding) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
