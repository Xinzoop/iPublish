/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iPublishClient.iPublishServlet;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.iPublishClient.Entities.*;
import com.iPublishClient.Service.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zipv5_000
 */
@WebServlet(name = "iPublishServlet", urlPatterns = {"/CoreconfServlet"})
public class CoreconfServlet extends HttpServlet {

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
        List<Coreconf> results = null;
        HttpSession session = request.getSession();
        String query = request.getParameter("query");
        String rank = request.getParameter("rank");
        session.setAttribute("query", query);
        session.setAttribute("rank", rank);
        String year = "";
        if (query.isEmpty()) {
            query = "*";
        } else {
            year = CoreconfUtility.getYearInfo(query, "");
        }

        if (!year.isEmpty()){
            query = query.replaceAll("\\d+", "");
        }
        else{
            year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        }

        try {
            CoreconfClient client = new CoreconfClient();
            String json = client.search_JSON(String.class, URLEncoder.encode(rank, "UTF-8"), URLEncoder.encode(query, "UTF-8"));
            results = new Gson().fromJson(json, new TypeToken<List<Coreconf>>() {
            }.getType());
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        if (null == results || results.isEmpty()) {
            request.setAttribute("message", "No related records for [" + query + " of Rank " + rank + "].");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        session.setAttribute("year", year);
        session.setAttribute("results", results);
        if (results.size() == 1) {
            getServletContext().getRequestDispatcher("/GoogleTwitterServlet").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/results.jsp").forward(request, response);
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
