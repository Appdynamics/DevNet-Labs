/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.dataloader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import supercars.form.EnquireForm;


/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnquiryDataLoader {
	
	private static Log log = LogFactory.getLog(EnquiryDataLoader.class);
	
	private static int LONG_QUERY_COUNT = 0;
	
    Statement statement = null;
    ResultSet resultSet = null;
    
    
    public EnquireForm getEnquiry(int enquiryId) {
        
        EnquireForm enquireForm = new EnquireForm();
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT ENQUIRY_ID, NAME, EMAIL, COMMENT, CAR_ID FROM ENQUIRIES WHERE ENQUIRY_ID = "+enquiryId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            enquireForm.setEnquireFormId(resultSet.getLong("ENQUIRY_ID"));
            enquireForm.setName(resultSet.getString("NAME"));
            enquireForm.setEmail(resultSet.getString("EMAIL"));
            enquireForm.setComment(resultSet.getString("COMMENT"));
            enquireForm.setCarId(resultSet.getInt("carId"));
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception ex){
        	log.error("Error on method getEnquiry", ex);
        }
        return enquireForm;
    }
    
    public Collection getEnquirysForCar(int carId) {
        
//    	if (Math.random() < 1.0/100.0) {
//      }

        Collection enquiries = new ArrayList();
        try (Connection connection = Constants.getDBConnection()) {
        	String sql;
        	
        	//synchronized (this) {
            	LONG_QUERY_COUNT++;
            	
            	if (Math.random() < 1.0/100.0) {
                //if (LONG_QUERY_COUNT >= 3) {
                	LONG_QUERY_COUNT = 0;
                	
                    sql = "SELECT NAME, EMAIL, COMMENT FROM ENQUIRIES WHERE CAR_ID = "+carId;
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    resultSet.close();
                    statement.close();

                	sql = "SELECT ENQUIRIES.CAR_ID, CARS.MODEL, CARS.DESCRIPTION, CARS.COLOUR, CARS.YEAR, CARS.PRICE, CARS.SUMMARY, ENQUIRIES.NAME, ENQUIRIES.COMMENT, MANUFACTURER.WEB, MANUFACTURER.EMAIL, ENQUIRIES.EMAIL, CARS.MANUFACTURER_ID, MANUFACTURER.NAME FROM ENQUIRIES LEFT OUTER JOIN CARS ON ENQUIRIES.CAR_ID = CARS.CAR_ID RIGHT OUTER JOIN MANUFACTURER ON CARS.MANUFACTURER_ID = MANUFACTURER.MANUFACTURER_ID WHERE CARS.SUMMARY LIKE '%Maximum%' AND CARS.DESCRIPTION LIKE '%Leather%' AND CARS.DESCRIPTION LIKE '%Pile%' ORDER BY CARS.PRICE ASC";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sql);
                    while(resultSet.next()) {
                    	resultSet.getString("cars.MODEL");
                    	resultSet.getString("cars.DESCRIPTION");
                    	resultSet.getString("cars.SUMMARY");
                    }
                    resultSet.close();
                    statement.close();            
                    
    			    // error generated intentionally
			    	try {
			    		
			    		EnquireForm form = new EnquireForm();
			    		form.setEnquireFormId(1l);
			    		form.setCarId(24);
			    		form.setName("John Smith");
			    		form.setEmail("john.smith@email.com");
			    		form.setComment("I would like to come see this car.");
			    		new EnquiryDataLoader().saveEnquireFormWithPK(form);
			    		
			    	} catch (Throwable ex) {
			    		log.error("Error Saving Enquiry", ex);
			    	}
                    
                }           
        		
        	//}
        	
            
            sql = "SELECT NAME, EMAIL, COMMENT FROM ENQUIRIES WHERE CAR_ID = "+carId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                EnquireForm enquireForm = new EnquireForm();
                enquireForm.setName(resultSet.getString("NAME"));
                enquireForm.setEmail(resultSet.getString("EMAIL"));
                enquireForm.setComment(resultSet.getString("COMMENT"));
                enquiries.add(enquireForm);
            }
            resultSet.close();
            statement.close();

            connection.close();
        } catch(Exception ex){
        	log.error("Error on method getEnquirysForCar", ex);
        }
        return enquiries;
    }
    
    public void saveEnquireForm(EnquireForm enquireForm){
        try (Connection connection = Constants.getDBConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ENQUIRIES (NAME, EMAIL, COMMENT, CAR_ID, DUMMY) SELECT ?,?,?,?, SLEEP(1)");
            pstmt.setString(1, enquireForm.getName());
            pstmt.setString(2, enquireForm.getEmail());
            pstmt.setString(3, enquireForm.getComment());
            pstmt.setInt(4, enquireForm.getCarId());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception ex) {
        	log.error("Error on method saveEnquireForm", ex);
        }
    }

    public void saveEnquireFormWithPK(EnquireForm enquireForm) throws Throwable {
        Connection connection = Constants.getDBConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ENQUIRIES (ENQUIRY_ID, NAME, EMAIL, COMMENT, CAR_ID, DUMMY) SELECT ?,?,?,?,?, SLEEP(1)");
        pstmt.setLong(1, enquireForm.getEnquireFormId());
        pstmt.setString(2, enquireForm.getName());
        pstmt.setString(3, enquireForm.getEmail());
        pstmt.setString(4, enquireForm.getComment());
        pstmt.setInt(5, enquireForm.getCarId());
        pstmt.execute();
        pstmt.close();
        connection.close();
    }

}
