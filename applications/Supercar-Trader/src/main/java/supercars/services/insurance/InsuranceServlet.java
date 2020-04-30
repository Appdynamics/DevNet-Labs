/**
 * 
 */
package supercars.services.insurance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author james
 *
 */
public class InsuranceServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(InsuranceServlet.class);
	
	
	private static Map<String, String> leakMap = new HashMap<String, String>();
	private static long memCntr = 0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7889011897341720634L;

	/**
	 * 
	 */
	public InsuranceServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			log.info("!!!!!!!!!!!!!!!! Insurance Service Recieved Request URI: " + request.getRequestURI());

			String leakNum = request.getParameter("heapLeak");
			if (leakNum != null) {
				
				log.info("Insurance Service : parameter heapLeak was " + leakNum);
				
				int lNum = Integer.parseInt(leakNum);
				
				if (lNum == 0) {
					log.info("Insurance Service : clearing the leak map");
					leakMap.clear();
				} else {
					log.info("Insurance Service : adding " + leakNum + " entries to the leak map");
					for (int i = 0; i < lNum; i++) {
						memCntr++;
						leakMap.put(memCntr + "", "*********************************"
								+ "*********************************************************"
								+ "*********************************************************"
								+ "*********************************************************"
								+ "*********************************************************"
								+ "*********************************************************"
								+ "*********************************************************"
								+ "********************************************************");
					}
					log.info("Insurance Service : number of entries in leak map = " + leakMap.size());
				}
				
				
			} else {
				log.info("Insurance Service : parameter heapLeak was NULL");
			}
			
			String url = "https://ratekick.com/";
			HttpClient client = HttpClientBuilder.create().build();
			
			
			HttpGet req = new HttpGet(url);
			HttpResponse res = client.execute(req);
			res.getStatusLine();

			url = "http://www.geico.com/";
			req = new HttpGet(url);
			res = client.execute(req);
			res.getStatusLine();
			
			url = "http://www.progressive.com/";
			req = new HttpGet(url);
			res = client.execute(req);
			res.getStatusLine();
			
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println("{ \"status\": \"ok\"}");	  				

			
			
		} catch (Throwable ex) {
			log.info("########################## Insurance Service Failure ##########################");
			log.error("########################## " + ex.getMessage() + " ##########################", ex);
			ex.printStackTrace();
				
		}
		

    }

    
}
