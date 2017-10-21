package plug;

import java.util.Date;
import java.sql.Time;

public class PlugData {
	public int id;
	public int consumption;
	public Date time;
	
	public PlugData(int id, int consumption) {
		this.id = id;
		this.consumption = consumption;
		this.time = new Date();
	}
	
	@Override
	public String toString() {
		return "[" + id + "," + consumption + "," + time.toString() + "]";
	}
	
}
