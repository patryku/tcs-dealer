package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import sql.PaintManager;
import javax.swing.JTextArea;

public class AdminPaintList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private static AdminPaintList instance = null; 
	private JPanel panel;
	private JTextArea resultArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPaintList frame = getInstance();
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
	private AdminPaintList() {
		setTitle("Lista kolorow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setRows(20);
		resultArea.setColumns(20);
		
		panel.add(resultArea);
		
		try{
			ResultSet rs = PaintManager.getPaints();
			int num = 1;
			while(rs.next()){
				resultArea.append(num + " " + rs.getString("typ_lakieru") + " " + rs.getString("kolor") + "\n");
				num++;
			}
		}catch(SQLException e){
			resultArea.append("Nie nawiazano polaczenia z baza");
		}
		
		pack();
		toFront();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static AdminPaintList getInstance(){
		if(instance == null)
			instance = new AdminPaintList();
		return instance;
	}

}
