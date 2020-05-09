 /*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import supercars.Manufacturer;
import supercars.utils.JsonHelper;
import supercars.utils.PropertiesHelper;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionInventory extends Action {

	private static Log log = LogFactory.getLog(ActionInventory.class);
	
	
	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
	
		CloseableHttpResponse res = null;
		
		try {
			String apiPort =  PropertiesHelper.getApiServiceProps().getProperty("listener.port");
			String apiContext = PropertiesHelper.getApiServiceProps().getProperty("root.context");
			String inventoryContext = PropertiesHelper.getInventoryServiceProps().getProperty("root.context");
			
			// String url = "http://localhost:8171/api/inventory/manufacturers";
			String url = "http://localhost:" + apiPort + "/" + apiContext + "/" + inventoryContext + "/manufacturers";
			
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet req = new HttpGet(url);
			 
			res = client.execute(req);
			
			if (res.getStatusLine().getStatusCode() == 200) {
				log.info("########################## API Service returned 200 ##########################");
				//System.out.println("########################## API Service returned 200 ##########################");
				
				String json = JsonHelper.getJsonFromHttpResponse(res);
				Collection<Manufacturer> result = JsonHelper.getManufacturerList(json);
				request.setAttribute("manufacturers", result);
				
			} else {
				log.info("########################## API Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
				//System.out.println("########################## API Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
			}
			
		} catch (Throwable ex) {
			log.error("########################## API Service Failure ##########################", ex);
			//System.out.println("########################## API Service Failure ##########################");
			//System.out.println("########################## " + ex.getMessage() + " ##########################");
			ex.printStackTrace();
			
		} finally {
			try {
				if (res != null) {
					res.close();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}		
		
//		ManufacturerDataLoader mdl = new ManufacturerDataLoader();
//		request.setAttribute("manufacturers", mdl.getManufacturers());
		
		
		return(mapping.findForward("success"));
	}
	
	
	
	
}
