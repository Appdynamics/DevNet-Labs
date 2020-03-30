<%@ page import="java.util.*, supercars.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%
try {
			//Random r = new Random();
			int i = 142; // r.nextInt(2000);
			Thread.sleep(i);
		}
		catch(Exception e){
			e.printStackTrace();
		}
%>
<%@ include file="header.jsp" %>
  <tr> 
    <td>
    <p><img src="images/cars/<c:out value="${car.photo}"/>.jpg" align="absmiddle">&nbsp;&nbsp;&nbsp;<img src="images/manufacturers/<c:out value="${manufacturer.smallLogo}"/>">
   	<br/><br/><a href="enquire.do?car=<c:out value="${car.carId}"/>&carName=<c:out value="${car.name}"/>&nbsp;<c:out value="${car.model}"/>"><img src="images/enquire_button.gif"/></a>
    </p> 
    <p class="car"><b>Performance Summary:</b><br>
    <%
    Car car = (Car)request.getAttribute("car");
    %>
    </p>
    <p class="car">Name:&nbsp;<c:out value="${car.name}"/><br>
    Model:&nbsp;<c:out value="${car.model}"/><br>
    Summary:&nbsp;<c:out value="${car.summary}"/><br>
    Description:&nbsp;<c:out value="${car.description}"/><br>
    Price:&nbsp;�<c:out value="${car.price}"/><br>
    Colour:&nbsp;<c:out value="${car.colour}"/><br>
    Year:&nbsp;<c:out value="${car.year}"/></p>
  	</td>
  </tr>
  <tr>
  	<td><a href="car.do?query=carEnquiries&cid=<c:out value="${car.carId}"/>"><img src="images/view_enquiries_button.gif"/></a><br/></td>
  </tr>
    <c:forEach var="enquiry" items="${enquiries}">
	  <tr class="grey"> 
	    <td> 
	    	<p>From:<c:out value="${enquiry.name}"/>,&nbsp;<c:out value="${enquiry.email}"/><br><c:out value="${enquiry.comment}"/><br><img src="images/line.gif"></p>
	  	</td>
	  </tr>
	  <tr>
	  	<td></td>
	  </tr>
	</c:forEach>
	<%	ArrayList al = (ArrayList)request.getAttribute("enquiries");
	if (al == null)
		{}
	else if (request.getParameter("query").equals("carEnquiries") && al.size() < 1) 
		out.print("<p>No Enquiries Found.</p>");%>
<%@ include file="footer.jsp" %>