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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import supercars.Car;
import supercars.dataloader.CarDataLoader;
import supercars.dataloader.ManufacturerDataLoader;
import supercars.form.EnquireForm;
import supercars.utils.JsonHelper;
import supercars.utils.PropertiesHelper;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ActionCar extends Action {

    // Perform Action
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String query = request.getParameter("query");
        int carId = Integer.parseInt(request.getParameter("cid"));

        Car car = new CarDataLoader().getCar(carId);
        request.setAttribute("car", car);
        request.setAttribute("manufacturer", new ManufacturerDataLoader().getManufacturer(car.getManufacturer()));
        request.setAttribute("enquiries", null);

        
        if (query.equals("carEnquiries")) {
        	
            //request.setAttribute("enquiries", new EnquiryDataLoader().getEnquirysForCar(carId));

    		try {
    			String apiPort =  PropertiesHelper.getApiServiceProps().getProperty("listener.port");
    			String apiContext = PropertiesHelper.getApiServiceProps().getProperty("root.context");
    			String enquiryContext = PropertiesHelper.getEnquiryServiceProps().getProperty("root.context");
    			
    			// String url = "http://localhost:8171/api/enquiry/carEnquiries/";
    			String url = "http://localhost:" + apiPort + "/" + apiContext + "/" + enquiryContext + "/carEnquiries/";
    			
    			HttpClient client = HttpClientBuilder.create().build();
    			
    			URIBuilder builder = new URIBuilder(url);
    			builder.setParameter("carId", "" + carId);
    			
    			HttpGet req = new HttpGet(builder.build());
    			
    			HttpResponse res = client.execute(req);
    			
    			if (res.getStatusLine().getStatusCode() == 200) {
    				System.out.println("########################## API Service returned 200 ##########################");
    				
    				String json = JsonHelper.getJsonFromHttpResponse(res);
    				Collection<EnquireForm> result = JsonHelper.getEnquiryList(json);
    				request.setAttribute("enquiries", result);
    				
    			} else {
    				System.out.println("########################## API Service returned " + res.getStatusLine().getStatusCode() + " ##########################");
    			}
    			
    		} catch (Throwable ex) {
    			System.out.println("########################## API Service Failure ##########################");
    			System.out.println("########################## " + ex.getMessage() + " ##########################");
    			ex.printStackTrace();
    			
    		}		
            
        }

        return (mapping.findForward("success"));
    }
}
