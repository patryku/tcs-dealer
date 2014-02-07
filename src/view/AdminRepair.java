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

import sql.RepairManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JTextArea;

public class AdminRepair extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField vinField;
	private JTextField postField;
	private JTextField dateField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminRepair instance = null; 
	private JTextField costField;
	private JPanel panel;
	private JTextArea opisField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRepair frame = getInstance();
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
	private AdminRepair() {
		setTitle("Dodaj nowa naprawe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newRepair = new JLabel("Dodaj naprawe");
		newRepair.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newRepair);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		body.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		vinField = new JTextField();
		panel.add(vinField);
		vinField.setText("numer windykacyjny auta");
		vinField.setToolTipText("Numer windykacyjny samochodu");
		vinField.setColumns(17);
		
		postField = new JTextField();
		panel.add(postField);
		postField.setText("miejsce");
		postField.setToolTipText("Numer placowki, w ktorej odbylo sie serwisowanie");
		postField.setColumns(6);
		
		dateField = new JTextField();
		panel.add(dateField);
		dateField.setText("data");
		dateField.setToolTipText("Data serwisowania, yyyy-mm-dd");
		dateField.setColumns(8);
		
		costField = new JTextField();
		panel.add(costField);
		costField.setToolTipText("Koszt naprawy w zl");
		costField.setText("koszt");
		costField.setColumns(8);
		
		opisField = new JTextArea();
		opisField.setFont(new Font("Consolas", Font.PLAIN, 11));
		opisField.setText("Opis naprawy");
		opisField.setRows(10);
		opisField.setColumns(40);
		body.add(opisField, BorderLayout.CENTER);
		
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
				String s1 = vinField.getText(), s2 = postField.getText(), s3 = dateField.getText(), s4 = costField.getText(), s5 = opisField.getText();
				
				try {
					RepairManager.addRepair(s1, s2, s3, s4, s5);
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
	
	public static AdminRepair getInstance(){
		if(instance == null)
			instance = new AdminRepair();
		return instance;
	}

}
