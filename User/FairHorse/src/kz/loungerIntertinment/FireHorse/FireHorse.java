package kz.loungerIntertinment.FireHorse;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

public class FireHorse implements TextSetAble{

	 private JFrame window;
     private JTextArea textArea;
     private JTextField textField;
     private JButton button;
     private ServerConnecion connection;
     private java.util.Timer timer;
     private String setter;
     private String getter;
     private JScrollPane scroll;
     
     /**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerConnecion connection = new MyConnection ();
										
					FireHorse fireHorse = new FireHorse(connection, "");
					fireHorse.window.setVisible(true);
					
					java.util.Timer timer = new java.util.Timer();
	                timer.schedule(new SayHello(fireHorse, connection, getter, setter), 0, 1000);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/	

	/**
	 * Create the application.
	 */
	public FireHorse(ServerConnecion connection, String setter, String getter) {
		this.setter = setter;
		this.getter = getter;
		initialize(connection);
	}

	public void setText(String s){
        
		if(textArea.getText().length() != s.length()){
			textArea.setText(s);
			JScrollBar vertical = scroll.getVerticalScrollBar();
			vertical.setValue( vertical.getMaximum() );
			//System.out.println(s);
		}
	}
	
	public void dispose(){
        
		window.dispose();

	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ServerConnecion connection) {

		
		//--connection things	
		this.connection = connection ;
		
		
		
        window = new JFrame("Salamchik: " + this.getter);
        window.setVisible(true);
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        window.setBounds(20, 20, 440, 388);
        window.getContentPane().setLayout(null);
        window.addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent windowEvent) {
        		window.dispose();
            }
		});

        textField = new JTextField();
        textField.setBounds(10, 320, 350, 20);
        textField.setColumns(10);
        textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(textField.getText().trim().length() != 0){
					//	System.out.println(FireHorse.this.setter + " " + FireHorse.this.getter  + " " + textField.getText());
						connection.setText(FireHorse.this.setter, FireHorse.this.getter ,Utilite.trnslate(textField.getText()));
						textField.setText("");
					}
				}
				
			}
		});
        window.getContentPane().add(textField);

        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        textArea.setColumns(20);
        //textArea.setBounds(10, 10, 400, 300);
        //textArea.setBorder(textField.getBorder());
        textArea.setLineWrap(true);

        scroll = new JScrollPane ();
        scroll.setViewportView(textArea);
        scroll.setBounds(10, 10, 400, 300);
        window.getContentPane().add(scroll);

        button = new JButton("go");
        button.setBounds(360, 320, 50, 20);
        button.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().trim().length() != 0){
					//System.out.println(FireHorse.this.setter + " " + FireHorse.this.getter  + " " + textField.getText());
					connection.setText(FireHorse.this.setter, FireHorse.this.getter ,Utilite.trnslate(textField.getText()));
					textField.setText("");
				}
 			}
		});

        window.getContentPane().add(button);
        
        ////
        
        timer = new java.util.Timer();
        timer.schedule(new SayHello(this, connection, this.setter, this.getter), 0, 1000);
        //

	}
}

class SayHello extends TimerTask {
    
    private TextSetAble textSetAble ;
    private ServerConnecion serverConnecion;
    private String setter;
    private String getter;

    SayHello(TextSetAble textSetAble, ServerConnecion serverConnecion, String setter, String getter){
    	this.textSetAble = textSetAble;
    	this.serverConnecion = serverConnecion;
    	this.setter = setter;
    	this.getter = getter;
    }
            public void run() {
            	
            	String message = serverConnecion.getText(setter, getter);
            	textSetAble.setText(message);
            	
            	if(serverConnecion.doIHaveNewMessage(this.setter)){
            		
            		String lastLine = message.substring(message.lastIndexOf("\n",message.length()-2)).replace("\n", ""); 
            		String title = lastLine.substring(0, lastLine.indexOf(":"));
            		String lastMessag = lastLine.substring(lastLine.indexOf(":") + 2);
            		
            		
            		//System.out.println(title);
        			//System.out.println(lastMessag);
        			
            		Utilite.notificate(title, lastMessag);
            	}
            	
            	
    }
}








