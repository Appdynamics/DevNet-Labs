/**
 * 
 */
package supercars.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author james
 *
 */
public class PropertiesHelper {

	/**
	 * 
	 */
	private PropertiesHelper() {
		
	}

	
	public static Properties getPropertiesFromClasspath(String propertiesPath) throws Throwable {
	    Properties configProperties = new Properties();
	    InputStream inputStream = PropertiesHelper.class.getClassLoader().getResourceAsStream(propertiesPath);
	    configProperties.load(inputStream);	
	    return configProperties;
	}
	
	
	public static Properties getApiServiceProps() throws Throwable {
		
		return PropertiesHelper.getPropertiesFromClasspath("services/api-service.properties");
	}
	
	public static Properties getInventoryServiceProps() throws Throwable {
		
		return PropertiesHelper.getPropertiesFromClasspath("services/inventory-service.properties");
	}

	public static Properties getInsuranceServiceProps() throws Throwable {
		
		return PropertiesHelper.getPropertiesFromClasspath("services/insurance-service.properties");
	}

	public static Properties getEnquiryServiceProps() throws Throwable {
		
		return PropertiesHelper.getPropertiesFromClasspath("services/enquiry-service.properties");
	}

	
	public static Properties getDBConnectionProps() throws Throwable {
		
		return PropertiesHelper.getPropertiesFromClasspath("db/connection.properties");
	}
	
}
