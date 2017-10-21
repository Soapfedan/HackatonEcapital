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
	private SerialPort port;
	
	public PlugConnection(String portName, int baudrate) {
		dataQueue = new ConcurrentLinkedQueue<>();
		port = SerialPort.getCommPort(portName);
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
	
	public List<PlugData> read() {
		List<PlugData> data = new LinkedList<>();
		while(dataQueue.size() > 0) {
			data.add(dataQueue.poll());
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
	
	@Override
	public void serialEvent(SerialPortEvent evt) {
		dataQueue.add(new PlugData(evt.getReceivedData()));
	}

	@Override
	public int getPacketSize() {
		return 8;
	}
	
	
	
	
	
	

}
