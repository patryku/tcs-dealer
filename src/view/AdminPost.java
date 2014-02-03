package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;

import sql.PostManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class AdminPost extends JFrame {

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
	private static AdminPost instance = null; 
	private JTextField emailField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPost frame = getInstance();
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
	private AdminPost() {
		setTitle("Dodaj nowa placowke");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newEngine = new JLabel("Dodaj nowa placowke");
		newEngine.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newEngine);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		nameField = new JTextField();
		nameField.setText("nazwa placowki");
		nameField.setToolTipText("Nazwa Placowki");
		body.add(nameField);
		nameField.setColumns(15);
		
		addressField = new JTextField();
		addressField.setText("adres placowki");
		addressField.setToolTipText("Adres placowki");
		body.add(addressField);
		addressField.setColumns(15);
		
		phoneField = new JTextField();
		phoneField.setText("nnn-nnn-nnn");
		phoneField.setToolTipText("Telefon do placowki");
		body.add(phoneField);
		phoneField.setColumns(8);
		
		emailField = new JTextField();
		emailField.setHorizontalAlignment(SwingConstants.LEFT);
		emailField.setToolTipText("Adres e-mail placowki");
		emailField.setText("e-mail");
		body.add(emailField);
		emailField.setColumns(12);
		
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
				String s1 = nameField.getText(), s2 = addressField.getText(), s3 = phoneField.getText(), s4 = emailField.getText();
				
				PostManager.addPost(s1, s2, s3, s4);
				dispose();
			}
		});
		buttonField.add(okButton);
		pack();
		toFront();
	}
	
	public static AdminPost getInstance(){
		if(instance == null)
			instance = new AdminPost();
		return instance;
	}

}
