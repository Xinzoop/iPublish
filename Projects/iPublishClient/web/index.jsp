<%-- 
    Document   : index
    Created on : Mar 20, 2014, 1:11:27 PM
    Author     : zipv5_000
--%>

<%@page import="com.iPublishClient.Service.CoreconfClient"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iPublish</title>
        <link rel="stylesheet" href="css/basic.css" type="text/css">        
        <script src="javascript/jquery-2.1.0.js"></script>
        <script src="javascript/basic.js"></script>
    </head>
    <body>

        <%
            String query = "";
            if (request.getSession().getAttribute("query") != null) {
                query = request.getSession().getAttribute("query").toString();
            }
            String rank = "ALL";
            if (request.getSession().getAttribute("rank") != null) {
                rank = request.getSession().getAttribute("rank").toString();
            }
            CoreconfClient client = new CoreconfClient();
            String[] rankAll = client.findRank().split(",");
        %>

        <div id="header">
            <div id="sitename">
                <p><a href="index.jsp"><img src="images/logo.png" alt="iPublish" height="50"></a></p>
            </div>
            <div id="search">
                <form action="CoreconfServlet" >
                        <select name="rank" id="rank">
                            <%
                                String tag = "<option";
                                if (rank.equals("ALL")) {
                                    tag += " selected=\"selected\"";
                                }
                                tag += ">ALL</option>";
                                out.println(tag);
                                for (String seg : rankAll) {
                                    tag = "<option";
                                    if (rank.equals(seg)) {
                                        tag += " selected=\"selected\"";
                                    }
                                    tag += ">" + seg + "</option>";
                                    out.println(tag);
                                }
                            %>
                        </select>
                    <input type="text" id="query" name="query" value="<%= query%>">
                    <input type="submit" id="btnSearch" value="Search">
                </form>
            </div>
        </div>

        <script src="javascript/basic.js" type="text/javascript"></script>
    </body>
</html>