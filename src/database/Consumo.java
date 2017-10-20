package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consumo {
	
	public static final String TABLE_NAME = "CONSUMO";
	public static final String PLUG_ID = "PLUG_ID";
	public static final String TIMEST = "TEMPO";
	public static final String CONSUMO = "P_CONS";
	
	//Inserimento del consumo
	public static void insertConsumo(int p_id, int cons){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+ "("+PLUG_ID+","+TIMEST+","+CONSUMO+") VALUES("
				+p_id+",SYSTIMESTAMP,"+cons+")";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	public static String getTotConsumo() {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		String json = null;
		
		
		int consumo_tot = 0;
		
		String query = "SELECT "+Attuatore.AT_ID+
				" FROM "+Attuatore.TABLE_NAME;
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			
			while(rs.next()) {
				int id = rs.getInt(Attuatore.AT_ID);
				consumo_tot += Attuatore.getCurrentConsumoInt(id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = "{ "
				+"\"consumo_tot\" :" + "\""+consumo_tot+"\" }";
		return json;
	}
}
