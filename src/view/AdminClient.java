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

import sql.ClientManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminClient instance = null; 
	private JTextField mailField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminClient frame = getInstance();
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
	private AdminClient() {
		setTitle("Dodaj nowego klienta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newClient = new JLabel("Dodaj nowego klienta");
		newClient.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newClient);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		nameField = new JTextField();
		nameField.setText("nazwa klienta");
		nameField.setToolTipText("Nazwa Klienta");
		body.add(nameField);
		nameField.setColumns(15);
		
		addressField = new JTextField();
		addressField.setText("adres");
		addressField.setToolTipText("Adres klienta");
		body.add(addressField);
		addressField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setText("telefon");
		phoneField.setToolTipText("Telefon do klienta");
		body.add(phoneField);
		phoneField.setColumns(8);
		
		mailField = new JTextField();
		mailField.setToolTipText("Adres email klienta");
		mailField.setText("email");
		body.add(mailField);
		mailField.setColumns(10);
		
		buttonField = new JPanel();
		contentPane.add(buttonField, BorderLayout.SOUTH);
		buttonField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonField.add(cancelButton);
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s1 = nameField.getText(), s2 = addressField.getText(), s3 = phoneField.getText(), s4 = mailField.getText();
				
				try {
					ClientManager.addClient(s1, s2, s3, s4);
				} catch (SQLException e) {
					System.out.println(e);
				}
				dispose();
			}
		});
		buttonField.add(okButton);
		pack();
		toFront();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public static AdminClient getInstance(){
		if(instance == null)
			instance = new AdminClient();
		return instance;
	}

}
