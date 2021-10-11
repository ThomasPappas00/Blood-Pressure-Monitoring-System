package ServerSide;

import java.io.IOException;
import java.util.Scanner;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BpmsServerServlet")
public class BpmsServerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;	
	
	String connectionUrl =                 
			"jdbc:sqlserver://localhost:1433;"
            + "databaseName=BPMS_Book;"
			+ "integratedSecurity=true;";
	Connection connection = null;
	BloodPressureBook book = new BloodPressureBook();	
	BPBHandler bh = new BPBHandler(book);
	private int serves = 0;
	
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// this method is not necessary for the communication scheme, only HTTP Post is needed
	}       

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		synchronized(this) {
			//get JSON from client 
			String jsonIn = "";
			Scanner scanner = new Scanner(request.getInputStream());
			while(scanner.hasNextLine()){
				jsonIn += scanner.nextLine();
				jsonIn += "\n";
			}
			scanner.close();
			
			
			//make connection with local Microsoft SQL Database
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				connection = DriverManager.getConnection(connectionUrl);
				System.out.println("Server connected to the SQL Database");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
			
			//serve the client
			ClientHandler ch = new ClientHandler(jsonIn,book,bh,connection);
			String jsonOut = null;
			try {
				jsonOut = ch.handlerDo();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			response.getOutputStream().println(jsonOut); //send appropriate JSON to client
		}
		
		serves = serves + 1;
		System.out.println("Server has served (" + serves + ") requests");
	}		
}
