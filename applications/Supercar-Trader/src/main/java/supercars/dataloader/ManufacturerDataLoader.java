/*
 * Created on 31-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package supercars.dataloader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import supercars.Manufacturer;


/**
 * @author v023094
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManufacturerDataLoader {
	
	private static Log log = LogFactory.getLog(ManufacturerDataLoader.class);

    
    Statement statement = null;
    ResultSet resultSet = null;
    
    public Collection getManufacturers() {
        
        Manufacturer manufacturer = null;
        List manufacturers = new ArrayList();
        
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT MANUFACTURER_ID, NAME, WEB, EMAIL, SMLLOGO, LRGLOGO FROM MANUFACTURER ORDER BY NAME";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                manufacturer = new Manufacturer();
                manufacturer.setManufacturerId(resultSet.getLong("MANUFACTURER_ID"));
                manufacturer.setName(resultSet.getString("NAME"));
                manufacturer.setWeb(resultSet.getString("WEB"));
                manufacturer.setEmail(resultSet.getString("EMAIL"));
                manufacturer.setSmallLogo(resultSet.getString("SMLLOGO"));
                manufacturer.setLargeLogo(resultSet.getString("LRGLOGO"));
                manufacturers.add(manufacturer);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception ex){
        	log.error("Error on method getManufacturers", ex);
        }
        
        return manufacturers;
    }
    
    public Manufacturer getManufacturer(String manufacturerId) {
        
        Manufacturer manufacturer = null;
        
        try (Connection connection = Constants.getDBConnection()) {
            String sql = "SELECT MANUFACTURER_ID, NAME, WEB, EMAIL, SMLLOGO, LRGLOGO FROM MANUFACTURER WHERE MANUFACTURER_ID = "+manufacturerId;
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                manufacturer = new Manufacturer();
                manufacturer.setManufacturerId(resultSet.getLong("MANUFACTURER_ID"));
                manufacturer.setName(resultSet.getString("NAME"));
                manufacturer.setWeb(resultSet.getString("WEB"));
                manufacturer.setEmail(resultSet.getString("EMAIL"));
                manufacturer.setSmallLogo(resultSet.getString("SMLLOGO"));
                manufacturer.setLargeLogo(resultSet.getString("LRGLOGO"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(Exception ex){
        	log.error("Error on method getManufacturer", ex);
        }
        
        return manufacturer;
    }
    
}
