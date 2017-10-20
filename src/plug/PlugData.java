package plug;

import java.sql.Date;

public class PlugData {
	public int id;
	public int consumption;
	public Date time;
	
	public PlugData(int id, int consumption, String date) {
		this.id = id;
		this.consumption = consumption;
		this.time = Date.valueOf(date);
	}
	
	
}
