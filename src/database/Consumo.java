package database;

import java.sql.SQLException;
import java.sql.Statement;

public class Consumo {
	
	private final String TABLE_NAME = "consumo";
	private final String PLUG_ID = "plug_id";
	private final String TIMEST = "tempo";
	private final String CONSUMO = "consumo";
	
	public void insertCon(String p_id){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO DBUSER"
				+ "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
				+" 'yyyy/mm/dd hh24:mi:ss'))";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL stetement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//ricerca
}
