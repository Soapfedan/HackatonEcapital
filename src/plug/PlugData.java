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
		id = buffer[0] << 8 | buffer[1];
		consumption = buffer[2] << 8 | buffer[3];
		time = new Date();
	}
	
	
}
