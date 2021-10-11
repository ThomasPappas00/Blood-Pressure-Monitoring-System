package SimulatorClientSide;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ServerSide.Command;
import ServerSide.Measurement;
import ServerSide.Reply;
import ServerSide.Request;
import ServerSide.Response;

public class SimulatorClient {
	
	Measurement m = new Measurement();
	ObjectMapper mapper = new ObjectMapper();
	Request outPacket = new Request();
	
	public String doSend() throws IOException {
		outPacket.setMeasurement(m);   
		outPacket.setCommand(Command.APPEND_BPM);
		
		// make JSON format
		String jsonOut = null;
		try {
			jsonOut = mapper.writeValueAsString(outPacket);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} 
		
		//make HTTP POST request
		String jsonIn = postRequest(jsonOut); 
		
		
		//transform JSON to Reply object
		Reply inPacket = mapper.readValue(jsonIn, Reply.class);
		if(inPacket.resp == Response.BPM_APPENDED) {
			return inPacket.message; 
		} 
		
		return "Please try again";
		
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
	
	
	public void setName(String name) {
		m.setUserID(name);
	}
	
	public void setDate() {
		String pattern = "dd/MM/yyyy";
		String dateInString =new SimpleDateFormat(pattern).format(new Date());
		m.setDate(dateInString);
	}
	
	public void setHour() {
		int hour = LocalTime.now().getHour();
		int minute = LocalTime.now().getMinute();
		int second = LocalTime.now().getSecond();
		String str_minute = String.valueOf(minute);
		String str_second = String.valueOf(second);
		if(second<10) {
			str_second = "0" + str_second;
		}
		if(minute<10) {
			str_minute = "0" + str_minute;
		}
		
		m.setHour(String.valueOf(hour) + ":" + str_minute + ":" + str_second);
	}
	
	public void setSysBP() {
		int randomNum = ThreadLocalRandom.current().nextInt(95, 130 + 1);
		m.setSysBP(randomNum);
	}
	
	public void setDiasBP() {
		int randomNum = ThreadLocalRandom.current().nextInt(70, 110 + 1);
		m.setDiasBP(randomNum);
	}
	
	public void setHeartRate() {
		int randomNum = ThreadLocalRandom.current().nextInt(70, 100 + 1);
		m.setHeartRate(randomNum);
	}
	
}
