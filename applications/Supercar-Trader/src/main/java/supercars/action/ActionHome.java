 /*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import supercars.externaldata.FuelPrices;
import supercars.services.ServiceFactory;


/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionHome extends Action {

	private static boolean servicesInitialized = false;
	private static Log log;
	
	
	static {
		try {

			System.setProperty("log4j.configuration", "file:/usr/local/apache/apache-tomcat-7.0.99/webapps/Supercar-Trader/logging/web-log4j.xml");
			
			log = LogFactory.getLog(ActionHome.class);
			
			if (!servicesInitialized) {
				try {
					
					int servicesStarted = ServiceFactory.startAllServices();
					log.info("########################## Services Started = " + servicesStarted + " ##########################");
					servicesInitialized = true;
				} catch (Throwable ex) {
					log.error("########################## Service Start Failure ##########################", ex);
					ex.printStackTrace();					
				}
				
			}
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}	
	}
	
	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		
		
		FuelPrices prices = FuelPrices.getFuelPrices();
                request.setAttribute("prices", prices);
                        
		return(mapping.findForward("success"));
	}
}
