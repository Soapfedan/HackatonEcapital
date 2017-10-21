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


public class PlugConnection implements SerialPortDataListener{
	private static final byte PACKET_BEGIN = (byte) 0x23;
	private Queue<PlugData> dataQueue;
	private SerialPort port;
	private OutputStreamWriter writer;
	private BufferedReader reader;
	
	public PlugConnection(String portName, int baudrate) {
		dataQueue = new ConcurrentLinkedQueue<>();
		port = SerialPort.getCommPort(portName);
		port.setBaudRate(baudrate);
		port.setComPortTimeouts(
				SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 
				100, 100);
		port.addDataListener(this);
		port.openPort();
		
		writer = new OutputStreamWriter(port.getOutputStream());
		reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
	}
	
	public boolean hasData() {
		return dataQueue.size() > 0;
	}
	
	public PlugData getPlugData() {
		return dataQueue.poll();
	}
	
	public void setPlug(int id, boolean on) {
		String ons = ((on) ? "1" : "0");
		try {
			writer.write("#" + id + ";" + ons + "\n");
			writer.flush();
		} catch (IOException e) {
		}
	}

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
			line = line.substring(1);
			String[] split = line.split(";");
			if(split.length == 2)
				dataQueue.add(new PlugData(Integer.valueOf(split[0]), Integer.valueOf(split[1])));
		} catch (IOException e) {
		}
		 
	}

	
	
	
	
	
	

}
