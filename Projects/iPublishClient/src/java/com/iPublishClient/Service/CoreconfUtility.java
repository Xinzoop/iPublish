/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iPublishClient.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zipv5_000
 */
public class CoreconfUtility {

    public static String getGoogleQueryString(String source) {
        return source.replaceAll("/", " OR ");
    }
    public static String getTwitterQueryString(String source) {
        return source.replaceAll("/", " OR ");
    }

    public static String getYearInfo(String source, String defYear) {
        String year = "";
        Pattern pattern = Pattern.compile("([^\\d]*)([\\d]+)([^\\d]*)");
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return matcher.group(2);
        }    
        return defYear;
    }
}
