/**
 * 
 */
package supercars.services.inventory;

// supercars.services.inventory.InventoryService

import java.util.Properties;

import org.apache.log4j.Logger;

import supercars.services.JettyServer;
import supercars.utils.PropertiesHelper;

/**
 * Helper class used to start the JettyServer for the Inventory service
 * 
 * 
 * @author james
 *
 */
public class InventoryService {
	
	private static Logger log;
	
	static {
		try {
			System.setProperty("log4j.configuration", "file:/usr/local/apache/apache-tomcat-7/webapps/Supercar-Trader/logging/inventory-log4j.xml");
			log = Logger.getLogger(InventoryService.class);
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}	
	
	private static JettyServer server;
	
	/**
	 * 
	 */
	public InventoryService() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// here we need to instantiate a Jetty Server and start it
		
		try {
			
			Properties serviceProps = PropertiesHelper.getInventoryServiceProps();
			server = new JettyServer();
			server.start(Integer.parseInt(serviceProps.getProperty("jetty.max.threads")), 
					Integer.parseInt(serviceProps.getProperty("jetty.min.threads")), 
					Integer.parseInt(serviceProps.getProperty("jetty.idle.timeout")), 
					Integer.parseInt(serviceProps.getProperty("listener.port")), 
					serviceProps.getProperty("servlet.class"), 
					serviceProps.getProperty("root.context"));
			
		} catch (Throwable ex) {
			log.error("Error starting Inventory Service", ex);
			ex.printStackTrace();
		}
		
	
	}
	
	
	public static void stop() throws Throwable {
		if (InventoryService.server != null) {
			InventoryService.server.stop();
		}
	}
	

}
