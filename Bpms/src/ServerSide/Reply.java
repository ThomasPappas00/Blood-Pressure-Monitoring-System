package ServerSide;
import java.io.Serializable;
import java.util.ArrayList;

public class Reply implements Serializable{
	private static final long serialVersionUID = 1L;
	public Response resp;
	public Measurement m;
	public String message;
	public ArrayList<Measurement> book = new ArrayList<>();     //arraylist with measurements to send to the client
	public Reply(){}
	
	
	//setters
	public void setResponse(Response resp) {
		this.resp = resp;
	}
	
	public void setMeasurement(Measurement m) {
		this.m = m;
	}
	
	public void setMessage(String message) {
		this.message = message;		
	}
	
	public void setBook(ArrayList<Measurement> book) {
		this.book = book;
	}
	
	//getters
	public Response getResponse() {
		return resp;
	}
	
	public Measurement getMeasurement() {
		return m;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ArrayList<Measurement> getBook(){
		return book;
	}
	
	public String toString() {
		return "Reply [resp=" + resp + ", m=" + m + ", message=" + message +", book=" + book +"]";
	}
}