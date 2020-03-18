/**
 * 
 */
package supercars.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author james
 *
 */
public class ProcessHelper {

	private static Map<String, Process> processes = new HashMap<String, Process>();
	
	/**
	 * 
	 */
	protected ProcessHelper() {
		
	}
	
	public static Process startNewJavaProcess(final String processName, final String optionsAsString, final String mainClass, final String[] arguments, final String processLibDir)
			throws Throwable {

		ProcessBuilder processBuilder = createProcess(optionsAsString, mainClass, arguments, processLibDir);
		Process process = processBuilder.start();
		//StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
		//Executors.newSingleThreadExecutor().submit(streamGobbler);
		processes.put(processName, process);
		//log.debug("Process " + process.toString() + " has started");
		return process;
	}
	
	
	private static ProcessBuilder createProcess(final String optionsAsString, final String mainClass, final String[] arguments, final String processLibDir) throws Throwable {
		
		String jvm = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		
		//String classpath = System.getProperty("java.class.path");
		
		//log.debug("classpath: " + classpath);
		//String workingDirectory = System.getProperty("user.dir");
		//System.out.println("########################## Process Helper user.dir ##########################");
		//System.out.println("########################## " + workingDirectory + " ##########################");

		String[] options = null;
		if (optionsAsString != null) {
			options = optionsAsString.split(" ");
		}
		
		List < String > command = new ArrayList <String>();
		command.add(jvm);
		
		//command.add("-cp " + processLibDir + "/* ");
		if (options != null) {
			command.addAll(Arrays.asList(options));
		}
		
		// add main class here
		command.add(mainClass);
		command.addAll(Arrays.asList(arguments));

		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Map< String, String > environment = processBuilder.environment();
		//for (String key : environment.keySet()) {
			//System.out.println("######## " + key + ": " + environment.get(key).toString());
			
		//}
		//System.out.println("########################## Process Helper CLASSPATH ##########################");
		//System.out.println("########################## " + classpath + " ##########################");
		
		environment.put("CLASSPATH", processLibDir + "/*");
		System.out.println("######## " + "CLASSPATH" + ": " + environment.get("CLASSPATH").toString());
		// TODO not sure if this is the correct place to do this
		processBuilder.inheritIO();
		
		return processBuilder;
	}


	public static void killProcess(final String processName) throws Throwable {
		
		if (processes.containsKey(processName)) {
			processes.get(processName).destroy();
		}
		
	}

	
	/**
	 * Kill all processes.
	 */
	public static void shutdown() throws Throwable {
		//log.debug("Killing " + processes.size() + " processes.");    
		for (String processName : processes.keySet()) {
			killProcess(processName);
		}
	}	
	
}
