package ServerSide;
import java.io.Serializable;

public class Request implements Serializable{		
	private static final long serialVersionUID = 1L;
	public Command cmd;
	public Measurement m;
	public String message;
	public Request() {}
	
	 //setters
	public void setCommand(Command cmd) {
		this.cmd = cmd;
	}
	
	public void setMeasurement(Measurement m) {
		this.m = m;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	//getters
	public Command getCommand() {
		return cmd;
	}
	
	public Measurement getMeasurement() {
		return m;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "Request [cmd=" + cmd + ", m=" + m + ", message=" + message +"]";
	}
}