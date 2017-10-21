package plug;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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


public class PlugConnection {
	private static final byte PACKET_BEGIN = (byte) 0x23;
	private Queue<PlugData> dataQueue;
	private SerialPort port;
	
	public PlugConnection(String portName, int baudrate) {
		dataQueue = new ConcurrentLinkedQueue<>();
		port = SerialPort.getCommPort(portName);
		port.setBaudRate(baudrate);
		port.setComPortTimeouts(
				SerialPort.TIMEOUT_READ_BLOCKING | SerialPort.TIMEOUT_WRITE_BLOCKING, 
				100, 100);
		port.openPort();
	}
	
	public List<PlugData> read(int max) {
		List<PlugData> data = new LinkedList<>();
		byte[] buff = new byte[8];

		while(data.size() < max) {
			do {
				port.readBytes(buff, 1);
			} while(buff[0] != PACKET_BEGIN);
			
			port.readBytes(buff, 8);
			data.add(new PlugData(buff));
		}
		return data;
	}
	
	public void setPlug(int id, boolean on) {
		byte[] buff = new byte[5];
		buff[0] = (byte) ((id & 0xFF000000) >> 24);
		buff[1] = (byte) ((id & 0x00FF0000) >> 16);
		buff[2] = (byte) ((id & 0x0000FF00) >> 8);
		buff[3] = (byte) ((id & 0x000000FF));
		buff[4] = (byte) ((on) ? 0xFF : 0x00);
		port.writeBytes(buff, buff.length);
	}

	
	
	
	
	
	

}
