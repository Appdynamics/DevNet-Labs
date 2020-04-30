/**
 * 
 */
package supercars.services;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import supercars.services.api.ApiService;
import supercars.services.enquiry.EnquiryService;
import supercars.services.insurance.InsuranceService;
import supercars.services.inventory.InventoryService;
import supercars.utils.FileUtils;
import supercars.utils.ProcessHelper;
import supercars.utils.PropertiesHelper;
import supercars.utils.StringUtils;

/**
 * Uses the ProcessHelper to create the REST services 
 * 
 * @author james
 *
 */
public class ServiceFactory {
	
	private static Log log = LogFactory.getLog(ServiceFactory.class);
	
	private static Map<String, Process> processes = new HashMap<String, Process>();
	private static Map<String, String> appdProps = new HashMap<String, String>();
	private static boolean haveChecked4AppdAgent = false;
	private static boolean appdAgentIsApplied = false;
    private static int numAppdOptsFound = 0;
    private static final int totalAppdOpts = 6;
	
    private static boolean apiServiceStarted = false;
    private static boolean inventoryServiceStarted = false;
    private static boolean insuranceServiceStarted = false;
    private static boolean enquiryServiceStarted = false;
    
    
	/**
	 * 
	 */
	public ServiceFactory() {
		
	}


	// starts all running services and returns the number of services started
	public static int startAllServices() throws Throwable {
		int numServicesStarted = 0;
		
		log.info("!!! Attempting to start API Service !!!");
		//System.out.println("!!! Attempting to start API Service !!!"); 
		if (startApiService()) {
			numServicesStarted++;
		}
		log.info("!!! Attempting to start Inventory Service !!!");
		//System.out.println("!!! Attempting to start Inventory Service !!!");
		if (startInventoryService()) {
			numServicesStarted++;
		}
		log.info("!!! Attempting to start Insurance Service !!!");
		//System.out.println("!!! Attempting to start Insurance Service !!!");
		if (startInsuranceService()) {
			numServicesStarted++;
		}
		log.info("!!! Attempting to start Enquiry Service !!!");
		//System.out.println("!!! Attempting to start Enquiry Service !!!");
		if (startEnquiryService()) {
			numServicesStarted++;
		}
		
		try {
			  Runtime.getRuntime().addShutdownHook(new Thread() 
			    { 
			      public void run() 
			      { 
			    	try {
			    		log.info("!!! Services Shutdown Hook is running !!!");
			    		//System.out.println("!!! Services Shutdown Hook is running !!!"); 
				        int servicesStopped = ServiceFactory.stopAllServices();
				        log.info("!!! A total of " + servicesStopped + " services were shutdown !!!");
				        //System.out.println("!!! A total of " + servicesStopped + " services were shutdown !!!");  
			    	} catch (Throwable ex) {
			    		ex.printStackTrace();
			    	}
			      } 
			    }); 
			     
		} catch (Throwable ex) {
			log.error("!!! Error on Services Shutdown !!! : " + ex.getMessage(), ex);
			//System.out.println("!!! Error on Services Shutdown !!! : " + ex.getMessage());
			//ex.printStackTrace();
		}

		return numServicesStarted;
	}
	
	// stops all running services and returns the number of services stopped
	public static int stopAllServices() throws Throwable {
		int numServicesStopped = 0;

		stopApiService();
		numServicesStopped++;
		stopInventoryService();
		numServicesStopped++;
		stopInsuranceService();
		numServicesStopped++;
		stopEnquiryService();
		numServicesStopped++;
		
		return numServicesStopped;
	}

	
	public static boolean startApiService() throws Throwable {
		return ServiceFactory.startService(apiServiceStarted, PropertiesHelper.getApiServiceProps(), ApiService.class.getName());
	}
	public static void stopApiService() throws Throwable {
		Properties serviceProps = PropertiesHelper.getApiServiceProps();		
		String processName = serviceProps.getProperty("service.process.name");
		ApiService.stop();
		ProcessHelper.killProcess(processName);
		
	}

	
	public static boolean startInventoryService() throws Throwable {
		return ServiceFactory.startService(inventoryServiceStarted, PropertiesHelper.getInventoryServiceProps(), InventoryService.class.getName());
	}
	public static void stopInventoryService() throws Throwable {
		Properties serviceProps = PropertiesHelper.getInventoryServiceProps();		
		String processName = serviceProps.getProperty("service.process.name");
		InventoryService.stop();
		ProcessHelper.killProcess(processName);
		
	}

	
	public static boolean startInsuranceService() throws Throwable {
		return ServiceFactory.startService(insuranceServiceStarted, PropertiesHelper.getInsuranceServiceProps(), InsuranceService.class.getName());
	}
	public static void stopInsuranceService() throws Throwable {
		Properties serviceProps = PropertiesHelper.getInsuranceServiceProps();		
		String processName = serviceProps.getProperty("service.process.name");
		InsuranceService.stop();
		ProcessHelper.killProcess(processName);
		
	}

	
	public static boolean startEnquiryService() throws Throwable {
		return ServiceFactory.startService(enquiryServiceStarted, PropertiesHelper.getEnquiryServiceProps(), EnquiryService.class.getName());
	}
	public static void stopEnquiryService() throws Throwable {
		Properties serviceProps = PropertiesHelper.getEnquiryServiceProps();		
		String processName = serviceProps.getProperty("service.process.name");
		InsuranceService.stop();
		ProcessHelper.killProcess(processName);
		
	}
	
	public static boolean startService(boolean serviceStarted, Properties serviceProps, String serviceClassName) throws Throwable {
		
		if (!serviceStarted) {
			
			String processName = serviceProps.getProperty("service.process.name");
			String options = null;
			String[] arguments = new String[1];
			arguments[0] = "";
			
			// check and if needed load all the APPD properties form the Tomcat JVM
			if (allAppdAgentPropertiesFound()) {
				options = "";
				options = options + appdProps.get("APPD_JAVAAGENT_JAR_PROPERTY") + " ";
				options = options + "-Dappdynamics.controller.hostName=" + appdProps.get("APPD_CONTROLLER_HOST_NAME") + " ";
				options = options + "-Dappdynamics.controller.port=" + appdProps.get("APPD_CONTROLLER_PORT") + " ";
				options = options + "-Dappdynamics.controller.ssl.enabled=" + appdProps.get("APPD_CONTROLLER_SSL_ENABLED") + " ";
				options = options + "-Dappdynamics.agent.applicationName=" + appdProps.get("APPD_AGENT_APPLICATION_NAME") + " ";
				options = options + "-Dappdynamics.agent.tierName=" + serviceProps.getProperty("appd.tier.name") + " ";
				options = options + "-Dappdynamics.agent.nodeName=" + serviceProps.getProperty("appd.node.name") + " ";
				options = options + "-Dappdynamics.agent.accountName=" + appdProps.get("APPD_AGENT_ACCOUNT_NAME") + " ";
				options = options + "-Dappdynamics.agent.accountAccessKey=" + appdProps.get("APPD_AGENT_ACCOUNT_ACCESS_KEY") + " ";			
				options = options + "-Xms" + serviceProps.getProperty("mem.xms") + " ";
				options = options + "-Xmx" + serviceProps.getProperty("mem.xmx") + " ";
				options = options + "-XX:MaxPermSize=" + serviceProps.getProperty("mem.max.perm");
				
			} else {
				options = "";
				options = options + "-Xms" + serviceProps.getProperty("mem.xms") + " ";
				options = options + "-Xmx" + serviceProps.getProperty("mem.xmx") + " ";
				options = options + "-XX:MaxPermSize=" + serviceProps.getProperty("mem.max.perm");				
			}
			
			Process service = ProcessHelper.startNewJavaProcess(processName, options, serviceClassName, arguments, serviceProps.getProperty("service.lib.dir"));
			processes.put(processName, service);
			serviceStarted = true;
		}
		
		return serviceStarted;
	}
	

	
	private static boolean allAppdAgentPropertiesFound() throws Throwable {
		if (appdAgentIsLoaded()) {
			if (numAppdOptsFound == totalAppdOpts) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean appdAgentIsLoaded() throws Throwable {
		
		if (haveChecked4AppdAgent) {
			return appdAgentIsApplied;
			
		} else {
			String agentOption = "noagent";
	        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
	        List<String> arguments = runtimeMxBean.getInputArguments();
	        for (String argument : arguments) {
	            if (argument.startsWith("-javaagent:")) {
	            	agentOption = argument;
	            }     
	        }
	        
	        if (agentOption.startsWith("-javaagent:")) {
	        	appdProps.put("APPD_JAVAAGENT_JAR_PROPERTY", agentOption);
	        	appdAgentIsApplied = true;
	        	System.out.println("-javaagent = " + agentOption);
	        }
	        
	        haveChecked4AppdAgent = true;
	        
	        boolean optConHostName = false;
	        boolean optConPort = false;
	        boolean optConSslEnabled = false;
	        boolean optAppName = false;
	        boolean optAcctName = false;
	        boolean optAccessKey = false;

	        // GET OPTS FROM ENV
	        if (System.getenv("APPDYNAMICS_CONTROLLER_HOST_NAME") != null) {
	        	appdProps.put("APPD_CONTROLLER_HOST_NAME", System.getenv("APPDYNAMICS_CONTROLLER_HOST_NAME"));
	        	optConHostName = true;
	        	numAppdOptsFound++;
	        }	        
	        if (System.getenv("APPDYNAMICS_CONTROLLER_PORT") != null) {
	        	appdProps.put("APPD_CONTROLLER_PORT", System.getenv("APPDYNAMICS_CONTROLLER_PORT"));
	        	optConPort = true;
	        	numAppdOptsFound++;
	        }
	        if (System.getenv("APPDYNAMICS_CONTROLLER_SSL_ENABLED") != null) {
	        	appdProps.put("APPD_CONTROLLER_SSL_ENABLED", System.getenv("APPDYNAMICS_CONTROLLER_SSL_ENABLED"));
	        	optConSslEnabled = true;
	        	numAppdOptsFound++;
	        }
	        if (System.getenv("APPDYNAMICS_AGENT_APPLICATION_NAME") != null) {
	        	appdProps.put("APPD_AGENT_APPLICATION_NAME", System.getenv("APPDYNAMICS_AGENT_APPLICATION_NAME"));
	        	optAppName = true;
	        	numAppdOptsFound++;
	        }
	        if (System.getenv("APPDYNAMICS_AGENT_ACCOUNT_NAME") != null) {
	        	appdProps.put("APPD_AGENT_ACCOUNT_NAME", System.getenv("APPDYNAMICS_AGENT_ACCOUNT_NAME"));
	        	optAcctName = true;
	        	numAppdOptsFound++;
	        }
	        if (System.getenv("APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY") != null) {
	        	appdProps.put("APPD_AGENT_ACCOUNT_ACCESS_KEY", System.getenv("APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY"));
	        	optAccessKey = true;
	        	numAppdOptsFound++;
	        }
	        
	        log.info("Number of Options Found = " + numAppdOptsFound);
	        
	        log.info("APPDYNAMICS_CONTROLLER_HOST_NAME = " + System.getenv("APPDYNAMICS_CONTROLLER_HOST_NAME"));
	        log.info("APPDYNAMICS_CONTROLLER_PORT = " + System.getenv("APPDYNAMICS_CONTROLLER_PORT"));
	        log.info("APPDYNAMICS_CONTROLLER_SSL_ENABLED = " + System.getenv("APPDYNAMICS_CONTROLLER_SSL_ENABLED"));
	        log.info("APPDYNAMICS_AGENT_APPLICATION_NAME = " + System.getenv("APPDYNAMICS_AGENT_APPLICATION_NAME"));
	        log.info("APPDYNAMICS_AGENT_ACCOUNT_NAME = " + System.getenv("APPDYNAMICS_AGENT_ACCOUNT_NAME"));
	        log.info("APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY = " + System.getenv("APPDYNAMICS_AGENT_ACCOUNT_ACCESS_KEY"));
	        
	        
	        
	        if (numAppdOptsFound < totalAppdOpts) {
	        	
		        // GET OPTS FROM SYS PROPS
		        if (!optConHostName) {
		        	if (System.getProperty("appdynamics.controller.hostName") != null) {
		        		appdProps.put("APPD_CONTROLLER_HOST_NAME", System.getProperty("appdynamics.controller.hostName"));
			        	optConHostName = true;
			        	numAppdOptsFound++;		        		
		        	}
		        }
		        if (!optConPort) {
		        	if (System.getProperty("appdynamics.controller.port") != null) {
		        		appdProps.put("APPD_CONTROLLER_PORT", System.getProperty("appdynamics.controller.port"));
		        		optConPort = true;
			        	numAppdOptsFound++;		        		
		        	}
		        }
		        if (!optConSslEnabled) {
		        	if (System.getProperty("appdynamics.controller.ssl.enabled") != null) {
		        		appdProps.put("APPD_CONTROLLER_SSL_ENABLED", System.getProperty("appdynamics.controller.ssl.enabled"));
		        		optConSslEnabled = true;
			        	numAppdOptsFound++;		        		
		        	}
		        }
		        if (!optAppName) {
		        	if (System.getProperty("appdynamics.agent.applicationName") != null) {
		        		appdProps.put("APPD_AGENT_APPLICATION_NAME", System.getProperty("appdynamics.agent.applicationName"));
		        		optAppName = true;
			        	numAppdOptsFound++;		        		
		        	}
		        }
		        if (!optAcctName) {
		        	if (System.getProperty("appdynamics.agent.accountName") != null) {
		        		appdProps.put("APPD_AGENT_ACCOUNT_NAME", System.getProperty("appdynamics.agent.accountName"));
		        		optAcctName = true;
			        	numAppdOptsFound++;		        		
		        	}
		        }
		        if (!optAccessKey) {
		        	if (System.getProperty("appdynamics.agent.accountAccessKey") != null) {
		        		appdProps.put("APPD_AGENT_ACCOUNT_ACCESS_KEY", System.getProperty("appdynamics.agent.accountAccessKey"));
		        		optAccessKey = true;
			        	numAppdOptsFound++;		        		
		        	}
		        }
	        	
		        log.info("Number of Options Found = " + numAppdOptsFound);
		        
		        log.info("appdynamics.controller.hostName = " + System.getProperty("appdynamics.controller.hostName"));
		        log.info("appdynamics.controller.port = " + System.getProperty("appdynamics.controller.port"));
		        log.info("appdynamics.controller.ssl.enabled = " + System.getProperty("appdynamics.controller.ssl.enabled"));
		        log.info("appdynamics.agent.applicationName = " + System.getProperty("appdynamics.agent.applicationName"));
		        log.info("appdynamics.agent.accountName = " + System.getProperty("appdynamics.agent.accountName"));
		        log.info("appdynamics.agent.accountAccessKey = " + System.getProperty("appdynamics.agent.accountAccessKey"));	        	        	
	        	
	        }
	        

	        if (numAppdOptsFound < totalAppdOpts) { 
	        	
		        // GET OPTS FROM FILE
		        
		        // strip off ends to get root agent directory
		        String jarPath = agentOption.substring(11);
		        //System.out.println("jarPath = " + jarPath);
		        
		        int endIdx = jarPath.lastIndexOf("/");
		        String jarDir = jarPath.substring(0, endIdx);
		        //System.out.println("jarDir = " + jarDir);
		        
		        
		        // Find the agent version conf directory
		        File[] dirs = FileUtils.findSubdirectoriesInDirectory(jarDir);
		        File agentVerConfDir = null;
		        for (int i = 0; i < dirs.length; i++) {
		        	if (dirs[i].getName().startsWith("ver")) {
		        		agentVerConfDir = new File(dirs[i].getAbsolutePath() + "/" + "conf");
		        		if (!agentVerConfDir.isDirectory()) {
		        			agentVerConfDir = null;
		        		} 
		        	}
		        	//System.out.println("ABS DIR = " + dirs[i].getAbsolutePath());
		        	//System.out.println("DIR = " + dirs[i].getName());
				}
		        
		        if (agentVerConfDir != null) {
		        	//System.out.println("AgentVerConfDir = " + agentVerConfDir.getAbsolutePath());
		        	
		        	String contInfoXmlPath = FileUtils.convertFileSystemPath(agentVerConfDir.getAbsolutePath() + "/controller-info.xml");
		        	//System.out.println("controller-info.xml path = " + contInfoXmlPath);
		        	
		        	String contInfoXml = StringUtils.getFileAsString(contInfoXmlPath);
		        	//System.out.println("controller-info.xml = " + contInfoXml);
		        	
		        	String strConHostName = null;
		        	String strConPort = null;
		        	String strConSslEnabled = null;
		        	String strAppName = null;
		        	String strAcctName = null;
		        	String strAccessKey = null;
		        	
		        	if (!optConHostName) {
			        	strConHostName = contInfoXml.substring(contInfoXml.indexOf("<controller-host>")+17, contInfoXml.indexOf("</controller-host>"));
			        	if (strConHostName != null) {
			        		appdProps.put("APPD_CONTROLLER_HOST_NAME", strConHostName);
				        	optConHostName = true;
				        	numAppdOptsFound++;		        		
			        	}		        		
		        	}
		        	if (!optConPort) {
		        		strConPort = contInfoXml.substring(contInfoXml.indexOf("<controller-port>")+17, contInfoXml.indexOf("</controller-port>"));
			        	if (strConPort != null) {
			        		appdProps.put("APPD_CONTROLLER_PORT", strConPort);
			        		optConPort = true;
				        	numAppdOptsFound++;		        		
			        	}		        				        		
		        	}
		        	if (!optConSslEnabled) {
		        		strConSslEnabled = contInfoXml.substring(contInfoXml.indexOf("<controller-ssl-enabled>")+24, contInfoXml.indexOf("</controller-ssl-enabled>"));
			        	if (strConSslEnabled != null) {
			        		appdProps.put("APPD_CONTROLLER_SSL_ENABLED", strConSslEnabled);
			        		optConSslEnabled = true;
				        	numAppdOptsFound++;		        		
			        	}		        				        		
		        	}
		        	if (!optAppName) {
		        		strAppName = contInfoXml.substring(contInfoXml.indexOf("<application-name>")+18, contInfoXml.indexOf("</application-name>"));
			        	if (strAppName != null) {
			        		appdProps.put("APPD_AGENT_APPLICATION_NAME", strAppName);
			        		optAppName = true;
				        	numAppdOptsFound++;		        		
			        	}		        				        		
		        	}
		        	if (!optAcctName) {
		        		strAcctName = contInfoXml.substring(contInfoXml.indexOf("<account-name>")+14, contInfoXml.indexOf("</account-name>"));
			        	if (strAcctName != null) {
			        		appdProps.put("APPD_AGENT_ACCOUNT_NAME", strAcctName);
			        		optAcctName = true;
				        	numAppdOptsFound++;		        		
			        	}		        				        		
		        	}
		        	if (!optAccessKey) {
		        		strAccessKey = contInfoXml.substring(contInfoXml.indexOf("<account-access-key>")+20, contInfoXml.indexOf("</account-access-key>"));
			        	if (strAccessKey != null) {
			        		appdProps.put("APPD_AGENT_ACCOUNT_ACCESS_KEY", strAccessKey);
			        		optAccessKey = true;
				        	numAppdOptsFound++;		        		
			        	}		        				        		
		        	}
		        	
		        	
		        	log.info("Number of Options Found = " + numAppdOptsFound);
		        	
		        	log.info("<controller-host> = " + strConHostName);
		        	log.info("<controller-port> = " + strConPort);
		        	log.info("<controller-ssl-enabled> = " + strConSslEnabled);
		        	log.info("<application-name> = " + strAppName);
		        	log.info("<account-name> = " + strAcctName);
		        	log.info("<account-access-key> = " + strAccessKey);
		        	
		        	
		        } else {
		        	log.info("AgentVerConfDir = NOT FOUND");
		        }
		        
		        log.info("Final Number of Options Found = " + numAppdOptsFound);
		        for (String key : appdProps.keySet()) {
		        	log.info(key + " = " + appdProps.get(key).toString());
				}
		        
		        
	        }
	        
	        
	        
		}
		
		return appdAgentIsApplied;
	}
	
}
