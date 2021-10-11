package UserClientSide;

import java.io.IOException;
import java.net.UnknownHostException;

public class BpmsUserApp {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		System.out.println("BPMSUserApp Started!\n");
		UserClient uc = new UserClient();
		ClientAppGui ag = new ClientAppGui(uc);	
		
	}
}

 
	
