package application;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

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
	 */
	public MainGui() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		SQL_order orders = new SQL_order();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 564, 192);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 214, 564, 36);
		frame.getContentPane().add(panel_1);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 544, 170);
		panel.add(scrollPane);
		
		JList list = new JList();
		list.setEnabled(false);
		
		scrollPane.setViewportView(list);
		
		JButton conBtn = new JButton("Connect");
		
		panel_1.add(conBtn);
		
		textField = new JTextField();
		textField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println(list.getModel());
			}
		});
		textField.setEnabled(false);
		panel_1.add(textField);
		textField.setColumns(20);
		
		conBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orders.connect();
				
				try {
					list.setModel(new AbstractListModel() {
						public List<String> values = orders.stmtToString();
						public int getSize() {
							return values.size();
						}
						public Object getElementAt(int index) {
							return values.get(index);
						}
					});
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(list.getModel().getSize());;
				list.setEnabled(true);
				textField.setEnabled(true);
			}
		});
	}
}
