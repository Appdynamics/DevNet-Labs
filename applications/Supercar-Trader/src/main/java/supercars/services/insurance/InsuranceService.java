/**
 * 
 */
package supercars.services.insurance;

import java.util.Properties;

import org.apache.log4j.Logger;

import supercars.services.JettyServer;
import supercars.utils.PropertiesHelper;

/**
 * @author james
 *
 */
public class InsuranceService {

	private static Logger log;
	
	static {
		try {
			System.setProperty("log4j.configuration", "file:/usr/local/apache/apache-tomcat-7.0.99/webapps/Supercar-Trader/logging/insurance-log4j.xml");
			log = Logger.getLogger(InsuranceService.class);
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}	

	private static JettyServer server;
	
	/**
	 * 
	 */
	public InsuranceService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// here we need to instantiate a Jetty Server and start it
		
		try {
			
			Properties serviceProps = PropertiesHelper.getInsuranceServiceProps();
			server = new JettyServer();
			server.start(Integer.parseInt(serviceProps.getProperty("jetty.max.threads")), 
					Integer.parseInt(serviceProps.getProperty("jetty.min.threads")), 
					Integer.parseInt(serviceProps.getProperty("jetty.idle.timeout")), 
					Integer.parseInt(serviceProps.getProperty("listener.port")), 
					serviceProps.getProperty("servlet.class"), 
					serviceProps.getProperty("root.context"));
			
		} catch (Throwable ex) {
			log.error("Error starting Insurance Service", ex);
			ex.printStackTrace();
		}
	}

	public static void stop() throws Throwable {
		if (InsuranceService.server != null) {
			InsuranceService.server.stop();
		}
	}

	
}
