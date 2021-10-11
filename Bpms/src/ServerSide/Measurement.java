package ServerSide;
import java.io.Serializable;

public class Measurement implements Serializable{	
	private static final long serialVersionUID = 1L;
	public String date;
	public String hour;
	public int sysBP;
	public int diasBP;
	public int heartRate;
	public String userID;
	
	public Measurement(){};
	public Measurement(String date,String hour, int sysBP,int diasBP, int heartRate, String userID){
		this.date = date;
		this.hour = hour;
		this.sysBP = sysBP;
		this.diasBP = diasBP;
		this.heartRate = heartRate;
		this.userID = userID;
	}
	
	public void setDate(String s) {
		this.date = s;
	}
	
	public void setHour(String s) {
		this.hour = s;
	}
	
	public void setSysBP(int i) {
		this.sysBP = i;
	}
	
	public void setDiasBP(int i) {
		this.diasBP = i;
	}
	
	public void setHeartRate(int i) {
		this.heartRate = i;
	}
	
	public void setUserID(String s) {
		this.userID = s;
	}
}
