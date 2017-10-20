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


public class PlugConnection implements SerialPortPacketListener{
	private Queue<PlugData> dataQueue;
	
	public PlugConnection(String portName, int baudrate) {
		dataQueue = new ConcurrentLinkedQueue<>();
		SerialPort port = SerialPort.getCommPort(portName);
		port.setBaudRate(baudrate);
		port.addDataListener(this);
	}

	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_WRITTEN;
	}
	
	public void fillArray(List<PlugData> buffer) {
		while(dataQueue.size() > 0) {
			buffer.add(dataQueue.poll());
		}
	}
	
	@Override
	public void serialEvent(SerialPortEvent evt) {
		dataQueue.add(new PlugData(evt.getReceivedData()));
	}

	@Override
	public int getPacketSize() {
		return 8;
	}
	
	
	
	
	
	

}
