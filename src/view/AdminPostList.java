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

import sql.PostManager;

public class AdminPostList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327043445757657040L;
	private JPanel contentPane;
	private static AdminPostList instance = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPostList frame = new AdminPostList();
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
	private AdminPostList() {
		setTitle("Lista placowek");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JTextArea dialogArea = new JTextArea();
		dialogArea.setText("Lista placowek:\n");
		dialogArea.setRows(15);
		dialogArea.setColumns(40);
		dialogArea.setEditable(false);
		
		panel.add(dialogArea);
		pack();
		toFront();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try{
			ResultSet rs = PostManager.getPosts();
			while(rs.next()){
				dialogArea.append(rs.getString("id_placowki") + " " + rs.getString("nazwa") + " " + rs.getString("adres") + "\n");
			}
		}catch(SQLException e){
			dialogArea.append("Nie nawiazano polaczenia z baza");
		}
	}
	
	public static AdminPostList getInstance(){
		if(instance == null)
			instance = new AdminPostList();
		return instance;
	}

}
