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
<p class="normal"></p>
<table>
    <tr>
        <!-- td><br/><img src="images/line.gif"></td -->
    </tr>
    <tr>
        <td><br/></td>
    </tr>
</table>
<table>
    <tr>
        <td class="home">
            <table>
                <tr>
                    <td><img src="images/home-title-01.gif" alt="New customers"><br />
                        &nbsp;<a href="" name="&lid=Register+Benefits&lpos=Left+Nav">Registration Benefits</a><br />
                        &nbsp;<a href="" name="&lid=Register&lpos=Left+Nav">Register here</a><br />
                        <br /><img src="images/home-title-02.gif" alt="Existing customers"><br />
                        &nbsp;<a href="" name="&lid=Log+In&lpos=Left+Nav">Log in</a><br />
                        &nbsp;<a href="" name="&lid=Log+Out&lpos=Left+Nav">Log out</a>
                    </td>
                </tr>
                <tr>
                    <td><br />
                        <form name="searchForm" action="search.do?query=search" method="POST">
                            <img src="images/home-title-03.gif" alt="Quick search"><br /><input type="text" name="criteria" />&nbsp;<br />
                            <input type="image" src="images/small_search_but.gif"/ align="absmiddle"><br />
                        </form>
                    </td>
                </tr>
                <tr>
                    <td><img src="images/home-title-04.gif" alt="Trade advertisers"><br />
                        &nbsp;<a href="" name="&lid=List&lpos=Left+Nav">List your stock</a><br />
                        &nbsp;<a href="" name="&lid=Place+Trade+Advert&lpos=Left+Nav">Place a Trade Advert</a><br />
                        &nbsp;<a href="" name="&lid=Testemonials&lpos=Left+Nav">Testimonials</a><br />
                        &nbsp;<a href="" name="&lid=Dealer+Directory&lpos=Left+Nav">Dealer Directory</a><br />
                    </td>
                </tr>
                <tr>
                    <td>
                        <br />
                        <img src="images/home-title-05.gif" alt="Find a dealer"><br />
                        &nbsp;<a href="">UK car dealer directory</a><br />
                        &nbsp;<a href="">Car dealers in your area</a></p>
                        <br />
                        &nbsp;<img src="images/home-title-06.gif" alt="Research cars"><br />
                        &nbsp;<a href="">New Car Roadtests</a><br />
                        &nbsp;<a href="">Used Car Roadtests</a><br/>
                        &nbsp;<a href="">Car Buying Advice</a>
                        <br/><br/><br/>
                    </td>
                </tr>
            </table>
        </td>
        <td class="home">
            <table>
                <tr>
                    <td>&nbsp;<img src="images/homepage_car.gif">&nbsp;&nbsp;<br /><br />

                    </td>
                </tr>
            </table>
        </td>
        <td class="home">
            <table>
                <tr>
                    <td class="home">
                        <img src="images/home-title-07.gif" alt="Finance center">
                        <br />&nbsp;Compare over 400 leading<br />&nbsp;loan suppliers including:<br />
                        <br />&nbsp;<a href="">Northern Smock</a>
                        <br />&nbsp;<a href="">Alliance & Lancaster</a>
                        <br />&nbsp;<a href="">Bresco Loans</a><br />&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="home">
                        <img src="images/home-title-08.gif" alt="Performance parts"> 
                        <br />&nbsp;Manufacturer OEM parts<br />&nbsp;from:<br />
                        <br />&nbsp;<a href="amg.jsp">AMG</a>
                        <br />&nbsp;<a href="alpina.jsp">Alpina</a>
                        <br />&nbsp;<a href="mazdaspeed.jsp">MazdaSpeed</a>
                        <br />&nbsp;<a href="ruf.jsp">Ruf</a>
                        <br />&nbsp;<a href="gembella.jsp">Gemballa</a>
                        <br />&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="home">
                        <img src="images/home-title-09.gif" alt="Latest fuel prices">
                        <br />&nbsp;Regular: <c:out value="${prices.regular}" />
                        <br />&nbsp;Mid Grade: <c:out value="${prices.midgrade}" />
                        <br />&nbsp;Premium: <c:out value="${prices.premium}" />
                        <br />&nbsp;E85: <c:out value="${prices.e85}" />
                        <br />&nbsp;Diesel: <c:out value="${prices.diesel}" />
                        <br/><br/><br/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<%@ include file="footer.jsp" %>