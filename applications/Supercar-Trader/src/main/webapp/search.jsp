<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ include file="header.jsp" %>
<%
try {
                        Random r = new Random();
                        int i = r.nextInt(999);
                        Thread.sleep(i);
                }
                catch(Exception e){
                        e.printStackTrace();
                }
%>
<form name="searchForm" id="searchForm" action="search.do?query=search" method="POST">
    <p class="normal"><!-- img src="images/line.gif" --></p>
    <p class="normal"></br></p>
    <p class="normal">Please enter your search criteria, for example 'Lamborghini', or '911'<br>
        <br><input type="text" id="criteria" name="criteria" />&nbsp;
        <input type="image" id="commit" name="commit" src="images/search_button.gif"/ align="absmiddle"><br>
        <% if (request.getParameter("criteria") != null) { %>
        <br>Your search '<%=request.getParameter("criteria")%>' returned the following <%=((ArrayList)request.getAttribute("cars")).size()%> results:
        <% }%></p>
    <table>
        <c:forEach var="car" items="${cars}">
            <tr class="grey"> 
                <td> 
                    <p><a href="car.do?query=car&cid=<c:out value="${car.carId}"/>"><img src="images/cars/<c:out value="${car.photo}"/>.jpg"></a></p>
                    <!-- p><img src="images/cars/<c:out value="${car.photo}"/>.jpg"></p -->
                    
                </td>
                <td> 
                    &nbsp;&nbsp;&nbsp;
                </td>
                <td>
                    <p class="summary"><a href="car.do?query=car&cid=<c:out value="${car.carId}"/>"><c:out value="${car.model}"/>&nbsp;<c:out value="${car.name}"/></a>&nbsp;&nbsp;<c:out value="${car.summary}"/></p>
                </td>
                <td>
                    <p>£<c:out value="${car.price}"/></p>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<%@ include file="footer.jsp" %>