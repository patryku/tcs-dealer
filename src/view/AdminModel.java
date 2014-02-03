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

import sql.ModelManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminModel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField producentField;
	private JTextField modelField;
	private JTextField dateField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminModel instance = null; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminModel frame = getInstance();
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
	private AdminModel() {
		setTitle("Dodaj nowy model");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newModel = new JLabel("Dodaj nowy model");
		newModel.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newModel);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		producentField = new JTextField();
		producentField.setText("producent");
		producentField.setToolTipText("Nazwa producenta");
		body.add(producentField);
		producentField.setColumns(15);
		
		modelField = new JTextField();
		modelField.setText("model");
		modelField.setToolTipText("Nazwa modelu");
		body.add(modelField);
		modelField.setColumns(15);
		
		dateField = new JTextField();
		dateField.setText("yyyy-mm-dd");
		dateField.setToolTipText("Poczatek produkcji");
		body.add(dateField);
		dateField.setColumns(10);
		
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
				String s1 = producentField.getText(), s2 = modelField.getText(), s3 = dateField.getText();
				
				ModelManager.addModel(s1, s2, s3);
				dispose();
			}
		});
		buttonField.add(okButton);
		pack();
		toFront();
	}
	
	public static AdminModel getInstance(){
		if(instance == null)
			instance = new AdminModel();
		return instance;
	}

}
