package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Attuatore {

	public static final String TABLE_NAME = "ATTUATORE";
	public static final String AT_ID = "AT_ID"; 
	public static final String NOME = "NOME";
	public static final String DESCRIZIONE = "DESCRIZIONE";
	public static final String CAT_ID = "CAT_ID";
	public static final String PRIORITA = "PRIORITA";
	
	//Inserimento dell'attuatore
	public static void insertAttuatore(String nm, String desc, int cat, int pr){
		Statement statement = null;

		String insertTableSQL = "INSERT INTO "+TABLE_NAME
				+ "("+NOME+","+DESCRIZIONE+","+CAT_ID+","+PRIORITA+") VALUES('"
				+nm+"','"+desc+"',"+cat+","+pr+")";

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//Modifica priorita
	public static void updatePriorita(int id, int pr) {
		Statement statement = null;

		String alterTableSQL = "UPDATE "+TABLE_NAME+" SET "
				+PRIORITA+" = "+pr
				+" WHERE "+AT_ID+" = "+id;

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(alterTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(alterTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//Cancellazione Attuatore
	public static void deleteAttuatore(int id) {
		Statement statement = null;

		String alterTableSQL = "DELETE FROM "+TABLE_NAME+" WHERE "
				+" WHERE "+AT_ID+" = "+id;

		try {
			
			statement = ConnectionHandler.getConn().createStatement();

			System.out.println(alterTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(alterTableSQL);

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} 
	}
	
	//Select di tutti gli attuatori
	public static String getAttuatori() {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		String json = null;
		String query = "SELECT "+AT_ID+","+TABLE_NAME+"."+NOME+" AS ATTUATORE_NOME,"+DESCRIZIONE+","+TABLE_NAME+"."+CAT_ID+" AS ATTUATORE_CAT_ID,"+TABLE_NAME+"."+PRIORITA+" AS ATTUATORE_PRIORITA,"+Categoria.TABLE_NAME+"."+Categoria.NOME+" AS CATEGORIA_NOME"+
				" FROM "+TABLE_NAME+","+Categoria.TABLE_NAME+
				" WHERE "+TABLE_NAME+"."+CAT_ID+" = "+Categoria.TABLE_NAME+"."+Categoria.CAT_ID;
		
		try {
			stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(query);
			
			json = "{ \"attuatori\": [ ";
			
			rs.last();
			int size = rs.getRow();
			rs.first();
			
			for(int k=0;k<size-1;k++){
				json = json + "{ "
							+"\"at_id\" :" + "\""+rs.getInt("AT_ID")+"\","
							+"\"nome\" :" + "\"" + rs.getString("ATTUATORE_NOME")+"\","
							+"\"descrizione\" :" + "\""+ rs.getString("DESCRIZIONE")+"\","
							+"\"cat_id\" :" + "\""+ rs.getInt("ATTUATORE_CAT_ID") +"\","
							+"\"priorita\" :" + "\""+ rs.getInt("ATTUATORE_PRIORITA") +"\","
							+"\"categoria\" :" + "\""+ rs.getString("CATEGORIA_NOME") +"\" },";
				rs.next();
			}
			json = json + "{ "
						+"\"at_id\" :" + "\""+rs.getInt("AT_ID")+"\","
						+"\"nome\" :" + "\"" + rs.getString("ATTUATORE_NOME")+"\","
						+"\"descrizione\" :" + "\""+ rs.getString("DESCRIZIONE")+"\","
						+"\"cat_id\" :" + "\""+ rs.getInt("ATTUATORE_CAT_ID") +"\","
						+"\"priorita\" :" + "\""+ rs.getInt("ATTUATORE_PRIORITA") +"\","
						+"\"categoria\" :" + "\""+ rs.getString("CATEGORIA_NOME") +"\" }";	 
			json = json + " ]"
						+ "}";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	//Select consumo attuale
	public static String getCurrentConsumo(int id) {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		String json;
		json = "{ \"consumo_att\": [ ";
		String query = "SELECT "+Consumo.CONSUMO+ 
				" FROM ("+ 
				"SELECT "+Consumo.CONSUMO+
				" FROM "+Consumo.TABLE_NAME+","+Presa.TABLE_NAME+","+TABLE_NAME+ 
				" WHERE "+ TABLE_NAME+"."+AT_ID+" = "+id+ 
				" AND "+ TABLE_NAME+"."+AT_ID+" = "+ Presa.TABLE_NAME+"."+Presa.AT_ID+ 
				" AND "+Presa.TABLE_NAME+"."+Presa.PLUG_ID+" = "+Consumo.TABLE_NAME+"."+Consumo.PLUG_ID+ 
				" ORDER BY "+Consumo.TIMEST+" DESC "+ 
				")" + 
				"WHERE ROWNUM = 1";
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			rs.next();
			json = json+"{ "
					+"\"consumo_att\" :" + "\""+rs.getInt(Consumo.CONSUMO)+"\" }";
			
		} catch (SQLException e) {
			json = json + "{ "
					+"\"consumo_att\" :" + "\0\" }";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = json + " ]"
				+ "}";
		return json;
	}
	
	public static int getCurrentConsumoInt(int id) {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		
		int cons = 0;
		
		String query = "SELECT "+Consumo.CONSUMO+ 
				" FROM ("+ 
				"SELECT "+Consumo.CONSUMO+
				" FROM "+Consumo.TABLE_NAME+","+Presa.TABLE_NAME+","+TABLE_NAME+ 
				" WHERE "+ TABLE_NAME+"."+AT_ID+" = "+id+ 
				" AND "+ TABLE_NAME+"."+AT_ID+" = "+ Presa.TABLE_NAME+"."+Presa.AT_ID+ 
				" AND "+Presa.TABLE_NAME+"."+Presa.PLUG_ID+" = "+Consumo.TABLE_NAME+"."+Consumo.PLUG_ID+ 
				" ORDER BY "+Consumo.TIMEST+" DESC "+ 
				")" + 
				"WHERE ROWNUM = 1";
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			while(rs.next()) {
				cons = rs.getInt(Consumo.CONSUMO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cons;
	}
}
