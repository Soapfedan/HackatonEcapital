package database;

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
	
	//ricerca
}
