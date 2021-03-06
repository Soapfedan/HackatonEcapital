package database;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.sun.nio.sctp.Notification;

import plug.PlugConnection;
import server.NotificationGenerator;

public class ThresholdAlgorithm {
	
	public static final int CONSUMO_TOT = 2000; 
	
	public static void start() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				do {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {}
					if(!checkSogliaMassima()) {
						shutdownScheduling();
					}
				} while(true);
			}
		}).start();;
	}
	
	public static boolean checkSogliaMassima() {
		int consAtt = Consumo.getTotConsumoInt();
		return consAtt < CONSUMO_TOT;
	}
	
	public static boolean checkSogliaMassima(int s) {
		return s < CONSUMO_TOT;
	}
	
	public static void shutdownScheduling() {
		Thread notify = new Thread(
				NotificationGenerator.notificationThread(true, "Attenzione! Hai superato il limite di consumo"));
		notify.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e2) {
		}
		
		int priorita = 1;
		int[] currPlug;
		ArrayList<Integer> spenti = new ArrayList<Integer>();
		do {
			boolean ok = false;
			int consAtt = Consumo.getTotConsumoInt();
			currPlug = new int[2];
			currPlug = Consumo.getMaxConsXPriority(priorita,spenti);
			int pl = currPlug[0];
			int cons = currPlug[1];
			
			PlugConnection.setPlug(pl, false);
			
			consAtt -= cons;
			
			ok = checkSogliaMassima(consAtt);
			if(ok) {
				
				break;
			} else {
				
			}
			
			spenti.add(pl);
			
			if(spenti.size()==Attuatore.countByPriority(priorita)) {
				priorita++;
				spenti.clear();
			}
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} while(priorita<=9);
	}
	
}
