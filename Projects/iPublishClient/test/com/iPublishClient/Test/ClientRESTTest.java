/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.Test;

import com.iPublishClient.Service.CoreconfClient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author zipv5_000
 */
public class ClientRESTTest {
    
    CoreconfClient client = null;
    
    public ClientRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        client = new CoreconfClient();
    }
    
    @After
    public void tearDown() {
        client.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void findTitle(){
        String json = client.search_JSON(String.class, "A*","org");
        System.out.println(json);
    }
    @Test
    public void findRank() throws Exception{
        String rankAll = client.findRank();
        System.out.println(rankAll);
    }
    
    @Test
    public void TestRegex(){
        String raw = "a2014  b 2015c";
        String regex = "([^\\d]*)([\\d]+)([^\\d]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(raw);
        String year = "";
        while(matcher.find())
        {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        }
        System.out.println(raw.replaceAll("[\\d+]", ""));
    }
}
