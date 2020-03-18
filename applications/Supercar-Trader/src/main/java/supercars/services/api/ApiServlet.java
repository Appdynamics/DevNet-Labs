/**
 * 
 */
package supercars.services.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import supercars.utils.JsonHelper;

/**
 * @author james
 *
 */
public class ApiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7888011897341720634L;

	/**
	 * 
	 */
	public ApiServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			System.out.println("!!!!!!!!!!!!!!!! API Service Recieved Request URI: " + request.getRequestURI());
			
			String reqUri = request.getRequestURI();
	    	
	    	if (reqUri.equals("/api/inventory/manufacturers")) {
	    		
	    		String url = "http://localhost:8172/inventory/manufacturers";
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet req = new HttpGet(url);
				 
				HttpResponse res = client.execute(req);
				
				if (res.getStatusLine().getStatusCode() == 200) {
					System.out.println("########################## Inventory Service returned 200 ##########################");
					
					String resp = JsonHelper.getJsonFromHttpResponse(res);
					
			        System.out.println("!!!!!!!!!!!!!!!! JSON Payload received by Inventory Service " + request.getRequestURI());
			        System.out.println(resp);
					
			        response.setContentType("application/json");
			        response.setStatus(HttpServletResponse.SC_OK);
			        response.getWriter().println(resp);
			        
				} else {
					System.out.println("########################## Inventory Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
				}

	    	} else if (reqUri.startsWith("/api/enquiry/carEnquiries")) {
	    		
	    		String carId = request.getParameter("carId");
	    		System.out.println("########################## API Service recieved REQ Param carId = " + carId + " ##########################");
	    		
	    		String url = "http://localhost:8174/enquiry/carEnquiries";
    			HttpClient client = HttpClientBuilder.create().build();
    			
    			URIBuilder builder = new URIBuilder(url);
    			builder.setParameter("carId", "" + carId);
    			
    			HttpGet req = new HttpGet(builder.build());
    			HttpResponse res = client.execute(req);
    							
				
				if (res.getStatusLine().getStatusCode() == 200) {
					System.out.println("########################## Enquiry Service returned 200 ##########################");

					String resp = JsonHelper.getJsonFromHttpResponse(res);
					
			        System.out.println("!!!!!!!!!!!!!!!! JSON Payload received by Enquiry Service " + request.getRequestURI());
			        System.out.println(resp);
					
			        response.setContentType("application/json");
			        response.setStatus(HttpServletResponse.SC_OK);
			        response.getWriter().println(resp);
								        
				} else {
					System.out.println("########################## Enquiry Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
				}
				
	    	} else {
	    		
	    		System.out.println("!!!!!!!!!!!!!!!! API Service URI HAD NO MATCH: " + request.getRequestURI());
	    		
	            response.setContentType("application/json");
	            response.setStatus(HttpServletResponse.SC_OK);
	            response.getWriter().println("{ \"status\": \"ok\"}");	    		
	    	}
			
		} catch (Throwable ex) {
			System.out.println("########################## API Service Failure ##########################");
			System.out.println("########################## " + ex.getMessage() + " ##########################");
			ex.printStackTrace();
				
		}
		

    }

    
}
