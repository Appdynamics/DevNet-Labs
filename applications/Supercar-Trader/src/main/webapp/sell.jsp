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
  <html:form action="/sell?query=save"> 
    <table width="80%" border="0">
      <tr>
       <td>Manufacturer:<br>&nbsp;<select name="manufacturer">
		<c:forEach var="manu" items="${manufacturers}">
  			<option value="<c:out value="${manu.manufacturerId}"/>"><c:out value="${manu.name}"/></option>
		</c:forEach>
       </select>
        </td>
      </tr>
      <tr> 
       <td>Model:<br>&nbsp;<html:text property="model" /></td> 
      </tr>
      <tr> 
       <td>Version:<br>&nbsp;<html:text property="name" /></td>
      </tr>
      <tr>
       <td>Color:<br>&nbsp;<html:text property="colour" /></td> 
      </tr>
      <tr>
       <td>Year:<br>&nbsp;<html:text property="year" /></td> 
      </tr>
      <tr>
       <td>Price:<br>&nbsp;<html:text property="price" /></td> 
      </tr>
      <tr>
       <td>Summary:<br><!-- img src="images/line.gif" --><br>&nbsp;<html:textarea rows="6" cols="45" property="summary" /></td> 
      </tr>
      <tr>
       <td>Detailed Description:<br><!-- img src="images/line.gif" --><br>&nbsp;<html:textarea rows="12" cols="45" property="description" /></td> 
      </tr>  
      <tr> 
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" src="images/submit_button.gif" align="absmiddle"></td> 
      </tr> 
    </table> 
  </html:form> 
<%@ include file="footer.jsp" %>