 /*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionInsurance extends Action {

	private static Logger log = Logger.getLogger(ActionInsurance.class);
	
	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		try {
			String url = "http://localhost:8173/insurance";
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet req = new HttpGet(url);
			 
			HttpResponse res = client.execute(req);
			if (res.getStatusLine().getStatusCode() == 200) {
				log.info("########################## Insurance Service returned 200 ##########################");
				//System.out.println("########################## Insurance Service returned 200 ##########################");				
			} else {
				log.info("########################## Insurance Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
				//System.out.println("########################## Insurance Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
			}			
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return(mapping.findForward("success"));
	}
}
