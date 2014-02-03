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

import sql.EngineManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminEngine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField typeField;
	private JTextField displacementField;
	private JTextField powerField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminEngine instance = null; 
	private JTextField momentField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminEngine frame = getInstance();
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
	private AdminEngine() {
		setTitle("Dodaj nowy silnik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newEngine = new JLabel("Dodaj nowy silnik");
		newEngine.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newEngine);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		typeField = new JTextField();
		typeField.setText("typ silnika");
		typeField.setToolTipText("Typ silnika");
		body.add(typeField);
		typeField.setColumns(15);
		
		displacementField = new JTextField();
		displacementField.setText("pojemnosc");
		displacementField.setToolTipText("Pojemnosc silnika w cm^3");
		body.add(displacementField);
		displacementField.setColumns(6);
		
		powerField = new JTextField();
		powerField.setText("moc");
		powerField.setToolTipText("Moc silnika w KM");
		body.add(powerField);
		powerField.setColumns(6);
		
		momentField = new JTextField();
		momentField.setToolTipText("Moment obrotowy silnika w Nm");
		momentField.setText("moment ");
		body.add(momentField);
		momentField.setColumns(6);
		
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
				String s1 = typeField.getText(), s2 = displacementField.getText(), s3 = powerField.getText(), s4 = momentField.getText();
				
				EngineManager.addEngine(s1, s2, s3, s4);
				dispose();
			}
		});
		buttonField.add(okButton);
		pack();
		toFront();
	}
	
	public static AdminEngine getInstance(){
		if(instance == null)
			instance = new AdminEngine();
		return instance;
	}

}
