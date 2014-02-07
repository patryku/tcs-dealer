package view;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientView extends JFrame {

	private static final long serialVersionUID = -3830536238771102821L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView frame = new ClientView();
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
	public ClientView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(80, 80, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnKonfigurator = new JButton("Konfigurator");
		btnKonfigurator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Configurator conf = new Configurator();
				conf.setVisible(true);
				conf.toFront();
			}
		});
		panel.add(btnKonfigurator);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnWyszukiwarkaModeli = new JButton("Wyszukiwarka modeli");
		btnWyszukiwarkaModeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarSearch cs = new CarSearch();
				cs.setVisible(true);
				cs.toFront();
			}
		});
		panel_1.add(btnWyszukiwarkaModeli);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnZamknij = new JButton("Zamknij");
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_2.add(btnZamknij);
	}

}
