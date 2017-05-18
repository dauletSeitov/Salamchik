package kz.loungerIntertinment.FireHorse;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginSignUp {

	private JFrame frmLoginsignUp;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label;
	private JTextField txtUsername;
	private JLabel lblName;
	private ServerConnecion connection = new MyConnection ();
	private String sender;
	private JLabel lblNotification;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSignUp window = new LoginSignUp();
					window.frmLoginsignUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginSignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginsignUp = new JFrame();
		frmLoginsignUp.setTitle("Salamchik: Login/Sign up");
		frmLoginsignUp.setBounds(100, 100, 450, 300);
		frmLoginsignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginsignUp.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(87, 93, 46, 14);
		frmLoginsignUp.getContentPane().add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(87, 118, 73, 14);
		frmLoginsignUp.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(170, 90, 135, 20);
		frmLoginsignUp.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(170, 115, 135, 20);
		frmLoginsignUp.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSignUp = new JLabel("Sign up");
		lblSignUp.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSignUp.setForeground(Color.BLUE);
		lblSignUp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblSignUp.setForeground(Color.BLUE);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblSignUp.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
								
				if (lblSignUp.getText().equals("Sign up")){ 
					label.setVisible(true);
					lblName.setVisible(true);
					textField_2.setVisible(true);
					txtUsername.setVisible(true);
					lblSignUp.setText("Login");
					}
				else{
					label.setVisible(false);
					lblName.setVisible(false);
					textField_2.setVisible(false);
					txtUsername.setVisible(false);
					lblSignUp.setText("Sign up");
					}
				
			}
		});
		lblSignUp.setBounds(378, 237, 46, 14);
		frmLoginsignUp.getContentPane().add(lblSignUp);
		
		JLabel lblForgot = new JLabel("Forgot");
		lblForgot.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblForgot.setForeground(Color.BLUE);
		lblForgot.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				//System.out.println("Do new account");
				JOptionPane.showMessageDialog(null, "oh really? Sorry but nothing i can do. Create new account.","Forgot", JOptionPane.INFORMATION_MESSAGE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblForgot.setForeground(Color.BLUE);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblForgot.setForeground(Color.BLACK);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		lblForgot.setBounds(307, 237, 46, 14);
		frmLoginsignUp.getContentPane().add(lblForgot);
		
		textField_2 = new JTextField();
		textField_2.setVisible(false);
		textField_2.setBounds(170, 146, 135, 20);
		frmLoginsignUp.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		label = new JLabel("Password");
		label.setVisible(false);
		label.setBounds(87, 149, 73, 14);
		frmLoginsignUp.getContentPane().add(label);
		
		JButton btnSublit = new JButton("Sublit");
		btnSublit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			    boolean result = false;
				if (lblSignUp.getText().equals("Sign up")){ 
					String login = textField.getText();
					String password = textField_1.getText();
					if (login.indexOf(' ') >= 0)  {lblNotification.setText("here most be no spases"); return;}
					if (login.indexOf('\'') >= 0) {lblNotification.setText("here most be no '"); return;}
					
					if (password.indexOf(' ') >= 0)  {lblNotification.setText("here most be no spases"); return;};
					if (password.indexOf('\'') >= 0) {lblNotification.setText("here most be no '"); return;};
					
					//System.out.println(textField.getText());//loging
					//System.out.println(textField_1.getText());//password
					
					result = connection.login(login, password);
					
					if (!result){lblNotification.setText("user or password is not correct or connection faild"); return;};
					sender = login;
					
					
				}
				else{
					String name = txtUsername.getText().replaceAll("'", "''");
					String login 			= textField.getText();
					String password 		= textField_1.getText();
					String secondPassword 	= textField_2.getText();
					
					//System.out.println(txtUsername.getText().replaceAll("'", "''"));//name
					//System.out.println(textField.getText());//loging
					//System.out.println(textField_1.getText());//password				
					//System.out.println(textField_2.getText());//password seconf
					
					
					if (login.indexOf(' ') >= 0)  {lblNotification.setText("here most be no spases"); return;};
					if (login.indexOf('\'') >= 0) {lblNotification.setText("here most be no '"); return;}
					
					if (password.indexOf(' ') >= 0)  {lblNotification.setText("here most be no spases"); return;};
					if (password.indexOf('\'') >= 0) {lblNotification.setText("here most be no '"); return;};
					if (!password.equals(secondPassword)) {lblNotification.setText("passwords is not equals"); return;};
									
					result = connection.signUp(name, login, secondPassword);
					if (!result){lblNotification.setText("login is not unique or connection faild"); return;};
					sender = login;
				}
				
				if(result){
					ListOfUsers ls = new ListOfUsers(LoginSignUp.this.sender, connection);
					
					LoginSignUp.this.frmLoginsignUp.dispose();
				}
				
			}
		});
		btnSublit.setBounds(216, 177, 89, 23);
		frmLoginsignUp.getContentPane().add(btnSublit);
		
		txtUsername = new JTextField();
		//txtUsername.setText("userName");
		txtUsername.setBounds(170, 59, 135, 20);
		frmLoginsignUp.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setVisible(false);
		
		lblName = new JLabel("Name");
		lblName.setBounds(87, 62, 46, 14);
		lblName.setVisible(false);
		frmLoginsignUp.getContentPane().add(lblName);
		
		lblNotification = new JLabel();
		lblNotification.setForeground(Color.RED);
		lblNotification.setBounds(87, 237, 210, 14);
		frmLoginsignUp.getContentPane().add(lblNotification);
		
	}
}
