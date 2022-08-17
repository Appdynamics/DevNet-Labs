/**
 * 
 */
package supercars.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * @author James Schneider
 *
 */
public class LogFactory {

	private static Map<String, Log> LOGMAP = new HashMap<String, Log>();
	private static String DEF_LOGR_NAME = "Supercar-Trader";
	private static Log DEF_LOGR = new Log(DEF_LOGR_NAME, "info");
	
	public static synchronized Log getLog(Class clazz) {
		if (clazz == null) {
			return DEF_LOGR;
		}
		
		if (LOGMAP.containsKey(clazz.getName())) {
			return LOGMAP.get(clazz.getName());
		} else {
			LOGMAP.put(clazz.getName(), new Log(clazz.getName(), "info"));
			return LOGMAP.get(clazz.getName());
			
		}
		
	}
	
}
