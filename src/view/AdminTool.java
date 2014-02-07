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
		
		JLabel toolLabel = new JLabel("ADMIN TOOL 2000");
		toolLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		header.add(toolLabel);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		body.add(panel);
		
		JButton addClientButton = new JButton("nowy klient");
		addClientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminClient.getInstance().setVisible(true);
			}
		});
		panel.add(addClientButton);
		
		JButton addPostButton = new JButton("nowa placowka");
		addPostButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPost.getInstance().setVisible(true);
			}
		});
		panel.add(addPostButton);
		
		JButton addRepairButton = new JButton("nowa naprawa");
		addRepairButton.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminRepair.getInstance().setVisible(true);
			}
		});
		panel.add(addRepairButton);
		
		JPanel panel_1 = new JPanel();
		body.add(panel_1);
		
		JButton addModelButton = new JButton("nowy model");
		addModelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminModel.getInstance().setVisible(true);
			}
		});
		panel_1.add(addModelButton);
		
		JButton addSuspButton = new JButton("nowe zawieszenie");
		addSuspButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSuspension.getInstance().setVisible(true);
			}
		});
		panel_1.add(addSuspButton);
		
		JButton addEngineButton = new JButton("nowy silnik");
		addEngineButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminEngine.getInstance().setVisible(true);
			}
		});
		panel_1.add(addEngineButton);
		
		JButton addPaintButton = new JButton("nowy kolor");
		addPaintButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPaint.getInstance().setVisible(true);
			}
		});
		panel_1.add(addPaintButton);
		
		JPanel panel_2 = new JPanel();
		body.add(panel_2);
		
		JButton addVerButton = new JButton("nowa wersja");
		addVerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminVersion.getInstance().setVisible(true);
			}
		});
		panel_2.add(addVerButton);
		
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
		pack();
		toFront();
	}

}