/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublishClient.iPublishServlet;

import com.aliasi.classify.ConditionalClassification;
import com.aliasi.classify.LMClassifier;
import com.aliasi.util.AbstractExternalizable;
import com.iPublishClient.Entities.Coreconf;
import com.iPublishClient.Entities.GoogleItem;
import com.iPublishClient.Entities.GoogleResponse;
import com.iPublishClient.Entities.TwitterItem;
import com.iPublishClient.Entities.TwitterResponse;
import com.iPublishClient.Service.CoreconfUtility;
import com.iPublishClient.Service.GoogleSearch;
import com.iPublishClient.Service.TwitterClient;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zipv5_000
 */
@WebServlet(name = "GoogleTwitterServlet", urlPatterns = {"/GoogleTwitterServlet"})
public class GoogleTwitterServlet extends HttpServlet {

    private final GoogleSearch googleAPI;
    private final TwitterClient twitterClient;
    public GoogleTwitterServlet() {
        googleAPI = new GoogleSearch();
        twitterClient = new TwitterClient();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            List<Coreconf> results = (List<Coreconf>)session.getAttribute("results");
            Coreconf conf = null;
            if(results.size() == 1){
                conf = results.get(0);
            }
            else{
                conf = results.get(Integer.parseInt(request.getParameter("seq").toString()));
            }
            
            conf.setYear(session.getAttribute("year").toString());
            consumeGoogleSearch(conf);
            consumeTwitterAPI(conf);
            request.setAttribute("conf", conf);
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    private void consumeGoogleSearch(Coreconf conf) throws Exception {
//            conf.setConfDescription("The 18th ACM Conference on Computer-Supported Cooperative Work and Social Computing (CSCW ... Feb 19, 2014: CSCW 2015 CfP and website launched!");
//            conf.setConfLink("http://cscw.acm.org/");
//            conf.getConfProperties().put("googleTitle", "CSCW 2015");  
//            conf.setYear("2015");
//            conf.setVenue("CSCW 2015 will take place at the Westin Bayshore hotel in beautiful and vibrant Vancouver, BC, Canada. Greater Vancouver lies along the west coast of ...");
//            conf.getConfProperties().put("venueImage", "http://cscw.acm.org/2015/images/BCPlacewebcamofFalseCreek.jpg");
            
        GoogleResponse googleRes = googleAPI.Search(CoreconfUtility.getGoogleQueryString(conf.getAcronym()) + " " + conf.getYear(), "", false);
        if (null != googleRes && googleRes.getItems() != null && googleRes.getItems().size() > 0) {
            GoogleItem item = googleRes.getItems().get(0);
            conf.setConfDescription(item.getSnippet());
            conf.setConfLink(item.getLink());
            conf.getConfProperties().put("googleTitle", item.getTitle());  
            conf.setYear(CoreconfUtility.getYearInfo(item.getTitle(), conf.getYear()));
            getConfVenue(conf);
        }
    }
    
    private void getConfVenue(Coreconf conf) throws IOException{
        String[] searchCond = {"be held at", "take place at", "be held in"};
        String searchVenue = "";
        for(String cond : searchCond){
            if(!searchVenue.isEmpty()){
                searchVenue += " OR ";
            }
            searchVenue += "\"" + cond + "\"";
        }
        searchVenue = conf.getAcronym() + " " + conf.getYear() + " will " + searchVenue;
        GoogleResponse googleRes = googleAPI.Search(searchVenue, conf.getConfLink(), false);
        if (null == googleRes || googleRes.getItems() == null || googleRes.getItems().isEmpty()) {
            googleRes = googleAPI.Search(searchVenue, "", false);
        }
        if (null != googleRes && googleRes.getItems() != null && googleRes.getItems().size() > 0) {
            GoogleItem item = googleRes.getItems().get(0);
            String snippet = item.getSnippet();
            conf.setVenue(snippet);

            for(String cond : searchCond){
                int index = snippet.indexOf(cond);
                if(index < 0)
                    continue;
                index += cond.length();
                int endIndex = snippet.indexOf(",", index);
                if(endIndex > 0){
                    snippet = snippet.substring(index, endIndex);
                    break;
                }
                endIndex = snippet.indexOf(".", index);
                if(endIndex > 0){
                    snippet = snippet.substring(index, endIndex);
                    break;
                }
                snippet = snippet.substring(index);
            }
            
            conf.getConfProperties().put("venueLocation", snippet);
            getVenueImage(conf);
        }
    }
    private void getVenueImage(Coreconf conf) throws IOException {
        String location = conf.getConfProperties().get("venueLocation");
        if (!location.isEmpty()) {
            GoogleResponse googleRes = googleAPI.Search(location, "", true);
            if (null != googleRes && googleRes.getItems() != null && googleRes.getItems().size() > 0) {
                GoogleItem item = googleRes.getItems().get(0);
                conf.getConfProperties().put("venueImage", item.getLink());
                conf.getConfProperties().put("venueSnippet", item.getSnippet());
            }
        }
    }
    
    private void consumeTwitterAPI(Coreconf conf) throws Exception {
        conf.getPositiveTwitter().clear();
        conf.getNeutralTwitter().clear();
        conf.getNegativeTwitter().clear();
        List<TwitterItem> itemList = new ArrayList<>();
        String query = "#" + CoreconfUtility.getTwitterQueryString(conf.getAcronym()) + " " + conf.getYear() + " meeting OR conf";
        //String query = "a";
        String maxId = "";
        for (int i = 0; i < 1; i++) {
            TwitterResponse twitterRes = twitterClient.TwitterSearch(query, maxId);
            if (null == twitterRes || twitterRes.getStatuses() == null || twitterRes.getStatuses().isEmpty())
                break;
            itemList.addAll(twitterRes.getStatuses());
            maxId = Long.toString(twitterRes.getStatuses().get(twitterRes.getStatuses().size() - 1).getId() - 1);
        }
        if (itemList.isEmpty()) {
            query = CoreconfUtility.getTwitterQueryString(conf.getAcronym());
            maxId = "";
            for (int i = 0; i < 1; i++) {
                TwitterResponse twitterRes = twitterClient.TwitterSearch(query, maxId);
                if (null == twitterRes || twitterRes.getStatuses() == null || twitterRes.getStatuses().isEmpty()) {
                    break;
                }
                itemList.addAll(twitterRes.getStatuses());
                maxId = Long.toString(twitterRes.getStatuses().get(twitterRes.getStatuses().size() - 1).getId() - 1);
            }
        }
        
        LMClassifier classifier = (LMClassifier) AbstractExternalizable.readObject(new File(getServletContext().getRealPath("") + "\\WEB-INF\\classifier.txt"));
        for(TwitterItem item : itemList){
            ConditionalClassification classification = classifier.classify(item.getText());
            String result = classification.bestCategory();
            if (result.equalsIgnoreCase("pos")) {
                conf.getPositiveTwitter().add(item);
            } else if (result.equalsIgnoreCase("neg")) {
                conf.getNegativeTwitter().add(item);
            } else {
                conf.getNeutralTwitter().add(item);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
