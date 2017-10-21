package plug;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import database.Consumo;


public class PlugConnection {
	private static final byte PACKET_BEGIN = (byte) 0x23;
	private static SerialPort port;
	private static OutputStreamWriter writer;
	private static BufferedReader reader;
	
	public static void init(String portName, int baudrate) {
		
		port = SerialPort.getCommPort(portName);
		port.setBaudRate(baudrate);
		port.setComPortTimeouts(
				SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 
				100, 100);
		port.addDataListener(new SerialPortDataListener() {
			
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}

			@Override
			public void serialEvent(SerialPortEvent evt) {
				 if (evt.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
			         return;
				 
				 try {
					String line = reader.readLine();
					if(line == null || line.length() == 0) {
						return;
					}
					System.out.println("Debug: " + line);
					line = line.substring(1);
					String[] split = line.split(";");
					if(split.length != 2) {
						return;
					}
					
					int id = Integer.parseInt(split[0]);
					int consumo =  Math.max(Integer.parseInt(split[1]), 0);
					
					Consumo.insertConsumo(id, consumo);

				 } catch (Exception e) {
				}
				 
			}
		});
		port.openPort();
		
		writer = new OutputStreamWriter(port.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
	}

	public static void setPlug(int id, boolean on) {
		String ons = ((on) ? "1" : "0");
		try {
			writer.write("#" + id + ";" + ons + "\n");
			writer.flush();
		} catch (IOException e) {
		}
	}


}
