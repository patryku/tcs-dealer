package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import sql.VersionManager;

public class AdminSearchVer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3751907295444339323L;
	private JPanel contentPane;
	private JTextField prodField;
	private JTextField modelField;
	private JTextField verField;
	private static AdminSearchVer instance = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSearchVer frame = new AdminSearchVer();
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
	public AdminSearchVer() {
		setTitle("Wyszukiwarka wersji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel searchPanel = new JPanel();
		panel.add(searchPanel);
		
		prodField = new JTextField();
		prodField.setToolTipText("Szukany producent");
		searchPanel.add(prodField);
		prodField.setColumns(10);
		
		modelField = new JTextField();
		modelField.setToolTipText("Szukany model");
		searchPanel.add(modelField);
		modelField.setColumns(10);
		
		verField = new JTextField();
		verField.setToolTipText("Szukana wersja");
		searchPanel.add(verField);
		verField.setColumns(10);
		
		
		
		JPanel resultPanel = new JPanel();
		panel.add(resultPanel);
		
		final JTextArea resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setRows(15);
		resultArea.setColumns(40);
		resultPanel.add(resultArea);
		
		JButton searchButton = new JButton("Szukaj");
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resultArea.setText("");
				try{
					ResultSet rs = VersionManager.searchVersion(prodField.getText(), modelField.getText(), verField.getText());
					while(rs.next()){
						resultArea.append(rs.getString("id") + " " + rs.getString("producent") + " " + rs.getString("model") + " " +
								rs.getString("wersja") + " " + rs.getString("cena") + "\n");
					}
				}catch(SQLException e){
					resultArea.append("Nie nawiazano polaczenia z baza");
				}
			}
		});
		searchPanel.add(searchButton);
		
		pack();
		toFront();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static AdminSearchVer getInstance(){
		if(instance == null)
			instance = new AdminSearchVer();
		return instance;
	}
	
}
