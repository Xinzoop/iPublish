/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iPublishClient.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.saas.RestConnection;

/**
 *
 * @author zipv5_000
 */
public class QueryResponseTest {

    public QueryResponseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void Connect() throws IOException {
        String[][] params = new String[][]{};
        String[][] paths = new String[][]{};
        String URL = "http://www.iswc.net/";
        RestConnection conn = new RestConnection(URL, paths, params);
        String content = conn.get().getDataAsString();
        content = html2Text(content);
        Pattern pattern = Pattern.compile("will\\s*be\\s*held\\s*([^\\.]*)");
        Matcher matcher = pattern.matcher(content);
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
    /**

     * html转化为text

     * @param inputString
     * @return
     */
    public String html2Text(String inputString) {
          String htmlStr = inputString; // 含html标签的字符串
          String textStr = "";
          java.util.regex.Pattern p_script;
          java.util.regex.Matcher m_script;
          java.util.regex.Pattern p_style;
          java.util.regex.Matcher m_style;
          java.util.regex.Pattern p_html;
          java.util.regex.Matcher m_html;
          try {
           String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script>]*?>[\s\S]*?<\/script>
           // }
           String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style>]*?>[\s\S]*?<\/style>
           // }
           String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

           p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
           m_script = p_script.matcher(htmlStr);
           htmlStr = m_script.replaceAll(""); // 过滤script标签
 
           p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
           m_style = p_style.matcher(htmlStr);
           htmlStr = m_style.replaceAll(""); // 过滤style标签
           p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
           m_html = p_html.matcher(htmlStr);
           htmlStr = m_html.replaceAll(""); // 过滤html标签

           textStr = htmlStr;
          } catch (Exception e) {
           System.err.println("Html2Text: " + e.getMessage());
          }
          return textStr;
        }
 @Test
 public void TestSplit(){
     String raw = " sfs      eete ";
     String[] segments = raw.split(" ");
     for(String seg : segments){
         System.out.println(seg);
     }
 }
}
