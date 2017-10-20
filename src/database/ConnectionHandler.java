
package database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHandler {
	private static Connection connection;
	
	public static void init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@172.23.147.80:1521:XE","UTE","UTE");
	}
	
	public static Connection getConn() {
		return connection;
	}
	
}
