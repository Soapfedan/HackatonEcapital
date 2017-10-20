package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;



public class JsonServer {
	private HttpServer server;
	private Thread worker;
	
	public JsonServer() throws IOException  {
		server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/attuatori", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				String response = "This is the response";
	            arg0.sendResponseHeaders(200, response.length());
	            OutputStream os = arg0.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
			}
		});
		server.createContext("/consumototale", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				
			}
		});
		server.createContext("/consumoattuatori", new com.sun.net.httpserver.HttpHandler() {
			
			@Override
			public void handle(com.sun.net.httpserver.HttpExchange arg0) throws IOException {
				
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
