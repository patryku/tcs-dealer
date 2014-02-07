package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class AdminTool extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1092962765295738245L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminTool frame = new AdminTool();
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
	public AdminTool() {
		setTitle("Admin Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		contentPane.add(header, BorderLayout.NORTH);
		
		JLabel toolLabel = new JLabel("ADMIN TOOL 1337");
		toolLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(toolLabel);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		body.add(panel);
		
		JButton addClientButton = new JButton("Nowy klient");
		addClientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminClient.getInstance().setVisible(true);
			}
		});
		panel.add(addClientButton);
		
		JButton addPostButton = new JButton("Nowa placowka");
		addPostButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPost.getInstance().setVisible(true);
			}
		});
		panel.add(addPostButton);
		
		JButton addRepairButton = new JButton("Nowa naprawa");
		addRepairButton.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminRepair.getInstance().setVisible(true);
			}
		});
		panel.add(addRepairButton);
		
		JPanel panel_1 = new JPanel();
		body.add(panel_1);
		
		JButton addModelButton = new JButton("Nowy model");
		addModelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminModel.getInstance().setVisible(true);
			}
		});
		panel_1.add(addModelButton);
		
		JButton addSuspButton = new JButton("Nowe zawieszenie");
		addSuspButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSuspension.getInstance().setVisible(true);
			}
		});
		panel_1.add(addSuspButton);
		
		JButton addEngineButton = new JButton("Nowy silnik");
		addEngineButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminEngine.getInstance().setVisible(true);
			}
		});
		panel_1.add(addEngineButton);
		
		JButton addPaintButton = new JButton("Nowy kolor");
		addPaintButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPaint.getInstance().setVisible(true);
			}
		});
		panel_1.add(addPaintButton);
		
		JPanel panel_2 = new JPanel();
		body.add(panel_2);
		
		JButton addVerButton = new JButton("Nowa wersja");
		addVerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminVersion.getInstance().setVisible(true);
			}
		});
		panel_2.add(addVerButton);
		
		JButton addCarButton = new JButton("Dodaj samochod");
		addCarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminAddCar.getInstance().setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Utworz dodatek");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminWypos.getInstance().setVisible(true);
			}
		});
		panel_2.add(btnNewButton_1);
		panel_2.add(addCarButton);
		
		JPanel lookupField = new JPanel();
		body.add(lookupField);
		
		JButton postListButton = new JButton("Lista placowek");
		postListButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminPostList.getInstance().setVisible(true);
			}
		});
		lookupField.add(postListButton);
		
		JButton verSearchButton = new JButton("Szukaj wersji");
		verSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminSearchVer.getInstance().setVisible(true);
			}
		});
		lookupField.add(verSearchButton);
		
		JButton paintListButton = new JButton("Lista kolorow");
		paintListButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminPaintList.getInstance().setVisible(true);
			}
		});
		lookupField.add(paintListButton);
		
		JButton clientListButton = new JButton("Lista klientow");
		clientListButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminClientList.getInstance().setVisible(true);
			}
		});
		lookupField.add(clientListButton);
		
		JPanel buyCarField = new JPanel();
		body.add(buyCarField);
		
		JButton buyACarButton = new JButton("Kupno samochodu");
		buyACarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminBuyACar.getInstance().setVisible(true);
				
			}
		});
		
		JButton carlistButton = new JButton("Lista samochodow");
		carlistButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminAvailCarList.getInstance().setVisible(true);
			}
		});
		buyCarField.add(carlistButton);
		buyCarField.add(buyACarButton);
		
		JButton configButton = new JButton("Dodaj akcesorium");
		configButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminConfig.getInstance().setVisible(true);
			}
		});
		buyCarField.add(configButton);
		
		JPanel buttonField = new JPanel();
		contentPane.add(buttonField, BorderLayout.SOUTH);
		buttonField.setLayout(new BorderLayout(0, 0));
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonField.add(okButton, BorderLayout.EAST);
		
		JButton btnKonsolaDoRecznych = new JButton("Konsola do recznych zapytan do bazy");
		btnKonsolaDoRecznych.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminRaw.getInstance().setVisible(true);
			}
		});
		buttonField.add(btnKonsolaDoRecznych, BorderLayout.WEST);
		pack();
		toFront();
	}

}
