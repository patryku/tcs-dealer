package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;

import sql.ConsoleManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AdminRaw extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JPanel buttonField;
	private JButton okButton;
	private static AdminRaw instance = null; 
	private JPanel panel;
	private JTextField consoleField;
	private JButton submitButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRaw frame = getInstance();
					frame.setVisible(true);
					frame.toFront();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private AdminRaw() {
		setTitle("Reczne zapytania");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel lblReczneUpdate = new JLabel("Reczne update");
		lblReczneUpdate.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(lblReczneUpdate);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		body.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		consoleField = new JTextField();
		panel.add(consoleField);
		consoleField.setColumns(40);
		
		submitButton = new JButton("submit");
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s1 = consoleField.getText();
				
				try {
					ConsoleManager.submit(s1);
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		});
		panel.add(submitButton);
		
		buttonField = new JPanel();
		contentPane.add(buttonField, BorderLayout.SOUTH);
		buttonField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonField.add(okButton);
		pack();
		toFront();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static AdminRaw getInstance(){
		if(instance == null)
			instance = new AdminRaw();
		return instance;
	}

}
