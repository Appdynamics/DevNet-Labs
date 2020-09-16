<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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
<%@ include file="header.jsp" %>
<script>
  function init() {
    var myButton = document.getElementById("myButton");
    var myTextfield = document.getElementById("myTextfield");
    myButton.onclick = function() {
      var userName = myTextfield.value;
    }
  }
  document.addEventListener('readystatechange', function() {
    if (document.readyState === "complete") {
      init();
    }
  });
</script>

<p class="normal"><!-- img src="images/line.gif" --></p>
<p class="normal"></br></p>
<p class="normal">Please enter details of your supercar:</p>
  <form name="sellForm" id="sellForm" action="sell.do?query=save" method="POST"> 
    <table width="80%" border="0">
      <tr>
       <td>Manufacturer:<br>&nbsp;<select name="manufacturer" id="manufacturer">
		<c:forEach var="manu" items="${manufacturers}">
  			<option name="carManuf" id="carManuf" value="<c:out value="${manu.manufacturerId}"/>"><c:out value="${manu.name}"/></option>
		</c:forEach>
       </select>
        </td>
      </tr>
      <tr> 
       <td>Model:<br>&nbsp;<input type="text" name="carModel" id="carModel" property="carModel" /></td> 
      </tr>
      <tr> 
       <td>Engine:<br>&nbsp;<input type="text" name="carEngine" id="carEngine" property="carEngine" /></td>
      </tr>
      <tr>
       <td>Color:<br>&nbsp;<input type="text" name="carColor" id="carColor" property="carColor" /></td> 
      </tr>
      <tr>
       <td>Year:<br>&nbsp;<input type="text" name="carYear" id="carYear" property="carYear" /></td> 
      </tr>
      <tr>
       <td>Price:<br>&nbsp;<input type="text" name="carPrice" id="carPrice" property="carPrice" /></td> 
      </tr>
      <tr>
       <td>Summary:<br><!-- img src="images/line.gif" --><br>&nbsp;<input typ="textarea" name="carSummary" id="carSummary" rows="16" cols="145" property="carSummary" /></td> 
      </tr>
      <tr>
       <td>Detailed Description:<br><!-- img src="images/line.gif" --><br>&nbsp;<input typ="textarea" name="carDetails" id="carDetails" rows="12" cols="145" property="carDetails" /></td> 
      </tr>  
      <tr> 
        <td>&nbsp;</td> 
      </tr> 
      <tr> 
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" id="commit" name="commit" src="images/submit_button.gif" align="absmiddle"></td> 
      </tr> 
    </table> 
  </form> 
<%@ include file="footer.jsp" %>