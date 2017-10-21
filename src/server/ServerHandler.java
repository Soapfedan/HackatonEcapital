package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Attuatore;
import database.Categoria;
import database.ConnectionHandler;
import database.Consumo;
import database.Prova;
import database.ThresholdAlgorithm;
import database.Utente;

public class ServerHandler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("prova");
		try {
			ConnectionHandler.init();
			//ThresholdAlgorithm.shutdownScheduling();
			//Attuatore.getCurrentConsumoInt(2);
			
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
