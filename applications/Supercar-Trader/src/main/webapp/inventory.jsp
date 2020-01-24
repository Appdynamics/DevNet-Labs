<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ include file="header.jsp" %>
<p class="normal"><img src="images/line.gif"></p>
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
<table>
<c:forEach var="manu" items="${manufacturers}">
  <tr> 
    <td bgcolor="#222a2d"> 
    <p><a href="cars.do?query=manu&mid=<c:out value="${manu.manufacturerId}"/>"><c:out value="${manu.name}"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  	</td>
  	<td><p><a href="cars.do?query=manu&mid=<c:out value="${manu.manufacturerId}"/>"><img src="images/manufacturers/<c:out value="${manu.logo}"/>"></a></p></td>
  </tr>
</c:forEach>
</table>
<%@ include file="footer.jsp" %>