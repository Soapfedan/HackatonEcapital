package database;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ThresholdAlgorithm {
	
	public static final int CONSUMO_TOT = 2900; 
	
	public static boolean checkSogliaMassima() {
		int consAtt = Consumo.getTotConsumoInt();
		return consAtt < CONSUMO_TOT;
	}
	
	public static boolean checkSogliaMassima(int s) {
		return s < CONSUMO_TOT;
	}
	
	public static void shutdownScheduling() {
		
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
			
			fakeSpegnimento(pl);
			
			consAtt -= cons;
			
			ok = checkSogliaMassima(consAtt);
			//if(ok) break;
			
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
	
	private static void fakeSpegnimento(int r) {
		r=0;
	}
}
