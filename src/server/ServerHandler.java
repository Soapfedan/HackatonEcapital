package server;

import java.sql.SQLException;

import database.ConnectionHandler;
import database.Prova;

public class ServerHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("prova");
		try {
			ConnectionHandler.init();
			Prova.prova();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
