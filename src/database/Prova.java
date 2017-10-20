package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prova {

	public static void prova() {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		String query = "select * from prova";
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt("id_prova");
				System.out.println(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
