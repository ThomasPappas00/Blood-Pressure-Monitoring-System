package SimulatorClientSide;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SimulatorGui {

	public SimulatorClient sc;
	public Button button0, button1;
	public static Frame window;
	public static TextField display;
	
	SimulatorGui(SimulatorClient sc){
		this.sc = sc;
		
	    window = new Frame("Blood Pressure Monitor Simulator");	        
	    window.setLayout(null);
	    window.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	    window.setBackground(Color.gray);
		
	    button0 = new Button("Insert name");
	    button0.setBounds(64, 120, 120, 28);
	    button0.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	    button0.setForeground(Color.blue);  
	    button0.addActionListener(new Button0Handler());
	    window.add(button0);
	    
        
        button1 = new Button("Start");      
        button1.setBounds(315, 220, 100, 28);
        button1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button1.setForeground(Color.blue);    
        button1.addActionListener(new Button1Handler());
        window.add(button1);

	       display = new TextField("Please type the patient's name here and press 'Insert name'");
	       display.setEditable(true);  
	       display.setBounds(13, 55, 440, 30);


	       window.add(display);         
	       window.setSize(470,300);
	       window.setLocation(1000,80);        
	       window.setVisible(true);
	       window.setResizable(false);  
	       window.addWindowListener(new CloseWindowAndExit()); 
	                    
	    
	}
	
	class CloseWindowAndExit extends WindowAdapter{
		  public void windowClosing(WindowEvent closeWindowAndExit){
		    System.exit(0);
		   }
	} 
	
	class Button0Handler implements ActionListener{     
	    public void actionPerformed(ActionEvent pushingButton0){
	    		String name = display.getText();
	    		display.setText("Press 'Start' to make measurement");
	    		sc.setName(name);    		
	      }
	  }
	
	class Button1Handler implements ActionListener{      
	     public void actionPerformed(ActionEvent pushingButton1){
	    	 	sc.setDate();
	    	 	sc.setHour();
	    	 	sc.setSysBP();
	    	 	sc.setDiasBP();
	    	 	sc.setHeartRate();
	    	 	try {
					String answer = sc.doSend();
					display.setText(answer);
					TimeUnit.SECONDS.sleep(4);
					display.setText("--------------------------------------------------------------------------------------------");
					TimeUnit.SECONDS.sleep(2);
					display.setText("Please type the patient's name here and press 'Insert name'");

				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}	    	 	
	       }
	   }
	
}
