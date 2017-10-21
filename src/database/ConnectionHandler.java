
package database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHandler {
	private static Connection connection;
	
	public static void init(String host) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521:XE","UTE","UTE");
	}
	
	public static Connection getConn() {
		return connection;
	}
    
}
