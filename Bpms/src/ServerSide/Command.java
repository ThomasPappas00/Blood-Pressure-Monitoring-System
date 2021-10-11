package ServerSide;
public enum Command{  
	APPEND_BPM(1),    
	FIND_BPM(3),  
	GET_BOOK(20),     
	SORT_BOOK_DATE(23),  
	SORT_BOOK_SYS(24),
	SORT_BOOK_DIAS(25),
	SORT_BOOK_HR(26),
	CLEAR_BOOK(27),  
	NOOP(100);  
	public final int code; 

	private Command(int code) {
		this.code = code;  
	}  

	public int code(){
		return code;  
	} 
} 