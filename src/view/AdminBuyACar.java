package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import sql.BuyACarManager;

public class AdminBuyACar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3000881260757202233L;
	private JPanel contentPane;
	private static AdminBuyACar instance = null;
	private JTextField vinField;
	private JTextField priceField;
	private JTextField registerField;
	private JPanel header;
	private JLabel buyacarLabel;
	private JTextField dateField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField clientField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminBuyACar frame = new AdminBuyACar();
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
	private AdminBuyACar() {
		setTitle("Kupno samochodu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		buyacarLabel = new JLabel("Kupno samochodu");
		buyacarLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(buyacarLabel);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		vinField = new JTextField();
		vinField.setToolTipText("Numer windykacyjny samochodu");
		vinField.setText("nr windykacyjny");
		body.add(vinField);
		vinField.setColumns(15);
		
		registerField = new JTextField();
		registerField.setToolTipText("Numer rejestracyjny samochodu");
		registerField.setText("nr rejestracji");
		body.add(registerField);
		registerField.setColumns(15);
		
		clientField = new JTextField();
		clientField.setToolTipText("Numer klienta kupujacego");
		clientField.setText("numer klienta");
		body.add(clientField);
		clientField.setColumns(15);
		
		priceField = new JTextField();
		priceField.setToolTipText("Cena sprzedazy");
		priceField.setText("cena");
		body.add(priceField);
		priceField.setColumns(15);
		
		dateField = new JTextField();
		dateField.setToolTipText("Data zakupu");
		dateField.setText("data");
		body.add(dateField);
		dateField.setColumns(15);
		
		buttonField = new JPanel();
		contentPane.add(buttonField, BorderLayout.SOUTH);
		
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
				String s1 = vinField.getText(), s2 = registerField.getText(), s3 = dateField.getText(), s4 = priceField.getText(), s5 = clientField.getText();
				try {
					BuyACarManager.perform(s1, s2, s3, s4, s5);
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
	
	public static AdminBuyACar getInstance(){
		if(instance == null)
			instance = new AdminBuyACar();
		return instance;
	}
	
}
