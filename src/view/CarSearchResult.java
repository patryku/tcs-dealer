package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CarSearchResult extends JFrame {

	private static final long serialVersionUID = -4381954315618058221L;
	private JPanel contentPane;
	private JTable resultTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarSearchResult frame = new CarSearchResult(new Vector<String>());
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
	public CarSearchResult(Vector<String> res) {
		setTitle("Wyniki wyszukiwania");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane resultPane = new JScrollPane();
		contentPane.add(resultPane, BorderLayout.CENTER);
		
		resultTable = new JTable();
		resultTable.setAutoCreateRowSorter(true);
		resultTable.setModel(new DefaultTableModel(
			new Object[res.size()][11],
			new String[] {
				"producent", "model", "wersja", "typ silnika", "pojemnosc", "moc", "moment obr", "nadwozie", "liczba drzwi", "lakier", "cena od"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class, Integer.class, 
				Integer.class, String.class, Integer.class, String.class, Double.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		resultTable.setFillsViewportHeight(true);
		resultTable.setOpaque(false);
		resultPane.setViewportView(resultTable);
		for(int i = 0; i < res.size(); i++) {
			String[] attr = res.get(i).split(",");
			for(int j = 0; j < 11; j++) {
				Class<?> cl = resultTable.getColumnClass(j);
				if(cl.equals(String.class))
					resultTable.setValueAt(attr[j], i, j);
				else if(cl.equals(Integer.class))
					resultTable.setValueAt(Integer.parseInt(attr[j]), i, j);
				else
					resultTable.setValueAt(Double.parseDouble(attr[j]), i, j);
			}
				
		}
		
		JPanel footer = new JPanel();
		contentPane.add(footer, BorderLayout.SOUTH);
		footer.setLayout(new BorderLayout(0, 0));
		
		JButton btnZamknij = new JButton("Zamknij");
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		footer.add(btnZamknij);
	}

}
