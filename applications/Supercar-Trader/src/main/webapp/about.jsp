<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ include file="header.jsp" %>
<%
    try {
        Random r = new Random();
        int i = r.nextInt(999);
        Thread.sleep(i);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<p class="normal"><!--  img src="images/line.gif" --></p>
<p class="car" align="left"><img src="images/about_car.gif"></p>
<p class="car" >
    Supercar Trader is published in 13 regional editions. To find details of your local edition, simply click on the links below or use the map to locate your local Supercar Trader magazine.
</p>
<p class="car">
    Launched in 2004, Supercar Trader is the biggest selling motoring magazine in Britain and is published across thirteen regional editions. More people buy and read Supercar Trader than any other motoring title and the magazine continues to be the market leader with a circulation of 351,654 (ABC Jan-June 2004) and a readership of 1,800,000 (NRS Jan-Dec 2003). 
</p>
<p class="car">
    Supercar Trader has positioned itself as the synonymous choice for buying and selling a motor vehicle with the success of the magazine built on the combination of photo-ads, choice and a unique structure of regional publishing, offering the combined advantages of local targeting and comprehensive national coverage.
</p>

<p class="webdesign">website design by<img src="images/web-site-design.png" title="james101"></p>

<!--  p style="color:#ffffff">
    <b>Leak Generation</b>
</p>
<p>
    To generate a memory leak, visit the <a href="leak.do">Leak Page</a>
</p -->
<%@ include file="footer.jsp" %>