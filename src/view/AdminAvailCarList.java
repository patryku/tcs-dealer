package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import sql.BuyACarManager;

public class AdminAvailCarList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327043445757657040L;
	private JPanel contentPane;
	private static AdminAvailCarList instance = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAvailCarList frame = new AdminAvailCarList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private AdminAvailCarList() {
		setTitle("Lista samochodow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JTextArea dialogArea = new JTextArea();
		dialogArea.setText("Lista dostepnych samochodow:\n");
		dialogArea.setRows(15);
		dialogArea.setColumns(40);
		dialogArea.setEditable(false);
		
		panel.add(dialogArea);
		pack();
		toFront();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try{
			ResultSet rs = BuyACarManager.getCars();
			while(rs.next()){
				dialogArea.append(rs.getString("vin") + " " + rs.getString("rok_produkcji") + " " + rs.getString("producent") + " "
					+ rs.getString("model") + "   numer_placowki: " + rs.getString("placowka") + "\n");
			}
		}catch(SQLException e){
			dialogArea.append("Nie nawiazano polaczenia z baza");
		}
	}
	
	public static AdminAvailCarList getInstance(){
		if(instance == null)
			instance = new AdminAvailCarList();
		return instance;
	}

}
