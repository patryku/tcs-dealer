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

import sql.PaintManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminPaint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField typeField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminPaint instance = null; 
	private JTextField colorField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPaint frame = getInstance();
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
	private AdminPaint() {
		setTitle("Dodaj nowy typ lakieru");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newPaint = new JLabel("Dodaj nowy typ lakieru");
		newPaint.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newPaint);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		typeField = new JTextField();
		typeField.setText("typ lakieru");
		typeField.setToolTipText("Typ lakieru");
		body.add(typeField);
		typeField.setColumns(15);
		
		colorField = new JTextField();
		colorField.setToolTipText("Kolor lakieru");
		colorField.setText("kolor lakieru");
		body.add(colorField);
		colorField.setColumns(10);
		
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
				String s1 = typeField.getText(), s2 = colorField.getText();
				
				try {
					PaintManager.addPaint(s1, s2);
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
	
	public static AdminPaint getInstance(){
		if(instance == null)
			instance = new AdminPaint();
		return instance;
	}

}
