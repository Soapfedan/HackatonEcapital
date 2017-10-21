package server;

import java.io.IOException;
import java.sql.SQLException;

import database.Attuatore;
import database.Categoria;
import database.ConnectionHandler;
import database.Consumo;
import database.Prova;
import database.ThresholdAlgorithm;
import database.Utente;
import plug.PlugConnection;
import plug.PlugData;

public class ServerHandler {

	public static void main(String[] args) throws IOException {
		PlugConnection conn = new PlugConnection("COM6", 115200);
		
		while(true) {
			for(PlugData data : conn.read(10)) {
				System.out.println(data.toString());
			}
			//conn.setPlug(123323, false);
		}
		
	}

}
