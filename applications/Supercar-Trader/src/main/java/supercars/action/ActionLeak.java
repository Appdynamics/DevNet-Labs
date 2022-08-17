/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supercars.logging.Log;
import supercars.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import supercars.form.FormHelper;
import supercars.form.LeakForm;

/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ActionLeak extends Action {

	private static Log log = LogFactory.getLog(ActionLeak.class);
	
	
    public static List<byte[]> leakyCollection = new LinkedList<>();
    
    // Perform Action
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String query = request.getParameter("query");
        String forward = "success";

        if (query == null) {
        } else if (query.equals("load")) {
            //getEnquireForm(Integer.parseInt(request.getParameter("enquireId")));
        } else if (query.equals("save")) {
            LeakForm leakForm = (LeakForm) form;
            if (FormHelper.isLeakValid(leakForm)) {
                addToCollection(leakForm.getNumber(), leakForm.getSize());
            }
            request.setAttribute("actionText", "selling");
            forward = "thanks";
        }
        form.reset(mapping, request);
        return (mapping.findForward(forward));
    }
    
    private void addToCollection(int number, int size) {
        for (int i = 0; i < number; i++) {
            ActionLeak.leakyCollection.add(new byte[size]);
        }
    }
}
