package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utente {
	private static final String TABLE_NAME = "UTENTE";
	private static final String UTENTE = "USERNAME";
	private static final String NOME = "NOME";
	private static final String COGNOME = "COGNOME";
	private static final String PASSWORD = "PASSWORD";
	private static final String IP = "IP";
	
	//Registrazione
	public static void insertUtente(String user, String password, String nome, String cognome, String ip){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+" VALUES('"
				+user+"','"+nome+"','"+cognome+"','"+password+"','"+ip+"')";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();
			statement.closeOnCompletion();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//Login
	public static boolean login(String user, String password) {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		int ok = 0;
		String query = "select COUNT(*) as LOGIN from "+TABLE_NAME+" WHERE "+UTENTE+" = '"+user+"' AND "+PASSWORD+" = '"+password+"'";
		try {
			stm = conn.createStatement();
			stm.closeOnCompletion();
			rs = stm.executeQuery(query);
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok==1;
	}
	
	//Modifica dell'indirizzo IP
	public static void updateIP(String user, String ip) {
		Statement statement = null;

		String alterTableSQL = "UPDATE "+TABLE_NAME+" SET "
				+IP+" = "+ip
				+" WHERE "+UTENTE+" = "+user;

		try {
			
			statement = ConnectionHandler.getConn().createStatement();
			statement.closeOnCompletion();
			System.out.println(alterTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(alterTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	
}
