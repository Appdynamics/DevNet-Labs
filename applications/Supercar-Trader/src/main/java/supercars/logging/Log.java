/**
 * 
 */
package supercars.logging;

/**
 * @author James Schneider
 *
 */
public class Log {

	String clazzName = "Supercar-Trader";
	String loggingLevel = "info";
	
	/**
	 * 
	 */
	public Log(String clazzName, String logLevel) {
		
		this.clazzName = clazzName;
		
		if (logLevel != null && !logLevel.equals("")) {
			
			String ll = logLevel.toLowerCase().trim();
			
			if (ll.equals("trace")) {
				this.loggingLevel = "trace";
				this.clazzName = clazzName;
			}
			if (ll.equals("debug")) {
				this.loggingLevel = "debug";
				this.clazzName = clazzName;
			}
			if (ll.equals("info")) {
				this.loggingLevel = "info";
			}
			
		} else {
			this.loggingLevel = "info";
		}

	}

	public void error(String msg, Throwable ex) {
		this.log(this.clazzName + "|ERROR|" + msg);
		if (ex != null) {
			ex.printStackTrace();
		}
	}

	public void error(String msg) {
		this.log(this.clazzName + "|ERROR|" + msg);
	}
	
	public void info(String msg) {
		if (this.isInfo()) { 
			this.log(this.clazzName + "|INFO|" + msg);
		}
		
	}

	public void debug(String msg) {
		if (this.isDebug()) {
			this.log(this.clazzName + "|DEBUG|" + msg);
		}
		
	}

	public void trace(String msg) {
		if (this.isTrace()) {
			this.log(this.clazzName + "|TRACE|" + msg);
		}
		
	}

	public void carriageReturnInfo() {
		if (this.isInfo()) {
			System.out.println("");
		}
	}

	public void carriageReturnDebug() {
		if (this.isDebug()) {
			System.out.println("");
		}
		
	}
	public void carriageReturnTrace() {
		if (this.isTrace()) {
			System.out.println("");
		}
		
	}
	public void carriageReturn() {
		System.out.println("");
	}
	
	
	public void log(String msg) {
		System.out.println(msg);
	}
	
	private boolean isTrace() {
		if (this.loggingLevel.equals("trace")) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isDebug() {
		if (this.loggingLevel.equals("debug") || this.loggingLevel.equals("trace")) {
			return true;
		} else {
			return false;
		}
	}
	private boolean isInfo() {
		if (this.loggingLevel.equals("info") || this.loggingLevel.equals("debug") || this.loggingLevel.equals("trace")) {
			return true;
		} else {
			return false;
		}
	}
	

}
