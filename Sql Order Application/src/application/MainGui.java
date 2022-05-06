package application;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGui {

	private JFrame frame;
	private JTextField textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * 
	 */
	public MainGui() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 601, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		SQL_order orders = new SQL_order();
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				orders.CloseConnection();
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 564, 192);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 214, 564, 36);
		frame.getContentPane().add(panel_1);
		
		DefaultListModel<String> listmodel = new DefaultListModel<String>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 544, 170);
		panel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setEnabled(false);
		
		scrollPane.setViewportView(list);
		
		JButton conBtn = new JButton("Connect");
		
		panel_1.add(conBtn);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					List <String> searchOrderSet = orders.stmtToString(textField.getText());
					listmodel.clear();
					for(int i = 0; i < searchOrderSet.size(); i++) {listmodel.addElement(searchOrderSet.get(i));}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				list.setModel(listmodel);
			}
		});
		
		textField.setEnabled(false);
		panel_1.add(textField);
		textField.setColumns(20);
		
		conBtn.addActionListener(new ActionListener() {
			//@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				orders.connect();
				
				try {
					List <String> orderset = orders.stmtToString("");
					
					for(int i = 0; i < orderset.size(); i++) {listmodel.addElement(orderset.get(i));}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				
				list.setModel(listmodel);
				
				
				System.out.println(list.getModel().getSize());;
				list.setEnabled(true);
				textField.setEnabled(true);
				conBtn.setEnabled(false);
			}
		});
	}
}
