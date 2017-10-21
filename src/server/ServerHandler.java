package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Attuatore;
import database.Categoria;
import database.ConnectionHandler;
import database.Consumo;
import database.ThresholdAlgorithm;
import database.Utente;
import plug.PlugConnection;

public class ServerHandler {

	public static void main(String[] args) throws IOException {
		
		PlugConnection.init(args[0], 9600);
		
		try {
			ConnectionHandler.init(args[1]);
			//ThresholdAlgorithm.shutdownScheduling();
			//Attuatore.getCurrentConsumoInt(2);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ThresholdAlgorithm.start();
		JsonServer server = new JsonServer();
		
		
		
	}

}
