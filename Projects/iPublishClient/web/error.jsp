<%-- 
    Document   : error
    Created on : Mar 21, 2014, 9:40:40 PM
    Author     : zipv5_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="index.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="content">
            <p>Error: <%= request.getAttribute("message") %></p>
        </div>
    </body>
</html>
