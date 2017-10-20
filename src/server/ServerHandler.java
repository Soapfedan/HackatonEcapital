package server;

import java.io.IOException;
import java.sql.SQLException;

import database.Attuatore;
import database.Categoria;
import database.ConnectionHandler;
import database.Consumo;
import database.Prova;
import database.Utente;

public class ServerHandler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("prova");
		try {
			ConnectionHandler.init();
			System.out.println(Attuatore.getCurrentConsumo(2));
			System.out.println(Attuatore.getAttuatori());
			System.out.println(Consumo.getTotConsumo());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonServer server = new JsonServer();
		
		
		
	}

}
