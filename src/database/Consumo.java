package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public static int getTotConsumoInt() {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		
		
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
		return consumo_tot;
	}
	
	public static int[] getMaxConsXPriority(int pr, ArrayList<Integer> sp) {
		Connection conn = ConnectionHandler.getConn();
		Statement stm = null;
		ResultSet rs = null;
		
		
		int plId = 0;
		int plCons = 0;
		String idSpenti = "(";
		if(sp.size()>0) {
			int count = 0;
			while(count<sp.size()-1) {
				idSpenti=idSpenti+sp.get(count)+",";
				count++;
			}
			idSpenti = idSpenti+sp.get(count);
		}
		idSpenti = idSpenti+")";
		String query = "SELECT "+PLUG_ID+","+CONSUMO+
				" FROM ("+ 
				"SELECT "+ Attuatore.TABLE_NAME+"."+Attuatore.AT_ID+",MAX("+CONSUMO+") AS P_CONS,"+TIMEST+","+TABLE_NAME+"."+Consumo.PLUG_ID+ 
				" FROM "+TABLE_NAME+","+Presa.TABLE_NAME+","+Attuatore.TABLE_NAME+ 
				" WHERE "+Attuatore.TABLE_NAME+"."+Attuatore.AT_ID+" = "+Presa.TABLE_NAME+"."+Presa.AT_ID+ 
				" AND "+Presa.TABLE_NAME+"."+Presa.PLUG_ID+" = "+TABLE_NAME+"."+PLUG_ID+ 
				" AND "+Attuatore.PRIORITA+" = "+pr+ 
				" AND "+TABLE_NAME+"."+PLUG_ID+" NOT IN "+idSpenti+
				" GROUP BY "+Attuatore.TABLE_NAME+"."+Attuatore.AT_ID+","+CONSUMO+","+TIMEST+","+TABLE_NAME+"."+Consumo.PLUG_ID+
				" ORDER BY "+TIMEST+" DESC" + 
				")" + 
				"WHERE ROWNUM = 1";
		
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(query);
			
			while(rs.next()) {
				plId = rs.getInt(PLUG_ID);
				plCons = rs.getInt(CONSUMO);
			}
			
		} catch (SQLException e) {
			query = "SELECT "+PLUG_ID+","+CONSUMO+
					" FROM ("+ 
					"SELECT "+ Attuatore.TABLE_NAME+"."+Attuatore.AT_ID+",MAX("+CONSUMO+") AS P_CONS,"+TIMEST+","+TABLE_NAME+"."+Consumo.PLUG_ID+ 
					" FROM "+TABLE_NAME+","+Presa.TABLE_NAME+","+Attuatore.TABLE_NAME+ 
					" WHERE "+Attuatore.TABLE_NAME+"."+Attuatore.AT_ID+" = "+Presa.TABLE_NAME+"."+Presa.AT_ID+ 
					" AND "+Presa.TABLE_NAME+"."+Presa.PLUG_ID+" = "+TABLE_NAME+"."+PLUG_ID+ 
					" AND "+Attuatore.PRIORITA+" = "+pr+ 
					" GROUP BY "+Attuatore.TABLE_NAME+"."+Attuatore.AT_ID+","+CONSUMO+","+TIMEST+","+TABLE_NAME+"."+Consumo.PLUG_ID+
					" ORDER BY "+TIMEST+" DESC" + 
					")" + 
					"WHERE ROWNUM = 1";
			try {
				stm = conn.createStatement();
				rs = stm.executeQuery(query);
				
				while(rs.next()) {
					plId = rs.getInt(PLUG_ID);
					plCons = rs.getInt(CONSUMO);
				}
			} catch (SQLException g) {
				g.printStackTrace();
			}
		}
		System.out.println(query);
		int[] result = {plId,plCons};
		return result;
	}
}
