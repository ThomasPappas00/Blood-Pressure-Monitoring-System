package ServerSide;

import java.util.Collections;

public class BPBHandler{
	
	BloodPressureBook book;
	
	public BPBHandler(BloodPressureBook book) {
		this.book = book;
	}
	
	public void putInBuffer(BloodPressureBook book, Measurement...measurements) {
		for(Measurement x:measurements) {
			book.buffer.add(x);
		}			
	}
	
	public int dateToInteger(String s) {       /* paradeigma: string 07/12/2019 ginetai-->  int 20191207 (etsi wste na ginei swsta h sygkrish dyo dates) */
		int intDate = Integer.parseInt(s.substring(6,10) + s.substring(3, 5) + s.substring(0, 2));
		return intDate;
	}
	
	public void sortByDate(BloodPressureBook book) {    //sorting me akriveia leptou
		
		for(int i=0;i<book.buffer.size();i++) {	 //epanalipsh gia buffer.size() fores etsi wste na mpoun oles oi metrhseis se fthinoysa seira
			
			int date = 21001231;   //max date = 31/12/2100 gia paradeigma
			int hour = 2359;         //max hour
			int j = -1;
			for(Measurement x:book.buffer) {      // h pio palia metrhsh tha paei sto telos 
				int prev_date = date;
				int prev_hour = hour;
				hour = Integer.parseInt(x.hour.substring(0,2)+x.hour.substring(3,5));
				date = dateToInteger(x.date);
				if(prev_date<date||((prev_date==date)&&(prev_hour<hour))) {
					Collections.swap(book.buffer,j,j+1);
				}
				j++;
			}
		}	                 
	}
	
	public void sortBySystolicBP(BloodPressureBook book) {
		
		for(int i=0;i<book.buffer.size();i++) {
			int sys = 300;
			int j = -1;
			for(Measurement x:book.buffer) {
				int prev_sys = sys;
				sys = x.sysBP;
				if(prev_sys<sys) {
					Collections.swap(book.buffer, j, j+1);
				}
				j++;
			}
		}
	}
	
	public void sortByDiastolicBP(BloodPressureBook book) {
		
		for(int i=0;i<book.buffer.size();i++) {
			int dias = 300;
			int j = -1;
			for(Measurement x:book.buffer) {
				int prev_dias = dias;
				dias = x.diasBP;
				if(prev_dias<dias) {
					Collections.swap(book.buffer, j, j+1);
				}
				j++;
			}
		}
	}
	
	public void sortByHeartRate(BloodPressureBook book) {
		
		for(int i=0;i<book.buffer.size();i++) {
			int hr = 300;
			int j = -1;
			for(Measurement x:book.buffer) {                     
				int prev_hr = hr;
				hr = x.heartRate;
				if(prev_hr<hr) {
					Collections.swap(book.buffer, j, j+1);
				}
				j++;
			}
		}
	}
}

