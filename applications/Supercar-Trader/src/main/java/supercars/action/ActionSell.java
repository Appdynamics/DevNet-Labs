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

import supercars.dataloader.CarDataLoader;
import supercars.dataloader.ManufacturerDataLoader;
import supercars.form.CarForm;
import supercars.form.FormHelper;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionSell extends Action {
	
	private static Log log = LogFactory.getLog(ActionSell.class);
	

	// Perform Action
	public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest req,
            HttpServletResponse response) throws Exception {
		
		String query = req.getParameter("query");
		String forward = "success";
		
		if(query == null){}
		else if (query.equals("load")) {
			//getEnquireForm(Integer.parseInt(request.getParameter("enquireId")));
		} else if(query.equals("save")) {
			
			CarForm cForm = (CarForm)form;
			cForm.setManufacturer(Integer.parseInt(req.getParameter("manufacturer")));
			cForm.setModel(req.getParameter("carModel"));
			cForm.setName(req.getParameter("carEngine"));
			cForm.setColour(req.getParameter("carColor"));
			cForm.setYear(Integer.parseInt(req.getParameter("carYear")));
			cForm.setManual(true);
			cForm.setPrice(Integer.parseInt(req.getParameter("carPrice")));
			cForm.setDescription(req.getParameter("carDetails"));
			cForm.setSummary(req.getParameter("carSummary"));
			
			if (FormHelper.isCarValid((CarForm)form)) {
				saveCarForm((CarForm)form);
			}
                        req.setAttribute("actionText", "selling");
			forward = "thanks";
		}
		
		req.setAttribute("manufacturers", new ManufacturerDataLoader().getManufacturers());
		form.reset(mapping, req);
		return(mapping.findForward(forward));
	}
	
	public void saveCarForm(CarForm carForm) {
		new CarDataLoader().saveCar(carForm);
	}
}
