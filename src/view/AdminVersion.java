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

import sql.VersionManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class AdminVersion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195985122129298274L;
	private JPanel contentPane;
	private JTextField prodField;
	private JTextField modelField;
	private JTextField vernameField;
	private JPanel buttonField;
	private JButton cancelButton;
	private JButton okButton;
	private static AdminVersion instance = null; 
	private JTextField costField;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField engineField;
	private JTextField displacementField;
	private JTextField powerField;
	private JTextField momentField;
	private JPanel panel_2;
	private JTextField suspField;
	private JTextField doorField;
	private JTextField paintField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminVersion frame = getInstance();
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
	private AdminVersion() {
		setTitle("Dodaj nowa wersje");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel newVersion = new JLabel("Dodaj nowa wersje");
		newVersion.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(newVersion);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		body.add(panel);
		
		prodField = new JTextField();
		panel.add(prodField);
		prodField.setText("producent");
		prodField.setToolTipText("Producent samochodu");
		prodField.setColumns(12);
		
		modelField = new JTextField();
		panel.add(modelField);
		modelField.setText("model");
		modelField.setToolTipText("Model samochodu");
		modelField.setColumns(10);
		
		vernameField = new JTextField();
		panel.add(vernameField);
		vernameField.setText("nazwa wersji");
		vernameField.setToolTipText("Nazwa wersji");
		vernameField.setColumns(8);
		
		costField = new JTextField();
		costField.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(costField);
		costField.setToolTipText("Cena wersji bez dodatkow w zl");
		costField.setText("cena");
		costField.setColumns(6);
		
		panel_1 = new JPanel();
		body.add(panel_1);
		
		engineField = new JTextField();
		engineField.setToolTipText("Typ silnika");
		engineField.setText("typ silnika");
		panel_1.add(engineField);
		engineField.setColumns(10);
		
		displacementField = new JTextField();
		displacementField.setToolTipText("Pojemnosc silnika w cm^3");
		displacementField.setText("pojemnosc");
		panel_1.add(displacementField);
		displacementField.setColumns(8);
		
		powerField = new JTextField();
		powerField.setToolTipText("Moc silnika w KM");
		powerField.setText("moc");
		panel_1.add(powerField);
		powerField.setColumns(6);
		
		momentField = new JTextField();
		momentField.setToolTipText("Moment obrotowy w Nm");
		momentField.setText("moment");
		panel_1.add(momentField);
		momentField.setColumns(6);
		
		panel_2 = new JPanel();
		body.add(panel_2);
		
		suspField = new JTextField();
		suspField.setToolTipText("Typ zawieszenia");
		suspField.setText("typ zawieszenia");
		panel_2.add(suspField);
		suspField.setColumns(10);
		
		doorField = new JTextField();
		doorField.setToolTipText("Ilosc drzwi w samochodzie");
		doorField.setText("il drzwi");
		panel_2.add(doorField);
		doorField.setColumns(6);
		
		paintField = new JTextField();
		paintField.setToolTipText("Typ lakieru wersji");
		paintField.setText("typ lakieru");
		panel_2.add(paintField);
		paintField.setColumns(12);
		
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
				String s1 = prodField.getText(), s2 = modelField.getText(), s3 = vernameField.getText(), s4 = costField.getText();
				String s5 = engineField.getText(), s6 = displacementField.getText(), s7 = powerField.getText(), s8 = momentField.getText();
				String s9 = suspField.getText(), s10 = doorField.getText(), s11 = paintField.getText();
				
				try {
					VersionManager.addVersion(s1, s2, s3, s5, s6, s7, s8, s9, s10, s11, s4);
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
	
	public static AdminVersion getInstance(){
		if(instance == null)
			instance = new AdminVersion();
		return instance;
	}

}
