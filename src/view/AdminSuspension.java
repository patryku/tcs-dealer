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

import sql.SuspensionManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminSuspension extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField typeField;
	private JTextField doorField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminSuspension instance = null; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSuspension frame = getInstance();
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
	private AdminSuspension() {
		setTitle("Dodaj nowy typ zawieszenia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newSuspension = new JLabel("Dodaj nowy typ zawieszenia");
		newSuspension.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newSuspension);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		typeField = new JTextField();
		typeField.setText("typ zawieszenia");
		typeField.setToolTipText("Typ zawieszenia");
		body.add(typeField);
		typeField.setColumns(15);
		
		doorField = new JTextField();
		doorField.setText("drzwi");
		doorField.setToolTipText("Ilosc drzwi");
		body.add(doorField);
		doorField.setColumns(5);
		
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
				String s1 = typeField.getText(), s2 = doorField.getText();
				
				try {
					SuspensionManager.addSuspension(s1, s2);
				} catch (SQLException e) {
					System.out.println(e);
				}
				dispose();
			}
		});
		buttonField.add(okButton);
		pack();
		toFront();
	}
	
	public static AdminSuspension getInstance(){
		if(instance == null)
			instance = new AdminSuspension();
		return instance;
	}

}
