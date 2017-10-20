package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

import database.Attuatore;
import database.Consumo;



public class JsonServer {
	private HttpServer server;
	private Thread worker;
	
	public JsonServer() throws IOException  {
		server = HttpServer.create(new InetSocketAddress(9600), 0);
		server.createContext("/attuatori", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				String response = Attuatore.getAttuatori();
	            arg0.sendResponseHeaders(200, response.length());
	            OutputStream os = arg0.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
			}
		});
		server.createContext("/consumototale", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				String response = Consumo.getTotConsumo();
	            arg0.sendResponseHeaders(200, response.length());
	            OutputStream os = arg0.getResponseBody();
	            os.write(response.getBytes());
	            os.close();	
			}
		});
		server.createContext("/consumoattuatore", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				String response = Attuatore.getCurrentConsumo(Integer.parseInt(arg0.getRequestURI().getQuery()));
	            arg0.sendResponseHeaders(200, response.length());
	            OutputStream os = arg0.getResponseBody();
	            os.write(response.getBytes());
	            os.close();	
				
			}
		});
		server.createContext("/modpriorita", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				
			}
		});
		
        server.setExecutor(null); // creates a default executor
        worker = new Thread(new Runnable() {
			@Override
			public void run() {
			       server.start();
			}
		});
        worker.start();
	}
	
	/*
	 * lista attuatori (nome, priorita, cat)
	 * lista consumi
	 * consumo totale attuatori 
	 * modifica priorita
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	
}
