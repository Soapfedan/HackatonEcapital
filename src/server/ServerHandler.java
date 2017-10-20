package server;

import java.sql.SQLException;

import database.Attuatore;
import database.Categoria;
import database.ConnectionHandler;
import database.Prova;
import database.Utente;

public class ServerHandler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("prova");
		try {
			ConnectionHandler.init();
			System.out.println(Attuatore.getCurrentConsumo(2));
			System.out.println(Attuatore.getAttuatori());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
