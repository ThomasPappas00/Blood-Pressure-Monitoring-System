package UserClientSide;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ServerSide.Measurement;
import ServerSide.Command;
import ServerSide.Request;
import ServerSide.Reply;

public class UserClient{
	Request outPacket = new Request();	
	Reply inPacket = new Reply();
	ObjectMapper mapper = new ObjectMapper();
	String jsonIn = null;
	
	
	public void seeAllMeasurements() {
		outPacket.setCommand(Command.GET_BOOK);
	}
	
	public void sortByDate() {
		outPacket.setCommand(Command.SORT_BOOK_DATE);
	}
	
	public void sortBySystolicBP() {
		outPacket.setCommand(Command.SORT_BOOK_SYS);
	}
	
	public void sortByDiastolicBP() {
		outPacket.setCommand(Command.SORT_BOOK_DIAS);
	}
	
	public void sortByHeartRate() {
		outPacket.setCommand(Command.SORT_BOOK_HR);
	}
	
	public void searchPatientHistory(String name) {
		outPacket.setCommand(Command.FIND_BPM);
		outPacket.setMessage(name);
	}
	
	public void clearBook() {
		outPacket.setCommand(Command.CLEAR_BOOK);
	}
	
	public void sendToServer() throws IOException {
		//make JSON
		String jsonOut = null;
		try {
			jsonOut = mapper.writeValueAsString(outPacket);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} 
		String jsonIn = postRequest(jsonOut);  //send and receive in JSON format
		inPacket = mapper.readValue(jsonIn, Reply.class); //make Reply from JSON input
	}

	
	public String serverReply() {
			switch(inPacket.resp) { 
			case BPM_FOUND:
				displayAllMeasurements();                
				return getAllMeasurementStrings();
			case BPM_NOT_FOUND:
				System.out.println("Patient not found!");
				return "Patient not found!";
			case BOOK_SORTED:
				System.out.println("Book sorting completed!");
				return "Book sorting completed!";
			case BOOK_LOADED:
				displayAllMeasurements();                
				return getAllMeasurementStrings();
			case BOOK_EMPTY:
				System.out.println("There is no patient history available in server!");
				return "There is no patient history available in server!";
			case BOOK_CLEARED: 
				System.out.println("History deleted!");
				return "History deleted!";
			case NO_ACTION_CMD:
				System.out.println("Pending...");
				return "Pending...";
			default:
				return "Error";
		}
	}
	
	public void displayMeasurement(Measurement m) {
		System.out.println(m.date + " | " + m.hour + " | " + m.sysBP + "mmHG systolic | " + m.diasBP +"mmHG diastolic | "+ m.heartRate +" HeartBeats/min | "+ m.userID + " ||");
	}
	
	public void displayAllMeasurements() {
		System.out.println("-------------------------");
		for(Measurement x:inPacket.book) {
			displayMeasurement(x);
		}	
		System.out.println("-------------------------");
	}
	
	public String getMeasurementString(Measurement m) {
		return m.date + " | " + m.hour + " | " + m.sysBP + "mmHG systolic | " + m.diasBP +"mmHG diastolic | "+ m.heartRate +" HeartBeats/min | "+ m.userID + " ||";
	}
	
	public String getAllMeasurementStrings() {
		String s = "Please see the 'Application Console' or scroll right here ---> ";
		for(Measurement x:inPacket.book) {
			s += getMeasurementString(x);
		}	
		return s;
	}
	
	public static String postRequest(String jsonOut) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:80/Bpms/BpmsServerServlet").openConnection();

		connection.setRequestMethod("POST");	
		connection.setDoOutput(true);
	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	    wr.write(jsonOut);
	    wr.flush();
		
		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			String response = "";
			Scanner scanner = new Scanner(connection.getInputStream());
			while(scanner.hasNextLine()){
				response += scanner.nextLine();
				response += "\n";
			}
			scanner.close();

			return response;
		}
		else if(responseCode == 401){
			System.out.println("Wrong password.");
		}
		return null;
	}

}
