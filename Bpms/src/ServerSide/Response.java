package ServerSide;

public enum Response {  
	BPM_FOUND(101),
	BPM_NOT_FOUND(102),
	BPM_APPENDED(103),
	BOOK_LOADED(122),
	BOOK_SORTED(123),
	BOOK_CLEARED(124),
	BOOK_EMPTY(125),
	NO_ACTION_CMD(152);
	
	private final int resCode; 
	
	private Response(int resCode) {
		this.resCode = resCode;      
	}  
	public int resCode(){
		return resCode;      
	} 
}
