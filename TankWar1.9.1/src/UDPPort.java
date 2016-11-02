

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UDPPort {
	public static List<Integer> portList = new ArrayList<Integer>();
	Random rn = new Random();

	
	
	private int createPort() {
		return rn.nextInt(65535);
	}
	
	public int getPort() {
		boolean checkEquals = false;
		boolean check = true;
		int rPort = 0;
		
		while(check) {
			rPort = this.createPort();
			for(int i = 0; i < portList.size(); i++) {
				if(rPort == portList.get(i)) {
					checkEquals = true;
				}
				
			}
			if(checkEquals) {
				continue;
			}
			else {
				check = false;
			}
			
		}
		
		portList.add(rPort);
		return rPort;
	}
	
}
