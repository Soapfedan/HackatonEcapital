package plug;

import java.util.Date;
import java.sql.Time;

public class PlugData {
	public int id;
	public int consumption;
	public Date time;
	
	public PlugData(int id, int consumption, String date) {
		this.id = id;
		this.consumption = consumption;
		this.time = new Date();
	}
	
	public PlugData(byte[] buffer) {
		id = ((buffer[0] & 0xFF)  << 24)
				| ((buffer[1] & 0xFF)  << 16)
				| ((buffer[2] & 0xFF)  << 8)
				| ((buffer[3] & 0xFF) );
		consumption = ((buffer[4] & 0xFF)  << 24)
				| ((buffer[5] & 0xFF)  << 16)
				| ((buffer[6] & 0xFF)  << 8)
				| ((buffer[7] & 0xFF) );
		time = new Date();
	}
	
	@Override
	public String toString() {
		return "[" + id + "," + consumption + "," + time.toString() + "]";
	}
	
}
