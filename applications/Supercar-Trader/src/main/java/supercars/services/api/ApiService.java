/**
 * 
 */
package supercars.services.api;

import java.util.Properties;

import supercars.logging.Log;
import supercars.logging.LogFactory;

import supercars.services.JettyServer;
import supercars.utils.PropertiesHelper;

/**
 * @author james
 *
 */
public class ApiService {

	
	private static Log log;
	
	static {
		try {
			System.setProperty("log4j.configuration", "file:/usr/local/apache/apache-tomcat-7/webapps/Supercar-Trader/logging/api-log4j.xml");
			log = LogFactory.getLog(ApiService.class);
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}	
	
	
	private static JettyServer server;
	
	
	/**
	 * 
	 */
	public ApiService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// here we need to instantiate a Jetty Server and start it
		
		try {
			
			Properties serviceProps = PropertiesHelper.getApiServiceProps();
			server = new JettyServer();
			server.start(Integer.parseInt(serviceProps.getProperty("jetty.max.threads")), 
					Integer.parseInt(serviceProps.getProperty("jetty.min.threads")), 
					Integer.parseInt(serviceProps.getProperty("jetty.idle.timeout")), 
					Integer.parseInt(serviceProps.getProperty("listener.port")), 
					serviceProps.getProperty("servlet.class"), 
					serviceProps.getProperty("root.context"));
			
		} catch (Throwable ex) {
			log.error("Error starting API Service", ex);
			ex.printStackTrace();
		}
	}

	public static void stop() throws Throwable {
		if (ApiService.server != null) {
			ApiService.server.stop();
		}
	}

	
}
