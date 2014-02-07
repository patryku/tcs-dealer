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

import sql.WyposManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminWypos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField verField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminWypos instance = null; 
	private JTextField addonField;
	private JTextField txtCena;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWypos frame = getInstance();
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
	private AdminWypos() {
		setTitle("Dodaj nowe akcesorium");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newWypos = new JLabel("Dodaj nowa opcje do samochodu");
		newWypos.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newWypos);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		verField = new JTextField();
		verField.setText("nr wersji");
		verField.setToolTipText("Numer wersji samochodu");
		body.add(verField);
		verField.setColumns(15);
		
		addonField = new JTextField();
		addonField.setToolTipText("Akcesorium");
		addonField.setText("dodatek");
		body.add(addonField);
		addonField.setColumns(10);
		
		txtCena = new JTextField();
		txtCena.setToolTipText("Koszt dodatku");
		txtCena.setText("cena");
		body.add(txtCena);
		txtCena.setColumns(10);
		
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
				String s1 = verField.getText(), s2 = addonField.getText(), s3 = txtCena.getText();
				
				try {
					WyposManager.addWypos(s1, s2, s3);
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
	
	public static AdminWypos getInstance(){
		if(instance == null)
			instance = new AdminWypos();
		return instance;
	}

}
