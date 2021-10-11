package SimulatorClientSide;
import java.io.IOException;

public class BpmsBPMSimulator {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("BPMSimulator Started...");		
		SimulatorClient sc = new SimulatorClient();
		SimulatorGui sg = new SimulatorGui(sc);

	}

}