/*
 * Constants.java
 *
 * Created on 31 July 2007, 09:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package supercars.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import supercars.utils.PropertiesHelper;

/**
 *
 * @author james
 */
public class Constants {
    
	private static Log log = LogFactory.getLog(Constants.class);
	
    
    /** Creates a new instance of Constants */
    public Constants() {
    }
    
    
    public static Connection getDBConnection() throws SQLException {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	Properties props = PropertiesHelper.getDBConnectionProps();
            Connection dbCon = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));;
            return dbCon;
        } catch (NamingException ex) {
        	log.error("Error getting database connection from the DriverManager", ex);
        } catch (SQLException ex) {
        	log.error("Error getting database connection from the DriverManager", ex);
        } catch (Throwable ex) {
        	log.error("Error getting database connection from the DriverManager", ex);

        }
        
        return null;
    }
    
    
    public static Connection getOldDBConnection() {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) webContext.lookup("jdbc/supercars");
            Connection dbCon = ds.getConnection();
            return dbCon;
        } catch (NamingException ex) {
        	log.error("Error getting database connection from JNDI", ex);
        } catch (SQLException ex) {
        	log.error("Error getting database connection from JNDI", ex);
        }
        
        return null;
    }
    
}
