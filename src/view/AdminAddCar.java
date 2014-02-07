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

import sql.AddCarManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class AdminAddCar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField vinField;
	private JTextField dateField;
	private JTextField przebiegField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminAddCar instance = null; 
	private JPanel panel;
	private JPanel panel_1;
	private JTextField versionField;
	private JTextField colorField;
	private JTextField postField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddCar frame = getInstance();
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
	private AdminAddCar() {
		setTitle("Dodaj nowego klienta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newAddCar = new JLabel("Dodaj nowy samochod");
		newAddCar.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newAddCar);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		body.add(panel);
		
		vinField = new JTextField();
		panel.add(vinField);
		vinField.setText("nr windykacyjny");
		vinField.setToolTipText("Numer windykacyjny");
		vinField.setColumns(15);
		
		dateField = new JTextField();
		panel.add(dateField);
		dateField.setHorizontalAlignment(SwingConstants.LEFT);
		dateField.setText("data produkcji");
		dateField.setToolTipText("Data produkcji samochodu");
		dateField.setColumns(10);
		
		przebiegField = new JTextField();
		panel.add(przebiegField);
		przebiegField.setText("przebieg");
		przebiegField.setToolTipText("Przebieg samochodu");
		przebiegField.setColumns(8);
		
		panel_1 = new JPanel();
		body.add(panel_1);
		
		versionField = new JTextField();
		versionField.setText("wersja");
		versionField.setToolTipText("Numer wersji samochodu");
		versionField.setColumns(8);
		panel_1.add(versionField);
		
		colorField = new JTextField();
		colorField.setToolTipText("Numer koloru samochodu");
		colorField.setText("kolor");
		panel_1.add(colorField);
		colorField.setColumns(4);
		
		postField = new JTextField();
		postField.setToolTipText("Placowka, do ktorej ma byc dodany samochod");
		postField.setText("placowka");
		panel_1.add(postField);
		postField.setColumns(6);
		
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
				String s1 = vinField.getText(), s2 = dateField.getText(), s3 = przebiegField.getText(), s4 = versionField.getText();
				String s5 = colorField.getText(), s6 = postField.getText();
				
				try {
					AddCarManager.addCar(s1, s2, s3, s4, s5, s6);
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
	
	public static AdminAddCar getInstance(){
		if(instance == null)
			instance = new AdminAddCar();
		return instance;
	}

}
