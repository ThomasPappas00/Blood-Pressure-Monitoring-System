package ServerSide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientHandler{
	Reply outPacket = new Reply();
	Request inPacket = new Request();
	ObjectMapper mapper = new ObjectMapper();
	String jsonIn = null;
	BloodPressureBook book;
	BloodPressureBook pers_book = new BloodPressureBook();  //personal book for a patient
	BPBHandler bh;
	Connection conn;
	PreparedStatement p_statement = null;
	PreparedStatement p_statement1 = null;
	String sel_all_query = "SELECT * FROM MEASUREMENT";
	Statement stmt;

	ClientHandler(String jsonIn,BloodPressureBook book,BPBHandler bh, Connection conn) {
		this.jsonIn = jsonIn;
		this.book = book;
		this.bh = bh;
		this.conn = conn;
	}
	
	public String handlerDo() throws JsonProcessingException, SQLException {
			inPacket = mapper.readValue(jsonIn, Request.class);  // make the input JSON a Request object
			
			String in_row= "INSERT INTO MEASUREMENT (date,hour,sysBP,diasBP,heartRate,userID)"
					+ "VALUES ( ?, ?, ?, ?, ?, ?)";
			p_statement = conn.prepareStatement(in_row);	
							
			switch(inPacket.cmd) {          //funcionality depending on cmd
				case APPEND_BPM:
					p_statement.setString(1,inPacket.m.date);
					p_statement.setString(2,inPacket.m.hour);
					p_statement.setInt(3,inPacket.m.sysBP);
					p_statement.setInt(4,inPacket.m.diasBP);
					p_statement.setInt(5,inPacket.m.heartRate);
					p_statement.setString(6,inPacket.m.userID);
					p_statement.executeUpdate();
					outPacket.setResponse(Response.BPM_APPENDED);
					outPacket.setMessage("Successfully inserted measurement of patient: " + inPacket.m.userID);
					break;
				case FIND_BPM:
					if(pers_book.buffer.size() > 0) {pers_book.buffer.clear();}
					String sel_query = "SELECT * FROM MEASUREMENT WHERE userID = ?";
					p_statement1 = conn.prepareStatement(sel_query);
					p_statement1.setString(1,inPacket.message);
					ResultSet rs = p_statement1.executeQuery();						
					setMeasurementsInBook(rs, pers_book);
					if(pers_book.buffer.size() < 1) {
						outPacket.setResponse(Response.BPM_NOT_FOUND);
					}else {
			    		outPacket.setResponse(Response.BPM_FOUND);
			    		outPacket.setBook(pers_book.buffer);
					}		    
					break;    
				case SORT_BOOK_DATE:   
					checkBookEmpty(book);
					bh.sortByDate(book);  
					outPacket.setResponse(Response.BOOK_SORTED);
					break;		
				case SORT_BOOK_SYS:
					checkBookEmpty(book);
					bh.sortBySystolicBP(book);
					outPacket.setResponse(Response.BOOK_SORTED);
					break;
				case SORT_BOOK_DIAS:
					checkBookEmpty(book);
					bh.sortByDiastolicBP(book);
					outPacket.setResponse(Response.BOOK_SORTED);
					break;
				case SORT_BOOK_HR:
					checkBookEmpty(book);
					bh.sortByHeartRate(book);
					outPacket.setResponse(Response.BOOK_SORTED);
					break;
				case GET_BOOK:		
					checkBookEmpty(book);
					if(book.buffer.size() < 1) {
						outPacket.setResponse(Response.BOOK_EMPTY);
					}
					else { 
						outPacket.setResponse(Response.BOOK_LOADED);
						outPacket.setBook(book.buffer);
					}	
					break;	
				case CLEAR_BOOK:
					Statement del_stmt = conn.createStatement();
					del_stmt.executeUpdate("DELETE FROM MEASUREMENT");
					book.buffer.clear();
					outPacket.setResponse(Response.BOOK_CLEARED);
					break;	
				case NOOP:
					outPacket.setResponse(Response.NO_ACTION_CMD);
					break;						
		}
		String jsonOut = mapper.writeValueAsString(outPacket);	//transform Reply object to JSON	
		if(outPacket.getResponse().resCode() == 122) {book.buffer.clear();}
		
		return jsonOut;			
	}
	
	
	private void setMeasurementsInBook(ResultSet rs, BloodPressureBook book) throws SQLException {
	    while(rs.next()) {
	    	Measurement m = new Measurement();
	        m.setDate  ( rs.getString("date") );
	        m.setHour  ( rs.getString("hour") );
	        m.setSysBP  ( rs.getInt("sysBP") );
	        m.setDiasBP  ( rs.getInt("diasBP") );
	        m.setHeartRate  ( rs.getInt("heartRate") );
	        m.setUserID  ( rs.getString("userID") );
	        bh.putInBuffer(book, m);
	    }
	}
	
	private void checkBookEmpty(BloodPressureBook book) throws SQLException {
		if(book.buffer.size() < 1) {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sel_all_query);
			setMeasurementsInBook(rs,book);
		}	
	}
}
