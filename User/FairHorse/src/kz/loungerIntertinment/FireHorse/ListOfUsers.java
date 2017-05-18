package kz.loungerIntertinment.FireHorse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ListOfUsers {

	private JFrame frmFriends;
	private ServerConnecion connection;
	private JTable table;
	private FireHorse fe;
	private String sender;
	private String getter;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						
					//ListOfUsers window = new ListOfUsers("user");
					//window.frmFriends.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListOfUsers(String sender, ServerConnecion connection) {
		this.connection = connection; 
		this.sender = sender;
		initialize();
	}
	
	class MyModel extends DefaultTableModel{ //thank you for good peoples
		//http://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable

		public MyModel(Object [][] table, Object []columns){
			super(table, columns);
		}
		public boolean isCellEditable(int a,int b){
			
			return false;
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void refreshList(){
		
		MyModel model = (MyModel)table.getModel();
		
		model.setRowCount(0);
		
		for (String [] row : connection.getAllUser()) {
			model.addRow(row);
		};
		
		table.setModel(model);
		model.fireTableDataChanged();
		
		
		System.out.println(connection.getAllUser());
		
	}
	
	private void initialize() {
		
		frmFriends = new JFrame("Salamchik: Friends");
		frmFriends.setBounds(100, 100, 304, 505);
		frmFriends.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmFriends.getContentPane().setLayout(null);
		frmFriends.setVisible(true);
		String[] columnNames = {"Name",
				"identifier",
				"second name"};
		
		
		Object[][] data = {
			    {"seems", "like", "error"}
			};
		
		MyModel dtm = new MyModel(data, columnNames);
				
		table = new JTable(dtm);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
		

			public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        
		        if (me.getClickCount() == 2) {
		        	//here we call message window
		        	try{
		        		ListOfUsers.this.getter  = (String)table.getValueAt(row, 1);
		        	}catch(Exception e){return;} //do nothing
		        	//if (fe != null)
		        	
		        	//test
		        	/*System.out.println("---------------------------");
		        	System.out.println(connection.login("Daulet", "Daulet"));
		        	System.out.println("---------------------------");
		        	*/
		        	//ListOfUsers.this.sender = "Daulet";
		        	//ListOfUsers.this.getter = "Abzal";
		        	//test
		        	fe = new FireHorse(connection, ListOfUsers.this.sender, ListOfUsers.this.getter);
		        	
		        	//fe.likeMain();
		        	
		            //System.out.println("yaho " + row);
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		frmFriends.getContentPane().setLayout(new BorderLayout());
		frmFriends.getContentPane().add(table.getTableHeader(), BorderLayout.PAGE_START);
		frmFriends.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		refreshList();
		//frmFriends.add(list);
	}
}
