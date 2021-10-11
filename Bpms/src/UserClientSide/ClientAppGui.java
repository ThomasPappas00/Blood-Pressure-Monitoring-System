package UserClientSide;
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

public class ClientAppGui {
	public UserClient uc;
	public Button button0, button1, button2;
	public Button button3, button4, button5;
	public Button button6,button7,button8,button9;
	public static Frame window;
	public static TextField display;
	
	ClientAppGui(UserClient uc){
		this.uc = uc;
		
	    window = new Frame("Health Monitoring Application");	        
	    window.setLayout(null);
	    window.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	    window.setBackground(Color.blue);
		
	    button0 = new Button("See all measurements");
	    button0.setBounds(64, 150, 150, 28);
	    button0.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	    button0.setForeground(Color.blue);  
	    button0.addActionListener(new Button0Handler());
	    window.add(button0);
	    
	    button1 = new Button("Sort by");      
	    button1.setBounds(64, 190, 100, 28);
	    button1.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	    button1.setForeground(Color.blue);    
	    button1.addActionListener(new Button1Handler());
	    window.add(button1);
	    
	    button2 = new Button("Date");      
	    button2.setBounds(90, 220, 100, 28);
	    button2.setFont(new Font("TimesRoman", Font.PLAIN, 14));
	    button2.setForeground(Color.blue);    
	    button2.addActionListener(new Button2Handler());
        window.add(button2);
        button2.setVisible(false);
	    
	    button3 = new Button("Systolic blood pressure");      
	    button3.setBounds(90, 250, 150, 28);
        button3.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button3.setForeground(Color.blue);    
        button3.addActionListener(new Button3Handler());
        window.add(button3);
        button3.setVisible(false);
        
        button4 = new Button("Diastolic blood pressure");
        button4.setBounds(90, 280, 150, 28);
        button4.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button4.setForeground(Color.blue);    
        button4.addActionListener(new Button4Handler());
        window.add(button4);
        button4.setVisible(false);
        
        button5 = new Button("Heart rate");      
        button5.setBounds(90, 310, 100, 28);
        button5.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button5.setForeground(Color.blue);    
        button5.addActionListener(new Button5Handler());
        window.add(button5);
        button5.setVisible(false);
        
        button6 = new Button("Search patient");      
        button6.setBounds(64, 350, 100, 28);
        button6.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button6.setForeground(Color.blue);    
        button6.addActionListener(new Button6Handler());
        window.add(button6);
        
        button7 = new Button("Submit name");      
        button7.setBounds(90, 380, 100, 28);
        button7.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button7.setForeground(Color.blue);    
        button7.addActionListener(new Button7Handler());
        window.add(button7);
        button7.setVisible(false);
       
	    
        button8 = new Button("Delete history");      
        button8.setBounds(64, 420, 100, 28);
        button8.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button8.setForeground(Color.blue);    
        button8.addActionListener(new Button8Handler());
        window.add(button8);
        
        button9 = new Button("Done");      
        button9.setBounds(500, 420, 100, 28);
        button9.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        button9.setForeground(Color.blue);    
        button9.addActionListener(new Button9Handler());
        window.add(button9);

	       display = new TextField("Please select an option and then press 'Done'");
	       display.setEditable(true);  
	       display.setBounds(13, 55, 580, 30);


	       window.add(display);         
	       window.setSize(650,500);
	       window.setLocation(40,80);        
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
	    		display.setText("Please press 'Done'");
	    		uc.seeAllMeasurements();
	      }
	  }
	
	class Button1Handler implements ActionListener{      
	     public void actionPerformed(ActionEvent pushingButton1){
	    	 button2.setVisible(true);
	    	 button3.setVisible(true);
	    	 button4.setVisible(true);
	    	 button5.setVisible(true);	    	 
	       }
	   }
	
	class Button2Handler implements ActionListener{      
	     public void actionPerformed(ActionEvent pushingButton2){
	    	 	display.setText("Please press 'Done'");
	    	 	uc.sortByDate();
	       }
	   }
	
	class Button3Handler implements ActionListener{     	 
	     public void actionPerformed(ActionEvent pushingButton3) { 
	    	display.setText("Please press 'Done'");
	    	uc.sortBySystolicBP();
	    }
	 }

	class Button4Handler implements ActionListener {     	 
	     public void actionPerformed(ActionEvent pushingButton4){ 
	    	 display.setText("Please press 'Done'");
	    	 uc.sortByDiastolicBP();
	     }
	}

	class Button5Handler implements ActionListener {     
	     public void actionPerformed(ActionEvent pushingButton5){ 
	    	 display.setText("Please press 'Done'");
	    	 uc.sortByHeartRate();
	    }
	}
	
	class Button6Handler implements ActionListener {     
	     public void actionPerformed(ActionEvent pushingButton6){ 
	    	 display.setText("Please enter patient's name");
	    	 button7.setVisible(true);
	    }
	}
	
	class Button7Handler implements ActionListener {     
	     public void actionPerformed(ActionEvent pushingButton7){ 
	    	 String name = display.getText();
	    	 display.setText("Please press 'Done'");
	    	 uc.searchPatientHistory(name);
	    }
	}
	
	class Button8Handler implements ActionListener {     
	     public void actionPerformed(ActionEvent pushingButton8){ 
	    	 	display.setText("Please press 'Done'");
	    	 	uc.clearBook();
	    }
	}
	
	class Button9Handler implements ActionListener {     
	     public void actionPerformed(ActionEvent pushingButton9){ 
	    	 button2.setVisible(false);
	    	 button3.setVisible(false);
	    	 button4.setVisible(false);
	    	 button5.setVisible(false);
	    	 button7.setVisible(false);
	    	 try {
				uc.sendToServer();
				String server_answer = uc.serverReply();
				display.setText(server_answer);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			}
	    }
	
}
