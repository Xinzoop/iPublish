<%-- 
    Document   : home
    Created on : Mar 21, 2014, 9:17:07 PM
    Author     : zipv5_000
--%>

<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.jfree.chart.labels.PieSectionLabelGenerator"%>
<%@page import="java.awt.Color"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.iPublishClient.Entities.*" %>
<%@page import="java.io.File"%>
<%@page import="org.jfree.chart.entity.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.data.general.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="index.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        
    </head>
    <body>
        <%            Coreconf conf = (Coreconf) request.getAttribute("conf");
            if (null != conf) {
                List<List<TwitterItem>> twitterRes = new ArrayList<List<TwitterItem>>();
                    twitterRes.add(conf.getPositiveTwitter());
                    twitterRes.add(conf.getNeutralTwitter());
                    twitterRes.add(conf.getNegativeTwitter());
                    String key1 = "Positive";
                    String key2 = "Neutral";
                    String key3 = "Negative";
                    final DefaultPieDataset data = new DefaultPieDataset();
                    data.setValue(key1, new Double(twitterRes.get(0).size()));
                    data.setValue(key2, new Double(twitterRes.get(1).size()));
                    data.setValue(key3, new Double(twitterRes.get(2).size()));
                    JFreeChart chart = ChartFactory.createPieChart("Twitter Analysis ", data, true, true, false);
                    
                    PiePlot plot = (PiePlot) chart.getPlot();
                    plot.setExplodePercent(key1, 0.10);
                    plot.setExplodePercent(key2, 0.10);
                    plot.setSimpleLabels(true);
                    PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
                    plot.setLabelGenerator(gen);

                    try {
                        final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                        final File file1 = new File(application.getRealPath("") + "\\piechart.png");
                        ChartUtilities.saveChartAsPNG(file1, chart, 400, 400, info);
                    } catch (Exception e) {
                        out.println(e.getMessage());
                    }
        %>      
        <div class="content">
            <div id="conf-info">
                <p><span>Rank : <%= conf.getRank() %></span> | <span>FoR: <%= conf.getForcode() %></span></p>
            </div>
            <div id="detail">
                <h1 id="googleTitle"><%= conf.getConfProperties().get("googleTitle") %></h1>
                <p><%= conf.getConfDescription() %></p>
                <a href="<%= conf.getConfLink()%>"><%= conf.getConfLink() %></a>
            </div>
            <div id="venue">
                <h2>Venue</h2>
                <div id="venue-img"><img src="<%= conf.getConfProperties().get("venueImage") %>" width="600" height="300"></div>
                <p><%= conf.getVenue() %></p>
            </div>
            <div id="twitter">
                    <h2>Recent Twitter Reviews</h2>
                    <div id="twitter-review">
                        <%
                            for(int index=0; index<3; ++index){
                                String tagStatement = "<div class=\"review";
                                if(index == 0){
                                    tagStatement += " cur-review";
                                }
                                tagStatement += "\">";
                                out.println(tagStatement);    
                                tagStatement = "<p class=\"review-banner\">";
                                if(index == 0){
                                    tagStatement += "Positive";
                                }
                                else if(index == 1){
                                    tagStatement += "Neutral";
                                }
                                else{
                                    tagStatement += "Negative";
                                }
                                List<TwitterItem> classItem = twitterRes.get(index);
                                int count = classItem.size();
                                if(count > 3)
                                    count = 3;
                                tagStatement += " (first " + count  + " of " + classItem.size() + ")</p>";
                                out.println(tagStatement);
                                out.println("<ul>");
                                for (int i = 0; i < count; ++i) {
                                        TwitterItem item = classItem.get(i);
                                        out.println("<li><table>");
                                        out.println("<td class=\"profile\"><img src=\"" + item.getUser().getProfile_image_url_https() + "\" alt=\"\"></td>");
                                        //out.println("<td class=\"profile\"><img src=\"#\" width=\"48\" height=\"48\" alt=\"\"></td>");
                                        out.println("<td class=\"review-content\">");
                                        out.println("<p class=\"author\"><strong>" + item.getUser().getName() + "</strong> <span class=\"screen-name\">@"
                                                + item.getUser().getScreen_name() + "</span> <small class=\"timestamp\"> - "
                                                + item.getCreated_at().substring(0, 19) + "</small></p>");
                                        out.println("<p class=\"text\">" + item.getText() + "</p>");
                                        out.println("</td>");
                                        out.println("</table></li>");
                                    }
                                out.println("</ul>");
                                out.println("</div>");
                            }
                        %>
                    </div>
                    <div id="twitterimg">
                        <IMG SRC="piechart.png" WIDTH="300" HEIGHT="300" alt="pie">
                    </div>
                    <div style="clear:both;"></div>
            </div>
        </div>
        <%
            }
        %>
    </body>
</html>
