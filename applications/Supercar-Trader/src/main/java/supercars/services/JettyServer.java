/**
 * 
 */
package supercars.services;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * 
 * @author james
 *
 */
public class JettyServer {
	
	private Server server;
	

	/**
	 * 
	 */
	public JettyServer() {
		
	}
	
	public void start(int maxThreads, int minThreads, int idleTimeout, int port, String servletClass, String rootContext) throws Throwable {
		
		QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
		
        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[] { connector });

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(servletClass, "/" + rootContext + "/*");	
        
        server.start();
        
	}
	
	
    public void stop() throws Throwable {
        server.stop();
    }	

}
