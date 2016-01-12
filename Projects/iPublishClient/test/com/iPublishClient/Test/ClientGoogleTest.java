/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.Test;

import com.iPublishClient.Service.GoogleSearch;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zipv5_000
 */
public class ClientGoogleTest {
    
    GoogleSearch client = null;
    
    public ClientGoogleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        client = new GoogleSearch();
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
    public void Search(){
        try {
            String result = client.SearchResponse("flower", "", true);
            System.out.println(result);
        } catch (Exception ex) {
            Logger.getLogger(ClientGoogleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void SearchHTTP(){
//        try {
//            String result = client.SearchHTTP("Org");
//        } catch (Exception ex) {
//            Logger.getLogger(ClientGoogleTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
}
