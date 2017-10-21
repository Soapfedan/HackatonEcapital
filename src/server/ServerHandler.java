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
		PlugConnection conn = new PlugConnection("COM7", 115200);
		
		while(true) {
			while(conn.hasData()) {
				System.out.println(conn.getPlugData().toString());
			}
			
			conn.setPlug(2, true);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			conn.setPlug(2, false);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
		}
		
	}

}
