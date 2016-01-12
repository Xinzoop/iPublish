<%-- 
    Document   : results
    Created on : Mar 23, 2014, 1:49:44 AM
    Author     : zipv5_000
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="index.jsp" %>
<%@page import="com.iPublishClient.Entities.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%            List<Coreconf> results = (List<Coreconf>) request.getSession().getAttribute("results");
            if (results != null) {
        %>
        <div class="content">
            <ol id="results">
                <%
                    for (int i = 0; i < results.size(); ++i) {
                        Coreconf conf = results.get(i);
                        String href = "GoogleTwitterServlet?seq=" + Integer.toString(i);
                        String title = conf.getAcronym() + " - " + conf.getConfTitle();
                        out.println("<li>");
                        String statement = "<a href=\"" + href + "\">" + title + "</a>";
                        out.println(statement);
                        statement = "<p class=\"result-rank\">Rank: " + conf.getRank() + "</p>";
                        out.println(statement);
                        statement = "<p class=\"result-forcode\">Research Field: " + conf.getForcode() + "</p>";
                        out.println(statement);
                        out.println("</li>");
                    }
                %>
            </ol>
        </div>
        <%
            }
        %>
    </body>
</html>
